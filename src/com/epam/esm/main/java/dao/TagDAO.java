package dao;

import bean.Tag;

import java.util.List;

public interface TagDAO {

    public void create(Tag tag);

    public void remove(Tag tag);

    public Tag getTagByID(int id);

    public List<Tag> getTags();

    public List<Tag> getTagsByName(String name);

}
