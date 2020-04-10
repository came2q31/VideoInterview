/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.data.interview.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "result_criteria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultCriteria.findAll", query = "SELECT r FROM ResultCriteria r")
    , @NamedQuery(name = "ResultCriteria.findById", query = "SELECT r FROM ResultCriteria r WHERE r.id = :id")
    , @NamedQuery(name = "ResultCriteria.findByJobId", query = "SELECT r FROM ResultCriteria r WHERE r.jobId = :jobId")
    , @NamedQuery(name = "ResultCriteria.findByUserId", query = "SELECT r FROM ResultCriteria r WHERE r.userId = :userId")
    , @NamedQuery(name = "ResultCriteria.findByChuyenmon", query = "SELECT r FROM ResultCriteria r WHERE r.chuyenmon = :chuyenmon")
    , @NamedQuery(name = "ResultCriteria.findByThaidocongviec", query = "SELECT r FROM ResultCriteria r WHERE r.thaidocongviec = :thaidocongviec")
    , @NamedQuery(name = "ResultCriteria.findByNgoaingu", query = "SELECT r FROM ResultCriteria r WHERE r.ngoaingu = :ngoaingu")
    , @NamedQuery(name = "ResultCriteria.findByGiaotiep", query = "SELECT r FROM ResultCriteria r WHERE r.giaotiep = :giaotiep")
    , @NamedQuery(name = "ResultCriteria.findByCreatedAt", query = "SELECT r FROM ResultCriteria r WHERE r.createdAt = :createdAt")})
public class ResultCriteria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = true)
    @Column(name = "company_id")
    private Long companyId;
    
    @Basic(optional = true)
    @Column(name = "job_id")
    private Long jobId;
    
    @Basic(optional = true)
    @Column(name = "user_id")
    private Long userId;
    
    @Basic(optional = true)
    @Column(name = "chuyenmon")
    private String chuyenmon;
    
    @Basic(optional = true)
    @Column(name = "thaidocongviec")
    private String thaidocongviec;
    
    @Basic(optional = true)
    @Column(name = "ngoaingu")
    private String ngoaingu;
    
    @Basic(optional = true)
    @Column(name = "giaotiep")
    private String giaotiep;
    
    @Basic(optional = true)
    @Lob
    @Column(name = "nhanxet")
    private String nhanxet;
    
    @Basic(optional = true)
    @Column(name = "created_at")
    private Timestamp createdAt;

    public ResultCriteria() {
    }

    public ResultCriteria(Long id) {
        this.id = id;
    }

    public ResultCriteria(Long id, Long companyId, Long jobId, Long userId, String chuyenmon, 
            String thaidocongviec, String ngoaingu, String giaotiep, String nhanxet, Timestamp createdAt) {
        this.id = id;
        this.companyId = companyId;
        this.jobId = jobId;
        this.userId = userId;
        this.chuyenmon = chuyenmon;
        this.thaidocongviec = thaidocongviec;
        this.ngoaingu = ngoaingu;
        this.giaotiep = giaotiep;
        this.nhanxet = nhanxet;
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

    public String getChuyenmon() {
        return chuyenmon;
    }

    public void setChuyenmon(String chuyenmon) {
        this.chuyenmon = chuyenmon;
    }

    public String getThaidocongviec() {
        return thaidocongviec;
    }

    public void setThaidocongviec(String thaidocongviec) {
        this.thaidocongviec = thaidocongviec;
    }

    public String getNgoaingu() {
        return ngoaingu;
    }

    public void setNgoaingu(String ngoaingu) {
        this.ngoaingu = ngoaingu;
    }

    public String getGiaotiep() {
        return giaotiep;
    }

    public void setGiaotiep(String giaotiep) {
        this.giaotiep = giaotiep;
    }

    public String getNhanxet() {
        return nhanxet;
    }

    public void setNhanxet(String nhanxet) {
        this.nhanxet = nhanxet;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
        if (!(object instanceof ResultCriteria)) {
            return false;
        }
        ResultCriteria other = (ResultCriteria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.elcom.data.interview.entity.ResultCriteria[ id=" + id + " ]";
    }
    
}
