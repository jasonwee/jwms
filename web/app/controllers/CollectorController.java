package controllers;

import java.util.List;

import javax.inject.Inject;

import models.Person;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import static play.libs.Json.toJson;

public class CollectorController extends Controller {
	
	private final FormFactory formFactory;
	private final JPAApi jpaApi;

	@Inject
	public CollectorController(FormFactory formFactory, JPAApi jpaApi) {
		this.formFactory = formFactory;
		this.jpaApi = jpaApi;
	}

	public Result index1() {
		//return ok(views.html.index1.render());
		return ok();
	}

	@Transactional
	public Result addPerson() {
		Person person = formFactory.form(Person.class).bindFromRequest().get();
		jpaApi.em().persist(person);
		return redirect(routes.CollectorController.index());
	}

	@Transactional(readOnly=true)
	public Result getPersons() {
		List<Person> persons = (List<Person>) jpaApi.em().createQuery("select p from Person p").getResultList();
		return ok(toJson(persons));
	}

	// -----------old-------------
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
	// -----------old-------------

}
