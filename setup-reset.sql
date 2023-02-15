drop table if exists users, planets, moons;

create table users(
	user_id serial primary key,
	user_username varchar(30) unique,
	user_password varchar(30)
);

create table planets(
	planet_id serial primary key,
	planet_name varchar(30) unique,
	user_id int references users(user_id) on delete cascade
);

create table moons(
	moon_id serial primary key,
	moon_name varchar(30) unique,
	planet_id int references planets(planet_id) on delete cascade
);

insert into users values (1, 'login','user');

insert into planets values (1, 'getPlanet', 1);

insert into moons values (1, 'getMoon', 1);

-- the two inserts below are needed for the Planetarium tests

insert into planets values (-1, 'delete', 1);

insert into moons values (-1, 'delete', 1);


