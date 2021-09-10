package event_lang.intern.parser

import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import java.util

import event_lang.types.GlobalTypes.{GTop, GType}
import org.antlr.v4.runtime.atn.{ATNConfigSet, ATNSimulator}
import org.antlr.v4.runtime.dfa.DFA
import org.antlr.v4.runtime.{ANTLRErrorListener, ANTLRInputStream, CharStream, CommonTokenStream, Parser, RecognitionException, Recognizer}

import scala.io.Source

/**
  * Created by me on 10/23/15.
  */
object ProtocolParser {

  //TODO add some error handling
  def parseString(protocol: String): GTop = {
    val stream: CharStream             = new ANTLRInputStream(new ByteArrayInputStream(protocol.getBytes(StandardCharsets.UTF_8)))
    val lexer: ProtocolGrammarLexer    = new ProtocolGrammarLexer(stream)
    val tokenStream: CommonTokenStream = new CommonTokenStream(lexer)
    val parser: ProtocolGrammarParser  = new ProtocolGrammarParser(tokenStream)
    //val listener: GrammarListener = new GrammarListener
    //parser.addParseListener(listener)
    //parser.gTop()

    val gtypeV = new GTypeVisitor()
    gtypeV.visitGTop(parser.gTop())
    //if (listener.error) throw new ParserError(listener.reason)
    //listener.re
  }

  def parse(pro: String): GType = {
    try {
      val source = Source.fromFile(pro)
      if (!source.isEmpty) {
        val strProtocol = source.mkString
        if (!strProtocol.isEmpty) return ProtocolParser.parseString(strProtocol)
      }
    } catch {
      case e: Exception =>
    }
    ProtocolParser.parseString(pro)
  }

}
