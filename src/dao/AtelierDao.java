package dao;

import metier.AtelierEntity;

public interface AtelierDao extends Dao<AtelierEntity> {
    public AtelierEntity findFirstAvailable();
}
