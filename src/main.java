
/**
 * UserInterface.java
 * @author Feiyue Zhang
 * CIS 22C, Final Project
 */

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import courseProject.Order.Shipping;

public class main {
    public static void main(String[] args) {
        //Print welcome message
        System.out.print("Welcome to A+ Music Store!\n\n");

        //Read orders file

        Heap orderHeap = new Heap();
        int counter = orderHeap.get_size();
 		/*
 		public Order(int id,final LocalDate orderDate,
 		             final int quantity,final Shipping shipment,
 		             final String productTitle,final String userName) {
    	this.id = id;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.shipment =  Shipping.OVERNIGHT;
        this.productTitle = productTitle;
        this.userName = userName;
    }
 		 * */
        orderHeap.insert(new Order(LocalDate.now(),0,"",""));
        //Read customers database file

        File file_customers = new File("customers.txt");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file_customers));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Hash<Customer> h = new Hash<Customer>(32);
        ArrayList<Customer> list = new ArrayList<>();

        try {
            String[] temp = new String[11];
            List<Order> orderIds = new List<>();
            while ((temp[0] = br.readLine()) != null) {
                for (int i = 1; i < 11; i++) {
                    temp[i] = br.readLine();
                }
                String[] orders = temp[9].split(",");
                for(int k = 0; k < orders.length; k++){
                    orderIds.addLast(orderHeap.get_element(Integer.parseInt(orders[k])));
                }
                Customer customer = new Customer(temp[0], temp[1],
                                                 temp[2], temp[3],
                                                 temp[4], temp[5],
                                                 temp[6], Integer.parseInt(temp[7]),temp[8], orderIds);
                h.insert(customer);
                list.add(customer);
                System.out.println(customer.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read product file

		File file_products = new File("products.txt");

 		br = null;
 		try {
 			br = new BufferedReader(new FileReader(file_products));
 		} catch (FileNotFoundException e) {
 			e.printStackTrace();
 		}

 		BST<Product> primaryBST = new BST<Product>();
 		BST<Product> secondaryBST = new BST<Product>();

 		try {
 			String[] temp = new String[9];
 			List<Integer> SongLists = new List<Integer>();
 			while ((temp[0] = br.readLine()) != null) {
 				for (int i = 1; i < 9; i++) {
 					temp[i] = br.readLine();
 				}
 				String[] SList = temp[5].split(",");
 				for(int k = 0; k < SList.length; k++){
 					SongLists.addLast(Integer.parseInt(SList[k]));
 				}
 				Product product1 = new Product(temp[1],temp[1], temp[2],
                                               Double.parseDouble(temp[3]),
                                               Integer.parseInt(temp[4]),
                                               SongLists,LocalDate.parse(temp[6], formatter),temp[7]);
 				primaryBST.insert(product1);
 				Product product2 = new Product(temp[2],temp[1], temp[2],
                                               Double.parseDouble(temp[3]),
                                               Integer.parseInt(temp[4]),
                                               SongLists,LocalDate.parse(temp[6], formatter),temp[7]);
 				secondaryBST.insert(product2);
 			}
 		} catch (IOException e) {
 			e.printStackTrace();
 		}


 		// File file_customers = new File("customers.txt");
 		// Scanner scanner = null;
 		// try{
 		// 	scanner = new Scanner(file_customers);
 		// }
 		// catch(java.io.FileNotFoundException fe) {
 		// 	System.out.print("Can't open customer.txt\n\n");
 		// 	return;
 		// }
 		/*
 		File file_products = new File("products.txt");
 		try{
 			scanner = new Scanner(file_products);
 		}
 		catch(java.io.FileNotFoundException fe) {
 			System.out.print("Can't open products.txt\n\n");
 			return;
 		}*/

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
 				Customer c = new Customer();
 				if(input.equalsIgnoreCase("A")) {
 					while(loggedInCustomer == false) {
 						System.out.print("Please enter your username: ");
 						username = scan.nextLine();
 						System.out.print("Please enter your password: ");
 						password = scan.nextLine();

 						for(int i = 0; i < list.size(); i++) {
 							Customer customer = list.get(i);
 							if(customer.getUserName().equals(username) && customer.getPassWord().equals(password)) {
 								loggedInCustomer = true;
 								c = list.get(i);
 							}
 						}

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
 						cont = true;
 						while(cont) {
 							System.out.print("Please search by entering the title: ");
 							String key = scan.nextLine();
 							if(searchProduct(primaryBST, key)) {
 								List<product> productlist = new List<>();
 								Product product = new Product();
 								for(int i = 0; i = productlist.getlength;i++) {
 									if(productlist.get(i).key.equals(key)) {
 										product = productlist.get(i);
 									}
 								}
 								System.out.print(key + "is in the database!\n");
 								System.out.print("Would you like to order this item? (Y/N)");
 								String s = scan.nextLine();
 								int qty = 0;
 								if(s.equals("N")) {
 									System.out.print("Search again? (Y/N)");
 									s = scan.nextLine();
 									if(s.equals("N")) {
 										cont = false;
 									} else {
 										//qty
 									}
 								}
 								else if(loggedInCustomer && s.equals("Y")) {
 									System.out.println("Please select from the following options:\n\n" +
 											"A. Overnight\n" +
 											"B. Rush\n" +
 											"C. Standard\n" +
 											"Enter your choice: ");
 									input = scan.nextLine();
 									System.out.print("\n");
 									Integer shippingCode = null;
 									//Overnight
 									if(input.equalsIgnoreCase("A")) {
 										//order.getShipment()
 										//placeOrder(1);
                                        shippingCode = 1;
                                    }
 									//Rush
 									else if(input.equalsIgnoreCase("B")) {
 										//order.getShipment()
 										//placeOrder(2);
                                        shippingCode = 2;
 									}
 									//Standard
 									else if(input.equalsIgnoreCase("C")) {
 										//order.getShipment()
 										//placeOrder(3);
                                        shippingCode = 3;
 									}
                                    Order order = new Order(LocalDate.now(),qty,
                                                            Order.Shipping.ofCode(shippingCode),
                                                            product,customer,0);
                                    orderHeap.insert(order);
 									customer.orders.addlast(order);
 									/*
 									else {
 										System.out.print("Invalid Selection!\n");
 									}
 									*/
 								} else {
 									System.out.print("Please create a new account before placing an order.\n");
 								}
 							} else {

 							}
 						}

 						//Place order?

 					}
 					//Find it by secondary key (artist)
 					else if(input.equals("B")) {
 						//Place order?
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
 					if(loggedInCustomer) {
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
 					} else {
 						System.out.print("You need an account before placing an order.\n\n");
 					}

 				}
 				//View purchases
 				else if(input.equalsIgnoreCase("D")) {
 					if(loggedInCustomer) {
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
 					} else {
 						System.out.print("You need an account before viewing purchases.\n\n");
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
 		//close file
 	}

 	/**
      * Returns if the customer's username and password match
      * @return true if matched, false if unmatched
      */
 	public static boolean idCustomer(ArrayList list, Customer customer) {

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

 	public static void createAccount(Hash h) {
 		String username;
 		String password;
 		String firstName;
 		String lastName;
 		String address;
 		String city;
 		String state;
 		int zip;
 		String phoneNumber;

 		Scanner scan = new Scanner(System.in);
 		System.out.print("Please enter your username: ");
 		username = scan.nextLine();
 		System.out.print("Please enter your password: ");
 		password = scan.nextLine();
 		System.out.print("Please enter your first name: ");
 		firstName = scan.nextLine();
 		System.out.print("Please enter your last name: ");
 		lastName = scan.nextLine();
 		System.out.print("Please enter your address: ");
 		address = scan.nextLine();
		System.out.print("Please enter your city: ");
 		city = scan.nextLine();
 		System.out.print("Please enter your state: ");
 		state = scan.nextLine();
 		System.out.print("Please enter your zip code: ");
 		zip = scan.nextInt();
		System.out.print("Please enter your phone number: ");
        phoneNumber = scan.nextLine();

 		h.insert(new Customer(username, password, firstName, lastName, address, city, state, zip, phoneNumber));
 	}

 	public static boolean searchProduct(BST bst, String key) {
 		if(bst.search(
 		        new Product(key, "", 0.0, 0,
                        new List<String>(), LocalDate.of(0,0,0), "")) != null) {
 			return true;
 		} else {
 			return false;
 			//System.out.print("The product you're looking for is not existing.\n\n");
 		}
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
}


