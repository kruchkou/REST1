package com.epam.esm.service.util.mapper;

import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.model.entity.GiftCertificate;

import java.util.ArrayList;
import java.util.List;

public final class EntityDTOGiftCertificateMapper {

    private EntityDTOGiftCertificateMapper() {
    }

    public static GiftCertificate toEntity(GiftCertificateDTO giftCertificateDTO) {
        final GiftCertificate giftCertificate = new GiftCertificate();

        giftCertificate.setId(giftCertificateDTO.getId());
        giftCertificate.setName(giftCertificateDTO.getName());
        giftCertificate.setDescription(giftCertificateDTO.getDescription());
        giftCertificate.setPrice(giftCertificateDTO.getPrice());
        giftCertificate.setDuration(giftCertificateDTO.getDuration());
        giftCertificate.setCreateDate(giftCertificateDTO.getCreateDate());
        giftCertificate.setLastsUpdateDate(giftCertificateDTO.getLastsUpdateDate());

        return giftCertificate;
    }

    public static GiftCertificateDTO toDTO(GiftCertificate giftCertificate) {
        final GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();

        giftCertificateDTO.setId(giftCertificate.getId());
        giftCertificateDTO.setName(giftCertificate.getName());
        giftCertificateDTO.setDescription(giftCertificate.getDescription());
        giftCertificateDTO.setPrice(giftCertificate.getPrice());
        giftCertificateDTO.setDuration(giftCertificate.getDuration());
        giftCertificateDTO.setCreateDate(giftCertificate.getCreateDate());
        giftCertificateDTO.setLastsUpdateDate(giftCertificate.getLastsUpdateDate());

        return giftCertificateDTO;
    }

    public static List<GiftCertificateDTO> toDTO(List<GiftCertificate> giftCertificateList) {
        final List<GiftCertificateDTO> giftCertificateDTOList = new ArrayList<>();

        for (GiftCertificate giftCertificate : giftCertificateList) {

            GiftCertificateDTO giftCertificateDTO = toDTO(giftCertificate);

            giftCertificateDTOList.add(giftCertificateDTO);
        }

        return giftCertificateDTOList;
    }

    public static List<GiftCertificate> toEntity(List<GiftCertificateDTO> giftCertificateDTOList) {
        final List<GiftCertificate> giftCertificateList = new ArrayList<>();

        for (GiftCertificateDTO giftCertificateDTO : giftCertificateDTOList) {

            GiftCertificate giftCertificate = toEntity(giftCertificateDTO);

            giftCertificateList.add(giftCertificate);
        }

        return giftCertificateList;
    }

}
