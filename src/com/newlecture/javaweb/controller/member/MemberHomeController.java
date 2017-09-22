package com.newlecture.javaweb.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.javaweb.dao.MemberRoleDao;
import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcMemberRoleDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Member;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/member/home")
public class MemberHomeController extends HttpServlet {
	@Override

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		HttpSession session = request.getSession();
		
		//1.로그인한 적이 없을 때 로그인하러가기


		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out =response.getWriter();
		
		Object _memberId=session.getAttribute("id");
		if(_memberId==null)
			out.write("<script> alert('로그인이 필요합니다.');location.href='login?returnURL=home';</script>");
			else {
				String memberId = _memberId.toString(); 
				MemberRoleDao memberRoleDao =new JdbcMemberRoleDao();
				String defaltRoleId = memberRoleDao.getDefaultRoleId(memberId);
				
        //response.sendRedirect("home");
		
		/*
		 * if(session.getAttribute("id")==null) 
		 * response.sendRedirect("login"); 
		 * else
		 * response.sendRedirect("home");
		 */
	  
		//2. 로그인 안하고 온 경우 로그인 후 기본 경로
				if(defaltRoleId.equals("ROLE_ADMIN")) 
				response.sendRedirect("../admin/index");
				else if(defaltRoleId.equals("ROLE_STUDENT")) 
				response.sendRedirect("../student/index");
				else
					response.sendRedirect("../index");
				
			}
		
		
		
	/*	response.sendRedirect("../index");*/
		

	}

	

}