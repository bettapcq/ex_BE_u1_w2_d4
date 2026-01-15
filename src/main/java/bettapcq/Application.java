package bettapcq;

import bettapcq.entities.Customer;
import bettapcq.entities.Order;
import bettapcq.entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Application {

    public static void main(String[] args) {
        Customer customer1 = new Customer("Angela", 3);
        Customer customer2 = new Customer("Kevin", 4);
        Customer customer3 = new Customer("Jim", 2);
        Customer customer4 = new Customer("Dwight", 1);
        Customer customer5 = new Customer("Mike", 2);
        Customer customer6 = new Customer("Pam", 2);

        ArrayList<Customer> allCustomers = new ArrayList<>();
        Collections.addAll(allCustomers, customer1, customer2, customer3, customer4, customer5, customer6);

        //prodotti:
        Product product1 = new Product("Harry Potter", "Books", 13.50);
        Product product2 = new Product("Sword", "Boys", 20.00);
        Product product3 = new Product("Barbie", "Girls", 42.90);
        Product product4 = new Product("Action Man", "Boys", 35.20);
        Product product5 = new Product("Sleepy Bear", "Baby", 11.00);
        Product product6 = new Product("Easy-Bake Oven", "Girls", 120.00);
        Product product7 = new Product("The Perfect Book", "Books", 190.00);
        Product product8 = new Product("The October List", "Books", 33.90);

        ArrayList<Product> allProducts = new ArrayList<>();
        Collections.addAll(allProducts, product1, product2, product3, product4, product5, product6, product7, product8);


        //Ordini:
        Order order1 = new Order(
                "processing",
                LocalDate.of(2024, 10, 15),
                LocalDate.of(2024, 10, 21),
                List.of(product1, product3, product6),
                customer1
        );

        Order order2 = new Order(
                "new",
                LocalDate.of(2021, 3, 23),
                LocalDate.of(2021, 4, 2),
                List.of(product7, product4, product5),
                customer2
        );

        Order order3 = new Order(
                "new",
                LocalDate.of(2021, 2, 11),
                LocalDate.of(2021, 2, 20),
                List.of(product1, product2, product8),
                customer3
        );

        Order order4 = new Order(
                "new",
                LocalDate.of(2021, 2, 11),
                LocalDate.of(2021, 2, 20),
                List.of(product1, product6, product5),
                customer3
        );

        Order order5 = new Order(
                "new",
                LocalDate.of(2021, 2, 11),
                LocalDate.of(2021, 2, 20),
                List.of(product4, product2, product7),
                customer1
        );


        ArrayList<Order> allOrders = new ArrayList<>();
        Collections.addAll(allOrders, order1, order2, order3, order4, order5);

        //ES1
        Map<String, List<Order>> ordersByCustomer = allOrders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer().getName()));
        ordersByCustomer.forEach((customer, ordersList) -> System.out.println("Customer: " + customer + ", Orders: " + ordersList));

        //ES2
        Map<String, Double> totAmountByCustomer = allOrders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer().getName(),
                        Collectors.summarizingDouble(order -> order.getProducts().stream()
                                .mapToDouble(product -> product.getPrice()).sum())));

        //ES3
        List<Product> moreExpensiveProducts = allProducts.stream().sorted(Comparator.comparing(Product::getPrice).reversed()).limit(3).toList();
        System.out.println("I tre prodotti più costosi:");
        moreExpensiveProducts.forEach(product -> System.out.println("- " + product.getName() + " e costa " + product.getPrice()));

        //ES4
        OptionalDouble ordersAmountAverage = allOrders.stream()
                .mapToDouble(order -> order.getProducts().stream()
                        .mapToDouble(Product::getPrice)
                        .sum())
                .average();
        if (ordersAmountAverage.isPresent()) System.out.println("La media è: " + ordersAmountAverage.getAsDouble());
        else System.out.println("Nessuna media, la lista è vuota!");

        //ES5
        

    }
}
