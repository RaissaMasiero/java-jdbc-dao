package model.dao.impl;

import model.dao.DepartmentDAO;
import model.entities.Department;

import java.sql.Connection;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Department d) {

    }

    @Override
    public void update(Department d) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}
