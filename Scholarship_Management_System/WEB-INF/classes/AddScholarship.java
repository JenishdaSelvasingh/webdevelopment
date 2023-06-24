import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class AddScholarship extends HttpServlet {
    public int addScholarship(String tid, String name, String source, String destination, String max_seats, String duration) throws Exception{
        String url = "jdbc:mysql://localhost:3306/rrs";
        String sqluser = "root";
        String sqlpwd = "Jenish18@";

        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Hey I am after forname method in addScholarship() - AddScholarship class");
        
        Connection con = DriverManager.getConnection(url, sqluser, sqlpwd);

        Statement st = con.createStatement();

        System.out.println("Hey I am after create method in addScholarship() - AddScholarship class");
        int ms=Integer.parseInt(max_seats);
        int ti=Integer.parseInt(tid);
        int du=Integer.parseInt(duration);
        try {
            String s = String.format("select count(*) from scholarship where tid = '%d'", ti);
            ResultSet rs = st.executeQuery(s);
            rs.next();
            int count = rs.getInt(1);
            if(count > 0) {
                return 0;
            }

            s = String.format("insert into scholarship(tid, name, source, destination, max_seats, duration) values('%d','%s','%s','%s','%d','%d')", ti, name, source, destination, ms, du);
            st.executeUpdate(s);
            System.out.println("Scholarship record inserted...");
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
        String name = req.getParameter("tname");
        String source = req.getParameter("source");
        String destination = req.getParameter("destination");
        String max_seats = req.getParameter("maxseats");
        String duration = req.getParameter("duration");
        System.out.println(id + ", " + name + ", " + source + ", " + destination + ", " + max_seats + ", " + duration);

        try {
            int result = addScholarship(id, name, source, destination, max_seats, duration);
            if(result == 0) {
                System.out.println("Scholarship already exists...");
                res.sendRedirect("addScholarship.html");
            }
            else if(result == 1) {
                System.out.println("New Scholarship added");
                res.sendRedirect("ahome.html");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}