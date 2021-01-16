package com.epam.esm.model.util;

public class GetGiftCertificateQueryParameter {

    private String name;
    private String description;
    private String tagName;
    private SortBy sortBy;
    private SortOrientation sortOrientation;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = SortBy.valueOf(sortBy.toUpperCase());
    }

    public SortOrientation getSortOrientation() {
        return sortOrientation;
    }

    public void setSortOrientation(SortOrientation sortOrientation) {
        this.sortOrientation = sortOrientation;
    }

    public void setSortOrientation(String sortOrientation) {
        this.sortOrientation = SortOrientation.valueOf(sortOrientation.toUpperCase());
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
}
