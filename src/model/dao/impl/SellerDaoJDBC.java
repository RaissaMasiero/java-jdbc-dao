package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDAO {

    private Connection connection;

    public SellerDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Seller s) {
        PreparedStatement st = null;
        try{
            st = connection.prepareStatement(
                    "INSERT INTO seller\n " +
                        "(Name, Email, BirthDate, BaseSalary, DepartmentId)\n " +
                        "VALUES\n " +
                        "(?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

            st.setString(1, s.getNome());
            st.setString(2, s.getEmail());
            st.setDate(3, new java.sql.Date(s.getDataNascimento().getTime()));
            st.setDouble(4, s.getSalarioBase());
            st.setInt(5, s.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    s.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Erro inesperado! Nenhuma linha afetada!");
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
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
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepName\n " +
                            "FROM seller INNER JOIN department\n" +
                            "ON seller.DepartmentId = department.Id\n" +
                            "ORDER BY Name");

            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()){
                Department d = map.get(rs.getInt("DepartmentId"));
                if(d == null){
                    d = instanciaDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), d);
                }

                Seller s = instanciaSeller(rs, d);
                list.add(s);
            }
            return list;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepName\n " +
                        "FROM seller INNER JOIN department\n" +
                        "ON seller.DepartmentId = department.Id\n" +
                        "WHERE DepartmentId = ? " +
                        "ORDER BY Name");

            st.setInt(1, department.getId());
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()){
                Department d = map.get(rs.getInt("DepartmentId"));
                if(d == null){
                    d = instanciaDepartamento(rs);
                    map.put(rs.getInt("DepartmentId"), d);
                }

                Seller s = instanciaSeller(rs, d);
                list.add(s);
            }
            return list;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
