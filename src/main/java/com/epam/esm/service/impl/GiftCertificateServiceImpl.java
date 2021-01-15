package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.exception.GiftCertificateDataValidationException;
import com.epam.esm.service.exception.GiftCertificateNotFoundException;
import com.epam.esm.service.util.mapper.EntityDTOGiftCertificateMapper;
import com.epam.esm.service.validator.GiftCertificateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDAO giftCertificateDAO;
    private final TagDAO tagDAO;

    private static final String NO_GIFT_CERTIFICATE_WITH_ID_FOUND = "No certificate with %d id found";
    private static final String NO_GIFT_CERTIFICATES_FOUND = "No certificates found";
    private static final String DATA_VALIDATION_EXCEPTION = "Data didn't passed validation";

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO, TagDAO tagDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
        this.tagDAO = tagDAO;
    }

    @Override
    @Transactional
    public void deleteCertificate(int id) {
        if (!giftCertificateDAO.getGiftCertificateByID(id).isPresent()) {
            throw new GiftCertificateNotFoundException();
        }
        giftCertificateDAO.deleteGiftCertificate(id);
    }

    @Override
    public GiftCertificateDTO getGiftCertificateByID(int id) {
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateDAO.getGiftCertificateByID(id);

        if (!optionalGiftCertificate.isPresent()) {
            throw new GiftCertificateNotFoundException(String.format(NO_GIFT_CERTIFICATE_WITH_ID_FOUND, id));
        }

        GiftCertificate giftCertificate = optionalGiftCertificate.orElseThrow(() ->
                new GiftCertificateNotFoundException(String.format(NO_GIFT_CERTIFICATE_WITH_ID_FOUND, id)));
        return EntityDTOGiftCertificateMapper.toDTO(giftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDTO updateCertificate(GiftCertificateDTO giftCertificateDTO, int id) {

        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateDAO.getGiftCertificateByID(id);

        if(!optionalGiftCertificate.isPresent()) {
            throw new GiftCertificateNotFoundException(String.format(NO_GIFT_CERTIFICATE_WITH_ID_FOUND, id));
        }

        GiftCertificate updatedGiftCertificate = EntityDTOGiftCertificateMapper.toEntity(giftCertificateDTO);
        updatedGiftCertificate = giftCertificateDAO.updateGiftCertificate(updatedGiftCertificate, id);

        return EntityDTOGiftCertificateMapper.toDTO(updatedGiftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDTO createGiftCertificate(GiftCertificateDTO giftCertificateDTO) {

        if (!GiftCertificateValidator.validateForCreate(giftCertificateDTO)) {
            throw new GiftCertificateDataValidationException(DATA_VALIDATION_EXCEPTION);
        }

        final String name = giftCertificateDTO.getName();
        final String desc = giftCertificateDTO.getDescription();
        final int price = giftCertificateDTO.getPrice();
        final int duration = giftCertificateDTO.getDuration();

        GiftCertificate newGiftCertificate = giftCertificateDAO.createGiftCertificate(name, desc, price, duration);

        createTagsIfNotFoundAndInsert(newGiftCertificate.getId(), giftCertificateDTO.getTagNames());

        return EntityDTOGiftCertificateMapper.toDTO(newGiftCertificate);
    }

    @Override
    public List<GiftCertificateDTO> getCertificates() {
        List<GiftCertificateDTO> giftCertificateDTOList = new ArrayList<>();
        List<GiftCertificate> giftCertificateList = giftCertificateDAO.getGiftCertificates();

        if (giftCertificateList.isEmpty()) {
            throw new GiftCertificateNotFoundException(NO_GIFT_CERTIFICATES_FOUND);
        }

        for (GiftCertificate giftCertificate : giftCertificateList) {
            giftCertificateDTOList.add(EntityDTOGiftCertificateMapper.toDTO(giftCertificate));
        }

        return giftCertificateDTOList;
    }

    @Override
    public List<GiftCertificateDTO> getCertificatesByTagName(String tagName) {
        List<GiftCertificate> giftCertificateList = giftCertificateDAO.getGiftCertificatesByTagName(tagName);

        if (giftCertificateList.isEmpty()) {
            throw new GiftCertificateNotFoundException(NO_GIFT_CERTIFICATES_FOUND);
        }

        return transformToDTOAndLoadTags(giftCertificateList);
    }

    @Override
    public List<GiftCertificateDTO> getCertificatesByNameOrDescription(String searchText) {
        List<GiftCertificate> giftCertificateList = giftCertificateDAO.getGiftCertificatesByNameOrDescription(searchText);

        if (giftCertificateList.isEmpty()) {
            throw new GiftCertificateNotFoundException(NO_GIFT_CERTIFICATES_FOUND);
        }

        return transformToDTOAndLoadTags(giftCertificateList);
    }


    private List<GiftCertificateDTO> transformToDTOAndLoadTags(List<GiftCertificate> giftCertificateList) {

        List<GiftCertificateDTO> giftCertificateDTOList = new ArrayList<>();

        for (GiftCertificate giftCertificate : giftCertificateList) {
            List<Tag> tagList = tagDAO.getTagListByGiftCertificateID(giftCertificate.getId());
            List<String> tagNamesList = getTagNamesList(tagList);
            GiftCertificateDTO giftCertificateDTO = EntityDTOGiftCertificateMapper.toDTO(giftCertificate);

            giftCertificateDTO.setTagNames(tagNamesList);
            giftCertificateDTOList.add(giftCertificateDTO);
        }
        return giftCertificateDTOList;
    }

    private List<String> getTagNamesList(List<Tag> tagList) {
        List<String> tagNamesList = new ArrayList<>();

        for (Tag tag : tagList) {
            tagNamesList.add(tag.getName());
        }
        return tagNamesList;
    }

    private void createTagsIfNotFoundAndInsert(int giftID, List<String> tagNamesList) {
        for (String tagName : tagNamesList) {
            Optional<Tag> optionalTag = tagDAO.getTagByName(tagName);
            Tag tag = optionalTag.orElseGet(() -> tagDAO.createTag(tagName));

            tagDAO.insertGiftTag(giftID, tag.getId());
        }
    }

}
