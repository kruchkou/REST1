package com.epam.esm.dao;

import com.epam.esm.model.entity.GiftCertificate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface GiftCertificateDAO {

    public void deleteGiftCertificate(int id);

    public GiftCertificate updateGiftCertificate(GiftCertificate updatedCertificate, int id);

    public GiftCertificate createGiftCertificate(String name, String description, int price, int duration);

    public Optional<GiftCertificate> getGiftCertificateByID(int id);

    public List<GiftCertificate> getGiftCertificates();

    public List<GiftCertificate> getGiftCertificatesByTagName(String tagName);

    public List<GiftCertificate> getGiftCertificatesByNameOrDescription(String searchText);

}
