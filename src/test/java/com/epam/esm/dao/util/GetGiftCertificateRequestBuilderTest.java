package com.epam.esm.dao.util;

import com.epam.esm.model.util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GetGiftCertificateRequestBuilderTest {

    private final static String TEST_NAME = "Cert";
    private final static String TEST_DESCRIPTION = "certificate";
    private final static String TEST_TAG_NAME = "second";

    private final static String CORRECT_BY_NAME_SQL = "SELECT * from gift_certificate gifts WHERE (gifts.name REGEXP ?) " +
            "ORDER BY gifts.name ASC";
    private final static String CORRECT_BY_TAG_NAME_SQL = "SELECT * from gift_certificate gifts " +
            "INNER JOIN gift_tag link ON gifts.id = link.gift " +
            "INNER JOIN tag tags ON link.tag = tags.id " +
            "WHERE (tags.name = ? AND description REGEXP ?) " +
            "ORDER BY last_update_date DESC";

    private final static Object[] CORRECT_BY_NAME_ELEMENTS = new Object[]{TEST_NAME};
    private final static Object[] CORRECT_BY_TAG_NAME_ELEMENTS = new Object[]{TEST_TAG_NAME,TEST_DESCRIPTION};

    @Test
    public void testBuildMethod() {
        GetGiftCertificateQueryParameter byNameQueryParameter = new GetGiftCertificateQueryParameter();
        byNameQueryParameter.setName(TEST_NAME);
        byNameQueryParameter.setSortBy(SortBy.NAME);
        byNameQueryParameter.setSortOrientation(SortOrientation.ASC);

        GetGiftCertificateQueryParameter byTagNameQueryParameter = new GetGiftCertificateQueryParameter();
        byTagNameQueryParameter.setTagName(TEST_TAG_NAME);
        byTagNameQueryParameter.setDescription(TEST_DESCRIPTION);
        byTagNameQueryParameter.setSortBy(SortBy.DATE);
        byTagNameQueryParameter.setSortOrientation(SortOrientation.DESC);

        GiftCertificateRequest byNameRequest = GetGiftCertificateRequestBuilder.getInstance().build(byNameQueryParameter);
        GiftCertificateRequest byTagNameRequest = GetGiftCertificateRequestBuilder.getInstance().build(byTagNameQueryParameter);


        assertEquals(CORRECT_BY_NAME_SQL,byNameRequest.getRequest());
        assertArrayEquals(CORRECT_BY_NAME_ELEMENTS,byNameRequest.getParams());
        assertEquals(CORRECT_BY_TAG_NAME_SQL,byTagNameRequest.getRequest());
        assertArrayEquals(CORRECT_BY_TAG_NAME_ELEMENTS,byTagNameRequest.getParams());
    }

}