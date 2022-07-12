package com.group11.lab4;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

// this interface declares database functions
// and does the mapping of SQL queries to functions
@Dao
public interface CustomerDao {
    //
    @Insert
    void insert(Customer customer);

    //Monitoring Query Result Changes with Live Data
    @Query("select * from Customer where userName = :username")
    Customer getCustomer(String username);

    @Query("select * from Customer where userName = :username and password = :password")
    Customer login(String username, String password);

    @Update
    void update(Customer customer);
}