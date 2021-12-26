package ru;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class App {
    public AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    private static final String COMMANDS = "show commands; show cart; show products; add id; remove id; add all; remove all; new cart; exit";
    private Cart cart;


    public static void main(String[] args) {
        new App().start();

    }
    public void start(){
        cart = newCart();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! If you want to know more about available commands please input show commands");
        String msg;
        while (!(msg = scanner.nextLine()).equals("exit")){
            switch(msg){
                case "show commands":
                    System.out.println("Available commands: " + COMMANDS.replace(";"," "));
                    break;
                case "show cart":
                    cart.showCart();
                    break;
                case "add all":
                    cart.addAllProducts();
                    break;
                case "remove all":
                    cart.removeAllProducts();
                    break;
                case "new cart":
                    cart = newCart();
                    break;
                case "show products":
                    cart.showProducts();
                    break;
                default: checkAnotherCommand(msg);
                    break;
            }
        }
        context.close();
    }

    private void checkAnotherCommand(String msg) {
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

    private Cart newCart() {
        System.out.println("New cart created");
        return context.getBean("cart",Cart.class);
    }


}
