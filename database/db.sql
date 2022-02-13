

CREATE TABLE IF NOT EXISTS person (
                       id bigserial NOT NULL,
                       age INT,
                       name varchar,
                       sur_name varchar,
                       vacation_type varchar,
                       PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cat (
                    id  bigserial NOT NULL,
                    name varchar(40),
                    age INT,
                    person_id bigint,
                    PRIMARY KEY (id),
                    FOREIGN KEY (person_id) REFERENCES PERSON(id)
);
CREATE TABLE IF NOT EXISTS vocationday (
                            id  bigserial NOT NULL,
                            day DATE,
                            person_id bigint,
                            PRIMARY KEY (id),
                            FOREIGN KEY (person_id) REFERENCES PERSON(id)
);




INSERT INTO person ( age,name,sur_name,vacation_type)
values (25, 'Pavelyyyyyyyyyyyyyyyyyy', 'DDD','ILLNESS');
INSERT INTO person ( age,name,sur_name,vacation_type)
values (35, 'Sasha', 'lll',' EXCEPTION');
INSERT INTO person ( age,name,sur_name,vacation_type)
values (35, 'Olia', 'lll',' EXCEPTION');
INSERT INTO person ( age,name,sur_name,vacation_type)
values (35, 'ssss', 'lll',' EXCEPTION');

INSERT INTO cat ( name,age,person_id )
values ('est',2,1);
INSERT INTO cat (name,age,person_id )
values ('aaaarrrrrrrrrrrrrrrrrr',3,1);
INSERT INTO cat (name,age,person_id )
values ('xxxx',3,2);
INSERT INTO cat ( name,age,person_id )
values ('eeee',2,1);
INSERT INTO cat ( name,age,person_id )
values ('ttt',2,4);

INSERT INTO vocationday ( day,person_id )
values ('2022-1-1',1);
INSERT INTO vocationday ( day,person_id )
values ('2022-2-1',1);