package com.epam.esm.service.util.mapper;

import com.epam.esm.service.model.dto.GiftCertificateDTO;
import com.epam.esm.repository.model.entity.GiftCertificate;
import com.epam.esm.repository.model.entity.Tag;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public final class EntityDTOGiftCertificateMapper {

    private EntityDTOGiftCertificateMapper() {
    }

    public static GiftCertificate toEntity(GiftCertificateDTO giftCertificateDTO) {
        LocalDateTime createDateLocalDateTime = giftCertificateDTO.getCreateDate();
        LocalDateTime lastUpdateDateLocalDateTime = giftCertificateDTO.getLastUpdateDate();

        GiftCertificate giftCertificate = new GiftCertificate();

        giftCertificate.setId(giftCertificateDTO.getId());
        giftCertificate.setName(giftCertificateDTO.getName());
        giftCertificate.setDescription(giftCertificateDTO.getDescription());
        giftCertificate.setPrice(giftCertificateDTO.getPrice());
        giftCertificate.setDuration(giftCertificateDTO.getDuration());

        if (createDateLocalDateTime != null) {
            Instant createDateInstant = giftCertificateDTO.getCreateDate().toInstant(ZoneOffset.UTC);
            giftCertificate.setCreateDate(createDateInstant);
        }

        if (lastUpdateDateLocalDateTime != null) {
            Instant lastUpdateDateZonedDateInstant = giftCertificateDTO.getLastUpdateDate().toInstant(ZoneOffset.UTC);
            giftCertificate.setLastUpdateDate(lastUpdateDateZonedDateInstant);
        }

        return giftCertificate;
    }

    public static GiftCertificateDTO toDTO(GiftCertificate giftCertificate) {
        Instant createDateLocalInstant = giftCertificate.getCreateDate();
        Instant lastUpdateDateInstant = giftCertificate.getLastUpdateDate();
        List<Tag> tagList = giftCertificate.getTagList();

        GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();

        giftCertificateDTO.setId(giftCertificate.getId());
        giftCertificateDTO.setName(giftCertificate.getName());
        giftCertificateDTO.setDescription(giftCertificate.getDescription());
        giftCertificateDTO.setPrice(giftCertificate.getPrice());
        giftCertificateDTO.setDuration(giftCertificate.getDuration());

        if (tagList != null ) {
            List<String> tagNamesList = new ArrayList<>();

            tagList.forEach(tag -> tagNamesList.add(tag.getName()));
            giftCertificateDTO.setTagNames(tagNamesList);
        }

        if (createDateLocalInstant != null) {
            LocalDateTime createDateLocalDateTime = LocalDateTime.ofInstant(giftCertificate.getCreateDate(), ZoneOffset.UTC);
            giftCertificateDTO.setCreateDate(createDateLocalDateTime);
        }

        if (lastUpdateDateInstant != null) {
            LocalDateTime lastUpdateDateLocalDateTime = LocalDateTime.ofInstant(giftCertificate.getLastUpdateDate(), ZoneOffset.UTC);
            giftCertificateDTO.setLastUpdateDate(lastUpdateDateLocalDateTime);
        }

        return giftCertificateDTO;
    }

    public static List<GiftCertificateDTO> toDTO(List<GiftCertificate> giftCertificateList) {
        List<GiftCertificateDTO> giftCertificateDTOList = new ArrayList<>();

        giftCertificateList.forEach(giftCertificate -> {
            GiftCertificateDTO giftCertificateDTO = toDTO(giftCertificate);
            giftCertificateDTOList.add(giftCertificateDTO);
        });

        return giftCertificateDTOList;
    }

    public static List<GiftCertificate> toEntity(List<GiftCertificateDTO> giftCertificateDTOList) {
        List<GiftCertificate> giftCertificateList = new ArrayList<>();

        giftCertificateDTOList.forEach(giftCertificateDTO -> {
            GiftCertificate giftCertificate = toEntity(giftCertificateDTO);
            giftCertificateList.add(giftCertificate);
        });

        return giftCertificateList;
    }

}
