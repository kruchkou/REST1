package com.epam.esm.controller;

import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/giftCertificates")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService) {
        this.giftCertificateService = giftCertificateService;
    }

    @GetMapping
    public List<GiftCertificateDTO> getCertificates() {
        return giftCertificateService.getCertificates();
    }

    @GetMapping("/{id}")
    public GiftCertificateDTO getGiftCertificateByID(@PathVariable int id) {
        return giftCertificateService.getGiftCertificateByID(id);
    }

    @GetMapping(params = {"tagName"})
    public List<GiftCertificateDTO> getGiftCertificateByTagName(@RequestParam(value = "tagNames") String tagNames) {
        return giftCertificateService.getCertificatesByTagName(tagNames);
    }

    @GetMapping(params = {"name"})
    public List<GiftCertificateDTO> getGiftCertificateByNameOrDesc(@RequestParam(value = "name") String name) {
        return giftCertificateService.getCertificatesByNameOrDescription(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDTO newGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateService.createGiftCertificate(giftCertificateDTO);
    }

    @PutMapping("/{id}")
    public GiftCertificateDTO updateGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateDTO, @PathVariable int id) {
        return giftCertificateService.updateCertificate(giftCertificateDTO,id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        giftCertificateService.deleteCertificate(id);
    }

}
