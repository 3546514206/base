/*
 酒店管理系统建表脚本
*/


/*删除数据库*/
DROP DATABASE  IF EXISTS HOTEL;

/*创建数据库*/
CREATE DATABASE IF NOT EXISTS HOTEL DEFAULT CHARACTER SET UTF8;

USE HOTEL;
/*员工管理员表*/
CREATE TABLE Staff (
	StaffNo 	        VARCHAR(20) PRIMARY KEY ,
	StaffName 	        VARCHAR(30), 
	StaffPassword		VARCHAR(100),
	StaffSex 	    	VARCHAR(4),     
    StaffAddress        VARCHAR(50),
	StaffType		    VARCHAR(20),
    StaffRemark		    VARCHAR(100)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*房间类型表*/
CREATE TABLE RoomType (
	RoomTypeId 		INT PRIMARY KEY AUTO_INCREMENT,
	Typename 		VARCHAR(20),
    TypePrice               INT
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

/*房间表*/
CREATE TABLE Room (
	RoomNo			VARCHAR(20) PRIMARY KEY,/*房间编号*/
	RoomTypeId 		INT, /*房间类型编号*/
	RoomState 		VARCHAR(50),/*状态*/
    RoomRemark		VARCHAR(100),/*备注*/
    FOREIGN KEY(RoomTypeId) references RoomType(RoomTypeId)		
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

/*入住房间表*/
CREATE TABLE InRoom(
    RoomNo               VARCHAR(20),  /*房间编号*/
	RoomTypeId 		     INT,/*房间类型编号*/
	InRoomTime		     DATETIME,/*入住时间*/
	InRoomName		     VARCHAR(20), /*客户姓名*/
    InRoomGender 	     INT,         /*客户性别*/
    InRoomPhone          VARCHAR(20), /*客户电话*/
    InRoomCash           VARCHAR(20), /*押金*/
    InRoomOrder          VARCHAR(20) PRIMARY KEY, /*订单号*/
	InRoomRemark		 VARCHAR(100),/*备注*/
    FOREIGN KEY(RoomTypeId) references RoomType(RoomTypeId),
    FOREIGN KEY(RoomNo) references Room(RoomNo)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*退房表*/
CREATE TABLE OutRoom(
	Id 			        INT PRIMARY KEY AUTO_INCREMENT,
    InRoomOrder          VARCHAR(20),/*订单号*/
	OutRoomTime		     DATETIME,/*住房总时间*/
	OutRoomPrice		 INT,         /*房费*/
	OutRoomRemark		 VARCHAR(100),/*备注*/
    FOREIGN KEY(InRoomOrder) references InRoom(InRoomOrder)

)ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*预定表*/
CREATE TABLE Book (
	Id 		        INT PRIMARY KEY AUTO_INCREMENT,
	BookTime	    DATETIME,/*预订时间*/
	RoomTypeId		INT,/*房间类型*/
    BookRemark		VARCHAR(100),/*备注*/
    FOREIGN KEY(RoomTypeId) references RoomType(RoomTypeId)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*顾客表*/
CREATE TABLE Customer (
	Id 		             INT PRIMARY KEY AUTO_INCREMENT,
	CustomerName	     VARCHAR(100),/*姓名*/
	CustomerSex 		 VARCHAR(4),   /*性别*/
    CustomerPassword	 VARCHAR(100),/*密码*/
    CustomerPhone        VARCHAR(20), /*\电话*/
    CustomerAddress      VARCHAR(50), /*住址*/
	CustomerData         DATETIME,	 /*入住时间*/
    CustomerRemark		 VARCHAR(100) /*备注*/
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
 