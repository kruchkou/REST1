package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.model.entity.GiftCertificate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GiftCertificateDAOImplTest {

    private EmbeddedDatabase embeddedDatabase;
    private GiftCertificateDAO giftCertificateDAO;

    @BeforeEach
    public void setUp() {

        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();

        giftCertificateDAO = new GiftCertificateDAOImpl(embeddedDatabase);
    }

    @AfterEach
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    public void createGiftCertificate() {
        final String TEST_NAME = "SaveTest Cert";
        final String TEST_DESC = "This is SaveTest certificate";

        final int TEST_PRICE = 300;
        final int TEST_DURATION = 30;

        GiftCertificate savedGift = giftCertificateDAO.createGiftCertificate(TEST_NAME, TEST_DESC, TEST_PRICE, TEST_DURATION);

        assertNotNull(savedGift);
        assertEquals(TEST_NAME, savedGift.getName());
        assertEquals(TEST_DESC, savedGift.getDescription());
        assertEquals(TEST_PRICE, savedGift.getPrice());
        assertEquals(TEST_DURATION, savedGift.getDuration());
    }

    @Test
    public void deleteGiftCertificate() {
        final int TEST_ID = 1;

        final int QUANTITY_WHEN_SUCCESSFULLY_DELETED = 1;
        final int QUANTITY_WHEN_NOTHING_DELETED = 0;

        giftCertificateDAO.deleteGiftCertificate(TEST_ID);
        Optional<GiftCertificate> giftCertificate = giftCertificateDAO.getGiftCertificateByID(TEST_ID);
        assertFalse(giftCertificate.isPresent());
    }

    @Test
    public void updateGiftCertificate() {
        final int NEW_TEST_PRICE = 500;
        final int TESTED_ID = 1;

        GiftCertificate gift = giftCertificateDAO.getGiftCertificateByID(TESTED_ID).get();

        gift.setPrice(NEW_TEST_PRICE);

        gift = giftCertificateDAO.getGiftCertificateByID(gift.getId()).get();

        assertEquals(NEW_TEST_PRICE, gift.getPrice());
        assertNotNull(gift.getLastsUpdateDate());
    }

    @Test
    public void getCertificateByID() {
        final int EXIST_ID = 1;
        final int NOT_EXIST_ID = 15;

        assertNotNull(giftCertificateDAO.getGiftCertificateByID(EXIST_ID));
        assertNull(giftCertificateDAO.getGiftCertificateByID(NOT_EXIST_ID));
    }

    @Test
    public void getCertificates() {
        final int EXIST_GIFT_QUANTITY = 2;
        final List<GiftCertificate> giftCertificateList = giftCertificateDAO.getGiftCertificates();

        assertNotNull(giftCertificateList);
        assertEquals(EXIST_GIFT_QUANTITY, giftCertificateList.size());
    }

    @Test
    public void getCertificatesByTagName() {
        final String FIRST_TEST_TAG_NAME = "first";
        final String SECOND_TEST_TAG_NAME = "second";
        final String THIRD_TEST_TAG_NAME = "third";

        final int GIFTS_QUANTITY_WITH_FIRST_TAG = 1;
        final int GIFTS_QUANTITY_WITH_SECOND_TAG = 2;
        final int GIFTS_QUANTITY_WITH_THIRD_TAG = 0;

        assertEquals(GIFTS_QUANTITY_WITH_FIRST_TAG, giftCertificateDAO.getGiftCertificatesByTagName(FIRST_TEST_TAG_NAME).size());
        assertEquals(GIFTS_QUANTITY_WITH_SECOND_TAG, giftCertificateDAO.getGiftCertificatesByTagName(SECOND_TEST_TAG_NAME).size());
        assertEquals(GIFTS_QUANTITY_WITH_THIRD_TAG, giftCertificateDAO.getGiftCertificatesByTagName(THIRD_TEST_TAG_NAME).size());
    }

    @Test
    public void getCertificatesByNameOrDescription() {
        final String SEARCH_PART_NAME = "Cert";
        final String SEARCH_PART_DESC = "This is";

        final int GIFT_QUANTITY_WITH_SEARCH_NAME = 2;
        final int GIFT_QUANTITY_WITH_DESC_NAME = 1;

        assertNotNull(giftCertificateDAO.getGiftCertificatesByNameOrDescription(SEARCH_PART_NAME));
        assertEquals(GIFT_QUANTITY_WITH_SEARCH_NAME, giftCertificateDAO.getGiftCertificatesByNameOrDescription(SEARCH_PART_NAME).size());
        assertEquals(GIFT_QUANTITY_WITH_DESC_NAME, giftCertificateDAO.getGiftCertificatesByNameOrDescription(SEARCH_PART_DESC).size());
    }

}