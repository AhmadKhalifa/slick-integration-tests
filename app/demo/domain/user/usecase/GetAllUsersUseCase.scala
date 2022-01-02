package demo.domain.user.usecase

import demo.domain.user.model.User
import demo.domain.user.repository.UserRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future
import scala.language.postfixOps

@Singleton
class GetAllUsersUseCase @Inject() (userRepository: UserRepository)
  extends (() => Future[Seq[User]]) {

  override def apply(): Future[Seq[User]] =
    userRepository findAll
}
