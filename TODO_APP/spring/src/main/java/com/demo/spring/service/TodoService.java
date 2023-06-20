package com.demo.spring.service;

import java.util.Date;
import java.util.List;

import com.demo.spring.model.Todo;

public interface TodoService {

	public List<Todo> retrieveTodos(String user);
	public void addTodo(String name, String description, Date targetDate,
            boolean isDone);
	public void deleteTodo(int id);
	public Todo retrieveTodo(int id);
	void updateTodo(Todo todo);
}
