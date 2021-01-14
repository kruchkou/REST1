package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDAO {

    public Tag createTag(String name);

    public void deleteTag(int id);

    public Optional<Tag> getTagByID(int id);

    public List<Tag> getTags();

    public Optional<Tag> getTagByName(String name);

}
