package event_lang

import java.io.PrintWriter

import event_lang.codegen.{ChannelGenerator, GlobalTypes}
import event_lang.intern.parser.ProtocolParser
import event_lang.types.Projection

import scala.io.{BufferedSource, Source}
import scala.io.Source.fromFile


object LocalTypeGenerator {
  def codeGen(gtype: String, pName: String, file: String, pack: String): Unit = {

    val g = ProtocolParser.parseString(gtype)
    val d = Projection(g, pName)
    val cb = ChannelGenerator.apply(d)

    //println(cb.channelCode())
    val pw = new PrintWriter(file)
    pw.write(pack + "\n")
    pw.write(cb.channelCode())
    pw.close()
  }

  def main(args: Array[String]): Unit = {

    val usage =
      """Please provide the following arguments: protocol_name protocol_package output_file (-f file_with_gType | -p name)
        """

    val d = args.toList
    d match {
      case pName :: pPackage :: outFile :: flag :: protol :: Nil =>

        if (flag == "-f") {
          var p: BufferedSource = null
          try {
            p = fromFile(protol)
            codeGen(p.mkString, pName,
              outFile,
              if (pPackage.startsWith("package ")) pPackage else "package " + pPackage)
          }
          catch {
            case e: Exception =>
              e.printStackTrace()
          }
          finally {
            p.close()
          }
        } else if (flag == "-p") {
          protol match {
            case s if s == "SESSION_CM_FULL" =>
              codeGen(GlobalTypes.OurPaper.SESSION_CM_FULL, pName,
                outFile, if (pPackage.startsWith("package ")) pPackage else "package " + pPackage)
          }
        } else {
          println(usage)
        }

      case _ =>
        println(usage)
    }


  }
}
