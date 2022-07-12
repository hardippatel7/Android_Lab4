package com.group11.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private TextView customerInfo;
    private TextView updateCustomerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        customerInfo = findViewById(R.id.custInfo);
        customerInfo.setOnClickListener((v) -> startActivity(new Intent(getApplicationContext(), CustomerActivity.class)));

        updateCustomerInfo = findViewById(R.id.updateCustInfo);
        updateCustomerInfo.setOnClickListener((v) -> startActivity(new Intent(getApplicationContext(), UpdateCustomerActivity.class)));
    }
}