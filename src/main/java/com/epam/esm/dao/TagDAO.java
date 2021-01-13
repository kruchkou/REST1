package dao;

import bean.Tag;

import java.util.List;

public interface TagDAO {

    public void create(String name);

    public void delete(int id);

    public Tag getTagByID(int id);

    public List<Tag> getTags();

    public List<Tag> getTagsByName(String name);

}
