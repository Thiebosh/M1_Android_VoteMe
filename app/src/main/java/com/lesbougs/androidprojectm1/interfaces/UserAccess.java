package com.lesbougs.androidprojectm1.interfaces;

import androidx.fragment.app.Fragment;

import com.lesbougs.androidprojectm1.model.User;

import java.util.Stack;

/**
 * needs to implement onBackPressed()
 */
public interface UserAccess {
    User getUser();
    void setUser(User user);
}
