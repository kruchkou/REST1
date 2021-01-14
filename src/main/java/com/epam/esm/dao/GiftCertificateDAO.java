package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDAO {

    public int deleteCertificate(int id);

    public int updateCertificate(GiftCertificate updatedCertificate);

    public GiftCertificate createGiftCertificate(String name, String description, int price, int duration);

    public Optional<GiftCertificate> getCertificateByID(int id);

    public List<GiftCertificate> getCertificates();

    public List<GiftCertificate> getCertificatesByTagName(String tagName);

    public List<GiftCertificate> getCertificatesByNameOrDescription(String searchText);

}
