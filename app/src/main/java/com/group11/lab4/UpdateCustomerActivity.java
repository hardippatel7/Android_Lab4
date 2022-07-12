package com.group11.lab4;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCustomerActivity extends AppCompatActivity {

    String usernameString;
    private CustomerViewModel customerViewModel;
    private Button updateButton;
    private Customer customer;
    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText password;
    private EditText address;
    private EditText city;
    private EditText postalCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        SharedPreferences sharedPreferences = getSharedPreferences("popupPref", MODE_PRIVATE);
        usernameString = sharedPreferences.getString("loginFinishPoint", "username");

        customerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);

        new Thread(() -> {
            customer = customerViewModel.getCustomer(usernameString);

            firstName = findViewById(R.id.firstNameUpdate);
            firstName.setText(customer.getFirstName());

            lastName = findViewById(R.id.lastNameUpdate);
            lastName.setText(customer.getLastName());

            username = findViewById(R.id.usernameUpdate);
            username.setText(customer.getUserName());

            password = findViewById(R.id.passwordUpdate);
            password.setText(customer.getPassword());

            address = findViewById(R.id.addressUpdate);
            address.setText(customer.getAddress());

            city = findViewById(R.id.cityUpdate);
            city.setText(customer.getCity());

            postalCode = findViewById(R.id.postalCodeUpdate);
            postalCode.setText(customer.getPostalCode());
        }).start();

        updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener((v) -> {

            firstName = findViewById(R.id.firstNameUpdate);
            customer.setFirstName(firstName.getText().toString());

            lastName = findViewById(R.id.lastNameUpdate);
            customer.setLastName(lastName.getText().toString());

            username = findViewById(R.id.usernameUpdate);
            customer.setUserName(username.getText().toString());

            password = findViewById(R.id.passwordUpdate);
            customer.setPassword(password.getText().toString());

            address = findViewById(R.id.addressUpdate);
            customer.setAddress(address.getText().toString());

            city = findViewById(R.id.cityUpdate);
            customer.setCity(city.getText().toString());

            postalCode = findViewById(R.id.postalCodeUpdate);
            customer.setPostalCode(postalCode.getText().toString());

            customerViewModel.updateCustomer(customer);
            Toast.makeText(getApplicationContext(), "Indormation Updated Successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), SubActivity.class));
        });
    }
}