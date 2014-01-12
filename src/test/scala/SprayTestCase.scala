package org.mael.scala.spray.http.client.test

import org.hamcrest.CoreMatchers._

import scala.concurrent.duration._

import org.junit.runner.RunWith
import org.junit.Assert._
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuiteLike

import spray.http._
import spray.client.pipelining._
import akka.actor.ActorSystem
import scala.concurrent.{Await, Future}


@RunWith(classOf[JUnitRunner])
class SprayTestCase extends FunSuiteLike {

  implicit val system = ActorSystem()

  import system.dispatcher

  test("The Basics - Retrieve an URL") {

    // given
    val pipeline: HttpRequest => Future[HttpResponse] = sendReceive

    // when
    val response: Future[HttpResponse] = pipeline(Get("http://www.google.com/"))

    // then
    val statusCode = Await.result(response, 100 seconds).status

    println(s"Status code: ${statusCode.intValue}")

    assertThat(statusCode.intValue, is(200))

  }


}

