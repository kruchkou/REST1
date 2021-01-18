package com.epam.esm.service.util.mapper;

import com.epam.esm.model.dto.TagDTO;
import com.epam.esm.model.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public final class EntityDTOTagMapper {

    private EntityDTOTagMapper() {
    }

    public static Tag toEntity(TagDTO tagDTO) {
        Tag tag = new Tag();

        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());

        return tag;
    }

    public static TagDTO toDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();

        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());

        return tagDTO;
    }

    public static List<TagDTO> toDTO(List<Tag> tagList) {
        List<TagDTO> tagDTOList = new ArrayList<>();

        tagList.forEach(tag -> tagDTOList.add(toDTO(tag)));

        return tagDTOList;
    }

    public static List<Tag> toEntity(List<TagDTO> tagDTOList) {
        List<Tag> tagList = new ArrayList<>();

        tagDTOList.forEach(tagDTO -> tagList.add(toEntity(tagDTO)));

        return tagList;
    }
}
