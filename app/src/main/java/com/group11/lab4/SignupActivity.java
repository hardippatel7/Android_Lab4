package com.group11.lab4;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private CustomerViewModel customerViewModel;
    private Customer customer;
    private Button submitButton;
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
        setContentView(R.layout.activity_signup);

        customerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);
        customer = new Customer();

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener((v) -> {
            firstName = findViewById(R.id.firstName);
            customer.setFirstName(firstName.getText().toString());

            lastName = findViewById(R.id.lastName);
            customer.setLastName(lastName.getText().toString());

            username = findViewById(R.id.username);
            customer.setUserName(username.getText().toString());

            password = findViewById(R.id.password);
            customer.setPassword(password.getText().toString());

            address = findViewById(R.id.address);
            customer.setAddress(address.getText().toString());

            city = findViewById(R.id.city);
            customer.setCity(city.getText().toString());

            postalCode = findViewById(R.id.postalCode);
            customer.setPostalCode(postalCode.getText().toString());

            customerViewModel.insert(customer);
            Toast.makeText(getApplicationContext(), "Signup Successful! Please login!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
    }
}