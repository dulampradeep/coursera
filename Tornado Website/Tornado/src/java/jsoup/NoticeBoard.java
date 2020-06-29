/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoup;
import java.io.IOException;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author PRADEEP
 */
public class NoticeBoard {
    public static String notice(){
        
      Document d=null;
        try {
            d = Jsoup.connect("http://www.swarnandhra.ac.in/notice-board").get();
        } catch (IOException ex) {
            Logger.getLogger(NoticeBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        String str="<tr><th>Notices</th><th>content</th></tr><tr>";
      Elements e= d.select("td");
      int i=0;
      for(Element x:e){
          i++;
          if(i%2==0){
              str+="<td>"+x.text()+"</td></tr><tr>";
              continue;
          }
          str+="<td>"+x.text()+"</td>";
      }
      str+="</tr>";
      System.out.print(str);
      return str;
      }
    public static void main(String args[]){
        NoticeBoard.notice();
    }
}

