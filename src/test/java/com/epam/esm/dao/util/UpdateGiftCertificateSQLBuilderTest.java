package com.epam.esm.dao.util;

import com.epam.esm.model.util.GiftCertificateSQL;
import com.epam.esm.model.util.UpdateGiftCertificateQueryParameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateGiftCertificateSQLBuilderTest {

    private final static String CORRECT_SQL = "UPDATE gift_certificate SET name = ?, description = ?, price = ?, " +
            "duration = ?, last_update_date = ? WHERE id = ?";
    private final static String TEST_NAME = "test_name";
    private final static String TEST_DESCRIPTION = "test_desc";
    private final static int TEST_ID = 2;
    private final static int TEST_PRICE = 10;
    private final static int TEST_DURATION = 30;
    private final static int FAKE_PRICE = 20;
    private final static Instant LAST_UPDATE_INSTANT = Instant.now();
    private final static UpdateGiftCertificateSQLBuilder updateGiftCertificateQueryBuilder =
            UpdateGiftCertificateSQLBuilder.getInstance();

    private Object[] correctElements;
    UpdateGiftCertificateQueryParameter updateParameter;

    @BeforeEach
    public void init() {
        correctElements = new Object[]{TEST_NAME, TEST_DESCRIPTION, TEST_PRICE, TEST_DURATION, LAST_UPDATE_INSTANT, TEST_ID};
        updateParameter = new UpdateGiftCertificateQueryParameter();

        updateParameter.setID(TEST_ID);
        updateParameter.setName(TEST_NAME);
        updateParameter.setDescription(TEST_DESCRIPTION);
        updateParameter.setPrice(FAKE_PRICE);
        updateParameter.setPrice(TEST_PRICE);
        updateParameter.setDuration(TEST_DURATION);
        updateParameter.setLastUpdateDate(LAST_UPDATE_INSTANT);
    }

    @Test
    public void testBuildMethod() {
        GiftCertificateSQL testSQL = updateGiftCertificateQueryBuilder
                .build(updateParameter);

        assertEquals(CORRECT_SQL, testSQL.getRequest());
        assertArrayEquals(correctElements, testSQL.getParams());
    }

}