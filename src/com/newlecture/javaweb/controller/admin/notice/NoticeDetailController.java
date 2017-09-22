package com.newlecture.javaweb.controller.admin.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.dao.jdbc.JdbcNoticeDao;
import com.newlecture.javaweb.entity.Notice;

@WebServlet("/admin/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String _id = request.getParameter("id");
/*
		Notice n = null;*/

		String id = ""; // 기본값
		if (_id != null && !_id.equals(""))
			id = _id;
		NoticeDao dao = new JdbcNoticeDao();
		Notice n = dao.get(id);
	
		request.setAttribute("notice", n);
		// response.sendRedirect("notice.jsp"); //아예 새로 출발
		request.getRequestDispatcher("/WEB-INF/views/admin/notice/detail.jsp").forward(request, response); // 이어서 출발
		// redirect
		// forward
		// 둘다 페이지를 다른데로 이동할 때 사용.

	}

}