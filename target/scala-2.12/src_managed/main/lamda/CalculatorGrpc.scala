package lamda

object CalculatorGrpc {
  val METHOD_ADD: _root_.io.grpc.MethodDescriptor[lamda.Operator, lamda.Output] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Calculator", "Add"))
      .setRequestMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(lamda.Operator))
      .setResponseMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(lamda.Output))
      .build()
  
  val METHOD_SUB: _root_.io.grpc.MethodDescriptor[lamda.Operator, lamda.Output] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Calculator", "Sub"))
      .setRequestMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(lamda.Operator))
      .setResponseMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(lamda.Output))
      .build()
  
  val METHOD_MUL: _root_.io.grpc.MethodDescriptor[lamda.Operator, lamda.Output] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Calculator", "Mul"))
      .setRequestMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(lamda.Operator))
      .setResponseMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(lamda.Output))
      .build()
  
  val METHOD_DIV: _root_.io.grpc.MethodDescriptor[lamda.Operator, lamda.Output] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("Calculator", "Div"))
      .setRequestMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(lamda.Operator))
      .setResponseMarshaller(new com.trueaccord.scalapb.grpc.Marshaller(lamda.Output))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("Calculator")
      .setSchemaDescriptor(new _root_.com.trueaccord.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(lamda.LamdaProto.javaDescriptor))
      .addMethod(METHOD_ADD)
      .addMethod(METHOD_SUB)
      .addMethod(METHOD_MUL)
      .addMethod(METHOD_DIV)
      .build()
  
  trait Calculator extends _root_.com.trueaccord.scalapb.grpc.AbstractService {
    override def serviceCompanion = Calculator
    def add(request: lamda.Operator): scala.concurrent.Future[lamda.Output]
    def sub(request: lamda.Operator): scala.concurrent.Future[lamda.Output]
    def mul(request: lamda.Operator): scala.concurrent.Future[lamda.Output]
    def div(request: lamda.Operator): scala.concurrent.Future[lamda.Output]
  }
  
  object Calculator extends _root_.com.trueaccord.scalapb.grpc.ServiceCompanion[Calculator] {
    implicit def serviceCompanion: _root_.com.trueaccord.scalapb.grpc.ServiceCompanion[Calculator] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = lamda.LamdaProto.javaDescriptor.getServices().get(0)
  }
  
  trait CalculatorBlockingClient {
    def serviceCompanion = Calculator
    def add(request: lamda.Operator): lamda.Output
    def sub(request: lamda.Operator): lamda.Output
    def mul(request: lamda.Operator): lamda.Output
    def div(request: lamda.Operator): lamda.Output
  }
  
  class CalculatorBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[CalculatorBlockingStub](channel, options) with CalculatorBlockingClient {
    override def add(request: lamda.Operator): lamda.Output = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_ADD, options), request)
    }
    
    override def sub(request: lamda.Operator): lamda.Output = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_SUB, options), request)
    }
    
    override def mul(request: lamda.Operator): lamda.Output = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_MUL, options), request)
    }
    
    override def div(request: lamda.Operator): lamda.Output = {
      _root_.io.grpc.stub.ClientCalls.blockingUnaryCall(channel.newCall(METHOD_DIV, options), request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): CalculatorBlockingStub = new CalculatorBlockingStub(channel, options)
  }
  
  class CalculatorStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[CalculatorStub](channel, options) with Calculator {
    override def add(request: lamda.Operator): scala.concurrent.Future[lamda.Output] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_ADD, options), request))
    }
    
    override def sub(request: lamda.Operator): scala.concurrent.Future[lamda.Output] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_SUB, options), request))
    }
    
    override def mul(request: lamda.Operator): scala.concurrent.Future[lamda.Output] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_MUL, options), request))
    }
    
    override def div(request: lamda.Operator): scala.concurrent.Future[lamda.Output] = {
      com.trueaccord.scalapb.grpc.Grpc.guavaFuture2ScalaFuture(_root_.io.grpc.stub.ClientCalls.futureUnaryCall(channel.newCall(METHOD_DIV, options), request))
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): CalculatorStub = new CalculatorStub(channel, options)
  }
  
  def bindService(serviceImpl: Calculator, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
    _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
    .addMethod(
      METHOD_ADD,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
        override def invoke(request: lamda.Operator, observer: _root_.io.grpc.stub.StreamObserver[lamda.Output]): Unit =
          serviceImpl.add(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_SUB,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
        override def invoke(request: lamda.Operator, observer: _root_.io.grpc.stub.StreamObserver[lamda.Output]): Unit =
          serviceImpl.sub(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_MUL,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
        override def invoke(request: lamda.Operator, observer: _root_.io.grpc.stub.StreamObserver[lamda.Output]): Unit =
          serviceImpl.mul(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .addMethod(
      METHOD_DIV,
      _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[lamda.Operator, lamda.Output] {
        override def invoke(request: lamda.Operator, observer: _root_.io.grpc.stub.StreamObserver[lamda.Output]): Unit =
          serviceImpl.div(request).onComplete(com.trueaccord.scalapb.grpc.Grpc.completeObserver(observer))(
            executionContext)
      }))
    .build()
  
  def blockingStub(channel: _root_.io.grpc.Channel): CalculatorBlockingStub = new CalculatorBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): CalculatorStub = new CalculatorStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = lamda.LamdaProto.javaDescriptor.getServices().get(0)
  
}