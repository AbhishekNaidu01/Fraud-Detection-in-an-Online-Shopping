/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import databaseconnection.databasecon;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author HARISH
 */
@MultipartConfig(maxFileSize = 16177215)
public class insert extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        

FileInputStream fis=null;

String s="Registered";
String tr="Trusted";


String a = request.getParameter("comname");

String b = request.getParameter("proname");
String c = request.getParameter("wardate");
String d = request.getParameter("proimage");
String e = request.getParameter("prorate");
InputStream inputStream = null;
        Part filePart = request.getPart("proimage");
         if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
Connection con=null;
PreparedStatement psmt1=null;

        
System.out.println(d);

System.out.println();
try {
con=databasecon.getconnection();
psmt1=con.prepareStatement("insert into products(comname,proname,wardate,proimage,prorate,status,adminact) values(?,?,?,?,?,?,?)");
psmt1.setString(1,a);
psmt1.setString(2,b);
psmt1.setString(3,c);
psmt1.setBinaryStream(4, inputStream);
psmt1.setString(5,e);
psmt1.setString(6,s);
psmt1.setString(7,tr);

int no=psmt1.executeUpdate();
if(no>0){
response.sendRedirect("seller_home.jsp?message=success");

}else{
    response.sendRedirect("seller_home.jsp?message=failed");

} 
}catch(Exception e2){
     System.out.println("Product adding Error "+e2.getMessage());

}             
                 
             
            
         }
 
         }
    }