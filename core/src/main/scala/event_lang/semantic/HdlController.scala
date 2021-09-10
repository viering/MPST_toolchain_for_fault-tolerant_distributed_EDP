package event_lang.semantic

/*
  "{" +
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
*/
case class HdlController(MSGAtoB: Boolean = true, RcvMSGAtoB: Boolean = true, MSGOne: Boolean =
true, MSGTwo: Boolean = true,
                         RcvMSGTwo: Boolean = true,
                         fail_bb: Boolean = true, FailGOne: Boolean = true, RcvMSGMCast: Boolean
                         = true,
                         spawnGone: Boolean = true, fail_b: Boolean = true,
                         IssueFailureMCast: Boolean = false,
                         IssueFailure_b_MSGAtoB: Boolean = false,
                         IssueFailure_bb_RcvMSGOne: Boolean = false,
                         IssueFailure_bb_RcvMSGAtoB: Boolean = false,
                         IssueFailure_b_RcvFailGOne : Boolean =false,
                         FailMain: Boolean = true)
