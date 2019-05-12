addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.20")

resolvers += Resolver.bintrayRepo("beyondthelines", "maven")

libraryDependencies ++= Seq(
//  "com.thesamet.scalapb" %% "compilerplugin" % "0.9.0-RC1",
//  "com.trueaccord.scalapb" %% "compilerplugin"          % "0.6.7",
//  "beyondthelines"         %% "grpcgatewaygenerator"    % "0.0.9",
//  "beyondthelines"         %% "grpcmonixgenerator"      % "0.0.7",
//  "beyondthelines"         %% "grpcakkastreamgenerator" % "0.1.1"
"com.trueaccord.scalapb" %% "compilerplugin"          % "0.6.7",
"beyondthelines"         %% "grpcgatewaygenerator"    % "0.0.6",
"beyondthelines"         %% "grpcmonixgenerator"      % "0.0.5",
"beyondthelines"         %% "grpcakkastreamgenerator" % "0.0.5"
)
