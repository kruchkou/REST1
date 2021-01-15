package com.epam.esm.service.util.mapper;

import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.model.entity.GiftCertificate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntityDTOGiftCertificateMapperTest {

    @Test
    public void toEntity(@Mock GiftCertificateDTO giftCertificateDTO) {
        final String testName = "test name";
        final String testDescription = "test description";
        final int testPrice = 10;
        final int testDuration = 20;
        final Date testCreateDate = new Date();
        final Date testLastUpdateDate = new Date();

        lenient().when(giftCertificateDTO.getName()).thenReturn(testName);
        lenient().when(giftCertificateDTO.getDescription()).thenReturn(testDescription);
        lenient().when(giftCertificateDTO.getPrice()).thenReturn(testPrice);
        lenient().when(giftCertificateDTO.getDuration()).thenReturn(testDuration);
        lenient().when(giftCertificateDTO.getCreateDate()).thenReturn(testCreateDate);
        lenient().when(giftCertificateDTO.getLastsUpdateDate()).thenReturn(testLastUpdateDate);

        final GiftCertificate giftCertificate = EntityDTOGiftCertificateMapper.toEntity(giftCertificateDTO);

        assertEquals(giftCertificateDTO.getName(),giftCertificate.getName());
        assertEquals(giftCertificateDTO.getDescription(),giftCertificate.getDescription());
        assertEquals(giftCertificateDTO.getPrice(),giftCertificate.getPrice());
        assertEquals(giftCertificateDTO.getDuration(),giftCertificate.getDuration());
        assertEquals(giftCertificateDTO.getCreateDate(),giftCertificate.getCreateDate());
        assertEquals(giftCertificateDTO.getLastsUpdateDate(),giftCertificate.getLastsUpdateDate());
    }

    @Test
    public void toDTO(@Mock GiftCertificate giftCertificate) {
        final String testName = "test name";
        final String testDescription = "test description";
        final int testPrice = 10;
        final int testDuration = 20;
        final Date testCreateDate = new Date();
        final Date testLastUpdateDate = new Date();

        lenient().when(giftCertificate.getName()).thenReturn(testName);
        lenient().when(giftCertificate.getDescription()).thenReturn(testDescription);
        lenient().when(giftCertificate.getPrice()).thenReturn(testPrice);
        lenient().when(giftCertificate.getDuration()).thenReturn(testDuration);
        lenient().when(giftCertificate.getCreateDate()).thenReturn(testCreateDate);
        lenient().when(giftCertificate.getLastsUpdateDate()).thenReturn(testLastUpdateDate);

        final GiftCertificateDTO giftCertificateDTO = EntityDTOGiftCertificateMapper.toDTO(giftCertificate);

        assertEquals(giftCertificate.getName(),giftCertificateDTO.getName());
        assertEquals(giftCertificate.getDescription(),giftCertificateDTO.getDescription());
        assertEquals(giftCertificate.getPrice(),giftCertificateDTO.getPrice());
        assertEquals(giftCertificate.getDuration(),giftCertificateDTO.getDuration());
        assertEquals(giftCertificate.getCreateDate(),giftCertificateDTO.getCreateDate());
        assertEquals(giftCertificate.getLastsUpdateDate(),giftCertificateDTO.getLastsUpdateDate());
    }
}