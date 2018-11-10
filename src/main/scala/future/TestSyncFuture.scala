package future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object TestSyncFuture {

  def main(args: Array[String]): Unit = {
    val fu = Future {
      println("Fu start ...")
      Thread.sleep(1000)
      100
    }


    fu.onSuccess{
      case  x => println(x)
    }

//  Await.result(fu,Duration.Inf)
    var result = Await.result(fu,Duration.Inf)
    println(result) //需输出result才会阻塞
  }
}
