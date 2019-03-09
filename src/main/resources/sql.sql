create table user (
    id int(11) not null AUTO_INCREMENT,
    name varchar(50) not null comment '昵称',
    username varchar(50) not null comment '用户名',
    password varchar(50) not null comment '密码',
    msg varchar not null comment '用户介绍',
    img varchar(50) not null comment '头像url',
    email varchar(50) not null comment '邮箱',
    phone varchar(20) not null comment '电话',
    update_time datetime not null  comment '更新时间',
    create_time datetime not null comment '创建时间',
    primary key (id)
)

create table concern(
  id int(11) not null,
  user int(11) not null comment'关注者主键',
  friending int(11) not null comment '被关注者的主键',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)

create table article(
  id int (11) not null,
  text text not null comment '文章正文',
  begin text not null comment '文章开头',
  img varchar(50) not null comment '图片url',
  collection int(11) not null comment '收藏数',
  like int(11)  not null comment '点赞数',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)

create table words(
  id int(11) not null,
  word varchar(50) not null comment'单词',
  sound_mark varchar(50) not null comment'音标',
  pos varchar(50) not null comment '词性',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key (id)
)

create table sentences(
    id int(11) not null,
    sentence varchar not null,
    update_time datetime not null comment '更新时间',
    create_time datetime not null comment '创建时间',
    primary key (id)
)

create table chatting_records(
  id int(11) not null,
  sender int(11) not null comment '发送者',
  receiver  int(11) not null comment '接收人',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)

create table search_history(
  id int(11) not null,
  user int(11) not null comment'用户id',
  word varchar(50) not null comment'单词',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)

create table article_comment(
  id int(11) not null,
  article int(11) not null comment '文章id',
  user int(11) not null comment '评论人id',
  text vatchar not null comment '评论内容',
  like int(11) not null comment '点赞数',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)

create table collection(
  id int(11) not null,
  user int(11) not null comment'收藏人主键',
  type int(2) not null comment'0美文1评论'
  article int(11) not null comment'文章主键',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)

