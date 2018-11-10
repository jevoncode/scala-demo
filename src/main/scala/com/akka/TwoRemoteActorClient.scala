package com.akka

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

/**
  * 注意, akka并没有服务端和客户端的概念, 都是平等的, 现在这命名只是方便突出谁发送消息给谁
  */
object TwoRemoteActorClient {


  def main(args: Array[String]): Unit = {
    //参数和服务端配置一样
    val conf = new java.util.HashMap[String, Object]()
    val list = new java.util.ArrayList[String]()
    list.add("akka.remote.netty.tcp")
    conf.put("akka.remote.enabled-transports", list) //参数是个集合
    conf.put("akka.actor.provider", "akka.remote.RemoteActorRefProvider")
    conf.put("akka.remote.netty.tcp.hostname", "127.0.0.1")
    conf.put("akka.remote.netty.tcp.port", "4343")  //这里不是服务端的端口, 而是自己的端口, 所以更加表明了, akka没有服务端和客户端之分的概念

    val sys = ActorSystem("myAkkaClientSys", ConfigFactory.parseMap(conf))
    sys.actorSelection("akka.tcp://TwoRemoteActorServer@127.0.0.1:3434/user/actor1") ! new People("jc")
  }
}


