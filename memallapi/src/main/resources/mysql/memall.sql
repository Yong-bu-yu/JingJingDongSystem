create database memall charset 'utf8';
use memall;
-- 商品表
create table commodity(
    id bigint(16) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    name nvarchar(255) NOT NULL COMMENT '商品名称',
    type nvarchar(9) DEFAULT '未知类型' COMMENT '商品类型',
    quantity bigint(16) DEFAULT 0 COMMENT '商品数量',
    primary key (id)
);
-- 用户表
create table user(
    id bigint(16) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    name nvarchar(255) NOT NULL UNIQUE COMMENT '用户名称',
    type nvarchar(9) NOT NULL DEFAULT '用户' UNIQUE COMMENT '类型',
    password nvarchar(255) NOT NULL DEFAULT '000000' COMMENT '密码',
    primary key (id)
);
-- 订单表
create table orders
(
    id          bigint(16)   NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    number      nvarchar(16) NOT NULL DEFAULT '0000000001' UNIQUE COMMENT '订单编号',
    shopping_cart_i_d      bigint(16)   NOT NULL COMMENT '商品ID',
    is_pay bigint(16)   NOT NULL COMMENT '是否已支付',
    time timestamp not null default CURRENT_TIMESTAMP comment '订单时间',
    primary key (id),
    foreign key (shopping_cart_i_d) references shopping_cart (id)
);
--购物车表
create table shopping_cart(
    id bigint(16) NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
    user_i_d bigint(16) NOT NULL COMMENT '用户ID',
    commodity_i_d bigint(16) NOT NULL COMMENT '商品ID',
    quantity bigint(16) DEFAULT 0 COMMENT '商品数量',
    primary key (id),
    foreign key (user_i_d) references user(id),
    foreign key (commodity_i_d) references commodity(id)
);
