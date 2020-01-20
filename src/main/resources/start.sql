create table if not exists users
(
    id bigserial not null
        constraint users_pkey
            primary key,
    name varchar(255),
    password varchar(255)
);

alter table users owner to admin;

INSERT INTO users (ID, NAME, PASSWORD) VALUES (1, 'admin','$2a$10$1p.q1YNIBIAdZhyWxWMYmOzax49MC01Jrj3czLxDDKOI3cZWrOY6q');
INSERT INTO users (ID, NAME, PASSWORD) VALUES (2, 'user2', '$2a$10$GJCfTy6hb.8s2N1mr3qNIOE3L5nc8nAFZpmBlF9H81IyhOHSLpdtK');
INSERT INTO users (ID, NAME, PASSWORD) VALUES (3, 'user3', '$2a$10$GJCfTy6hb.8s2N1mr3qNIOE3L5nc8nAFZpmBlF9H81IyhOHSLpdtK');

create table if not exists roles
(
    id bigserial not null
        constraint roles_pkey
            primary key,
    name varchar(255)
);

alter table roles owner to admin;

INSERT INTO roles (ID, NAME) VALUES (1, 'ADMIN');
INSERT INTO roles (ID, NAME) VALUES (2, 'USER');

create table if not exists user_role
(
    user_id bigint not null
        constraint fkopu6559jject4b14oftxlswxv
            references users,
    role_id bigint not null
        constraint fk5rcif1upc9f450igguo8cehsq
            references roles,
    constraint user_role_pkey
        primary key (user_id, role_id)
);

alter table user_role owner to admin;

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (3, 2);


create table if not exists clients
(
    id bigserial not null
        constraint clients_pkey
            primary key,
    name varchar(255),
    user_id bigint
        constraint fkb4a1ca6shncyh7w568jm860md
            references users
);

alter table clients owner to admin;

INSERT INTO clients (ID, NAME, user_id) VALUES (1, 'Misha', 2);
INSERT INTO clients (ID, NAME, user_id) VALUES (2, 'Masha', 3);


create table if not exists services
(
    id bigserial not null
        constraint services_pkey
            primary key,
    name varchar(255)
);

alter table services owner to admin;

INSERT INTO services (ID, NAME) VALUES (1, 'Man haircut');
INSERT INTO services (ID, NAME) VALUES (2, 'Woman haircut');


create table if not exists resources
(
    type varchar(31) not null,
    id bigserial not null
        constraint resources_pkey
            primary key,
    name varchar(255)
);

alter table resources owner to admin;

INSERT INTO resources (type, ID, NAME) VALUES ('employee', 1, 'Worker1');
INSERT INTO resources (type, ID, NAME) VALUES ('equipment', 2, 'WorkPlace1');


create table if not exists events
(
    id bigserial not null
        constraint events_pkey
            primary key,
    client_id bigint not null
        constraint fkhcwxmgjjge0xhy4m7m0qn2p35
            references clients,
    service_id bigint not null
        constraint fkrx4vwnyek66f916pgk8lxe2vn
            references services
);

alter table events owner to admin;

INSERT INTO events (ID, client_id, service_id) VALUES (1, 1, 1);
INSERT INTO events (ID, client_id, service_id) VALUES (2, 2, 2);


create table if not exists event_resource
(
    event_id bigint not null
        constraint fk59sukc6jt5h7a73yxr3jfagtb
            references events,
    resource_id bigint not null
        constraint fkjiyj06kbccg9n55oo9hqqua4f
            references resources,
    constraint event_resource_pkey
        primary key (event_id, resource_id)
);

alter table event_resource owner to admin;

INSERT INTO event_resource (event_id, resource_id) VALUES (1, 1);
INSERT INTO event_resource (event_id, resource_id) VALUES (1, 2);
INSERT INTO event_resource (event_id, resource_id) VALUES (2, 1);
INSERT INTO event_resource (event_id, resource_id) VALUES (2, 2);
