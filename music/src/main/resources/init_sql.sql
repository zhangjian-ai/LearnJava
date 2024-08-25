create database if not exists music charset utf8mb4 collate utf8mb4_general_ci;

create table if not exists music.admin
(
    id       int unsigned auto_increment primary key comment 'ID',
    name     varchar(50) unique not null comment '用户名',
    password varchar(120)       not null comment '密码'
) charset utf8mb4
  collate utf8mb4_general_ci
    comment '管理员表';

create table if not exists music.consumer
(
    id           int unsigned auto_increment primary key comment 'ID',
    username     varchar(32) unique not null comment '用户名',
    password     varchar(120)       not null comment '密码',
    gender       tinyint default 0 comment '性别。0 女 1 男',
    phone        character(11) comment '电话号',
    email        character comment '电子邮箱',
    birth        date comment '出生日期',
    introduction char(255) comment '介绍',
    location     char(255) comment '地区',
    avatar       char(255) comment '头像 头像图片地址',
    create_time  datetime comment '创建时间',
    update_time  datetime comment '更新时间'
) charset utf8mb4
  collate utf8mb4_general_ci
    comment '用户表';

create table if not exists music.song
(
    id           int unsigned auto_increment primary key comment 'ID',
    singer_id    int         not null comment '歌手id',
    name         varchar(64) not null comment '名字',
    introduction char(255) comment '简介',
    create_time  datetime comment '创建时间',
    update_time  datetime comment '更新时间',
    pic          char(255) comment '图片地址',
    lyric        text comment '歌词',
    url          char(255) comment '歌曲下载地址'
) charset utf8mb4
  collate utf8mb4_general_ci
    comment '歌曲表';

create table if not exists music.singer
(
    id           int unsigned auto_increment primary key comment 'ID',
    name         varchar(64) unique not null comment '名字',
    gender       tinyint default 0 comment '性别。0 女 1 男',
    pic          char(255) comment '图片地址',
    birth        date comment '出生日期',
    location     char(255) comment '地区',
    introduction char(255) comment '简介'
) charset utf8mb4
  collate utf8mb4_general_ci
    comment '歌手表';

create table if not exists music.song_sheet
(
    id           int unsigned auto_increment primary key comment 'ID',
    title        varchar(64) not null comment '名字',
    pic          char(255) comment '图片地址',
    style        varchar(12) comment '风格',
    introduction char(255) comment '简介'
) charset utf8mb4
  collate utf8mb4_general_ci
    comment '歌单';

create table if not exists music.sheet_song
(
    id            int unsigned auto_increment primary key comment 'ID',
    song_id       int not null comment '歌曲id',
    song_sheet_id int not null comment '歌单id'
) charset utf8mb4
  collate utf8mb4_general_ci
    comment '歌单和歌曲的映射关系';

create table if not exists music.collect
(
    id            int unsigned auto_increment primary key comment 'ID',
    consumer_id   int not null comment '用户id',
    type          tinyint default 0 comment '性别。0 歌曲 1 歌单',
    song_id       int comment '歌曲id',
    song_sheet_id int comment '歌单id',
    create_time   datetime comment '创建时间'
) charset utf8mb4
  collate utf8mb4_general_ci
    comment '用户歌单歌曲收藏表';

create table if not exists music.comment
(
    id            int unsigned auto_increment primary key comment 'ID',
    consumer_id   int not null comment '用户id',
    type          tinyint default 0 comment '性别。0 歌曲 1 歌单',
    song_id       int comment '歌曲id',
    song_sheet_id int comment '歌单id',
    content       varchar(256) comment '评论内容',
    up            int     default 0 comment '评论的点赞次数',
    create_time   datetime comment '创建时间'
) charset utf8mb4
  collate utf8mb4_general_ci
    comment '歌单或歌曲的评论表';

create table if not exists music.rank
(
    id            int unsigned auto_increment primary key comment 'ID',
    song_sheet_id int not null comment '歌单id',
    consumer_id   int not null comment '用户id',
    score         int comment '用户给歌单的打分'
) charset utf8mb4
  collate utf8mb4_general_ci
    comment '歌单评分表';
