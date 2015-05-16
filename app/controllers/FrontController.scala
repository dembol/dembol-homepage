package controllers

import play.api.mvc._

object FrontController extends AbstractController {

  implicit val context = play.api.libs.concurrent.Execution.Implicits.defaultContext

  def index() =
    Action { request =>
      Results.Ok(views.html.index())
    }

  def resume =
    Action { request =>
      Results.Ok(views.html.index.render())
    }

  def content_dynamic(tripId: play.libs.F.Option[String], from: play.libs.F.Option[Long]) =
    Action {
      Results.Ok(views.html.grid.dynamic())
    }
}

