
import io.grpc.{ManagedChannelBuilder, Server, ServerBuilder}
//import hello.{GreeterGrpc, HelloReply, HelloRequest}
import lamda.{LamdaProto, CalculatorGrpc, Operator, Output}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContextExecutor, Future}


object Client_GRPC {
//  implicit lazy val global: ExecutionContextExecutor = hello.ExecutionContextImpl.fromExecutor(null: Executor)
  def main(args: Array[String]): Unit = {
    //  Creating a channel:
    val channel = ManagedChannelBuilder.forAddress("localhost", 8980).usePlaintext(true).build
    val request = Operator(varA = "15", varB = "5")


    //  Blocking call:
    val blockingStub = CalculatorGrpc.blockingStub(channel)
    /**
      *Client making GRPC call "Add" using protobuffs
     */
    val opAdd: Output = blockingStub.add(request)
    println(opAdd)

    /**
      *Client making GRPC call "Div" using protobuffs
      */
    val opDiv: Output = blockingStub.div(request)
    println(opDiv)

    /**
      *Client making GRPC call "Mul" using protobuffs
      */
    val opMul: Output = blockingStub.mul(request)
    println(opMul)

    /**
      *Client making GRPC call "Sub" using protobuffs
      */
    val opSub: Output = blockingStub.sub(request)
    println(opMul)


    //  Async call:
//      val stub = CalculatorGrpc.stub(channel)
//      val f: Future[Output] = stub.add(request)
//      Thread.sleep(1000)
//      f onComplete println
//    println(f)
  }
}
