package com.wehavescience.repositories;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Alternative
public class Repository<T> {
    private Datastore datastore;
    private Class<T> typeClass;

    public Repository(Datastore datastore, Class<T> typeClass) {
        this.datastore = datastore;
        this.typeClass = typeClass;
    }

    public List<T> findAll() {
        List<T> objectList = new ArrayList<T>();
        Query<T> query = datastore.find(typeClass);
        query.forEach(obj -> objectList.add(obj));
        return objectList;
    }

    public T findById(Object id){
        return datastore
                .find(typeClass)
                    .field("_id")
                    .equal(id)
                .get();
    }

    public T findOneBy(String field, Object value){
        return datastore.find(typeClass).field(field).equal(value).get();
    }

    public void save(T entity){
        datastore.save(entity);
    }

    public Datastore datastore() {
        return datastore;
    }
}
