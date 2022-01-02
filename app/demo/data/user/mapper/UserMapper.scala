package demo.data.user.mapper

import demo.data.user.entity.{ProfileEntity, UserEntity}
import demo.domain.user.model.User

import java.util.UUID
import javax.inject.Singleton

@Singleton
class UserMapper {

  def toDomain(userDetails: (UserEntity, ProfileEntity)): User =
    userDetails match {
      case (user, profile) =>
        User(
          Some(user.uuid),
          Some(profile.uuid),
          user.username,
          user.email,
          profile.firstName,
          profile.lastName
        )
    }

  def fromDomain(user: User): (UserEntity, ProfileEntity) = {
    val userUUID = user.uuid getOrElse UUID.randomUUID().toString
    (
      UserEntity(
        userUUID,
        user.username,
        user.email
      ),
      ProfileEntity(
        user.profileUUID getOrElse UUID.randomUUID().toString,
        userUUID,
        user.firstName,
        user.lastName
      )
    )
  }
}
