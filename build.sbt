name := "shashank_maithani_cs441_hw6"

//version := "0.1"

scalaVersion := "2.12.8"

lazy val commonSettings = Seq(
  organization := "Shanks.lamda",
  version := "0.1"
)
lazy val app = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "LamdaGrpc"
  ).
  enablePlugins(AssemblyPlugin)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

resolvers += Resolver.bintrayRepo("beyondthelines", "maven")
//PB.pythonExe := "C:\\ProgramData\\Python2.7\\python.exe"
PB.targets in Compile := Seq(
//  scalapb.gen(grpc=true, javaConversions=true) -> (sourceManaged in Compile).value,
//  PB.gens.java -> (sourceManaged in Compile).value,
  scalapb.gen() -> (sourceManaged in Compile).value,
  // generate Swagger spec files into the `resources/specs`
  grpcgateway.generators.SwaggerGenerator -> (resourceDirectory in Compile).value / "specs",
  // generate the Rest Gateway source code
  grpcgateway.generators.GatewayGenerator -> (sourceManaged in Compile).value,
  // generate the reactive (monix) files
  grpcmonix.generators.GrpcMonixGenerator() -> (sourceManaged in Compile).value,
  // generate the akka stream files
  grpc.akkastreams.generators.GrpcAkkaStreamGenerator() -> (sourceManaged in Compile).value
)

//val scalapbVersion = com.trueaccord.scalapb.compiler.Version.scalapbVersion

libraryDependencies ++= Seq(
  "com.trueaccord.scalapb" %% "scalapb-runtime" % "0.6.7" % "protobuf",
// for gRPC
  "io.grpc"                %  "grpc-netty"            % "1.8.0",
  
  "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % "0.6.7",
// for JSON conversion
  "com.trueaccord.scalapb" %% "scalapb-json4s"        % "0.3.0",
  // for GRPC Gateway
  "beyondthelines"         %% "grpcgatewayruntime"    % "0.0.6" % "compile,protobuf",
  // for GRPC Monix
  "beyondthelines"         %% "grpcmonixruntime"      % "0.0.5",
  // for GRPC Akkastream
  "beyondthelines"         %% "grpcakkastreamruntime" % "0.0.5"
)

scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation")
