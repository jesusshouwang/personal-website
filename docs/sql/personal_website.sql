drop table if exists article;
create table article
(
    id             bigint auto_increment comment '文章id'
        primary key,
    type_id        int(3)       null comment '类型编号',
    title          varchar(50)  null comment '标题',
    description    varchar(255) null comment '描述',
    content        text         null comment '内容',
    user_id        int          null comment '用户编号',
    views          int          null comment '浏览数',
    appreciation   bit          null comment '赞赏？',
    share          bit          null comment '分享？',
    comment        bit          null comment '评论？',
    recommendation bit          null comment '推荐？',
    published      bit          null comment '发表？',
    create_time    datetime     null comment '发表时间',
    update_time    datetime     null comment '更新时间'
)
    comment '文章表';

drop table if exists type;
create table type
(
    id     bigint auto_increment comment '编号'
        primary key,
    name   varchar(10) null comment '分类名',
    status bit         null comment '分类状态'
)
    comment '文章类型';

drop table if exists user;
create table user
(
    id              bigint auto_increment comment '用户id'
        primary key,
    avatar          varchar(255) null comment '用户头像',
    username        varchar(16)  null comment '用户名',
    password        varchar(255) null comment '用户密码',
    note            varchar(100) null comment '用户描述',
    email           varchar(20)  null comment '用户邮箱',
    rights          bit          null comment '用户权限',
    create_time     datetime     null comment '用户创建时间',
    last_login_time datetime     null comment '用户最后登陆时间'
)
    comment '用户表';

drop table if exists website;
create table website
(
    id     bigint auto_increment comment '站点编号'
        primary key,
    name   varchar(20) null comment '站点名称',
    url    varchar(30) null comment '链接地址',
    top    bit         null comment '开启超级推荐',
    status bit         null comment '启用状态'
)
    comment '推荐站点表';