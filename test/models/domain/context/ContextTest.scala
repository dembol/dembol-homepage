package models.domain.context

import models.domain.auth.Token
import models.domain.user.User
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

import scala.collection.mutable
import scala.util.Random

class ContextTest extends Specification with Mockito {

  val tokenMock: Token = mock[Token]
  val userMock: User = mock[User]

  "the constructor" >> {
    "generate unique id" >> {
      val expectedIdsCount: Int = math.abs(Random.nextInt(1000))

      val ids = mutable.Set.empty[String]
      for (i <- 1 to expectedIdsCount) {
        val context = new Context(userMock)
        ids += context.id
      }

      ids must size(expectedIdsCount)
    }
  }

  "copyWith" >> {
    "create new object" >> {
      val context = new Context(userMock)
      val newContext = context.copyWith("new id")

      newContext must not beTheSameAs context
    }
  }
}
