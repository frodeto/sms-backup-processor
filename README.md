# sms-backup-processor

## Purpose

This project is primarily intended as a demo/test project for Spring Boot + Kotlin with Maven.  

It also demonstrates XML deserialization using the framework `Simple` (http://simple.sourceforge.net).  

## Functionality

A Spring Boot microservice capable of converting xml files created by the app `SMS Backup & Restore`
(https://www.synctech.com.au/) to comma-separated files of text messages and image files of
mms pictures.  

The service contains an http endpoint which can be used to input file locations and start the processing.  
Example request:  
```shell
curl --location 'localhost:8080/processbackup' \
--header 'Content-Type: application/json' \
--data '{"fromBackupFiles":["/Users/you/sms/sms_20130203114812.xml", "/Users/you/sms/sms_20211106083421.xml"],"destinationFolder":"./tmp"}'
```

## Build & run

Build with `mvn clean install`  

Run in IntelliJ with provided run config or with `java -jar`.  

