import sbt._
import Keys._
import com.github.siasia._
import PluginKeys._
import WebPlugin._
import WebappPlugin._

object LiftProjectBuild extends Build {
  override lazy val settings = super.settings ++ buildSettings
  
  lazy val buildSettings = Seq(
    organization := "com.yourorganization",
    version      := "0.1-SNAPSHOT",
    scalaVersion := "2.9.1")
  
  def yourWebSettings = webSettings ++ Seq(
    // If you are use jrebel
    scanDirectories in Compile := Nil
    )
  
  lazy val liftQuickstart = Project(
    id = "lift-quickstart",
    base = file("."),
    settings = defaultSettings ++ yourWebSettings)
    
  lazy val defaultSettings = Defaults.defaultSettings ++ Seq(
    name := "lift-quickstart",
    resolvers ++= Seq(
      "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases", 
      "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"),
    
    libraryDependencies ++= {
	  val liftVersion = "2.4"
	  Seq(
	    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
	    "org.eclipse.jetty" % "jetty-webapp" % "7.5.4.v20111024" % "container",
	    "ch.qos.logback" % "logback-classic" % "1.0.0" % "compile",
	    "org.scalatest" %% "scalatest" % "1.6.1" % "test",
	    "junit" % "junit" % "4.10" % "test")
	},

    // compile options
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked"),
    javacOptions  ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),

    // show full stack traces
    testOptions in Test += Tests.Argument("-oF")
  )
}

