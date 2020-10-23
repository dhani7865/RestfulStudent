package com.example.dh11a.restfulstudentapp;

// Import packages

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

// Creating public class for delete student
public class DeleteStudentActivity extends AppCompatActivity {
    // Creating fina for hashmap
    final HashMap<String, String> params = new HashMap<String, String>();

    // Creating protected void onCreate
    protected void onCreate(Bundle savedInstanceState) {
        // Creating super for onCreate
        super.onCreate(savedInstanceState);
        // Setting content view and calling the activity delete student
        setContentView(R.layout.activity_delete_student);

        // Creating bundle to get extras
        Bundle extras = getIntent().getExtras();
        // Creating a student object from the student object that was passed over from
        // the MainActivity. Notice you use the key ('student') to retrieve the value/variable needed.
        Student student = (Student) extras.get("student");

        // Run network on main thread hack
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        // Setting the thread policy
        //StrictMode.setThreadPolicy(policy);

        // Creating text view to findViewById to view the different student information on the app
        final EditText name = (EditText) findViewById(R.id.DeleteStudentName);
        final EditText gender = (EditText) findViewById(R.id.DeleteStudentGender);
        final EditText dob = (EditText) findViewById(R.id.DeleteStudentDOB);
        final EditText address = (EditText) findViewById(R.id.DeleteStudentAddress);
        final EditText postcode = (EditText) findViewById(R.id.DeleteStudentPostcode);
        final EditText studentNumber = (EditText) findViewById(R.id.DeleteStudentStudentNumber);
        final EditText courseTitle = (EditText) findViewById(R.id.DeleteStudentCourseTitle);
        final EditText startDate = (EditText) findViewById(R.id.DeleteStudentStartDate);
        final EditText bursary = (EditText) findViewById(R.id.DeleteStudentBursary);
        final EditText email = (EditText) findViewById(R.id.DeleteStudentEmail);

        // Instantiating the button for delete student
        Button DeleteStudentButton = (Button) findViewById(R.id.buttonConfirmDelete);

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


        // Creating on click listener for the DeleteStudentButton
        DeleteStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating gson object
                Gson gson = new Gson();
                // Setting the text to get the text from the Tostring
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
                // Creating new student
                Student student = new Student(nameS, genderS, dobS, addressS, postcodeS, studentNumberS, courseTitleS, startDateS, bursaryS, emailS);
                // Creating string for student json and converting students to json format
                String StudentJson = gson.toJson(student);
                // Printing out students in json format
                System.out.println(StudentJson);
                // Creating params for the api key
                params.put("apikey", "032794ed45");
                // Creating param for student number to delete the student
                params.put("studentnumber", String.valueOf(student.getstudentNumber()));
                // Creating string url and setting the url to delete the student
                String url = "http://radikaldesign.co.uk/sandbox/studentapi/delete.php ";
                //performPostCall(url, params); // Performing post call
                new deleteStudent().execute();
            } // close public void onClick
        }); // Close on click listener for delte student button
    } // Close onCreate class

    // Creating public string for performPostCall and creating string requestURL, hasmap string and postDataParams
    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {
        // URL variable
        URL url;
        // Creating empty string for the response
        String response = "";
        // Creating try for the pose request
        try {
            // Creating url variable and requesting url
            url = new URL(requestURL);

            // Creating the connection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Creating connection for read time out and setting it to 15000
            conn.setReadTimeout(15000);
            // Creating set time out connection
            conn.setConnectTimeout(15000);
            // Creating request method for post
            conn.setRequestMethod("POST");
            // Creating do input for the connection and setting it boolean value of true
            conn.setDoInput(true);
            // Creating do output connection and setting it boolean value as true
            conn.setDoOutput(true);

            // Posting the data to the connection using OutputStream and BufferedWriter
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            // Post value data encoded
            // Printing the post data string
            System.out.println(getPostDataString(postDataParams));
            writer.write(getPostDataString(postDataParams));
            // Flushing the writer
            writer.flush();
            // Close the writer
            writer.close();
            // Closing the output stream
            os.close();

            // Getting the server reponse code
            int responseCode = conn.getResponseCode();
            // Printing out the response code
            System.out.println("responseCode = " + responseCode);
            // Creating if statement for the response code
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                // Creating toast for the text
                //Toast.makeText(this, "Student Has Been Deleted", Toast.LENGTH_LONG).show();
                // Creating string line
                String line;
                // Creating new buffered reader and getting input stream
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));
                // Creating while loop for readline not equal to null
                while ((line = br.readLine()) != null) {
                    response += line; // Adding line
                } // Close while loop for line
            } // Close if statment for responce code
            // Otherwise print out error message
            else {
                //Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                // Empty response
                response = "";
            } // Close else
        } // Close try
        // Creating catch exception e
        catch (Exception e) {
            e.printStackTrace();
        } // Close catch exception e
        // Print response
        System.out.println("response = " + response);
        return response; // Return the response
    } // Close public string performPostCall

    // Creating private string for get post data string
    private String getPostDataString(HashMap<String, String> params) throws
            UnsupportedEncodingException {
        // Creating string buffer and creating new string builder
        StringBuilder result = new StringBuilder();

        boolean first = true; // Creating boolean first and setting it a value as true
        // Creating for loop for the Map.Entry
        for (Map.Entry<String, String> entry : params.entrySet()) {
            // Creating if statement for first
            if (first)
                first = false; // Creating variable name for first and setting it boolean value as false
            else // Otherwise rsult.append (&)
                result.append("&");
            // Creating result.append to get the entry key
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            // Result.append for equal
            result.append("=");
            // Creating result.append to get the value
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        } // Close for loop for map
        return result.toString(); // Return the result
    } // Close get post data string

    /*
    * Creating class for Delete student for the async task.
    * The async class will run the network call in the background.
    * AsyncTask enables easy use of the UI thread. This class allows you to perform background operations and
    * publish results on the UI thread effectively without having to manipulate threads and/or handlers.
    */

    // Creating class for delete student for the async task
    class deleteStudent extends AsyncTask<Void, Void, Void> {
        // Creating Protected void to run the delete api network call in the background
        protected Void doInBackground(Void... voids) {
            // Performing post call for the student api for the delete student
            performPostCall("http://radikaldesign.co.uk/sandbox/studentapi/delete.php", params);
            return null; // Return null
        } // Close protected void DoInBackground
    } // Close delete student class for the async task
} // Close delete student activity class