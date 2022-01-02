package demo.data.user.dao

import demo.data.user.entity.UserEntity
import play.api.db.slick.HasDatabaseConfig
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

trait UserDao {
  self: HasDatabaseConfig[JdbcProfile] =>

  import profile.api._

  class UserTable(tag: Tag) extends Table[UserEntity](tag, "app_user") {

    def uuid: Rep[String]     = column[String]("uuid")
    def username: Rep[String] = column[String]("username")
    def email: Rep[String]    = column[String]("email")

    override def * : ProvenShape[UserEntity] =
      (uuid, username, email) <> (UserEntity.tupled, UserEntity.unapply)
  }

  lazy val UserTable = TableQuery[UserTable]
}
