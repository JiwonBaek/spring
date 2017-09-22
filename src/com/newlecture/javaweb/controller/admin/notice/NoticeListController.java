package com.newlecture.javaweb.controller.admin.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

@WebServlet("/admin/notice/list")
public class NoticeListController extends HttpServlet{
         @Override
         protected void service(
               HttpServletRequest request, 
               HttpServletResponse response) throws ServletException, IOException{ 
         
        	 //인증 한 적 없다-> 로그인
        	 
        	 //인증정보 받고 그 역할이 관리자인지 확인
        	 
        	 //없으면 권한없음을 알림
        	 
        	 
        	 
         String _title = request.getParameter("title");
         String _page = request.getParameter("p");
            
         int page = 1; 
         
         if(_page != null && !_page.equals("")) 
            page = Integer.parseInt(_page); 
         
         int offset = (page-1)*10;
         
         String title=""; 
         
         if(_title != null && !_title.equals(""))
            title=_title;
         /*----------------------------------------------------*/
         
       NoticeDao noticeDao = new JdbcNoticeDao();

      request.setAttribute("list",  noticeDao.getList(page,title));
      request.setAttribute("count",noticeDao.getCount());
      
      //response.sendRedirect("notice.jsp"); 
      request.getRequestDispatcher("/WEB-INF/views/admin/notice/list.jsp").forward(request,response);
   }
}
