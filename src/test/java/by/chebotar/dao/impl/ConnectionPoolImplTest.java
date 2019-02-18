package by.chebotar.dao.impl;

import by.chebotar.dao.ConnectionPool;
import org.junit.Assert;
import org.junit.Ignore;
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

    private static final int N_THREADS = 80;
    private static final int POOL_CAPACITY = 40;

    @Ignore
    @Test
    public void shouldGetConnection() throws InterruptedException {
        ConnectionPool connectionPool = Mockito.spy(ConnectionPoolImpl.getInstance());
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        Set<Integer> hashCodes = Collections.synchronizedSet(new HashSet<>());

        IntStream.range(0, N_THREADS).forEach(i -> executorService.submit(() -> {
            try (Connection connection = connectionPool.retrieveConnection()) {
                Thread.sleep(1_00L);
                Assert.assertTrue(connection instanceof Proxy);
                int hashCode = connection.hashCode();
                hashCodes.add(hashCode);
            } catch (Exception e) {

            }
        }));

        executorService.awaitTermination(5L, TimeUnit.SECONDS); //N_THREADS / POOL_CAPACITY
        Assert.assertEquals(POOL_CAPACITY, hashCodes.size());
        Mockito.verify((connectionPool), Mockito.times(N_THREADS)).putBackConnection(Mockito.any());
    }
}