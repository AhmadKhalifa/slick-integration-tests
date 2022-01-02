package demo.domain.user.usecase

import demo.domain.user.repository.UserRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class DeleteUserUseCase @Inject()(userRepository: UserRepository)
  extends (String => Future[Int]) {

  override def apply(userUUID: String): Future[Int] =
    userRepository delete userUUID
}
