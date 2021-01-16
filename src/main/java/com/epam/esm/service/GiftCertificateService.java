package com.epam.esm.service;

import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.model.util.GetGiftCertificateQueryParameter;
import com.epam.esm.model.util.UpdateGiftCertificateQueryParameter;

import java.util.List;

public interface GiftCertificateService {

    void deleteCertificate(int id);

    GiftCertificateDTO getGiftCertificateByID(int id);

    GiftCertificateDTO createGiftCertificate(GiftCertificateDTO giftCertificateDTO);

    GiftCertificateDTO updateCertificate(UpdateGiftCertificateQueryParameter updateParameter, int id);

    List<GiftCertificateDTO> getCertificates();

    List<GiftCertificateDTO> getCertificates(GetGiftCertificateQueryParameter giftCertificateQueryParameter);

    List<GiftCertificateDTO> getCertificatesByTagName(String tagName);

    List<GiftCertificateDTO> getCertificatesByNameOrDescription(String searchText);

}
