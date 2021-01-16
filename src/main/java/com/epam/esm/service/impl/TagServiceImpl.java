package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.model.dto.TagDTO;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exception.TagDataValidationException;
import com.epam.esm.service.exception.TagNotFoundException;
import com.epam.esm.service.util.mapper.EntityDTOTagMapper;
import com.epam.esm.service.validator.TagValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagDAO tagDAO;

    private static final String NO_TAGS_FOUND = "No tags found";
    private static final String NO_TAG_WITH_ID_FOUND = "No tag with %d id found";
    private static final String NO_TAG_WITH_NAME_FOUND = "No tag with %s name found";
    private static final String DATA_VALIDATION_EXCEPTION = "Data didn't passed validation";

    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    @Transactional
    public TagDTO createTag(TagDTO tagDTO) {
        if (!TagValidator.validateForCreate(tagDTO)) {
            throw new TagDataValidationException(DATA_VALIDATION_EXCEPTION);
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

        Tag tag = optionalTag.orElseThrow(() -> new TagNotFoundException(String.format(NO_TAG_WITH_ID_FOUND, id)));

        return EntityDTOTagMapper.toDTO(tag);
    }

    @Override
    public List<TagDTO> getTags() {
        List<Tag> tagList = tagDAO.getTags();

        if (tagList.isEmpty()) {
            throw new TagNotFoundException(NO_TAGS_FOUND);
        }

        return EntityDTOTagMapper.toDTO(tagList);
    }

    @Override
    public TagDTO getTagByName(String name) {
        Optional<Tag> optionalTag = tagDAO.getTagByName(name);

        Tag tag = optionalTag.orElseThrow(() -> new TagNotFoundException(String.format(NO_TAG_WITH_NAME_FOUND, name)));

        return EntityDTOTagMapper.toDTO(tag);
    }
}
