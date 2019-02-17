package by.chebotar.dao.impl;

import by.chebotar.dao.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(JUnit4.class)
public class ConnectionPoolImplTest {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolImplTest.class);
    private static final int N_THREADS = 127;
    private static final int THREAD_SLEEP = 1_00;
    private static final int POOL_CAPACITY = 40;

    @Test
    public void shouldGetConnection() {
        ConnectionPool connectionPool = Mockito.spy(ConnectionPoolImpl.getInstance());
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        Set<Integer> hashCodes = Collections.synchronizedSet(new HashSet<>());

        IntStream.range(0, N_THREADS).forEach(i -> executorService.submit(() -> {
            sleep();
            LOGGER.info("Try to get connection");
            try (Connection connection = connectionPool.retrieveConnection()) {
                LOGGER.info("working with connection...");
                sleep();
                Assert.assertTrue(connection instanceof Proxy);
                int hashCode = connection.hashCode();
                hashCodes.add(hashCode);
                LOGGER.info("release connection: " + hashCode);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }));

        try {
            executorService.awaitTermination(N_THREADS / POOL_CAPACITY, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
        Assert.assertEquals(POOL_CAPACITY, hashCodes.size());
        Mockito.verify((connectionPool), Mockito.times(N_THREADS)).putBackConnection(Mockito.any());
    }

    private void sleep() {
        try {
            Thread.sleep(THREAD_SLEEP);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}