------------------------------
-- 1. 规则主表 (RULE)
------------------------------
CREATE TABLE RULE (
                      ID               NUMBER(19)        PRIMARY KEY,          -- 主键
                      CODE             VARCHAR2(50)      NOT NULL UNIQUE,      -- 唯一业务编码
                      NAME             VARCHAR2(100)     NOT NULL,             -- 规则名称
                      DESCRIPTION      VARCHAR2(500),                         -- 规则描述
                      STATUS           NUMBER(1)         DEFAULT 1 NOT NULL,   -- 状态: 0-禁用,1-启用
                      COMBINATION_TYPE VARCHAR2(10)      DEFAULT 'ALL' NOT NULL,-- 条件组合类型
                      PRIORITY         NUMBER(9)         DEFAULT 0 NOT NULL,   -- 执行优先级
    -- 数据校验
                      CONSTRAINT CHK_RULE_STATUS CHECK (STATUS IN (0,1)),
                      CONSTRAINT CHK_COMBINATION_TYPE CHECK (COMBINATION_TYPE IN ('ALL','ANY'))
);

COMMENT ON COLUMN RULE.CODE IS '业务唯一标识（如：RULE_TEMP_ALARM）';

------------------------------
-- 2. 规则条件表 (RULE_CONDITION)
------------------------------
CREATE TABLE RULE_CONDITION (
                                ID            NUMBER(19)        PRIMARY KEY,          -- 主键
                                RULE_ID       NUMBER(19)        NOT NULL,             -- 逻辑关联规则ID
                                CODE          VARCHAR2(50),                           -- 条件编码
                                NAME          VARCHAR2(100)     NOT NULL,             -- 条件名称
                                DESCRIPTION   VARCHAR2(500),                          -- 新增条件描述
                                FIELD         VARCHAR2(100)     NOT NULL,             -- 数据字段名
                                OPERATOR      VARCHAR2(10)      NOT NULL,             -- 比较操作符
                                COMPARE_VALUE VARCHAR2(255)     NOT NULL,             -- 比较值
    -- 数据校验
                                CONSTRAINT CHK_CONDITION_OPERATOR CHECK (OPERATOR IN ('EQ','NE','GT','LT','GE','LE'))
);

COMMENT ON COLUMN RULE_CONDITION.RULE_ID IS '逻辑关联规则ID（无外键约束）';
COMMENT ON COLUMN RULE_CONDITION.DESCRIPTION IS '条件详细说明';

------------------------------
-- 3. 序列与自增逻辑
------------------------------
-- 规则表序列
CREATE SEQUENCE SEQ_RULE_ID START WITH 1000 INCREMENT BY 1 NOCACHE;

-- 规则条件表序列
CREATE SEQUENCE SEQ_RULE_CONDITION_ID START WITH 1000 INCREMENT BY 1 NOCACHE;

-- 规则表自增触发器
CREATE OR REPLACE TRIGGER TRG_RULE_AUTOINC
BEFORE INSERT ON RULE FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
SELECT SEQ_RULE_ID.NEXTVAL INTO :NEW.ID FROM DUAL;
END IF;
END;
/

-- 规则条件表自增触发器
CREATE OR REPLACE TRIGGER TRG_RULE_CONDITION_AUTOINC
BEFORE INSERT ON RULE_CONDITION FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
SELECT SEQ_RULE_CONDITION_ID.NEXTVAL INTO :NEW.ID FROM DUAL;
END IF;
END;
/

------------------------------
-- 4. 索引优化方案
------------------------------
-- 规则表
CREATE INDEX IDX_RULE_PRIORITY ON RULE(PRIORITY DESC, STATUS);  -- 优先级排序优化
CREATE UNIQUE INDEX UQ_RULE_CODE ON RULE(CODE);                 -- 编码查询加速

-- 规则条件表
CREATE INDEX IDX_CONDITION_FIELD ON RULE_CONDITION(FIELD);      -- 字段查询优化
CREATE INDEX IDX_CONDITION_RULE ON RULE_CONDITION(RULE_ID);     -- 规则关联查询