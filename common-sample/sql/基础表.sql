------------------------------------------------------------------------
create table sys_user (
  user_id number(10) primary key not null ,
  user_account varchar2(32) not null,
  user_name varchar2(64) not null,
  password varchar2(256) not null,
  salt varchar2(32) default null ,
  locked varchar2(1) default null
);
alter table sys_user add constraint sys_user_pk primary key (user_id);
CREATE INDEX idx_sys_user ON sys_user(user_account);
comment on table sys_user is '用户表';
comment on column sys_user.user_id is '主键';
comment on column sys_user.user_account is '账号';
comment on column sys_user.user_name is '姓名';
comment on column sys_user.password is '密码';
comment on column sys_user.salt is '盐';
comment on column sys_user.locked is '账号是否锁定，1：锁定，0未锁定';
------------------------------------------------------------------------
create table sys_role (
  role_id number(10) not null,
  role_name varchar2(128) not null,
  available char(1) default '1'
);
alter table sys_role add constraint sys_role_pk primary key (role_id);
comment on table sys_role is '角色表';
comment on column sys_role.role_id is '主键';
comment on column sys_role.role_name is '角色名称';
comment on column sys_role.available is '是否可用,1：可用，0不可用';
-------------------------------------------------------------------------
create table sys_menu (
  menu_id number(10) not null,
  menu_code varchar2(128) not null ,
  menu_name varchar2(128) not null ,
  menu_type varchar2(32) not null,
  ico varchar2(128) default null,
  action_url varchar2(128) default null ,
  parent_id number(10) default null ,
  order_no  number(10) default null,
  available  char(1) default '1'
);
alter table sys_menu add constraint sys_menu_pk primary key (menu_id);
comment on table sys_menu is '菜单表';
comment on column sys_menu.menu_id is '主键';
comment on column sys_menu.menu_name is '资源名称';
comment on column sys_menu.menu_type is '资源类型：menu,button,';
comment on column sys_menu.ico is '图标';
comment on column sys_menu.action_url is '访问url地址';
comment on column sys_menu.parent_id is '父结点id';
comment on column sys_menu.order_no is '排序号';
comment on column sys_menu.available is '是否可用,1：可用，0不可用';
-----------------------------------------------------------------------------
create table sys_role_menu (
  id varchar2(36) primary  key not null,
  sys_role_id varchar2(32) not null,
  sys_menu_id varchar2(32) not null
);
comment on table sys_role_menu is '角色权限表';
comment on column sys_role_menu.id is 'id';
comment on column sys_role_menu.sys_role_id is '角色id';
comment on column sys_role_menu.sys_menu_id is '权限id';
-----------------------------------------------------------------------------
create table sys_user_role (
  id number(10) primary key not null,
  sys_user_id number(10) not null,
  sys_role_id number(10) not null
);
comment on table sys_user_role is '用户角色表';
comment on column sys_user_role.id is '主键';
comment on column sys_user_role.sys_user_id is '用户id';
comment on column sys_user_role.sys_role_id is '角色id';
-----------------------------------------------------------------------------
CREATE SEQUENCE sys_user_seq
       minvalue 100000
       maxvalue 9999999999999999999999999999
       start with 100000
       increment by 1
       cache 20
-----------------------------------------------------------------------------
declare
  i Integer;
begin
  i := 1;
  while i <= 50 loop
    insert into sys_user(user_id,user_account,user_name,password,salt,locked)
    values(i,'admin_'||i,'我是'||i,'a5fb0ca6b0d8e63a2429258905152c97c94de9b1ce76218620a28af61282cb9c','123321',0);
    i := i + 1;
    if mod(i, 100) = 0 then
      commit;
    end if;
  end loop;
  commit;
end;
------------------------------------------------------------------------------------
insert into sys_menu (MENU_ID, MENU_NAME, MENU_TYPE, ICO, ACTION_URL, PARENT_ID, ORDER_NO, AVAILABLE, MENU_CODE)
values (1, '首页', 'menu', 'el-icon-s-home', null, 0, 1, '1', 'Home');

insert into sys_menu (MENU_ID, MENU_NAME, MENU_TYPE, ICO, ACTION_URL, PARENT_ID, ORDER_NO, AVAILABLE, MENU_CODE)
values (2, '系统配置', 'menu', 'el-icon-setting', null, 0, 2, '1', 'SysConf');

insert into sys_menu (MENU_ID, MENU_NAME, MENU_TYPE, ICO, ACTION_URL, PARENT_ID, ORDER_NO, AVAILABLE, MENU_CODE)
values (3, '用户管理', 'menu', 'el-icon-user', null, 2, 3, '1', 'UserManage');

insert into sys_menu (MENU_ID, MENU_NAME, MENU_TYPE, ICO, ACTION_URL, PARENT_ID, ORDER_NO, AVAILABLE, MENU_CODE)
values (4, '菜单管理', 'menu', 'el-icon-user', null, 2, 4, '1', 'MenuManage');

insert into sys_role (ROLE_ID, ROLE_NAME, AVAILABLE)
values (1, '管理员', '1');

insert into sys_role (ROLE_ID, ROLE_NAME, AVAILABLE)
values (2, '业务员', '1');
commit;
------------------------------------------------------------------------------------
