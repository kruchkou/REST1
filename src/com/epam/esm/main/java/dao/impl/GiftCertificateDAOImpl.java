package dao.impl;

import bean.GiftCertificate;
import dao.GiftCertificateDAO;
import dao.mapper.GiftCertificateMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class GiftCertificateDAOImpl implements GiftCertificateDAO {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    private final static String SELECT_TAGS_BY_GIFT_ID = "SELECT id,name FROM tag tags JOIN gift_tag link ON tags.id = link.tag WHERE link.gift = ?";

    public GiftCertificateDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public GiftCertificate create(String name, String description, int price, int duration) {
        final String CREATE_SQL = "INSERT INTO gift_certificate (name,description,price,duration,create_date) VALUES (?,?,?,?,?)";
        final int NAME_PARAM_ID = 1;
        final int DESC_PARAM_ID = 2;
        final int PRICE_PARAM_ID = 3;
        final int DURATION_PARAM_ID = 4;
        final int CREATE_DATE_PARAM_ID = 5;

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final Date CURRENT_DATETIME = new java.sql.Date(new java.util.Date().getTime());

        //jdbcTemplate.update(CREATE_SQL,name,description,price,duration,CURRENT_DATETIME);

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(NAME_PARAM_ID, name);
            ps.setString(DESC_PARAM_ID, description);
            ps.setInt(PRICE_PARAM_ID, price);
            ps.setInt(DURATION_PARAM_ID, duration);
            ps.setDate(CREATE_DATE_PARAM_ID, CURRENT_DATETIME);
            return ps;
        }, keyHolder);

        return getCertificateByID(keyHolder.getKey().intValue());
    }

    @Override
    public int update(GiftCertificate updatedCertificate) {
        final Date CURRENT_DATETIME = new java.sql.Date(new java.util.Date().getTime());
        final String UPDATE_SQL = "UPDATE gift_certificate SET name = ?, description = ?, price = ?, duration = ?, last_update_date = ? WHERE id = ?";
        return jdbcTemplate.update(UPDATE_SQL,
                updatedCertificate.getName(),
                updatedCertificate.getDescription(),
                updatedCertificate.getPrice(),
                updatedCertificate.getDuration(),
                CURRENT_DATETIME,
                updatedCertificate.getId());
    }

    @Override
    public int delete(int id) {
        final String DELETE_SQL = "DELETE FROM gift_certificate WHERE id = ?";

        return jdbcTemplate.update(DELETE_SQL, id);
    }

    @Override
    public GiftCertificate getCertificateByID(int id) {
        final String SELECT_GIFT_BY_ID_SQL = "SELECT * FROM gift_certificate WHERE (id = ?)";
        final int FIRST_ELEMENT_INDEX = 0;
        final int EMPTY_LIST_SIZE = 0;

        List<GiftCertificate> resultList = jdbcTemplate.query(SELECT_GIFT_BY_ID_SQL,
                new Object[]{id}, new GiftCertificateMapper());

        if (resultList.size() == EMPTY_LIST_SIZE) {
            return null;
        } else {
            return resultList.get(FIRST_ELEMENT_INDEX);
        }
    }

    @Override
    public List<GiftCertificate> getCertificates() {
        final String SELECT_ALL_SQL = "SELECT * FROM gift_certificate";

        return jdbcTemplate.query(SELECT_ALL_SQL, new GiftCertificateMapper());
    }

    @Override
    public List<GiftCertificate> getCertificatesByTagName(String tagName) {
        final String SELECT_BY_TAG_NAME_SQL = "SELECT * FROM gift_certificate gifts " +
                "INNER JOIN gift_tag link ON gifts.id = link.gift " +
                "INNER JOIN tag tags ON link.tag = tags.id " +
                "WHERE (tags.name = ?)";

        return jdbcTemplate.query(SELECT_BY_TAG_NAME_SQL, new Object[]{tagName}, new GiftCertificateMapper());
    }

    @Override
    public List<GiftCertificate> getCertificatesByNameOrDescription(String searchText) {
        final String SELECT_BY_NAME_OR_DESCRIPTION_SQL = "SELECT * FROM gift_certificate gift " +
                "WHERE (name REGEXP ? OR description REGEXP ?)";

        return jdbcTemplate.query(SELECT_BY_NAME_OR_DESCRIPTION_SQL, new Object[]{searchText, searchText}, new GiftCertificateMapper());
    }

}
