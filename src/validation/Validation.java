package validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Validation {

	public boolean isFileCorrect(File file) throws FileNotFoundException {
		if (!isLineCorrect(file) || !isNumberCorrect(file)) {
			System.out.println("Content is not correct.");
			return false;
		}
		return true;
	}

	public boolean isLineCorrect(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int line = 1;
		while (input.hasNextLine()) {
			String sentence = input.nextLine();
			if (sentence.startsWith(" ") || sentence.startsWith("\u0009")) {
				System.out.printf("Line %d start with space or tab.\n", line);
				return false;
			}
			line++;
		}
		return true;
	}

	public boolean isNumberCorrect(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int line = 1;
		while (input.hasNextLine()) {
			String[] words = input.nextLine().split(" ");
			for (String word : words) {
				if (word.startsWith("0")) {
					System.out.printf("At line <%d>, character<%s> starts with 0.\n", line, word);
					return false;
				}
			}
			line++;
		}
		return true;
	}

	public boolean isANumber(String number) {
		char[] symbols = number.toCharArray();
		for (Character c : symbols) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}