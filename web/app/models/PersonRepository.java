package models;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import com.google.inject.ImplementedBy;

/**
 * This interface provides a non-blocking API for possibly blocking operations.

 */
@ImplementedBy(JPAPersonRepository.class)
public interface PersonRepository {
	
	CompletionStage<Person> add(Person person);
	
	CompletionStage<Stream<Person>> list();

}
