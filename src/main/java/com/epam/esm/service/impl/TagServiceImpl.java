package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.validator.TagValidator;

import java.util.List;
import java.util.Optional;

public class TagServiceImpl implements TagService {

    private final TagDAO tagDAO;
    private static final TagValidator tagValidator = new TagValidator();

    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public Tag createTag(TagDTO tagDTO) throws ServiceException {
        if(tagValidator.validateForCreate(tagDTO)) {
            final String name = tagDTO.getName();

            return tagDAO.createTag(name);
        }
        else {
            throw new ServiceException("Data didn't passed validation");
        }
    }

    @Override
    public void deleteTag(int id) {
        tagDAO.deleteTag(id);
    }

    @Override
    public Optional<Tag> getTagByID(int id) {
        return tagDAO.getTagByID(id);
    }

    @Override
    public List<Tag> getTags() {
        return tagDAO.getTags();
    }

    @Override
    public Optional<Tag> getTagByName(String name) {
        return tagDAO.getTagByName(name);
    }
}
