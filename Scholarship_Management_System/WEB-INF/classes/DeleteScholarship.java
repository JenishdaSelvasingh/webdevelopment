import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class DeleteScholarship extends HttpServlet {
    public int deleteScholarship(String tid) throws Exception{
        String url = "jdbc:mysql://localhost:3306/rrs";
        String sqluser = "root";
        String sqlpwd = "Jenish18@";

        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Hey I am after forname method in deleteScholarship() - DeleteScholarship class");
        
        Connection con = DriverManager.getConnection(url, sqluser, sqlpwd);

        Statement st = con.createStatement();

        System.out.println("Hey I am after create method in deleteScholarship() - DeleteScholarship class");
        int ti=Integer.parseInt(tid);
        try {
            String s = String.format("select count(*) from scholarship where tid = '%d'", ti);
            ResultSet rs = st.executeQuery(s);
            rs.next();
            int count = rs.getInt(1);
            if(count == 0) {
                return 0;
            }

            s = String.format("delete from scholarship where tid = '%d'", ti);
            st.executeUpdate(s);
            System.out.println("Scholarship record deleted...");
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
        String id = req.getParameter("tid");
        System.out.println(id);

        try {
            int result = deleteScholarship(id);
            if(result == 0) {
                System.out.println("Scholarship Record does not exists...");
                res.sendRedirect("ahome.html");
            }
            else if(result == 1) {
                System.out.println("Scholarship Record deleted...");
                res.sendRedirect("deleteScholarship.html");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}