package com.example.dh11a.restfulstudentapp;

// importing the different packages to this class

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This is the details activity which would get the details of the different students, such as, the name, gender, dob, address, postcode, studentnumber, coursetitle, startdate, bursary and email
 * This file is attached with the activity_details.xml file where i have created the text view.
 * hen the student/user clicks a student it will take them straight to this page where it will display all the students details
 * This class allows the students to view all the details for all the students.
 * This class also shows the update button, which would take the student to the update form and the delete would take the student to the delete form, wheee they will be able to delete student using the student number (ID).
 */

// Creating public DetailsActivity which extends the AppCompatActivity
public class DetailsActivity extends AppCompatActivity {
    // Creating protected void onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Creating super.onCreate
        setContentView(R.layout.activity_details); // Setting the content view and calling the activity_details2 file
        // Get the intent for extras
        Bundle extras = getIntent().getExtras();
        // Creating a student object from the student object that was passed over from
        // the MainActivity. Notice you use the key ('student') to retrieve the value/variable needed.
        final Student student = (Student) extras.get("student");
        // Print messgae
        // System.out.println("received from the intent: "+student.getname());
        // Instantiating the button to update the student
        Button UpdateStudent = (Button) findViewById(R.id.buttonUpdateStudent);

        // Instantiating the button to delete the student
        Button DeleteStudent = (Button) findViewById(R.id.buttonDeleteStudent);


        // Creating text view to findViewById to view the different student information on the app
        TextView name = (TextView) findViewById(R.id.textViewStudentname);
        TextView gender = (TextView) findViewById(R.id.textViewStudentgender);
        TextView dob = (TextView) findViewById(R.id.textViewStudentdob);
        TextView address = (TextView) findViewById(R.id.textViewStudentaddress);
        TextView postcode = (TextView) findViewById(R.id.textViewStudentpostcode);
        TextView studentNumber = (TextView) findViewById(R.id.textViewStudentstudentNumber);
        TextView courseTitle = (TextView) findViewById(R.id.textViewStudentcourseTitle);
        TextView startDate = (TextView) findViewById(R.id.textViewStudentstartDate);
        TextView bursary = (TextView) findViewById(R.id.textViewStudentbursary);
        TextView email = (TextView) findViewById(R.id.textViewStudentemail);


        // Setting the text for the text view
        name.setText(student.name);
        gender.setText(student.getgender());
        dob.setText(student.getdob());
        address.setText(student.getaddress());
        postcode.setText(student.getpostcode());
        studentNumber.setText(student.getstudentNumber());
        courseTitle.setText(student.getcourseTitle());
        startDate.setText(student.getstartDate());
        bursary.setText(student.getbursary());
        email.setText(student.getemail());

        // Creating on click listener for UpdateStudent button
        UpdateStudent.setOnClickListener(new View.OnClickListener() {
            // Creating public void onClick
            public void onClick(View view) {
                // Creating intent and calling the Update student class
                Intent intent1 = new Intent(getApplicationContext(), UpdateStudentActivity.class);
                // Creating the intent to put the extras and calling the student class
                intent1.putExtra("student", student);
                startActivity(intent1); // Start the activity
            } // Close public void onClick
        }); // Close setOnClickListener for button UpdateStudent


        // Creating on click listener for DeleteStudent button
        DeleteStudent.setOnClickListener(new View.OnClickListener() {
            // creating public void onClick
            public void onClick(View view) {
                // Creating intent to call the delete student activity class
                Intent intent2 = new Intent(getApplicationContext(), DeleteStudentActivity.class);
                // Creating intent for put extras and calling the student class
                intent2.putExtra("student", student);
                startActivity(intent2); // Starting the activity
            } // close public void onClick
        }); // Close setOnClickListener for button DeleteStudent
    } // Close protected void on create


} // Close details activity classa