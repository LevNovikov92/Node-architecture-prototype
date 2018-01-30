package com.levnovikov.core_network.interceptors;

import com.example.core_auth.AuthManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Author: lev.novikov
 * Date: 30/1/18.
 */
public class AuthInterceptorTest {

    @Mock
    AuthManager authManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void intercept() throws Exception {
    }

    @Test
    public void waitForRefreshOrTimeout_interruption() throws Exception {
        System.out.println("Start interruption test");
        final AuthInterceptor interceptor = new AuthInterceptor(authManager);
        interceptor.TOKEN_REFRESH_IN_PROGRESS = true;

        new Thread(() -> {
            final double ticks = System.currentTimeMillis();
            interceptor.waitForRefreshOrTimeout();

            double time = System.currentTimeMillis() - ticks;
            System.out.println("Time: " + time + " ms");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                //
            }
            interceptor.TOKEN_REFRESH_IN_PROGRESS = false;
        }).start();
        Thread.sleep(4000);
    }


    @Test
    public void waitForRefreshOrTimeout_timeout() throws Exception {
        System.out.println("Start timeout test");
        final AuthInterceptor interceptor = new AuthInterceptor(authManager);
        interceptor.TOKEN_REFRESH_IN_PROGRESS = true;

        new Thread(() -> {
            final double ticks = System.currentTimeMillis();
            interceptor.waitForRefreshOrTimeout();

            double time = System.currentTimeMillis() - ticks;
            System.out.println("Time: " + time + " ms");
        }).start();
        Thread.sleep(4000);
    }
}