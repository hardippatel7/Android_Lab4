package com.example.simpleroomdatabasemvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

//a class for managing multiple data sources
//
public class CustomerRepository {
    private final CustomerDao customerDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    //
    public CustomerRepository(Context context) {
        //create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        customerDao = db.customerDao();
    }

    //inserts a customer asynchronously
    public void insert(Customer customer) {
        insertAsync(customer);
    }
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    private void insertAsync(final Customer customer) {

        new Thread(() -> {
            try {
                customerDao.insert(customer);
                insertResult.postValue(1);
            } catch (Exception e) {
                insertResult.postValue(0);
            }
        }).start();
    }

    public Customer login(String username, String password) {
        return customerDao.login(username, password);
    }

    public Customer getOne(String username) {
        return customerDao.getCustomer(username);
    }
}
