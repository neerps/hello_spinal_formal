package unary_counter

import spinal.core._
import spinal.core.formal._
import spinal.lib.CountOne
import spinal.lib.Counter

object UnaryCounterFormal extends App {
  FormalConfig.withProve(10).doVerify(new Component {
      val dut = FormalDut(UnaryCounter())

      assumeInitial(clockDomain.isResetActive)

      anyseq(dut.io.count_up)

      var check_out = UInt(8 bits)
      check_out \= dut.io.count_out
      for (idx <- 0 to 7) {
        when (check_out(0)) {
          check_out \= check_out |>> 1
        }
      }
      when (pastValid()) {
        assert(check_out === 0)
      }

      when (pastValidAfterReset() && past(dut.io.count_up) && !(past(dut.counter) === 0xFF)) {
        assert((CountOne(dut.counter) - CountOne(past(dut.counter))) === 1)
      }

      when (pastValidAfterReset() && !(past(dut.io.count_up)) && !(past(dut.counter) === 0)) {
        assert((CountOne(past(dut.counter)) - CountOne(dut.counter)) === 1)
      }
    })
}
