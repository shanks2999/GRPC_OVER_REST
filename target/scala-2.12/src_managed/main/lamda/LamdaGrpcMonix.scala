package lamda

import _root_.com.google.protobuf.Descriptors.ServiceDescriptor
import _root_.com.trueaccord.scalapb.grpc.{ AbstractService, ConcreteProtoFileDescriptorSupplier, Marshaller, ServiceCompanion }
import _root_.io.grpc.{ CallOptions, Channel, MethodDescriptor, ServerServiceDefinition }
import _root_.grpcmonix.GrpcMonix._
import _root_.io.grpc.stub.{ AbstractStub, ClientCalls, ServerCalls, StreamObserver }
import _root_.monix.eval.Task
import _root_.monix.execution.{ Cancelable, Scheduler }
import _root_.monix.reactive.Observable
import _root_.org.reactivestreams.{ Publisher => PublisherR, Subscriber => SubscriberR }

object LamdaGrpcMonix {
  
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
    def add(request: lamda.Operator): Task[lamda.Output]
    def sub(request: lamda.Operator): Task[lamda.Output]
    def mul(request: lamda.Operator): Task[lamda.Output]
    def div(request: lamda.Operator): Task[lamda.Output]
  }
  
  object Calculator extends ServiceCompanion[Calculator] {
    implicit def serviceCompanion: ServiceCompanion[Calculator] = this
    def javaDescriptor: ServiceDescriptor = lamda.LamdaProto.javaDescriptor.getServices().get(0)
  }
  
  class CalculatorStub(
    channel: Channel,
    options: CallOptions = CallOptions.DEFAULT
  ) extends AbstractStub[CalculatorStub](channel, options) with Calculator {
    override def add(request: lamda.Operator): Task[lamda.Output] = 
      guavaFutureToMonixTask(
        ClientCalls.futureUnaryCall(channel.newCall(METHOD_ADD, options), request)
      )
    override def sub(request: lamda.Operator): Task[lamda.Output] = 
      guavaFutureToMonixTask(
        ClientCalls.futureUnaryCall(channel.newCall(METHOD_SUB, options), request)
      )
    override def mul(request: lamda.Operator): Task[lamda.Output] = 
      guavaFutureToMonixTask(
        ClientCalls.futureUnaryCall(channel.newCall(METHOD_MUL, options), request)
      )
    override def div(request: lamda.Operator): Task[lamda.Output] = 
      guavaFutureToMonixTask(
        ClientCalls.futureUnaryCall(channel.newCall(METHOD_DIV, options), request)
      )
    override def build(channel: Channel, options: CallOptions): CalculatorStub = 
      new CalculatorStub(channel, options)
  }
  
  def bindService(serviceImpl: Calculator, scheduler: Scheduler): ServerServiceDefinition = 
    ServerServiceDefinition
      .builder("Calculator")
      .addMethod(
        METHOD_ADD,
        ServerCalls.asyncUnaryCall(
          new ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
            override def invoke(request: lamda.Operator, observer: StreamObserver[lamda.Output]): Unit =
              serviceImpl.add(request).runAsync(grpcObserverToMonixCallback(observer))(scheduler)
          }
        )
      )
      .addMethod(
        METHOD_SUB,
        ServerCalls.asyncUnaryCall(
          new ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
            override def invoke(request: lamda.Operator, observer: StreamObserver[lamda.Output]): Unit =
              serviceImpl.sub(request).runAsync(grpcObserverToMonixCallback(observer))(scheduler)
          }
        )
      )
      .addMethod(
        METHOD_MUL,
        ServerCalls.asyncUnaryCall(
          new ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
            override def invoke(request: lamda.Operator, observer: StreamObserver[lamda.Output]): Unit =
              serviceImpl.mul(request).runAsync(grpcObserverToMonixCallback(observer))(scheduler)
          }
        )
      )
      .addMethod(
        METHOD_DIV,
        ServerCalls.asyncUnaryCall(
          new ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
            override def invoke(request: lamda.Operator, observer: StreamObserver[lamda.Output]): Unit =
              serviceImpl.div(request).runAsync(grpcObserverToMonixCallback(observer))(scheduler)
          }
        )
      )
      .build()
  
  def stub(channel: Channel): CalculatorStub = new CalculatorStub(channel)
  
  def javaDescriptor: ServiceDescriptor = 
    lamda.LamdaProto.javaDescriptor.getServices().get(0)
}
