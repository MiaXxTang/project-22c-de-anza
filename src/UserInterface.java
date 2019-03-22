/**
 * UserInterface.java
 * @author Feiyue Zhang
 * CIS 22C, Final Project
 */

import java.io.*;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        //Print welcome message
        System.out.print("Welcome to A+ Music Store!\n\n");

        //Read product database file
        File file_customers = new File("customers.txt");
        Scanner scanner = null;
        try{
            scanner = new Scanner(file_customers);
        }
        catch(java.io.FileNotFoundException fe) {
            System.out.print("Can't open customer.txt\n\n");
            return;
        }
        File file_products = new File("products.txt");
        try{
            scanner = new Scanner(file_products);
        }
        catch(java.io.FileNotFoundException fe) {
            System.out.print("Can't open products.txt\n\n");
            return;
        }

        //Build product database


        //Identify the user
        boolean cont = true;
        boolean loggedInCustomer = false;
        boolean loggedInEmployee = false;
        String username = "";
        String password = "";
        String option = "";
        Scanner scan = new Scanner(System.in);
        while(cont) {
            System.out.print("Are you a customer or an employee?\n\n" +
                    "1. Customer\n" +
                    "2. Employee\n" +
                    "Enter your choice: ");
            option = scan.nextLine();
            System.out.print("\n");

            //Log in as a customer
            if(option.equals("1")) {
                System.out.print("Please select from the following options:\n\n" +
                        "A. Enter username and password\n" +
                        "B. Create a new account\n" +
                        "C. Log in as a guest\n" +
                        "Enter your choice: ");
                String input = scan.nextLine();
                System.out.print("\n");

                //Enter username and password
                if(input.equalsIgnoreCase("A")) {
                    while(loggedInCustomer == false) {
                        loggedInCustomer = idCustomer();
                        if(loggedInCustomer == true) {
                            System.out.print("You are logged in as a member!\n\n");
                            cont = false;
                        } else {
                            System.out.print("Username and password are not matched!\n"
                                    + "Please try to log in again!\n\n");
                        }
                    }
                }
                //Create a new account
                else if(input.equalsIgnoreCase("B")) {
                    createAccount();
                    System.out.print("You have created a new account!\n");
                }
                //Log in as a guest
                else if(input.equalsIgnoreCase("C")) {
                    System.out.print("You are logged in as a guest!\n");
                    cont = false;
                }
                else {
                    System.out.print("Invalid selection!\n");
                }
                System.out.print("\n");
            }

            //Log in as an employee
            else if(option.equals("2")) {
                //Enter username and password
                while(loggedInEmployee == false) {
                    loggedInEmployee = idEmployee();
                    if(loggedInEmployee == true) {
                        System.out.print("You are logged in as an employee!\n\n");
                        cont = false;
                    } else {
                        System.out.print("Username and password are not matched!\n"
                                + "Please try to log in again!\n\n");
                    }
                }
            }

            //Invalid input
            else {
                System.out.print("Invalid selection. Please enter available options.\n");
            }
        }

        //Store menu
        cont = true;
        while(cont) {
            //If you are a customer
            if(option.equals("1")) {
                System.out.print("Please select from the following options:\n\n" +
                        "A. Search for a product\n" +
                        "B. Show all products\n" +
                        "C. Place an order\n" +
                        "D. View purchases\n" +
                        "X. Quit\n" +
                        "Enter your choice: ");
                String input = scan.nextLine();
                System.out.print("\n");

                //Search for a product
                if(input.equalsIgnoreCase("A")) {
                    System.out.println("Please select from the following options:\n\n" +
                            "A. Search by title\n" +
                            "B. Search by artist\n" +
                            "Enter your choice: ");
                    input = scan.nextLine();
                    //Find it by primary key (title)
                    if(input.equals("A")) {

                    }
                    //Find it by secondary key (artist)
                    else if(input.equals("B")) {

                    }
                    else {
                        System.out.print("Invalid Selection!\n");
                    }
                }
                //Show all products
                else if(input.equalsIgnoreCase("B")) {
                    System.out.println("Please select from the following options:\n\n" +
                            "A. Show by title\n" +
                            "B. Show by artist\n" +
                            "Enter your choice: ");
                    input = scan.nextLine();
                    //Sorted by primary key (title)
                    if(input.equalsIgnoreCase("A")) {

                    }
                    //Sorted by secondary key (artist)
                    else if(input.equalsIgnoreCase("B")) {

                    }
                    else {
                        System.out.print("Invalid Selection!\n");
                    }
                }
                //Place an order (Overnight, Rush, or Standard shipping?)
                else if(input.equalsIgnoreCase("C")) {
                    System.out.println("Please select from the following options:\n\n" +
                            "A. Overnight\n" +
                            "B. Rush\n" +
                            "C. Standard\n" +
                            "Enter your choice: ");
                    input = scan.nextLine();
                    System.out.print("\n");
                    //Overnight
                    if(input.equalsIgnoreCase("A")) {
                        placeOrder(1);
                    }
                    //Rush
                    else if(input.equalsIgnoreCase("B")) {
                        placeOrder(2);
                    }
                    //Standard
                    else if(input.equalsIgnoreCase("C")) {
                        placeOrder(3);
                    }
                    else {
                        System.out.print("Invalid Selection!\n");
                    }
                }
                //View purchases
                else if(input.equalsIgnoreCase("D")) {
                    System.out.println("Please select from the following options:\n\n" +
                            "A. View shipped orders\n" +
                            "B. View unshipped orders\n" +
                            "Enter your choice: ");
                    input = scan.nextLine();
                    System.out.print("\n");
                    //View shipped orders
                    if(input.equalsIgnoreCase("A")) {

                    }
                    //View unshipped orders
                    else if(input.equalsIgnoreCase("B")) {

                    }
                    else {
                        System.out.print("Invalid selection!\n");
                    }
                }
                //Quit
                else if(input.equalsIgnoreCase("X")) {
                    System.out.print("Goodbye!\n");
                }
                else {
                    cont = false;
                    System.out.print("Invalid selection!\n");
                }
            }

            //If you are an employee
            if(option.equals("2")) {
                if(loggedInEmployee) {
                    System.out.println("Please select from the following options:\n\n" +
                            "A. Search a customer by name\n" +
                            "B. Display all information\n" +
                            "C. View orders by priority\n" +
                            "D. Ship an order\n" +
                            "E. List product databse\n" +
                            "F. Add a new product\n" +
                            "G. Remove a product\n" +
                            "X. Quit\n" +
                            "Enter your choice: ");
                    String input = scan.nextLine();
                    System.out.print("\n");

                    //Search a customer by name
                    if(input.equalsIgnoreCase("A")) {

                    }
                    //Display unsorted customer information including first
                    //and last name, address, phone number, order history
                    else if(input.equalsIgnoreCase("B")) {

                    }
                    //View orders by priority of shipment method
                    else if(input.equalsIgnoreCase("C")) {

                    }
                    //Ship an order
                    else if(input.equalsIgnoreCase("D")) {

                    }
                    //List database of products
                    else if(input.equalsIgnoreCase("E")) {
                        System.out.println("Please select from the following options:\n\n" +
                                "A. List data by title\n" +
                                "B. List data by artist\n" +
                                "Enter your choice: ");
                        input = scan.nextLine();
                        System.out.print("\n");
                        //List data by primary key (title)
                        if(input.equalsIgnoreCase("A")) {

                        }
                        //List data by secondary key (artist)
                        else if(input.equalsIgnoreCase("B")) {

                        }
                        else {
                            System.out.print("Invalid selection!\n");
                        }
                    }
                    //Add a new product to database
                    else if(input.equalsIgnoreCase("F")) {
                        addProduct();
                    }
                    //Remove a product from database
                    else if(input.equalsIgnoreCase("G")) {
                        removeProduct();
                    }
                    //Quit
                    else if(input.equalsIgnoreCase("X")) {
                        cont = false;
                        System.out.print("Goodbye!\n");
                    }
                    else {
                        System.out.print("Invalid selection!\n");
                    }
                } else {
                    System.out.print("You are not logged in as an employee yet.\n"
                            + "Please try to enter the store and log in again!\n");
                }
            }
        }
    /**
     * Returns if the customer's username and password match
     * @return true if matched, false if unmatched
     */
    public static boolean idCustomer() {
        String username;
        String password;

        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        username = scan.nextLine();
        System.out.print("Please enter your password: ");
        password = scan.nextLine();

        //if matched
        //return true

        //else return false

        return false;
    }

    /**
     * Returns if the employee's username and password match
     * @return true if matched, false if unmatched
     */
    public static boolean idEmployee() {
        String username;
        String password;

        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        username = scan.nextLine();
        System.out.print("Please enter your password: ");
        password = scan.nextLine();
        System.out.print("\n");

        if(username.equals("user000") && password.equals("123456")) {
            return true;
        } else {
            return false;
        }
    }

    public static void createAccount() {
        String username;
        String password;

        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        username = scan.nextLine();
        System.out.print("Please enter your password: ");
        password = scan.nextLine();
    }

    public static void placeOrder(int priority) {
    }

    public static void addProduct() {
        System.out.print("Enter the title of the album: ");
        System.out.print("Enter the artist of the album: ");
        System.out.print("Enter the retail price of the album: ");
    }

    public static void removeProduct() {
        System.out.print("Enter the title of the album: ");
        System.out.print("Enter the artist of the album: ");
    }

    public static void searchByPrimary() {

    }

    public static void searchBySecondary() {

    }

    public static void showByPrimary() {

    }

    public static void showBySecondary() {

    }
}
