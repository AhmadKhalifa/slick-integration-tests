-- default schema

-- !Ups
CREATE TABLE `app_user`
(
    uuid     varchar(64) NOT NULL PRIMARY KEY,
    username varchar(64) NOT NULL,
    email    varchar(64) NOT NULL,
    password varchar(64)
);

-- !Ups
CREATE TABLE `profile`
(
    uuid       varchar(64) NOT NULL PRIMARY KEY,
    user_uuid  varchar(64) NOT NULL,
    first_name varchar(64) NOT NULL,
    last_name  varchar(64) NOT NULL,
    FOREIGN KEY (user_uuid) REFERENCES `app_user` (uuid)
);

-- !Downs
DROP TABLE profile;

-- !Downs
DROP TABLE app_user;
