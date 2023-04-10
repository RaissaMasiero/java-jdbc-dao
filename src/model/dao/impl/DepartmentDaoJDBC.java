package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = connection.prepareStatement("SELECT * FROM department WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Department d = instanciaDepartment(rs);
                return d;
            }

            return null;

        }catch(SQLException e){
            throw new DbException(e.getMessage());

        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        return null;
    }

    private Department instanciaDepartment(ResultSet rs) throws SQLException{
        Department d = new Department();
        d.setId(rs.getInt("Id"));
        d.setName(rs.getString("Name"));
        return d;
    }
}
