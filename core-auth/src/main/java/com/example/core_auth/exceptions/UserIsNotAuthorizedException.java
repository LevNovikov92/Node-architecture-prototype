package com.example.core_auth.exceptions;

/**
 * Author: lev.novikov
 * Date: 30/1/18.
 */

public class UserIsNotAuthorizedException extends Exception {

    @Override
    public String getMessage() {
        return "User is not authorized";
    }
}
