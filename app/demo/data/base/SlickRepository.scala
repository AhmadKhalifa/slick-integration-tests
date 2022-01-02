package demo.data.base

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.dbio.DBIO
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

abstract class SlickRepository(databaseConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfig[JdbcProfile] {

  def run[A](action: DBIO[A]): Future[A] =
    db.run(action)

  override protected val dbConfig = databaseConfigProvider.get
}
