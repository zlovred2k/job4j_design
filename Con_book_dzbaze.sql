create table book 
(
    id serial primary key,
    name varchar(128),
    year date,
    pages smallint
);

insert into book(name, year, pages) values ('Java', '2020-01-01', 1100);

select * from book;

update book set pages = 2000;

delete from book;

