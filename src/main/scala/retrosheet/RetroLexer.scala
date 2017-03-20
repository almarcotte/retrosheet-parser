package retrosheet

import scala.util.parsing.combinator.RegexParsers

object RetroLexer extends RegexParsers {
  override def skipWhitespace = true
  override val whiteSpace = "[ \t\r\f]+".r

  def hit: Parser[HIT] = {
    "[S|D|T][1-9]*".r ^^ { str => HIT(str) }
  }

  def fc: Parser[FC] = {
    "FC[1-9]".r ^^ { str => FC(str) }
  }

}
