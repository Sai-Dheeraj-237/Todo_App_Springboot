package com.demo.spring.service;

import org.springframework.stereotype.Service;

import com.demo.spring.model.Todo;

//import com.demo.spring.model.Todo;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

	
	private static List <Todo> todos = new ArrayList<Todo>();
	private static int todoCount = 3;
	
	static {
		todos.add(new Todo(1,"saidheeraj","completed java core", new Date(), false));
		todos.add(new Todo(2,"saidheeraj", "completed hibernate", new Date(), false));
		todos.add(new Todo(3,"saidheeraj", "learning springboot", new Date(), false));
	}

	//FOR DISPLAYING TODOS
	@Override
	public List<Todo> retrieveTodos(String user) {
		// TODO Auto-generated method stub
		List<Todo> filteredTodoList = new ArrayList<Todo>();
		for (Todo todo : todos) {
			if(todo.getUser().equals(user)) {
				filteredTodoList.add(todo);
			}
		}
		
		return filteredTodoList;
	}
	
	//FOR DISPLAYING THE TODO IN ORDER TO UPDATE THE TODO
	@Override
	public Todo retrieveTodo(int id) {
		// TODO Auto-generated method stub
		for (Todo todo : todos) {
			if(todo.getId()==id) {
				return todo;
			}
		}
		
		return null;
	}
	
	//FOR UPDATING TODO
	@Override
	public void updateTodo(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
	}
	
	
	
	//FOR ADDING TODO
	@Override
	public void addTodo(String name, String description, Date targetDate, boolean isDone) {
		// TODO Auto-generated method stub
		todos.add(new Todo(++ todoCount, name, description, targetDate, isDone));
	}

	//FOR DELEING TODO
	@Override
	public void deleteTodo(int id) {
		// TODO Auto-generated method stub
		Iterator<Todo> iterator = todos.iterator();
		while(iterator.hasNext()) {
			Todo td = iterator.next();
			if(td.getId()==id) {
				iterator.remove();
			}
		}
		
	}




	
	
}
