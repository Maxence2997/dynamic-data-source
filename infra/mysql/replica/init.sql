CREATE USER 'root'@'%' IDENTIFIED BY 'root';
# GRANT SELECT, SHOW VIEW, SHOW DATABASES, REPLICATION CLIENT, PROCESS ON *.* TO 'root'@'%';
GRANT ALL ON *.* TO 'root'@'%';
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'password';
FLUSH PRIVILEGES;
CREATE DATABASE dynamic_data_source CHARACTER SET utf8 COLLATE utf8_general_ci;
SET GLOBAL READ_ONLY = 1;
CHANGE MASTER TO
    MASTER_HOST ='mysql_main',
    MASTER_USER ='replica',
    MASTER_PASSWORD ='replica',
    MASTER_LOG_FILE ='replicas-mysql-bin.000003', -- File文件名
    MASTER_LOG_POS = 156;
-- binlog记录位置

# ALL: This would allow a MySQL user all access
# ALL PRIVILEGES : This would allow a MySQL user all access
# CREATE : allows them to create new tables or databases
# DROP : allows them to them to delete tables or databases
# DELETE : allows them to delete rows from tables
# INSERT : allows them to insert rows into tables
# UPDATE : allow them to update table rows
# SELECT : allows them to use the Select command to read through databases
# SHOW VIEW : allows them to show view schema.
# SHOW DATABASES : allows them to show databases.
# REPLICATION CLIENT : allows them to check replication/slave status. But need to give permission on all DB.
# PROCESS : allows them to check running process. Will work with all DB only
# GRANT OPTION : allows them to grant or remove other users' privileges