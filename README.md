# Hospital-Management-System
A simple and user friendly Hospital Management System created using JSF-2.0 during Hackathon 2017, Infosys, Chandigarh
<br />
## Roles:
* Patient
* Doctor
* Admin
* API

## Prerequisites
```
1) Java EE IDE.
2) Apache Tomcat Server
    - Goto Apache/conf/context.xml
    - Edit it
    - Uncomment <Manager pathname="" /> tag in the XML to disable session persistence across Tomcat restarts
3) MySql Database
    - Import the MySql dump provided.
```
## Installing

* Integrate the MySql Database by changing the database password.
```
- Edit AccountModel.java and DocAccountModel.java in workspace/Project Name/src/model
- Edit the getConnection() private function
- Change the variable pass with the password of the database
- Change the username of the database as the 2nd argument in the call to DriverManager.getConnection() function. The default username is “root”.
```
* 'import' the project into your workspace and right click on project name and run it on the server.
## Built With
* [JSF-2](https://en.wikipedia.org/wiki/JavaServer_Faces)
* [Primefaces](https://www.primefaces.org/)
* [Omnifaces](showcase.omnifaces.org/)

## Screenshots
* Homepage
    
    * Login Page
    
    ![Login Page](screenshots/login.png "Login Page")
    
    * Patient Register Page
    
    ![Patient Register Page](screenshots/patient_register.png "Patient Register Page")
    
* Patient’s Pages
    
    * Book Appointment
    
    ![Book Appointment](screenshots/patient_book_appointment.png "Book Appointment")
    
    ![Book Appointment 2](screenshots/patient_book_appointment2.png "Book Appointment 2")
    
    * View Appointment
    
    ![View Appointment](screenshots/patient_view_appointment.png "View Appointment")
    
    * View Medical History
    
    ![View Medical History](screenshots/patient_view_medical_history.png "View Medical History")
    
    * Edit Profile
    
    ![Edit Profile](screenshots/patient_edit_profile.png "Edit Profile")
    
* Doctor’s Pages
    
    * View Appointments
    
    ![View Appointments](screenshots/doctor_view_appointment.png "View Appointments")
    
    * View patient details
    
    ![View patient details](screenshots/doctor_view_patient.png "View patient details")
    
    ![View patient details2](screenshots/doctor_view_patient2.png "View patient details 2")
    
    * Edit Profile
    
    ![Edit Profile](screenshots/doctor_edit_profile.png "Edit Profile")
    
* Admin’s Pages
    
    * Add new doctor
    
    ![Add new doctor](screenshots/admin_add_a_doctor.png "Add new doctor")
    
    * Change Availability of Doctor
    
    ![Change Availability of Doctor](screenshots/admin_change_availability_doctor.png "Change Availability of Doctor")
    
## What's Next
* Work Under Progress (Some modules are being worked upon..)

## Any Issues?
Found any Bug? Please feel free to report it. :)
