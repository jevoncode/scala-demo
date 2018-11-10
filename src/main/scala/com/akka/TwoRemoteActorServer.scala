package com.akka

import java.util

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory


/**
  * 注意, akka并没有服务端和客户端的概念, 都是平等的, 现在这命名只是方便突出谁发送消息给谁
  */
case class People(name: String) extends Serializable {
  val namex = name
}

object TwoRemoteActorServer {

  class Actor1 extends Actor {
    println("new a thread " + Thread.currentThread().getName)

    override def receive: Receive = {
      case msg: String => {
        println("Actor1 receive: " + msg)
      }
      case people: People => {
        println("听说" + people.namex + "这人物要来了")
      }
    }
  }


  def main(args: Array[String]): Unit = {
    val conf = new util.HashMap[String, Object]()
    val list = new util.ArrayList[String]()

    list.add("akka.remote.netty.tcp")
    conf.put("akka.remote.enabled-transports", list) //参数是个集合
    conf.put("akka.actor.provider", "akka.remote.RemoteActorRefProvider")
    conf.put("akka.remote.netty.tcp.hostname", "127.0.0.1")
    conf.put("akka.remote.netty.tcp.port", "3434")


    val sys = ActorSystem("TwoRemoteActorServer", ConfigFactory.parseMap(conf))
    sys.actorOf(Props[Actor1], "actor1")
  }
}

