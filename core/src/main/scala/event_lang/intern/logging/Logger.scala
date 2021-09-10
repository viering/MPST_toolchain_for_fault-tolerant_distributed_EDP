package event_lang.intern.logging

//import org.slf4j.LoggerFactory

import org.apache.logging.log4j.LogManager

/**
 * enable a log level via
 * -Dorg.slf4j.simpleLogger.defaultLogLevel=debug
 * -Dlog4j.configurationFile=/home/me/git/sessions/compiler/src/resources/simplelogger.properties
 */
trait Logger {

  val INFO = 'info
  val DEBUG = 'debug


//  protected val log: org.slf4j.Logger = LoggerFactory.getLogger(getClass.getName)

  protected val log = LogManager.getLogger(this.getClass);


  def info(msg: String): Unit = logger(msg, INFO)

  def trace(msg: String): Unit = logger(msg, 'trace)

  def debug(msg: String): Unit = logger(msg, 'debug)

  def warn(msg: String): Unit = logger(msg, 'warn)

  def errorLog(msg: String): Unit = logger(msg, 'error)

  def error(msg: String): Unit = logger(msg, 'error)

  def logger(msg: String, lvl: Symbol = 'debug): Unit = {
    try {
      assert(msg != null && lvl != null)

      lvl match {
        case 'debug => log.debug(s"$msg")
        case 'info => log.info(s"$msg")
        case 'error => log.error(s"$msg")
        case 'warn => log.warn(s"$msg")
        case 'trace => log.trace(s"$msg")
        case _ => log.debug(s"Log level $lvl is unknown the msg is $msg")

      }
    } catch {
      case e: RuntimeException =>
        error(s"${e.toString}")
    }
  }

}

