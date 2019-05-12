package lamda

import _root_.akka.NotUsed
import _root_.akka.stream.Materializer
import _root_.akka.stream.scaladsl.{ Flow, Sink, Source }
import _root_.com.google.protobuf.Descriptors.ServiceDescriptor
import _root_.com.trueaccord.scalapb.grpc.{ AbstractService, ConcreteProtoFileDescriptorSupplier, Grpc, Marshaller, ServiceCompanion }
import _root_.grpc.akkastreams.GrpcAkkaStreams._
import _root_.io.grpc.{ CallOptions, Channel, MethodDescriptor, ServerServiceDefinition }
import _root_.io.grpc.stub.{ AbstractStub, ClientCalls, ServerCalls, StreamObserver }
import _root_.org.reactivestreams.{ Publisher, Subscriber }
import _root_.scala.concurrent.Await
import _root_.scala.concurrent.duration._
import _root_.scala.util.{ Failure, Success }

object LamdaGrpcAkkaStream {
  val METHOD_ADD: MethodDescriptor[lamda.Operator, lamda.Output] =
    MethodDescriptor.newBuilder()
      .setType(MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(MethodDescriptor.generateFullMethodName("Calculator", "Add"))
      .setRequestMarshaller(new Marshaller(lamda.Operator))
      .setResponseMarshaller(new Marshaller(lamda.Output))
      .build()
  val METHOD_SUB: MethodDescriptor[lamda.Operator, lamda.Output] =
    MethodDescriptor.newBuilder()
      .setType(MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(MethodDescriptor.generateFullMethodName("Calculator", "Sub"))
      .setRequestMarshaller(new Marshaller(lamda.Operator))
      .setResponseMarshaller(new Marshaller(lamda.Output))
      .build()
  val METHOD_MUL: MethodDescriptor[lamda.Operator, lamda.Output] =
    MethodDescriptor.newBuilder()
      .setType(MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(MethodDescriptor.generateFullMethodName("Calculator", "Mul"))
      .setRequestMarshaller(new Marshaller(lamda.Operator))
      .setResponseMarshaller(new Marshaller(lamda.Output))
      .build()
  val METHOD_DIV: MethodDescriptor[lamda.Operator, lamda.Output] =
    MethodDescriptor.newBuilder()
      .setType(MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(MethodDescriptor.generateFullMethodName("Calculator", "Div"))
      .setRequestMarshaller(new Marshaller(lamda.Operator))
      .setResponseMarshaller(new Marshaller(lamda.Output))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor = _root_.io.grpc.ServiceDescriptor.newBuilder("Calculator")
    .setSchemaDescriptor(new ConcreteProtoFileDescriptorSupplier(lamda.LamdaProto.javaDescriptor))
    .addMethod(METHOD_ADD)
    .addMethod(METHOD_SUB)
    .addMethod(METHOD_MUL)
    .addMethod(METHOD_DIV)
    .build()
  
  trait Calculator extends AbstractService {
    override def serviceCompanion = Calculator
    def add: Flow[lamda.Operator, lamda.Output, NotUsed]
    def sub: Flow[lamda.Operator, lamda.Output, NotUsed]
    def mul: Flow[lamda.Operator, lamda.Output, NotUsed]
    def div: Flow[lamda.Operator, lamda.Output, NotUsed]
  }
  
  object Calculator extends ServiceCompanion[Calculator] {
    implicit def serviceCompanion: ServiceCompanion[Calculator] = this
    def javaDescriptor: ServiceDescriptor =
      lamda.LamdaProto.javaDescriptor.getServices().get(0)
  }
  
  class CalculatorStub(
    channel: Channel,
    options: CallOptions = CallOptions.DEFAULT
  ) extends AbstractStub[CalculatorStub](channel, options) with Calculator {
    override def add: Flow[lamda.Operator, lamda.Output, NotUsed] =
      Flow[lamda.Operator].flatMapConcat(request =>
        Source.fromFuture(
          Grpc.guavaFuture2ScalaFuture(
            ClientCalls.futureUnaryCall(channel.newCall(METHOD_ADD, options), request)
          )
        )
      )
    override def sub: Flow[lamda.Operator, lamda.Output, NotUsed] =
      Flow[lamda.Operator].flatMapConcat(request =>
        Source.fromFuture(
          Grpc.guavaFuture2ScalaFuture(
            ClientCalls.futureUnaryCall(channel.newCall(METHOD_SUB, options), request)
          )
        )
      )
    override def mul: Flow[lamda.Operator, lamda.Output, NotUsed] =
      Flow[lamda.Operator].flatMapConcat(request =>
        Source.fromFuture(
          Grpc.guavaFuture2ScalaFuture(
            ClientCalls.futureUnaryCall(channel.newCall(METHOD_MUL, options), request)
          )
        )
      )
    override def div: Flow[lamda.Operator, lamda.Output, NotUsed] =
      Flow[lamda.Operator].flatMapConcat(request =>
        Source.fromFuture(
          Grpc.guavaFuture2ScalaFuture(
            ClientCalls.futureUnaryCall(channel.newCall(METHOD_DIV, options), request)
          )
        )
      )
    override def build(channel: Channel, options: CallOptions): CalculatorStub =
      new CalculatorStub(channel, options)
  }
  
  def bindService(serviceImpl: Calculator)(implicit mat: Materializer): ServerServiceDefinition =
    ServerServiceDefinition
      .builder("Calculator")
      .addMethod(
        METHOD_ADD,
        ServerCalls.asyncUnaryCall(
          new ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
            override def invoke(request: lamda.Operator, responseObserver: StreamObserver[lamda.Output]) =
              Source
                .single(request)
                .via(serviceImpl.add)
                .runForeach(responseObserver.onNext)
                .onComplete {
                  case Success(_) => responseObserver.onCompleted()
                  case Failure(t) => responseObserver.onError(t)
                }(mat.executionContext)
          }
        )
      )
      .addMethod(
        METHOD_SUB,
        ServerCalls.asyncUnaryCall(
          new ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
            override def invoke(request: lamda.Operator, responseObserver: StreamObserver[lamda.Output]) =
              Source
                .single(request)
                .via(serviceImpl.sub)
                .runForeach(responseObserver.onNext)
                .onComplete {
                  case Success(_) => responseObserver.onCompleted()
                  case Failure(t) => responseObserver.onError(t)
                }(mat.executionContext)
          }
        )
      )
      .addMethod(
        METHOD_MUL,
        ServerCalls.asyncUnaryCall(
          new ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
            override def invoke(request: lamda.Operator, responseObserver: StreamObserver[lamda.Output]) =
              Source
                .single(request)
                .via(serviceImpl.mul)
                .runForeach(responseObserver.onNext)
                .onComplete {
                  case Success(_) => responseObserver.onCompleted()
                  case Failure(t) => responseObserver.onError(t)
                }(mat.executionContext)
          }
        )
      )
      .addMethod(
        METHOD_DIV,
        ServerCalls.asyncUnaryCall(
          new ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
            override def invoke(request: lamda.Operator, responseObserver: StreamObserver[lamda.Output]) =
              Source
                .single(request)
                .via(serviceImpl.div)
                .runForeach(responseObserver.onNext)
                .onComplete {
                  case Success(_) => responseObserver.onCompleted()
                  case Failure(t) => responseObserver.onError(t)
                }(mat.executionContext)
          }
        )
      )
      .build()
  
  def stub(channel: Channel): CalculatorStub = new CalculatorStub(channel)
  
  def javaDescriptor: ServiceDescriptor =
    lamda.LamdaProto.javaDescriptor.getServices().get(0)
}
