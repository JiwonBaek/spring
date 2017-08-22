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

@WebServlet("/notice")
public class Nana extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// utf8 �о�� �ϴ� �������� utf8�� ��������
		response.setCharacterEncoding("utf-8");
		// ���������� html�����̶�°� �˷��� ���� �ٷ� �Ʒ�
		response.setContentType("text/html; chatset=utf-8");

		PrintWriter out = response.getWriter();
		// OutputStream os = response.getOutputStream();
		// PrintStream out = new PrintStream(os);

		String title = request.getParameter("title");
		System.out.println(title);
		List<Notice> list = null;
		String sql = "SELECT * FROM Notice where title like ?";
		// ������ ���������� �ֱⰡ ����� �ϴ� ?�� �ְ� �Ʒ����� ó����

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ����̹� �ε�
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ���� / ����
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ����
			// Statement st = con.createStatement();
			// PreparedStatement�� �̸� sql�� �ִ°��̹Ƿ� �Ʒ����� sql�� �������
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + title + "%");

			// ��� ��������
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery();

			// Model
			list = new ArrayList<>();

			// ��� ����ϱ�
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
		// --------------view---------------

		
		//�˻����ϰ� �ڷ� ���� ���� �� �˻��� �ϱ� ����
		//html�� �ڹٿ��� ǥ���� �ߴµ� �ѳ� �����Ѱ�
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("	<title>Insert title here</title>");
		out.println("</head>");
		out.println("	<body>");
		out.println("	<form action=\"notice\" method=\"get\">");
		out.println("	<label>�˻���</label>");
		out.println("	<input type=\"text\" name=\"title\">");
		out.println("	<input type=\"submit\">");
		out.println("</form>");			
	
		

		// �ֿܼ� ��� System.out.println(list.get(0).getTitle());
		for (Notice n : list)
			out.println(n.getTitle() + "<br/>");
		out.println("	</body>");
		out.println("	</html>");

	}
}
