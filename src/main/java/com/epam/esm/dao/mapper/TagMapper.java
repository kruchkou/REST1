package com.epam.esm.dao.mapper;

import com.epam.esm.model.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tag tag = new Tag();
        tag.setId(rs.getInt(ParamColumn.ID));
        tag.setName(rs.getString(ParamColumn.NAME));

        return tag;
    }

    private static class ParamColumn {
        private static final String ID = "id";
        private static final String NAME = "name";
    }
}
