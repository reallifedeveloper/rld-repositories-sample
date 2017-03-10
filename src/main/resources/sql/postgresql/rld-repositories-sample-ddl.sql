DROP SEQUENCE IF EXISTS hibernate_sequence;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department (
    id bigint NOT NULL PRIMARY KEY,
    name character varying(255) NOT NULL
);

CREATE TABLE employee (
    id bigint NOT NULL PRIMARY KEY,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    salary numeric(19,2) NOT NULL,
    department_id bigint NOT NULL REFERENCES department(id)
);

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
