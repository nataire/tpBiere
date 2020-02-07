package dao;

import metier.MachineEntity;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.Collection;

public class JpaMachineDao extends JpaDao<MachineEntity> implements MachineDao {
    @Override
    public MachineEntity find(Integer id) {
        Query query = session.createQuery("SELECT m FROM MachineEntity m WHERE id = " + id);
        return (MachineEntity) query.getResultList();
    }

    @Override
    public MachineEntity find(Class c, Integer id) {
        return null;
    }

    @Override
    public Collection<MachineEntity> findAll() {
        Query query = session.createQuery("SELECT m FROM MachineEntity m");
        return (Collection<MachineEntity>) query.getResultList();
    }

    @Transactional
    public boolean deleteAll() {
        Query query = session.createQuery("DELETE FROM AtelierEntity");
        query.executeUpdate();
        return true;
        /*Query query = session.createQuery("DELETE FROM MachineEntity m");
        return (boolean) query.getSingleResult();*/
    }
}
