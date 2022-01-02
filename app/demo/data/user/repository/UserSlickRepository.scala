package demo.data.user.repository

import demo.data.base.SlickRepository
import demo.data.user.mapper.UserMapper
import demo.data.user.dao.{ProfileDao, UserDao}
import demo.domain.user.model.User
import demo.domain.user.repository.UserRepository
import play.api.db.slick.DatabaseConfigProvider

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserSlickRepository @Inject()(
  userMapper: UserMapper,
  databaseConfigProvider: DatabaseConfigProvider
)(implicit val executionContext: ExecutionContext) extends SlickRepository(databaseConfigProvider)
  with UserRepository
  with UserDao
  with ProfileDao {

  import profile.api._

  override def findAll: Future[Seq[User]] =
    run {
      (UserTable join ProfileTable on { case (user, profile) => user.uuid === profile.userUUID })
        .result
        .map(results => results.map(userMapper.toDomain))
    }

  override def findAllByUUID(uuid: String): Future[Option[User]] =
    run {
      (UserTable join ProfileTable on { case (user, profile) => user.uuid === profile.userUUID })
        .filter { case (user, _) => user.uuid === uuid }
        .result
        .headOption
        .map(result => result.map(userMapper.toDomain))
    }

  override def save(user: User): Future[User] =
    run {
      userMapper.fromDomain(user) match {
        case (user, profile) =>
          for {
            _ <- UserTable += user
            _ <- ProfileTable += profile
          } yield userMapper.toDomain(user, profile)
      }
    }

  override def delete(uuid: String): Future[Int] =
    run {
      (for {
        deletedProfiles <- ProfileTable.filter(_.userUUID === uuid).delete
        deletedUsers   <- UserTable.filter(_.uuid === uuid).delete
      } yield deletedProfiles + deletedUsers).transactionally
    }
}
