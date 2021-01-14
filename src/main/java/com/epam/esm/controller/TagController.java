package com.epam.esm.controller;

import com.epam.esm.dao.GiftCertificateDAO;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tags")
public class TagController {

    private final TagDAO tagDAO;

    public TagController(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @GetMapping
    List<Tag> getTags() {
        return tagDAO.getTags();
    }

    @PostMapping
    Tag newTag(@RequestBody Tag tag) {
        return tagDAO.createTag(tag.getName());
    }

    @GetMapping("/{id}")
    Tag getGiftCertificateByID(@PathVariable int id) {
        return tagDAO.getTagByID(id);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable int id) {
        tagDAO.deleteTag(id);
    }

}
