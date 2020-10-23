package com.example.dh11a.restfulstudentapp;

// Importing packages to the class

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * This is the main activity which would get the lists working.
 * It would display the list of the students and it also connects to the database, where it will get the students from.
 * This file is attached with the activity_main.xml file where i have created the list.
 * I have also created add student button which is attached to the add student class, where it will let the user add student to the database.
 * Once the user clicks on student it will then bring up there details.
 * As you can see at the bottom of every class I have added async task which would run the network call in the background.
 */

// Creating public class called MainActiviy which extends the compatActivity
public class MainActivity extends AppCompatActivity {
    // Creating string object for all students, to store all students into array, studentlist, add student button and Async task button
    String[] Student;
    ListView StudentsList;
    Button buttonAddStudent;
    Button AsyncTask;
    Context c = this;

    // Creating array to store all students
    ArrayList<Student> allStudents = new ArrayList<>();

    // Creating protected void onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Creating super.onCreate
        setContentView(R.layout.activity_main); // Cetting the setContentView layout and calling the activity_main file

        // Instantiating the gson object
        //Gson gson = new Gson();

        // Ceating strict mode for the thread policy
        // Run network on main thread hack
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        // Setting the thread policy
//        StrictMode.setThreadPolicy(policy);
        // Creating list view for the student list
        StudentsList = (ListView) findViewById(R.id.StudentsList);
        // Instantiating the button to add the student
        Button buttonAddStudent = (Button) findViewById(R.id.buttonAddStudent);

        new getData().execute(); // Calling the GetData function for the async task

        // Calling the student list function
        //getStudentsList();
        /*
        // Making a http call
        HttpURLConnection urlConnection;
        InputStream in = null; // Creating InputStream object = null
        // Creating try for connecting to the server
        try {
            // The url we wish to connect to
            URL url = new URL("http://radikaldesign.co.uk/sandbox/studentapi/getallstudents.php?apikey=032794ed45");
            // Open the connection to the specified URL
            urlConnection = (HttpURLConnection) url.openConnection();
            // Get the response from the server in an input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
        } /// Close try
        // Creating catch IOException and print the stack trace
        catch (IOException e) {
            e.printStackTrace();
        } // Close catch IoExcetion
        // Covert the input stream to a string
        String response = convertStreamToString(in);
        // Print the response to android monitor/log cat
        System.out.println("Server response = " + response);
        // Creating try for the jsonArray
        try {
            // Declaring a new json array and pass it the string response from the server.
            // This will convert the string into a JSON array which we can then iterate
            // over using a loop
            JSONArray jsonArray = new JSONArray(response);
            // Instantiating the studentNames array and setting the size
            // to the amount of student object returned by the server
            Student = new String[jsonArray.length()];

            // Using a for loop to iterate over the JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                // The following line of code will get the name of the students from the
                // current JSON object and store it in a string variable called name
                String name = jsonArray.getJSONObject(i).get("name").toString();
                String gender = jsonArray.getJSONObject(i).get("gender").toString();
                String dob = jsonArray.getJSONObject(i).get("dob").toString();
                String address = jsonArray.getJSONObject(i).get("address").toString();
                String postcode = jsonArray.getJSONObject(i).get("postcode").toString();
                String studentNumber = jsonArray.getJSONObject(i).get("studentNumber").toString();
                String courseTitle = jsonArray.getJSONObject(i).get("courseTitle").toString();
                String startDate = jsonArray.getJSONObject(i).get("startDate").toString();
                String bursary = jsonArray.getJSONObject(i).get("bursary").toString();
                String email = jsonArray.getJSONObject(i).get("email").toString();

                // Creating new student
                Student student = new Student(name, gender, dob, address, postcode, studentNumber, courseTitle, startDate, bursary, email);
                allStudents.add(student); // Adding student

                // Print the name to log cat
                System.out.println("name = " + name);
                // Add the name and email of the current student to the studentNames array
                Student[i] = name + "\n" + email;
            } // Close for loop jsonArray
        } catch (JSONException e) { // Close for loop and starting catch IoException
            e.printStackTrace(); // PrintStackTrace
        } // Close catch Ioexception

        // Creating new array adapter for the new students
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Student);
        StudentsList.setAdapter(arrayAdapter); // Setting the adapter for the StudentsList
*/

        // Creating on click listener for AddStudent button
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            // creating public void onClick
            public void onClick(View view) {
                // Creating intent and linking it to the add student activity class
                Intent intent1 = new Intent(getApplicationContext(), AddStudentActivity.class);
                startActivity(intent1); // Starting the activity
            } // close public void onClick
        }); // Close setOnClickListener for button add student

        // Creating studentsList for set on item click listener
        StudentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long
                    l) {
                // Creating toast for the student list
                Toast.makeText(getApplicationContext(), "you pressed " +
                        allStudents.get(i).getname(), Toast.LENGTH_SHORT).show(); // getting all students
                // Declaring a new intent and give it the context and
                // specify which activity you want to open/start
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                // Add/put the selected student object into the intent which will
                // be passed over to the activity that is started.
                // We have used a KEY:VALUE structure to pass variable/objects
                // between activities. Here the key is ‘student’ and the value is
                // the student object from the student array list using the position
                // which is specified by the ‘i’ variable.
                intent.putExtra("student", allStudents.get(i));
                // Launch the activity
                startActivity(intent);
            } // close public void
        }); // Close setOnItemClickListener for student list
    } // Close protected void onCreate

    /*
    private ArrayList<com.example.dh11a.restfulstudentapp.Student> getStudentsList() {
        // Making a http call
        HttpURLConnection urlConnection;
        InputStream in = null; // Creating InputStream object = null
        // Creating try for connecting to the server
        try {
            // The url we wish to connect to
            URL url = new URL("http://radikaldesign.co.uk/sandbox/studentapi/getallstudents.php?apikey=032794ed45");
            // Open the connection to the specified URL
            urlConnection = (HttpURLConnection) url.openConnection();
            // Get the response from the server in an input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
        } /// Close try
        // Creating catch IOException and print the stack trace
        catch (IOException e) {
            e.printStackTrace();
        } // Close catch IoExcetion
        // Covert the input stream to a string
        String response = convertStreamToString(in);
        // Print the response to android monitor/log cat
        System.out.println("Server response = " + response);
        // Creating try for the jsonArray
        try {
            // Declaring a new json array and pass it the string response from the server.
            // This will convert the string into a JSON array which we can then iterate
            // over using a loop
            JSONArray jsonArray = new JSONArray(response);
            // Instantiating the studentNames array and setting the size
            // to the amount of student object returned by the server
            Student = new String[jsonArray.length()];

            // Using a for loop to iterate over the JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                // The following line of code will get the name of the students from the
                // current JSON object and store it in a string variable called name
                String name = jsonArray.getJSONObject(i).get("name").toString();
                String gender = jsonArray.getJSONObject(i).get("gender").toString();
                String dob = jsonArray.getJSONObject(i).get("dob").toString();
                String address = jsonArray.getJSONObject(i).get("address").toString();
                String postcode = jsonArray.getJSONObject(i).get("postcode").toString();
                String studentNumber = jsonArray.getJSONObject(i).get("studentNumber").toString();
                String courseTitle = jsonArray.getJSONObject(i).get("courseTitle").toString();
                String startDate = jsonArray.getJSONObject(i).get("startDate").toString();
                String bursary = jsonArray.getJSONObject(i).get("bursary").toString();
                String email = jsonArray.getJSONObject(i).get("email").toString();

                // Creating new student
                Student student = new Student(name, gender, dob, address, postcode, studentNumber, courseTitle, startDate, bursary, email);
                allStudents.add(student); // Adding student

                // Print the name to log cat
                System.out.println("name = " + name);
                // Add the name and email of the current student to the studentNames array
                Student[i] = name + "\n" + email;
            } // Close for loop jsonArray
        } catch (JSONException e) { // Close for loop and starting catch IoException
            e.printStackTrace(); // PrintStackTrace
        } // Close catch Ioexception

        // Creating new array adapter for the new students
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Student);
        StudentsList.setAdapter(arrayAdapter); // Setting the adapter for the StudentsList
        return null;
    } // Close ublic void getStudentsList

    // Creating protected void for on resume and creating super
    protected void  onResume() {
        super.onResume();
        // Calling the getStudentsList function
        getStudentsList();
    } // Close protected void onResume*/


    // Creating public string for convertStreamToString
    public String convertStreamToString(InputStream is) {
        // Creating java.util.Scanner
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : ""; // Return the s.hasNext
    } // Close public string convertStreamToString

    /**
     * The code below makes a http call
     * the URL shown below is the url I want to connect to
     * urlConnection opens a connection to the URL
     * All the network call will be done in the background.
     * All students are saved in a array.
     * AsyncTask enables easy use of the UI thread. This class allows you to perform background operations and
     * publish results on the UI thread effectively without having to manipulate threads and/or handlers.
     */
    // Class for get data for the async task
    class getData extends AsyncTask<Void, Void, ArrayList<Student>> {
        // Creating protected array list for student
        protected ArrayList<Student> doInBackground(Void... voids) {
            // Creating array list to store all students in array
            ArrayList<Student> students = new ArrayList<Student>();

            // Creating http url connection variable
            HttpURLConnection urlConnection;
            // Creating input stream equal to null
            InputStream in = null;
            // Creating try for the network call and connecting it tot he student apito get all students from the dataabse
            try {
                // The url to get all students using my api key
                URL url = new URL("http://radikaldesign.co.uk/sandbox/studentapi/getallstudents.php?apikey=032794ed45");
                urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            } // Close catch
            // Creating string respose
            String response = convertStreamToString(in);
            // Print the server response
            System.out.println("Server response = " + response);

            // Creating try for the jsonArray
            try {
                // Declaring a new json array and pass it the string response from the server.
                // This will convert the string into a JSON array which we can then iterate
                // over using a loop
                JSONArray jsonArray = new JSONArray(response);
                // Instantiating the studentNames array and setting the size
                // to the amount of student object returned by the server
                Student = new String[jsonArray.length()];

                // Using a for loop to iterate over the JSON array
                for (int i = 0; i < jsonArray.length(); i++) {
                    // The following line of code will get the name of the students from the
                    // current JSON object and store it in a string variable called name
                    String name = jsonArray.getJSONObject(i).get("name").toString();
                    String gender = jsonArray.getJSONObject(i).get("gender").toString();
                    String dob = jsonArray.getJSONObject(i).get("dob").toString();
                    String address = jsonArray.getJSONObject(i).get("address").toString();
                    String postcode = jsonArray.getJSONObject(i).get("postcode").toString();
                    String studentNumber = jsonArray.getJSONObject(i).get("studentNumber").toString();
                    String courseTitle = jsonArray.getJSONObject(i).get("courseTitle").toString();
                    String startDate = jsonArray.getJSONObject(i).get("startDate").toString();
                    String bursary = jsonArray.getJSONObject(i).get("bursary").toString();
                    String email = jsonArray.getJSONObject(i).get("email").toString();

                    // Creating new student
                    Student student = new Student(name, gender, dob, address, postcode, studentNumber, courseTitle, startDate, bursary, email);
                    students.add(student); // Adding student

                } // Close for loop jsonArray
            } catch (JSONException e) { // Close for loop and starting catch IoException
                e.printStackTrace(); // PrintStackTrace
            } // Close catch Ioexception

            return students; // return students
        } // close protected array list for student

        // Creating protected void onPostExectue
        protected void onPostExecute(ArrayList<Student> students) {
            Student = new String[students.size()];
            for (int i = 0; i < students.size(); i++) {
                allStudents.add(students.get(i));
                Student[i] = students.get(i).getname() + "\n" + students.get(i).getemail();
            } // close for loop
            ArrayAdapter arrayAdapter = new ArrayAdapter(c, android.R.layout.simple_list_item_1, Student);
            StudentsList.setAdapter(arrayAdapter);
        } // close protected void onPostExectue
    } // close get data async task class
} // Close main activity class