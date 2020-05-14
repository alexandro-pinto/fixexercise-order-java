package application;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		System.out.println("Enter cliente data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Birth date (DD/MM/YYYY): ");
		Date birthDate = sdf.parse(sc.next());
		System.out.println("Enter order data:");
		System.out.print("Status: ");
		sc.nextLine();
		String status = sc.nextLine();
		System.out.print("How many items to this order? ");
		int qtdItem = sc.nextInt(); 
		
		Order order = new Order(new Date(System.currentTimeMillis()), OrderStatus.valueOf(status), new Client(name, email, birthDate));
		
		for (int i = 0; i < qtdItem; i++) {
			System.out.println("Enter #"+ (i+1) +" item data:");
			System.out.print("Product name: ");
			sc.nextLine();
			String productName = sc.nextLine();
			System.out.print("Product price: ");
			Double price = sc.nextDouble();
			System.out.print("Quantity: ");
			int quantity = sc.nextInt();
			order.addItem(new OrderItem(new Product(productName, price) , quantity));
		}
		
		System.out.println();
		System.out.println("ORDER SUMMARY:");
		System.out.println("Order moment:" + sdf2.format(order.getMoment()));
		System.out.println("Order status: "+ order.getStatus());
		System.out.println("Client: " + order.getClient());
		System.out.println("Order items:");
		for (OrderItem item : order.getItems()) {
			System.out.println(item);
		}
		System.out.println("Total price: $" +  String.format("%.2f", order.total()));
		sc.close();
	}
}
