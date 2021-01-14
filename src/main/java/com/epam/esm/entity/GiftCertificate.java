package com.epam.esm.entity;

import java.util.Date;
import java.util.List;

public class GiftCertificate {

    private int id;
    private String name;
    private String description;
    private int price;
    private int duration;
    private Date createDate;
    private Date lastsUpdateDate;

    private List<Tag> tagList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastsUpdateDate() {
        return lastsUpdateDate;
    }

    public void setLastsUpdateDate(Date lastsUpdateDate) {
        this.lastsUpdateDate = lastsUpdateDate;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "GiftCertificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", createDate=" + createDate +
                ", lastsUpdateDate=" + lastsUpdateDate +
                ", tagList=" + tagList +
                '}';
    }

}
