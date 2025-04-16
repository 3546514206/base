-----------------------------------------
-- 1. 规则主表 (RULE)
-----------------------------------------
CREATE TABLE RULE (
                      ID               NUMBER(19)        PRIMARY KEY,          -- 主键
                      CODE             VARCHAR2(50)      NOT NULL,             -- 规则编码（业务唯一标识）
                      NAME             VARCHAR2(100)     NOT NULL,             -- 规则名称
                      DESCRIPTION      VARCHAR2(500),                          -- 规则描述
                      STATUS           NUMBER(1)         DEFAULT 1 NOT NULL,   -- 状态：0-禁用，1-启用
                      COMBINATION_TYPE VARCHAR2(10)      DEFAULT 'ALL' NOT NULL,-- 条件组合类型
                      PRIORITY         NUMBER(9)         DEFAULT 0 NOT NULL,   -- 执行优先级
    -- 约束
                      CONSTRAINT UQ_RULE_CODE UNIQUE (CODE),
                      CONSTRAINT CHK_RULE_STATUS CHECK (STATUS IN (0,1)),
                      CONSTRAINT CHK_RULE_COMBINATION CHECK (COMBINATION_TYPE IN ('ALL','ANY'))
);

COMMENT ON TABLE RULE IS '规则主表';
COMMENT ON COLUMN RULE.CODE IS '业务唯一标识（如：RULE_TEMP_ALARM）';
COMMENT ON COLUMN RULE.PRIORITY IS '数值越大优先级越高（0-999999999）';

-----------------------------------------
-- 2. 规则条件表 (RULE_CONDITION)
-----------------------------------------
CREATE TABLE RULE_CONDITION (
                                ID            NUMBER(19)        PRIMARY KEY,          -- 主键
                                RULE_ID       NUMBER(19)        NOT NULL,             -- 关联规则ID（逻辑外键）
                                CODE          VARCHAR2(50),                           -- 条件编码（可选）
                                NAME          VARCHAR2(100)     NOT NULL,             -- 条件名称
                                DESCRIPTION   VARCHAR2(500),                          -- 条件描述
                                FIELD         VARCHAR2(100)     NOT NULL,             -- 数据字段名
                                OPERATOR      VARCHAR2(10)      NOT NULL,             -- 比较操作符
                                PRIORITY      NUMBER(9)         DEFAULT 0 NOT NULL,   -- 条件优先级（新增字段）
                                COMPARE_VALUE VARCHAR2(255)     NOT NULL,             -- 比较值
    -- 约束
                                CONSTRAINT CHK_COND_OPERATOR CHECK (OPERATOR IN ('EQ','NE','GT','LT','GE','LE'))
);

COMMENT ON TABLE RULE_CONDITION IS '规则条件表';
COMMENT ON COLUMN RULE_CONDITION.FIELD IS '输入数据字段名（如：temperature）';
COMMENT ON COLUMN RULE_CONDITION.PRIORITY IS '条件执行顺序（数值小优先）';

-----------------------------------------
-- 3. 序列与自增逻辑
-----------------------------------------
-- 规则表序列
CREATE SEQUENCE SEQ_RULE_ID
    START WITH 1000
    INCREMENT BY 1
    NOCACHE;

-- 规则条件表序列
CREATE SEQUENCE SEQ_RULE_CONDITION_ID
    START WITH 1000
    INCREMENT BY 1
    NOCACHE;

-- 规则表自增触发器
CREATE OR REPLACE TRIGGER TRG_RULE_AUTOINC
BEFORE INSERT ON RULE
FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
SELECT SEQ_RULE_ID.NEXTVAL INTO :NEW.ID FROM DUAL;
END IF;
END;
/

-- 规则条件表自增触发器
CREATE OR REPLACE TRIGGER TRG_RULE_CONDITION_AUTOINC
BEFORE INSERT ON RULE_CONDITION
FOR EACH ROW
BEGIN
    IF :NEW.ID IS NULL THEN
SELECT SEQ_RULE_CONDITION_ID.NEXTVAL INTO :NEW.ID FROM DUAL;
END IF;
END;
/

-----------------------------------------
-- 4. 性能优化索引
-----------------------------------------
-- 规则表索引
CREATE INDEX IDX_RULE_PRIORITY ON RULE(PRIORITY DESC);  -- 优先级排序优化
CREATE INDEX IDX_RULE_STATUS ON RULE(STATUS);           -- 状态查询优化

-- 规则条件表索引
CREATE INDEX IDX_CONDITION_FIELD ON RULE_CONDITION(FIELD);       -- 字段查询优化
CREATE INDEX IDX_CONDITION_RULE_ID ON RULE_CONDITION(RULE_ID);  -- 规则关联查询优化