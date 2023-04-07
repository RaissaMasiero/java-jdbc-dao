package application;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args) {

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
    }
}
