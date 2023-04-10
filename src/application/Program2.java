package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDAO departmentDAO = DaoFactory.createDepartmentDao();

        System.out.println("=== TESTE 1: Department findById ===");
        Department d1 = departmentDAO.findById(14);
        System.out.println(d1);

        System.out.println("\n=== TESTE 2: Department Insert ===");
        Department d2 = new Department(null, "Food");
        departmentDAO.insert(d2);
        System.out.println("INSERIDO! Novo id = " + d2.getId());

        System.out.println("\n=== TESTE 3: Department Delete ===");
        System.out.print("ID para deletar: ");
        int id = sc.nextInt();
        departmentDAO.deleteById(id);
        System.out.println("DELETE COMPLETO!");

        System.out.println("\n=== TESTE 4: Department Update ===");
        d1 = departmentDAO.findById(4);
        d1.setName("Language");
        departmentDAO.update(d1);
        System.out.println("UPDATE COMPLETO!");

        System.out.println("\n=== TESTE 5: Department findAll ===");
        List<Department> list = departmentDAO.findAll();
        for(Department department : list){
            System.out.println(department);
        }

        sc.close();
    }
}
