package crud;

import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import validation.Validation;;

public class Crud {
	Validation validator = new Validation();

	public void insertNumber(Map<Integer, LinkedList<String>> lines) {
		try {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter line index: ");
			int lineIndex = Integer.parseInt(input.nextLine());
			System.out.print("Enter number index: ");
			int numberIndex = Integer.parseInt(input.nextLine());
			System.out.print("Number to be inserted: ");
			String numberToBeInserted = input.nextLine();

			if (validator.isANumber(numberToBeInserted)) {
				lines.get(lineIndex - 1).add(numberIndex - 1, numberToBeInserted);
			} else {
				System.out.println("This is not a number.");
			}
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("->Enter valid indexes!<-");
		}
	}

	public void readNumber(Map<Integer, LinkedList<String>> lines) {
		try {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter line index: ");
			int lineIndex = input.nextInt();
			System.out.print("Enter number index: ");
			int numberIndex = input.nextInt();

			String readNumber = lines.get(lineIndex - 1).get(numberIndex - 1);
			System.out.println(readNumber);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("->Enter valid indexes!<-");
		}
	}

	public void modifyNumber(Map<Integer, LinkedList<String>> lines) {
		try {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter line index: ");
			int lineIndex = Integer.parseInt(input.nextLine());
			System.out.print("Enter number index: ");
			int numberIndex = Integer.parseInt(input.nextLine());
			System.out.print("Number to be inserted: ");
			String valueToImprove = input.nextLine();

			if (validator.isANumber(valueToImprove)) {
				lines.get(lineIndex - 1).remove(numberIndex - 1);
				lines.get(lineIndex - 1).add(numberIndex - 1, valueToImprove);
			} else {
				System.out.println("This is not a number.");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("->Enter valid indexes!<-");
		}
	}

	public void removeNumber(Map<Integer, LinkedList<String>> lines) {
		try {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter line index: ");
			int lineIndex = input.nextInt();
			System.out.print("Enter number index: ");
			int numberIndex = input.nextInt();

			lines.get(lineIndex - 1).remove(numberIndex - 1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("->Enter valid indexes!<-");
		}
	}
}