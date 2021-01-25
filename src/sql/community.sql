create table user
(
    id          bigint       not null
        primary key,
    gitId       varchar(14)  null,
    avatar_url  varchar(100) null,
    username    varchar(32)  null,
    modified    varchar(64)  null,
    createdTime varchar(64)  null
)
    comment '用户表';