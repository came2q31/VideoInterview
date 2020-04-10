/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.entity;

import com.elcom.data.interview.entity.json.CustomJsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Transient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "letter")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Letter.findAll", query = "SELECT l FROM Letter l")
    , @NamedQuery(name = "Letter.findById", query = "SELECT l FROM Letter l WHERE l.id = :id")
    , @NamedQuery(name = "Letter.findByJobId", query = "SELECT l FROM Letter l WHERE l.jobId = :jobId")
    , @NamedQuery(name = "Letter.findByAppointmentTime", query = "SELECT l FROM Letter l WHERE l.appointmentTime = :appointmentTime")
    , @NamedQuery(name = "Letter.findByEmail", query = "SELECT l FROM Letter l WHERE l.email = :email")
    , @NamedQuery(name = "Letter.findByUserTo", query = "SELECT l FROM Letter l WHERE l.userTo = :userTo")
    , @NamedQuery(name = "Letter.findByProcedureId", query = "SELECT l FROM Letter l WHERE l.procedureId = :procedureId")
    , @NamedQuery(name = "Letter.findByStatus", query = "SELECT l FROM Letter l WHERE l.status = :status")
    , @NamedQuery(name = "Letter.findByUserView", query = "SELECT l FROM Letter l WHERE l.userView = :userView")
    , @NamedQuery(name = "Letter.findByInterviewView", query = "SELECT l FROM Letter l WHERE l.interviewView = :interviewView")
    , @NamedQuery(name = "Letter.findByCreatedAt", query = "SELECT l FROM Letter l WHERE l.createdAt = :createdAt")})
public class Letter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    @Column(name = "id")
    private Long id;

    @Basic(optional = true)
    @Column(name = "job_id")
    private Long jobId;

    @Basic(optional = true)
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "appointment_time")
    private Timestamp appointmentTime;

    @Basic(optional = true)
    @Column(name = "email")
    private String email;

    @Basic(optional = true)
    @Column(name = "link_cv")
    private String linkCv;

    @Basic(optional = true)
    @Column(name = "user_to")
    private Long userTo;

    @Basic(optional = true)
    @Column(name = "procedure_id")
    private Long procedureId;

    @Basic(optional = true)
    @Column(name = "status")
    private short status;

    @Basic(optional = true)
    @Column(name = "user_view")
    private short userView;

    @Basic(optional = true)
    @Column(name = "interview_view")
    private short interviewView;

    @Basic(optional = true)
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @Basic(optional = true)
    @Column(name = "company_id")
    private Long companyId;
    
    @Basic(optional = true)
    @Column(name = "company_name")
    private String companyName;
    
    @Basic(optional = true)
    @Column(name = "password")
    private String password;

    @Basic(optional = true)
    @Transient
    private Long appointmentTimeTs;
    
    @Basic(optional = true)
    @Transient
    private Integer whereStatus;

    public Letter() {
    }

    public Letter(Long id) {
        this.id = id;
    }

    public Letter(Long id, Long jobId, Timestamp appointmentTime, String email, String linkCv,
            Long userTo, Long procedureId, short status, short userView,
            short interviewView, Timestamp createdAt) {
        this.id = id;
        this.jobId = jobId;
        this.appointmentTime = appointmentTime;
        this.email = email;
        this.linkCv = linkCv;
        this.userTo = userTo;
        this.procedureId = procedureId;
        this.status = status;
        this.userView = userView;
        this.interviewView = interviewView;
        this.createdAt = createdAt;
    }

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

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public short getUserView() {
        return userView;
    }

    public void setUserView(short userView) {
        this.userView = userView;
    }

    public short getInterviewView() {
        return interviewView;
    }

    public void setInterviewView(short interviewView) {
        this.interviewView = interviewView;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAppointmentTimeTs() {
        return appointmentTimeTs;
    }

    public void setAppointmentTimeTs(Long appointmentTimeTs) {
        this.appointmentTimeTs = appointmentTimeTs;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getWhereStatus() {
        return whereStatus;
    }

    public void setWhereStatus(Integer whereStatus) {
        this.whereStatus = whereStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Letter)) {
            return false;
        }
        Letter other = (Letter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elcom.data.interview.entity.Letter[ id=" + id + " ]";
    }

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        System.out.println(df.format(new Date()));

        Date date = new Date(1585644300 * 1000L);
        Timestamp ts = new Timestamp(date.getTime());
        System.out.println(ts);
        System.out.println(System.currentTimeMillis());
    }
}
