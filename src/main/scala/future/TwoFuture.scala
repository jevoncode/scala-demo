package future

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object TwoFuture {


  def main(args: Array[String]): Unit = {

    println("new a thread "+Thread.currentThread().getName)  //new a thread main
    val fu1 = Future {
      println("fu1 new a thread "+Thread.currentThread().getName)  //fu1 new a thread scala-execution-context-global-10
      println("fu1 开始计算...")
      Thread.sleep(200)
      100
    }

    val fu2 = Future {
      println("fu2 new a thread "+Thread.currentThread().getName)  //fu2 new a thread scala-execution-context-global-11
      println("fu2 开始计算...")
      Thread.sleep(300)
      200
    }

    //yield 把结果放到一个新数组中
    val c = for (a <- fu1; b <- fu2) yield a + b
    println(Await.result(c, Duration.Inf))
  }
}
