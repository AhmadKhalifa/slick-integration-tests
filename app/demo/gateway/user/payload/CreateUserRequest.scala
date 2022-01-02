package demo.gateway.user.payload

import play.api.libs.json.{Json, Reads}

case class CreateUserRequest(
  username: String,
  email: String,
  firstName: String,
  lastName: String
)

object CreateUserRequest {
  implicit val reads: Reads[CreateUserRequest] = Json.reads[CreateUserRequest]
}
