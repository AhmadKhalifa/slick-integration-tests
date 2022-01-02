package demo.data.user.dao

import demo.data.user.entity.ProfileEntity
import play.api.db.slick.HasDatabaseConfig
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

trait ProfileDao {
  self: HasDatabaseConfig[JdbcProfile] =>

  import profile.api._

  class ProfileTable(tag: Tag) extends Table[ProfileEntity](tag, "profile") {

    def uuid: Rep[String]      = column[String]("uuid")
    def userUUID: Rep[String]  = column[String]("user_uuid")
    def firstName: Rep[String]  = column[String]("first_name")
    def lastName: Rep[String]  = column[String]("last_name")

    override def * : ProvenShape[ProfileEntity] =
      (uuid, userUUID, firstName, lastName) <> (ProfileEntity.tupled, ProfileEntity.unapply)
  }

  lazy val ProfileTable = TableQuery[ProfileTable]
}
