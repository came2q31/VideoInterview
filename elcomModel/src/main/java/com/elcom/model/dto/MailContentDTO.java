/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.model.dto;

import com.google.gson.Gson;

/**
 *
 * @author Admin
 */
public class MailContentDTO {
    private String fromName;
    private String emailFrom;
    private String emailTo;
    private String emailTitle;
    private String emailContent;

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }
    public static void main(String[] args) {
        MailContentDTO dto = new MailContentDTO();
        dto.setFromName("Ha Nguyen Minh");
        dto.setEmailTo("ha211188@gmail.com");
        dto.setEmailTitle("Tieu de test");
        dto.setEmailFrom("vietanhanhviet@gmail.com");
        dto.setEmailContent("Day la noi dung email test tu service");
        
        Gson gson = new Gson();
        System.out.println(gson.toJson(dto));
    }
}
