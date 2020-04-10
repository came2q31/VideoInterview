package com.elcom.sharedbiz.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * Send mail
 * @author anhdv
 */
public class SendMail {
    
	private static final Logger logger = Logger.getLogger(SendMail.class.getName());
	
    public boolean sendMail(String host, String port, final String fromMail, final String passFromMail
    		, String subject, String content, List<ToMail> listToMail) {
    	
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.socketFactory.port", port);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", port);
            props.put("mail.mime.charset", "utf-8");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            //Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromMail,passFromMail);
                }
            });
        
            InternetAddress[] listAddress=new InternetAddress[listToMail.size()];
            InternetAddress address =null;
            
            for (int i = 0; i < listToMail.size(); i++) {
                ToMail toMail=listToMail.get(i);
                address=new InternetAddress();
                address.setAddress(toMail.getAddress());
                address.setPersonal(toMail.getName());
                listAddress[i]=address;
            }
            //Message message = new MimeMessage(session);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromMail));
            message.setRecipients(Message.RecipientType.TO, listAddress); //InternetAddress.parse(toMail)
            message.setSubject(subject,"UTF-8");
            message.setContent(content, "text/html; charset=UTF-8");
            //message.setText(content);

            Transport.send(message);

            System.out.println("Gửi mail thành công!");
            
            return true;
            
        }catch (Exception ex) {
        	
            logger.error("SendMail.sendMail.ex: " + ex.toString());
        }
        
        return false;
    }
}
