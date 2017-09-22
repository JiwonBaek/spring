package com.newlecture.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.newlecture.javaweb.dao.MemberDao;
import com.newlecture.javaweb.dao.MemberRoleDao;
import com.newlecture.javaweb.entity.Member;
import com.newlecture.javaweb.entity.NoticeView;

public class JdbcMemberDao implements MemberDao{

   @Override
   public int insert(String id, String pwd, String name, String phone,String email) {
   
      return insert(new Member(id,pwd,name,null,null,phone,email));
   }

   @Override
   public Member get(String id) {
      
      Member m =null;
        String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
         String sql = "SELECT * FROM Member WHERE id like ?";

         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "sist", "cclass");
            // Statement st = conn.createStatement();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
             m = new Member();
                   m.setId(rs.getString("id"));
                   m.setPwd(rs.getString("pwd"));
                   m.setName(rs.getString("name"));
                   m.setGender(rs.getString("gender"));
                   m.setBirthday(rs.getString("birthday"));
                   m.setPhone(rs.getString("phone"));
                   m.setEmail(rs.getString("email"));
                   m.setIsLunar(rs.getString("isLunar"));
            }

            rs.close();
            st.close();
            conn.close();

         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } catch (SQLException e) {
            e.printStackTrace();
         }
         
      return m;

   }

   
   /*아래 insert만 구현하자*/
   @Override
   public int insert(Member member) {

      int result=0;
        String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
         String sql = "insert into Member(id, pwd, name,phone,email,birthday) values (?,?,?,?,?,?)";
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "sist", "cclass");
            // Statement st = conn.createStatement();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,member.getId());
            st.setString(2,member.getPwd() );
            st.setString(3,member.getName() );
            st.setString(4,member.getPhone());
            st.setString(5,member.getEmail());
            st.setString(6,member.getBirthday());


            result = st.executeUpdate();
       /*    업데이트 된 row갯수를 알려줌. st.executeUpdate*/

            st.close();
            conn.close();

         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } catch (SQLException e) {
            e.printStackTrace();
         }
         return result;
       /*  response.sendRedirect("list");*/
     /*    sendRedirect그냥 딴페이지로 가는 거. 목록을 보여줘야되니까*/
      
    /*  super.doPost(request, response);*/
      
 }

@Override
public int insert(String id, String pwd, String name, String gender, String birthday, String phone, String email) {
	// TODO Auto-generated method stub
	return 0;
}



   }