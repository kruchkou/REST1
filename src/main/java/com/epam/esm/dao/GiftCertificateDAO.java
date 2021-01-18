package com.epam.esm.dao;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.util.GiftCertificateSQL;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDAO {

    void deleteGiftCertificate(int id);

    void deleteLinkWithTagsByID(int id);

    GiftCertificate updateGiftCertificate(GiftCertificateSQL giftCertificateSQL, int id);

    GiftCertificate createGiftCertificate(GiftCertificate giftCertificate);

    Optional<GiftCertificate> getGiftCertificateByID(int id);

    List<GiftCertificate> getGiftCertificates();

    List<GiftCertificate> getGiftCertificates(GiftCertificateSQL giftCertificateSQL);

    List<GiftCertificate> getGiftCertificatesByTagName(String tagName);

    List<GiftCertificate> getGiftCertificatesByNameOrDescription(String searchText);

}
