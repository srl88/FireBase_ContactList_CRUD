package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    /*
*Variables
 */
    public  String uid;
    public String number;
    public  String name;
    public String typeOfBussines;
    public  String address;
    public String province;

    /**
     * Default Constructor
     */
    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Constructor for contact/bussines. All paramaters are type String
     * @param uid (Reference in FireBase)
     * @param name (Name of the bussnines)
     * @param number (Number of the bussines)
     * @param typeOfBussines
     * @param address
     * @param province
     */
    public Contact(String uid, String name, String number, String typeOfBussines, String address, String province){
        this.uid = uid;
        this.name = name;
        this.number = number;
        this.typeOfBussines = typeOfBussines;
        this.address = address;
        this.province = province;
    }


    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("number", number);
        result.put("typeOfBussines", typeOfBussines);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
