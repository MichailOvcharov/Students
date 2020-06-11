CREATE TABLE public.students
(
    id SERIAL PRIMARY KEY,
    NAME character varying(200) NOT NULL,
	passport character varying(300) NOT NULL
);
INSERT INTO students (NAME, passport) VALUES ('Ovcharov Michail Borisovich','57 04 154460');
INSERT INTO students (NAME, passport) VALUES ('Kamaltdinova Marina Rakipovna','55 44 17899');
INSERT INTO students (NAME, passport) VALUES ('Oborin Vladimir Nikolaevich','43 36 789451');