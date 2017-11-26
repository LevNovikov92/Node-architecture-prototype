package com.levnovikov.core_profile.di;

import com.levnovikov.core_profile.UserRepository;
import com.levnovikov.core_profile.di.stubs.UserRepoStub;

import dagger.Component;
import dagger.Provides;


/**
 * Created by lev.novikov
 * Date: 21/11/17.
 */

@Component(modules = ProfileModule.class)
public interface ProfileComponent {

}
