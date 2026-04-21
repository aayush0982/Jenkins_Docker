package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.entities.Trainee;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		System.out.println("Application Started");

		ITraineeSerivces trs = context.getBean(ITraineeSerivces.class);

		Scanner sc = new Scanner(System.in);
		int choice = 1;

		while (choice != 0) {

			System.out.println("\n--- MENU ---");
			System.out.println("1. Add Trainee");
			System.out.println("2. View All");
			System.out.println("3. Find By Id");
			System.out.println("4. Delete By Id");
			System.out.println("5. Update Trainee");
			System.out.println("6. Find By Name");
			System.out.println("7. Find By Name AND Salary");
			System.out.println("8. Find By Name OR Salary");
			System.out.println("9. Salary Greater Than");
			System.out.println("10. Salary Less Than");
			System.out.println("11. Salary >= ");
			System.out.println("12. Salary <= ");
			System.out.println("13. Find By Name Ignore Case");
			System.out.println("0. Exit");

			System.out.print("Enter choice: ");
			choice = sc.nextInt();

			switch (choice) {

			case 1:
				sc.nextLine();
				System.out.print("Enter Name: ");
				String name = sc.nextLine();

				System.out.print("Enter Domain: ");
				String domain = sc.nextLine();

				System.out.print("Enter Location: ");
				String location = sc.nextLine();

				System.out.print("Enter Salary: ");
				double sal = sc.nextDouble();

				trs.addTrainee(new Trainee(name, domain, location, sal));
				System.out.println("Trainee Added");
				break;

			case 2:
				trs.fetchAll().forEach(System.out::println);
				break;

			case 3:
				System.out.print("Enter ID: ");
				int id = sc.nextInt();
				Optional<Trainee> t = trs.fetchById(id);
				System.out.println(t.orElse(null));
				break;

			case 4:
				System.out.print("Enter ID: ");
				trs.deleteById(sc.nextInt());
				System.out.println("Deleted");
				break;

			case 5:
				System.out.print("Enter ID: ");
				int uid = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter Name: ");
				String uname = sc.nextLine();

				System.out.print("Enter Domain: ");
				String udom = sc.nextLine();

				System.out.print("Enter Location: ");
				String uloc = sc.nextLine();

				System.out.print("Enter Salary: ");
				double usal = sc.nextDouble();

				Trainee ut = new Trainee(uname, udom, uloc, usal);
				ut.setId(uid);

				trs.updateTrainee(ut);
				System.out.println("Updated");
				break;

			case 6:
				sc.nextLine();
				System.out.print("Enter Name: ");
				String n = sc.nextLine();

				List<Trainee> list = trs.fetchByName(n);
				if (list.isEmpty())
					System.out.println("No trainee found");
				else
					list.forEach(System.out::println);
				break;

			case 7:
				sc.nextLine();
				System.out.print("Enter Name: ");
				String n1 = sc.nextLine();
				System.out.print("Enter Salary: ");
				double s1 = sc.nextDouble();

				System.out.println(trs.findByNameAndSalary(n1, s1));
				break;

			case 8:
				sc.nextLine();
				System.out.print("Enter Name: ");
				String n2 = sc.nextLine();
				System.out.print("Enter Salary: ");
				double s2 = sc.nextDouble();

				System.out.println(trs.findByNameOrSalary(n2, s2));
				break;

			case 9:
				System.out.print("Enter Salary: ");
				trs.findBySalaryGreaterThan(sc.nextDouble()).forEach(System.out::println);
				break;

			case 10:
				System.out.print("Enter Salary: ");
				trs.findBySalaryLessThan(sc.nextDouble()).forEach(System.out::println);
				break;

			case 11:
				System.out.print("Enter Salary: ");
				trs.findBySalaryGreaterThanEqual(sc.nextDouble()).forEach(System.out::println);
				break;

			case 12:
				System.out.print("Enter Salary: ");
				double sal1 = sc.nextDouble();
				trs.findBySalaryLessThanEqual(sal1).forEach(System.out::println);
				break;

			case 13:
				sc.nextLine();
				System.out.print("Enter Name: ");
				System.out.println(trs.findByNameIgnoreCase(sc.nextLine()));
				break;

			case 0:
				System.out.println("Exiting...");
				break;

			default:
				System.out.println("Invalid choice");
			}
		}
		sc.close();
	}
}