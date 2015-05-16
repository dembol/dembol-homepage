package controllers

import models.domain.context.{Context, ContextRepository}
import org.junit.runner.RunWith
import org.specs2.mock.Mockito
import org.specs2.mutable.{BeforeAfter, Specification}
import org.specs2.runner.JUnitRunner
import play.api.mvc._
import play.api.test.Helpers._

import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class AuthActionTest extends Specification with Mockito {

  val TestId: String = "some id"

  val requestMock: Request[AnyRef] = mock[Request[AnyRef]]
  val sessionMock: Session = mock[Session]
  val contextRepositoryMock: ContextRepository = mock[ContextRepository]
  val actionMock: Action[AnyRef] = mock[Action[AnyRef]]
  val futureMock: Future[Result] = mock[Future[Result]]
  val contextMock: Option[Context] = mock[Option[Context]]

  val sut: AuthAction[AnyRef] = AuthAction[AnyRef](actionMock, contextRepositoryMock)

  trait TestContext extends BeforeAfter {
    override def before: Any = {
      actionMock.apply(requestMock) returns futureMock
      contextRepositoryMock.find(TestId) returns contextMock
    }

    override def after: Any = {
    }
  }

  "apply" should {
    "redirect to login when context id does not exists" in {
      requestMock.session returns sessionMock
      sessionMock.get("contextId") returns None

      val result: Future[Result] = sut.apply(requestMock)

      status(result) mustEqual 303
      redirectLocation(result) mustEqual Some("/login.html")
    }

    "redirect to panel when context id exists" in new TestContext {
      requestMock.session returns sessionMock
      sessionMock.get("contextId") returns Some(TestId)

      val result: Future[Result] = sut.apply(requestMock)

      result must beTheSameAs(futureMock)
    }
  }
}
