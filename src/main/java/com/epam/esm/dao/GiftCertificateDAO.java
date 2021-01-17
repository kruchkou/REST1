package com.epam.esm.dao;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.util.GiftCertificateSQL;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDAO {

    public void deleteGiftCertificate(int id);

    public GiftCertificate updateGiftCertificate(GiftCertificateSQL giftCertificateSQL, int id);

    public GiftCertificate createGiftCertificate(GiftCertificate giftCertificate);

    public Optional<GiftCertificate> getGiftCertificateByID(int id);

    public List<GiftCertificate> getGiftCertificates();

    public List<GiftCertificate> getGiftCertificates(GiftCertificateSQL giftCertificateSQL);

    public List<GiftCertificate> getGiftCertificatesByTagName(String tagName);

    public List<GiftCertificate> getGiftCertificatesByNameOrDescription(String searchText);

}
