package dao;

import metier.AtelierEntity;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.Collection;

public class JpaAtelierDao extends JpaDao<AtelierEntity> implements AtelierDao{
    public AtelierEntity findFirstAvailable() {
        Query query = session.createQuery("SELECT a FROM AtelierEntity a ORDER BY datedispo asc");
        return (AtelierEntity) query.setMaxResults(1).getResultList();
    }

    @Override
    public AtelierEntity find(Integer idAtelier) {
        Query query = session.createQuery("SELECT a FROM AtelierEntity a WHERE id = "+idAtelier);
        return (AtelierEntity) query.getResultList();
    }

    @Override
    public AtelierEntity find(Class c, Integer id) {
        return null;
    }

    @Override
    public Collection<AtelierEntity> findAll() {
        Query query = session.createQuery("SELECT a FROM AtelierEntity a");
        return (Collection<AtelierEntity>) query.getResultList();
    }

    @Override
    @Transactional
    public boolean deleteAll() {
        Query query = session.createQuery("DELETE FROM AtelierEntity");
        query.executeUpdate();
        return true;
    }
}
