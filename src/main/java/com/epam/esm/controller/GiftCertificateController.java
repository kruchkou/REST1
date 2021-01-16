package com.epam.esm.controller;

import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.model.util.GetGiftCertificateQueryParameter;
import com.epam.esm.model.util.UpdateGiftCertificateQueryParameter;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/gift-certificates")
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    private final static String EMPTY_STRING = "";

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

    @GetMapping(params = {"name", "description", "tagName", "sortBy", "sortOrientation"})
    public List<GiftCertificateDTO> getGiftCertificateByAllParams(
            @RequestParam(value = "tagName", defaultValue = EMPTY_STRING) String tagName,
            @RequestParam(value = "name", defaultValue = EMPTY_STRING) String name,
            @RequestParam(value = "description", defaultValue = EMPTY_STRING) String description,
            @RequestParam(value = "sortBy", defaultValue = EMPTY_STRING) String sortBy,
            @RequestParam(value = "sortOrientation", defaultValue = EMPTY_STRING) String sortOrientation) {

        GetGiftCertificateQueryParameter giftCertificateQueryParameter = new GetGiftCertificateQueryParameter();

        if (!tagName.equals(EMPTY_STRING)) {
            giftCertificateQueryParameter.setTagName(tagName);
        }
        if (!name.equals(EMPTY_STRING)) {
            giftCertificateQueryParameter.setName(name);
        }
        if (!description.equals(EMPTY_STRING)) {
            giftCertificateQueryParameter.setDescription(description);
        }
        if (!sortBy.equals(EMPTY_STRING)) {
            giftCertificateQueryParameter.setSortBy(sortBy);
        }
        if (!sortOrientation.equals(EMPTY_STRING)) {
            giftCertificateQueryParameter.setSortOrientation(sortOrientation);
        }

        return giftCertificateService.getCertificates(giftCertificateQueryParameter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificateDTO newGiftCertificate(@RequestBody GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateService.createGiftCertificate(giftCertificateDTO);
    }

    @PutMapping("/{id}")
    public GiftCertificateDTO updateGiftCertificate(@RequestBody UpdateGiftCertificateQueryParameter updateParameter, @PathVariable int id) {
        return giftCertificateService.updateCertificate(updateParameter, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        giftCertificateService.deleteCertificate(id);
    }

}
