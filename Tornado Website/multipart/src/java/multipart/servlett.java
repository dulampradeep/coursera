/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multipart;

import java.io.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

/**
 *
 * @author PRADEEP
 */
@MultipartConfig
public class servlett extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final Part filepart=request.getPart("file");
        final PrintWriter pw=response.getWriter();
        final String filename=getFileName(filepart);
        pw.println("New file "); 
        String path="E:\\";
        OutputStream out=null;
        InputStream in=null;
       
        pw.println("New file "); 
        try{
             pw.println(path + File.separator + filename); 
             try{
            out = new FileOutputStream(new File(path + File.separator+ filename));
             }
             catch(FileNotFoundException x){
                 System.out.println("jsa");
             }
              pw.println("New file 1"); 
                in=filepart.getInputStream();
              pw.println("New file 2"); 
            final byte bytes[]=new byte[1024];
              pw.println("New file 3"); 
            int read=0;
            while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
        }
             pw.println("New file 5");           
            pw.println("New file " + filename + " created at " + path);        
        }catch(IOException e){
            System.out.println(e);
        }
        
        
       finally {
        if (out != null) {
            out.close();
        }
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
    }
        }
    
private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}
}
