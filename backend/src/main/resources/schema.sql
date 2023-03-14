create table passec.users
(
    id       int auto_increment primary key,
    username text,
    password text,
    is_admin bool
);

create table passec.pastes
(
    id             varchar(6) primary key,
    user           int,
    title          text,
    content        longtext,
    createion_date datetime default now(),
    foreign key (user) references users (id)
);


