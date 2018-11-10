package com.akka

import akka.actor.{Actor, ActorSystem, Props}

object TestActor {


  class Actor1 extends Actor {

    println("new a thread "+Thread.currentThread().getName)  //new a thread sys-akka.actor.default-dispatcher-3
    /**
      * 会阻塞至有消息到来
      * @return
      */
    def receive = {
      case msg: String => println(msg)
    }

  }

  def main(args: Array[String]): Unit = {

    println("new a thread "+Thread.currentThread().getName)  //new a thread main
    val sys = ActorSystem("sys");
    println("init a sys")
    val a = sys.actorOf(Props[Actor1])
    println("get actor a")

    a ! "双十一要来啦"
  }

  /**
    * output: //可以看出在!("告知")Actor a后才实例化Actor1
    * new a thread main
    * init a sys
    * get actor a
    * new a thread sys-akka.actor.default-dispatcher-2
    * 双十一要来啦
    */
}
