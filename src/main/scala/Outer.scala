import scala.collection.mutable.ArrayBuffer

class Outer {

  private var value =0
  class Inner(val name:String){
    def callOuter()={
      value=10
    }

  }


  val inners = new ArrayBuffer[Inner]

  def getInner(name:String) ={
    new Inner(name)
  }
}


