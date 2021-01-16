package com.epam.esm.dao.util;

import com.epam.esm.model.util.UpdateGiftCertificateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateGiftCertificateRequestBuilderTest {

    private final static String CORRECT_SQL = "UPDATE gift_certificate SET name = ?, description = ?, price = ?, duration = ?, last_update_date = ? WHERE id = ?";
    private final static String TEST_NAME = "test_name";
    private final static String TEST_DESCRIPTION = "test_desc";
    private final static int TEST_PRICE = 10;
    private final static int TEST_DURATION = 30;
    private final static int FAKE_PRICE = 20;
    private final static Instant LAST_UPDATE_INSTANT = Instant.now();
    private final static UpdateGiftCertificateRequestBuilder updateGiftCertificateQueryBuilder =
            UpdateGiftCertificateRequestBuilder.getInstance();

    private Object[] correctElements;

    @BeforeEach
    public void init() {
        correctElements = new Object[]{TEST_NAME, TEST_DESCRIPTION, TEST_PRICE, TEST_DURATION, LAST_UPDATE_INSTANT};
    }

    @Test
    public void testBuildMethod() {
        UpdateGiftCertificateRequest testRequest = updateGiftCertificateQueryBuilder
                .setName(TEST_NAME)
                .setDescription(TEST_DESCRIPTION)
                .setPrice(FAKE_PRICE)
                .setDuration(TEST_DURATION)
                .setPrice(TEST_PRICE)
                .setDuration(TEST_DURATION)
                .build(LAST_UPDATE_INSTANT);

        assertEquals(CORRECT_SQL,testRequest.getRequest());
        assertArrayEquals(correctElements,testRequest.getParams());
    }

}