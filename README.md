OrgChart_Java
=============

Getting started

Setting up MySQL

Download the latest version of MySQL Community Server at:
https://dev.mysql.com/downloads/mysql/
ie mysql-5.6.26-winx64.zip

Unpack the zip file:
ie C:\mysql-5.6.26-winx64

Set the root password:
ie C:\mysql-5.6.26-winx64\mysql -u root

at the sql prompt:
set password for 'root'@'localhost' = PASSWORD('password');

[optional] Make a convienent script for starting the db:
ie start_mysql.bat:
"C:\mysql-5.6.26-winx64\bin\mysqld.exe" --console
pause

Create schemas and tables for release and test (see scripts in repo).

Clean and build the project with Maven
ie mvn clean install

Run PIT
ie mvn org.pitest:pitest-maven:mutationCoverage