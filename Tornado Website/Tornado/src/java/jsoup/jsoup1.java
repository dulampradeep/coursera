/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoup;
import static java.lang.System.out;
import org.jsoup.*;
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author PRADEEP
 */
public class jsoup1  {
    void conn1(){
        try{
        Document doc = Jsoup.connect("https://www.amazon.in/dp/B07PP2JD3V/ref=sspa_dk_detail_0?psc=1&pd_rd_i=B07PP2JD3V&pd_rd_w=1aPzr&pf_rd_p=357151f8-058c-46f1-b7d1-fa3647ce3999&pd_rd_wg=xP8Wb&pf_rd_r=29QNBT630BD6H9NRCVDR&pd_rd_r=3253195a-51ab-4af7-9307-c424dc2404a1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzSEZJTFJLWUJDREs0JmVuY3J5cHRlZElkPUEwNTQ4NDQzMkRURUlOWEE0WlBZUiZlbmNyeXB0ZWRBZElkPUEwNzU3ODk4MVlYWldMUERSSUpIVSZ3aWRnZXROYW1lPXNwX2RldGFpbCZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=").get();  
        Elements links = doc.select("span[id]");   
        int i=0;int v=0;
        for(Element li : links){
            if(li.attr("id").equals("priceblock_ourprice")){
                String x=li.text();
                x=x.substring(2,x.indexOf(".")).replace(",", "");
                v=Integer.valueOf(x);
            out.println("Element "+ ++i +" "+v);
            }
        }
       int k=0;
        while(true){
            if(v<34999){
                out.println("leesss");
               break;
            }
            out.println("hai");
            Thread.sleep(3000);
            k++;
            if(k==11){
                v=34998;
            }
        }}
        catch(Exception e){
            e.printStackTrace();
        }
}
    public static void main(String args[]){
        jsoup1 x=new jsoup1();
        x.conn1();
    }
}
