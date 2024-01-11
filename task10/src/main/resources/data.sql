merge into authors (firstname, lastname) key (firstname, lastname)
values ('Lev', 'Tolstoy');
merge into authors (firstname, lastname) key (firstname, lastname)
values ('Ayn', 'Rand');
merge into authors (firstname, lastname) key (firstname, lastname)
values ('Robert', 'Lafore');
merge into authors (firstname, lastname) key (firstname, lastname)
values ('Cay', 'Horstmann');

merge into genres (genrename) key (genrename)
values ('roman');
merge into genres (genrename) key (genrename)
values ('fantasy');

merge into books (bookname, author_id, genre_id) key (bookname, author_id, genre_id)
values ('War&peace', 1, 1);
merge into books (bookname, author_id, genre_id) key (bookname, author_id, genre_id)
values ('Atlant', 2, 1);
merge into books (bookname, author_id, genre_id) key (bookname, author_id, genre_id)
values ('Atlant2', 2, 1);

merge into comments (message, book_id) key (message, book_id)
values ('excellent',1);
merge into comments (message, book_id) key (message, book_id)
values ('perfect',1);
merge into comments (message, book_id) key (message, book_id)
values ('this is good',1);
merge into comments (message, book_id) key (message, book_id)
values ('nice',2);
merge into comments (message, book_id) key (message, book_id)
values ('grand',2);