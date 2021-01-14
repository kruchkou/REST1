package com.epam.esm.dto;

import java.util.Date;
import java.util.List;

public class GiftCertificateDTO {

    private String name;
    private String description;
    private int price;
    private int duration;
    private Date createDate;
    private Date lastsUpdateDate;
    private List<String> tagNames;

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

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    @Override
    public String toString() {
        return "GiftCertificateDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", createDate=" + createDate +
                ", lastsUpdateDate=" + lastsUpdateDate +
                ", tagNames=" + tagNames +
                '}';
    }
}
