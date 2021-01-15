CREATE TABLE gift_certificate
(
    id               INT          NOT NULL AUTO_INCREMENT,
    name             VARCHAR(45)  NOT NULL,
    description      VARCHAR(200) NOT NULL,
    price            INT          NOT NULL,
    duration         INT          NOT NULL,
    create_date      DATETIME     NOT NULL,
    last_update_date DATETIME     NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tag
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE gift_tag
(
    gift INT NOT NULL,
    tag  INT NOT NULL,
    PRIMARY KEY (gift, tag),
    CONSTRAINT fk_gift_tag_1
        FOREIGN KEY (gift)
            REFERENCES gift_certificate (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_gift_tag_2
        FOREIGN KEY (tag)
            REFERENCES tag (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);