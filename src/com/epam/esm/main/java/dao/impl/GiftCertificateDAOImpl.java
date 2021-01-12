package dao.impl;

import bean.GiftCertificate;
import dao.GiftCertificateDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class GiftCertificateDAOImpl implements GiftCertificateDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public GiftCertificateDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(String name, String description, int price, int duration) {
        final String CREATE_SQL = "INSERT INTO gift_certificate (name,description,price,duration,create_date) VALUES (?,?,?,?,?)";

    }

    @Override
    public void delete(int id) {
        final String DELETE_SQL = "DELETE FROM gift_certificate WHERE id = ?";
    }

    @Override
    public GiftCertificate getCertificateByID(int id) {
        final String SELECT_GIFT_BY_ID_SQL = "SELECT * FROM gift_certificate WHERE (id = ?)";
        final String SELECT_TAGS_BY_GIFT_ID = "SELECT id,name FROM tag tags JOIN gift_tag link ON tags.id = link.tag WHERE link.gift = ?";

        return null;
    }

    @Override
    public List<GiftCertificate> getCertificates() {
        final String SELECT_BY_ID_SQL = "SELECT * FROM gift_certificate";

        return null;
    }

    @Override
    public List<GiftCertificate> getCertificatesByTagName(String tagName) {
        final String SELECT_BY_ID_SQL = "SELECT * FROM gift_certificate gift JOIN WHERE (tag.name = ?)";

        return null;
    }

    @Override
    public List<GiftCertificate> getCertificatesByNameOrDescription(String name) {
        return null;
    }
}
