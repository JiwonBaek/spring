package com.newlecture.javaweb.controller.admin.notice;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

import com.newlecture.javaweb.entity.Notice;

@WebServlet("/admin/notice/reg")
public class NoticeRegController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
	
		String title=request.getParameter("title");
		String content =request.getParameter("content");
		
	/*	"SELECT IFNULL(MAX(CAST(id as unsigned)),0)+1 from Notice;"*/
		
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		 String sql = "insert into Notice (id, title,content,writerId) values ((select IFNULL(MAX(CAST(id as unsigned)),0)+1 from Notice as b) ,?,?,?)";

		// Jdbc 드라이버 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// 연결 / 인증
			Connection conn = DriverManager.getConnection(url, "sist", "cclass"); // dclass

			// 실행
			/* Statement st = conn.createStatement(); */
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1,title); //물음표에 알맞게 넣을 값들
			st.setString(2,content);
			st.setString(3,"newlec");
			
			
			// 입력되었는지 알려주는 결과를 가져온다.
			int result = st.executeUpdate();
			
			st.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("notice/list");
	}
	
	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out =response.getWriter();
		
		HttpSession session = request.getSession();
		
		Object _memberId=session.getAttribute("id");
		if(_memberId==null)
			out.write("<script> alert('로그인이 필요합니다.');location.href='../../member/login'returnURL='../../member/home';</script>");
			else
				request.getRequestDispatcher("/WEB-INF/views/admin/notice/reg.jsp").forward(request, response);
		
		// response.sendRedirect("notice.jsp"); //아예 새로 출발
		 // 이어서 출발
		// redirect
		// forward
		// 둘다 페이지를 다른데로 이동할 때 사용.

	}

}