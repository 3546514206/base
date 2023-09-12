/*
 �Ƶ����ϵͳ����ű�
*/


/*ɾ�����ݿ�*/
DROP DATABASE  IF EXISTS HOTEL;

/*�������ݿ�*/
CREATE DATABASE IF NOT EXISTS HOTEL DEFAULT CHARACTER SET UTF8;

USE HOTEL;
/*Ա������Ա��*/
CREATE TABLE Staff (
	StaffNo 	        VARCHAR(20) PRIMARY KEY ,
	StaffName 	        VARCHAR(30), 
	StaffPassword		VARCHAR(100),
	StaffSex 	    	VARCHAR(4),     
    StaffAddress        VARCHAR(50),
	StaffType		    VARCHAR(20),
    StaffRemark		    VARCHAR(100)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*�������ͱ�*/
CREATE TABLE RoomType (
	RoomTypeId 		INT PRIMARY KEY AUTO_INCREMENT,
	Typename 		VARCHAR(20),
    TypePrice               INT
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

/*�����*/
CREATE TABLE Room (
	RoomNo			VARCHAR(20) PRIMARY KEY,/*������*/
	RoomTypeId 		INT, /*�������ͱ��*/
	RoomState 		VARCHAR(50),/*״̬*/
    RoomRemark		VARCHAR(100),/*��ע*/
    FOREIGN KEY(RoomTypeId) references RoomType(RoomTypeId)		
)ENGINE=INNODB DEFAULT CHARSET=UTF8;

/*��ס�����*/
CREATE TABLE InRoom(
    RoomNo               VARCHAR(20),  /*������*/
	RoomTypeId 		     INT,/*�������ͱ��*/
	InRoomTime		     DATETIME,/*��סʱ��*/
	InRoomName		     VARCHAR(20), /*�ͻ�����*/
    InRoomGender 	     INT,         /*�ͻ��Ա�*/
    InRoomPhone          VARCHAR(20), /*�ͻ��绰*/
    InRoomCash           VARCHAR(20), /*Ѻ��*/
    InRoomOrder          VARCHAR(20) PRIMARY KEY, /*������*/
	InRoomRemark		 VARCHAR(100),/*��ע*/
    FOREIGN KEY(RoomTypeId) references RoomType(RoomTypeId),
    FOREIGN KEY(RoomNo) references Room(RoomNo)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*�˷���*/
CREATE TABLE OutRoom(
	Id 			        INT PRIMARY KEY AUTO_INCREMENT,
    InRoomOrder          VARCHAR(20),/*������*/
	OutRoomTime		     DATETIME,/*ס����ʱ��*/
	OutRoomPrice		 INT,         /*����*/
	OutRoomRemark		 VARCHAR(100),/*��ע*/
    FOREIGN KEY(InRoomOrder) references InRoom(InRoomOrder)

)ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*Ԥ����*/
CREATE TABLE Book (
	Id 		        INT PRIMARY KEY AUTO_INCREMENT,
	BookTime	    DATETIME,/*Ԥ��ʱ��*/
	RoomTypeId		INT,/*��������*/
    BookRemark		VARCHAR(100),/*��ע*/
    FOREIGN KEY(RoomTypeId) references RoomType(RoomTypeId)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
/*�˿ͱ�*/
CREATE TABLE Customer (
	Id 		             INT PRIMARY KEY AUTO_INCREMENT,
	CustomerName	     VARCHAR(100),/*����*/
	CustomerSex 		 VARCHAR(4),   /*�Ա�*/
    CustomerPassword	 VARCHAR(100),/*����*/
    CustomerPhone        VARCHAR(20), /*\�绰*/
    CustomerAddress      VARCHAR(50), /*סַ*/
	CustomerData         DATETIME,	 /*��סʱ��*/
    CustomerRemark		 VARCHAR(100) /*��ע*/
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
 