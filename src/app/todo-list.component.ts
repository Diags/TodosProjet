import { Component, OnInit } from '@angular/core';
import { Todo } from './todo';
import { NgForm } from '@angular/forms';
import { TodoService } from './todo.service';
import { error } from 'util';
@Component({
  selector: 'todo-list',
  templateUrl: './todo-list.component.html'
})

export class TodoListComponent implements OnInit {
    todos: Todo[];
    newTodo: Todo = new Todo();
    editing: boolean = false;
    editingTodo: Todo = new Todo();
    statusMessage:string="";
    constructor(
      private todoService: TodoService,
    ) {}
  
    ngOnInit(): void {
      this.getTodos();
    }
  
    getTodos(): void {
      this.todoService.getTodos()
        .then(todos => this.todos = todos ).catch((error)=>{
          this.statusMessage = "Problem with the servie call";
          throw(error);
        });    
    }
  
    createTodo(todoForm: NgForm): void {
      this.todoService.createTodo(todoForm.value)
      .then(createTodo => {   
        console.log(todoForm.value);
        this.newTodo = todoForm.value;
        this.todos.push( this.newTodo) ; 
        this.getTodos();
        todoForm.reset();
  
      }, error=>{
        console.log("coooooollll this is error from data "+ error);
      }).catch((error)=>{
          console.log("this is resquest probleme"+error);
          throw(error);
        })
    }
  
    deleteTodo(id: string): void {
      this.todoService.deleteTodo(id)
      .then(() => {
      this.todos = this.todos.filter(todo => todo.id != id);
        this.getTodos();
      }, error=>{
        console.log("coooooollll this is error from data "+ error);
      }).catch((error)=>{
          console.log("this is resquest probleme"+error);
          throw("this is resquest probleme"+error);
    
    });
    }
  
    updateTodo(todoData: Todo): void {
      console.log(todoData);
      this.todoService.updateTodo(todoData)
      .then(updatedTodo => {
        let existingTodo = this.todos.find(todo => todo.id === updatedTodo.id);
        Object.assign(existingTodo, updatedTodo);
        this.clearEditing();
      });
    }
  
    toggleCompleted(todoData: Todo): void {
      todoData.completed = !todoData.completed;
      this.todoService.updateTodo(todoData)
      .then(updatedTodo => {
        let existingTodo = this.todos.find(todo => todo.id === updatedTodo.id);
        Object.assign(existingTodo, updatedTodo);
      });
    }
  
    editTodo(todoData: Todo): void {
      this.editing = true;
      Object.assign(this.editingTodo, todoData);
    }
  
    clearEditing(): void {
      this.editingTodo = new Todo();
      this.editing = false;
    }
  }