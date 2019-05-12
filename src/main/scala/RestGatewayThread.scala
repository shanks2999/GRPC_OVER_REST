import grpcgateway.server.GrpcGatewayServerBuilder
import io.grpc.internal.DnsNameResolverProvider
import io.grpc.netty.NettyChannelBuilder
import lamda.CalculatorHandler

import scala.concurrent.ExecutionContext.Implicits.global

class RestGatewayThread extends Runnable {

  def run: Unit = {
    val channel =
//      io.grpc.ManagedChannelBuilder
      NettyChannelBuilder
//        .forAddress("7es3asy8nd.execute-api.us-east-1.amazonaws.com", 8980)
        .forAddress("localhost", 8980)
        .nameResolverFactory(new DnsNameResolverProvider())
        .usePlaintext(true)
        .build()

    val gateway = GrpcGatewayServerBuilder
      .forPort(8981)
      .addService(new CalculatorHandler(channel))
      .build()
    gateway.start()

    sys.addShutdownHook {
      gateway.shutdown()
    }
  }

}