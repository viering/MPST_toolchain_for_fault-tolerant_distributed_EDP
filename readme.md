
### Implementation of our toolchain presented in our OOPSLA paper

---

## A Multiparty Session Typing Discipline for Fault-tolerant Event-driven Distributed Programming

*Malte Viering, Raymond Hu, Patrick Eugster and Lukasz Ziarek*

This artifact demonstrates our toolchain for **fault-tolerant event-driven
distributed programming in Scala**.
Our toolchain implements our theory of fault-tolerant multiparty session types
(MPSTs) developed in this paper.

This artifact contains:

- An **Overview** of the artifact (i.e., this document).
- The toolchain implementation in this repository.
- The [Session-CM](https://github.com/viering/spark_cm_use_case) use case.
- The artifact as a pre-build [Docker image](https://drive.google.com/file/d/1YAVYupSGN3mhwlx2bQtAqSmVK09HKsD_/view?usp=sharing). 
- The `Dockerfile` used to build the above image.

This document is structured as follows:

- [**<u>&#35;1. Getting Started Guide</u>**](#S1): set up and test the artifact
  ("kick the tires").
- [**<u>&#35;2. Step by Step Instructions</u>**](#S2): verify the claims made in
  the paper.
- **Appendices**
  - [**<u>&#35;A.1.</u>**](#A1) Build the Docker image from the `Dockerfile`.
  - [**<u>&#35;A.2.</u>**](#A2) Run the TPC-H benchmark in a distributed
    setting.


**Acknowledgements**.
We thank Cheng-En Chuang, Grant Iraci and the reviews of the artifact submission for testing this artifact.


---

### <a name="NOTES"></a>Notes on syntax and other minor differences between the paper and this artifact.

*The reader may skip over these details on their first pass and refer back later
if and as needed.*

Regarding **global type** syntax and definitions:

- In the paper, we use the `root` keyword to declare the root/main subprotocol
  of a global type.  
  In this artifact, the toolchain does not use the `root` keyword: it assumes
  the root/main subprotocol is named `Main`.

- In the paper, we use the `assign` keyword to declare that a participant
from role set is assigned to play a role when this subsession is initiated.  
  The artifact uses an `_` (underscore) instead of the `assign` keyword.

- Our toolchain implementation extends the definition of global-to-local type
  projection in the paper (Sec. 4.2) to permit more flexible branching
  patterns.
  - For a branch, say, `a -> b { l₁: ..., l₂: ... }`,
    our implementation allows a third party `c` to engage in different nested
    *input* branches in each case provided they both receive from the *same
    sender* as the outer branch, i.e., `a`, and have disjoint labels; the
    projection onto `c` is given by merging the nested input branches.
    For all other kinds of actions (e.g., spawn, recursion), the projection onto
    `c` must be the same in all cases (as in the paper).
    - E.g., Our implementation allows projecting the following onto `c`
      ```c
      a -> b {
        l₁: a -> c: l₃ ...,
        l₂: a -> c { l₄: ..., l₅: ... }
      }
      ```
      yielding: `a ? { l₃: ..., l₄: ..., l₅: ... }`.  
      We use this extension in the full Session-CM use case (but not the
      simplified version presented in the main sections of the paper).

Regarding our session **event handlers** and **event loops** in Scala:  
Here, let `E` stand for a session **event** type, `C` for a session **channel**
type, `D` for a plain **data** object type, `M` for a **message** type, and `T` for
an arbitrary type, as represented in Scala by our
toolchain.
(See Sec. 3 in the paper for explanations of these concepts.)

- Syntax for defining **event handlers**:
  - In the paper, we use the syntax: `𝜆(E₁, E₂) { case (D, C) => ... }`  
    In this artifact, the corresponding syntax is:
    `𝜆_state(E₁, E₂) { case (D, C) => ... }`, i.e., an additional `_state` suffix
  - Our toolchain library provides further variants for defining handlers.
    E.g.
    - Omitting the data object: `𝜆(E₁, E₂) { case C => ... }`, i.e., no `_state` suffix
    - Handling a single event/action (and omitting the data object): `𝜆(E) { case (C) => ... }`
    - Subsession spawn handlers: `𝜆_static_state(E) { case (D, C) => ... }`

- **I/O methods** supported by session channel types:
  - In the paper, receive method signatures for input-typed channels are
    specified as: `?(): (M, C)`, i.e., method name `?`  
    In this artifact, the corresponding signature is:
    `rcvXYZ(): (M, C)`, where `XYZ` is substituted for the
    relevant role or roleset.
  - Our toolchain provides additional method signature variants for handling
    receive events.
    E.g.
    - To handle the received message using a nested callback function that
      consumes the messge and the (implicit) channel continuation:  
      `?[T](f : PartialFunction[M, C, T]) : T`, here the method *is* named `?`
    - A convenience method that simply returns the continuation channel
      (discarding the received message payload, if any):
      `channelCon : C`
  - In the paper, spawn-typed channels have an explicit `init` method.  
    The toolchain allows to define single-event handlers, which are called on spawns. While performing the actual spawn implicit as defined by the formalism.
  - In the paper, failure suspicion channels have a `failure` method.  
    In this artifact, this method is named `failed_r_R`, where `r` is
    substituted for the name of the role suspected of failure and `R` for its
    roleset.

- Additional kinds of **event handlers**:  
  Using our toolchain, event loops are implemented by inheriting from
  a generated subclass of the `event_lang.dsl.AbstractEndPoint` class.
  - All the kinds of event handlers described in the paper are supplied to the
    event loop by populating this inherited attribute:  
    `val receive: Seq[HDL[D]]`, i.e., a list of the kinds of handlers seen in
    the paper (as created by the `𝜆` methods).
  - In this artifact, our toolchain supports additional kinds of event handlers
    not featured in the paper:
    - Handlers for customizing how the promoted participant is picked during a
      subsession spawn can be added to this inherited attribute:  
      `val pickHandler : Seq[PickHandler[D]]`
    - Handlers for subsession completion events can be added to this inherited
      attribute:  
      `val subFinishHandler : Seq[FinishSpawnHandler[D]]`


---

---

## <a name="S1"></a>&#35;1. <u>Getting Started Guide</u>

### <a name="S1_1"></a>&#35;1.1. Set Up the Artifact

We describe two main ways to set up the artifact:

1. Load the provided Docker image using `docker load`: explained next.
2. Alternatively, build the image yourself from the `Dockerfile`: see
   [**<u>&#35;A.1</u>**](#A1).


---

### <a name="S1_1_1"></a>&#35;1.1.1 Load the Docker Image and Start a Container

*Prerequisite*: Docker -- e.g., see
["<u>Orientation and setup</u>"](https://docs.docker.com/get-started/).  
**Note**: The commands listed in this document are modulo execution privileges, e.g.,
prefix with `sudo` as appropriate.

0. Check Docker is running.
1. Enter the directory containing `mpst-toolchain-fault-tolerant-distributed-edp.tar` (as extracted from the docker image archive).
2. In that directory, do:
   ```c
   docker load < mpst-toolchain-fault-tolerant-distributed-edp.tar
   ```
   - **N.B.** If Docker complains `no space left on device`, check if your
     system has limited Docker's access to disk space; if so, try expanding it.
3. Start a container with an interactive terminal session:
   ```c
   docker run -it mpst-toolchain-fault-tolerant-distributed-edp:submission
   ```


---

### <a name="S1_2"></a>&#35;1.2. Main Directories in the Artifact

<table>
<tr>
<td><strong>Directory</strong></td>
<td></td>
<td><strong>Path</strong></td>
<td><strong>Env var</strong></td>
</tr>
<tr>
<td>Toolchain</td>
<td>Base dir</td>
<td><code>/code/git/session_event_types</code></td>
<td><code>&#36;TOOL_DIR</code></td>
</tr>
<tr>
<td></td><td>(Main) source dir&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><code>$TOOL_DIR/core/src/main/scala</code></td>
<td><code>&#36;TOOL_SRC</code></td>
</tr>
<tr>
<td>Misc. examples</td>
<td></td>
<td><code>&#36;TOOL_DIR/examples/src/main/scala/example</code>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><code>&#36;EXAMPLE_DIR</code></td>
</tr>
<tr>
<td>Apache Spark</td>
<td></td>
<td><code>/code/git/edp_session_spark</code></td>
<td><code>&#36;SPARK_DIR</code></td>
</tr>
<tr>
<td>Session-CM use case</td>
<td>Base dir</td>
<td><code>&#36;SPARK_DIR/resource-managers/session-cm</code></td>
<td><code>&#36;SESSION_CM_DIR</code></td>
</tr>
<tr>
<td></td>
<td>Source dir</td>
<td><code>&#36;SESSION_CM_DIR/src/main/scala</code></td>
<td><code>&#36;SESSION_CM_SRC</code></td>
</tr>
<tr>
<td>TPC-H benchmark suite&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td></td>
<td><code>&#36;SPARK_DIR/tpch-spark</code></td>
<td><code>&#36;TPCH_DIR</code></td>
</tr>
</table>

- The base directory of our **toolchain** repository is `$TOOL_DIR`.
  - This document gives several copy-pasteable commands to be executed in this
    directory, e.g., for compiling and executing the toolchain and examples via
    the `sbt` build tool for Scala (cf. the `build.sbt` file there).
  - **Global types** (top-level protocol specifications) for all examples
    are contained in:  
    `$TOOL_SRC/event_lang/codegen/GlobalTypes.scala`
  - Scala **endpoint programs** for all examples (except the Session-CM use
    case, see next) are in subdirectories of `$EXAMPLE_DIR`.


- Our main use case is **Session-CM**, a session-typed cluster
  manager for Apache Spark (see [**<u>#2</u>**](#S2)).
  - The base repository for Apache Spark is at `$SPARK_DIR`.
  - The base directory of our Session-CM use case is `$SESSION_CM_DIR`, a
    subdirectory of the above.
      - We again give copy-pasteable commands -- for this use case we also use
        `mvn` (cf. `pom.xml` in this directory).
      - The global types for this use case are contained in the global types
        file mentioned earlier.
      - The endpoint programs are in subdirectories (e.g., `Master`, `worker`)
        of:  
        `$SESSION_CM_SRC/org/apache/spark/deploy/`


---

### <a name="S1_3"></a>&#35;1.3. Test the Artifact (Kick the Tires)

This artifact has been tested on:

- A Windows 10 laptop, CPU@1.8GHz, 16 GB RAM  
- Ubuntu 20.04.2 LTS VM, on a MacBook Pro with an Intel(R) Core(TM) i9-9880H CPU@2.30GHz and 32GB Ram.
- A MacBook Pro with an Intel(R) Core(TM) i9-9880H CPU@2.30GHz and 32GB Ram. Using the following Docker resource settings: CPUs:4, Memory:4GB, Swap 1 GB and Disk image size: 60GB. 

The artifact has `vim` and `nano` preinstalled.
It is set to the root user by default; you can easily install further
software by, e.g., `apt-get install`.


#### Test the tool chain and main paper examples.

1. Enter the toolchain directory.
   ```c
   cd $TOOL_DIR
   ```
2. Build the toolchain (there may be a few warnings which you can disregard):
   ```c
   sbt compile
   ```
3. Run all tests:
   ```c
   sbt test
   ```
   The above tests all the examples described in the *main sections* of
   the paper apart from the Session-CM use case (see next).
   It will essentially perform all the steps in [**&#35;2.3**](#S2_3) and
   [**&#35;A.3**](#A3) (and some other small tests) in one go.  
   It may take a while to complete.
   The final output should look like:
   ```c
   ...
   [info] Run completed in 43 seconds, 957 milliseconds.
   [info] Total number of tests run: 46
   [info] Suites: completed 12, aborted 0
   [info] Tests: succeeded 46, failed 0, canceled 0, ignored 0, pending 0
   [info] All tests passed.
   [success] Total time: 50 s, completed Jul 7, 2021, 10:40:30 AM
   ```


#### Test the Session-CM use case.

1. Enter the Session-CM directory.
   ```c
   cd $SESSION_CM_DIR
   ```
2. Build the Session-CM (there will be some warnings in yellow text):
   ```c
   mvn clean compile 
   ```
3. Run some basic example applications as tests (may take a while):
   ```c
   mvn test 
   ```
   There will be a lot of console output including warnings and `assertion
   failed` outputs in **red** (some tests intentionally cause endpoints to fail
   using `assert(false)`).
   The final output should look like:
   
   ```c
   ...
   Run completed in 3 minutes, 11 seconds.
   Total number of tests run: 8
   Suites: completed 2, aborted 0
   Tests: succeeded 8, failed 0, canceled 0, ignored 0, pending 0
   All tests passed.
   [INFO]  ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   [INFO]  ------------------------------------------------------------------------
   [INFO] Total time:  03:22 min
   [INFO] Finished at: 2021-07-09T15:24:51+02:00
   [INFO]  ------------------------------------------------------------------------
   ```


---

---

## <a name="S2"></a>&#35;2. <u>Step by Step Instructions</u>

Fig. 2 in the paper depicts the main stages of our toolchain.
In a nutshell, a *user* performs two main steps:

1. Specify the multiparty protocol as a **global type**.
    - The toolchain checks the global type (according to our Multiparty Session
      Type theory presented in the paper) and generates a family of
      **event-driven, MPST-based APIs** for implementing each endpoint kind in
      this protocol in Scala.
2. Implement the **endpoint programs** in Scala using the generated APIs.

As discussed in the paper:

- Our presented prototype combines static Scala type checking with a few
  lightweight dynamic checks to guarantee that such an endpoint program will
  never perform an I/O action that is not compliant with the protocol other than
  premature termination, e.g., due to failure.
  The dynamic checks performed by our prototype are:
    - A one-time check on endpoint initialization that the registered session
      event handlers *cover* all possible events.
    - Some cases of checking *linear* usage of session channels (i.e., one
      I/O action is performed on each session channel instance) -- note, a
      violation can be treated simply as a failure.

The remainder of this section demonstrates the claims from the paper regarding
our prototype implementations of our toolchain and event-driven multiparty
session runtime.

- [**<u>&#35;2.1.</u>**](#S2_1) **Session-CM Use Case**  
  We use our toolchain and session runtime to specify, implement and run a
  session-typed cluster manager component for
  [<u>Apache Spark</u>](https://spark.apache.org/).
  It is compatible with other standard Spark Core components, and supports the
  execution of existing third-party Spark applications without code
  modification.


- [**<u>&#35;2.2.</u>**](#S2_2) **Spark-CM vs. Session-CM Performance Evaluation**  
  We compare the execution time of using our session-typed cluster manager and
  session runtime to execute the [<u>TPC-H</u>](http://www.tpc.org/tpch/)
  [<u>benchmark</u>](https://github.com/ssavvides/tpch-spark) against the standard
  standalone cluster manager of Spark.


- [**<u>&#35;2.3.</u>**](#S2_3) **Further Examples from MPSTs Literature**  
  We use our toolchain to specify, implement and execute examples from MPSTs
  literature that we adapt/extend with crash failure handling.


- [**<u>&#35;2.4.</u>**](#S2_4) **Misc. Comments regarding our Prototype Implementation**  
  We demonstrate comments regarding, e.g., the dynamic checks mentioned above.

Each subsection lists <u>Main steps</u> and <u>Optional steps</u>.


---

### <a name="S2_1"></a>&#35;2.1. Use Case: Session-CM

The main running example in our paper is a session-typed version of Apache
Spark's
[<u>standalone cluster manager</u>](https://spark.apache.org/docs/latest/cluster-overview.html).
The version presented in, e.g., Sec. 2 and 3 of the paper is a simplified
version for brevity.
As stated in the paper, we use a "full version" (included in **App. G** of
the paper) for our performance evaluation (Sec. 7 of the paper) -- see
[**<u>&#35;2.2</u>**](#S2_2) for claims specifically regarding the latter.

- In this document, we refer to full version of our session-typed cluster
  manager as "**Session-CM**".

- Claims from the paper:

  > 235: *Fig. 1 specifies a global type for a simplified but representative
    version of the Spark-CM, ...; **our full version (App. G) is compatible with
    the actual Spark framework and supports existing third-party Spark
    applications**.*

  > 1047:  *Our Session-CM is not just a “toy” – it is **compatible with the
    other Spark Core components** and supports **the execution of existing
    third-party Spark applications without any code modification***


<u>Main steps</u>:

1. Generate the Endpoint APIs from the Session-CM global type for all the
   endpoint kinds in this protocol:
   ```c
   cd $TOOL_DIR
   sbt "generateTypes SesCMPaperTypes org.apache.spark.deploy.types ${SESSION_CM_SRC}/org/apache/spark/deploy/types/SesCMPaperTypes.scala -p SESSION_CM_FULL"
   ```
   
   - (Optional) &nbsp;
     The following command shows the global type in the artifact:
     The three endpoint kinds are visible as *rolesets* in the `Main`
     subprotocol: Master `M`, Worker `W`, and ZooKeeper `ZK`.
     ```c
     sed -n '/val SESSION_CM_FULL/,/"};}"/p' $TOOL_SRC/event_lang/codegen/GlobalTypes.scala
     ```


2. Compile all the endpoint programs and the generated APIs:
   ```c
   cd $SESSION_CM_DIR
   mvn compile
   ```


3. Run all the above, together with existing Spark Core components, to give a
   functional Spark runtime -- here, we use it to execute the Scala version of
   the [<u>Pi Estimation</u>](https://spark.apache.org/examples.html) Spark
   application provided in the standard Spark 
   [<u>distribution</u>](https://github.com/apache/spark/blob/master/examples/src/main/scala/org/apache/spark/examples/SparkPi.scala).

   1. Enter the Session-CM directory:
      ```c
      cd  $SESSION_CM_DIR
      ```
       
   2. Bootstrap the Session-CM and execute Pi Estimation (expect a lot of
      output):
      ```c
      mvn exec:java -Dexec.mainClass="org.apache.spark.deploy.Bootstrap" \
          -Dexec.args="2 556 1 org.apache.spark.examples.SparkPi 2"
      ```
      
      - The source code for Pi Estimation being executed above is here:  
        `$SPARK_DIR/examples/src/main/scala/org/apache/spark/examples/SparkPi.scala`
   3. Inspect the output of Pi Estimation:
      ```c
      ./lastDriverOutput.sh
      ```
      We provide the above script for inspecting the most recent console
      output produced by our Session-CM.
      It simply shows the last 10 lines of printed by any Spark application
      Driver to `stdout`.  
      The output should look like (the main application output is on the last
      line):
      ```c
      ######
      ###	We will print the last 10 lines of the most recent stdout created by any driver (in the outfolder)
      ######

          Some(1),
          None,
          "root",
          ArraySeq()
        )
      )
      offer
      target num: 2
      current ex: 0
      Pi is roughly 3.1436157180785904
      ``` 
      
      - The full output can be found in: `$SESSION_CM_DIR/output/`.  
        (This directory is created by running the Session-CM.)
        The above script specifically shows the last 10 lines from the file
        `drv_X_[time]/stdout`, where `X` is the most recently
        executed (i.e., highest) application instance identifier and `[time]`
        is a timestamp.

The above steps demonstrate:

- Our Session-CM running together with other preexisting Spark Core
  components, e.g., the components for running
  [<u>Driver</u>](https://github.com/apache/spark/blob/master/core/src/main/scala/org/apache/spark/deploy/worker/DriverRunner.scala)
  and
  [<u>Executor</u>](https://github.com/apache/spark/blob/master/core/src/main/scala/org/apache/spark/deploy/worker/ExecutorRunner.scala)
  processes.  
  - The code for these third-party components being executed in this artifact is
    at
    `$SPARK_DIR/core/src/main/scala/org/apache/spark/deploy/worker/DriverRunner.scala`
    and `ExecutorRunner.scala` with the same directory prefix.
- The resulting Spark runtime being used to run an existing third-party Spark
  application, Pi Estimation, without code modification.
  The next section demonstrates another (considerably larger) such third-party
  application, a Spark version of the TPC-H benchmark suite.



<u>Optional steps</u>:

1. Inspect the global type for the Session-CM in the artifact.
   (It may also be compared against the version in App.G of the paper -- note,
   the order of the subprotocols may differ).  
   *Reminder*: this command shows the global type in the artifact:
   ```c
   sed -n '/val SESSION_CM_FULL/,/"};}"/p' $TOOL_SRC/event_lang/codegen/GlobalTypes.scala
   ```


2. View the generated API code for this example (warning: not designed to be 
   human readable):
   ```c
   cat $SESSION_CM_SRC/org/apache/spark/deploy/types/SesCMPaperTypes.scala
   ```
   

3. View the Scala endpoint programs (written assuming the generated APIs).
   E.g.,
   
   - Master -- implements the "endpoint kind" (roleset) `M` in the
     global type:
     ```c
     less $SESSION_CM_SRC/org/apache/spark/deploy/Master/SessionMaster.scala
     ```
     The core structure of this program can be compared at a high level against
     the minimal Scala version illustrated in Fig. 3(d) of the paper -- note,
     however, the former is for the "full" Session-CM whereas the latter is for
     the simplified version in the main sections of the paper (i.e., their
     global types differ), so there are many differences in their details.
     The (full) Session-CM includes communications omitted from the simplified
     version.
       - In the above, the event loop implementation starts with
       `override val receive: Seq[HDL[StateM]] = ELoop ...` on line 364.
     - E.g., Fig. 3 of the paper includes the event handlers
       `𝜆(SndAddEx, SndAddEx)` and `𝜆(SndOk, SndOk)`.  
       These correspond to the handlers
       `λ_state(PExSchedule_m_M.SndStartExCase)` and
       `λ_state(PExSchedule_m_M.SndEnd)` around lines 593--617 (i.e.,
       `SndAddEx`&#126;`SndStartExCase` and `SndOk`&#126;`SndEnd`) .

   - Worker -- implements the "endpoint kind" (roleset) `W` in the
     global type:
     ```c
     less $SESSION_CM_SRC/org/apache/spark/deploy/worker/SessionWorker.scala
     ```
     Again, the above can be compared at a high level against the (formal)
     simplified version given in the paper, but there are many differences.
     - In the above, the event loop implementation starts with
       `override val receive: Seq[HDL[StateM]] = ELoop ...` on line 256.
     - Sec. B.4.2 of the paper includes the handlers (in formal noation)
       `[m?l_InitEx]λx. x[m]?l_InitEx` and `[m!l_ExDone]λx. x[m]!l_ExDone`.  
       These correspond to the handlers
       `λ_state(PExecutor_wEx_W.RcvStartEx, PExecutor_wEx_W.SndExStarted)` on
       line 361 and `λ_state(PExecutor_wEx_W.SndExDone)` on line 423
       (i.e., `l_InitEx`&#126;`StartEx` and `l_ExDone`&#126;`ExDone`; the send
       `PExecutor_wEx_W.SndExStarted` in the full version is omitted from the
       simplified version.).


As mentioned above, the Session-CM output folder for all application runs is:
`$SESSION_CM_DIR/output/`.  
This folder will contain subdirectories `Drv_X_[time]` and `Ex_Y_Drv_X_[time]`,
where `X` is an application instance identifier (just an internal counter
value -- each application features a single Driver), and `Y` is an Executor
identifier (also just an internal counter value).
`[time]` is the timestamp when the directory is created, which corresponds to
when that process is assigned its Driver or Executor role.
The output of the Driver and Executors in each application is written
to, e.g., `stdout` and `stderr` files in these subdirectories.
The output format is the same as if the application is executed using Spark's
own standalone cluster manager.


---

### <a name="S2_2"></a>&#35;2.2. Spark-CM vs. Session-CM Performance Evaluation

Terminology:

- **Spark-CM** refers to the standard
  [<u>standalone cluster manager</u>](https://spark.apache.org/docs/latest/cluster-overview.html)
  provided by the
  [<u>Scala version</u>](https://github.com/apache/spark/blob/1c3bdabc03117494ffbf8fd6863ea82d4961379b/core/src/main/scala/org/apache/spark/deploy/master/Master.scala)
  of Spark.
  (Cf. Sec. 2.1 of the paper.)
- **Session-CM** refers to the full version of our session-typed Spark cluster
   manager (as in [**<u>&#35;2.1.</u>**](#S2_1)).

Sec. 7 of the paper presents a performance evaluation that compares the
execution time for queries from a
[<u>Spark implementation</u>](https://github.com/ssavvides/tpch-spark) of the
[<u>TPC-H benchmark suite</u>](http://www.tpc.org/tpch/) using our Session-CM
against the Spark-CM.

- Claims from the paper:

  > 78: *We use a Spark implementation of the industry-standard **TPC-H
    benchmark suite** [(TPC) 2020] to test and **evaluate the runtime
    performance of our session-typed CM**.*
  > *The results show our prototype implementation incurs an average overhead
    below 10%.*

  > 1027: *...we compare the performance of the full version of our Session-CM
    (App. G), running over our **prototype session runtime**, to Spark's default
    CM.*

  > 1050: *We use a Spark implementation [Savvides 2020] of the
    industry-standard TPC-H benchmark [(TPC) 2020] as benchmark application.
    ...; we use a database scaling factor of 10 (i.e., database size ∼10GB).
  > Each benchmark run measures the **total time (including application startup
    time) to execute one query as an independent application** using **(a) our
    Session-CM and session runtime**, and **(b) the Spark-CM**, for scheduling;
    all other factors are the same.*

For this artifact, we explain as the main steps how to:

- inspect the benchmark programs, scripts and configuration parameters;
- run the benchmarks on a standalone Spark cluster *locally* inside the
  artifact (i.e., all within the Docker container) on a smaller pregenerated
  data set.
  
The main steps of this artifact do *not*:

- execute the benchmarks on a dataset of size ~10GB;
- nor execute the benchmarks in a *distributed* setting;
- and thus do not directly attempt to verify the "*average overhead below 10%*"
  result under those parameters, as presented in the paper.

The primary contribution of our paper is our MPST theory for safe development
of fault-tolerant, event-driven applications and its formal properties.
As such, the main application development toolchain is the core focus of this
practical artifact, and our implementation of the session runtime is a (rather
primitive) prototype needed to execute the resulting programs.
The performance evaluation is not essential to the
central results of this paper.  
However, we provide an optional guide for interested reviewers on how to
configure and run the benchmarks in a distributed setting outside of the
artifact.
Doing so will require manual configuration of scripts and access to multiple
servers (e.g., an appropriate cluster).  


<u>Main steps</u>:

a) **Session-CM**. &nbsp;
   1. The following command:
      
      - starts our Session-CM;
      - uses the Session-CM to execute (a subset of the queries of) the TPC-H
        benchmark locally on a pregenerated dataset of ~100 MB;
      - and shuts down the Session-CM.  
      ```c
      cd $SESSION_CM_DIR 
      mvn exec:java -Dexec.mainClass="org.apache.spark.deploy.test.LaunchLocalTPCHCluster" \
          -Dexec.args="3 1 2"
      ``` 
      The three numerical arguments are, in this order:
      
      - the number of times to **repeat** each of the preset TPC-H queries (an
        integer &#8805;1);
      - the **first** query number in the range of queries to execute (&#8805;1 and
        &#8804;22);
      - the **last** query number in the range of queries to execute (&#8805;
        **first** and &#8804;22).
        
      Even for three iterations and two queries (as in the above example), the
      execution will take a while.  
      **N.B.** There will be a lot of console output, potentially including
        text in red -- that is fine.

   2. Inspect the final output.
      It should look like (potentially mixed/appended with some other lines):
      ```c
      ...
      Times: 
      Q1;Q2
      30012580676;91731883937
      33551245231;110361015987
      35723775012;112096264834

      FINISHED;FINISHED
      FINISHED;FINISHED
      FINISHED;FINISHED
      ...
      ```
      - The first line lists the executed query numbers separated by semicolons.
      - The next set of lines list the execution time in
        nanoseconds for each query per repetition, i.e., there is one line per
        repetition.  
      - The final set of lines confirm the completion of each query; there is one
        line per repetition.


b) **Spark-CM.** &nbsp;
   
   1. Start a local Spark cluster and run TPC-H.
      - The three numerical arguments are the same as for the Session-CM case
        above.
      - **N.B.** There will be a lot of console output, potentially including
        text in red -- that is fine.
      ```c
      cd $SESSION_CM_DIR
      ./startSparkCM.sh
      ./runSparkCMLocalTPCh.sh 3 1 2
      ```
  
   2. Inspect the final output.
     Similarly to the Session-CM case, it should look like: 

       ```c
       ...
       Times: 
       Q1;Q2
       20866499270;43482266892
       21145146177;49919284426
       20850886543;48331329896
       ```

   2. Stop the Spark cluster.
      In the same directory as for (i) above:
      ```c
      ./stopSparkCM.sh 
      ```  
c) (Optional) &nbsp;
   Compare the TPC-H output (i.e., the results of the application queries, not
   the benchmark times) from both cluster managers.
   1. Run the script that prints the result of a specified query for both CMs
      ```c
      cd $SESSION_CM_DIR 
      ./lastBenchEvalRes.sh 01
      ```
      The argument `01` above is the query number and the leading `0` is
      required for numbers below 10.
   2. Compare the output.
      ```c
      ######
      ###	We will print the result of the provided query (must be spezified as 01-22) of both CMs
      ######

      Q01.dat

      Result from Session CM (last 10 lines):

      [...OMITTED...]

      Result from Spark CM (last 10 lines):

      [...OMITTED...]

      Difference between those two (via diff):
      ```
       There should be *no* output after `Difference between those two (via diff):`


**Notes on the above benchmarks and the results.**

- We find the overheads of our Session-CM compared to the Spark-CM (i.e.,
  increase in TPC-H execution time) are more pronounced in this local artifact
  deployment than in the distributed setting presented in the paper.
  We believe one reason is that our Session-CM and prototype runtime are not
  optimized and are more CPU intensive than the Spark-CM -- and that the local
  setting (i.e., all session endpoints and runtimes sharing the same resources)
  exacerbates these overheads in comparison to the distributed setting.
  However, it is tricky to determine exactly how the local Docker
  environment is affecting performance overall.
  As stated in the paper, the results presented in Sec. 7 were taken in the
  following distributed environment:

  > 1055: *...each Spark application (query) has three servers with identical
    hardware (Intel Xeon E-2278G CPUs, 64GB RAM) and running Ubuntu 18.04.3
    LTS.*
   
- The deployment mode is slightly different between Session-CM and Spark-CM as
  executed by the above scripts.
  - Session-CM executes the Spark application in "cluster mode": as specified
    in the global type, the Master assigns both the Driver process and all
    Executor processes dynamically.
  - Spark-CM executes the Spark application in "client mode": the
    Driver is statically assigned on startup by the user (not the Master), and only the executors are
    dynamically assigned by the Master (as in the second part of cluster mode).
    
  Should this difference impact the overall performance, we believe it benefits
  Spark-CM in terms of shorter execution times (i.e., our presented results are
  conservative) because "cluster mode" basically involves extra steps that 
  "client mode" does not.  
  We specified and implemented Session-CM following Spark's "cluster mode"
  because it is the more complex mode and offers a better test of the
  expressiveness and practicality of our theory.
  Spark-CM of course supports "cluster mode", but we were not able to reliably
  determine the exact completion time of an application in this mode.
  Hence we opted for our current conservative approach.


<u>Optional steps</u>

1. Inspect the main benchmark scripts.
   The scripts used both to run the main steps above and obtain the results
   presented in the paper are located at:  
   
   - Session-CM:  
     `$SESSION_CM_SRC/org/apache/spark/deploy/launcher/SubmitBenchmark.scala`  
   - Spark-CM:   
     `$SESSION_CM_SRC/org/apache/spark/deploy/sparkLauncher/SparkBench.scala`
     
   The following commands show their main loops:
   
   - Session-CM:
     ```c
     sed -n '/Main benchmark loop/,/end benchmark/p' \
         $SESSION_CM_SRC/org/apache/spark/deploy/launcher/SubmitBenchmark.scala
     ```
   - Spark-CM:
     ```c
     sed -n '/Main benchmark loop/,/Finish benchmark/p' \
         $SESSION_CM_SRC/org/apache/spark/deploy/sparkLauncher/SparkBench.scala
     ```
     
   Both scripts:
   
   - Execute the same TPC-H suite (installed at `$TPCH_DIR`).
   - Measure the time from just before the cluster manager is called to start
     the application, to just after the cluster manager reports the application
     is completed.
   - Their main loops iterate over the specified TPC-H queries and number of
     repetitions, executing each repetition of a query as an independent
     application, and saving the result.
   - Both expect that their respective cluster managers are already running --
     the all-in-one commands in the main steps start the relevant cluster
     manager and script in one go.


2. The pregenerated ~100 MB dataset used by the main steps was generated by
   setting the TPC-H database scaling factor to 0.1.
   It was generated by the Dockerfile.  
   The following command shows the generated table files:
   ```c
   ls -Slahs $TPCH_DIR/dbgen/ | grep tbl
   ```


3. (Extra optional) &nbsp;
   Run the benchmarks outside the main artifact in a distributed setting.
   See [**<u>&#35;A.2</u>**](#A2).




---

### <a name="S2_3"></a>&#35;2.3. Further Examples from MPSTs Literature

- Claims from the paper:

  > 91: *We use our Scala toolchain to specify and implement a **selection of
  examples from MPSTs literature** ... The **full source code of our toolchain
  and all examples/benchmarks** will be submitted as an artifact.*

  > 1019: *Fig. 10 (left) summarizes further examples from MPST literature that
  we have specified and implemented (with added failure handling) using our
  Scala toolchain to demonstrate its expressiveness. The three main takeaways,
  corresponding to the top three sections, are that our system: **(1) subsumes
  the core communication constructs of standard MPSTs**; **(2) by necessity
  supports communication patterns previously limited to relatively exotic MPST
  features**; and **(3) can express MPST-based application-level exception and
  interrupt patterns**. The full source code of these examp les will be included
  in an artifact together with our prototype and benchmarks (∼5k lines of
  code).*

Fig. 10 (left) in Sec. 7 of the paper lists further examples from MPSTs
literature that we adapt/extend with crash failure handling.
We summarise the examples again below.

- *Reminder*: The global types are all contained in this file:
  `$TOOL_SRC/event_lang/codegen/GlobalTypes.scala`  
  - To view the global type of a given example, search in that file for
    the specified constant identifier, e.g., `TWO_BUYER`.
    Look for the string constant pointed to by this identifier; it defines the
    global type.
- See Fig. 10 (left) in the paper for references to the original versions of
  these examples in the MPSTs literature.

<table border-collapse="collapse">
<tr><td>
<strong>Category / Example</strong>
&nbsp;&nbsp;&nbsp;&nbsp;
</td><td>
<code>sbt</code> <strong>Tag</strong>
</td><td>
<strong>Main source code</strong> (G=Global type; E=Endpoint programs)
</td></tr><tr><td colspan="3"; style="border-bottom: 1px solid black; border-top: 1px solid black">
&nbsp;(1) Core MPSTs
</td></tr><tr><td valign="top">
2-Buyers
</td><td valign="top">runTwoBuyer</td><td>
G: <code>TWO_BUYER</code>
<br>
E: <code>&#36;EXAMPLE_DIR/two_buyer/TwoBuyerImpl.scala</code>
</td></tr><tr><td valign="top">
Streaming
</td><td valign="top">runStreaming</td><td>
G: <code>STREAMING</code>
<br>
E: <code>&#36;EXAMPLE_DIR/streaming_popl_08/StreamingEndpoinsImpl.scala</code>
</td></tr><tr><td valign="top">
Sutherland-Hodgman
&nbsp;&nbsp;
</td><td valign="top">runDSH</td>
<td>
G: <code>DSH</code>
<br>
E: <code>&#36;EXAMPLE_DIR/dsh/DSHImpl.scala</code>
</td>
</tr><tr><td colspan="3" style="border-bottom: 1px solid black; border-top: 1px solid black">
&nbsp;(2) Dynamic/parameterized participants
</td></tr><tr><td valign="top">
3-Buyers
</td><td valign="top">runThreeBuyer</td>
<td>
G: <code>THREE_BUYER</code>
<br>
E: <code>&#36;EXAMPLE_DIR/three_buyer_global_progress/ThreeBuyerImpl.scala</code>
</td></tr><tr><td valign="top">
𝑁-stage Pipe
</td><td valign="top">runNPipe</td>
<td>
G: <code>PIPE</code>
<br>
E: <code>&#36;EXAMPLE_DIR/pipe/PipeEP.scala</code>
</td></tr><tr><td valign="top">
𝑁-stage Ring
</td><td valign="top">runNRing</td>
<td>
G: <code>RING</code>
<br>
E: <code>&#36;EXAMPLE_DIR/ring/RingEP.scala</code>
</td>
</tr><tr><td colspan="3" style="border-bottom: 1px solid black; border-top: 1px solid black">
&nbsp;(3) Application-level exceptions/interrupts
</td></tr><tr><td valign="top">
Two Factor
</td><td valign="top">runTwoFactor</td>
<td>
G: <code>twoFactor</code>
<br>
E: <code>&#36;EXAMPLE_DIR/ExceptionalAsynST_POPL/twoFactor/EPsTwoFactor.scala</code>
</td></tr><tr><td valign="top">
Resource Control
</td><td valign="top">runResControl</td>
<td>
G: <code>resAccessControl</code>
<br>
E: <code>&#36;EXAMPLE_DIR/resourceAccessControl/EPsResControll.scala</code>
</td></tr><tr><td valign="top">
WebCrawler
</td><td valign="top">runWebCrawler</td>
<td>
G: <code>webCrawler</code>
<br>
E: <code>&#36;EXAMPLE_DIR/LetItRecover/webcrawler/WebCrawlerEps.scala</code>
</td></tr><tr><td valign="top">
Interruptible
<br>
3-Buyers
</td><td valign="top">runInterruptibleThreeBuyer</td>
<td>
G: <code>InterruptableThreeBuyer</code>
<br>
E: <code>&#36;EXAMPLE_DIR/globalescape/buyer/GEscapeBuyerEPs.scala</code>
</td>
</tr><tr><td colspan="3" style="border-bottom: 1px solid black; border-top: 1px solid black">
&nbsp;(4) Crash failure handling
</td></tr><tr><td valign="top">
Failure-Aware
<br>
Streaming
</td><td valign="top">runFailureAwareStreaming
&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
G: <code>EXAMPLE</code>
<br>
E: <code>&#36;EXAMPLE_DIR/esop_crash_handling/streaming/EPs.scala</code>
</td>
</tr>
</table>



<u>Main steps</u>:

1. The following executes all the examples in the above table. 
   It is also executed as part of the `sbt test` command in
   [**<u>1.3</u>**](#S1_3).
   ```c
   cd $TOOL_DIR
   sbt runTestAllExample
   ```
      
   The output should look like:
   ```c
   [info] PaperExampleTests:
   [info] - 2 Buyer
   [info] - Streaming
   [info] - Sutherland-Hodgman
   [info] - 3-Buyers
   [info] - N State Pipe
   [info] - Ring
   [info] - Two Factor
   [info] - Resource Control
   [info] - WebCrawler
   [info] - Interruptible 3 Buyer
   [info] - Interruptible 3 Buyer (one interrupt)
   [info] - Failure-Aware Streaming
   [info] Run completed in 17 seconds, 688 milliseconds.
   [info] Total number of tests run: 12
   [info] Suites: completed 1, aborted 0
   [info] Tests: succeeded 12, failed 0, canceled 0, ignored 0, pending 0
   [info] All tests passed.
   [success] Total time: 20 s, completed Jul 3, 2021, 4:49:38 AM
   ```


<u>Optional steps</u>:

1. To compile and run each example individually:
   ```c
   cd $TOOL_DIR
   sbt [Tag]
   ```
   where `[Tag]` is as specified in the above table.
   E.g., `sbt runTwoBuyer` for 2-Buyers.
   - **N.B.** When run this way, the `runTwoBuyer` and `runThreeBuyer` examples
     prompt for interactive user input (enter integer values and press enter).
     

2. To view the generated API code, look for an accompanying file ending
   `...Types.scala` alongside the source file specified in the above table, or
   in a subdirectory of the specified directory.
   E.g., `$EXAMPLE_DIR/two_buyer/TwoBuyerTypes.scala`  
   **N.B.** As mentioned earlier, the generated API code is currently not
   generated with human readability in mind.
   
    - To generate the APIs for all the examples in the above table without
      compiling or running them:
      ```c
      sbt generateExampleTypes
      ```




---

### <a name="S2_4"></a>&#35;2.4. Misc. Comments regarding our Prototype Implementation



The following considers additional (minor) comments from the paper and actions
to demonstrate them in the artifact if required.


- Implementation of the presented theory as a practical toolchain.

  > 341: *For practical programming, we implement our theory as a toolchain in
    Scala.*

  - This artifact as a whole demonstrates the implementation of our theory as a
    practical toolchain in Scala.
    Further comments regarding minor differences between the presented theory
    and our prototype implementation follow below.


- Dynamic checking of event coverage on endpoint initialization.

  > 411: *Our prototype implementation **checks*** [**event coverage, and that
    each handler is non-blocking apart from the initial event,**]
    ***dynamically** when the list of handlers is registered (i.e., when the
    endpoint is first initialized)*
   
  - The following runs a version of 2-Buyers that has the handler for
    role `b1`'s first receive action (`Price1`) commented out.
    ```c
    cd $TOOL_DIR
    sbt runTwoBuyerCovError
    ```
    The run-time output due to the failed endpoint initialization should look
    like:
    ```c
    No matching handler for EPTwoBuyer_b1_Buyer.RcvPrice1
    [error] (run-main-0) java.lang.RuntimeException: Coverage test failed
    [error] java.lang.RuntimeException: Coverage test failed
    [error] 	at event_lang.semantic.CoverageTest.check(CoverageTest.scala:167)
    ...
    ```
  - To view the exact differences between this version and the original:
    ```c
    diff $EXAMPLE_DIR/two_buyer/TwoBuyerImpl.scala \
        $EXAMPLE_DIR/faulty/twoBuyer/coverage/TwoBuyerImplFaulty.scala
    ```

- Decoupling of the failure detection mechanism from event loops.

  > 419: *Following our theory, **our prototype separates the mechanism for
    failure detection from the event loop** itself. The former may be
    implemented in various ways (e.g., heartbeats); the latter simply reacts to
    a failure suspicion event by switching to the failure handling CFSM and
    firing the suspicion event handler (e.g., `SuswD`).*

  - Our runtime implements the operational semantics of our session event
    loops (Sec. 5 in the paper) on top of this
    interface: `event_lang.network.SessionChannelEP` (in
    `$TOOL_SRC/event_lang/network/SessionChannelEP.scala`).  
    This interface represents a transport layer channel that is capable of
    multiplexing multiple application layer subsession channels.
    It declares (abstract) methods for sending a message on a given
    subsession, accessing a subsession input queue, subsession spawning,
    failure signalling, and retrieving the current set of participant process
    identifiers *suspected* of failure.
    The latter method may be implemented using various failure detection
    mechanisms, and thus decouples event loops from the underlying
    implementation of this interface.
    The specific method in question is:
    ```c
    def failed_Ids: Set[Int]
    ```

- Dynamic checking of linear channel usage.

  > 459: *Our theory integrates linearity into the static type system, as
    standard in session types.  Our implementation **checks linearity at
    runtime** [Castro-Perez et al. 2019; Padovani 2017] and can **safely treat
    violations as failures**.*
   
  - In more detail:
    - Our prototype implementation *dynamically* asserts that
      *output*-typed channels are used *at most* once.
      See below for an example of such a violation detected at run-time.  
      Note, the return type of an event handler can only be *statically*
      satisfied by actually performing the action(s) *at least* once in
      order to return a value of that type (i.e., the continuation session
      channel).
      In any case, violating linearity ultimately either leads to premature
      termination, or a violation of *progress* but not *safety*.
    - The API operations for *input*-typed and *failure suspicion*
      channels are generated to be implicitly *idempotent*, e.g., repeat
      invocations of a receive method on the same channel instance simply
      return the same (already received) message.

  - The following runs a version of 2-Buyers that has duplicated the line of
    code where role `b1` sends the `Request` message.
    ```c
    cd $TOOL_DIR
    sbt runTwoBuyerLinError
    ```
    The output should look like the below.
    It demonstrates:
    - the linearity violation by `b1` dynamically detected as an assertion
      failure;
    - the participant processes of role `s` (`b1`'s monitor) and roleset `B`
      (which includes the process of role `b2`) reacting to and safely handling
      `b1`'s linearity violation as a failure.
      The relevant failure handlers in the remaining participants print a
      message (`CancelMain`) and end the session safely.
      Note: the order of this output is non-deterministic.
      ```c
      ...
      [s] CancelMain was triggered
      [B] CancelMain was triggered
      [error] (Thread-2) java.lang.AssertionError: assertion failed: The channel send musted be used linear
      [error] java.lang.AssertionError: assertion failed: The channel send musted be used linear
      [error] 	at scala.Predef$.assert(Predef.scala:223)
      [error] 	at example.two_buyer.TwoBuyer$Buyer$TwoBuyer_b1_Buyer$__SndRequestImp.$bang(TwoBuyerTypes.scala:477)
      ...
      ```
  - To view the exact differences between this version and the original:
    ```c
    diff $EXAMPLE_DIR/two_buyer/TwoBuyerImpl.scala \
        $EXAMPLE_DIR/faulty/twoBuyer/linearity/TwoBuyerImplFaulty.scala
    ```


- Distributed monitoring tree and fail set environments.

  > 664: *We can model* [the monitoring tree and fail set] *environments in a
    global fashion since their **decomposition into local views (as implemented
    in our prototype)** is straightforward*

  - The **monitoring tree** tracks the parent-child relationship between
    subsession instances at run-time.
    It is used, e.g., to stop activities in descendent subsessions when a
    failure handler gets activated.  
    Our implementation maintains and uses this information fully locally
    at each endpoint, i.e., based solely on the subsession spawn/close events
    that the endpoint engages in locally.
    The class `event_lang.semantic.OperationalSemantic`
    (in `$TOOL_SRC/event_lang/semantic/OperationalSemantic.scala`)
    records this information in the local variable `config` locally at each
    endpoint.
  - The **fail set** records the current set of participant process identifiers
    suspected of failure.  
    Our implementation populates this information locally at each endpoint
    solely from information received asynchronously over the network (cf. the
    `failed_Ids` method of the `SessionChannelEP` interface discussed earlier).
    The class `event_lang.semantic.OperationalSemantic` records this information
    in the local variable `known_failures` locally at each endpoint, and uses it
    to, e.g., activate failure handling.


- Spawn handler firing.

  > 834: *Our prototype (Sec. 7) does **fire spawn handlers***

  - See, e.g.,
    `$EXAMPLE_DIR/streaming_popl_08/StreamingEndpoinsImpl.scala`
    or
    `$EXAMPLE_DIR/three_buyer_global_progress/ThreeBuyerImpl.scala`
    -- the handlers defined using `λ_static` are spawn handlers.  
    - E.g., the output of the following includes output produced
      by a spawn handler (the messages containing `...Spawned...')`: 
      (Note, running `runThreeBuyer` this way prompts for interactive user
      input.)
      ```c
      cd $TOOL_DIR
      sbt runThreeBuyer
      ```


---

---

## <a name="A"></a>&#35;A. <u>Appendix</u>

### <a name="A1"></a>&#35;A.1. Build the Docker Image from the `Dockerfile`

*Prerequisite*: Docker -- e.g., see
["Orientation and setup"](https://docs.docker.com/get-started/).

1. Copy the provided `Dockerfile` to a new directory, and build the image with:
   ```c
   docker build --tag oopsla21-artifact5:submission .
   ```
2. Start a container with an interactive terminal session:
   ```c
   docker run -it oopsla21-artifact5:submission
   ```
3. You may now continue as per [**<u>&#35;1.3</u>**](#S1_3) and
   [**<u>&#35;2</u>**](#S2).


---

### <a name="A2"></a>&#35;A.2. Run the TPC-H Benchmark in a Distributed Setting

This is a rough guide for running the TCP-H benchmark for both the Session-CM
and Spark-CM in a *distributed* setting, i.e., outside of the aritfact (cf.
[**<u>#2.2</u>**](#S_2)).
*Requirements*: filling in some blanks in the instructions, manual changes to
shell scripts and configuration files, access to four servers, and time
(building Spark).
*Please feel free to contact us for help!*

1. **Build and deploy**: our toolchain, TPC-H (dataset and benchmark programs),
   Spark-CM and Session-CM.
   
   - Basically: manually adapt and follow all the steps in the provided
     `Dockerfile`, including the GitHub SSH key steps, on each server.
     The goal is to have Spark with the Spark-CM and Session-CM, TPC-H and our
     toolchain present on every server.
     - The Dockerfile commands assume root priveleges, so adjust as appropriate.
     - Use `./dbgen -s 10` (i.e., database scaling factor 10) instead of `0.1`
       as in the Dockerfile to generate the same sized dataset as used in our
       paper.
       Be sure to do this at every server that hosts Worker processes.
     - In the `application.conf` step, adapt the parameters in that file to
       your setting.
       Be sure to do this before the actual build step (`mvn clean compile`).  
     -  As in the Dockerfile, be sure to do the `distributedJars.sh` step to
        distribute the build jars.
   - **N.B.**, `mvn test` (cf. [**<u>#1.3</u>**](#S1_3)) will *not* work in
     this deployment.


2. **Run**: Session-CM
   
   - Start the Session-CM cluster:
     - In the `$SPARK_DIR` repository, look in the
       `/resource-managers/session-cm/bscripts` directory for the scripts we
       use for the Session-CM evaluation.
       Adapt the (hardcoded) parameters in those scripts for your setting: e.g.,
       IP addresses, file paths and ssh configuration entries.
   - Run the benchmark:
       * Modify the following command accordingly
       ```c
       cd ~/git/edp_session_spark/resource-managers/session-cm; mvn exec:java \
           -Dexec.mainClass="org.apache.spark.deploy.launcher.SubmitBenchmark" \
           -Dexec.args="10.167.6.100 8900 submit 10.167.6.100 8007 -cores 12 -memEx 10000 -coresEx 6 -dMem 12000 -dCores 6 -iter 10 -startQ 1 -endQ 22 main.scala.TpchQuery /home/dspadmin/git/edp_session_spark/tpch-spark/dbgen"
       ```
   - For the Executor failure experiment mentioned in the paper, see the
     `bench_crash.sh` script.


3. **Run**: Spark-CM
   
   - See `startStandaloneCM.sh` for starting Spark-CM.
     See [**<u>#2.2</u>**](#S2_2) for an explanation why Spark-CM and Session-CM
     use a slightly different cluster layout.
   - On one of the servers that hosts *neither* the Spark-CM Master nor a
     Spark-CM Worker, run (adapting as needed):
    ```c
    mvn exec:java -Dexec.mainClass="org.apache.spark.deploy.sparkLauncher.SparkBench" \
      -Dexec.args="--class main.scala.TpchQuery \
      --master spark://10.167.6.100:7077 \
      --deploy-mode client --driver-memory 12000M \
      --executor-memory 10000M \
      --total-executor-cores 12 \
      --start-query 1 \
      --end-query 21 \
      --num-iter 10 \
      --sub-path /home/dspadmin/git/edp_session_spark/bin/spark-submit" \
          > logFile 2>1 &
    ``` 
   - For the Executor failure experiment mentioned in the paper, see the
     `crash_bench_script.sh` script.



#### Troubleshooting

- **Session-CM tests/evaluations hang or fail.** Docker under macOS (and Windows) runs inside a VM with a fixed (and potentially insufficient) amount of CPU, Memory, and disk space allocated.


    - Potential fix: Assign more resources to the Docker VM on macOS (or Windows).

        + Open the Docker preferences (e.g., right-click the Docker icon in the menu bar, then Preferences...)
        + Open the Resources tab
        + Set the resources to, e.g., (we believe these are "safe" values):
            + CPU 4
            + Memory: 16 GB
            + Disk image Size 60 GB
        + Apply the changes and restart docker (i.e., Apply & Restart)
        (You can restore the original values after finishing with this artifact)



- **The initial Netty bootstrap hangs.**
  Our toolchain uses Netty for distributed communications.
  We implemented a bootstrap system for the preliminary step of distributing the
  endpoint IP addresses and establish point-to-point connections.
  Unfortunately, this initial step can hang in rare cases.
  To clarify, however, this is just a preliminary step to setup the cluster --
  it does not relate to the main evaluation itself or impact the results
  presented in the paper.
  - *Fix*: restarting the application will also restart the bootstrapping
    (e.g., restart the current `sbt`/`mvn` tasks).
- **Port in use error.**
  Our Netty bootstrap (and also Spark-CM) uses fixed port values.
  - *Fix*: Kill all existing toolchain, Session-CM, Spark or Spark-CM related
    applications (e.g., to kill any orphan tasks) with `pkill -f java` (all
    these components run on the JVM), and restart the desired tasks.
- **Test time limit exceeded.**
  Most test cases have a time limit after which they fail (in general, the
  tests are not expected to timeout).
  - *Fix*: Reduce the load on the machine and restart the tests.
    Alternatively, increase the timeout time: e.g., in the class of the
    timed-out test (`sbt` will report the class), increase the value of the
    `timeLimit` constant.
- **Tips.** 
  - Use fully qualified paths. 
    Avoid using `~` around Session-CM and Spark-CM, and Spark applications
    executed using either of those.
  - Restarting a failed task is generally a potential fix should any bug
    occur.  
    E.g., `pkill -f java`, and repeat the desired command.


---

---





