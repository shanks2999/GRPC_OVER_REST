
import grpcgateway.server.GrpcGatewayServerBuilder
import io.grpc.netty.NettyServerBuilder
import io.grpc.{Server}
import lamda.{CalculatorGrpc, CalculatorHandler, Operator, Output}
import scala.concurrent.Future

class Server_GRPC(server: Server) {


  def start(): Unit = {
    server.start()
    sys.addShutdownHook {
      // Use stderr here since the logger may has been reset by its JVM shutdown hook.
      System.err.println("*** shutting down gRPC server since JVM is shutting down")
      stop()
      System.err.println("*** server shut down")
    }
    ()
  }

  def stop(): Unit = {
    server.shutdown()
  }

  /**
    * Await termination on the main thread since the grpc library uses daemon threads.
    */
  def blockUntilShutdown(): Unit = {
    server.awaitTermination()
  }
}

object Server_GRPC extends App {
    val server = new Server_GRPC(
      NettyServerBuilder
        .forPort(8980)
        .addService(
          CalculatorGrpc.bindService(
            new expose_grpc,
            scala.concurrent.ExecutionContext.global
          )
        )
        .build()
    )
    new Thread(new RestGatewayThread).start()
    server.start()
    server.blockUntilShutdown()

}

private class expose_grpc extends CalculatorGrpc.Calculator {
  override def add(req: Operator) = {
    val reply = Output(result = (req.varA.toInt + req.varB.toInt).toString)
    Future.successful(reply)
  }
  override def div(req: Operator) = {
    val reply = Output(result = (req.varA.toInt / req.varB.toInt).toString)
    Future.successful(reply)
  }
  override def mul(req: Operator) = {
    val reply = Output(result = (req.varA.toInt * req.varB.toInt).toString)
    Future.successful(reply)
  }
  override def sub(req: Operator) = {
    val reply = Output(result = (req.varA.toInt - req.varB.toInt).toString)
    Future.successful(reply)
  }
}