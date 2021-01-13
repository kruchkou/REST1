package dao.impl;

import bean.GiftCertificate;
import bean.Tag;
import dao.TagDAO;
import dao.mapper.GiftCertificateMapper;
import dao.mapper.TagMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class TagDAOImpl implements TagDAO {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public TagDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String name) {
        final String CREATE_SQL = "INSERT INTO tag(name) VALUES (?)";
        jdbcTemplate.update(CREATE_SQL,name);
    }

    @Override
    public void delete(int id) {
        final String DELETE_SQL = "DELETE FROM tag WHERE id = ?";
        jdbcTemplate.update(DELETE_SQL,id);
    }

    @Override
    public Tag getTagByID(int id) {
        final String GET_TAG_BY_ID_SQL = "SELECT * FROM tag WHERE id = ?";

        final int FIRST_ELEMENT_INDEX = 0;
        final int EMPTY_LIST_SIZE = 0;

        List<Tag> resultList = jdbcTemplate.query(GET_TAG_BY_ID_SQL,
                new Object[]{id}, new TagMapper());

        if (resultList.size() == EMPTY_LIST_SIZE) {
            return null;
        } else {
            return resultList.get(FIRST_ELEMENT_INDEX);
        }
    }

    @Override
    public List<Tag> getTags() {
        final String GET_TAGS_SQL = "SELECT * FROM tag";

        return jdbcTemplate.query(GET_TAGS_SQL, new TagMapper());
    }

    @Override
    public List<Tag> getTagsByName(String name) {
        final String SELECT_BY_TAG_NAME_SQL = "SELECT * FROM tag WHERE (name = ?)";

        return jdbcTemplate.query(SELECT_BY_TAG_NAME_SQL, new Object[]{name}, new TagMapper());
    }
}
