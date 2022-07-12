package com.group11.lab4;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button signupBtn;
    private Button loginBtn;
    private EditText username;
    private EditText password;
    private CustomerViewModel customerViewModel;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);
        signupBtn = findViewById(R.id.signupButton);
        signupBtn.setOnClickListener((v) -> startActivity(new Intent(getApplicationContext(), SignupActivity.class)));

        loginBtn = findViewById(R.id.loginButton);
        loginBtn.setOnClickListener((v) -> {

            username = findViewById(R.id.usernameLogin);
            password = findViewById(R.id.passwordLogin);

            new Thread(() -> {
                customer = customerViewModel.login(username.getText().toString(), password.getText().toString());
                if(customer == null){
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT)
                            .show());
                }
                else {
                    SharedPreferences sharedPreferences = getSharedPreferences("popupPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("loginFinishPoint", customer.getUserName());
                    editor.commit();

                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT)
                            .show());

                    startActivity(new Intent(getApplicationContext(), SubActivity.class)
                            .putExtra("username", customer.getUserName()));
                }
            }).start();
        });
    }
}

