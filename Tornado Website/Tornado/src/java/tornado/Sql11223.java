
package tornado;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PRADEEP
 */
public class Sql11223 {
    Connection con;
    Statement st;
   public Statement connect12() throws ClassNotFoundException, SQLException{
       Class.forName("jdbc.oracle.driver.OracleDriver");
       con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","tornado","tornado");
       st=con.createStatement();
       return st;
   }
   
}
