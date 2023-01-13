SET SEARCH_PATH TO HR;
 
-- drop the view
DROP VIEW IF EXISTS emp_details_view;

-- drop the tables one by one (and cascade the delete)
DROP TABLE IF EXISTS regions CASCADE;
DROP TABLE IF EXISTS departments CASCADE;
DROP TABLE IF EXISTS locations CASCADE;
DROP TABLE IF EXISTS jobs CASCADE;
DROP TABLE IF EXISTS job_history CASCADE;
DROP TABLE IF EXISTS employees CASCADE;
DROP TABLE IF EXISTS countries CASCADE;

-- drop the whole schema
DROP SCHEMA IF EXISTS hr CASCADE;

-- Change to another database in order to execute the next statement, otherwise there will be an error:
-- -- ERROR:  cannot drop the currently open database
-- DROP DATABASE IF EXISTS dae;
COMMIT;