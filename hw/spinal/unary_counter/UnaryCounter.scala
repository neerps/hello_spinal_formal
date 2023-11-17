package unary_counter

import spinal.core._

case class UnaryCounter(width: Int = 8) extends Component {
  val io = new Bundle {
    val count_up  = in  Bool()
    val count_out = out UInt(width bits)
  }

  val counter = Reg(UInt(width bits)) init 0

  when(io.count_up) {
    counter := counter(0 to width-2) @@ U(1, 1 bits)
  } otherwise {
    counter := U(0, 1 bits) @@ counter(1 to width-1)
  }

  io.count_out := counter
}

object UnaryCounterVerilog extends App {
  Config.spinal.generateVerilog(UnaryCounter())
}

object UnaryCounterVhdl extends App {
  Config.spinal.generateVhdl(UnaryCounter())
}
