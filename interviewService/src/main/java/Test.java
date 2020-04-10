
import java.sql.Timestamp;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Test {
    public static void main(String[] args) {
        long str = 1578134100 + 123929392;
        Date ts = new Date(str * 1000L);
        System.out.println("ts: " + ts + "," + ts.getTime() + ", " + new Date());
        System.out.println("Long: " + ts.getTime() + ", now: " + new Date().getTime());
        String link = "http://103.21.151.185:8080/interviewService/upload/09042020/images/anhtest3.gif";
        System.out.println(link.substring(link.indexOf("/upload/")));
    }
}
