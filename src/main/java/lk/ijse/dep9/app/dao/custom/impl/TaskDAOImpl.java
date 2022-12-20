package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.TaskDAO;
import lk.ijse.dep9.app.entity.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDAOImpl implements TaskDAO {
    private final Connection connection;

    public TaskDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Task save(Task task) {

        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Task (content, status, project_id) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, task.getContent());
            stm.setString(2, String.valueOf(task.getStatus()));
            stm.setInt(3, task.getProjectId());
            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            task.setId(generatedKeys.getInt(1));
            return task;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Task task) {
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE Task SET content=?,status=?,project_id=? WHERE id=?");
            stm.setInt(4, task.getId());
            stm.setInt(3, task.getProjectId());
            stm.setString(2, String.valueOf(task.getStatus()));
            stm.setString(1, task.getContent());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Task WHERE id=?");
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Task> findById(Integer id) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT content, status, project_id FROM Task WHERE id=?");
            stm.setInt(1, id);
            ResultSet rst = stm.executeQuery();
            if(rst.next()){
                return Optional.of(new Task(id,
                        rst.getString("content"), Task.Status.valueOf(rst.getString("status")),rst.getInt("project_id")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> findAll() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Task");
            ResultSet rst = stm.executeQuery();
            List<Task> taskList = new ArrayList<>();
            while (rst.next()){
                int id = rst.getInt("id");
                String content = rst.getString("content");
                Task.Status status = Task.Status.valueOf(rst.getString("status"));
                int project_id = rst.getInt("project_id");
                taskList.add(new Task(id,content,status,project_id));
            }
            return taskList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {

        try {
            PreparedStatement stm = connection.prepareStatement("SELECT COUNT(id) FROM Task");
            ResultSet rst = stm.executeQuery();
            rst.next();
            return rst.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }

    @Override
    public List<Task> findAllTaskByProjectId(Integer project_id) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Task WHERE project_id=?");
            stm.setInt(1,project_id);
            ResultSet rst = stm.executeQuery();
            List<Task> taskList = new ArrayList<>();
            while (rst.next()){
                int id = rst.getInt("id");
                String content = rst.getString("content");
                Task.Status status = Task.Status.valueOf(rst.getString("status"));
                taskList.add(new Task(id,content,status,project_id));
            }
            return taskList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
