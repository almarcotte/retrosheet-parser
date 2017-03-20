package retrosheet

import scala.util.parsing.combinator.Parsers
import scala.util.parsing.input.{NoPosition, Position, Reader}

object RetroParser extends Parsers {
  override type Elem = RetroToken
}

class RetroTokenReader(tokens: Seq[RetroToken]) extends Reader[RetroToken] {
  override def first: RetroToken = tokens.head
  override def atEnd: Boolean = tokens.isEmpty
  override def pos: Position = NoPosition
  override def rest: Reader[RetroToken] = new RetroTokenReader(tokens.tail)
}