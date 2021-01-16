package com.epam.esm.service.util.mapper;

import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.model.entity.GiftCertificate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EntityDTOGiftCertificateMapperTest {

    private final static int TEST_ID = 3;
    private final static String TEST_NAME = "test name";
    private final static String TEST_DESCRIPTION = "test description";
    private final static int TEST_PRICE = 10;
    private final static int TEST_DURATION = 20;
    private final static Instant TEST_CREATE_DATE_INSTANT = Instant.now();
    private final static Instant TEST_LAST_UPDATE_DATE_INSTANT = Instant.now();
    private final static String ZONE_ID_MINSK = "Europe/Minsk";
    private final static ZonedDateTime TEST_CREATE_DATE_ZONED_DATE_TIME = TEST_CREATE_DATE_INSTANT.atZone(ZoneId.of(ZONE_ID_MINSK));
    private final static ZonedDateTime TEST_LAST_UPDATE_DATE_ZONED_DATE_TIME = TEST_LAST_UPDATE_DATE_INSTANT.atZone(ZoneId.of(ZONE_ID_MINSK));
    private GiftCertificate giftCertificate;
    private GiftCertificateDTO giftCertificateDTO;

    @BeforeEach
    public void init() {
        giftCertificate = new GiftCertificate();
        giftCertificateDTO = new GiftCertificateDTO();

        giftCertificate.setId(TEST_ID);
        giftCertificate.setName(TEST_NAME);
        giftCertificate.setDescription(TEST_DESCRIPTION);
        giftCertificate.setPrice(TEST_PRICE);
        giftCertificate.setDuration(TEST_DURATION);
        giftCertificate.setCreateDate(TEST_CREATE_DATE_INSTANT);
        giftCertificate.setLastsUpdateDate(TEST_LAST_UPDATE_DATE_INSTANT);

        giftCertificateDTO.setId(TEST_ID);
        giftCertificateDTO.setName(TEST_NAME);
        giftCertificateDTO.setDescription(TEST_DESCRIPTION);
        giftCertificateDTO.setPrice(TEST_PRICE);
        giftCertificateDTO.setDuration(TEST_DURATION);
        giftCertificateDTO.setCreateDate(TEST_CREATE_DATE_ZONED_DATE_TIME);
        giftCertificateDTO.setLastsUpdateDate(TEST_LAST_UPDATE_DATE_ZONED_DATE_TIME);
    }

    @Test
    public void shouldConvertToEntity() {
        final GiftCertificate testedGiftCertificate = EntityDTOGiftCertificateMapper.toEntity(giftCertificateDTO);

        assertEquals(TEST_NAME, testedGiftCertificate.getName());
        assertEquals(TEST_DESCRIPTION, testedGiftCertificate.getDescription());
        assertEquals(TEST_PRICE, testedGiftCertificate.getPrice());
        assertEquals(TEST_DURATION, testedGiftCertificate.getDuration());
        assertEquals(TEST_CREATE_DATE_INSTANT, testedGiftCertificate.getCreateDate());
        assertEquals(TEST_LAST_UPDATE_DATE_INSTANT, testedGiftCertificate.getLastsUpdateDate());
    }

    @Test
    public void shouldConvertToDTO() {
        final GiftCertificateDTO testedGiftCertificateDTO = EntityDTOGiftCertificateMapper.toDTO(giftCertificate);

        assertEquals(TEST_NAME, testedGiftCertificateDTO.getName());
        assertEquals(TEST_DESCRIPTION, testedGiftCertificateDTO.getDescription());
        assertEquals(TEST_PRICE, testedGiftCertificateDTO.getPrice());
        assertEquals(TEST_DURATION, testedGiftCertificateDTO.getDuration());
        assertEquals(TEST_CREATE_DATE_ZONED_DATE_TIME, testedGiftCertificateDTO.getCreateDate());
        assertEquals(TEST_LAST_UPDATE_DATE_ZONED_DATE_TIME, testedGiftCertificateDTO.getLastsUpdateDate());
    }
}