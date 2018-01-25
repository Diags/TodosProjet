package com.App;

import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.entity.Todo;
import com.repo.TodoRepository;
@SpringBootApplication
@ComponentScan(basePackages = {"com.service", "com.controler"})
@EntityScan("com.entity")
@EnableJpaRepositories("com.repo")

public class TodoAppApplication implements CommandLineRunner {
	@Autowired
private TodoRepository todorepo;
	public static void main(String[] args) {
	/*	ApplicationContext context =*/	SpringApplication.run(TodoAppApplication.class, args);
		/*Todo cust = (Todo)context.getBean("Todo");
		cust.setTitle("ddddddd");
    	System.out.println(cust.getTitle())*/;
	}

	@Override
public void run(String... arg0) throws Exception {
		Stream.of("bonjour","comment tu vas","Chocola").forEach(name ->todorepo.save(new Todo(name)));
		todorepo.findAll().forEach(System.out::println);
		
	}
}
