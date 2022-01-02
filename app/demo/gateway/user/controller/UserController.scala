package demo.gateway.user.controller

import demo.domain.user.usecase._
import demo.gateway.user.mapper.UserMapper
import demo.gateway.user.payload.CreateUserRequest
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(
  getAllUsersUseCase: GetAllUsersUseCase,
  getUserByUUIDUseCase: GetUserByUUIDUseCase,
  createUserUseCase: CreateUserUseCase,
  deleteUserUseCase: DeleteUserUseCase,
  userMapper: UserMapper,
  controllerComponents: ControllerComponents
)(implicit ec: ExecutionContext) extends AbstractController(controllerComponents) {

  private def errorResponse(status: Status, message: String): Future[Result] =
    Future(status.apply(Json.obj("message" -> message)))

  def getAllUsers: Action[AnyContent] = Action.async { _ =>
    getAllUsersUseCase()
      .map(users => users.map(userMapper.fromDomain))
      .map(users => Ok(Json.toJson(users)))
  }

  def getUserByUUID(uuid: String): Action[AnyContent] = Action.async { _ =>
    getUserByUUIDUseCase(uuid)
      .map(user => user.map(userMapper.fromDomain))
      .map {
        case Some(user) => Ok(Json.toJson(user))
        case None => NotFound
      }
  }

  def createUser: Action[AnyContent] = Action.async { request =>
    request.body.asJson match {
      case Some(jsonBody) =>
        jsonBody.validate[CreateUserRequest] match {
          case JsSuccess(createUserRequest, _) =>
            createUserUseCase(userMapper.toDomain(createUserRequest))
              .map(userMapper.fromDomain)
              .map(user => Ok(Json.toJson(user)))
          case JsError(errors) =>
            errorResponse(BadRequest, s"Invalid body: $errors")
        }
      case None =>
        errorResponse(BadRequest, "No body found")
    }
  }

  def deleteUser(uuid: String): Action[AnyContent] = Action.async {
    deleteUserUseCase(uuid)
      .map {
        case 0 => NotFound
        case _ => NoContent
      }
  }
}
