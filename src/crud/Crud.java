package crud;

import static java.lang.System.out;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import validation.Validation;;

public class Crud {

	Validation validator = new Validation();
	Scanner console = new Scanner(System.in);

	public void insertNumber(Map<Integer, LinkedList<String>> lines) {
		try {
			out.print("Enter line index: ");
			int lineIndex = Integer.parseInt(console.nextLine());
			out.print("Enter number index: ");
			int numberIndex = Integer.parseInt(console.nextLine());
			out.print("Number to be inserted: ");
			String numberToBeInserted = console.nextLine();

			if (validator.isANumber(numberToBeInserted)) {
				lines.get(lineIndex).add(numberIndex - 1, numberToBeInserted);
			} else {
				out.println("This is not a number.");
			}
		} catch (IndexOutOfBoundsException e) {
			printMessage();
		}
	}

	public void readNumber(Map<Integer, LinkedList<String>> lines) {
		try {
			out.print("Enter line index: ");
			int lineIndex = console.nextInt();
			out.print("Enter number index: ");
			int numberIndex = console.nextInt();

			String numberToBeShown = lines.get(lineIndex).get(numberIndex - 1);
			out.println(numberToBeShown);
		} catch (IndexOutOfBoundsException e) {
			printMessage();
		}
	}

	public void modifyNumber(Map<Integer, LinkedList<String>> lines) {
		try {
			out.print("Enter line index: ");
			int lineIndex = Integer.parseInt(console.nextLine());
			out.print("Enter number index: ");
			int numberIndex = Integer.parseInt(console.nextLine());
			out.print("Number to be inserted: ");
			String numberToBeImproved = console.nextLine();

			if (validator.isANumber(numberToBeImproved)) {
				lines.get(lineIndex).remove(numberIndex - 1);
				lines.get(lineIndex).add(numberIndex - 1, numberToBeImproved);
			} else {
				out.println("This is not a number.");
			}
		} catch (IndexOutOfBoundsException e) {
			printMessage();
		}
	}

	public void removeNumber(Map<Integer, LinkedList<String>> lines) {
		try {
			out.print("Enter line index: ");
			int lineIndex = console.nextInt();
			out.print("Enter number index: ");
			int numberIndex = console.nextInt();

			lines.get(lineIndex).remove(numberIndex - 1);
		} catch (IndexOutOfBoundsException e) {
			printMessage();
		}
	}

	public void printMessage() {
		out.println("Enter valid indexes!");
	}
}