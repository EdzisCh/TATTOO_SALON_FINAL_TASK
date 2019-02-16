package by.chebotar.dao.impl;

import by.chebotar.dao.ConnectionPool;
import by.chebotar.dao.ConnectionPoolFactory;
import by.chebotar.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


@RunWith(JUnit4.class)
public class ConnectionPoolImplTest {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolImplTest.class);
    private static final int N_THREADS = 127;
    private static final int THREAD_SLEEP = 1_000;
    private static final int POOL_CAPACITY = 40;
    private static final String JDBCDRIVER_CLASS = "org.mysql.jdbc.JDBCDriver";
    private static final String DB_URL = "jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";

    private static final ConnectionPoolFactory FACTORY_INSTANCE = ConnectionPoolFactory.getInstance();


    @Test
    public void shouldGetConnection() throws InterruptedException {
        ConnectionPool connectionPool = Mockito.spy(FACTORY_INSTANCE.getConnectionPool());
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
            } catch (SQLException | IllegalStateException | ConnectionPoolException e) {
                LOGGER.error(e);
            }
        }));


        executorService.awaitTermination(N_THREADS / POOL_CAPACITY, TimeUnit.SECONDS);
        Assert.assertEquals(POOL_CAPACITY, hashCodes.size());
        Mockito.verify(((ConnectionPoolImpl) connectionPool), Mockito.times(N_THREADS)).putBackConnection(Mockito.any());
    }

    private void sleep() {
        try {
            Thread.sleep(Math.abs(new Random().nextInt(THREAD_SLEEP)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
