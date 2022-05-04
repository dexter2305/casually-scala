# Effect


## Properties of effect
  - describe the type of computation to be performed
  - describe the type of VALUE returned by the computation
  - construction of effect should be separated from execution of effect


Monads/Properties | Type of computation  | Type of return value | Construction & Execution decoupled
------------------|----------------------|----------------------|-----------------------------------
Option(42)        |  YES (may be absent) | YES                  | Side effect are not required. Option is pure effect
Future            |  YES (asyc - nature) | YES                  | No, Execution is required. Future is impure effect
MyIO              | Generic but yes      | YES                  | Yes. Uses call by name



### Reference
- [Rock The JVM](https://rockthejvm.com/p/cats-effect "Rock The JVM")