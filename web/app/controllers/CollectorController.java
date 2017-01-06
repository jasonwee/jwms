package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class CollectorController extends Controller {
	
	
	public Result collect() {
		//Result notFound = notFound();
		//Result pageNotFound = notFound("<h1>Page not found</h1>").as("text/html");
		//Result badRequest = badRequest(views.html.form.render(formWithErrors));
		//Result oops = internalServerError("Oops");
		//Result anyStatus = status(488, "Strange response type");
		return ok("collected " + request() + "!");
	}
	
	public Result index() {
		//     return temporaryRedirect("/user/home");
		return redirect("/login");
	}

}
