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
import com.epam.esm.service.exception.impl.GiftCertificateDataValidationException;
import com.epam.esm.service.exception.impl.GiftCertificateNotFoundException;
import com.epam.esm.service.util.mapper.EntityDTOGiftCertificateMapper;
import com.epam.esm.service.validator.GiftCertificateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDAO giftCertificateDAO;
    private final TagDAO tagDAO;

    private final static String NO_GIFT_CERTIFICATE_WITH_ID_FOUND = "No certificate with id: %d found";
    private final static String DATA_VALIDATION_EXCEPTION = "Data didn't passed validation";

    private final static String ERROR_CODE_GIFT_VALIDATION_FAILED = "0101";
    private final static String ERROR_CODE_GIFT_NOT_FOUND_FAILED = "0102404%d";

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDAO giftCertificateDAO, TagDAO tagDAO) {
        this.giftCertificateDAO = giftCertificateDAO;
        this.tagDAO = tagDAO;
    }

    @Override
    @Transactional
    public void deleteCertificate(int id) {
        if (!giftCertificateDAO.getGiftCertificateByID(id).isPresent()) {
            throw new GiftCertificateNotFoundException(
                    String.format(NO_GIFT_CERTIFICATE_WITH_ID_FOUND, id),
                    String.format(ERROR_CODE_GIFT_NOT_FOUND_FAILED, id));
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

        GiftCertificate giftCertificate = optionalGiftCertificate.orElseThrow(() -> new GiftCertificateNotFoundException(
                String.format(NO_GIFT_CERTIFICATE_WITH_ID_FOUND, id),
                String.format(ERROR_CODE_GIFT_NOT_FOUND_FAILED, id)));

        return loadTagsAndTransformToDTO(giftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDTO updateCertificate(GiftCertificateDTO giftCertificateDTO, int id) {
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateDAO.getGiftCertificateByID(id);

        if (!optionalGiftCertificate.isPresent()) {
            throw new GiftCertificateNotFoundException(
                    String.format(NO_GIFT_CERTIFICATE_WITH_ID_FOUND, id),
                    String.format(ERROR_CODE_GIFT_NOT_FOUND_FAILED, id));
        }

        giftCertificateDTO.setId(id);

        UpdateGiftCertificateSQLBuilder updateBuilder = UpdateGiftCertificateSQLBuilder.getInstance();
        GiftCertificate giftCertificate = EntityDTOGiftCertificateMapper.toEntity(giftCertificateDTO);
        GiftCertificateSQL updateGiftCertificateSQL = updateBuilder.build(giftCertificate);

        GiftCertificate updatedGiftCertificate = giftCertificateDAO.updateGiftCertificate(updateGiftCertificateSQL, id);
        giftCertificateDAO.deleteLinkWithTagsByID(id);
        createTagsIfNotFoundAndInsert(id, giftCertificateDTO.getTagNames());

        return loadTagsAndTransformToDTO(updatedGiftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDTO createGiftCertificate(GiftCertificateDTO giftCertificateDTO) {

        if (!GiftCertificateValidator.validateForCreate(giftCertificateDTO)) {
            throw new GiftCertificateDataValidationException(
                    DATA_VALIDATION_EXCEPTION, ERROR_CODE_GIFT_VALIDATION_FAILED);
        }

        GiftCertificate giftCertificate = EntityDTOGiftCertificateMapper.toEntity(giftCertificateDTO);

        GiftCertificate newGiftCertificate = giftCertificateDAO.createGiftCertificate(giftCertificate);

        createTagsIfNotFoundAndInsert(newGiftCertificate.getId(), giftCertificateDTO.getTagNames());

        return loadTagsAndTransformToDTO(newGiftCertificate);
    }

    @Override
    public List<GiftCertificateDTO> getCertificates() {
        List<GiftCertificateDTO> giftCertificateDTOList = new ArrayList<>();
        List<GiftCertificate> giftCertificateList = giftCertificateDAO.getGiftCertificates();

        giftCertificateList.forEach(giftCertificate ->
                giftCertificateDTOList.add(loadTagsAndTransformToDTO(giftCertificate)));

        return giftCertificateDTOList;
    }

    @Override
    public List<GiftCertificateDTO> getCertificates(GetGiftCertificateQueryParameter giftCertificateQueryParameter) {
        if (giftCertificateQueryParameter.isEmpty()) {
            return getCertificates();
        }
        GiftCertificateSQL giftCertificateRequest = GetGiftCertificateSQLBuilder.getInstance().build(giftCertificateQueryParameter);

        List<GiftCertificate> giftCertificateList = giftCertificateDAO.getGiftCertificates(giftCertificateRequest);
        return loadTagsAndTransformToDTO(giftCertificateList);
    }

    private List<GiftCertificateDTO> loadTagsAndTransformToDTO(List<GiftCertificate> giftCertificateList) {
        List<GiftCertificateDTO> giftCertificateDTOList = new ArrayList<>();

        giftCertificateList.forEach(giftCertificate ->
                giftCertificateDTOList.add(loadTagsAndTransformToDTO(giftCertificate)));

        return giftCertificateDTOList;
    }

    private GiftCertificateDTO loadTagsAndTransformToDTO(GiftCertificate giftCertificate) {
        List<Tag> tagList = tagDAO.getTagListByGiftCertificateID(giftCertificate.getId());
        giftCertificate.setTagList(tagList);

        return EntityDTOGiftCertificateMapper.toDTO(giftCertificate);
    }

    private void createTagsIfNotFoundAndInsert(int giftID, List<String> tagNamesList) {
        if (tagNamesList != null) {
            tagNamesList.forEach(tagName -> {
                Optional<Tag> optionalTag = tagDAO.getTagByName(tagName);
                Tag tag = optionalTag.orElseGet(() -> tagDAO.createTag(tagName));

                giftCertificateDAO.insertGiftTag(giftID, tag.getId());
            });
        }
    }

}
