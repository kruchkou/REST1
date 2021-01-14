package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dto.GiftCertificateDTO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.validator.GiftCertificateValidator;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDAO giftCertificateDAO;
    private final TagService tagService;
    private static final GiftCertificateValidator giftCertificateValidator = new GiftCertificateValidator();

    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO, TagService tagService) {
        this.giftCertificateDAO = giftCertificateDAO;
        this.tagService = tagService;
    }

    @Override
    public int deleteCertificate(int id) {
        return giftCertificateDAO.deleteCertificate(id);
    }

    @Override
    public int updateCertificate(GiftCertificateDTO giftCertificateDTO, int id) {
        return 0;
    }

    @Override
    @Transactional
    public GiftCertificate createGiftCertificate(GiftCertificateDTO giftCertificateDTO) throws ServiceException {
        if(giftCertificateValidator.validateForCreate(giftCertificateDTO)) {
            List<Tag> tagList = new ArrayList<>();

            final String name = giftCertificateDTO.getName();
            final String desc = giftCertificateDTO.getDescription();
            final int price = giftCertificateDTO.getPrice();
            final int duration = giftCertificateDTO.getDuration();

            List<String> tagNames = giftCertificateDTO.getTagNames();
            for(String tagName : tagNames) {
                Optional<Tag> tag = tagService.getTagByName(name);

                if (!tag.isPresent()) {
                    //tag = tagService.createTag(tagName);
                }
                //tagList.add(tag);
            }

            GiftCertificate newGiftCertificate = giftCertificateDAO.createGiftCertificate(name,desc,price,duration);
            newGiftCertificate.setTagList(tagList);
            return newGiftCertificate;
        }
        else {
            throw new ServiceException("Data didn't passed validation");
        }
    }

    @Override
    public List<GiftCertificate> getCertificates() {
        return giftCertificateDAO.getCertificates();
    }

    @Override
    public List<GiftCertificate> getCertificatesByTagName(String tagName) {
        return giftCertificateDAO.getCertificatesByTagName(tagName);
    }

    @Override
    public Optional<GiftCertificate> getGiftCertificateByID(int id) {
        return giftCertificateDAO.getCertificateByID(id);
    }

    @Override
    public List<GiftCertificate> getCertificatesByNameOrDescription(String searchText) {
        return giftCertificateDAO.getCertificatesByNameOrDescription(searchText);
    }
}
