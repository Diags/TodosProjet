package com.controler;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import com.entity.Todo;
import com.service.ITodoService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

    @Autowired
    ITodoService todoRepository;

    public TodoController(ITodoService todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	@GetMapping("/listTodos")
    public List<Todo> getAllTodos() {
       
        return todoRepository.getAllTodos();
    }

   @PostMapping("/todos")
   // @RequestMapping(value = "/todos", method = RequestMethod.POST)
    @ResponseBody
    public   Todo createTodo( @Valid @RequestBody Todo todo) {
       
        return todoRepository.createTodo(todo);
    }

    @GetMapping(value="/todos/{id}")
   // @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id) {
       
            return todoRepository.getTodoById(id);
        }
    

    @PutMapping(value="/todos/{id}")
   // @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") Long id,
                                           @Valid @RequestBody Todo todo) {
       
        return todoRepository.updateTodo(id, todo);
    }

    @DeleteMapping(value="/todos/{id}")
   // @RequestMapping(method = RequestMethod.DELETE)
    public void deleteTodo(@PathVariable("id") Long id) {
        todoRepository.deleteTodo(id);
    }


	public void setTodoRepository(ITodoService todoRepository) {
		this.todoRepository = todoRepository;
	}
    
}