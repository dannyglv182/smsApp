# Sms Application
## This application allows a user to create a questionnaire style form and send it via text message. Contacts can then respond to the user through text message.

 - SmsApp.java
	 - This file contains the HTTP methods for resources. HTTP request methods are implemented through the Spark micfro framwork. Functions to send text messages, and carry out CRUD operations on the database are called from here, but not implemented here.

- Query.java
	-  This file contains the functions to perform CRUD operations against the database. Database connectivity is established using JDBC and MySQL

- helper.java
	- This file contains the functions to send Sms messages and parse request bodies.

- cred.java
	- This file contains the required passwords for Twilio and JDBC
