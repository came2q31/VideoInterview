package com.elcom.data.interview.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the PROCEDURES database table.
 *
 */
@Entity
@Table(name = "procedures")
public class Procedures implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_INTERVIEW")
    private Long userInterview;

    @Column(name = "JOB_ID")
    private Long jobId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private Integer type;
    
    @Column(name = "stt")
    private Integer stt;
    
    @Column(name = "total")
    private Integer total;

    @Column(name = "CREATED_AT", updatable = false)
    private Timestamp createdAt;
    
    @Column(name = "total_point")
    private Integer totalPoint;
    
    @Column(name = "total_question")
    private Integer totalQuestion;
    
    @Column(name = "time_test")
    private Long timeTest;

    @Transient
    private String email;

    @Transient
    private String fullName;

    public Procedures() {
    }

    public Long getUserInterview() {
        return userInterview;
    }

    public void setUserInterview(Long userInterview) {
        this.userInterview = userInterview;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }

    public Integer getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(Integer totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public Long getTimeTest() {
        return timeTest;
    }

    public void setTimeTest(Long timeTest) {
        this.timeTest = timeTest;
    }

}
