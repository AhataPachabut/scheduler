package com.it.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public abstract class GenericDAOImpl<T, U> implements GenericDAO<T, U> {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();
                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);
                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();
                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    private final Class<T> type;

    GenericDAOImpl(Class<T> type) {
        this.type = type;
    }

    /**
     * Gets persistent entity by id
     *
     * @param id - entity ID
     */
    public T getOne(U id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(type, (Serializable) id);
        }
    }

    /**
     * Deletes persistent entity by id
     *
     * @param id - entity ID
     */
    public void delete(U id) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T entity = session.get(type, (Serializable) id);
            if (entity != null) {
                session.delete(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * Saves an transient entity
     *
     * @param entity - transient entity
     */
    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    /**
     * Updates a persistent\detached entity
     *
     * @param entity - persistent\detached entity
     */
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
