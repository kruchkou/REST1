package com.epam.esm.service.util.mapper;

import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.model.entity.GiftCertificate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EntityDTOGiftCertificateMapperTest {

    private static final int testID = 3;
    private static final String testName = "test name";
    private static final String testDescription = "test description";
    private static final int testPrice = 10;
    private static final int testDuration = 20;
    private static final Date testCreateDate = new Date();
    private static final Date testLastUpdateDate = new Date();

    private GiftCertificate giftCertificate;
    private GiftCertificateDTO giftCertificateDTO;

    @BeforeEach
    public void init() {
        giftCertificate = new GiftCertificate();
        giftCertificateDTO = new GiftCertificateDTO();

        giftCertificate.setId(testID);
        giftCertificate.setName(testName);
        giftCertificate.setDescription(testDescription);
        giftCertificate.setPrice(testPrice);
        giftCertificate.setDuration(testDuration);
        giftCertificate.setCreateDate(testCreateDate);
        giftCertificate.setLastsUpdateDate(testLastUpdateDate);

        giftCertificateDTO.setId(testID);
        giftCertificateDTO.setName(testName);
        giftCertificateDTO.setDescription(testDescription);
        giftCertificateDTO.setPrice(testPrice);
        giftCertificateDTO.setDuration(testDuration);
        giftCertificateDTO.setCreateDate(testCreateDate);
        giftCertificateDTO.setLastsUpdateDate(testLastUpdateDate);
    }

    @Test
    public void shouldConvertToEntity() {
        final GiftCertificate testedGiftCertificate = EntityDTOGiftCertificateMapper.toEntity(giftCertificateDTO);

        assertEquals(testName, testedGiftCertificate.getName());
        assertEquals(testDescription, testedGiftCertificate.getDescription());
        assertEquals(testPrice, testedGiftCertificate.getPrice());
        assertEquals(testDuration, testedGiftCertificate.getDuration());
        assertEquals(testCreateDate, testedGiftCertificate.getCreateDate());
        assertEquals(testLastUpdateDate, testedGiftCertificate.getLastsUpdateDate());
    }

    @Test
    public void shouldConvertToDTO() {
        final GiftCertificateDTO testedGiftCertificateDTO = EntityDTOGiftCertificateMapper.toDTO(giftCertificate);

        assertEquals(testName, testedGiftCertificateDTO.getName());
        assertEquals(testDescription, testedGiftCertificateDTO.getDescription());
        assertEquals(testPrice, testedGiftCertificateDTO.getPrice());
        assertEquals(testDuration, testedGiftCertificateDTO.getDuration());
        assertEquals(testCreateDate, testedGiftCertificateDTO.getCreateDate());
        assertEquals(testLastUpdateDate, testedGiftCertificateDTO.getLastsUpdateDate());
    }
}