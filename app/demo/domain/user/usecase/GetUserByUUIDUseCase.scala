package demo.domain.user.usecase

import demo.domain.user.model.User
import demo.domain.user.repository.UserRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class GetUserByUUIDUseCase @Inject() (userRepository: UserRepository)
  extends (String => Future[Option[User]]) {

  override def apply(userUUID: String): Future[Option[User]] =
    userRepository findAllByUUID userUUID
}
