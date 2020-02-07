package dao;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Collection;

public abstract class JpaDao<T> implements Dao<T> {
    protected static final SessionFactory ourSessionFactory;
    protected static Session session;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
            session = ourSessionFactory.openSession();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }


    public Session getSession()
    {
        if (session == null)
        {
            session = ourSessionFactory.openSession();
            session.setFlushMode(FlushMode.ALWAYS);
        }
        return session;
    }

    @Override
    public boolean create(T obj) {
        Transaction tx = session.beginTransaction();
        session.save(obj);
        tx.commit();
        return false;
    }

    @Override
    public T find(Class c,Integer id) {

        //T obj = (T) session.find(c, id);
        T obj = (T) session.load(c,id);
        return(obj);
    }



    @Override
    public Collection<T> findAll() {

        return null;
    }

    @Override
    public boolean update(T obj) {
        Transaction tx = session.beginTransaction();
        session.update(obj);
        session.refresh(obj);
        tx.commit();
        return true;
    }

    @Override
    public boolean delete(T obj) {
        /*Query query = session.createQuery("DELETE FROM );
        return (TacheE) query.getResultList();*/
        return false;
    }

    @Override
    public void close() {
        session.close();
        ourSessionFactory.close();
    }
}
