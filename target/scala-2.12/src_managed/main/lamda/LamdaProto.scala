// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package lamda



object LamdaProto extends _root_.com.trueaccord.scalapb.GeneratedFileObject {
  lazy val dependencies: Seq[_root_.com.trueaccord.scalapb.GeneratedFileObject] = Seq(
    com.google.api.annotations.AnnotationsProto
  )
  lazy val messagesCompanions: Seq[_root_.com.trueaccord.scalapb.GeneratedMessageCompanion[_]] = Seq(
    lamda.Operator,
    lamda.Output
  )
  private lazy val ProtoBytes: Array[Byte] =
      com.trueaccord.scalapb.Encoding.fromBase64(scala.collection.Seq(
  """CgtsYW1kYS5wcm90bxocZ29vZ2xlL2FwaS9hbm5vdGF0aW9ucy5wcm90byIyCghPcGVyYXRvchISCgR2YXJBGAEgASgJUgR2Y
  XJBEhIKBHZhckIYAiABKAlSBHZhckIiIAoGT3V0cHV0EhYKBnJlc3VsdBgBIAEoCVIGcmVzdWx0MrwBCgpDYWxjdWxhdG9yEioKA
  0FkZBIJLk9wZXJhdG9yGgcuT3V0cHV0Ig+C0+STAgkSBC9hZGQ6ASoSKgoDU3ViEgkuT3BlcmF0b3IaBy5PdXRwdXQiD4LT5JMCC
  RIEL3N1YjoBKhIqCgNNdWwSCS5PcGVyYXRvchoHLk91dHB1dCIPgtPkkwIJEgQvbXVsOgEqEioKA0RpdhIJLk9wZXJhdG9yGgcuT
  3V0cHV0Ig+C0+STAgkSBC9kaXY6ASpiBnByb3RvMw=="""
      ).mkString)
  lazy val scalaDescriptor: _root_.scalapb.descriptors.FileDescriptor = {
    val scalaProto = com.google.protobuf.descriptor.FileDescriptorProto.parseFrom(ProtoBytes)
    _root_.scalapb.descriptors.FileDescriptor.buildFrom(scalaProto, dependencies.map(_.scalaDescriptor))
  }
  lazy val javaDescriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val javaProto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(ProtoBytes)
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(javaProto, Array(
      com.google.api.annotations.AnnotationsProto.javaDescriptor
    ))
  }
  @deprecated("Use javaDescriptor instead. In a future version this will refer to scalaDescriptor.", "ScalaPB 0.5.47")
  def descriptor: com.google.protobuf.Descriptors.FileDescriptor = javaDescriptor
}