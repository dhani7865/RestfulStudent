package com.example.dh11a.restfulstudentapp;

import java.io.Serializable;


/**
 * Created by Dhanyaal on 17/02/2018.
 * This class is used to create the student
 * This class is th emain student class where i have created all the setters and getters.
 * This class wil be linked to all the other classes, without this class all the data wouldn't be able be displayed.
 * Therefore, in order to get the data to be shown, I would need to link this class, to the other classes.
 */

// Creating public class for student which implements Serializable
public class Student implements Serializable {
    // Creating string variables for Name, Gender, DOB, Address, Postcode, integer for StudentNumber, CourseTitle, startDate, Float for the Bursary and Email
    String name;
    String gender;
    String dob;
    String address;
    String postcode;
    String studentNumber;
    String courseTitle;
    String startDate;
    String bursary;
    String email;

    // Creating string constructors for the different fields
    public Student(String name, String gender, String dob, String address, String postcode, String studentNumber, String courseTitle, String startDate, String bursary, String email) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.postcode = postcode;
        this.studentNumber = studentNumber;
        this.courseTitle = courseTitle;
        this.startDate = startDate;
        this.bursary = bursary;
        this.email = email;
    } // close constructor

    // Creating public void for setname and getname
    public void setname(String name) {
        this.name = name;
    } // Close public void setname

    // Creating public string for getname, which would return the Name
    public String getname() {
        return this.name;
    } // Close public string getname

    // Creating public void for setgender and getgender
    public void setgender(String gender) {
        this.gender = gender;
    }// close public void setgender

    // Creating public string for getgender, which would return the gender
    public String getgender() {
        return gender;
    }// Close public void getgender

    // Creating public void for setdob and getdob
    public void setdob(String dob) {
        this.dob = dob;
    }// Close public void setdob

    // Creating public string for getdob, which would return the dob
    public String getdob() {
        return dob;
    }// Close public void getdob

    // Creating public void for setaddress and getaddress
    public void setaddress(String address) {
        this.address = address;
    }// Close public void setaddress

    // Creating public string for Getaddress, which would return the address
    public String getaddress() {
        return this.address;
    }// Close public void getaddress

    // Creating public void for setpostcode and getpostocde
    public void setpostcode(String postcode) {
        this.postcode = postcode;
    }// Close public void setpostcode

    // Public string for getpostcode, which would return the postcode
    public String getpostcode() {
        return postcode;
    }// Close public void getpostcode

    // Creating public void for setstudentNumber and getstudentNumber
    public void setstudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }// Close public void setstudentNumber

    // Creating public string for getstudentNumber, which would return the studentNumber
    public String getstudentNumber() {
        return studentNumber;
    }// Close public void getstudentNumber

    // Creating public void for setcourseTitle and getcourseTitle
    public void setcourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }// Close public void setcourseTitle

    // Creating public string for GetcourseTitle, which would return the courseTitle
    public String getcourseTitle() {
        return courseTitle;
    }// Close public void getcourseTitle

    // Creating public void for setstartDate and getstartDate
    public void setstartDate(String startDate) {
        this.startDate = startDate;
    }// Close public void setstartDate

    // Creating public string for getstartDate, which would return the startDate
    public String getstartDate() {
        return startDate;
    }// Close public void getstartDate

    // Creating public void for setbursary and getbursary
    public void setbursary(String bursary) {
        this.bursary = bursary;
    }// Close public void setbursary

    // Creating public string for getbursary, which would return the bursary
    public String getbursary() {
        return bursary;
    }// Close public void getbursary

    // Creating public void for setemail and getemail
    public void setemail(String email) {
        this.email = email;
    }// Close public void setemail

    // Creating public string for getemail, which would return the email
    public String getemail() {
        return email;
    }// Close public void getemail
} // Close public class student