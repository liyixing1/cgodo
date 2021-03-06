/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-5-13 20:15:12                           */
/*==============================================================*/


drop table if exists client_request;

drop table if exists client_request_summary;

drop index Index_1 on counter;

drop table if exists counter;

drop table if exists power;

drop index iqu1 on qq_userinfo;

drop table if exists qq_userinfo;

drop table if exists role;

drop table if exists role_power;

drop index isu1 on sina_userinfo;

drop table if exists sina_userinfo;

drop table if exists temp;

drop table if exists user_role;

drop index ci2 on userinfo;

drop index ci1 on userinfo;

drop index iu1 on userinfo;

drop index iu2 on userinfo;

drop table if exists userinfo;

drop index i_wu1 on wechat_userinfo;

drop index iwu1 on wechat_userinfo;

drop table if exists wechat_userinfo;

/*==============================================================*/
/* Table: client_request                                        */
/*==============================================================*/
create table client_request
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   IP                   varchar(100) comment 'ip地址',
   REQUEST_TIME         datetime comment '请求时间',
   PROCESSING_TIME      bigint comment '处理时间',
   REQUEST_URL          varchar(1000) comment '请求地址',
   USER_AGENT           varchar(1000) comment '请求浏览器',
   primary key (ID)
);

alter table client_request comment 'web请求';

/*==============================================================*/
/* Table: client_request_summary                                */
/*==============================================================*/
create table client_request_summary
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   CLIENT_NUMBER        int comment '总客户数，每次请求，都被视为一次统计',
   CURRENT_DAY_NUMBER   int comment '当天客户数，每次新客户请求，都被视为一次统计',
   SUMMARY_TIME         datetime comment '统计当天时间',
   UV                   int comment '当天UV，按站点统计，即当天进入站点的人数(IP)',
   primary key (ID)
);

alter table client_request_summary comment '请求汇总';

/*==============================================================*/
/* Table: counter                                               */
/*==============================================================*/
create table counter
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   NAME                 varchar(100) comment '计数器名称',
   VALUE                bigint comment '最后一次保存的值',
   TYPE                 varchar(100) comment '分类',
   primary key (ID)
);

alter table counter comment '计数器';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on counter
(
   TYPE,
   NAME
);

/*==============================================================*/
/* Table: power                                                 */
/*==============================================================*/
create table power
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   NAME                 varchar(100) comment '权限名称',
   primary key (ID)
);

alter table power comment '权限';

/*==============================================================*/
/* Table: qq_userinfo                                           */
/*==============================================================*/
create table qq_userinfo
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   OPEN_ID              varchar(255) comment 'QQopenid',
   USER_ID              char(32) comment '用户ID',
   primary key (ID)
);

alter table qq_userinfo comment 'qq用户';

/*==============================================================*/
/* Index: iqu1                                                  */
/*==============================================================*/
create unique index iqu1 on qq_userinfo
(
   OPEN_ID
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   NAME                 varchar(100) comment '角色名称',
   primary key (ID)
);

alter table role comment '角色';

/*==============================================================*/
/* Table: role_power                                            */
/*==============================================================*/
create table role_power
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   ROLE_ID              char(32) comment '角色ID',
   POWER_ID             char(32) comment '权限ID',
   primary key (ID)
);

alter table role_power comment '角色权限';

/*==============================================================*/
/* Table: sina_userinfo                                         */
/*==============================================================*/
create table sina_userinfo
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   UID                  varchar(255) comment 'UID',
   USER_ID              char(32) comment '用户ID',
   primary key (ID)
);

alter table sina_userinfo comment '新浪用户';

/*==============================================================*/
/* Index: isu1                                                  */
/*==============================================================*/
create unique index isu1 on sina_userinfo
(
   UID
);

/*==============================================================*/
/* Table: temp                                                  */
/*==============================================================*/
create table temp
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   primary key (ID)
);

alter table temp comment '临时，无作用';

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   USER_ID              char(32) comment '用户ID',
   ROLE_ID              char(32) comment '角色ID',
   primary key (ID)
);

alter table user_role comment '用户角色';

/*==============================================================*/
/* Table: userinfo                                              */
/*==============================================================*/
create table userinfo
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   USER_NAME            varchar(100) comment '用户名',
   PASSWORD             varchar(100) comment '密码',
   SPEAKING_STATUS      varchar(100) comment '发言状态，表示是否可以发言',
   USER_TYPE            varchar(100) comment '用户类型',
   IMG_URL              varchar(1000) comment '图片',
   NICK_NAME            varchar(100) comment '昵称',
   FIRST_TIME           datetime comment '第一次登陆时间',
   LAST_TIME            datetime comment '最后登陆时间',
   PHONE                varchar(11) comment '手机号',
   EMAIL                varchar(255) comment '邮箱',
   primary key (ID)
);

alter table userinfo comment '用户';

/*==============================================================*/
/* Index: iu2                                                   */
/*==============================================================*/
create index iu2 on userinfo
(
   USER_TYPE
);

/*==============================================================*/
/* Index: iu1                                                   */
/*==============================================================*/
create unique index iu1 on userinfo
(
   USER_NAME
);

/*==============================================================*/
/* Index: ci1                                                   */
/*==============================================================*/
create unique index ci1 on userinfo
(
   PHONE
);

/*==============================================================*/
/* Index: ci2                                                   */
/*==============================================================*/
create unique index ci2 on userinfo
(
   EMAIL
);

/*==============================================================*/
/* Table: wechat_userinfo                                       */
/*==============================================================*/
create table wechat_userinfo
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   OPEN_ID              varchar(255) comment '微信openid',
   USER_ID              char(32) comment '用户ID',
   primary key (ID)
);

alter table wechat_userinfo comment '微信用户';

/*==============================================================*/
/* Index: iwu1                                                  */
/*==============================================================*/
create unique index iwu1 on wechat_userinfo
(
   OPEN_ID
);

/*==============================================================*/
/* Index: i_wu1                                                 */
/*==============================================================*/
create unique index i_wu1 on wechat_userinfo
(
   USER_ID
);

alter table qq_userinfo add constraint FK_Reference_7 foreign key (USER_ID)
      references userinfo (ID) on delete restrict on update restrict;

alter table role_power add constraint FK_m1 foreign key (POWER_ID)
      references power (ID) on delete restrict on update restrict;

alter table role_power add constraint FK_m2 foreign key (ROLE_ID)
      references role (ID) on delete restrict on update restrict;

alter table sina_userinfo add constraint FK_Reference_6 foreign key (USER_ID)
      references userinfo (ID) on delete restrict on update restrict;

alter table user_role add constraint FK_m3 foreign key (USER_ID)
      references userinfo (ID) on delete restrict on update restrict;

alter table user_role add constraint FK_m4 foreign key (ROLE_ID)
      references role (ID) on delete restrict on update restrict;

alter table wechat_userinfo add constraint FK_Reference_5 foreign key (USER_ID)
      references userinfo (ID) on delete restrict on update restrict;

