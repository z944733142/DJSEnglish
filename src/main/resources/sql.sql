create table user (
  id int(11) not null AUTO_INCREMENT,
  name varchar(50) not null comment '昵称',
  phone varchar(20) not null comment '电话(账号)',
  password varchar(50) not null comment '密码',
  sex varchar(20) default '' comment '性别',
  stage varchar(20) default ''comment '学习阶段',
  msg varchar(200)  default '此人很懒, 尚未填写个人信息.'comment '用户介绍',
  img varchar(200) default 'default.jpg' comment '头像url',
  update_time datetime not null  comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key (id),
  unique index (phone)
)ENGINE=InnoDB AUTO_INCREMENT=22;


-- 更新
create table qq_user(
  id int(11) not null  AUTO_INCREMENT,
  qq_id varchar(50) not null comment 'qq返回的唯一标识',
  phone varchar(50) not null comment '对应的用户手机',
  update_time datetime not null  comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key (id),
  unique index (qq_id),
)ENGINE=InnoDB AUTO_INCREMENT=22;

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

create table sentence(
  id int(11) not null AUTO_INCREMENT,
  user_id int(11) not null comment '用户id',
  sentence varchar(500) not null comment '造句',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=22 ;

-- 更新
create table message(
  id int(11) not null AUTO_INCREMENT,
  sender int(11) not null comment '发送者id',
  to  int(11) not null comment '接收人id',
  text varchar(500) not null comment '内容',
  update_time datetime not null comment '更新时间',
  create_time datetime not null comment '创建时间',
  primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=22;

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
  primary key (id)
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