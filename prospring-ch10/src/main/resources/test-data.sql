insert into contact (first_name, last_name, birth_date) values
('Yeonho', 'Jang' , '1973-09-18');

insert into contact (first_name, last_name, birth_date) values
('Mijung', 'Hong' , '1978-01-12');

insert into contact (first_name, last_name, birth_date) values
('Eunchae', 'Jang' , '2009-02-11');

insert into contact_tel_detail (contact_id, tel_type, tel_number)
values (1, 'Mobile', '1234567890');


insert into contact_tel_detail (contact_id, tel_type, tel_number)
values (1, 'Home', '1234567890');

insert into contact_tel_detail (contact_id, tel_type, tel_number)
values (2, 'Home', '1234567890');

insert into hobby (hobby_id) values ('Swimming');
insert into hobby (hobby_id) values ('Jogging');
insert into hobby (hobby_id) values ('Programming');
insert into hobby (hobby_id) values ('Movies');
insert into hobby (hobby_id) values ('Reading');


insert into contact_hobby_detail (contact_id, hobby_id) values (1, 'Swimming');
insert into contact_hobby_detail (contact_id, hobby_id) values (1, 'Movies');
insert into contact_hobby_detail (contact_id, hobby_id) values (2, 'Swimming');