package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.dto.TagDTO;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exception.impl.TagDataValidationException;
import com.epam.esm.service.exception.impl.TagNotFoundException;
import com.epam.esm.service.util.mapper.EntityDTOTagMapper;
import com.epam.esm.service.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagDAO tagDAO;

    private final static String NO_TAG_WITH_ID_FOUND = "No tag with id: %d found";
    private final static String NO_TAG_WITH_NAME_FOUND = "No tag with name: %s found";
    private final static String DATA_VALIDATION_EXCEPTION = "Data didn't passed validation";

    private final static String ERROR_CODE_TAG_VALIDATION_FAILED = "0201";
    private final static String ERROR_CODE_TAG_BY_ID_NOT_FOUND_FAILED = "0202404%d";
    private final static String ERROR_CODE_TAG_BY_NAME_NOT_FOUND_FAILED = "0212404";

    @Autowired
    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    @Transactional
    public TagDTO createTag(TagDTO tagDTO) {
        if (!TagValidator.validateForCreate(tagDTO)) {
            throw new TagDataValidationException(DATA_VALIDATION_EXCEPTION, ERROR_CODE_TAG_VALIDATION_FAILED);
        }

        String tagName = tagDTO.getName();
        Tag tag = tagDAO.createTag(tagName);

        return EntityDTOTagMapper.toDTO(tag);
    }

    @Override
    @Transactional
    public void deleteTag(int id) {
        tagDAO.deleteTag(id);
    }

    @Override
    public TagDTO getTagByID(int id) {
        Optional<Tag> optionalTag = tagDAO.getTagByID(id);

        Tag tag = optionalTag.orElseThrow(() -> new TagNotFoundException(
                String.format(NO_TAG_WITH_ID_FOUND, id),
                String.format(ERROR_CODE_TAG_BY_ID_NOT_FOUND_FAILED, id)));

        return EntityDTOTagMapper.toDTO(tag);
    }

    @Override
    public List<TagDTO> getTags() {
        List<Tag> tagList = tagDAO.getTags();

        return EntityDTOTagMapper.toDTO(tagList);
    }

    @Override
    public List<TagDTO> getTagListByGiftCertificateID(int id) {
        List<Tag> tagList = tagDAO.getTagListByGiftCertificateID(id);

        return EntityDTOTagMapper.toDTO(tagList);
    }

    @Override
    public TagDTO getTagByName(String name) {
        Optional<Tag> optionalTag = tagDAO.getTagByName(name);

        Tag tag = optionalTag.orElseThrow(() -> new TagNotFoundException(
                String.format(NO_TAG_WITH_NAME_FOUND, name),
                ERROR_CODE_TAG_BY_NAME_NOT_FOUND_FAILED));

        return EntityDTOTagMapper.toDTO(tag);
    }
}
