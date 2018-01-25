package com.example.TodoApp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.Arrays;

import org.glassfish.grizzly.http.server.HttpServer;

import javax.inject.Inject;
import javax.validation.constraints.AssertFalse;
import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import org.glassfish.*;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.controler.TodoController;
import com.entity.Todo;
import com.repo.TodoRepository;
import com.service.ITodoService;
import com.service.TodoImpl;

@RunWith(SpringRunner.class)
public class TodoAppApplicationTests {
	@Mock
private TodoRepository todoRepo ;
	//private TodoImpl todoImpl= new TodoImpl(todoRepo);
	@Mock
private ITodoService iTodoService =new TodoImpl(todoRepo);
private TodoController todoControl =  new  TodoController(iTodoService);
	private Boolean  textVirify=false;
	@Mock
	private Todo todo ;


@Before
public void setUpMockito() throws InterruptedException {

}



@Test
public void shouldReturnTodo() {
when(todoRepo.save(todo)).thenReturn(todo);
Todo saveTodo = todoControl.createTodo(todo);
assertThat(saveTodo,is(equals(todo)));
}
@Test
public void getSuccessResultTest() {
	// Given
	// When
	Mockito.when(todoControl.getAllTodos());
	todoControl.setTodoRepository(iTodoService);// injection du mock dans la ressource
	// injection du mock dans la ressource
	todoControl.getAllTodos();// interaction entre le mockSession et le service
	// Then
	Mockito.verify(iTodoService).getAllTodos();

   todoControl.getAllTodos().stream().map(title-> title.getTitle()).forEach(title->{ textVirify = title.contains("Bombon");});
   Assert.assertTrue(textVirify);
}


}


