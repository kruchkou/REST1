package dao.impl;

import bean.Tag;
import dao.TagDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class TagDAOImpl implements TagDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public TagDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void create(Tag tag) {

    }

    @Override
    public void remove(Tag tag) {

    }

    @Override
    public Tag getTagByID(int id) {
        return null;
    }

    @Override
    public List<Tag> getTags() {
        return null;
    }

    @Override
    public List<Tag> getTagsByName(String name) {
        return null;
    }
}
