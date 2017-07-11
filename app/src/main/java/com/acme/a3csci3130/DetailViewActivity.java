package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Map;

public class DetailViewActivity extends Activity {

    /*
    *Variables
     */
    private EditText nameField, numberField, businessField, addressField, provinceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    /**
     * launches the activity View Contact.
     * All Edit Texts and Buttons get inizialized here and assigned to their corresponding
     * UI unit. This activity allows to erase contacts as well as to updates them.
     * @param savedInstanceState (hasmap for app variables)
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.NEWname);
        numberField = (EditText) findViewById(R.id.NEWnumber);
        businessField = (EditText) findViewById(R.id.NEWbussines);
        addressField = (EditText) findViewById(R.id.NEWaddress);
        provinceField = (EditText) findViewById(R.id.NEWprovince);

        appState = ((MyApplicationData) getApplicationContext());

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            numberField.setText(receivedPersonInfo.number);
            businessField.setText(receivedPersonInfo.typeOfBussines);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);

        }
    }

    /**
     * Gets called once user clicks on update contact. Updates contact information and stores new
     * data
     * @param v (click)
     */
    public void updateContact(View v){
        String newName = nameField.getText().toString();
        String newNumber = numberField.getText().toString();
        String newBusiness = businessField.getText().toString();
        String newAddress = addressField.getText().toString();
        String newProvince = provinceField.getText().toString();

        Map<String, Object> childUpdate = receivedPersonInfo.toMap();
        childUpdate.put("name", newName);
        childUpdate.put("number", newNumber);
        childUpdate.put("typeOfBussines", newBusiness.toUpperCase());
        childUpdate.put("address", newAddress);
        childUpdate.put("province", newProvince.toUpperCase());
        appState.firebaseReference.child(receivedPersonInfo.uid).setValue(childUpdate);
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);



    }

    /**
     * Gets called everytime the user clicks on earse contact. Deletes contact from firebase database
     * @param v
     */
    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
