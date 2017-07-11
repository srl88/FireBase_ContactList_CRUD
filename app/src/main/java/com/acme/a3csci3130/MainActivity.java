package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {


    /*
*Variables
 */
    private ListView contactListView;
    private FirebaseListAdapter<Contact> firebaseAdapter;

    /**
     * Launches main activity. It uses a Listview to show all the data stored in the Firebase
     * Databse. Inizializes all buttons for this activity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("contacts");


        //Get the reference to the UI contents
        contactListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
        firebaseAdapter = new FirebaseListAdapter<Contact>(this, Contact.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Contact model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.name);
            }
        };
        contactListView.setAdapter(firebaseAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact person = (Contact) firebaseAdapter.getItem(position);
                showDetailView(person);
            }
        });
    }

    /**
     * Gets called once the user clicks on Create Contact button. It sends the user to CreateContact
     * Activity
     * @param v
     */
    public void createContactButton(View v)
    {
        Intent intent=new Intent(this, CreateContactAcitivity.class);
        startActivity(intent);
    }

    /**
     * Gets called once the user clicks on an active contact from the listview. It sends the user to the
     * DeatlViewActivity activity
     * @param person (Linked in the listview and stored in the database )
     */
    private void showDetailView(Contact person)
    {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Contact", person);
        startActivity(intent);
    }



}
