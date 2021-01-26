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
create table question
(
    id            int auto_increment
        primary key,
    title         varchar(50)   null,
    description   text          null,
    created       varchar(30)   null,
    modified      bigint        null,
    creator       bigint        null,
    comment_count int default 0 null,
    view_count    int default 0 null,
    like_count    int default 0 null,
    tag           varchar(256)  null
)
    comment '发布的问题';
create table tag
(
    id   int auto_increment
        primary key,
    q_Id int         null,
    tag  varchar(32) null
)
    comment '问题的标签表';