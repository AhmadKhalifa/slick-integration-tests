package demo.domain.user.repository

import demo.domain.user.model.User

import scala.concurrent.Future

trait UserRepository {

  def findAll: Future[Seq[User]]

  def findAllByUUID(uuid: String): Future[Option[User]]

  def save(user: User): Future[User]

  def delete(uuid: String): Future[Int]
}
