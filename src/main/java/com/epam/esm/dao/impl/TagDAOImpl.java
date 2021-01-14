package com.epam.esm.dao.impl;

import com.epam.esm.dao.mapper.GiftCertificateMapper;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.mapper.TagMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class TagDAOImpl implements TagDAO {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public TagDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public Tag createTag(String name) {
        final String CREATE_SQL = "INSERT INTO tag(name) VALUES (?)";
        jdbcTemplate.update(CREATE_SQL,name);

        return getTagByName(name).get();
    }

    @Override
    public void deleteTag(int id) {
        final String DELETE_SQL = "DELETE FROM tag WHERE id = ?";
        jdbcTemplate.update(DELETE_SQL,id);
    }

    @Override
    public Optional<Tag> getTagByID(int id) {
        final String GET_TAG_BY_ID_SQL = "SELECT * FROM tag WHERE id = ?";

        final int FIRST_ELEMENT_INDEX = 0;

        List<Tag> resultList = jdbcTemplate.query(GET_TAG_BY_ID_SQL,
                new Object[]{id}, new TagMapper());

        if (resultList.isEmpty()) {
            return null;
        } else {
            return Optional.of(resultList.get(FIRST_ELEMENT_INDEX));
        }
    }

    @Override
    public List<Tag> getTags() {
        final String GET_TAGS_SQL = "SELECT * FROM tag";

        return jdbcTemplate.query(GET_TAGS_SQL, new TagMapper());
    }

    @Override
    public Optional<Tag> getTagByName(String name) {
        final String SELECT_BY_TAG_NAME_SQL = "SELECT * FROM tag WHERE (name = ?)";

        final int FIRST_ELEMENT_INDEX = 0;

        List<Tag> resultList = jdbcTemplate.query(SELECT_BY_TAG_NAME_SQL,
                new Object[]{name}, new TagMapper());

            return Optional.of(resultList.get(FIRST_ELEMENT_INDEX));
    }
}
