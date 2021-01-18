package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.util.GetGiftCertificateSQLBuilder;
import com.epam.esm.dao.util.UpdateGiftCertificateSQLBuilder;
import com.epam.esm.model.dto.GiftCertificateDTO;
import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.model.util.GetGiftCertificateQueryParameter;
import com.epam.esm.model.util.GiftCertificateSQL;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.exception.GiftCertificateDataValidationException;
import com.epam.esm.service.exception.GiftCertificateNotFoundException;
import com.epam.esm.service.util.mapper.EntityDTOGiftCertificateMapper;
import com.epam.esm.service.validator.GiftCertificateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDAO giftCertificateDAO;
    private final TagDAO tagDAO;

    private static final String NO_GIFT_CERTIFICATE_WITH_ID_FOUND = "No certificate with id: %d found";
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

        List<Tag> tagList = tagDAO.getTagListByGiftCertificateID(id);
        for (Tag tag : tagList) {
            tagDAO.deleteTag(tag.getId());
        }

        giftCertificateDAO.deleteGiftCertificate(id);
    }

    @Override
    public GiftCertificateDTO getGiftCertificateByID(int id) {
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateDAO.getGiftCertificateByID(id);

        GiftCertificate giftCertificate = optionalGiftCertificate.orElseThrow(() ->
                new GiftCertificateNotFoundException(String.format(NO_GIFT_CERTIFICATE_WITH_ID_FOUND, id)));

        return transformToDTOAndLoadTags(giftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDTO updateCertificate(GiftCertificateDTO giftCertificateDTO, int id) {
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateDAO.getGiftCertificateByID(id);

        giftCertificateDTO.setId(id);

        if (!optionalGiftCertificate.isPresent()) {
            throw new GiftCertificateNotFoundException(String.format(NO_GIFT_CERTIFICATE_WITH_ID_FOUND, id));
        }

        UpdateGiftCertificateSQLBuilder updateBuilder = UpdateGiftCertificateSQLBuilder.getInstance();
        giftCertificateDTO.setLastUpdateDate(LocalDateTime.now());
        GiftCertificate giftCertificate = EntityDTOGiftCertificateMapper.toEntity(giftCertificateDTO);
        GiftCertificateSQL updateGiftCertificateSQL = updateBuilder.build(giftCertificate);

        GiftCertificate updatedGiftCertificate = giftCertificateDAO.updateGiftCertificate(updateGiftCertificateSQL, id);
        giftCertificateDAO.deleteLinkWithTagsByID(id);
        createTagsIfNotFoundAndInsert(id, giftCertificateDTO.getTagNames());

        return transformToDTOAndLoadTags(updatedGiftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDTO createGiftCertificate(GiftCertificateDTO giftCertificateDTO) {

        if (!GiftCertificateValidator.validateForCreate(giftCertificateDTO)) {
            throw new GiftCertificateDataValidationException(DATA_VALIDATION_EXCEPTION);
        }
        GiftCertificate giftCertificate = EntityDTOGiftCertificateMapper.toEntity(giftCertificateDTO);

        GiftCertificate newGiftCertificate = giftCertificateDAO.createGiftCertificate(giftCertificate);

        createTagsIfNotFoundAndInsert(newGiftCertificate.getId(), giftCertificateDTO.getTagNames());

        return transformToDTOAndLoadTags(newGiftCertificate);
    }

    @Override
    public List<GiftCertificateDTO> getCertificates() {
        List<GiftCertificateDTO> giftCertificateDTOList = new ArrayList<>();
        List<GiftCertificate> giftCertificateList = giftCertificateDAO.getGiftCertificates();

        giftCertificateList.forEach(giftCertificate ->
                giftCertificateDTOList.add(transformToDTOAndLoadTags(giftCertificate)));

        return giftCertificateDTOList;
    }

    @Override
    public List<GiftCertificateDTO> getCertificates(GetGiftCertificateQueryParameter giftCertificateQueryParameter) {
        if (giftCertificateQueryParameter.isEmpty()) {
            return getCertificates();
        }
        GiftCertificateSQL giftCertificateRequest = GetGiftCertificateSQLBuilder.getInstance().build(giftCertificateQueryParameter);

        List<GiftCertificate> giftCertificateList = giftCertificateDAO.getGiftCertificates(giftCertificateRequest);
        return transformToDTOAndLoadTags(giftCertificateList);
    }

    @Override
    public List<GiftCertificateDTO> getCertificatesByTagName(String tagName) {
        List<GiftCertificate> giftCertificateList = giftCertificateDAO.getGiftCertificatesByTagName(tagName);

        return transformToDTOAndLoadTags(giftCertificateList);
    }

    @Override
    public List<GiftCertificateDTO> getCertificatesByNameOrDescription(String searchText) {
        List<GiftCertificate> giftCertificateList = giftCertificateDAO.getGiftCertificatesByNameOrDescription(searchText);

        return transformToDTOAndLoadTags(giftCertificateList);
    }


    private List<GiftCertificateDTO> transformToDTOAndLoadTags(List<GiftCertificate> giftCertificateList) {
        List<GiftCertificateDTO> giftCertificateDTOList = new ArrayList<>();

        giftCertificateList.forEach(giftCertificate ->
                giftCertificateDTOList.add(transformToDTOAndLoadTags(giftCertificate)));

        return giftCertificateDTOList;
    }

    private GiftCertificateDTO transformToDTOAndLoadTags(GiftCertificate giftCertificate) {
        List<Tag> tagList = tagDAO.getTagListByGiftCertificateID(giftCertificate.getId());
        List<String> tagNamesList = getTagNamesList(tagList);
        GiftCertificateDTO giftCertificateDTO = EntityDTOGiftCertificateMapper.toDTO(giftCertificate);

        giftCertificateDTO.setTagNames(tagNamesList);
        return giftCertificateDTO;
    }

    private List<String> getTagNamesList(List<Tag> tagList) {
        List<String> tagNamesList = new ArrayList<>();
        tagList.forEach(tag -> tagNamesList.add(tag.getName()));

        return tagNamesList;
    }

    private void createTagsIfNotFoundAndInsert(int giftID, List<String> tagNamesList) {
        if (tagNamesList != null) {
            tagNamesList.forEach(tagName -> {
                Optional<Tag> optionalTag = tagDAO.getTagByName(tagName);
                Tag tag = optionalTag.orElseGet(() -> tagDAO.createTag(tagName));

                tagDAO.insertGiftTag(giftID, tag.getId());
            });
        }
    }

}
