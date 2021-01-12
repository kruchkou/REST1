package bean;

import java.util.Date;
import java.util.List;

public class GiftCertificate {

    private int id;
    private String name;
    private String description;
    private int price;
    private int duration;
    private Date createDate;
    private Date lastsUpdateDate;

    private List<Tag> tagList;

}
