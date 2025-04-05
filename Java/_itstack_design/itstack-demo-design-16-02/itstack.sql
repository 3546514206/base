DROP TABLE school;
CREATE TABLE school
(
    id         bigint NOT NULL AUTO_INCREMENT,
    name       varchar(64),
    address    varchar(256),
    createTime datetime,
    updateTime datetime,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into school (id, name, address, createTime, updateTime)
values (1, '������ѧ', '�����к������ú�԰·5��', '2019-10-18 13:35:57', '2019-10-18 13:35:57');
insert into school (id, name, address, createTime, updateTime)
values (2, '�Ͽ���ѧ', '�й�������Ͽ�������·94��', '2019-10-18 13:35:57', '2019-10-18 13:35:57');
insert into school (id, name, address, createTime, updateTime)
values (3, 'ͬ�ô�ѧ', '�Ϻ�������·1��ͬ�ô���A¥7¥7��', '2019-10-18 13:35:57', '2019-10-18 13:35:57');
DROP TABLE user;
CREATE TABLE user
(
    id         bigint(11) NOT NULL AUTO_INCREMENT,
    name       varchar(32),
    age        int(4),
    address    varchar(128),
    entryTime  datetime,
    remark     varchar(64),
    createTime datetime,
    updateTime datetime,
    status     int(4) DEFAULT '0',
    dateTime   varchar(64),
    PRIMARY KEY (id),
    INDEX      idx_name ( name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into user (id, name, age, address, entryTime, remark, createTime, updateTime, status, dateTime)
values (1, 'ˮˮ', 18, '����ʡ�����к��������Ҵ�5��', '2019-12-22 00:00:00', '��', '2019-12-22 00:00:00',
        '2019-12-22 00:00:00', 0, '20200309');
insert into user (id, name, age, address, entryTime, remark, createTime, updateTime, status, dateTime)
values (2, '����', 18, '����ʡ�����������˾���407·', '2019-12-22 00:00:00', '��', '2019-12-22 00:00:00',
        '2019-12-22 00:00:00', 1, null);
insert into user (id, name, age, address, entryTime, remark, createTime, updateTime, status, dateTime)
values (3, '����', 19, '����ʡ�����������˾���407·', '2019-12-22 00:00:00', '��', '2019-12-22 00:00:00',
        '2019-12-22 00:00:00', 0, '20200310');
