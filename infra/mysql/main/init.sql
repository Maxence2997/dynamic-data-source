CREATE USER 'root'@'%' IDENTIFIED BY 'root';
GRANT ALL ON *.* TO 'root'@'%';
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'password';
CREATE USER 'replica'@'%' IDENTIFIED BY 'replica';
GRANT ALL ON *.* TO 'replica'@'%';
ALTER USER 'replica'@'%' IDENTIFIED WITH mysql_native_password BY 'replica';
FLUSH PRIVILEGES;
CREATE DATABASE dynamic_data_source CHARACTER SET utf8 COLLATE utf8_general_ci;
