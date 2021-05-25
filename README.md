***************************************************
* Project: 		COVID-19 Vaccination Scheduling Portal - Group Project
* Last Updated: May 25, 2021
***************************************************
* Contents:	
* - Introduction
*	- Technologies Used
*	- Features
*	-	Getting Started
*	- Usage
* - Team members
* - Licenses
***************************************************
0. Introduction

  Covid Vaccination Portal is a scheduling system for people who are eligible for covid vaccination and would like to book an appointment for it. The portal has a       list of vaccination clinics, and users can book and cancel appointments. Also admins can make days available fo vaccination and edit clinics capacity and hours of     operation


1.  Technologies Used:

	1.1 Java
  1.2 Spring Boot and Data
  1.3 Angular 
  1.4 AWS RDS
  1.5 AWS EC2
  1.6 JUnit/Mockito
  1.8 AWS S3
  1.9 Karma/Jasmine
  1.10 Jenkins
	

2. 	Features

 	2.1 Booking Appointment with Real-time Scheduling
  2.2 Cancelling Booking
  2.3 Making calendar days available for booking by admins
  2.4 Edit clinic capacity and hours of operation by admins
  2.5 Chatbot for Generally Asked Questions
  2.6 Email Notification for booking Appointment
  2.7 Password Encryption


3.  Getting Started  

  Execute the following command in git to import the project locally.
  - git pull https://github.com/Revature-Project2/Covid-Vaccination-portal.git
  Then inside the project folder run the following commands in a terminal to run the front-end part of the application
  npm install
  ng serve
  ** There must be Node.js installed on the machine that you are running the application on.
  This application fetches back-end resources from an AWS EC2 instance. In case it is not responsive, pull the back-end files from the 'master-back' branch and run     locally, and after running them change the base URL part of the sections that are fetching from the back-end to your local machine ("http://localhost:9010").
  In order to run the back-end files localy, import the project in Eclypse or spring suite tool 4 as a Spring Boot application. Find the project name in the Boot       Dashboard and start it. You can change the back-end port in the application.yaml file.
  
  
4.	Usage

  ![proj-0](https://user-images.githubusercontent.com/50775688/119525226-510e7c00-bd4c-11eb-934e-92191109c819.png)
 User Layer
 General public can book appointments by selecting clinic and vaccine type.
 They can see calendar with dates that have slots available for booking and days that are not available
 Once they submit appointment request they will get a confirmation number to  email that they provided while booking.
 ![proj-2](https://user-images.githubusercontent.com/50775688/119525231-510e7c00-bd4c-11eb-9f41-c4f4e97f36c1.png)
![proj-3](https://user-images.githubusercontent.com/50775688/119525234-51a71280-bd4c-11eb-8ffd-3a089a069f0c.png)
![proj4](https://user-images.githubusercontent.com/50775688/119525221-5075e580-bd4c-11eb-9aac-9f1a3e0b5817.png)
 They can cancel their appointment by entering the confirmation number and email.
 ![proj-5](https://user-images.githubusercontent.com/50775688/119525224-5075e580-bd4c-11eb-8045-fb21eae57076.png)
 Admin Layer
 Has to go to login page in order to login as  Admin and perform admin finctionalities.
 Can make calendar days as available or not available for booking.
 Can update clinics with opening hours, closing hours and number of beds.
 ![proj-1](https://user-images.githubusercontent.com/50775688/119525229-510e7c00-bd4c-11eb-8b03-9592b5418422.png)
  
  
5. Team Members

  - Borisagar, Mona
  - Deverapally, Divya
  - Karimi, Saman
  - KC, Dharam 
  - Variyath, Nisanth


6.	Licenses




