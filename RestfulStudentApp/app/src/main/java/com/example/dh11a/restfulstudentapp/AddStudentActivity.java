package com.example.dh11a.restfulstudentapp;

// importing packages

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

// Creating public class for add student which extends app compat activity
public class AddStudentActivity extends AppCompatActivity {
    // Creating final for hashmap and creating new hash map
    final HashMap<String, String> params = new HashMap<>();

    // Creating string object for all students, to store all students into array, studentlist, add student button and Async task button
    String[] Student;
    ListView StudentsList;
    Button buttonAddStudent;
    Context c = this;

    // Creating array to store all students
    ArrayList<Student> allStudents = new ArrayList<>();

    // Creating protected void for onCreate
    protected void onCreate(Bundle savedInstanceState) {
        // Creating super for saved instance state
        super.onCreate(savedInstanceState);
        // Setting the content view and calling the activity add student
        setContentView(R.layout.activity_add_student);

        // Creating Gson object
        Gson gson = new Gson();

        // Run network on main thread hack
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        // Setting the thread policy
//        StrictMode.setThreadPolicy(policy);

        // Instantiating the add student button object
        Button AddStudentButton = (Button) findViewById(R.id.buttonAddNewStudent);

        // Creating final edit text for the name
        final EditText name = (EditText) findViewById(R.id.editTextStudentName);
        // Creating final edit text for the gender
        final EditText gender = (EditText) findViewById(R.id.editTextStudentGender);
        // Creating final edit text for the dob
        final EditText dob = (EditText) findViewById(R.id.editTextStudentDOB);
        // Creating final edit text for the Address
        final EditText address = (EditText) findViewById(R.id.editTextStudentAddress);
        // Creating final edit text for the postcode
        final EditText postcode = (EditText) findViewById(R.id.editTextStudentPostcode);
        // Creating final edit text for the StudentnNumber
        final EditText studentnNumber = (EditText) findViewById(R.id.editTextStudentStudentNumber);
        // Creating final edit text for the coursetitle
        final EditText courseTitle = (EditText) findViewById(R.id.editTextStudentCourseTitle);
        // Creating final edit text for the startDate
        final EditText startDate = (EditText) findViewById(R.id.editTextStudentStartDate);
        // Creating final edit text for the Bursary
        final EditText bursary = (EditText) findViewById(R.id.editTextStudentBursary);
        // Creating final edit text for the email
        final EditText email = (EditText) findViewById(R.id.editTextStudentEmail);

        // Creating on click listener for the add student button
        AddStudentButton.setOnClickListener(new View.OnClickListener() {
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
                String studentnNumberS = studentnNumber.getText().toString();
                String courseTitleS = courseTitle.getText().toString();
                String startDateS = startDate.getText().toString();
                String bursaryS = bursary.getText().toString();
                String emailS = email.getText().toString();
                // Creating student object and creating new student
                Student s = new Student(nameS, genderS, dobS, addressS, postcodeS, studentnNumberS, courseTitleS, startDateS, bursaryS, emailS);
                // Converting student json string to json
                String StudentJson = gson.toJson(s);
                // Print student in json format
                System.out.println(StudentJson);
                // Putting the string into hashmap
                params.put("json", StudentJson);
                // The url which you use to add student in json format to the database
                // Calling the api key
                params.put("apikey", "032794ed45");
                // Creating string url and calling the add student api
                String url = "http://radikaldesign.co.uk/sandbox/studentapi/add.php";

                new insertStudent().execute(); // Calling the insert student function method in the async task

                // Perform the post call for the url and params
                //performPostcall(url, params);
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
            // Setting the do input boolean value as true
            conn.setDoInput(true);
            // Setting the do output a boolean value as true
            conn.setDoOutput(true);

            // Write/send/post data to the connection using output stream and bufferedwriter
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            // Write/send/post key/value data (url encoded) to the server
            writer.write(getPostDataString(postDataParams));

            // Close the writer and flushing the writer
            writer.flush();
            writer.close();

            // Close the output stream
            os.close();

            // Get the server response code to determine what to do next (i.e. sucess/error)
            int responseCode = conn.getResponseCode();

            // Print the response code
            System.out.println("responseCode = " + responseCode);


            // Creating if statement for response code
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                // Creating toast
                //Toast.makeText(this, "Student added to the database successfully:)", Toast.LENGTH_LONG).show();

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
                //Toast.makeText(this, "Error failed to add student :(", Toast.LENGTH_LONG).show();
                // Print response
                response = "";
            } // Close else
        } // Close try
        // Creating catch tor the ProtocolException
        catch (ProtocolException e) {
            e.printStackTrace(); // Print stack trace
        } // Close catch
        // Creating catch for UnsupportedEncodingException
        catch (UnsupportedEncodingException e) {
            e.printStackTrace(); // Print stack trace
        } // Close catch for UnsupportedEncodingException
        // Creating MalformedURLException
        catch (MalformedURLException e) {
            e.printStackTrace(); // Print stack trace
        } // Close catch for MalformedURLException
        // Creating IOException
        catch (IOException e) {
            e.printStackTrace(); // Print stack trace
        } // Close IOException

        // Print the response
        System.out.println("response = " + response);
        // Return response
        return response;
    } // Close public String performPostcall

    // This method converts a hashmap to a url query string of key/values pairs
    //(e.g. : name = kaleem & job= tutor&.....
    // Creating private string method for getPostdataString
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        // Creating new string builder
        StringBuilder result = new StringBuilder();
        // Creating boolean first equal to true
        boolean first = true;

        // Creating for loop for map
        for (Map.Entry<String, String> entry : params.entrySet()) {
            // Creating if statement for first
            if (first)
                // Creating variable name for first equal to false
                first = false;
                // Otherwise
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
        // Return the result
        return result.toString();
    } // Close private string method for getPostDataString

    /*
    * Creating class for insert student for the async task.
    * The async class will run the network call in the background.
    * AsyncTask enables easy use of the UI thread. This class allows you to perform background operations and
    * publish results on the UI thread effectively without having to manipulate threads and/or handlers.
    */
    // Creating Insert student class for the async task
    class insertStudent extends AsyncTask<Void, Void, Void> {
        // Creating protected void do in background to run the network call in the background
        protected Void doInBackground(Void... voids) {
            // Running the add student api network call
            performPostcall("http://radikaldesign.co.uk/sandbox/studentapi/add.php", params);
            return null; // Return null
        } // Close protected void doInbackground

/*
        protected void onPostExecute() {
            //Toast.makeText(getApplicationContext(), "Student added to the dataabse successfully" +
                    //allStudents.get(DEFAULT_KEYS_DIALER).getname(), Toast.LENGTH_SHORT).show(); // getting all students
        } // Close protected void onPostExecute*/
    } // Close insert student class for the async task
} // Close add student activity class