package com.levnovikov.core_network.interceptors;

import com.example.core_auth.AuthManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

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

    @Test
    public void intercept() throws Exception {
        MockWebServer server = new MockWebServer();
        HttpUrl url = server.url("/");
//        server.enqueue(new MockResponse().setResponseCode(401).setBody("body 1").setBodyDelay(1, TimeUnit.SECONDS));
//        server.enqueue(new MockResponse().setResponseCode(401).setBody("body 2").setBodyDelay(2, TimeUnit.SECONDS));
//        server.enqueue(new MockResponse().setResponseCode(401).setBody("body 3").setBodyDelay(3, TimeUnit.SECONDS));
        server.enqueue(new MockResponse().setResponseCode(401).setBody("body 1").setBodyDelay(5, TimeUnit.SECONDS));
        server.enqueue(new MockResponse().setResponseCode(401).setBody("body 2").setBodyDelay(3, TimeUnit.SECONDS));
        server.enqueue(new MockResponse().setResponseCode(401).setBody("body 3").setBodyDelay(1, TimeUnit.SECONDS));
        server.enqueue(new MockResponse().setResponseCode(200));
        server.enqueue(new MockResponse().setResponseCode(200));
        server.enqueue(new MockResponse().setResponseCode(200));

        Mockito.when(authManager.refreshToken(Mockito.any(Interceptor.Chain.class))).thenReturn(
                Completable.fromAction(() ->
                        Thread.sleep(1000)));

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(authManager))
                .build();

        final CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " started");
                Response response = client.newCall(new Request.Builder().url(url).build()).execute();
                System.out.println(Thread.currentThread().getName() + " ended");
                Assert.assertEquals(200, response.code());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }, "Thread 1").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " started");
                Response response = client.newCall(new Request.Builder().url(url).build()).execute();
                System.out.println(Thread.currentThread().getName() + " ended");
                Assert.assertEquals(200, response.code());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }, "Thread 2").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " started");
                Response response = client.newCall(new Request.Builder().url(url).build()).execute();
                System.out.println(Thread.currentThread().getName() + " ended");
                Assert.assertEquals(200, response.code());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }, "Thread 3").start();

        countDownLatch.await();
        Mockito.verify(authManager, Mockito.times(1))
                .refreshToken(Mockito.any(Interceptor.Chain.class));
        server.shutdown();
    }
}