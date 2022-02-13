DROP TABLE IF EXISTS PERSON;
DROP TABLE IF EXISTS CAT;

CREATE TABLE PERSON(
                       id long NOT NULL AUTO_INCREMENT,
                       age INT,
                       name varchar,
                       sur_name varchar,
                       PRIMARY KEY (id)
);

CREATE TABLE CAT(
                     id long  NOT NULL AUTO_INCREMENT,
                     name varchar(40),
                     age INT,
                     person_id long,
                     PRIMARY KEY (id),
                     FOREIGN KEY (person_id) REFERENCES PERSON(id)
);
INSERT INTO PERSON( age,name,sur_name)
values (25, 'Pavel', 'DDD');
INSERT INTO PERSON( age,name,sur_name)
values (35, 'Sasha', 'lll');

INSERT INTO CAT( name,age,person_id )
values ('est',2,1);
INSERT INTO CAT(name,age,person_id )
values ('aaaa',3,1);