package com.epam.esm.controller;

import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("giftCertificates")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    List<GiftCertificateDTO> getCertificates() {
        return giftCertificateService.getCertificates();
    }

    @GetMapping("/{id}")
    GiftCertificateDTO getGiftCertificateByID(@PathVariable int id) {
        return giftCertificateService.getGiftCertificateByID(id);
    }

    @GetMapping(params = {"tagName"})
    List<GiftCertificateDTO> getGiftCertificateByTagName(@RequestParam(value = "tagNames") String tagNames) {
        return giftCertificateService.getCertificatesByTagName(tagNames);
    }

    @GetMapping(params = {"name"})
    List<GiftCertificateDTO> getGiftCertificateByNameOrDesc(@RequestParam(value = "name") String name) {
        System.out.println(name);
        return giftCertificateService.getCertificatesByNameOrDescription(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    GiftCertificateDTO newGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        System.out.println(giftCertificateDTO);
        return giftCertificateService.createGiftCertificate(giftCertificateDTO);
    }

    @PutMapping("/{id}")
    GiftCertificateDTO updateGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateDTO, @PathVariable int id) {
        return giftCertificateService.updateCertificate(giftCertificateDTO,id);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable int id) {
        giftCertificateService.deleteCertificate(id);
    }

}
