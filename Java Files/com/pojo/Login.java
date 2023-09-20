package com.pojo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import data.connector.Database;
import data.login.FriendRequest;
import data.login.Login1;
import data.pass.Password;

public class Login extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	SessionMap<String, String> sessionmap;
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String fname;
	private String sname;
	private String email;
	private String mob;
	private String pass;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String login() {
		Login1 loginBean = Database.login(this.username);
		if (this.username.equals(loginBean.getUsername()) && this.pass.equals(loginBean.getPass())) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Login1 user = Database.login(this.username);
			session.setAttribute("username", this.username);
			session.setAttribute("id", user.getId());
			session.setAttribute("fname", user.getFname());
			session.setAttribute("sname", user.getSname());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("mob", user.getMob());
			session.setAttribute("name", loginBean.getFname() + " " + loginBean.getSname());
			return "success";
		} else
			return "failure";

	}

	public String register() {
		String page="";
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Login1 loginBean = Database.login(this.username);
		if (loginBean.getUsername()==null)
		{   String pwd = Password.getAlphaNumericString(8);
		this.setPass(pwd);
			int status = Database.save(this);
			if (status > 0) {

				request.setAttribute("username", this.username);
				request.setAttribute("pass", pwd);
				request.setAttribute("msg", "Successfully Registered");
				page="success";
			}

		} else {
			request.setAttribute("msg", this.username + " already exist. please choose another Username");
			page="failure";
		}
		return page;
	}

	public String signupRequest() {
		return "success";

	}

	public String loginPage() {
		return "success";

	}

	public String sendRequest() throws SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		int receiver_id = Integer.parseInt(request.getParameter("id"));
		Login1 sender = Database.login(username);
		request.setAttribute("requestMsg", "Request Sent Successfully!!");
		System.out.println("id" + id + "userename=" + username);
		int status = Database.saveRequest(sender.getId(), receiver_id, "sent");
		if (status > 0) {
			return "success";
		} else
			return "failure";

	}

	public String friendRequests() throws SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString();
		ArrayList<Login1> requestList = new ArrayList<>();
		ArrayList<FriendRequest> list = Database.getRequestsList(Integer.parseInt(id));
		for (FriendRequest fr : list) {
			int sender_id = fr.getSender_id();
			if (fr.getStatus().equals("sent"))
				requestList.add(Database.getById(sender_id));
		}
		request.setAttribute("requests", requestList);
		return "success";
	}

	public String myFriendsList() throws SQLException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String id = session.getAttribute("id").toString();
		ArrayList<Login1> requestList = new ArrayList<>();
		ArrayList<FriendRequest> list = Database.getRequestsList(Integer.parseInt(id));
		for (FriendRequest fr : list) {
			int sender_id = fr.getSender_id();
			if (fr.getStatus().equals("Accepted")) {
				if (sender_id != Integer.parseInt(id))
					requestList.add(Database.getById(sender_id));
				else
					requestList.add(Database.getById(fr.getReceiver_id()));
			}
		}
		request.setAttribute("requests", requestList);
		return "success";
	}

	public String acceotRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int sender_id = Integer.parseInt(request.getParameter("sender_id"));
		int receiver_id = Integer.parseInt(request.getParameter("receiver_id"));
		int status = Database.updateStatus(sender_id, receiver_id, "Accepted");
		if (status > 0) {
			request.setAttribute("successMsg", "Accepted");
			return "success";
		}
		return null;
	}

	public String rejectRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int sender_id = Integer.parseInt(request.getParameter("sender_id"));
		int receiver_id = Integer.parseInt(request.getParameter("receiver_id"));
		int status = Database.updateStatus(sender_id, receiver_id, "Rejected");
		if (status > 0) {
			request.setAttribute("successMsg", "Rejected");
			return "success";
		}
		return null;
	}

	public String viewFrofile() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Login1 user = (Login1) Database.login(username);
		request.setAttribute("user", user);
		return "success";
	}

	public String viewAllUsers() {
		HttpServletRequest request = ServletActionContext.getRequest();
		ArrayList<Login1> userList = Database.getUsers();
		request.setAttribute("userList", userList);
		return "success";
	}

	public void setSession(Map map) {
		sessionmap = (SessionMap) map;
		sessionmap.put("login", "true");
	}

	public String logout() {

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("msg", "Successfully Logout!!");
		sessionmap.invalidate();
		return "success";
	}

}
