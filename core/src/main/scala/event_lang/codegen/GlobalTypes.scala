package event_lang.codegen

import event_lang._
import event_lang.types.LocalTypes.AllEndPointTypes
import event_lang.types.Projection
import intern.parser.ProtocolParser.parseString

object GlobalTypes {


  object TestProtocols {

    object NotProjectable {
      val faultyOne: String = {
        "{" +
          "Main(a : A, b : B, c : C, _d : D, D) = {" +
          "   a -> b : {" +
          "     L1() : a-> c : M().0," +
          "     L2() : 0" +
          "   }" +
          " with " +
          "   d@a. a->b :FM().a->c :FM1().a->D :FM2().0" +
          "};" +
          "}"
      }
      val faultyTwo: String = {
        "{" +
          "Main(a : A, b : B, c : C, _d : D, D) = {" +
          "   a -> b : {" +
          "     L1() : a-> c : M().0," +
          "     L2() : b -> c : M1(). a -> c : M2().0" +
          "   }" +
          " with " +
          "   d@a. a->b :FM().a->c :FM1().a->D :FM2().0" +
          "};" +
          "}"
      }
      val faultyThree: String = {
        "{" +
          "Main(a : A, b : B, c : C, _d : D, D) = {" +
          "   a -> b : {" +
          "     L1() : a-> c : M().0," +
          "     L2() : a-> c : M(). a -> c : M2().0" +
          "   }" +
          " with " +
          "   d@a. a->b :FM().a->c :FM1().a->D :FM2().0" +
          "};" +
          "}"
      }
      val faultyNotMergeableOne: String = {
        "{" +
          "Main(a : A, b : B, _c : C, D) = {" +
          "   mu t." +
          "   a -> b : {" +
          "     L1() : a-> c : M().0," +
          "     L2() : a -> c : {" +
          "         M1() : 0," +
          "         M2() : t" +
          "   }" +
          "   }" +
          " with " +
          "   c@a. a->b :FM().a->D :FM2().0" +
          "};" +
          "}"
      }
      val faultyNotMergeableTwo : String = {
        "{" +
          "Main(a : A, b : B, _c : C, D) = {" +
          //"   mu t." +
          "   a -> b : {" +
          "     L1() : a-> c : M().0," +
          "     L2() : a -> c : {" +
          "         M1() : 0," +
          "         M() : 0" +
          "   }" +
          "   }" +
          " with " +
          "   c@a. a->b :FM().a->D :FM2().0" +
          "};" +
          "}"
      }
    }

      val SndIntoBranch: String = "{" +
        "Main (a : A, _b : B) = {" +
        " a -> b : M(). a -> b : {" +
        "   B1() : a -> b : {" +
        "         B11() : 0," +
        "         B12() : 0" +
        "         }," +
        "   B2() : a -> b : B21().0" +
        "}" +
        " with " +
        "   b@a. 0" +
        "};}"
      val BranchInFActivation: String = "{" +
        "Main (a : A, _b : B, B) = {" +
        " a -> b : M()." +
        " b -> a : M1().0" +
        " with " +
        "   b@a. a -> B : {" +
        "   F1() : 0," +
        "   F2() : 0" +
        "}" +
        "};}"
      val RestartInFhandling: String = "{" +
        "RestartP (a : A, b: B, _bb : B, B) = {" +
        " a -> b : M()." +
        " b -> a : M1().0" +
        " with " +
        "   bb@a. a -> B : {" +
        "   F1() : spawn RestartP(a,b,_B,B).0," +
        "   F2() : 0}" +
        "};" +
        "Main (a : A, _b : B, B) = {" +
        " spawn RestartP(a,b,_B,B).0" +
        " with " +
        "   b@a. a -> B : FEnd(). 0" +
        "};}"

      val SIMPLE_REC: String = "{" +
        "Main (a : A, _b : B) = {" +
        " mu t. a -> b: M(v : Int). t" +
        " with " +
        "   b@a. 0" +
        "};}"

      val SIMPLE_SPAWN: String = "{" +
        "GOne (a : A, b : B, _bb : B) = {" +
        "   a -> b: MSGAtoB(). bb -> a : MSGOne(). b -> a : MSGTwo().0" +
        " with " +
        "   bb@a. a -> b : FailGOne().0" +
        "};" +
        "Main (a : A, _b : B,B) = {" +
        "   a -> B: MSGMCast(). spawn GOne(a,b,_B).0" +
        " with " +
        "   b@a. a -> B : FailMain().0" +
        "};}"

      //This is not proejctable -- but could be save
      val PRJ_MERGE: String = {
        "{" +
          "Main(a : A, b : B, c : C, _d : D, D) = {" +
          "   a -> b : {" +
          "     L1() : a-> c : M().0," +
          "     L2() : spawn Foo(a,b,c, _D,D).0," +
          "     L3() : b -> a : M().0" +
          "   }" +
          " with " +
          "   d@a. a->D :FM().0" +
          "};" +
          "}"
      }


      val PING_PONG: String = "{" +
        "Main (a : A, _b : B) = {" +
        "mu t. a -> b : Ping(m:String). b -> a : Pong(m :String). t" +
        " with " +
        "b@a. 0" +
        "};}"

      //A.PING_PONG_Spawn_a_A.RcvStart
      val PING_PONG_Spawn: String = "{" +
        "PingPong (a : A, b : B, _bb : B) = {" +
        "bb -> a : Start(). mu t. a -> b : Ping(m:String). b -> a : Pong(m :String). t" +
        " with " +
        "bb@a. a -> b : FailD().0" +
        "};" +
        "Main (a : A, _b : B,B) = {" +
        "a -> b : LMain(). spawn PingPong(a,b,_B).0" +
        " with " +
        "b@a. a -> B : FailB().0" +
        "};}"

      val PING_PONG_Spawn_Branch: String = "{" +
        "PingPong (a : A, b : B, _bb : B) = {" +
        "bb -> a : Start(). mu t. " +
        "a -> b : {" +
        "Ping(m:String) : a -> bb : PingBrn(). b -> a : Pong(m :String). t," +
        "End() : a -> bb : EndBrn(). 0" +
        "}" +
        " with " +
        "bb@a. a -> b : FailD().0" +
        "};" +
        "Main (a : A, _b : B,B) = {" +
        "a -> b : LMain(). spawn PingPong(a,b,_B).0" +
        " with " +
        "b@a. a -> B : FailB().0" +
        "};}"
    }

    object OurPaper {
      val VALUE_BRANCHING: String = "{" +
        "Main (s : S, _b : B,B) = {" +
        "   s -> b: Book(price : Int)." +
        "   b -> s : {" +
        "      OK() : 0," +
        "      No() : 0}" +
        " with " +
        "   b@s. s -> B : FailMain().0" +
        "};}"

      val RING_ONE = "{" +
        "Main(a : A, _b : B, C) = {" +
        "   a -> b : L1(). " +
        "   spawn g1(a,b,_C). 0" +
        " with " +
        "   b@a. a -> C : L6(). " +
        "   spawn g2(a,_C).0" +
        "};" +
        "g1(a : A, b : B, _c : C) = {" +
        "   b -> c : L2(). " +
        "   c -> a : L3().0" +
        " with " +
        "   c@a. " +
        "   a -> b : L7(). " +
        "   b -> a : L8().0" +
        "};" +
        "g2(a : A, _c : C) = {" +
        "   a -> c : L4()." +
        "   c -> a : L5().0" +
        " with " +
        "   c@a. 0" +
        "};}"


      val RING_FAULT_TOLERANT = "{" +
        "Main(a : A, _b : B, B,C) = {" +
        "   a -> b : L1(m : String). " +
        "   spawn g1(a,b, _C, C). 0" +
        " with " +
        "   b@a. " +
        "   a -> B : LFMain1(). " +
        "   a -> C : LFMain2(). " +
        "   spawn Main(a,_B,B,C).0" +
        "};" +
        "g1(a : A, b : B, _c : C,C) = {" +
        "   b -> c : L2(m : String). " +
        "   c -> a : L3(m : String).0" +
        " with " +
        "   c@a. " +
        "   a -> b : LFg2()." +
        "   a -> C : LFg3()." +
        "   spawn g1(a,b,_C,C).0" +
        "};}"

  // Session cm global type from the paper
      val SESSION_CM_SIMPLE_ALTERNATIVE_DESIGN : String = "{ " +
        "GStartEx(m : M, w : W, _wEx : W) = {" +
        "   m -> wEx : StartEx(appId : Int exId : Int task : example.sparkCluster.Task)." +
        "   wEx -> m : ExStarted(appId : Int exId : Int)." +
        "   m -> w : ExRunning(appId : Int exId : Int)." +
        "   wEx -> m : ExDone(appId : Int exId : Int)." +
        "   m -> w : ExFinishStatus(appId : Int exId : Int).0" +
        " with " +
        "   wEx@m. m -> w : ExFailed(appId : Int exId : Int).0" +
        "};" +
        "GSel (m : M, _w : W, W) = {" +
        "   m -> w : LaunchDriver(appID : Int driver : example.sparkCluster.AbstractDriver)." +
        "   w -> m : AckNStatus(appID : Int)." +
        "   mu t. " +
        "     m -> W : {" +
        "       StartExCase(appID : Int) : spawn GStartEx(m, w, _W).t," +
        "     End() : 0}" +
        " with " +
        "   w@m. m -> W : FailGSelMtoW(appId : Int). 0" +
        "};" +
        //GMain
        "Main (zk : ZK, _m : M, M, W) = {" +
        "   mu t. " +
        "     m -> zk : {" +
        "       NewDriver(appID: Int numEx : Int) : m -> W : PrepSpawn(). spawn GSel(m, _W, W). t, " +
        "       DriverDone(appID : Int) : m -> W : BMsg(). t, " +
        "       End() : m -> W : L3(). 0}" +
        " with " +
        "   m@zk. zk -> M : FailMtoM(). zk -> W : FailMtoW(). 0" +
        "};}"


      // This is an alternative (the first) design of the full session CM, it is not implemented
      val SESSION_CM_ALTERNATIVE: String = "{ " +
        "GStartEx(m : M, w : W, _wEx : W) = {" +
        "   m -> wEx : StartEx(appId : Int exId : Int launchEx : org.apache.spark.deploy.DeployMessages.LaunchExecutor)." +
        "   wEx -> m : ExStarted(appId : Int exId : Int)." +
        "   m -> w : ExRunning(appId : Int exId : Int)." +
        "   wEx -> m : ExDone(appId : Int exId : Int)." +
        "   m -> w : ExFinishStatus(appId : Int exId : Int).0" +
        " with " +
        "   wEx@m. m -> w : ExFailed(appId : Int exId : Int).0" +
        "};" +
        "GHdlExSpawn(m : M, w : W, _tw : W, W) = {" +
        "   mu t. " +
        "     m -> W : {" +
        "       StartExCase(appID : Int) : spawn GStartEx(m, w, _W).t," +
        "     End() : 0}" +
        " with " +
        "   tw@m. m -> W : FailGHdlExSpawn(appId : Int). 0" +
        "};" +
        "GSel(m : M, _w : W, W) = {" +
        "   m -> w : LaunchDriver(appID : Int driver : org.apache.spark.deploy.DeployMessages.LaunchDriver)." +
        "   w -> m : AckNStatus(appID : Int)." +
        "   spawn GHdlExSpawn(m,w,_W,W)." +
        "   w -> m : DriverStateChange(status : org.apache.spark.deploy.master.DriverState.DriverState).0" +
        " with " +
        "   w@m. m -> W : FailGSelMtoW(appId : Int). 0" +
        "};" +
        //GMain
        "Main (zk : ZK, _m : M, M, W) = {" +
        "   mu t. " +
        "     m -> zk : {" +
        "       NewDriver(appID: Int numEx : Int) : m -> W : PrepSpawn(). spawn GSel(m, _W, W). t, " +
        "       DriverDone(appID : Int) : m -> W : BMsg(). t, " +
        "       End() : m -> W : L3(). 0}" +
        " with " +
        "   m@zk. zk -> M : FailMtoM(). zk -> W : FailMtoW(). 0" +
        "};}"

      //this is the version the current session cm uses (i.e. the one used by the second cm version)
      val SESSION_CM_FULL: String = "{ " +
        "PExecutor(m : M, w : W, _wEx : W, W) = {" +
        "   m -> wEx : StartEx(appId : Int exId : Int launchEx : org.apache.spark.deploy.DeployMessages.LaunchExecutor)." +
        "   wEx -> m : ExStarted(appId : Int exId : Int)." +
        "   m -> w : ExRunning(appId : Int exId : Int)." +
        "   wEx -> m : ExDone(appId : Int exId : Int)." +
        "   m -> w : ExFinishStatus(appId : Int exId : Int).0" +
        " with " +
        "   wEx@m. " +
        "     m -> W : {" +
        "       ExFailSpawn(appId : Int exId : Int) : " +
        "         spawn PExecutor(m,w,_W,W).0," +
        "       ExFailEnd(appId : Int exId : Int) : 0" +
        "    }" +
        "};" +
        // we need a sub session and tw is a dummy pick
        "PExSchedule(m : M, w : W, _tw : W, W) = {" +
        "   mu t. " +
        "     m -> W : {" +
        "       StartExCase(appID : Int) : spawn PExecutor(m, w, _W, W).t," +
        "       End() : 0}" +
        " with " +
        "   tw@m. " +
        "     m -> W : {" +
        "       FailExScheduleSpawn(appId : Int): " +
        "         spawn PExSchedule(m,w,_W,W).0," +
        "       FailExScheduleEnd(appId : Int) : 0}" +
        "};" +
        "PDriver(m : M, _w : W, W) = {" +
        "   m -> w : LaunchDriver(appID : Int driver : org.apache.spark.deploy.DeployMessages.LaunchDriver)." +
        "   w -> m : AckNStatus(appID : Int)." +
        "   spawn PExSchedule(m,w,_W,W)." +
        "   w -> m : DriverStateChange(status : org.apache.spark.deploy.master.DriverState.DriverState).0" +
        " with " +
        "   w@m. " +
        "     m -> W : {" +
        "       FailDriverSpawn(appId : Int newAppID : Int) : " +
        "         spawn PDriver(m,_W,W).0," +
        "       FailDriverEnd(appId : Int ) : 0}" +
        "};" +
        //GMain
        "Main (zk : ZK, _m : M, M, W) = {" +
        "   mu t. " +
        "     m -> zk : {" +
        "       NewDriver(appID: Int ) : m -> W : PrepSpawn(). spawn PDriver(m, _W, W). t, " +
        "       DriverDone(appID : Int) : m -> W : BMsg(). t, " +
        "       EndCM() : m -> W : Terminate(). 0}" +
        " with " +
        "   m@zk. zk -> M : FailMtoM(). zk -> W : FailMtoW(). 0" +
        "};}"

//      val SPARK_SESSION_CM_CLIENT_DEPLOY: String = "{ " +
//        "GExecutor(m : M, _wEx : W, W) = {" +
//        "   m -> wEx : StartEx(appId : Int exId : Int launchEx : org.apache.spark.deploy.DeployMessages.LaunchExecutor)." +
//        "   wEx -> m : ExStarted(appId : Int exId : Int)." +
//        "   wEx -> m : ExDone(appId : Int exId : Int).0" +
//        " with " +
//        "   wEx@m. m -> W : {" +
//        "     ExFailedRestart(appId : String exId : Int) : spawn GExecutor(m,_W,W). 0," +
//        "     ExFailStop() : 0}" +
//        "};" +
//        "GSparkApp(m : M, _tw : W, W) = {" +
//        "   mu t. " +
//        "     m -> W : {" +
//        "       StartExCase(appID : Int) : spawn GExecutor(m, _W, W).t," +
//        "       EndSparkApp() : 0}" +
//        " with " +
//        "   tw@m. m -> W : FailGHdlExSpawn(appId : Int). 0" +
//        "};" +
//        //GMain
//        "Main (zk : ZK, _m : M, M, W) = {" +
//        "   mu t. " +
//        "     m -> zk : {" +
//        "       NewDriver(appID: Int) : m -> W : PrepSpawn(). spawn GSparkApp(m, _W, W). t, " +
//        "       EndMain() : m -> W : EndMain2(). 0}" +
//        " with " +
//        "   m@zk. zk -> M : FailMtoM(). zk -> W : FailMtoW(). 0" +
//        "};}"

      //This is the global type from the paper, implemented in the example module
      val SESSION_CM_PAPER: String = "{ " +
        // this sub protocol handles the life cycle of an executor
        "StartEx(m : M, wD : W, _wEx : W, W) = {" +
        "   m -> wEx : ExDesc(launchEx : example.sparkcmminus.LaunchExecutor)." +
        "   wEx -> m : ExDone(appId : String exId : Int)." +
        "   m -> wD : ExFinished(appId : String exId : Int).0" +
        " with " +
        "   wEx@m." +
        "     m -> W : ExFailed(appId : String exId : Int). spawn StartEx(m,wD,_W,W).0" +
        "};" +
        "StartDriver(m : M, _wD : W, W) = {" +
        "   m -> wD : DriverDesc(driver : example.sparkcmminus.LaunchDriver)." +
        "   wD -> m : Ack(appID : String)." +
        "   mu t. " +
        "     m -> W : {" +
        "       PrepSpawn() : spawn StartEx(m, wD, _W, W).t," +
        "       End() : 0}" +
        " with " +
        "   wD@m. " +
        "     m -> W : DriverFailed(appId : String). " +
        "     spawn StartDriver(m,_W,W)." +
        "     0" +
        "};}"
    }


    object GlobalProgress {
      val THREE_BUYER: String = "{" +
        "threeBuyers (a : A, b : B, s : S, _ss : S,C) = {" +
        "   a -> s : Book(title : String). " +
        "   s -> a : PriceA(p : Int)." +
        "   s -> b : PriceB(p : Int)." +
        "   a -> b : MyShare(p : Int)." +
        "   spawn negotiationBC(b,_C)." +
        "   b -> s : {" +
        "     OkS() : " +
        "       b -> a : OkA(). " +
        "       b -> s : Addr(s : String)." +
        "       s -> b : ShipD(d : String).0," +
        "     QuitS(): " +
        "       b -> a : QuitA().0" +
        "   }" +
        " with " +
        "   ss@a. a -> b : F3Bb(). a -> s : F3Bs(). a -> C : F3BC(). 0 " +
        "};" +
        "negotiationBC(b : B, _c : C) = {" +
        "   b -> c : {" +
        "     YourShare(i : Int) : " +
        "       c -> b : {" +
        "         Ok() : 0," +
        "         No() : 0" +
        "       }," +
        "     End() : 0" +
        "   }" +
        " with " +
        "   c@b. 0" +
        "};" +
        "SelS (a : A, b : B, _s : S, S,C) = {" +
        "   spawn threeBuyers(a,b,s, _S,C)." + //we pick one more s as we have to do it
        "   0" +
        " with " +
        "   s@a. a -> b : FSelb(). a -> S : FSelS(). a -> C : FSelC(). 0};" +
        "Main (a : A, _b : B, C, S) = {" +
        " spawn SelS(a,b,_S,S,C).0" +
        " with " +
        "b@a. a -> C : FMainF1(). a -> S : FMainF2(). 0};" +
        "}"
    }


    object JACM_16 {
      val TWO_BUYER: String = "{" +
        "TwoBuyer(s : Seller,b1 : Buyer, _b2 : Buyer) = {" +
        "   b1 -> s : Request(s : String)." +
        "   s -> b1 : Price1(i : Int)." +
        "   s -> b2 : Price2(i : Int)." +
        "   b1 -> b2 : IPay(i : Int)." +
        "   b2 -> s : {" +
        "     Ok() : b2 -> s : Addr(s : String). s -> b2 : ArrivalDate(d : String).0," +
        "     Quit(): 0}" +
        " with " +
        "   b2@s. s -> b1 : CancelTrans().0" +
        "};" +
        "Main(s : Seller, _b1 : Buyer, Buyer) = {" +
        "   spawn TwoBuyer(s,b1,_Buyer).0" +
        " with " +
        "   b1@s. s -> Buyer : CancelMain().0" +
        "};}"

      val INSTRUMENT_CONTROLLING: String = ""
      val STREAMING: String = "{" +
        "Streaming(d : Data, k : Key, kernel : Kernel, _c : Consumer) = {" +
        "   mu t." +
        "   d -> kernel : Data(n : Int)." +
        "   k -> kernel : Key(n : Int)." +
        "   kernel -> c : Result(n : Int)." +
        "   t" +
        " with " +
        "   c@kernel. kernel -> k : FStreamingk(). kernel -> d : FStreamingd(). 0" +
        "};" +
        "SelKernel(d : Data, k : Key, _kernel : Kernel, Consumer) = {" +
        "   spawn Streaming(d,k,kernel, _Consumer)." + //we pick one more s as we have to do it
        "   0" +
        " with " +
        "   kernel@d. d -> k : FSelKernelk(). d -> Consumer : FSelKernelCons(). 0" +
        "};" +
        "Main(d : Data, _k : Key, Kernel, Consumer) = {" +
        "  spawn SelKernel(d,k, _Kernel, Consumer).0" +
        " with " +
        "   k@d. d -> Kernel : FMainKern(). d -> Consumer : FMainCons(). 0" +
        "};}"
    }

    object LightweightSessionProgramming {
      val BARBER = ""
    }

    object ParameterisedSession {

      val RING = "{" +
        "Main (a : A, _w0: W, W) = {" +
        "  spawn Pick(a, w0, _W, W).0" +
        " with " +
        "   w0@a. a -> W : FMain().0" +
        "};" +
        "Pick (a : A, w0 : W, _w1 : W, W) = {" +
        "  w0 -> w1 : M1()." +
        "  spawn Chain(a, w0,w1, _W, W).0" +
        " with " +
        "   w1@a. a -> W : FPick().0" +
        "};" +
        "Chain(a : A, w0 : W, wI: W, _wII : W, W) = {" +
        "   wI -> wII : MI()." +
        "   a -> W : {" +
        "     Spawn() : " +
        "       spawn Chain(a,w0,wII,_W,W). 0," +
        "     End(): " +
        "       wII -> w0 : MLast(). 0" +
        "   }" +
        " with " +
        "   wII@a. a -> W : FChain().  0" +
        "};}"

      val RING_FIX = "{" +
        "Main(a : A, _b : B, C) = {" +
        "   spawn Ring(a, b, _C, C).0" +
        " with " +
        "   b@a. " +
        "   a -> b : F(). " +
        "   a-> C: F().0" +
        "}; " +
        "Ring(a : A, b : B, _c : C, C) = {" +
        "   a -> b: ROne(). " +
        "   b -> c : RTwo(). " +
        "   c -> a : RComplete().0" +
        " with " +
        "   c@a. " +
        "   a -> b : RingF()." +
        "   a -> C : RingF(). 0" +
        "};}"


      val PIPE = "{" +
        "Main(a : A, _nP : P, P) = {" +
        "   spawn Pipe(a,nP, _P,P). 0" +
        " with " +
        "   nP@a. a -> P : FMain().0" +
        "};" +
        "Pipe(a : A, nP : P, _p : P, P) ={" +
        "  nP -> p : PM(id : Int)." +
        "  a -> P : {" +
        "       PrepSpawn() : spawn Pipe(a,p,_P,P).0," +
        "       End() : 0" +
        "     }" +
        " with " +
        "   p@a. a -> P : FPipe().0" +
        "};}"

      // pipe -- https://github.com/nickng/scribble-go-examples/blob/master/4_pipeline/Pipeline.scr
      //adder -- https://github.com/scribble/scribble-java/blob/master/scribble-demos/scrib/betty16/src/betty16/lec2
      // /adder/Adder.scr
      // cc18
    }


    object LetItRecoverCC17 {
      val webCrawler: String = "{" +
        "WebCrawler(m : M, _i : I, D, P) = {" +
        "  mu t." +
        "  m -> i : {" +
        "      IAddD() :" +
        "         m -> D : DAddD(). m -> P : PAddD()." +
        "         spawn Download(m,i, _D, D, P).t," +
        "      IDone() :" +
        "         m -> D : DDOne(). m -> P : PDone()." +
        "         i -> m : Url(u : String). 0 } " +
        " with" +
        "  i@m. m -> D : F1(). m -> P : F2().0" +
        "};" +
        "Download(m : M, i : I, _d : D, D, P) = {" +
        "   spawn Parse(m,i,d, _P, P).0" +
        " with " +
        "   d@m. m -> i : DF(). m -> D : DF1(). m -> P : DF2()." +
        "        spawn Download(m,i,_D,D,P).0" +
        "};" +
        "Parse(m : M, i : I, d : D, _p : P, P) = {" +
        "   d -> p : Parse(m : String)." +
        "   p -> i : Index(i : String).0" +
        " with" +
        "   p@m. m -> i : PF(). " +
        "        m -> d : PF1(). " +
        "        m -> P : PF2()." +
        "        spawn Parse(m,i,d,_P,P).0" +
        "};}"
    }


    object PracticalInterruptible {
      val resAccessControl: String = "{" +
        "ResAccCon( c : C, _u : U, A, InOne, InTwo) = {" +
        "   u -> c : Req(dur : Int)." +
        "   spawn HdlRes(u,c,_A,InOne,InTwo). 0" +
        "with" +
        "   u@c. c -> A : F(). c -> InOne : F1(), c -> InTwo : F2().0" +
        "};" +
        "HdlRes(u : U, c : C, _a : A, InOne, InTwo) = {" +
        "   c -> a : Start()." +
        "   spawn InterruptOne(c,u,a, _InOne, InTwo).0" +
        "with" +
        "   a@c. c -> u : F(). c -> InOne : F1(), c -> InTwo : F2().0" +
        "};" +
        "InterruptOne(c : C, u : U,a : A, _iOne : InOne, InTwo) = {" +
        "   spawn InterruptTwo(c,u,a, _InTwo, InTwo). 0" +
        "with" +
        "   iOne@c. c -> u : F(). c -> a : F1(), c -> InTwo : F2().0" +
        "};" +
        "InterruptTwo(c : C, u : U ,  a : A, _iTwo : InTwo, InTwo) = {" +
        "   mu t." +
        "      a -> u : Data().t " +
        "with" +
        "   iTwo@c. c -> u : InterPaus(). " +
        "             c -> InTwo : F2()." +
        "             c -> a : F3()."+
        "             u -> a : Resume()." +
        "             spawn InterruptTwo( c,u, a , _InTwo, InTwo). 0" +
        "};}"
    }

    object CC {
      //Calculator R
      //Producer P
      //Consumer C
      val DSH: String = "{" +
        "SH(p : P, r : R, _c : C) = {" +
        "   mu t." +
        "     p -> r : {" +
        "       IsAbove(x : Int y : Int):" +
        "         r -> p : Res(b : Boolean)." +
        "         p -> r : IsAboveSec(x:Int y:Int)." +
        "         r -> p : ResSec(b : Boolean)." +
        "         p -> r : {" +
        "          BothInR() :" +
        "            p -> c : BothInC(x2 : Int y2 : Int).t," +
        "          BothOut() : " +
        "             p -> c : BothOutTwo().t," +
        "          Intrsct(x1 : Int y1 : Int x2 : Int y2 : Int):" +
        "            r -> p : ResIntrsct(x1 : Int y1 : Int)." +
        "            p -> c : {" +
        "              SecOut(x : Int y : Int) : t," +
        "              SecIn(x1 : Int y1 : Int x2 : Int y2 : Int) : t" +
        "            }" +
        "         }," +
        "       ClosePR() :" +
        "           p -> c : ClosePC().0" +
        "       }" +
        " with " +
        "   c@p. " +
        "   p -> r : FM().0" +
        "};" +
        "Main(p : P, _r : R, C) = {" +
        "   spawn SH(p, r, _C).0" +
        " with " +
        "   r@p. p -> C : F().0" +
        "};" +
        "}"

    }

    object GlobalEscape {
      val InterruptableThreeBuyer = "{" +
        "NegotiationCB(c : Client, b: Bank, _i : Interrupt) = {" +
        "   b -> c : { " +
        "      OK() : 0," +
        "      NEM() : 0" +
        "}" +
        " with " +
        "   i@c. c -> b : AboardNeg().0" +
        "};" +
        "ThreeBuyers(s : Seller, c : Client, b : Bank, _i : Interrupt, Interrupt) = {" +
        "   c -> s : O(). " +
        "   s -> c : A()." +
        "   c -> b : Co()." +
        "   spawn NegotiationCB(c, b, _Interrupt)." +
        "   c -> s : M()." +
        "   s -> c : D().0" +
        " with " +
        "   i@s. s -> c : BuyerInterToC()." +
        "   s -> b : BuyerInterToB(). " +
        "   s -> Interrupt : BuyerInterToInter()." +
        "   spawn Aboard(s,c,b, _Interrupt).0 " +
        "};" +
        //
        "Aboard (s : Seller, c : Client, b : Bank, _i : Interrupt) = {" +
        "   s -> c : D(). 0" +
        " with " +
        //the interrupt is monitored by s
        // but every end point can trigger the failure detection
        //  which in turn triggers teh monitor statment
        "   i@s. s -> c : AboardToC(). s -> b : AboardToB() .0 " +
        "};" +
        //
        "SelS (s : Seller, c : Client, _b : Bank, Interrupt) = {" +
        "   spawn ThreeBuyers(s,c,b, _Interrupt, Interrupt)." + //we pick one more s as we have to do it
        "   0" +
        " with " +
        "   b@s. s -> c : BFailedToC(). s -> Interrupt : BFailedToI().0};" +
        //
        "Main (s : Seller, _c : Client, Bank, Interrupt) = {" +
        " spawn SelS(s, c, _Bank, Interrupt).0" +
        " with " +
        "c@s. s -> Bank : MFailureToB(). s -> Interrupt : MFailureToI(). 0};" +
        "}"
    }

    object FORTE_FHandling {

      // T[ a -> b : M v {f1,f2} a -> c]H[f1 : c -> e]
      // T[ c -> p. p -> l v f1,f2. p-> ES]
      // H[ f1: l -> s; f2 : l -> c ]

      val EXAMPLE: String = "{" +
        "Main (l : L, _c : C, S,P,ES,F) = {" +
        "  spawn TryHdl(l,c,_S,P,ES,F).0" +
        " with " +
        "   c@l. 0" +
        "};" +
        "TryHdl(l : L, c : C, _s : S, P,ES,F) = {" +
        "   spawn SusB(l,c,s, _F,P,ES,F).0 " +
        " with " +
        "   s@l. 0" +
        "};" +
        "SusB (l : L, c : C, s : S, _f1 : F, P,ES,F) = {" +
        "  spawn QWarn(l,c,_F,P,ES,F).0" +
        " with " +
        "   f1@l. l -> s : SuspiciousB().0" +
        "};" +
        "QWarn (l : L, c : C, _f2 : F, P,ES) = {" +
        "  spawn SusB(l,c,_F,P,ES,F).0" +
        " with " +
        "   f2@l. l -> c : QWarn().0" +
        "};" +
        "DBlock (l : L, c : C, _p : P, ES) = {" +
        "  spawn DBlockNext(l,c,p,_ES,ES).0" +
        " with " +
        "   p@l. l -> ES : DM().0" +
        "};" +
        "DBlockNext (l : L, c : C, p : P, _es : ES, ES) = {" +
        "  c -> p : MOut(). p -> l : MLog(). p -> es : MOutEx().0" +
        " with " +
        "   es@l. l -> ES : DM().0" +
        "};}"
    }

    object ExceptionalAsynST_POPL {
      val twoFactor = "{" +
        "TwoFactor(s : S, _c : C) = {" +
        "  c -> s : AccData(us : String upw: String)." +
        "  s -> c : {" +
        "    Authenticated() : 0," +
        "    Challenge() : " +
        "      s -> c : ChallengeKey(k : String). " +
        "      c -> s : Response(s : String)." +
        "      s -> c : {" +
        "         ChallengeAuthenticated() : 0," +
        "         ChallengeAccessDenied() : 0" +
        "      }," +
        "    AccessDenied() : 0 }" +
        "with" +
        "  c@s. 0" +
        "};}"
    }

    object ESOP_Crash_Handling {
      val GENERALIZED_STREAMING: String = "{" +
        "Main (zk : ZK, _dfs : DFS, W) = {" +
        "   mu t." +
        "     zk -> dfs : {" +
        "       Spawn() : zk -> W : PrepSpawn(). spawn Partition(dfs, _W, W).t," +
        "       End() : zk-> W : EndP(). 0" +
        "}" +
        " with" +
        "   dfs@zk. zk -> W : DM().0" +
        "};" +
        "Partition(dfs : DFS, _w : W, W) = {" +
        "   mu t." +
        "   dfs -> w : WorkLoad()." +
        "   w -> dfs : Result().t" +
        " with  " +
        "   w@dfs. dfs -> W : DM(). spawn Partition(dfs, _W, W).0" +
        "};}"
//
//      val EXAMPlE: String = "{" +
//        "Main(dfs : DFS, _w1 : W, W) = {" +
//        "  spawn  " +
//        "WOne(dfs : DFS, _w1 : W, W) = {" +
//        "   spawn WTwo(dfs,w1, _W, W). 0" +
//        " with  " +
//        "   w1@dfs. spawn WTwoHandling(dfs,_W,W).0 " + //dfs -> w2 w2 -> dfs"
//        "};" +
//        "WTwo(dfs : DFS, w1, _w2 : W, W) = {" +
//        "   dfs -> w1 : M()." +
//        "   dfs -> w2 : M()." +
//        "   w1 -> dfs : R()." +
//        "   w2 -> dfs : R()." +
//        "   0" +
//        " with" +
//        "   w2@dfs. dfs -> w1 : MP(). w1 -> dfs : RP()" +
//        "}"
    }


    def main(args: Array[String]): Unit = {

      def project(gType: String, name: String = "PLACEHOLDER"): AllEndPointTypes = {
        val g = parseString(gType)
        val d = Projection(g, name)
        d
      }

      println(CC.DSH.slice(685, 695))
      val prj = project(CC.DSH)
      println("")
    }
  }
