create database if not exists onlinelibrarysystem;
use onlinelibrarysystem;

create table if not exists genre (
    id bigint primary key auto_increment not null,
    name varchar(255) not null
);

create table if not exists publisher (
    id bigint primary key auto_increment not null,
    name varchar(255) not null
);

create table if not exists author (
    id bigint primary key auto_increment not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null
);

# TODO create country, language and translator columns in book table
create table if not exists book (
    id bigint primary key auto_increment not null,
    title varchar(255) not null,
    page int not null,
    publish_year int not null,
    edition varchar(25),
    image blob,
    description varchar(500) not null
);

create table if not exists role (
    id int primary key auto_increment not null,
    name varchar(10) not null
);

create table if not exists user (
    id bigint primary key auto_increment not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    username varchar(255) unique not null,
    password varchar(255) not null,
    enable boolean not null,
    create_date date not null,
    update_date date,
    delete_date date
);

create table if not exists vote (
    id bigint primary key auto_increment not null,
    value enum('1', '2', '3', '4', '5') not null
);

create table if not exists authors_books (
    id bigint primary key auto_increment not null,
    book_id bigint not null,
    author_id bigint not null,

    foreign key (book_id) references book(id),
    foreign key (author_id) references author(id)
);

create table if not exists genres_books (
    id bigint primary key auto_increment not null,
    book_id bigint not null,
    genre_id bigint not null,

    foreign key (book_id) references book(id),
    foreign key (genre_id) references genre(id)
);

create table if not exists books_publishers (
    id bigint primary key auto_increment not null,
    book_id bigint not null,
    publisher_id bigint not null,

    foreign key (book_id) references book(id),
    foreign key (publisher_id) references publisher(id)
);

create table if not exists books_vote (
    id bigint primary key auto_increment not null,
    book_id bigint not null,
    vote_id bigint not null,

    foreign key (book_id) references book(id),
    foreign key (vote_id) references vote(id)
);

create table if not exists user_role (
    id bigint primary key auto_increment not null,
    user_id varchar(36) not null,
    role_id varchar(36) not null,

    foreign key (user_id) references user(id),
    foreign key (role_id) references role(id)
);

create table if not exists user_book (
    id varchar(36) primary key not null,
    user_id varchar(36) not null,
    book_id varchar(36) not null,

    foreign key (user_id) references user(id),
    foreign key (book_id) references book(id)
);

insert into book(title, page, publish_year, image, description) VALUES ('The Adventures of Huckleberry Finn', 320, 1884, null,
        'All American literature comes from one book by Mark Twain called Huckleberry Finn.');
insert into book(title, page, publish_year, image, description) VALUES ('The Adventures of Tom Sawyer', 168, 1876, null,
        'The Adventures of Tom Sawyer by Mark Twain is an 1876 novel about a young boy growing up along the Mississippi River.');

insert into author (first_name, last_name)
values ('Mark', 'Twain');

insert into authors_books(book_id, author_id) VALUES (1, 1);
insert into authors_books(book_id, author_id) VALUES (2, 1);

insert into publisher(name) value ('Chatto & Windus / Charles L. Webster And Company.');
insert into publisher(name) value ('American Publishing Company');

insert into books_publishers(book_id, publisher_id) VALUES (1, 1);
insert into books_publishers(book_id, publisher_id) VALUES (2, 2);