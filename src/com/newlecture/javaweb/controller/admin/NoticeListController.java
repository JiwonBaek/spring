package com.newlecture.javaweb.controller.admin;

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

import com.newlecture.javaweb.entity.Notice;

@WebServlet("/admin/notice-List")
public class NoticeListController extends HttpServlet{
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		String _title = request.getParameter("title");
		String title=""; //기본�?

		if(_title != null && !_title.equals(""))
			title = _title;

		List<Notice> list = null;
		String sql = "SELECT * FROM Notice where title like ?";
		// 쿼리�? 복잡?���?�? ?��기�? ?��?��?�� ?��?�� ?�? ?���? ?��?��?��?�� 처리?��

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ?��?��?���? 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ?���? / ?���?
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ?��?��
			// Statement st = con.createStatement();
			// PreparedStatement?�� 미리 sql?�� ?��?��것이�?�? ?��?��?��?�� sql?�� 빼줘?��?��
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + title + "%");

			// 결과 �??��?���?
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// Model
			list = new ArrayList<>();

			// 결과 ?��?��?���?
			while (rs.next()) {
				Notice n = new Notice();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				// ..

				list.add(n);
			}
			rs.close();
			st.close();
			con.close();

			/* out.println(list.get(0).getTitle()); */

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	request.setAttribute("list", list);
		
//	response.sendRedirect("notice.jsp"); //?��로출발하?��바업
	request.getRequestDispatcher("/WEB-INF/views/admin/notice/list.jsp").forward(request, response); //?��?��?�� 출발?��?�� 방법
	//redirect
	//forward
	}
	

}