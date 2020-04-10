/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class InterviewLetterDTO {
    private Long id;
    private Long jobId;
    private Timestamp appointmentTime;
    private String email;
    private String linkCv;
    private Long userTo;
    private Long procedureId;
    private int status;
    private int userView;
    private int interviewView;
    private Timestamp createdAt;
    private String password;
    //Job
    private String jobName;
    private String jobGroup;
    //user ung tuyen
    private Long applyId;
    private String applyName;
    private String applyEmail;
    private String applyMobile;
    //Procedure
    private String procedureName;
    private int procedureType;
    //user phong van
    private Long interviewId;
    private String interviewName;
    private String interviewEmail;
    //Trang thai lam bai test
    @JsonIgnore
    private int doTest = -1;
    //Comapnay
    private Long companyId;
    private String companyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkCv() {
        return linkCv;
    }

    public void setLinkCv(String linkCv) {
        this.linkCv = linkCv;
    }

    public Long getUserTo() {
        return userTo;
    }

    public void setUserTo(Long userTo) {
        this.userTo = userTo;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId = procedureId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserView() {
        return userView;
    }

    public void setUserView(int userView) {
        this.userView = userView;
    }

    public int getInterviewView() {
        return interviewView;
    }

    public void setInterviewView(int interviewView) {
        this.interviewView = interviewView;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyEmail() {
        return applyEmail;
    }

    public void setApplyEmail(String applyEmail) {
        this.applyEmail = applyEmail;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public int getProcedureType() {
        return procedureType;
    }

    public void setProcedureType(int procedureType) {
        this.procedureType = procedureType;
    }

    public Long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public String getInterviewEmail() {
        return interviewEmail;
    }

    public void setInterviewEmail(String interviewEmail) {
        this.interviewEmail = interviewEmail;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public int getDoTest() {
        return doTest;
    }

    public void setDoTest(int doTest) {
        this.doTest = doTest;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApplyMobile() {
        return applyMobile;
    }

    public void setApplyMobile(String applyMobile) {
        this.applyMobile = applyMobile;
    }
    
}
