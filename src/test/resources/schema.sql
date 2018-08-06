DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR(ID INT PRIMARY KEY, NAME VARCHAR(255));

DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE(ID INT PRIMARY KEY, NAME VARCHAR(255));

DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK(ID INT PRIMARY KEY, NAME VARCHAR(255), GENRE_ID INT);

DROP TABLE IF EXISTS BOOK_AUTHORS;
CREATE TABLE BOOK_AUTHORS(BOOK_ID INT , AUTHOR_ID INT, PRIMARY KEY(BOOK_ID, AUTHOR_ID));