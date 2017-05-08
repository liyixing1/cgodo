/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-5-8 13:37:50                            */
/*==============================================================*/


drop table if exists temp;

drop table if exists wechat_call;

drop table if exists wechat_notify;

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
/* Table: wechat_call                                           */
/*==============================================================*/
create table wechat_call
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   OPEN_ID              char(32) comment 'OPEN_ID',
   IP                   varchar(100) comment 'IP',
   USER_ID              char(32) comment '调用者用户ID',
   PARAMS               varchar(1500) comment '调用参数',
   RESULT               varchar(1000) comment '调用结果',
   TYPE                 varchar(100) comment '接口类型',
   EXTERNAL_NO          varchar(100) comment '外部编号，调用微信接口时关联起来',
   primary key (ID)
);

alter table wechat_call comment '微信调用记录';

/*==============================================================*/
/* Table: wechat_notify                                         */
/*==============================================================*/
create table wechat_notify
(
   ID                   char(32) not null,
   STATUS               varchar(100) comment '状态',
   VERSION              bigint comment '版本号',
   GMT_CREATED          datetime comment '创建时间',
   GMT_UPDATED          datetime comment '修改时间',
   PARAMS               varchar(1500) comment '回调参数',
   RESULT               varchar(100) comment '处理结果',
   TYPE                 varchar(100) comment '回调类型',
   primary key (ID)
);

alter table wechat_notify comment '微信回调通知';

