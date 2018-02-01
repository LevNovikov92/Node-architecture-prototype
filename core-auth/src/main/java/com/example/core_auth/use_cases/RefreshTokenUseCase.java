package com.example.core_auth.use_cases;

import com.example.core_auth.AuthRepo;
import com.example.core_auth.exceptions.UserIsNotAuthorizedException;
import com.example.core_auth.provider.AuthProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Author: lev.novikov
 * Date: 30/1/18.
 */

@Singleton
public class RefreshTokenUseCase {

    private static final MediaType MEDIA_TYPE
            = MediaType.parse("text/plain; charset=utf-8");
    private final AuthRepo authRepo;

    @Inject
    RefreshTokenUseCase(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }

    public Completable refreshToken(Interceptor.Chain chain) {
        try {
            final AuthProvider.Info authInfo = authRepo.getAuthInfo();
            if (authInfo == null) throw new UserIsNotAuthorizedException();

            final Response response = chain.proceed(getRefreshRequest(authInfo));
            if (response.isSuccessful()) {
                authRepo.setSessionToken(readTokenFromResponse(response));
                return Completable.complete();
            } else {
                return Completable.error(new Exception("Unsuccessful request"));
            }
        } catch (Exception e) {
            return Completable.error(e);
        }
    }

    private String readTokenFromResponse(Response response) throws Exception {
        return "token";
    }

    private Request getRefreshRequest(AuthProvider.Info authInfo) {
        return new Request.Builder()
                .post(getRequestBody(authInfo))
                .build();
    }

    private RequestBody getRequestBody(AuthProvider.Info authInfo) {
        return RequestBody.create(MEDIA_TYPE, getBody(authInfo));
    }

    private String getBody(AuthProvider.Info authInfo) {
        return authInfo.token + " " + authInfo.providerId;
    }
}
