package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateContactAcitivity extends Activity {
    /*
*Variables
 */
    private Button submitButton;
    private EditText nameField, numberField, businessField, addressField, provinceField;
    private MyApplicationData appState;


    /**
     * launches the activity create contact.
     * All Edit Texts and Buttons get inizialized here and assigned to their corresponding
     * UI unit.
     * @param savedInstanceState (hasmap for app variables)
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.NEWname);
        numberField = (EditText) findViewById(R.id.NEWnumber);
        businessField = (EditText) findViewById(R.id.NEWbussines);
        addressField = (EditText) findViewById(R.id.NEWaddress);
        provinceField = (EditText) findViewById(R.id.NEWprovince);
    }

    /**
     * Gets activated once the user clicks on the submit button on the createContact activity.
     * It Creates a contact and stores it in Firebase
     * NOTE: Firebase rules must be followed in order to store data
     * @param v (click)
     */
    public void submitInfoButton(View v) {

        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String number = numberField.getText().toString();
        String bussines = businessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();

        /**
         * Note that all province and type of business is cast to upper case
         */
        Contact person = new Contact(personID, name, number, bussines.toUpperCase(), address, province.toUpperCase());

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
