package com.epam.esm.dao.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.dao.GiftCertificateDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.jupiter.api.Assertions.*;

class GiftCertificateDAOImplTest {

    private EmbeddedDatabase embeddedDatabase;
    private GiftCertificateDAO giftCertificateDAO;

    @BeforeEach
    void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();

        giftCertificateDAO = new GiftCertificateDAOImpl(embeddedDatabase);
    }

    @AfterEach
    void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    void createGiftCertificate() {
        final String TEST_NAME = "SaveTest Cert";
        final String TEST_DESC = "This is SaveTest certificate";

        final int TEST_PRICE = 300;
        final int TEST_DURATION = 30;

        GiftCertificate savedGift = giftCertificateDAO.createGiftCertificate(TEST_NAME,TEST_DESC, TEST_PRICE, TEST_DURATION);

        assertNotNull(savedGift);
        assertEquals(savedGift.getName(),TEST_NAME);
        assertEquals(savedGift.getDescription(),TEST_DESC);
        assertEquals(savedGift.getPrice(),TEST_PRICE);
        assertEquals(savedGift.getDuration(),TEST_DURATION);
    }

    @Test
    void deleteGiftCertificate() {
        final int TEST_ID = 1;

        final int QUANTITY_WHEN_SUCCESSFULLY_DELETED = 1;
        final int QUANTITY_WHEN_NOTHING_DELETED = 0;

        assertEquals(QUANTITY_WHEN_SUCCESSFULLY_DELETED,giftCertificateDAO.deleteCertificate(TEST_ID));
        assertEquals(QUANTITY_WHEN_NOTHING_DELETED,giftCertificateDAO.deleteCertificate(TEST_ID));
    }

    @Test
    void updateGiftCertificate() {
        final int NEW_TEST_PRICE = 500;
        final int TESTED_ID = 1;
        final int QUANTITY_WHEN_SUCCESSFULLY_UPDATED = 1;

        GiftCertificate gift = giftCertificateDAO.getCertificateByID(TESTED_ID);

        gift.setPrice(NEW_TEST_PRICE);

        assertEquals(QUANTITY_WHEN_SUCCESSFULLY_UPDATED,giftCertificateDAO.updateCertificate(gift));

        gift = giftCertificateDAO.getCertificateByID(gift.getId());

        assertEquals(gift.getPrice(),NEW_TEST_PRICE);
        assertNotNull(gift.getLastsUpdateDate());
    }

    @Test
    void getCertificateByID() {
        final int EXIST_ID = 1;
        final int NOT_EXIST_ID = 15;

        assertNotNull(giftCertificateDAO.getCertificateByID(EXIST_ID));
        assertNull(giftCertificateDAO.getCertificateByID(NOT_EXIST_ID));
    }

    @Test
    void getCertificates() {
        final int EXIST_GIFT_QUANTITY = 2;

        assertNotNull(giftCertificateDAO.getCertificates());
        assertEquals(EXIST_GIFT_QUANTITY, giftCertificateDAO.getCertificates().size());
    }

    @Test
    void getCertificatesByTagName() {
        final String FIRST_TEST_TAG_NAME = "first";
        final String SECOND_TEST_TAG_NAME = "second";
        final String THIRD_TEST_TAG_NAME = "third";

        final int GIFTS_QUANTITY_WITH_FIRST_TAG = 1;
        final int GIFTS_QUANTITY_WITH_SECOND_TAG = 2;
        final int GIFTS_QUANTITY_WITH_THIRD_TAG = 0;

        assertEquals(GIFTS_QUANTITY_WITH_FIRST_TAG,giftCertificateDAO.getCertificatesByTagName(FIRST_TEST_TAG_NAME).size());
        assertEquals(GIFTS_QUANTITY_WITH_SECOND_TAG,giftCertificateDAO.getCertificatesByTagName(SECOND_TEST_TAG_NAME).size());
        assertEquals(GIFTS_QUANTITY_WITH_THIRD_TAG,giftCertificateDAO.getCertificatesByTagName(THIRD_TEST_TAG_NAME).size());
    }

    @Test
    void getCertificatesByNameOrDescription() {
        final String SEARCH_PART_NAME = "Cert";
        final String SEARCH_PART_DESC = "This is";

        final int GIFT_QUANTITY_WITH_SEARCH_NAME = 2;
        final int GIFT_QUANTITY_WITH_DESC_NAME = 1;

        assertNotNull(giftCertificateDAO.getCertificatesByNameOrDescription(SEARCH_PART_NAME));
        assertEquals(GIFT_QUANTITY_WITH_SEARCH_NAME,giftCertificateDAO.getCertificatesByNameOrDescription(SEARCH_PART_NAME).size());
        assertEquals(GIFT_QUANTITY_WITH_DESC_NAME,giftCertificateDAO.getCertificatesByNameOrDescription(SEARCH_PART_DESC).size());
    }

}