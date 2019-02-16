package by.chebotar.dao;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Factory producer
 * Provide DAO Factory by type
 */
public class FactoryProducer {
    private static FactoryProducer instance;
    private Lock instanceLock = new ReentrantLock();

    private FactoryProducer() {}

    public FactoryProducer getInstance() {
        FactoryProducer localInstance = instance;
        instanceLock.lock();
        if (localInstance == null) {
            localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FactoryProducer();
                }
        }
        instanceLock.unlock();
        return instance;
    }

    public static DaoFactory getDaoFactory(DaoFactoryType type) {

        throw new UnsupportedOperationException();
    }
}
