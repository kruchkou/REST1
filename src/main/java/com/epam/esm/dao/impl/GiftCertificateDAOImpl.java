package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.model.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class GiftCertificateDAOImpl implements GiftCertificateDAO {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    private final static GiftCertificateMapper giftCertificateMapper = GiftCertificateMapper.getInstance();
    private final static TagMapper tagMapper = TagMapper.getInstance();

    private final static String SELECT_BY_NAME_OR_DESCRIPTION_SQL = "SELECT * FROM gift_certificate gift " +
            "WHERE (name REGEXP ? OR description REGEXP ?)";
    private final static String SELECT_BY_TAG_NAME_SQL = "SELECT * FROM gift_certificate gifts " +
            "INNER JOIN gift_tag link ON gifts.id = link.gift " +
            "INNER JOIN tag tags ON link.tag = tags.id " +
            "WHERE (tags.name = ?)";
    private final static String SELECT_ALL_SQL = "SELECT * FROM gift_certificate";

    private final static String SELECT_TAGS_SQL = "SELECT * FROM tag JOIN gift_tag gt on tag.id = gt.tag " +
            "where gt.gift = ?";
    private final static String SELECT_GIFT_BY_ID_SQL = "SELECT * FROM gift_certificate WHERE (id = ?)";
    private final static String DELETE_SQL = "DELETE FROM gift_certificate WHERE id = ?";
    private final static String UPDATE_SQL = "UPDATE gift_certificate SET name = ?, description = ?, price = ?, duration = ?, last_update_date = ? WHERE id = ?";
    private final static String CREATE_SQL = "INSERT INTO gift_certificate (name,description,price,duration,create_date) VALUES (?,?,?,?,?)";

    @Autowired
    public GiftCertificateDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public GiftCertificate createGiftCertificate(String name, String description, int price, int duration) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final Date CURRENT_DATETIME = new java.sql.Date(new java.util.Date().getTime());

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(ParamColumn.NAME_PARAM_ID, name);
            ps.setString(ParamColumn.DESC_PARAM_ID, description);
            ps.setInt(ParamColumn.PRICE_PARAM_ID, price);
            ps.setInt(ParamColumn.DURATION_PARAM_ID, duration);
            ps.setDate(ParamColumn.CREATE_DATE_PARAM_ID, CURRENT_DATETIME);
            return ps;
        }, keyHolder);

        return getGiftCertificateByID(keyHolder.getKey().intValue()).get();
    }

    @Override
    public GiftCertificate updateGiftCertificate(GiftCertificate updatedCertificate, int id) {
        final Date CURRENT_DATETIME = new java.sql.Date(new java.util.Date().getTime());
        jdbcTemplate.update(UPDATE_SQL,
                updatedCertificate.getName(),
                updatedCertificate.getDescription(),
                updatedCertificate.getPrice(),
                updatedCertificate.getDuration(),
                CURRENT_DATETIME,
                id);
        return getGiftCertificateByID(updatedCertificate.getId()).get();
    }

    @Override
    public void deleteGiftCertificate(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public Optional<GiftCertificate> getGiftCertificateByID(int id) {
        final int FIRST_ELEMENT_INDEX = 0;

        List<GiftCertificate> giftList = jdbcTemplate.query(SELECT_GIFT_BY_ID_SQL,
                new Object[]{id}, giftCertificateMapper);

        return giftList.isEmpty() ? Optional.empty() : Optional.of(giftList.get(FIRST_ELEMENT_INDEX));
    }

    @Override
    public List<GiftCertificate> getGiftCertificates() {
        List<GiftCertificate> certificates = jdbcTemplate.query(SELECT_ALL_SQL, giftCertificateMapper);
        for (GiftCertificate certificate : certificates) {
            certificate.setTagList(jdbcTemplate.query(SELECT_TAGS_SQL, new Object[]{certificate.getId()}, TagMapper.getInstance()));
        }
        return certificates;
    }

    @Override
    public List<GiftCertificate> getGiftCertificatesByTagName(String tagName) {
        return jdbcTemplate.query(SELECT_BY_TAG_NAME_SQL, new Object[]{tagName}, giftCertificateMapper);
    }

    @Override
    public List<GiftCertificate> getGiftCertificatesByNameOrDescription(String searchText) {
        return jdbcTemplate.query(SELECT_BY_NAME_OR_DESCRIPTION_SQL, new Object[]{searchText, searchText}, giftCertificateMapper);
    }

    public static class ParamColumn {
        private final static int NAME_PARAM_ID = 1;
        private final static int DESC_PARAM_ID = 2;
        private final static int PRICE_PARAM_ID = 3;
        private final static int DURATION_PARAM_ID = 4;
        private final static int CREATE_DATE_PARAM_ID = 5;
    }

}
