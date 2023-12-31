import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.lang.reflect.Executable;
import java.util.*;

public class GetRecord extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/rrs";
        String sqluser = "root";
        String sqlpwd = "Jenish18@";

        int ticno = Integer.parseInt(req.getParameter("ticno"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Hey I am after forname method in get() - GetRecord class");
            
            Connection con = DriverManager.getConnection(url, sqluser, sqlpwd);

            Statement st = con.createStatement();

            System.out.println("Hey I am after create method in get() - GetRecord class");

            res.setContentType("text/html");
            PrintWriter pw = res.getWriter();

            String s = String.format("select * from status where ticno='%d'", ticno);
            ResultSet rs = st.executeQuery(s);
            rs.next();
            int pid = rs.getInt(2);
            int tid = rs.getInt(3);
            String dot = rs.getString(4);
            int asno = rs.getInt(5);
            int csno = rs.getInt(6);
            String status = rs.getString(7);

            s = String.format("select name from student where pid='%d'", pid);
            ResultSet rs1 = st.executeQuery(s);
            rs1.next();
            String pname = rs1.getString(1);
            // System.out.println("pname: "+ pname);
            
            s = String.format("select name,source,destination from scholarship where tid='%d'", tid);
            ResultSet rs2 = st.executeQuery(s);
            rs2.next();
            System.out.println("executed sql");


            String record = new String();
            record = "<table border='2' cellspacing='3' cellpadding='30' background-color='#04AA6D' color='white' bordercolor='#9a00ff' style='margin-left:420px;margin-top: 30px;'><tr><th>Student Number</th><th>Student Name</th><th>Scholarship Id</th><th>Eligibility</th><th>Scholarship Name</th><th>Community</th><th>Role</th><th>Aadhar Number</th><th>Update Now</th></tr>";
            record += "<tr>";
            record += "<td>" + Integer.toString(ticno) + "</td>"; //ticket no
            record += "<td>" + pname + "</td>"; // passenger name
            record += "<td>" + Integer.toString(tid) + "</td>"; // train id
            record += "<td>"+ rs2.getString(1) +"</td>"; // train name
            record += "<td>"+ rs2.getString(2) +"</td>"; // src
            record += "<td>"+ rs2.getString(3) +"</td>"; // dest
            record += "<td>"+ dot +"</td>"; // date of travel
            record += "<td>"+ Integer.toString(asno + csno) +"</td>"; // no of seats
            record += "<td><button class='open-button' onclick='cancel()'>Update Request</button></td>";
            record += "</tr>";
            System.out.println(record);
            pw.println(record);
            pw.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}