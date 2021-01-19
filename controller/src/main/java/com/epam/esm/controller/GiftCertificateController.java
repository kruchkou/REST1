package com.epam.esm.controller;

import com.epam.esm.service.model.dto.GiftCertificateDTO;
import com.epam.esm.service.model.dto.TagDTO;
import com.epam.esm.repository.model.util.GetGiftCertificateQueryParameter;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/gift-certificates")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;
    private final TagService tagService;

    private final static String EMPTY_STRING = "";

    @Autowired
    public GiftCertificateController(GiftCertificateService giftCertificateService, TagService tagService) {
        this.giftCertificateService = giftCertificateService;
        this.tagService = tagService;
    }


    @GetMapping("/{id}")
    public GiftCertificateDTO getGiftCertificateByID(@PathVariable int id) {
        return giftCertificateService.getGiftCertificateByID(id);
    }

    @GetMapping("/{id}/tags")
    public List<TagDTO> getTagListByGiftCertificateID(@PathVariable int id) {
        return tagService.getTagListByGiftCertificateID(id);
    }

    @GetMapping
    public List<GiftCertificateDTO> getGiftCertificateByAllParams(
            @RequestParam(value = "tagName", defaultValue = EMPTY_STRING) String tagName,
            @RequestParam(value = "name", defaultValue = EMPTY_STRING) String name,
            @RequestParam(value = "description", defaultValue = EMPTY_STRING) String description,
            @RequestParam(value = "sortBy", defaultValue = EMPTY_STRING) String sortBy,
            @RequestParam(value = "sortOrientation", defaultValue = EMPTY_STRING) String sortOrientation) {

        GetGiftCertificateQueryParameter giftCertificateQueryParameter = new GetGiftCertificateQueryParameter(
                tagName, name, description, sortBy, sortOrientation);

        return giftCertificateService.getCertificates(giftCertificateQueryParameter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDTO createGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateService.createGiftCertificate(giftCertificateDTO);
    }

    @PutMapping("/{id}")
    public GiftCertificateDTO updateGiftCertificate(
            @RequestBody GiftCertificateDTO giftCertificateDTO, @PathVariable int id) {

        return giftCertificateService.updateCertificate(giftCertificateDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        giftCertificateService.deleteCertificate(id);
    }

}
