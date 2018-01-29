package start;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import crud.Crud;
import validation.Validation;

public class Files {

	public Map<Integer, LinkedList<String>> lines = new HashMap<Integer, LinkedList<String>>();
	public String filepath = "";
	public File file;
	private static boolean isReadyToGo = false;

	Validation validator = new Validation();
	Crud c = new Crud();

	public void enterFilePath() {
		boolean isCorrect = false;
		while (!isCorrect) {
			try {
				Scanner scanner = new Scanner(System.in);
				System.out.print("Enter file path: ");
				filepath = scanner.nextLine();
				file = new File(filepath);
				if (validator.isFileCorrect(file)) {
					copyContentFromFile();
					isReadyToGo = true;
				}
				isCorrect = true;
			} catch (FileNotFoundException e) {
				System.out.println("File is not found.");
			}
		}
	}

	public void copyContentFromFile() throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		int line = 1;
		while (scanner.hasNextLine()) {
			lines.put(line, new LinkedList<String>());
			String[] words = scanner.nextLine().split(" ");
			for (String word : words) {
				if (!word.isEmpty()) {
					lines.get(line).add(word);
				}
			}
			line++;
		}
	}

	public void showMenu() {
		System.out.println("1.Switch entire line from the file with an entire other line.");
		System.out.println(
				"2.Switch number at specific index in one line with a number with specific index from another line.");
		System.out.println("3.Insert a number at a given position in the file.");
		System.out.println("4.Read a number at a given position.");
		System.out.println("5.Modify a number at a given position.");
		System.out.println("6.Remove a number at a given position.");
		System.out.println("0.Exit");
		System.out.print("Make your choise: ");
	}

	public void switchTwoLines() {

		Scanner console = new Scanner(System.in);
		System.out.print("Enter first line index: ");
		int firstLineIndex = Integer.parseInt(console.nextLine());
		System.out.print("Enter second line index: ");
		int secondLineIndex = Integer.parseInt(console.nextLine());
		if (!(firstLineIndex < 0) && !(firstLineIndex > lines.size())) {
			if (!(secondLineIndex < 0) && !(secondLineIndex > lines.size())) {
				LinkedList<String> tempLineIndex = lines.get(firstLineIndex);
				lines.put(firstLineIndex, lines.get(secondLineIndex));
				lines.put(secondLineIndex, tempLineIndex);
			} else {
				System.out.println("Enter valid indexes!");
			}
		} else {
			System.out.println("Enter valid indexes!");
		}
	}

	public void switchTwoNumbersFromDiffLines() {
		try {
			Scanner console = new Scanner(System.in);
			System.out.print("Enter first line index and first line number index: ");
			int firstLineIndex = console.nextInt();
			int firstNumberIndex = console.nextInt();
			System.out.print("Enter second line index and second line number index: ");
			int secondLineIndex = console.nextInt();
			int secondNumberIndex = console.nextInt();

			String firstValue = lines.get(firstLineIndex).get(firstNumberIndex - 1);
			String secondValue = lines.get(secondLineIndex).get(secondNumberIndex - 1);
			lines.get(firstLineIndex).remove(firstNumberIndex - 1);
			lines.get(secondLineIndex).remove(secondNumberIndex - 1);
			lines.get(firstLineIndex).add(firstNumberIndex - 1, secondValue);
			lines.get(secondLineIndex).add(secondNumberIndex - 1, firstValue);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Enter valid indexes!");
		}
	}

	public void copyContentToFile() throws FileNotFoundException {
		file = new File(filepath);
		PrintWriter output = new PrintWriter(file);
		for (Integer line : lines.keySet()) {
			for (String word : lines.get(line)) {
				output.print(word + " ");
			}
			output.println();
		}
		output.close();
	}

	public void start() throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		int option;
		do {
			showMenu();
			option = Integer.parseInt(console.nextLine());
			switch (option) {
			case 1:
				switchTwoLines();
				break;
			case 2:
				switchTwoNumbersFromDiffLines();
				break;
			case 3:
				c.insertNumber(lines);
				break;
			case 4:
				c.readNumber(lines);
				break;
			case 5:
				c.modifyNumber(lines);
				break;
			case 6:
				c.removeNumber(lines);
				break;
			case 0:
				copyContentToFile();
				break;
			default:
				System.out.println("Invalid option.");
			}
		} while (option != 0);
	}

	public static void main(String[] args) throws Exception {
		Files files = new Files();
		files.enterFilePath();
		if (isReadyToGo) {
			files.start();
		}
	}
}