package com.uniquedevepler.resgistration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uemail= request.getParameter("username");
		String upwd= request.getParameter("password");
	HttpSession session = request.getSession();
		RequestDispatcher dispacter = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		Connection	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usecompany?useSSL=false","root","hg");
		PreparedStatement psn= con.prepareStatement(" select * from users where uemail = ?  and upwd = ?");
		psn.setString(1, uemail);
		psn.setString(2, upwd);
		
		
		
		ResultSet rs = psn.executeQuery();
		if (rs.next()) {
			session.setAttribute("name", rs.getString("uemail"));
			dispacter = request.getRequestDispatcher("index.jsp");
			
		}else {
			request.setAttribute("status", "failed");
			dispacter = request.getRequestDispatcher("login.jsp");
						}
		dispacter.forward(request, response);
		
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}

}
