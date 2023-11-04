create table tickets
(
    id    serial primary key,
    route varchar,
    price int
);

create table persons
(
    id        serial primary key,
    name      varchar,
    age       int,
    ticket_id int references tickets (id)
);


select s.name, s.age, f.name
from student s
join faculty f on f.id = s.faculty_id;

select s.name, s.id
from avatar a
join student s on s.id = a.student_id;

