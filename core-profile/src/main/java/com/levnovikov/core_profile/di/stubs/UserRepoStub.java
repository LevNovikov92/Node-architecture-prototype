package com.levnovikov.core_profile.di.stubs;

import com.levnovikov.core_profile.UserRepository;
import com.levnovikov.core_profile.model.UserProfile;

import io.reactivex.Single;

/**
 * Created by lev.novikov
 * Date: 21/11/17.
 */

public class UserRepoStub implements UserRepository {
    @Override
    public Single<Boolean> isUserLoggedIn() {
        return Single.just(false);
    }

    @Override
    public Single<UserProfile> updateUserProfile() {
        return Single.just(UserProfile.create(1,"John", "Doe")).map(profile -> {
            Thread.sleep(2000);
            return profile;
        });
    }
}
