package com.akka

import akka.actor.{Actor, ActorSystem, Props}

object TwoLocalActors {

  class Actor1 extends Actor {
    println("new a thread "+Thread.currentThread().getName)
    override def receive: Receive = {
      case msg: String => {
        println("Actor1 receive: " + msg)
        val b = context.actorOf(Props[Actor2])
        b ! "听说双十一要来了"
      }
    }
  }


  class Actor2 extends Actor {
    println("new a thread "+Thread.currentThread().getName)
    override def receive: Receive = {
      case msg: String => {
        println("Actor2 receive: " + msg)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println("new a thread "+Thread.currentThread().getName)
    val sys = ActorSystem("sys")
    val a = sys.actorOf(Props[Actor1])
    a ! "双十一要来啦"
  }
}

/**output:
  * new a thread main
  * new a thread sys-akka.actor.default-dispatcher-3
  * Actor1 receive: 双十一要来啦
  * new a thread sys-akka.actor.default-dispatcher-4
  * Actor2 receive: 听说双十一要来了
  */
