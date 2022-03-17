create sequence accounts_seq increment by 10;
create table if not exists accounts
(
    id           bigint not null,
    email        varchar(255),
    password     varchar(255),
    status       varchar(255),
    username     varchar(255),
    created_by   varchar(255),
    created_time timestamp not null DEFAULT CURRENT_TIMESTAMP,
    updated_by   varchar(255),
    updated_time timestamp not null DEFAULT CURRENT_TIMESTAMP,
    constraint accounts_pkey primary key (id),
    constraint uk_account_email unique (email),
    constraint uk_account_username unique (username)
);

create sequence roles_seq increment by 10;
create table if not exists roles
(
    id          bigint not null,
    description varchar(255),
    name        varchar(255),
    system      boolean      not null,
    constraint roles_pkey primary key (id),
    constraint uk_role_name unique (name)
);

create sequence accounts_roles_seq increment by 10;
create table if not exists accounts_roles
(
    id bigint not null,
    account_id bigint not null,
    role_id    bigint not null,
    constraint accounts_roles_pkey primary key (id),
    constraint fk_accounts_roles_role_id foreign key (role_id) references roles,
    constraint fk_accounts_roles_account_id foreign key (account_id) references accounts
);

create sequence users_seq increment by 10;
create table if not exists users
(
    id           bigint not null,
    avatar_url   varchar(255),
    email        varchar(255),
    first_name   varchar(255),
    last_name    varchar(255),
    status       varchar(255),
    username     varchar(255),
    created_by   varchar(255),
    created_time timestamp not null DEFAULT CURRENT_TIMESTAMP,
    updated_by   varchar(255),
    updated_time timestamp not null DEFAULT CURRENT_TIMESTAMP,
    constraint users_pkey primary key (id),
    constraint uk_user_email unique (email),
    constraint uk_user_username unique (username)
);

create sequence categories_seq increment by 10;
CREATE TABLE if not exists categories (
      id bigint NOT NULL,
      name varchar(255) NULL,
      description text NULL,
      is_deleted bool NOT NULL DEFAULT false,
      created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
      created_by varchar(255) NULL,
      updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
      updated_by varchar(255) NULL,
      CONSTRAINT categories_pk PRIMARY KEY (id)
);

create sequence if not exists story_types_seq increment by 10;
CREATE TABLE story_types (
     id bigint NOT NULL,
     name varchar(255) NULL,
     description text NULL,
     is_deleted bool NOT NULL DEFAULT false,
     created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     created_by varchar(255) NULL,
     updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
     updated_by varchar(255) NULL,
     CONSTRAINT story_types_pk PRIMARY KEY (id)
);

create sequence if not exists nations_seq increment by 10;
CREATE TABLE nations (
     id bigint NOT NULL,
     name varchar(255) NULL,
     description text NULL,
     flag varchar(512) NULL,
     is_deleted bool NOT NULL DEFAULT false,
     created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     created_by varchar(255) NULL,
     updated_time timestamp NULL DEFAULT CURRENT_TIMESTAMP,
     updated_by varchar(255) NULL,
     CONSTRAINT nations_pk PRIMARY KEY (id)
);
