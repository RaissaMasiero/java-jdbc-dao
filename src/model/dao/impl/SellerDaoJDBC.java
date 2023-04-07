package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDAO {

    private Connection connection;

    public SellerDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Seller s) {

    }

    @Override
    public void update(Seller s) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName\n" +
                        "FROM seller INNER JOIN department\n" +
                        "ON seller.DepartmentId = department.Id\n" +
                        "WHERE seller.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Department d = instanciaDepartamento(rs);
                Seller s = instanciaSeller(rs, d);
                return s;
            }
            return null;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Seller instanciaSeller(ResultSet rs, Department d) throws SQLException{
        Seller s = new Seller();
        s.setId(rs.getInt("Id"));
        s.setNome(rs.getString("Name"));
        s.setEmail(rs.getString("Email"));
        s.setSalarioBase(rs.getDouble("BaseSalary"));
        s.setDataNascimento(rs.getDate("BirthDate"));
        s.setDepartment(d);
        return s;
    }

    private Department instanciaDepartamento(ResultSet rs) throws SQLException{
        Department d = new Department();
        d.setId(rs.getInt("DepartmentId"));
        d.setName(rs.getString("DepName"));
        return d;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
