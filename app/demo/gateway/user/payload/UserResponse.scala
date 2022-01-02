package demo.gateway.user.payload

import play.api.libs.json.{Json, Writes}

case class UserResponse (
  uuid: Option[String],
  profileUUID: Option[String],
  username: String,
  email: String,
  firstName: String,
  lastName: String
)

object UserResponse {
  implicit val writes: Writes[UserResponse] = Json.writes[UserResponse]
}
