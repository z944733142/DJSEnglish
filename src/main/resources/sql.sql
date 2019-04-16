create table user (
  id int(11) not null AUTO_INCREMENT,
  name varchar(50) not null comment '昵称',
  username varchar(50) not null comment '用户名',
  password varchar(50) not null comment '密码',
  msg varchar(200) not null comment '用户介绍',
  img varchar(50) not null comment '头像url',
  email varchar(50) not null comment '邮箱',
  phone varchar(20) not null comment '电话',
  update_time datetime not null  comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=22 ;

create table concern(
  id int(11) not null AUTO_INCREMENT,
  user int(11) not null comment'关注者主键',
  friending int(11) not null comment '被关注者的主键',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=22 ;

create table article(
  id int (11) not null AUTO_INCREMENT,
  text text not null comment '文章正文',
  begin text not null comment '文章开头',
  img varchar(50) not null comment '图片url',
  collection int(11) not null comment '收藏数',
  likes int(11)  not null comment '点赞数',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=22 ;

create table words(
  id int(11) not null AUTO_INCREMENT,
  word varchar(50) not null comment'单词',
  mean varchar(200) not null comment '解释翻译',
  sound_mark varchar(50) not null comment'音标',
  pos varchar(50) not null comment '词性',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=22 ;

create table sentences(
  id int(11) not null AUTO_INCREMENT,
  sentence varchar(500) not null,
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=22 ;

create table chatting_records(
  id int(11) not null AUTO_INCREMENT,
  sender int(11) not null comment '发送者',
  receiver  int(11) not null comment '接收人',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=22 ;

create table search_history(
  id int(11) not null AUTO_INCREMENT,
  user int(11) not null comment'用户id',
  word varchar(50) not null comment'单词',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=22;

create table article_comment(
  id int(11) not null AUTO_INCREMENT,
  article int(11) not null comment '文章id',
  user int(11) not null comment '评论人id',
  text varchar(500) not null comment '评论内容',
  likes int(11) not null comment '点赞数',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=22;

create table collection(
  id int(11) not null AUTO_INCREMENT,
  user int(11) not null comment '收藏人主键',
  article int(11) not null comment '收藏文章主键',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key (id)1
)ENGINE=InnoDB AUTO_INCREMENT=22;

create table article_like(
  id int(11) not null AUTO_INCREMENT,
  user int(11) not null comment'收藏人主键',
  article_id int(11) not null comment'文章主键',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=22;

create table comment_like(
  id int(11) not null AUTO_INCREMENT,
  user int(11) not null comment'收藏人主键',
  comment_id int(11) not null comment'评论主键',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=22;