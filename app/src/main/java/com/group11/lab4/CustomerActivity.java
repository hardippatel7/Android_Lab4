package com.group11.lab4;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CustomerActivity extends AppCompatActivity {

    String username;
    private CustomerViewModel customerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        SharedPreferences sharedPreferences = getSharedPreferences("popupPref", MODE_PRIVATE);
        username = sharedPreferences.getString("loginFinishPoint", "username");

        customerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);

        new Thread( () -> {
            Customer customer = customerViewModel.getCustomer(username);
            TextView firstName = findViewById(R.id.firstNameView);
            firstName.setText(customer.getFirstName());

            TextView lastName = findViewById(R.id.lastNameView);
            lastName.setText(customer.getLastName());

            TextView username = findViewById(R.id.usernameView);
            username.setText(customer.getUserName());

            TextView address = findViewById(R.id.addressView);
            address.setText(customer.getAddress());

            TextView city = findViewById(R.id.cityView);
            city.setText(customer.getCity());

            TextView postalCode = findViewById(R.id.postalCodeView);
            postalCode.setText(customer.getPostalCode());
        }).start();
    }

}