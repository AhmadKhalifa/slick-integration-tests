package demo.domain.user.model

case class User (
  uuid: Option[String],
  profileUUID: Option[String],
  username: String,
  email: String,
  firstName: String,
  lastName: String
)
