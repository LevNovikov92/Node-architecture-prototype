package com.levnovikov.core_profile.model;

import com.google.auto.value.AutoValue;

/**
 * Created by lev.novikov
 * Date: 21/11/17.
 */

@AutoValue
public abstract class UserProfile {

    public static UserProfile create(int id, String name, String surname) {
        return new AutoValue_UserProfile(id, name, surname);
    }

    abstract int id();
    abstract String name();
    abstract String surname();
}
