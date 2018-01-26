package com.example.TodoApp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.controler.TodoController;
import com.entity.Todo;
import com.repo.TodoRepository;
import com.service.ITodoService;
import com.service.TodoImpl;


@RunWith(MockitoJUnitRunner.class)
public class TodoAppApplicationTests {
	@Mock
	private TodoRepository todoRepo;
	@Mock
	private ITodoService iTodoService = new TodoImpl() ;
	@InjectMocks
	private TodoController todoControl = new TodoController(iTodoService);
	private Boolean textVirify = false;
	@Mock
	private Todo todo;

	@Before
	public void setUpMockito() throws InterruptedException {

	}

	@Test
	public void shouldReturnTodo() {
		todo=	new Todo("bombon");
		when(iTodoService.createTodo(todo)).thenReturn(todo);
		todoControl.setTodoRepository(iTodoService);
		Todo todoSave = todoControl.createTodo(todo);
		Assert.assertTrue(todoSave.getTitle().contains("bombon"));
	}//

	@Test
	public void getSuccessResultTest() {
		// Given
		todo=	new Todo("bombon");
		java.util.List<Todo> listtodo =  Arrays.asList(new Todo("bombon"),new Todo("tartre"),new Todo("chocola"));
		// When
		Mockito.when(todoControl.getAllTodos()).thenReturn(listtodo);
		// todoControl.setTodoRepository(iTodoService);// injection du mock dans
		// la ressource
		// injection du mock dans la ressource
		todoControl.setTodoRepository(iTodoService);
		// interaction entre le mockSession et le
		java.util.List<Todo> listtodos =	todoControl.getAllTodos();					// service
		// Thes
		 Mockito.verify(iTodoService).getAllTodos();

		todoControl.getAllTodos().stream().map(title -> title.getTitle()).forEach(title -> {
			textVirify = title.contains("Bombon");
		});
		Assert.assertFalse(textVirify);
	}

}
