package application;

import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {

    public static void main(String[] args) {

        Department d1 = new Department(1, "Livros");
        Seller s1 = new Seller(2, "Bob", "bob@gmail.com", new Date(), 3000.0, d1);
        System.out.println(s1);
    }
}
