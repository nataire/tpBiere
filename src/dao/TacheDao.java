package dao;

import metier.TacheEntity;

import java.util.Collection;

public interface TacheDao extends Dao<TacheEntity> {
    public Collection<TacheEntity> findAllNotScheduled();
}
