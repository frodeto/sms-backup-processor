# sms-backup-processor

## Purpose

This project is intended as a demo/example project for Spring Boot and Kotlin with Maven,
and also an example usage of XML deserialization using the framework `Simple` (http://simple.sourceforge.net).

## Functionality

A Spring Boot microservice capable of converting xml files created by the app `SMS Backup & Restore`
(https://www.synctech.com.au/) to comma-separated files of text messages and image files of
mms pictures (which are stored as byte arrays in the xml backup files).  

The service contains a REST endpoint which can be used to input file locations and start the processing.  
Example request:  
```shell
curl --location 'localhost:8080/processbackup' \
--header 'Content-Type: application/json' \
--data '{"fromBackupFiles":["/Users/you/sms/sms_20130203114812.xml"],"destinationFolder":"./tmp"}'
```

## Build & run

Build with `mvn clean install`  

Run as Spring Application in IntelliJ

