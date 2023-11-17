# Hello Spinal Formal

A simple project to try Formal Property Verification with SpinalHDL.

Based on this [article](https://fpga-systems.ru/formal-verification-with-symbiyosys) which introduces Formal 
Verification with Verilog and SymbiYosys.

The project hierarchy is based on [SpinalHDL template project](https://github.com/SpinalHDL/SpinalTemplateSbt)

To try to generate HDL with `sbt` run:

```sh
# Verilog
sbt "runMain unary_counter.UnaryCounterVerilog"

# VHDL
sbt "runMain unary_counter.UnaryCounterVhdl"

```

To run Formal Property Verification:

```sh
sbt "runMain unary_counter.UnaryCounterFormal"

```

On 2023.11.17 I didn't implement a way to run different tasks like BMC and Prove for the same DUT. But it's
cerainly possible to do so.
