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

@WebServlet("/admin/notice/edit")
public class NoticeEditController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String id= request.getParameter("id");
		String title=request.getParameter("title");
		String content =request.getParameter("content");
		
	
		
	NoticeDao noticeDao = new JdbcNoticeDao();
	noticeDao.update(id,title,content);
		
		response.sendRedirect("notice/detail");
	}
	
	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String _id = request.getParameter("id");

		Notice n = null;

		String id = ""; 
		if (_id != null && !_id.equals(""))
			id = _id;

		/* System.out.println(title); */

		// List<Notice> list = null;

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "SELECT * FROM Notice WHERE id LIKE ?";

	
		try {
			Class.forName("com.mysql.jdbc.Driver");

	
			Connection conn = DriverManager.getConnection(url, "sist", "cclass"); // dclass

	
			/* Statement st = conn.createStatement(); */
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1,id);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				n = new Notice();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setContent(rs.getString("CONTENT"));
				n.setWriterId(rs.getString("writerid"));
				n.setHit(rs.getInt("hit"));
				n.setRegDate(rs.getDate("regDate"));
			}

			rs.close();
			st.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("notice", n);
		// response.sendRedirect("notice.jsp"); //�ƿ� ���� ���
		request.getRequestDispatcher("/WEB-INF/views/admin/notice/edit.jsp").forward(request, response); // �̾ ���
		// redirect
		// forward
		// �Ѵ� �������� �ٸ����� �̵��� �� ���.

	}

}