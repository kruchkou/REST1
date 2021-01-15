package com.epam.esm.service;

import com.epam.esm.model.dto.TagDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TagService {

    TagDTO createTag(TagDTO tagDTO);

    void deleteTag(int id);

    TagDTO getTagByID(int id);

    List<TagDTO> getTags();

    TagDTO getTagByName(String name);

}
