package demo.gateway.user.mapper

import demo.domain.user.model.User
import demo.gateway.user.payload.{CreateUserRequest, UserResponse}

import javax.inject.Singleton

@Singleton
class UserMapper {

  def toDomain(createUserRequest: CreateUserRequest): User =
    User(
      None,
      None,
      createUserRequest.username,
      createUserRequest.email,
      createUserRequest.firstName,
      createUserRequest.lastName
    )

  def fromDomain(user: User): UserResponse =
    UserResponse(
      user.uuid,
      user.profileUUID,
      user.username,
      user.email,
      user.firstName,
      user.lastName,
    )
}
