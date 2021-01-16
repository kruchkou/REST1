package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.util.UpdateGiftCertificateSQLBuilder;
import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.util.GiftCertificateSQL;
import com.epam.esm.model.util.UpdateGiftCertificateQueryParameter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.time.Instant;
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

        giftCertificateDAO.deleteGiftCertificate(TEST_ID);
        Optional<GiftCertificate> giftCertificate = giftCertificateDAO.getGiftCertificateByID(TEST_ID);
        assertFalse(giftCertificate.isPresent());
    }

    @Test
    public void updateGiftCertificate() {
        final int NEW_TEST_PRICE = 500;
        final int TESTED_ID = 1;

        GiftCertificate gift = giftCertificateDAO.getGiftCertificateByID(TESTED_ID).get();

        UpdateGiftCertificateQueryParameter updateParameter = new UpdateGiftCertificateQueryParameter();
        updateParameter.setPrice(NEW_TEST_PRICE);
        updateParameter.setID(TESTED_ID);
        updateParameter.setLastUpdateDate(Instant.now());

        GiftCertificateSQL updateRequest = UpdateGiftCertificateSQLBuilder.getInstance().build(updateParameter);

        GiftCertificate updatedGift = giftCertificateDAO.updateGiftCertificate(updateRequest,TESTED_ID);

        assertEquals(NEW_TEST_PRICE, updatedGift.getPrice());
        assertNotEquals(gift.getLastsUpdateDate(),updatedGift.getLastsUpdateDate());
    }

    @Test
    public void getCertificateByID() {
        final int EXIST_ID = 1;
        final int NOT_EXIST_ID = 15;

        Optional<GiftCertificate> existGiftCertificate = giftCertificateDAO.getGiftCertificateByID(EXIST_ID);
        Optional<GiftCertificate> notExistGiftCertificate = giftCertificateDAO.getGiftCertificateByID(NOT_EXIST_ID);

        assertTrue(existGiftCertificate.isPresent());
        assertFalse(notExistGiftCertificate.isPresent());
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

        List<GiftCertificate> firstGiftCertificateList = giftCertificateDAO.getGiftCertificatesByTagName(FIRST_TEST_TAG_NAME);
        List<GiftCertificate> secondGiftCertificateList = giftCertificateDAO.getGiftCertificatesByTagName(SECOND_TEST_TAG_NAME);
        List<GiftCertificate> thirdGiftCertificateList = giftCertificateDAO.getGiftCertificatesByTagName(THIRD_TEST_TAG_NAME);

        assertEquals(GIFTS_QUANTITY_WITH_FIRST_TAG, firstGiftCertificateList.size());
        assertEquals(GIFTS_QUANTITY_WITH_SECOND_TAG, secondGiftCertificateList.size());
        assertEquals(GIFTS_QUANTITY_WITH_THIRD_TAG, thirdGiftCertificateList.size());
    }

    @Test
    public void getCertificatesByNameOrDescription() {
        final String SEARCH_PART_NAME = "Cert";
        final String SEARCH_PART_DESC = "This is";

        final int GIFT_QUANTITY_WITH_SEARCH_NAME = 2;
        final int GIFT_QUANTITY_WITH_DESC_NAME = 1;

        List<GiftCertificate> giftCertificateListByName = giftCertificateDAO.getGiftCertificatesByNameOrDescription(SEARCH_PART_NAME);
        List<GiftCertificate> giftCertificateListByDesc = giftCertificateDAO.getGiftCertificatesByNameOrDescription(SEARCH_PART_DESC);

        assertEquals(GIFT_QUANTITY_WITH_SEARCH_NAME, giftCertificateListByName.size());
        assertEquals(GIFT_QUANTITY_WITH_DESC_NAME, giftCertificateListByDesc.size());
    }

}