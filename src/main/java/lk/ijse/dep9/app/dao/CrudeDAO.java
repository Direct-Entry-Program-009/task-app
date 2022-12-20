package lk.ijse.dep9.app.dao;

import lk.ijse.dep9.app.entity.SuperEntity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudeDAO<T extends SuperEntity, ID extends Serializable>extends SuperDAO{
    T save(T t);
    void update(T t);
    void deleteById(ID pk);
    Optional<T> findById(ID pk);
    List<T> findAll();
    long count();
    boolean existsById(ID pk);

}
