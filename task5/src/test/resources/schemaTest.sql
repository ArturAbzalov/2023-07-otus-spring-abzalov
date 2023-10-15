
DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES
(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    GENRENAME VARCHAR(255)
);
DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS
(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    FIRSTNAME VARCHAR(255) NOT NULL,
    LASTNAME VARCHAR(255) NOT NULL
);
DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS
(
    BOOK_ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    BOOKNAME VARCHAR(255),
    AUTHOR_ID BIGINT REFERENCES AUTHORS(ID) NOT NULL ,
    GENRE_ID BIGINT REFERENCES GENRES(ID) NOT NULL
);

-- select name,firstname,lastname,genrename from book left join fetch author on book.author_id=author.id left join fetch genre on book.genre_id=genre.id
-- insert into author(firstname,lastname) values('pushkin','alex');
-- set @a := scope_identity();
--
-- insert into genre(genrename) values('fantasy');
-- SET @b := scope_identity();
--
-- insert into book(bookname, author_id, genre_id) values('hole',@a,@b)


-- select id from final table(insert into author(firstname,lastname) values('pushkin','alex'));