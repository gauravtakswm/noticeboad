package com.noticeboardapp.model_classes;


import java.io.Serializable;

public class NoticeDataBean implements Serializable {

    private int id;
    public NoticeDataBean()
    {

    }

    public NoticeDataBean(int id, String title, String issuedBy, String description, String picUrl, String contactPerson, String contactEmail, String noticeEventDate, String webUrl, String category) {
        this.id = id;
        this.title = title;
        this.issuedBy = issuedBy;
        this.description = description;
        this.picUrl = picUrl;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.noticeEventDate = noticeEventDate;
        this.webUrl = webUrl;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String title;
    private String issuedBy;
    private String description;
    private String picUrl;
    private String contactPerson;
    private String contactEmail;
    private String noticeEventDate;
    private String webUrl;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getNoticeEventDate() {
        return noticeEventDate;
    }

    public void setNoticeEventDate(String noticeEventDate) {
        this.noticeEventDate = noticeEventDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }





    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
