package com.elcom.model.dto;

import java.io.Serializable;
import java.util.Date;

public class CompanyUpsertDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5113310993905485424L;

    private Long id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String officePhone;
    private String hotline;
    private String website;
    private Integer status;
    private Integer _package;
    private Date packageStart;
    private Date packageEnd;
    private Long careerId;

    public CompanyUpsertDTO() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer get_package() {
        return _package;
    }

    public void set_package(Integer _package) {
        this._package = _package;
    }

    public Date getPackageStart() {
        return packageStart;
    }

    public void setPackageStart(Date packageStart) {
        this.packageStart = packageStart;
    }

    public Date getPackageEnd() {
        return packageEnd;
    }

    public void setPackageEnd(Date packageEnd) {
        this.packageEnd = packageEnd;
    }

    public Long getCareerId() {
        return careerId;
    }

    public void setCareerId(Long careerId) {
        this.careerId = careerId;
    }
}
