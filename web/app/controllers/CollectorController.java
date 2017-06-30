package controllers;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import javax.inject.Inject;

import models.Person;
import models.PersonRepository;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import static play.libs.Json.toJson;

public class CollectorController extends Controller {
	
	private final FormFactory formFactory;
	private final PersonRepository personRepository;
	private final HttpExecutionContext ec;

	@Inject
	public CollectorController(FormFactory formFactory, PersonRepository personRepository, HttpExecutionContext ec) {
		this.formFactory = formFactory;
		this.personRepository = personRepository;
		this.ec = ec;
	}

    public Result index() {
    	Form<Person> form = formFactory.form(Person.class);
        return ok(views.html.index.render(form));
    }

	public CompletionStage<Result> addPerson() {
		Person person = formFactory.form(Person.class).bindFromRequest().get();
		return personRepository.add(person).thenApplyAsync(p -> {
			return redirect(routes.CollectorController.index());
		}, ec.current());
	}

	public CompletionStage<Result> getPersons() {
		return personRepository.list().thenApplyAsync(personStream -> {
			return ok(toJson(personStream.collect(Collectors.toList())));
		}, ec.current());
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

	public Result index1() {
		//     return temporaryRedirect("/user/home");
		return redirect("/login");
	}
	// -----------old-------------

}
