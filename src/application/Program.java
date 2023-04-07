package application;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SellerDAO sellerDAO = DaoFactory.createSellerDao();

        System.out.println("=== TESTE 1: Seller findById ===");
        Seller s = sellerDAO.findById(3);
        System.out.println(s);

        System.out.println("\n=== TESTE 2: Seller findByDepartment ===");
        Department d = new Department(2, null);
        List<Seller> list = sellerDAO.findByDepartment(d);
        for(Seller seller : list){
            System.out.println(seller);
        }

        System.out.println("\n=== TESTE 3: Seller findAll ===");
        list = sellerDAO.findAll();
        for(Seller seller : list){
            System.out.println(seller);
        }

        System.out.println("\n=== TESTE 4: Seller Insert ===");
        Seller s2 = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, d);
        sellerDAO.insert(s2);
        System.out.println("INSERIDO! Novo id = " + s2.getId());

        System.out.println("\n=== TESTE 5: Seller Update ===");
        s = sellerDAO.findById(1);
        s.setNome("Martha Waine");
        sellerDAO.update(s);
        System.out.println("UPDATE COMPLETO!");

        System.out.println("\n=== TESTE 6: Seller Delete ===");
        System.out.print("ID para deletar: ");
        int id = sc.nextInt();
        sellerDAO.deleteById(id);
        System.out.println("DELETE COMPLETO!");

        sc.close();
    }
}
