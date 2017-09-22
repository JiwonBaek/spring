package com.newlecture.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.javaweb.dao.NoticeDao;
import com.newlecture.javaweb.entity.Notice;
import com.newlecture.javaweb.entity.NoticeView;




public class JdbcNoticeDao implements NoticeDao {

	public List<NoticeView> getList(int page, String query){
		 String sql = "SELECT *FROM CommunityView WHERE title like ? order by regDate desc limit ?, 10";       
         String url = "jdbc:mysql://211.238.142.247/puppydb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
         // JDBC ����̹� �ε�
         
         List<NoticeView> list=null;
         int offset = (page-1)*10;

         try {
            Class.forName("com.mysql.jdbc.Driver");
            
            // ���� / ����
            Connection con = DriverManager.getConnection(url, "puppy", "0728");
         
             // ����
             //Statement st = con.createStatement();
             PreparedStatement st = con.prepareStatement(sql);
             st.setString(1, "%"+query+"%");
             st.setInt(2, offset);
  
             
             // ��� ��������
             ResultSet rs = st.executeQuery();
         
             // Model 
             list = new ArrayList<>();
             
             // ��� ����ϱ�
             while (rs.next()) {
                NoticeView n = new NoticeView();
                n.setId(rs.getString("ID"));
                n.setTitle(rs.getString("TITLE"));
            	n.setWriterId(rs.getString("writerid"));
            	n.setWriterName(rs.getString("writerName"));
				n.setHit(rs.getInt("hit"));
				n.setRegDate(rs.getDate("regDate"));
				n.setCountCmt(rs.getInt("countCmt"));
				
                
                list.add(n);
         }
             
         rs.close();
         st.close();
         con.close();
         
            
         } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
		return list;
	}

	public int getCount() {
		int count =0;
		
         String sqlCount = "SELECT COUNT(id) COUNT FROM Board";         
         String url = "jdbc:mysql://211.238.142.247/puppydb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
         
         // JDBC ����̹� �ε�
         try {
            Class.forName("com.mysql.jdbc.Driver");
            
            // ���� / ����
            Connection con = DriverManager.getConnection(url, "puppy", "0728");
         
             // ����
             //Statement st = con.createStatement();
             
             
             Statement stCount = con.createStatement();
             ResultSet rsCount = stCount.executeQuery(sqlCount);
             rsCount.next();
             count = rsCount.getInt("count");
             
             // ��� ��������
 
             
         rsCount.close();
         con.close();
         
            
         } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
		return count;
	}

	@Override
	public NoticeView get(String id) {
		
		NoticeView n = null;
		String url = "jdbc:mysql://211.238.142.247/puppydb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "SELECT * FROM CommunityView WHERE id LIKE ?";

		// Jdbc ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ���� / ����
			 Connection conn = DriverManager.getConnection(url, "puppy", "0728");

			// ����
			/* Statement st = conn.createStatement(); */
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1,id);
			// ��� ��������
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				n = new NoticeView();
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
		return  n;
	}

	@Override
	public int update(String id, String title, String content) {
		int result =0;
		String url = "jdbc:mysql://211.238.142.247/puppydb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "UPDATE Board SET title=?, content=? where id=?";

		// Jdbc ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ���� / ����
			 Connection conn = DriverManager.getConnection(url, "puppy", "0728");

			// ����
			/* Statement st = conn.createStatement(); */
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql);
			st.setString(1,title); //����ǥ�� �˸°� ���� ����
			st.setString(2,content);
			st.setString(3,id);
			
			// �ԷµǾ����� �˷��ִ� ����� �����´�.
			result = st.executeUpdate();
			
			st.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
