CREATE DATABASE dynamic_data_source;

-- Provide privilege to slave user from slave database
CREATE USER repl_user WITH LOGIN REPLICATION PASSWORD 'repl_user';
GRANT CONNECT ON DATABASE dynamic_data_source TO repl_user;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO repl_user;
GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO repl_user;
GRANT USAGE ON SCHEMA public TO repl_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO repl_user;