package com.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.entity.Todo;
import com.repo.TodoRepository;

@Service
@Transactional
public class TodoImpl implements ITodoService {
	@Autowired
	private TodoRepository todoRepository;

	@Override
	public List<Todo> getAllTodos() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return todoRepository.findAll(sortByCreatedAtDesc);
	}

	@Override
	public Todo createTodo(Todo todo) {
		todo.setCompleted(false);
		return todoRepository.save(todo);
	}

	@Override
	public ResponseEntity<Todo> getTodoById(Long id) {
		Todo todo = todoRepository.findOne(id);
		if (todo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Todo>(todo, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Todo> updateTodo(Long id, Todo todo) {
		Todo todoData = todoRepository.findOne(id);
		if (todoData == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		todoData.setTitle(todo.getTitle());
		todoData.setCompleted(todo.getCompleted());
		Todo updatedTodo = todoRepository.save(todoData);
		return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
	}

	@Override
	public void deleteTodo(Long id) {
		todoRepository.delete(id);
	}
}
