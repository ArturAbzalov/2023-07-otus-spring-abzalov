insert into authors (firstname, lastname)
values ('Lev', 'Tolstoy');
insert into authors (firstname, lastname)
values ('Ayn', 'Rand');
insert into authors (firstname, lastname)
values ('Robert', 'Lafore');
insert into authors (firstname, lastname)
values ('Cay', 'Horstmann');

insert into genres (GENRENAME)
values ('roman');
insert into genres (GENRENAME)
values ('fantasy');

insert into books (bookname, author_id, genre_id)
values ('War&peace', 1, 1);
insert into books (bookname, author_id, genre_id)
values ('Atlant', 2, 1);
insert into books (bookname, author_id, genre_id)
values ('Atlant2', 2, 1);