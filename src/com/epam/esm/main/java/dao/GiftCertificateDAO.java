package dao;

import bean.GiftCertificate;
import bean.Tag;

import java.util.List;

public interface GiftCertificateDAO {

    public int delete(int id);

    public int update(GiftCertificate updatedCertificate);

    public GiftCertificate create(String name, String description, int price, int duration);

    public GiftCertificate getCertificateByID(int id);

    public List<GiftCertificate> getCertificates();

    public List<GiftCertificate> getCertificatesByTagName(String tagName);

    public List<GiftCertificate> getCertificatesByNameOrDescription(String searchText);

}
