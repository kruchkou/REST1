package com.epam.esm.dao;

import com.epam.esm.model.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface TagDAO {

    void deleteTag(int id);

    void insertGiftTag(int giftID, int tagID);

    Tag createTag(String name);

    Optional<Tag> getTagByID(int id);

    List<Tag> getTags();

    Optional<Tag> getTagByName(String name);

    List<Tag> getTagListByGiftCertificateID(int id);

}
