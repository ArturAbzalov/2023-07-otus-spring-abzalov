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

insert into comments (message, book_id)
values ('excellent',1);
insert into comments (message, book_id)
values ('perfect',1);
insert into comments (message, book_id)
values ('this is good',1);
insert into comments (message, book_id)
values ('nice',2);
insert into comments (message, book_id)
values ('grand',2);