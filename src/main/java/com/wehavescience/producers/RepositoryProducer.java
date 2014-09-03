package com.wehavescience.producers;

import com.mongodb.MongoClient;
import com.wehavescience.repositories.Repository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.reflect.ParameterizedType;
import java.net.UnknownHostException;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
public class RepositoryProducer {
    @Produces
    @RequestScoped
    public Datastore datastore() throws UnknownHostException {
        return new Morphia().createDatastore(new MongoClient(), "blog");
    }

    @Produces
    public <T> Repository<T> repository(InjectionPoint injectionPoint, Datastore datastore){
        Class<T> clazz = (Class<T>) ((ParameterizedType)injectionPoint.getType()).getActualTypeArguments()[0];
        return new Repository<>(datastore, clazz);
    }
}
