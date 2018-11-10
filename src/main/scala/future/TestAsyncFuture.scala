package future

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object TestAsyncFuture {

  def main(args: Array[String]): Unit = {
    val fu = Future {
      println("Fu start ...")
      Thread.sleep(100)
      100
    }


    fu.onSuccess{
      case  x => println(x)
    }

    Thread.sleep(1000)
  }
}
