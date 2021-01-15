INSERT INTO gift_certificate (name, description, price, duration, create_date)
values ('First Cert', 'This is first certificate', 50, 10, '2020-01-12 20:15:00');
INSERT INTO gift_certificate (name, description, price, duration, create_date)
values ('Second Cert', 'That is second certificate', 100, 20, '2020-01-12 20:25:00');

INSERT INTO tag (name)
values ('first');
INSERT INTO tag (name)
values ('second');
INSERT INTO tag (name)
values ('third');

INSERT INTO gift_tag(gift, tag)
values (1, 1);
INSERT INTO gift_tag(gift, tag)
values (1, 2);
INSERT INTO gift_tag(gift, tag)
values (2, 2);