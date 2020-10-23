package com.example.dh11a.restfulstudentapp;

// Importing the packages

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

// Creating public class for update student which extends the app compat activity
public class UpdateStudentActivity extends AppCompatActivity {
    final HashMap<String, String> params = new HashMap<>();
    Context c = this;

    // Creating protected void onCreate
    protected void onCreate(Bundle savedInstanceState) {
        // Creating super onCreate
        super.onCreate(savedInstanceState);
        // Setting the content view and calling the activity update student
        setContentView(R.layout.activity_update_student);
        // Creating bundle extras
        Bundle extras = getIntent().getExtras();
        // Creating extras for the student and calling the student class
        Student student = (Student) extras.get("student");

        // Creating fina for hashmap


//        // Run network on main thread hack
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        // Setting the thread policy
//        StrictMode.setThreadPolicy(policy);

        // Instantiating the button for update button
        Button buttonUpdateStudent = (Button) findViewById(R.id.buttonUpdateStudent);

        // Creating final edit text to update student name
        final EditText name = (EditText) findViewById(R.id.UpdateStudentName);
        // Creating final edit text to update student gender
        final EditText gender = (EditText) findViewById(R.id.UpdateStudentGender);
        // Creating final edit text to update the dob
        final EditText dob = (EditText) findViewById(R.id.UpdateStudentDOB);
        // Creating final edit text to update the Address
        final EditText address = (EditText) findViewById(R.id.UpdateStudentAddress);
        // Creating final edit text to update the postcodes
        final EditText postcode = (EditText) findViewById(R.id.UpdateStudentPostcode);
        // Creating final edit text to update the StudentnNumber
        final EditText studentNumber = (EditText) findViewById(R.id.UpdateStudentStudentNumber);
        // Creating final edit text to update the coursetitle
        final EditText courseTitle = (EditText) findViewById(R.id.UpdateStudentCourseTitle);
        // Creating final edit text to update the startDate
        final EditText startDate = (EditText) findViewById(R.id.UpdateStudentStartDate);
        // Creating final edit text to update the Bursary
        final EditText bursary = (EditText) findViewById(R.id.UpdateStudentBursary);
        // Creating final edit text to update the email
        final EditText email = (EditText) findViewById(R.id.UpdateStudentEmail);
        // Setting the text and calling the students from the student class
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

        // Creating on click listener for the update button
        buttonUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            // creating public void on click
            public void onClick(View view) {
                // Creating gson object
                Gson gson = new Gson();
                // Creating student object called s
                String nameS = name.getText().toString();
                String genderS = gender.getText().toString();
                String dobS = dob.getText().toString();
                String addressS = address.getText().toString();
                String postcodeS = postcode.getText().toString();
                String studentNumberS = studentNumber.getText().toString();
                String courseTitleS = courseTitle.getText().toString();
                String startDateS = startDate.getText().toString();
                String bursaryS = bursary.getText().toString();
                String emailS = email.getText().toString();
                Student s = new Student(nameS, genderS, dobS, addressS, postcodeS, studentNumberS, courseTitleS, startDateS, bursaryS, emailS);
                // Converting student json string to json
                String StudentJson = gson.toJson(s);
                // Print student in json format
                System.out.println(StudentJson);
                // Putting the string into hashmap
                params.put("json", StudentJson);
                // The url which you use to update student in json format to the database
                // Calling the api key to update the student
                params.put("apikey", "032794ed45");
                // Url to update the student
                String url = "http://radikaldesign.co.uk/sandbox/studentapi/update.php";

                // Perform the post call for the url and params
                //performPostcall(url, params);
                new UpdateStudent().execute();
            } // Close public void view
        }); // Close on click listener for the button
    } // Close public void onCreate

    // Creating public string method for performPostcall
    public String performPostcall(String requestURL, HashMap<String, String> postDataParams) {
        // URL variable
        URL url;
        // Creating empty string for the response
        String response = "";
        // Creating try for the url connection
        try {
            url = new URL(requestURL); // Request url
            // Creating the connection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Creating read time out
            conn.setReadTimeout(15000);
            // Creating connect time out
            conn.setConnectTimeout(15000);
            // Setting the request method for post
            conn.setRequestMethod("POST");
            // Setting the do input aboolean value as true
            conn.setDoInput(true);
            // Setting the do output a boolean value as true
            conn.setDoOutput(true);

            // Write/send/post data to the connection using output stream and bufferedwriter
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            // Write/send/post key/value data (url encoded) to the server
            writer.write(getPostDataString(postDataParams));

            // Flush the writer and close the writer
            writer.flush();
            writer.close();

            // Close the output stream
            os.close();

            // Get the server response code to determine what to do next (i.e. sucess/error)
            int responseCode = conn.getResponseCode();
            // Print the response code
            System.out.println("responseCode = " + responseCode);

            // If statement for response code
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                // Creating toast
                //Toast.makeText(this, "Student updated in the database:)", Toast.LENGTH_LONG).show();

                // Creating string line
                String line;
                // Creating buffered reader
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                // Creating while loop for read line
                while ((line = br.readLine()) != null) {
                    response += line;
                } // Close while loop for readline
            } // Close if statement for http url connection
            // Otherwise
            else {
                // Creating toast to print out error message
                //Toast.makeText(this, "Error failed to update student :(", Toast.LENGTH_LONG).show();
                // Print response
                response = "";
            } // Close else
        } // Close try
        // Creating catch for tor the ProtocolException
        catch (ProtocolException e) {
            e.printStackTrace(); // Print stack trace
        } // Close catch
        // Creating catch for UnsupportedEncodingException
        // Printing stack trace
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } // close catch for support encoding exception
        // Creating catch for malformed URl exception
        catch (MalformedURLException e) {
            e.printStackTrace(); // Print stack trace
        } // Close catch for malformed url exception
        // Creating catch for IOException
        catch (IOException e) {
            e.printStackTrace(); // Print stack trace
        } // Close catch for IOexception

        // Print the response
        System.out.println("response = " + response);
        // Return response
        return response;
    } // Close public String performPostcall

    // This method converts a hashmap to a url query string of key/values pairs
    //(e.g. : name = kaleem & job= tutor&.....
    // Creating private string method for getPostdataString
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        // Creating string builder
        StringBuilder result = new StringBuilder();
        // Creating boolean first equal to true
        boolean first = true;

        // Creating for loop for map
        for (Map.Entry<String, String> entry : params.entrySet()) {
            // Creating if statement for first
            if (first)
                // Creating variable name for first equal to false
                first = false;
                // Ctherwise
            else
                // Result append
                result.append("&");

            // Creating result.append for the url encoder to get the key
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            // Result append for equal
            result.append("=");
            // Creating result.append for the url encoder to get the value
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        } // Close if statement for first
        // return the result
        return result.toString();
    } // Close private string method for getPostDataString

    /*
     * Creating class for update student for the async task.
     * The async class will run the network call in the background.
     * AsyncTask enables easy use of the UI thread. This class allows you to perform background operations and
     * publish results on the UI thread effectively without having to manipulate threads and/or handlers.
     */
    // Creating class for the async class for the insert student.
    class UpdateStudent extends AsyncTask<Void, Void, Void> {
        // Creating protected void to do the network in the background
        protected Void doInBackground(Void... voids) {
            // Performing the post call for the url
            performPostcall("http://radikaldesign.co.uk/sandbox/studentapi/update.php", params);
            return null; // Return null
        } // Close protected void doInbackground
    } // Close update student class for the async task
} // Close add student activity class