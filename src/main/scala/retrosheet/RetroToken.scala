package retrosheet

sealed trait RetroToken

// DGR/L9LS.2-H
case class IDENTIFIER(str: String) extends RetroToken
case class LITERAL(str: String) extends RetroToken
case class HIT(str: String) extends RetroToken
case class ERROR(str: String) extends RetroToken
case class FC(str: String) extends RetroToken
case object SEPARATOR extends RetroToken