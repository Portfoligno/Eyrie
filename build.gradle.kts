plugins {
  maven
}
tasks.getByName<Wrapper>("wrapper") {
  gradleVersion = "4.10.3"
}

subprojects {
  afterEvaluate {
    convention.findPlugin(JavaPluginConvention::class)?.apply {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
    }
  }

  repositories {
    jcenter()
  }
}
