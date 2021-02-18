CREATE TABLE pet (
   id bigint PRIMARY KEY,
   owner_pid bigint REFERENCES PERSON(pid),
   favorite_in_group bigint,
   name varchar(20) not null,
   type varchar(30) not null,
   age integer
   );