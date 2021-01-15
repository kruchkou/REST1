package com.epam.esm;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.dao.GiftCertificateDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        GiftCertificateDAO giftCertificateDAO =
                (GiftCertificateDAO) context.getBean("giftCertificateDAO");
        giftCertificateDAO.createGiftCertificate("MyGift","GiftForMe",15,10);
        List<GiftCertificate> list = giftCertificateDAO.getGiftCertificates();
        for(GiftCertificate element : list) {
            System.out.println(element);
        }

        System.out.println("ready");
    }

}
