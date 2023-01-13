--DROP SCHEMA IF EXISTS hr CASCADE;
CREATE SCHEMA HR;

SET SEARCH_PATH TO hr;

/* *** REGIONS */
CREATE TABLE regions (
    region_id SERIAL,
    region_name VARCHAR(25)
);

ALTER TABLE regions ADD PRIMARY KEY (region_id); 

/* *** COUNTRIES */
CREATE TABLE countries (
    country_id CHARACTER(2) PRIMARY KEY,
    country_name VARCHAR(40),
    region_id INTEGER NOT NULL,
	/* <<!Bsp_1_Lösung_Countries!>> */
    FOREIGN KEY (region_id) REFERENCES regions (region_id)
);

/* *** LOCATIONS */
CREATE TABLE locations (
    location_id SERIAL PRIMARY KEY,
    street_address VARCHAR(40),
    postal_code VARCHAR(12),
    city VARCHAR(30) NOT NULL,
    state_province VARCHAR(25),
    country_id CHARACTER(2) NOT NULL,
    FOREIGN KEY (country_id) REFERENCES countries (country_id)
);

/* *** JOBS */
CREATE TABLE jobs (
    job_id VARCHAR(35) PRIMARY KEY, /* alternative zu serial */
    job_title VARCHAR(35) NOT NULL,
    min_salary NUMERIC(8, 2),
    max_salary NUMERIC(8, 2)
);

/* *** DEPARTMENTS */
CREATE TABLE departments (
    department_id SERIAL PRIMARY KEY,
    department_name VARCHAR(30) NOT NULL,
    manager_id INTEGER,
    location_id INTEGER,
    FOREIGN KEY (location_id) REFERENCES locations (location_id)
);

/* *** EMPLOYEES */
CREATE TABLE employees (
    employee_id SERIAL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(25) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    hire_date DATE NOT NULL,
    job_id VARCHAR(35) NOT NULL,
    salary NUMERIC(8, 2) NOT NULL,
    commission_pct NUMERIC(2,2),
    manager_id INTEGER,
    department_id INTEGER,
    FOREIGN KEY (job_id) REFERENCES jobs (job_id),
    FOREIGN KEY (department_id) REFERENCES departments (department_id),
    FOREIGN KEY (manager_id) REFERENCES employees (employee_id)
);

ALTER TABLE departments ADD CONSTRAINT dept_mgr_fk
  FOREIGN KEY (manager_id) REFERENCES employees (employee_id);
ALTER TABLE departments ALTER CONSTRAINT dept_mgr_fk DEFERRABLE INITIALLY IMMEDIATE; 


/* *** JOB_HISTORY */
CREATE TABLE job_history
    ( employee_id   SERIAL
    , start_date    DATE NOT NULL
    , end_date      DATE NOT NULL
    , job_id        VARCHAR(35) NOT NULL
    , department_id INTEGER NOT NULL
    , CHECK (end_date > start_date) -- https://www.postgresql.org/docs/9.4/ddl-constraints.html
    , CONSTRAINT jhist_emp_id_st_date_pk PRIMARY KEY (employee_id, start_date)
    , CONSTRAINT jhist_job_fk FOREIGN KEY (job_id) REFERENCES jobs
    , CONSTRAINT jhist_emp_fk FOREIGN KEY (employee_id) REFERENCES employees
    , CONSTRAINT jhist_dept_fk FOREIGN KEY (department_id) REFERENCES departments   
	);

/* *** EMP_DETAILS_VIEW */
-- PostgreSQL documentation: https://www.postgresql.org/docs/9.2/sql-createview.html

CREATE OR REPLACE VIEW emp_details_view
  (employee_id,
   job_id,
   manager_id,
   department_id,
   location_id,
   country_id,
   first_name,
   last_name,
   salary,
   commission_pct,
   department_name,
   job_title,
   city,
   state_province,
   country_name,
   region_name)
AS SELECT
  e.employee_id,
  e.job_id,
  e.manager_id,
  e.department_id,
  d.location_id,
  l.country_id,
  e.first_name,
  e.last_name,
  e.salary,
  e.commission_pct,
  d.department_name,
  j.job_title,
  l.city,
  l.state_province,
  c.country_name,
  r.region_name
FROM
  employees e,
  departments d,
  jobs j,
  locations l,
  countries c,
  regions r
WHERE e.department_id = d.department_id
  AND d.location_id = l.location_id
  AND l.country_id = c.country_id
  AND c.region_id = r.region_id
  AND j.job_id = e.job_id;

COMMIT;