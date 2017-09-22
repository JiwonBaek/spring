package com.newlecture.javaweb.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/student/index")
public class StudentIndexController extends HttpServlet {

   
    protected void service(  HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException{
       
       request.getRequestDispatcher("/WEB-INF/views/student/index.jsp").forward(request, response);
       
       /*캐비닛 비우는 방법 !! 로그아웃하면 로그인한 정보를 다 없애야하니까 !!*/
       
    //   session.removeAttribute(arg0); 이건 세션에 저장된 걸 하나씩 하나씩 다 비우는 거
   //request.getRequestDispatcher("/WEB-INF/views/member/logout.jsp").forward(request, response);
   
      }
    
}