# Sms Application

This application uses Twilio, Java, JDBC, and the Spark Web Framework to send a text message from a web page form.

## Build and Run
*Instructions to build and run the project.*
* Clone the repository.
* Select new project from existing sources In IntelliJ IDEA and choose the new folder you cloned.
* Add jdbc:postgresql://host:port/database to url in cred.java
* Set TwilUsername to your Twilio Account SID.
* Set TwilPassword to your Twilio Auth Token.
* Set TwilPhoneNumber to your Twilio Phone Number.
* Set password to the password for your database.
* Run SmsApp.java and go to localhost:4567

## Files
 - [SmsApp.java](https://github.com/dannyglv182/smsApp/blob/main/src/main/java/SmsApp.java)
	 - This file contains the HTTP methods. HTTP request methods are implemented through the Spark micro framework. Functions to send text messages, and carry out CRUD operations on the database are called from here. Database connectivity is established using JDBC and PostgreSql.

- [Query.java](https://github.com/dannyglv182/smsApp/blob/main/src/main/java/Query.java)
	-  This file creates sql queries as strings to use with PostgreSQL and JDBC.

- helper.java
	- This file contains the functions to send Sms messages and read Spark request.body().

- cred.java
	- This file contains the required passwords for Twilio and JDBC

## Documentation
* https://sparkjava.com/documentation
* https://www.postgresql.org/docs/current/
* https://www.twilio.com/docs/sms
