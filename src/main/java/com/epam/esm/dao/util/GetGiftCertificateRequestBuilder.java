package com.epam.esm.dao.util;

import com.epam.esm.model.util.GetGiftCertificateQueryParameter;
import com.epam.esm.model.util.GiftCertificateRequest;
import com.epam.esm.model.util.SortBy;
import com.epam.esm.model.util.SortOrientation;

import java.util.ArrayList;
import java.util.List;

public class GetGiftCertificateRequestBuilder {

    private final static GetGiftCertificateRequestBuilder instance = new GetGiftCertificateRequestBuilder();

    private final static String SPLIT_PARAM_STRING = " AND ";

    private final static String SELECT_SQL = "SELECT * from gift_certificate gifts WHERE (";
    private final static String SELECT_WITH_TAG_NAME_SQL = "SELECT * from gift_certificate gifts " +
            "INNER JOIN gift_tag link ON gifts.id = link.gift " +
            "INNER JOIN tag tags ON link.tag = tags.id " +
            "WHERE (";
    private final static String ADD_NAME_SQL = "gifts.name REGEXP ?";
    private final static String ADD_DESCRIPTION_SQL = "description REGEXP ?";
    private final static String ADD_TAG_NAME_PARAM = "tags.name = ?";
    private final static String CLOSE_WHERE_SQL = ")";
    private final static String ORDER_BY_SQL = " ORDER BY ";
    private final static String NAME_PARAM = "gifts.name";
    private final static String LAST_UPDATE_DATE_PARAM = "last_update_date";
    private final static String ORDER_ORIENTATION_ASC = " ASC";
    private final static String ORDER_ORIENTATION_DESC = " DESC";

    private GetGiftCertificateRequestBuilder() {
    }

    public static GetGiftCertificateRequestBuilder getInstance() {
        return instance;
    }

    public GiftCertificateRequest build(GetGiftCertificateQueryParameter giftCertificateQueryParameter) {
        StringBuilder queryBuilder = new StringBuilder();
        List<String> conditionList = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        String name = giftCertificateQueryParameter.getName();
        String description = giftCertificateQueryParameter.getDescription();
        String tagName = giftCertificateQueryParameter.getTagName();
        SortBy sortBy = giftCertificateQueryParameter.getSortBy();
        SortOrientation sortOrientation = giftCertificateQueryParameter.getSortOrientation();

        if (tagName != null) {
            queryBuilder.append(SELECT_WITH_TAG_NAME_SQL);
            conditionList.add(ADD_TAG_NAME_PARAM);
            params.add(tagName);
        } else {
            queryBuilder.append(SELECT_SQL);
        }

        if (name != null) {
            conditionList.add(ADD_NAME_SQL);
            params.add(name);
        }

        if (description != null) {
            conditionList.add(ADD_DESCRIPTION_SQL);
            params.add(description);
        }

        queryBuilder.append(String.join(SPLIT_PARAM_STRING, conditionList));
        queryBuilder.append(CLOSE_WHERE_SQL);

        if (sortBy != null) {
            queryBuilder.append(ORDER_BY_SQL);

            if (sortBy == SortBy.NAME) {
                queryBuilder.append(NAME_PARAM);
            } else if (sortBy == SortBy.DATE) {
                queryBuilder.append(LAST_UPDATE_DATE_PARAM);
            }
        }

        switch (sortOrientation) {
            case ASC: {
                queryBuilder.append(ORDER_ORIENTATION_ASC);
                break;
            }
            case DESC:
                queryBuilder.append(ORDER_ORIENTATION_DESC);
                break;
        }

        return new GiftCertificateRequest(queryBuilder.toString(), params.toArray());
    }

}

