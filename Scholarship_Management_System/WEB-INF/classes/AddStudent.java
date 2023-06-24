import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class AddStudent extends HttpServlet {
    public int addStudent(String email,String pwd ,String name, String gender, String city, String contact) throws Exception{
        String url = "jdbc:mysql://localhost:3306/rrs";
        String sqluser = "root";
        String sqlpwd = "Jenish18@";

        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Hey I am after forname method in addStudent() - AddStudent class");
        
        Connection con = DriverManager.getConnection(url, sqluser, sqlpwd);

        Statement st = con.createStatement();

        System.out.println("Hey I am after create method in addStudent() - AddStudent class");
        //int pi=Integer.parseInt(pid);
        try {
            String s = String.format("select count(*) from student where email = '%s'", email);
            ResultSet rs = st.executeQuery(s);
            rs.next();
            int count = rs.getInt(1);
            if(count > 0) {
                return 0;
            }

            s = String.format("insert into student(email, pwd, name, gender, city, contact) values('%s','%s','%s','%s','%s','%s')", email, pwd, name, gender, city, contact);
            st.executeUpdate(s);
            System.out.println("Student record inserted...");
        }
        catch(Exception e) {
            System.out.println(e);
            return -1;
        }
        return 1;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String city = req.getParameter("city");
        String contact = req.getParameter("contact");
        System.out.println( email + ", " + pwd + ", " + name + ", " + gender + ", " + ","+city+","+contact);

        try {
            int result = addStudent(email, pwd, name, gender, city,contact);
            if(result == 0) {
                System.out.println("Student already exists...");
                res.sendRedirect("addStudent.html");
            }
            else if(result == 1) {
                System.out.println("New Student added");
                res.sendRedirect("ohome.html");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}