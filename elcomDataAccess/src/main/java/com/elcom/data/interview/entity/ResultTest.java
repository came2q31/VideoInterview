/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "result_test")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultTest.findAll", query = "SELECT r FROM ResultTest r")
    , @NamedQuery(name = "ResultTest.findById", query = "SELECT r FROM ResultTest r WHERE r.id = :id")
    , @NamedQuery(name = "ResultTest.findByJobId", query = "SELECT r FROM ResultTest r WHERE r.jobId = :jobId")
    , @NamedQuery(name = "ResultTest.findByUserId", query = "SELECT r FROM ResultTest r WHERE r.userId = :userId")
    , @NamedQuery(name = "ResultTest.findByQuestionId", query = "SELECT r FROM ResultTest r WHERE r.questionId = :questionId")
    , @NamedQuery(name = "ResultTest.findByAnswerId", query = "SELECT r FROM ResultTest r WHERE r.answerId = :answerId")
    , @NamedQuery(name = "ResultTest.findByCreatedAt", query = "SELECT r FROM ResultTest r WHERE r.createdAt = :createdAt")})
public class ResultTest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "job_id")
    private Long jobId;
    
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    
    @Basic(optional = false)
    @Column(name = "question_id")
    private Long questionId;
    
    @Basic(optional = false)
    @Column(name = "answer_id")
    private Long answerId;
    
    @Basic(optional = false)
    @Lob
    @Column(name = "answer_content")
    private String answerContent;
    
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public ResultTest() {
    }

    public ResultTest(Long id) {
        this.id = id;
    }

    public ResultTest(Long id, Long jobId, Long userId, Long questionId, Long answerId, String answerContent, Date createdAt) {
        this.id = id;
        this.jobId = jobId;
        this.userId = userId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.answerContent = answerContent;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        if (!(object instanceof ResultTest)) {
            return false;
        }
        ResultTest other = (ResultTest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elcom.data.interview.entity.ResultTest[ id=" + id + " ]";
    }
    
}
