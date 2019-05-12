package lamda

import _root_.com.trueaccord.scalapb.GeneratedMessage
import _root_.com.trueaccord.scalapb.json.JsonFormat
import _root_.grpcgateway.handlers._
import _root_.io.grpc._
import _root_.io.netty.handler.codec.http.{HttpMethod, QueryStringDecoder}

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future}
import com.trueaccord.scalapb.json.JsonFormatException
import scala.util._

class CalculatorHandler(channel: ManagedChannel)(implicit ec: ExecutionContext)
  extends GrpcGatewayHandler(channel)(ec) {
  override val name: String = "Calculator"
  private val stub = CalculatorGrpc.stub(channel)
  
  override def supportsCall(method: HttpMethod, uri: String): Boolean = {
    val queryString = new QueryStringDecoder(uri)
    (method.name, queryString.path) match {
      case ("GET", "/add") => true
      case ("GET", "/sub") => true
      case ("GET", "/mul") => true
      case ("GET", "/div") => true
      case _ => false
    }
  }
  
  override def unaryCall(method: HttpMethod, uri: String, body: String): Future[GeneratedMessage] = {
    val queryString = new QueryStringDecoder(uri)
    (method.name, queryString.path) match {
      case ("GET", "/add") => 
        val input = Try {
          val varA = 
            queryString.parameters().get("varA").asScala.head
          val varB = 
            queryString.parameters().get("varB").asScala.head
          Operator(varA = varA, varB = varB)
        }
        Future.fromTry(input).flatMap(stub.add)
      case ("GET", "/sub") => 
        val input = Try {
          val varA = 
            queryString.parameters().get("varA").asScala.head
          val varB = 
            queryString.parameters().get("varB").asScala.head
          Operator(varA = varA, varB = varB)
        }
        Future.fromTry(input).flatMap(stub.sub)
      case ("GET", "/mul") => 
        val input = Try {
          val varA = 
            queryString.parameters().get("varA").asScala.head
          val varB = 
            queryString.parameters().get("varB").asScala.head
          Operator(varA = varA, varB = varB)
        }
        Future.fromTry(input).flatMap(stub.mul)
      case ("GET", "/div") => 
        val input = Try {
          val varA = 
            queryString.parameters().get("varA").asScala.head
          val varB = 
            queryString.parameters().get("varB").asScala.head
          Operator(varA = varA, varB = varB)
        }
        Future.fromTry(input).flatMap(stub.div)
      case (methodName, path) => 
        Future.failed(InvalidArgument(s"No route defined for $methodName($path)"))
    }
  }
}

