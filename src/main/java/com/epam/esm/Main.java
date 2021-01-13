import bean.GiftCertificate;
import dao.GiftCertificateDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        GiftCertificateDAO giftCertificateDAO =
                (GiftCertificateDAO) context.getBean("giftCertificateDAO");
        giftCertificateDAO.create("MyGift","GiftForMe",15,10);
        List<GiftCertificate> list = giftCertificateDAO.getCertificates();
        for(GiftCertificate element : list) {
            System.out.println(element);
        }

        System.out.println("ready");
    }

}
