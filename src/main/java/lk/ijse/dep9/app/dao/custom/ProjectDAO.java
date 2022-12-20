package lk.ijse.dep9.app.dao.custom;

import lk.ijse.dep9.app.dao.CrudeDAO;
import lk.ijse.dep9.app.entity.Project;

import java.util.List;

public interface ProjectDAO extends CrudeDAO<Project,Integer> {
    List<Project> findAllProjectsByUserName(String username);
}
