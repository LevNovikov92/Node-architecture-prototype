package com.levnovikov.core_profile.di;

import com.levnovikov.core_profile.UserRepository;
import com.levnovikov.core_profile.di.stubs.UserRepoStub;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lev.novikov
 * Date: 21/11/17.
 */
@Module
public class ProfileModule {

    @Provides
    UserRepository provideUserRepo() {
        return new UserRepoStub();
    }
}
