create SCHEMA IF NOT EXISTS public_module;

create TABLE public_module.subject
(id character varying(255) PRIMARY KEY NOT NULL,
code integer NOT NULL,
name character varying(255));

create TABLE public_module.student
(id character varying(255) PRIMARY KEY NOT NULL,
firstName character varying(255),
lastName character varying(255),
age integer NOT NULL,
admissionDate date);

create TABLE public_module.grade
(id character varying(255) PRIMARY KEY NOT NULL,
value integer NOT NULL,
subject_id character varying(255) NOT NULL);

create TABLE public_module.group
(id character varying(255) PRIMARY KEY NOT NULL,
name character varying(255));

create TABLE public_module.educator
(id character varying(255) PRIMARY KEY NOT NULL,
firstname character varying(255),
lastname character varying(255),
age integer NOT NULL,
subject_id character varying(255) NOT NULL);

create TABLE public_module.student_grade
(grade_id character varying(255) NOT NULL,
students_id character varying(255) NOT NULL);

create TABLE public_module.group_student
(group_id character varying(255) NOT NULL,
students_id character varying(255) NOT NULL);

insert into public_module.subject (id, code, name) values ('78ee2069-5dfe-4bbd-8dd5-d4cd62769e13', 764, 'ScienceSubject-3');
insert into public_module.subject  (id, code, name) values ('e80b77ac-cd5f-4e76-b903-17d962cab3d9', 125, 'ScienceSubject-17');

insert into public_module.student (id, firstName, lastName, age, admissionDate) values ('33e5adc8-fe2c-4a06-9bc8-99e24377e58a', 'Axl', 'Rose', 60, '2022-09-12');
insert into public_module.student (id, firstName, lastName, age, admissionDate) values ('dbb6b3eb-605e-42ff-832d-76abbef24f05', 'Myles', 'Kennedy', 52, '2022-09-12');

insert into public_module.grade (id, value, subject_id) values ('78ee2069-5dfe-4bbd-8dd5-76abbef24f05', 5, '78ee2069-5dfe-4bbd-8dd5-d4cd62769e13');
insert into public_module.grade (id, value, subject_id) values ('33e5adc8-cd5f-4e76-b903-17d962cab3d9', 3, 'e80b77ac-cd5f-4e76-b903-17d962cab3d9');

insert into public_module.group (id, name) values ('7fa68655-5a2f-4e76-b903-17d962cab3d9', 'OPK-227');
insert into public_module.group (id, name) values ('e50303b6-e317-4c0d-9d22-b64ac27119c5', 'RPZ-128');

insert into public_module.educator (id, firstname, lastname, age, subject_id) values ('799922f1-ae8f-47ca-b6c8-a6e43ad84de4',  'Steven', 'Tyler', 74, '78ee2069-5dfe-4bbd-8dd5-d4cd62769e13');
insert into public_module.educator (id, firstname, lastname, age, subject_id) values ('95ad7817-0647-4e69-9030-9ec4ba9009b8', 'Saul', 'Hudson', 57, 'e80b77ac-cd5f-4e76-b903-17d962cab3d9');

insert into public_module.student_grade(grade_id, students_id) values ('78ee2069-5dfe-4bbd-8dd5-76abbef24f05', '33e5adc8-fe2c-4a06-9bc8-99e24377e58a');
insert into public_module.student_grade(grade_id, students_id) values ('33e5adc8-cd5f-4e76-b903-17d962cab3d9', 'dbb6b3eb-605e-42ff-832d-76abbef24f05');

insert into public_module.group_student(group_id, students_id) values ('7fa68655-5a2f-4e76-b903-17d962cab3d9', '33e5adc8-fe2c-4a06-9bc8-99e24377e58a');
insert into public_module.group_student(group_id, students_id) values ('e50303b6-e317-4c0d-9d22-b64ac27119c5', 'dbb6b3eb-605e-42ff-832d-76abbef24f05');