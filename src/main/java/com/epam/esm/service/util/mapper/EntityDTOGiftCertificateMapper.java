package com.epam.esm.service.util.mapper;

import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.model.entity.GiftCertificate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public final class EntityDTOGiftCertificateMapper {

    private EntityDTOGiftCertificateMapper() {
    }

    public static GiftCertificate toEntity(GiftCertificateDTO giftCertificateDTO) {
        final String ZONE_ID_MINSK = "Europe/Minsk";
        final Instant createDateInstant = giftCertificateDTO.getCreateDate().toInstant();
        final Instant lastUpdateDateZonedDateInstant = giftCertificateDTO.getLastsUpdateDate().toInstant();
        final GiftCertificate giftCertificate = new GiftCertificate();

        giftCertificate.setId(giftCertificateDTO.getId());
        giftCertificate.setName(giftCertificateDTO.getName());
        giftCertificate.setDescription(giftCertificateDTO.getDescription());
        giftCertificate.setPrice(giftCertificateDTO.getPrice());
        giftCertificate.setDuration(giftCertificateDTO.getDuration());
        giftCertificate.setCreateDate(createDateInstant);
        giftCertificate.setLastsUpdateDate(lastUpdateDateZonedDateInstant);

        return giftCertificate;
    }

    public static GiftCertificateDTO toDTO(GiftCertificate giftCertificate) {
        final String ZONE_ID_MINSK = "Europe/Minsk";
        final ZonedDateTime createDateZonedDateTime = giftCertificate.getCreateDate().atZone(ZoneId.of(ZONE_ID_MINSK));
        final ZonedDateTime lastUpdateDateZonedDateTime = giftCertificate.getLastsUpdateDate().atZone(ZoneId.of(ZONE_ID_MINSK));
        final GiftCertificateDTO giftCertificateDTO = new GiftCertificateDTO();

        giftCertificateDTO.setId(giftCertificate.getId());
        giftCertificateDTO.setName(giftCertificate.getName());
        giftCertificateDTO.setDescription(giftCertificate.getDescription());
        giftCertificateDTO.setPrice(giftCertificate.getPrice());
        giftCertificateDTO.setDuration(giftCertificate.getDuration());
        giftCertificateDTO.setCreateDate(createDateZonedDateTime);
        giftCertificateDTO.setLastsUpdateDate(lastUpdateDateZonedDateTime);

        return giftCertificateDTO;
    }

    public static List<GiftCertificateDTO> toDTO(List<GiftCertificate> giftCertificateList) {
        final List<GiftCertificateDTO> giftCertificateDTOList = new ArrayList<>();

        giftCertificateList.forEach(giftCertificate -> {
            GiftCertificateDTO giftCertificateDTO = toDTO(giftCertificate);
            giftCertificateDTOList.add(giftCertificateDTO);
        });

        return giftCertificateDTOList;
    }

    public static List<GiftCertificate> toEntity(List<GiftCertificateDTO> giftCertificateDTOList) {
        final List<GiftCertificate> giftCertificateList = new ArrayList<>();

        giftCertificateDTOList.forEach(giftCertificateDTO -> {
            GiftCertificate giftCertificate = toEntity(giftCertificateDTO);
            giftCertificateList.add(giftCertificate);
        });

        return giftCertificateList;
    }

}
