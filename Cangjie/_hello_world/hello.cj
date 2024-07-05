class CA {}
 struct A {
     var ca = CA()
 }
 struct B {
     var x = 3
 }
 func foo() : Int64 {
     var x : A = A()
     match (x) {
         case b : B => b.x // 此处处理存在异常
         case _ => 0
     }
 }
 main() { foo() }
