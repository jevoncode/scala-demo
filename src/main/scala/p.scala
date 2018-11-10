package p {
  class Super {
    protected[p] def f() { println("f") }

    class inner {
      (new Super).f()
    }
  }
  class Sub extends Super {
    f()
  }

  class Other {
    (new Super).f() // error: f is not accessible
  }
}
