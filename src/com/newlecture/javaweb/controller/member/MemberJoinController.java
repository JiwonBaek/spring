package com.newlecture.javaweb.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.MemberDao;
import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcMemberDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Member;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet {

	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	String id=request.getParameter("id");
	String[] pwds=request.getParameterValues("pwd");
	String isLunar=request.getParameter("is-Lunar");
	String name =request.getParameter("name");
	String gender=request.getParameter("gender");
	String birthday =request.getParameter("birthday");
	String phone=request.getParameter("phone");
	String email=request.getParameter("email");
	System.out.printf("id :%s, pwd : %s, isLunar : %s",id ,pwds[0] ,isLunar);
	
	MemberDao memberDao =new JdbcMemberDao();
	/*int result= memberDao.insert(id,pwds[0],name,gender,birthday,phone,email);*/
	
	
	Member member = new Member(id,pwds[0],name,gender,birthday,phone,email);
	int result=memberDao.insert(member);
	
	if(result>0)
		response.sendRedirect("confirm");
	else
		response.sendRedirect("../error?code=1234");
	
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		request.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(request, response); // �̾ ���
	
	}

	
	

}
