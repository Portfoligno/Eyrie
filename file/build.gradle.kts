plugins {
  maven
  scala
  `java-library`
}
val scalaCompilerPlugin: Configuration = configurations.create("scalaCompilerPlugin")

dependencies {
  scalaCompilerPlugin("org.scalamacros:paradise_2.12.8:2.1.1")
  api("org.scala-lang:scala-library:2.12.8")

  api(project(":core", "default"))

  testImplementation("junit:junit:4.12")
  testImplementation("org.scalatest:scalatest_2.12:3.0.5")
  testCompileOnly("com.chuusai:shapeless_2.12:2.3.3")
}

tasks.withType<ScalaCompile> {
  scalaCompileOptions.additionalParameters = listOf(
      "-Xplugin:" + scalaCompilerPlugin.asPath,
      "-Ypartial-unification",
      "-language:higherKinds",
      "-language:implicitConversions")
}
