import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class BookScholarship extends HttpServlet {
    public int addScholarship(String pname, String trname, int trno, String src, String dest, String dot, int asno, int csno) {
        String url = "jdbc:mysql://localhost:3306/rrs";
        String sqluser = "root";
        String sqlpwd = "Jenish18@";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Hey I am after forname method in addScholarship() - BookScholarship class");
            
            Connection con = DriverManager.getConnection(url, sqluser, sqlpwd);

            Statement st = con.createStatement();

            System.out.println("Hey I am after create method in addScholarship() - BookScholarship class");

            String s = String.format("select pid from student where name='%s'", pname);
            ResultSet rs = st.executeQuery(s);
            if(rs.next()) {
                int pid = rs.getInt(1);
                s = String.format("insert into status(pid, tid, dot, ano, cno, ticstatus) values('%d', '%d', '%s', '%d', '%d', 'Confirmed')", pid, trno, dot, asno, csno);
                st.executeUpdate(s);
                System.out.println("Scholarship details inserted...");
            }
            else {
                System.out.println("Student doesn't exist");
                return 0;
            }
            return 1;
        }
        catch(Exception e) {
            System.out.println(e);
            return -1;
        }
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pname = req.getParameter("spname");
        String trname = req.getParameter("stname");
        int trno = Integer.parseInt(req.getParameter("stno"));
        String src = req.getParameter("sfrom");
        String dest = req.getParameter("sto");
        String dot = req.getParameter("sdot");
        int asno = Integer.parseInt(req.getParameter("sasno"));
        int csno = Integer.parseInt(req.getParameter("scsno"));
        System.out.println("Scholarship to be added...");
        System.out.println(pname + ", " + trname + ", " + trno + ", " + src + ", " + dest + ", " + dot + ", " + asno + ", " + csno);
        try {
            int result = addScholarship(pname, trname, trno, src, dest, dot, asno, csno);
            if(result == 1) {
                System.out.println("Scholarship Booked...");
                res.sendRedirect("application.html");
            }
            else {
                System.out.println("Scholarship not booked...");
                res.sendRedirect("bookScholarship.html");
            }
            // res.sendRedirect("payment.html");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}