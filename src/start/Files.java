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

	public String enterFilePath(Map<Integer, LinkedList<String>> lines) {
		String filepath = "";
		boolean isCorrect = false;
		while (!isCorrect) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter file path: ");
			filepath = scanner.nextLine();
			File file = new File(filepath);
			try {
				copyContentFromFile(lines, file);
				isCorrect = true;
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
			}
		}
		return filepath;
	}

	public void copyContentFromFile(Map<Integer, LinkedList<String>> lines, File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		int line = 0;
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

	public void switchTwoLines(Map<Integer, LinkedList<String>> lines) {

		Scanner console = new Scanner(System.in);
		System.out.print("Enter first line index: ");
		int firstLineIndex = Integer.parseInt(console.nextLine());
		System.out.print("Enter second line index: ");
		int secondLineIndex = Integer.parseInt(console.nextLine());
		if (!(firstLineIndex < 0) && !(firstLineIndex > lines.size())) {
			if (!(secondLineIndex < 0) && !(secondLineIndex > lines.size())) {
				LinkedList<String> tempLineIndex = lines.get(firstLineIndex - 1);
				lines.put(firstLineIndex - 1, lines.get(secondLineIndex - 1));
				lines.put(secondLineIndex - 1, tempLineIndex);
			} else {
				System.out.println("Enter valid indexes!");
			}
		} else {
			System.out.println("Enter valid indexes!");
		}
	}

	public void switchTwoNumbersFromDiffLines(Map<Integer, LinkedList<String>> lines) {
		try {
			Scanner console = new Scanner(System.in);
			System.out.print("Enter first line index and first line number index: ");
			int firstLineIndex = console.nextInt();
			int firstNumberIndex = console.nextInt();
			System.out.print("Enter second line index and second line number index: ");
			int secondLineIndex = console.nextInt();
			int secondNumberIndex = console.nextInt();

			String firstValue = lines.get(firstLineIndex - 1).get(firstNumberIndex - 1);
			String secondValue = lines.get(secondLineIndex - 1).get(secondNumberIndex - 1);
			lines.get(firstLineIndex - 1).remove(firstNumberIndex - 1);
			lines.get(secondLineIndex - 1).remove(secondNumberIndex - 1);
			lines.get(firstLineIndex - 1).add(firstNumberIndex - 1, secondValue);
			lines.get(secondLineIndex - 1).add(secondNumberIndex - 1, firstValue);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Enter valid indexes!");
		}
	}

	public void copyContentToFile(Map<Integer, LinkedList<String>> lines, String filepath)
			throws FileNotFoundException {
		File file = new File(filepath);
		PrintWriter output = new PrintWriter(file);
		for (Integer line : lines.keySet()) {
			for (String word : lines.get(line)) {
				output.print(word + " ");
			}
			output.println();
		}
		output.close();
	}

	public static void main(String[] args) throws Exception {
		Files files = new Files();
		Crud crud = new Crud();
		Validation validator = new Validation();
		Map<Integer, LinkedList<String>> lines = new HashMap<Integer, LinkedList<String>>();
		String filepath = files.enterFilePath(lines);
		Scanner console = new Scanner(System.in);
		int choosenOption = 0;
		do {
			files.showMenu();
			choosenOption = Integer.parseInt(console.nextLine());
			switch (choosenOption) {
			case 1:
				if (validator.isNumberCorrect(lines) && validator.isLineCorrect(lines)) {
					files.switchTwoLines(lines);
				}
				break;
			case 2:
				if (validator.isNumberCorrect(lines) && validator.isLineCorrect(lines)) {
					files.switchTwoNumbersFromDiffLines(lines);
				}
				break;
			case 3:
				crud.insertNumber(lines);
				break;
			case 4:
				crud.readNumber(lines);
				break;
			case 5:
				crud.modifyNumber(lines);
				break;
			case 6:
				crud.removeNumber(lines);
				break;
			case 0:
				files.copyContentToFile(lines, filepath);
				break;
			default:
				System.out.println("Invalid option.");
			}
		} while (choosenOption != 0);
	}
}