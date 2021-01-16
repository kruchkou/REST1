package com.epam.esm.dao.util;

import com.epam.esm.model.util.GiftCertificateSQL;
import com.epam.esm.model.util.UpdateGiftCertificateQueryParameter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UpdateGiftCertificateSQLBuilder {

    private final static UpdateGiftCertificateSQLBuilder instance = new UpdateGiftCertificateSQLBuilder();

    private final static String SPLIT_PARAM_STRING = ", ";
    private final static String UPDATE_SQL = "UPDATE gift_certificate SET ";
    private final static String ADD_NAME_SQL = "name = ?";
    private final static String ADD_DESCRIPTION = "description = ?";
    private final static String ADD_PRICE = "price = ?";
    private final static String ADD_DURATION = "duration = ?";
    private final static String SET_ID_SQL = " WHERE id = ?";
    private final static String ADD_LAST_UPDATE_DATE = "last_update_date = ?";

    private UpdateGiftCertificateSQLBuilder() {
    }

    public static UpdateGiftCertificateSQLBuilder getInstance() {
        return instance;
    }

    public GiftCertificateSQL build(UpdateGiftCertificateQueryParameter updateParameter) {
        StringBuilder queryBuilder = new StringBuilder();
        List<String> conditionList = new ArrayList<>();
        List<Object> paramList = new ArrayList<>();

        Integer id = updateParameter.getID();
        String name = updateParameter.getName();
        String description = updateParameter.getDescription();
        Integer price = updateParameter.getPrice();
        Integer duration = updateParameter.getDuration();
        Instant lastUpdateDate = updateParameter.getLastUpdateDate();

        if (name != null) {
            conditionList.add(ADD_NAME_SQL);
            paramList.add(name);
        }
        if (description != null) {
            conditionList.add(ADD_DESCRIPTION);
            paramList.add(description);
        }
        if (price != null) {
            conditionList.add(ADD_PRICE);
            paramList.add(price);
        }
        if (duration != null) {
            conditionList.add(ADD_DURATION);
            paramList.add(duration);
        }
        if (lastUpdateDate != null) {
            conditionList.add(ADD_LAST_UPDATE_DATE);
            paramList.add(lastUpdateDate);
        }

        paramList.add(id);

        Object[] paramArray = paramList.toArray();

        final String SQL_PARAM_QUERY = String.join(SPLIT_PARAM_STRING, conditionList);
        final String REQUEST_SQL = queryBuilder.append(UPDATE_SQL).append(SQL_PARAM_QUERY).append(SET_ID_SQL).toString();

        return new GiftCertificateSQL(REQUEST_SQL, paramArray);
    }

}
