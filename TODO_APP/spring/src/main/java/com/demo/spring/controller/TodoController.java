package com.demo.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.spring.model.Todo;
import com.demo.spring.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;
	
	
	//DISPLAY TODOS
	@RequestMapping(value="/todoList", method = RequestMethod.GET)
	public String showTodosList(ModelMap model) {
	
		String name = getLoggedInUserName(model);
		model.put("todos", todoService.retrieveTodos(name));
//		System.out.println(todoService.retrieveTodos("saidheeraj"));

		return "todoList";
	}

//This getLoggedInUserName method is used for above method in displaying todos. This below method will gets the name of the user 
//	which will be passed to the above method in displaying the todos of a particular user;
	
	//BY SESSION
		
//	private String getLoggedInUserName(ModelMap model) {
//		return (String) model.get("name");
//	}
	
	//BY SPRING SECURITY
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}
	
	
	//ADDING EMPTY TODOS
	@RequestMapping(value="/add-todo", method=RequestMethod.GET)
	public String displaytodos (ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model),"Default Desc", new Date(), false));
		return "todo";
	}
	
	//ADDING TODOS WITH VALUES
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addtodos(ModelMap model, @Valid @ModelAttribute("todo") Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		todoService.addTodo(getLoggedInUserName(model), todo.getDescription(), todo.getTargetDate(), false);
	return "redirect:/todoList";	
	};
	
	
	//SHOWING VALUES ON THE FORM FOR UPDATING TODOS
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.retrieveTodo(id); 
		model.put("todo", todo);
		return "todo";
	}
	
	//UPDATING TODOS
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateTodos(@RequestParam int id, ModelMap model,@Valid @ModelAttribute("todo") Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		todo.setUser(getLoggedInUserName(model));
//		todo.setTargetDate(new Date());
		todoService.updateTodo(todo);
		return "redirect:/todoList";
	}
	
	//FOR SETTING CUSTOM DATE
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	
	//DELETE TODOS
	@RequestMapping(value="/delete-todo", method=RequestMethod.GET)
	public String deletetodo(@RequestParam int id) {
		todoService.deleteTodo(id); 
		return "redirect:/todoList";
	}
}


