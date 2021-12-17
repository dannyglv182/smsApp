# Sms Application

This application uses Twilio, Java, JDBC, and the Spark Web Framework to send a text message from a web page form.

## Build and Run
*Instructions to build and run your project.*
* Clone the repository.
* Select new project from existing sources In IntelliJ IDEA and choose the new folder you cloned.
* Create a file named cred.java in src/main/java
* Add the following to cred.java
```
	public class cred {
    		static String url = "";
    		static String password = "";
    		static String twilUsername = "";
    		static String twilPassword = "";
    		static String twilPhoneNUmber = "";
	}
```
* Download and install postgresql if you haven't yet and create a database.
* Set url to jdbc:postgresql://host:port/database
* Set password to the password for your database.
* Create a Twilio Account if you haven't yet.
* Set TwilUsername to your TWilio Account SID.
* Set TwilPassword to your Twilio Auth Token.
* Set TwilPhoneNumber to your Twilio Phone Number.
* Run SmsApp.java and go to localhost:4567

 - SmsApp.java
	 - This file contains the HTTP methods. HTTP request methods are implemented through the Spark micro framwork. Functions to send text messages, and carry out CRUD operations on the database are called from here. Database connectivity is established using JDBC and PostgreSql.

- Query.java
	-  This file creates sql queries as strings to use with PostgreSQL and JDBC.

- helper.java
	- This file contains the functions to send Sms messages and read Spark request.body().

- cred.java
	- This file contains the required passwords for Twilio and JDBC
