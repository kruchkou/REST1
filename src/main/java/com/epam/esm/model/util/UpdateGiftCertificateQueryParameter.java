package com.epam.esm.model.util;

import java.time.Instant;
import java.util.Objects;

public class UpdateGiftCertificateQueryParameter {

    private String name;
    private String description;
    private Integer price;
    private Integer duration;
    private Instant instant;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateGiftCertificateQueryParameter that = (UpdateGiftCertificateQueryParameter) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(instant, that.instant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, duration, instant);
    }

    @Override
    public String toString() {
        return "UpdateGiftCertificateQueryParameter{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", instant=" + instant +
                '}';
    }
}
