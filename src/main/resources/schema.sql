CREATE TABLE IF NOT EXISTS USERS(
    ID SERIAL PRIMARY KEY,
    VERSION INTEGER,
    NAME VARCHAR(50),
    EMAIL VARCHAR(50),
    PASSWORD VARCHAR(100),
    CODE VARCHAR(50),
    ROLE_ID INTEGER,
    IS_ACTIVE BOOLEAN
);

CREATE TABLE IF NOT EXISTS ROLE(
    ID SERIAL PRIMARY KEY,
    ROLE_NAME VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS USER_FILE(
    ID SERIAL PRIMARY KEY,
    FILE_NAME VARCHAR(100),
    FILE_DATA BYTEA,
    DESCRIPTION VARCHAR(50),
    USER_ID INTEGER
);