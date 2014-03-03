package com.sdm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;  

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sdm.DAO.UserDAO;
import com.sdm.model.User;

public class UserAction extends ActionSupport implements ModelDriven<User>, SessionAware {

	HttpServletRequest request;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	User user = new User();
	List<User> users = new ArrayList<User>();
	UserDAO userDAO = new UserDAO();
	private SessionMap<String,Object> sessionMap;

	@Override
	public User getModel() {
		return user;
	}

	public String addUser() {
		System.out.println("*********");
		System.out.println(user.getUserId());
		System.out.println(user.getPassword());
		user.setAccessLevel("user");
		userDAO.addUser(user);
		return "success";
	}
	
	

	public String login() {
		String result = "failure";
		User loggedUser = userDAO.getUserById(user.getUserId());
		if (loggedUser.getPassword().equals(user.getPassword())) {
			sessionMap.put("userId", loggedUser.getUserId());
			sessionMap.put("accessLevel", loggedUser.getAccessLevel());
			result = "success";
		}
		return result;
	}

	public String logout(){
		String result = "failure";
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
		if(session!=null){  
			session.invalidate();
			result = "success";
		}
		return result;		
	}
	
	public String listUsers() {
		users = userDAO.getUsers();
		return "success";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}
}
