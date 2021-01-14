package com.epam.esm.service;

import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface TagService {

    public Tag createTag(TagDTO tagDTO) throws ServiceException;

    public void deleteTag(int id);

    public Optional<Tag> getTagByID(int id);

    public List<Tag> getTags();

    public Optional<Tag> getTagByName(String name);

}
