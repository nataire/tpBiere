package dao;

import metier.TacheEntity;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.Collection;

public class JpaTacheDao extends JpaDao<TacheEntity> implements TacheDao{
    @Override
    public Collection<TacheEntity> findAllNotScheduled() {
        Query query = session.createQuery("SELECT t FROM TacheEntity t WHERE nmachine is null");
        return (Collection<TacheEntity>) query.getResultList();
    }

    @Override
    public TacheEntity find(Integer id) {
        Query query = session.createQuery("SELECT t FROM TacheEntity t WHERE id = " + id);
        return (TacheEntity) query.getResultList();
    }

    @Override
    public TacheEntity find(Class c, Integer id) {
        return null;
    }

    @Override
    public Collection<TacheEntity> findAll() {
        Query query = session.createQuery("SELECT t FROM TacheEntity t");
        return (Collection<TacheEntity>) query.getResultList();
    }

    @Transactional
    public boolean deleteAll() {
        Query query = session.createQuery("DELETE FROM AtelierEntity");
        query.executeUpdate();
        return true;
    }
}
