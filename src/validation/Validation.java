package validation;

import java.util.LinkedList;
import java.util.Map;

public class Validation {
	public boolean isLineCorrect(Map<Integer, LinkedList<String>> lines) {
		for (Integer line : lines.keySet()) {
			for (String word : lines.get(line)) {
				if (word.startsWith(" ") || word.startsWith("\u0009")) {
					System.out.printf("Line %d start with space or tab.\n", line + 1);
					return false;
				}
			}
		}
		return true;
	}

	public boolean isNumberCorrect(Map<Integer, LinkedList<String>> lines) {
		for (Integer line : lines.keySet()) {
			for (String word : lines.get(line)) {
				if (word.startsWith("0")) {
					System.out.printf("At line <%d>, character<%s> starts with 0.\n", line + 1, word);
					return false;
				}
			}
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