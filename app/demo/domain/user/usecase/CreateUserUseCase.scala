package demo.domain.user.usecase

import demo.domain.user.model.User
import demo.domain.user.repository.UserRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class CreateUserUseCase @Inject()(userRepository: UserRepository)
  extends (User => Future[User]) {

  override def apply(user: User): Future[User] =
    userRepository save user
}
