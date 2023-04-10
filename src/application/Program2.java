package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

    public static void main(String[] args) {

        DepartmentDAO departmentDAO = DaoFactory.createDepartmentDao();

        System.out.println("=== TESTE 1: Department findById ===");
        Department d1 = departmentDAO.findById(4);
        System.out.println(d1);
    }
}
