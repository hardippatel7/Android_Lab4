package com.example.simpleroomdatabasemvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

//provides data to the UI and acts as a communication center
// between the Repository and the UI.
public class CustomerViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private CustomerRepository customerRepository;
    private LiveData<Integer> insertResult;
    //
    public CustomerViewModel(@NonNull Application application) {
        super(application);
        customerRepository = new CustomerRepository(application);
        insertResult = customerRepository.getInsertResult();
    }
    //calls repository to insert a person
    public void insert(Customer person) {
        customerRepository.insert(person);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }

    public Customer login(String username, String password) {

        Customer customer = customerRepository.login(username, password);
        return customer;
    }

    public Customer getCustomer(String username) {
        Customer customer = customerRepository.getOne(username);
        return customer;
    }
}