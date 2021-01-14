package com.epam.esm.service;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateService {

    public int deleteCertificate(int id);

    public int updateCertificate(GiftCertificateDTO giftCertificateDTO, int id);

    public GiftCertificate createGiftCertificate(GiftCertificateDTO giftCertificateDTO) throws ServiceException;

    public List<GiftCertificate> getCertificates();

    public List<GiftCertificate> getCertificatesByTagName(String tagName);

    public Optional<GiftCertificate> getGiftCertificateByID(int id);

    public List<GiftCertificate> getCertificatesByNameOrDescription(String searchText);

}
