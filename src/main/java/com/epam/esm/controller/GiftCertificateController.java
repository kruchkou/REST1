package com.epam.esm.controller;

import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.dao.GiftCertificateDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("giftCertificates")
public class GiftCertificateController {

    private final GiftCertificateDAO giftCertificateDAO;

    public GiftCertificateController(GiftCertificateDAO giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }

    @GetMapping
    List<GiftCertificate> getCertificates() {
        return giftCertificateDAO.getCertificates();
    }

    @PostMapping
    GiftCertificate newGiftCertificate(@RequestBody GiftCertificateDTO giftCertificate) {
        System.out.println(giftCertificate);
        return giftCertificateDAO.createGiftCertificate(giftCertificate.getName(),giftCertificate.getDescription(),giftCertificate.getPrice(),giftCertificate.getDuration());
        //return giftCertificateDAO.getCertificatesByTagName(giftCertificate.getTagNames().get(0)).get(0);
    }

    @GetMapping("/{id}")
    GiftCertificate getGiftCertificateByID(@PathVariable int id) {
        return giftCertificateDAO.getCertificateByID(id);
    }

    @PutMapping("/{id}")
    GiftCertificate replaceEmployee(@RequestBody GiftCertificate newGift, @PathVariable int id) {
        giftCertificateDAO.updateCertificate(newGift);
        return getGiftCertificateByID(newGift.getId());
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable int id) {
        giftCertificateDAO.deleteCertificate(id);
    }

}
