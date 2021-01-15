package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.model.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class TagDAOImpl implements TagDAO {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Tag createTag(String name) {
        final String CREATE_SQL = "INSERT INTO tag(name) VALUES (?)";
        jdbcTemplate.update(CREATE_SQL, name);

        return getTagByName(name).get();
    }

    @Override
    public void deleteTag(int id) {
        final String DELETE_SQL = "DELETE FROM tag WHERE id = ?";
        jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public void insertGiftTag(int giftID, int tagID) {
        final String INSERT_INTO_GIFT_TAG_SQL = "INSERT INTO gift_tag(gift, tag) VALUES (?,?)";
        jdbcTemplate.update(INSERT_INTO_GIFT_TAG_SQL, giftID, tagID);
    }

    @Override
    public Optional<Tag> getTagByID(int id) {
        final String GET_TAG_BY_ID_SQL = "SELECT * FROM tag WHERE id = ?";
        final int FIRST_ELEMENT_INDEX = 0;

        Optional<Tag> optional;
        List<Tag> tagList = jdbcTemplate.query(GET_TAG_BY_ID_SQL,
                new Object[]{id}, new TagMapper());

        if (tagList.isEmpty()) {
            optional = Optional.empty();
        } else {
            optional = Optional.of(tagList.get(FIRST_ELEMENT_INDEX));
        }

        return optional;
    }

    @Override
    public List<Tag> getTags() {
        final String GET_TAGS_SQL = "SELECT * FROM tag";

        return jdbcTemplate.query(GET_TAGS_SQL, new TagMapper());
    }

    public List<Tag> getTagListByGiftCertificateID(int id) {
        final String SELECT_BY_TAG_NAME_SQL = "SELECT * FROM tag tags " +
                "INNER JOIN gift_tag link ON tags.id = link.tag " +
                "INNER JOIN gift_certificate gift ON link.gift = gift.id " +
                "WHERE (gift.id = ?)";

        return jdbcTemplate.query(SELECT_BY_TAG_NAME_SQL, new Object[]{id}, new TagMapper());
    }

    @Override
    public Optional<Tag> getTagByName(String name) {
        final String SELECT_BY_TAG_NAME_SQL = "SELECT * FROM tag WHERE (name = ?)";

        final int FIRST_ELEMENT_INDEX = 0;

        List<Tag> tagList = jdbcTemplate.query(SELECT_BY_TAG_NAME_SQL,
                new Object[]{name}, new TagMapper());

        Optional<Tag> optional;

        if (tagList.isEmpty()) {
            optional = Optional.empty();
        } else {
            optional = Optional.of(tagList.get(FIRST_ELEMENT_INDEX));
        }

        return optional;
    }
}
