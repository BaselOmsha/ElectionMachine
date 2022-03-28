package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.dao.Dao;
import app.security.SecurityUtils;

@WebServlet(
		name = "RegisterCandidate",
		urlPatterns = {"/candidate"}
		)
public class RegisterCandidateApp extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		response.sendRedirect("login.html");
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		PrintWriter out=response.getWriter();
		try {
		Dao dao = new Dao();

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String ssn = request.getParameter("ssn");
		String party= request.getParameter("party");
		String email = request.getParameter("email");
		String uname = request.getParameter("uname");
		int age = Integer.parseInt(request.getParameter("age"));
		String paswd = request.getParameter("paswd");

	
		if (
				fname == null || fname.isEmpty() ||
				lname == null || lname.isEmpty() ||
				ssn == null || ssn.isEmpty() ||
				party == null || party.isEmpty() ||
				uname == null || uname.isEmpty() ||
				request.getParameter("age") == null || request.getParameter("age").isEmpty() ||
				paswd == null || paswd.isEmpty() 
				) {
				
			RequestDispatcher rd=request.getRequestDispatcher("/candFillUp.html");
	        rd.include(request,  response);
	       
		 } else if (dao.checkUname(uname)){  //if user name is in use reload the form
			 RequestDispatcher rd=request.getRequestDispatcher("/unameTakenCan.html");
		        rd.include(request,  response); 
	    } else{
	        
		// Create salt and hashed pw
		String salt = SecurityUtils.getSalt();
		String hashpw = SecurityUtils.getPasswordHashed(paswd, salt);
		
		dao.addUser(fname, lname, ssn, email, uname, hashpw, salt);
		
		dao.close();
		response.sendRedirect("candFilled.html");
		
	}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}



