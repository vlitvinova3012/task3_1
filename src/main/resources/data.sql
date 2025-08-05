INSERT INTO Author (name) VALUES ('Pushkin A.S.'), ('Tolstoy L.N.');

INSERT INTO Genre (name) VALUES ('Fairy tale'), ('Novel');

INSERT INTO Comment (comment) VALUES ('For publish'), ('Not publish');

INSERT INTO Book (title, author_id, genre_id, comment_id) VALUES ('Anna Karenina', 2, 2, 1), ('The Tale of Tsar Saltan', 1, 1, 2);