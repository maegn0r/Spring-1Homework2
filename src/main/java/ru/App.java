package ru;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class App {
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    private static final String COMMANDS = "show commands; show cart; show products; add id; remove id; add all; remove all; new cart; exit";
    static Cart cart = newCart();


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String msg;
        while (!(msg = scanner.nextLine()).equals("exit")){
               switch(msg){
                   case "show commands":
                       System.out.println(COMMANDS);
                       break;
                   case "show cart":
                       System.out.println(cart.items);
                       break;
                   case "add all":
                       cart.addAllProducts();
                       System.out.println(cart.items);
                       break;
                   case "remove all":
                       cart.removeAllProducts();
                       System.out.println("Cart is empty");
                       break;
                   case "new cart":
                       newCart();
                       break;
                   case "show products":
                       System.out.println(cart.productRepository.getProductList());
                       break;
                   default: checkAnotherCommand(msg);
               }
        }
    }

    private static void checkAnotherCommand(String msg) {
        if (msg.startsWith("add")) {
            try {
                String s = msg.split(" ")[1];
                cart.addProduct(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                System.out.println("Input correct id");
            }

        } else if (msg.startsWith("remove")) {
            try {
                String s = msg.split(" ")[1];
                cart.removeProduct(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                System.out.println("Input correct id");
            }

        } else System.out.println("Unknown command");
    }

    public static Cart newCart() {
        return context.getBean("cart",Cart.class);
    }


}
