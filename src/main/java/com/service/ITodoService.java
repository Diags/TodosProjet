package com.service;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.entity.Todo;
@Service
public interface ITodoService {
	public List<Todo> getAllTodos();
	public Todo createTodo( Todo todo);
	public ResponseEntity<Todo> getTodoById( Long id);
	public ResponseEntity<Todo> updateTodo(Long id, Todo todo);
	public void deleteTodo(Long id);
}
