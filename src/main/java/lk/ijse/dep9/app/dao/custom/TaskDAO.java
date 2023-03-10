package lk.ijse.dep9.app.dao.custom;

import lk.ijse.dep9.app.dao.CrudeDAO;
import lk.ijse.dep9.app.entity.Task;

import java.util.List;

public interface TaskDAO extends CrudeDAO<Task,Integer> {
    List<Task> findAllTaskByProjectId(Integer project_id);
}
