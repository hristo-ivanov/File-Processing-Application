package crud;

import static java.lang.System.out;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import validation.Validation;;

public class Crud {
	public int lineIndex;
	public int numberIndex;
	public String numberToBeInserted;
	public String numberToBeShown;
	public String numberToBeImproved;
	Validation validator = new Validation();
	Scanner input = new Scanner(System.in);

	public void insertNumber(Map<Integer, LinkedList<String>> lines) {
		try {
			out.print("Enter line index: ");
			lineIndex = Integer.parseInt(input.nextLine());
			out.print("Enter number index: ");
			numberIndex = Integer.parseInt(input.nextLine());
			out.print("Number to be inserted: ");
			numberToBeInserted = input.nextLine();

			if (validator.isANumber(numberToBeInserted)) {
				lines.get(lineIndex - 1).add(numberIndex - 1, numberToBeInserted);
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
			lineIndex = input.nextInt();
			out.print("Enter number index: ");
			numberIndex = input.nextInt();

			numberToBeShown = lines.get(lineIndex - 1).get(numberIndex - 1);
			out.println(numberToBeShown);
		} catch (IndexOutOfBoundsException e) {
			printMessage();
		}
	}

	public void modifyNumber(Map<Integer, LinkedList<String>> lines) {
		try {
			out.print("Enter line index: ");
			lineIndex = Integer.parseInt(input.nextLine());
			out.print("Enter number index: ");
			numberIndex = Integer.parseInt(input.nextLine());
			out.print("Number to be inserted: ");
			numberToBeImproved = input.nextLine();

			if (validator.isANumber(numberToBeImproved)) {
				lines.get(lineIndex - 1).remove(numberIndex - 1);
				lines.get(lineIndex - 1).add(numberIndex - 1, numberToBeImproved);
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
			lineIndex = input.nextInt();
			out.print("Enter number index: ");
			numberIndex = input.nextInt();

			lines.get(lineIndex - 1).remove(numberIndex - 1);
		} catch (IndexOutOfBoundsException e) {
			printMessage();
		}
	}

	public void printMessage() {
		out.println("Enter valid indexes!");
	}
}