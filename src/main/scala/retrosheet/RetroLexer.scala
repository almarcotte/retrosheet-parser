package retrosheet

import com.sun.tools.internal.ws.wsdl.framework.ParserListener

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

trait RetroLexerCompilationError
case class RetroLexerError(msg: String) extends RetroLexerCompilationError

object RetroLexer extends RegexParsers {
  override def skipWhitespace = true
  override val whiteSpace: Regex = "[ \t\r\f]+".r

  def hit: Parser[HIT] = {
    "[S|D|T][1-9]*".r ^^ { str => HIT(str) }
  }

  def fc: Parser[FC] = {
    "FC[1-9]".r ^^ { str => FC(str) }
  }

  def sep: Parser[SEPARATOR.type] = "/" ^^ (_ => SEPARATOR)

  def tokens: Parser[List[RetroToken]] = {
    phrase(rep1(hit | fc | sep))
  }

  def apply(event: String): Either[RetroLexerError, List[RetroToken]] = {
    parse(tokens, event) match {
      case NoSuccess(msg, _) => Left(RetroLexerError(msg))
      case Success(result, _) => Right(result)
    }
  }
}
