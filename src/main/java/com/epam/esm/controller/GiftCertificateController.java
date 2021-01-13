package controller;

import bean.GiftCertificate;
import dao.GiftCertificateDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GiftCertificateController {

    private final GiftCertificateDAO giftCertificateDAO;

    public GiftCertificateController(GiftCertificateDAO giftCertificateDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
    }

    @GetMapping("/giftCertificates")
    List<GiftCertificate> getCertificates() {
        return giftCertificateDAO.getCertificates();
    }

    @PostMapping("/giftCertificates")
    GiftCertificate newGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        return giftCertificateDAO.create(giftCertificate.getName(),giftCertificate.getDescription(),giftCertificate.getPrice(),giftCertificate.getDuration());
    }

    @GetMapping("/giftCertificates/{id}")
    GiftCertificate getGiftCertificateByID(@PathVariable int id) {
        return giftCertificateDAO.getCertificateByID(id);
    }

    @PutMapping("/giftCertificates/{id}")
    GiftCertificate replaceEmployee(@RequestBody GiftCertificate newGift, @PathVariable int id) {
        giftCertificateDAO.update(newGift);
        return getGiftCertificateByID(newGift.getId());
    }

    @DeleteMapping("/giftCertificates/{id}")
    void deleteEmployee(@PathVariable int id) {
        giftCertificateDAO.delete(id);
    }

}
