package pro.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pro.dao.Person;

@Configuration
public class ConfigProcessor {
	Logger log = LoggerFactory.getLogger(ConfigProcessor.class);


	@Bean
	public ItemProcessor<Person, Person> processor2() {
		return person -> {
			if (person.getGender().equalsIgnoreCase("female"))
				return person;
			return null;
		};
	}

}
