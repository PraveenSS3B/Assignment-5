package pro.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import pro.dao.Person;

@Configuration
public class ConfigReader {

	Logger log = LoggerFactory.getLogger(ConfigReader.class);


	public LineMapper<Person> lineMapperTXT() {
		DefaultLineMapper<Person> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(" ");
		lineTokenizer.setNames(new String[] { "id", "firstName", "lastName", "gender" });
		BeanWrapperFieldSetMapper<Person> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<Person>();
		beanWrapperFieldSetMapper.setTargetType(Person.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		return defaultLineMapper;
	}

	@Bean
	public FlatFileItemReader<Person> txtReader() {
		FlatFileItemReader<Person> flatFileItemReader = new FlatFileItemReader<Person>();
		flatFileItemReader.setResource(new ClassPathResource("/input/person.txt"));
		flatFileItemReader.setLineMapper(lineMapperTXT());
		return flatFileItemReader;
	}

}
