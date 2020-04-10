/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a")
    , @NamedQuery(name = "Answer.findById", query = "SELECT a FROM Answer a WHERE a.id = :id")
    , @NamedQuery(name = "Answer.findByQuestionId", query = "SELECT a FROM Answer a WHERE a.questionId = :questionId")
    , @NamedQuery(name = "Answer.findByAnswer", query = "SELECT a FROM Answer a WHERE a.answer = :answer")
    , @NamedQuery(name = "Answer.findByIsTrue", query = "SELECT a FROM Answer a WHERE a.isTrue = :isTrue")
    , @NamedQuery(name = "Answer.findByLinkVideo", query = "SELECT a FROM Answer a WHERE a.linkVideo = :linkVideo")
    , @NamedQuery(name = "Answer.findByCreatedAt", query = "SELECT a FROM Answer a WHERE a.createdAt = :createdAt")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "question_id")
    private Long questionId;
    
    @Basic(optional = false)
    @Column(name = "answer")
    private String answer;
    
    @Basic(optional = false)
    @Column(name = "is_true")
    private Integer isTrue;
    
    @Basic(optional = true)
    @Column(name = "link_video")
    private String linkVideo;
    
    @Basic(optional = true)
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Answer() {
    }

    public Answer(Long id) {
        this.id = id;
    }

    public Answer(Long id, Long questionId, String answer, Integer isTrue, Timestamp createdAt) {
        this.id = id;
        this.questionId = questionId;
        this.answer = answer;
        this.isTrue = isTrue;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
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
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elcom.data.interview.entity.Answer[ id=" + id + " ]";
    }

}
