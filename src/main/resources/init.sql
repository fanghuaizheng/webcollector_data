drop table if exists `t_crawler_match`;
create table t_crawler_match(
    guid varchar(32) primary key COMMENT '主键',
    urls varchar(300) default null COMMENT '爬取网址集合',
    de_key varchar(20) default null COMMENT  '主题词',
    info_type int default null
)engine=innodb default charset=utf8mb4 COMMENT='爬虫规则表';

insert into t_crawler_match values ('1','https://news.baidu.com/','出发吧',1);

drop table if exists `t_file_path`;
create table t_file_path(
    guid varchar(32) primary key ,
    file_url varchar(300) default null COMMENT '文件下载地址',
    file_path varchar(100) default null COMMENT  '文件保存地址'
)engine=innodb default charset=utf8mb4 COMMENT='爬虫文件保存地址';

insert into t_file_path values ('1','http://baidu.com','ssssss');