import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class SearchStudent extends HttpServlet {
    public String[][] getStudents(String name, String contact) throws Exception {
        String[][] trainlist = new String[10][6];

        String url = "jdbc:mysql://localhost:3306/rrs";
        String sqluser = "root";
        String sqlpwd = "Jenish18@";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Hey I am after forname method in getStudent() - SearchStudent class");
            
            Connection con = DriverManager.getConnection(url, sqluser, sqlpwd);

            Statement st = con.createStatement();

            System.out.println("Hey I am after create method in getStudents() - SearchStudent class");

            String s = String.format("select * from student where name = '%s' and contact = '%s'", name, contact);
            ResultSet rs = st.executeQuery(s);
            int i = 0;
            while(rs.next()) {
                trainlist[i][0] = rs.getString(1);
                trainlist[i][1] = rs.getString(2);
                trainlist[i][2] = rs.getString(3);
                trainlist[i][3] = rs.getString(4);
                trainlist[i][4] = rs.getString(5);
                trainlist[i][5] = rs.getString(6);
                System.out.println(trainlist[i][0] + ", " + trainlist[i][1] + ", " + trainlist[i][2] + ", " + trainlist[i][3] + ", " + trainlist[i][4] + ", " + trainlist[i][5]);
                i++;
            }
            return trainlist;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String contact = req.getParameter("contact");
        // String dot = req.getParameter("dot");
        System.out.println(name + ", " + contact);
        try {
            res.setContentType("text/html");
            PrintWriter pw = res.getWriter();

            // String[][] trlist = getTrains(source, destination, dot);
            String[][] trlist = getStudents(name, contact);
            for(int i = 0; i < trlist.length; i++) {
                for(int j = 0; j < 6; j++) {
                    System.out.println(trlist[i][j]);
                }
            }
            String trlistcontent = new String();
            // pw.println("testing");
            trlistcontent = "<table id='trainlist' border='2' cellspacing='3'  background-color='#04AA6D' color='white'' cellpadding='8' bordercolor='#9a00ff' align='center'><tr><th>Student Id</th><th>Email</th><th>Password</th><th>Student Name</th><th>Gender</th><th>City</th></tr>";
            for(int i = 0; i < trlist.length; i++) {
                if(trlist[i][0] != null) {
                    trlistcontent += "<tr>";
                    trlistcontent += "<td id='tnoin'>" + trlist[i][0] + "</td>";
                    trlistcontent += "<td id='tnamein'>"+ trlist[i][1] +"</td>";
                    trlistcontent += "<td id='srcin'>"+ trlist[i][2] +"</td>";
                    trlistcontent += "<td id='desin'>"+ trlist[i][3] +"</td>";             
                    trlistcontent += "<td id='seatsin'>"+ trlist[i][4] +"</td>";
                    trlistcontent += "<td id='durin'>"+ trlist[i][5] +"</td>";
                    //trlistcontent += "<td><button class='open-button' onclick='booknow(document.getElementById(\"tnamein\").innerHTML, document.getElementById(\"tnoin\").innerHTML, document.getElementById(\"pricein\").innerHTML, document.getElementById(\"srcin\").innerHTML, document.getElementById(\"desin\").innerHTML, document.getElementById(\"seatsin\").innerHTML, document.getElementById(\"durin\").innerHTML);'>Apply Scholarship</button></td>";
                    trlistcontent += "</tr>";
                }
            }
            trlistcontent += "</table>";
            System.out.println(trlistcontent);
            pw.println(trlistcontent);
            pw.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}