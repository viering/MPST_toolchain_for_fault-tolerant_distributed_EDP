FROM ubuntu:20.04


ARG JAVA_VERSION="11.0.11.hs-adpt"
ARG DEBIAN_FRONTEND=noninteractive
ARG GIT_FOLDER="/code/git"
ENV TZ=Europe/Berlin
RUN apt-get update
RUN apt-get install -y
RUN apt-get install curl zip unzip git maven make build-essential vim nano htop -y

RUN curl -s https://get.sdkman.io | bash


RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
    yes | sdk install java $JAVA_VERSION && \
    yes | sdk install sbt"




RUN mkdir -p -m 0600 ~/.ssh && ssh-keyscan github.com >> ~/.ssh/known_hosts

#  SSH KEY Management
COPY ./deployKeys/toolchain root/.ssh/edp_session
COPY ./deployKeys/spark_usecase root/.ssh/spark_cm
RUN chmod 600 $HOME/.ssh/edp_session
RUN chmod 600 $HOME/.ssh/spark_cm
# clone repos
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
    cd / && mkdir code && cd code && mkdir git && cd git &&\
    GIT_SSH_COMMAND='ssh -i /root/.ssh/edp_session -o IdentitiesOnly=yes' git clone git@github.com:viering/MPST_toolchain_for_fault-tolerant_distributed_EDP.git"
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
    cd /code/git &&\
    GIT_SSH_COMMAND='ssh -i /root/.ssh/spark_cm -o IdentitiesOnly=yes' git clone git@github.com:viering/spark_cm_use_case.git"

#Build Spark
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
        cd /code/git/spark_cm_use_case && \
        build/mvn install -DskipTests -Dcheckstyle.skip"
#Build TPC H benchmark
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
        cd /code/git/spark_cm_use_case/tpch-spark/dbgen && \
        make && \
        yes | ./dbgen -s 0.1 && \
        cd /code/git/spark_cm_use_case/tpch-spark && \
        sbt package"

# Build and publish session lang locally
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
        cd /code/git/MPST_toolchain_for_fault-tolerant_distributed_EDP && \
        sbt package && \
        sbt publishM2"

#Build Session CM
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
            cd /code/git/spark_cm_use_case/resource-managers/session-cm && \
            mv src/main/resources/application_docker.conf src/main/resources/application.conf && \
            mvn clean compile && \
            mvn test"

RUN bash -c "cd /code/git/spark_cm_use_case/resource-managers/session-cm && \
             chmod +x distributedJars.sh && \
             ./distributedJars.sh"


#Setup env variables
ENV GIT_FOLDER="/code/git"
ENV GIT_DIR="/code/git"
ENV SPARK_DIR="/code/git/spark_cm_use_case"
ENV SESSION_CM_DIR="/code/git/spark_cm_use_case/resource-managers/session-cm"
ENV SESSION_CM_SRC="${SESSION_CM_DIR}/src/main/scala"
ENV TPCH_DIR="/code/git/spark_cm_use_case/tpch-spark"
ENV TOOL_DIR="/code/git/MPST_toolchain_for_fault-tolerant_distributed_EDP"
ENV TOOL_SRC="${TOOL_DIR}/core/src/main/scala"
ENV EXAMPLE_DIR="/code/git/MPST_toolchain_for_fault-tolerant_distributed_EDP/examples/src/main/scala/example"


CMD ["/bin/bash"]