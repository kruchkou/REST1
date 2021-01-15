package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.model.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class TagDAOImpl implements TagDAO {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    private final static TagMapper tagMapper = TagMapper.getInstance();

    private final static String CREATE_SQL = "INSERT INTO tag(name) VALUES (?)";
    private final static String DELETE_SQL = "DELETE FROM tag WHERE id = ?";
    private final static String INSERT_INTO_GIFT_TAG_SQL = "INSERT INTO gift_tag(gift, tag) VALUES (?,?)";
    private final static String GET_TAG_BY_ID_SQL = "SELECT * FROM tag WHERE id = ?";
    private final static String GET_TAGS_SQL = "SELECT * FROM tag";
    private final static String SELECT_BY_TAG_NAME_SQL = "SELECT * FROM tag tags " +
            "INNER JOIN gift_tag link ON tags.id = link.tag " +
            "INNER JOIN gift_certificate gift ON link.gift = gift.id " +
            "WHERE (gift.id = ?)";

    @Autowired
    public TagDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Tag createTag(String name) {
        jdbcTemplate.update(CREATE_SQL, name);

        return getTagByName(name).get();
    }

    @Override
    public void deleteTag(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public void insertGiftTag(int giftID, int tagID) {
        jdbcTemplate.update(INSERT_INTO_GIFT_TAG_SQL, giftID, tagID);
    }

    @Override
    public Optional<Tag> getTagByID(int id) {
        final int FIRST_ELEMENT_INDEX = 0;

        List<Tag> tagList = jdbcTemplate.query(GET_TAG_BY_ID_SQL,
                new Object[]{id}, tagMapper);

        return tagList.isEmpty() ? Optional.empty() : Optional.of(tagList.get(FIRST_ELEMENT_INDEX));
    }

    @Override
    public List<Tag> getTags() {
        return jdbcTemplate.query(GET_TAGS_SQL, tagMapper);
    }

    public List<Tag> getTagListByGiftCertificateID(int id) {
        return jdbcTemplate.query(SELECT_BY_TAG_NAME_SQL, new Object[]{id}, tagMapper);
    }

    @Override
    public Optional<Tag> getTagByName(String name) {
        final String SELECT_BY_TAG_NAME_SQL = "SELECT * FROM tag WHERE (name = ?)";
        final int FIRST_ELEMENT_INDEX = 0;

        List<Tag> tagList = jdbcTemplate.query(SELECT_BY_TAG_NAME_SQL,
                new Object[]{name}, tagMapper);

        return tagList.isEmpty() ? Optional.empty() : Optional.of(tagList.get(FIRST_ELEMENT_INDEX));
    }
}
