package org.example.util;

import java.io.File;


/**
 * Created by Arif on 3/9/2016.
 */
public class CaseConverter {
	public static boolean isPascalCaseString(String simpleString) {
		char firstChar = simpleString.charAt(0);
		return firstChar >= 'A' && firstChar <= 'Z';
	}

	public static boolean isCamelCaseString(String simpleString) {
		char firstChar = simpleString.charAt(0);
		return firstChar >= 'a' && firstChar <= 'z';
	}

	public static String toCamelCase(String simpleString) {
		if (HelperUtils.isNotEmpty(simpleString) && isPascalCaseString(simpleString)) {
			simpleString = simpleString.trim();
		} else {
			return simpleString;
		}
		String camelCaseString = "";
		int i = 0;
		String temp1 = "", temp2 = "";

		for (i = 0; i < simpleString.length(); i++) {
			if (simpleString.charAt(i) != ' ') {
				temp1 = temp1 + simpleString.charAt(i);
			} else {
				temp2 = temp2 + simpleString.charAt(i + 1);
				if (temp2.charAt(0) >= 'a' && temp2.charAt(0) <= 'z') {
					temp1 = temp1 + temp2.toUpperCase();
					i++;
				}
			}
		}
		temp2 = "";
		if (temp1.length() > 0) {
			if (temp1.charAt(0) >= 'A' && temp1.charAt(0) <= 'Z') {
				temp2 = temp2 + temp1.charAt(0);
				temp2 = temp2.toLowerCase();
			} else {
				temp2 = temp2 + temp1.charAt(0);
			}
		} else {
			System.out.println("#################");
		}

		for (i = 1; i < temp1.length(); i++) {
			temp2 = temp2 + temp1.charAt(i);
		}
		camelCaseString = temp2;
		return camelCaseString;
	}

	public static String toPascalCase(String simpleString) {
		if (HelperUtils.isNotEmpty(simpleString) && isCamelCaseString(simpleString)) {
			simpleString = simpleString.trim();
		} else {
			return simpleString;
		}
		String pascalCaseString = "";
		int i = 0;
		String temp1 = "", temp2 = "";

		for (i = 0; i < simpleString.length(); i++) {
			if (simpleString.charAt(i) != ' ') {
				temp1 = temp1 + simpleString.charAt(i);
			} else {
				temp2 = temp2 + simpleString.charAt(i + 1);
				if (temp2.charAt(0) >= 'a' && temp2.charAt(0) <= 'z') {
					temp1 = temp1 + temp2.toUpperCase();
					i++;
				}
			}
		}
		temp2 = "";
		if (temp1.length() > 0) {
			if (temp1.charAt(0) >= 'a' && temp1.charAt(0) <= 'z') {
				temp2 = temp2 + temp1.charAt(0);
				temp2 = temp2.toUpperCase();
			} else {
				temp2 = temp2 + temp1.charAt(0);
			}
		} else {
			System.out.println("#################");
		}

		for (i = 1; i < temp1.length(); i++) {
			temp2 = temp2 + temp1.charAt(i);
		}
		pascalCaseString = temp2;
		return pascalCaseString;
	}

	public static String toLowerCase(String simpleString) {
		if (HelperUtils.isNotEmpty(simpleString)) {
			simpleString = simpleString.trim();
		} else {
			return simpleString;
		}
		return simpleString.toLowerCase();
	}

	public static String toUpperCase(String simpleString) {
		if (HelperUtils.isNotEmpty(simpleString)) {
			simpleString = simpleString.trim();
		} else {
			return simpleString;
		}
		return simpleString.toUpperCase();
	}

	public static String toSentence(String multiWordString) {
		if (HelperUtils.isNotEmpty(multiWordString)) {
			multiWordString = multiWordString.trim();
		} else {
			return multiWordString;
		}
		String sentence = "";
		multiWordString += " ";
		String tempString = "";
		int i = 0;
		for (i = 0; i < multiWordString.length(); i++) {
			if (multiWordString.charAt(i) >= 'a' && multiWordString.charAt(i) <= 'z') {
				sentence += multiWordString.charAt(i);
			}
			if ((multiWordString.charAt(i) >= 'a' && multiWordString.charAt(i) <= 'z')
					&& (multiWordString.charAt(i + 1) >= 'A' && multiWordString.charAt(i + 1) <= 'Z')) {
				sentence += " ";
			}
			if (multiWordString.charAt(i) >= 'A' && multiWordString.charAt(i) <= 'Z') {
				sentence += multiWordString.charAt(i);
			}
		}
		if (sentence.charAt(0) >= 'a' && sentence.charAt(0) <= 'z') {
			tempString += sentence.charAt(0);
			// tempString= toUpperCase(tempString);
			// System.out.println(Character.toString(sentence.charAt(0)));
			/*
			 * i=1; while (sentence.charAt(i)!=' ') { tempString = tempString +
			 * sentence.charAt(i); i++; }
			 */
			sentence = sentence.replaceFirst(Character.toString(sentence.charAt(0)), tempString);
		}
		return sentence;
	}

	public static String toSnakeCase(String multiWordString) {
		return toUnderscoreWord(multiWordString);
	}

	public static String toUnderscoreWord(String multiWordString) {
		if (HelperUtils.isNotEmpty(multiWordString)) {
			multiWordString = multiWordString.trim();
		} else {
			return multiWordString;
		}
		String sentence = "";
		multiWordString += " ";
		String tempString = "";
		int i = 0;
		for (i = 0; i < multiWordString.length(); i++) {
			if (multiWordString.charAt(i) >= 'a' && multiWordString.charAt(i) <= 'z') {
				sentence += multiWordString.charAt(i);
			}
			if ((multiWordString.charAt(i) >= 'a' && multiWordString.charAt(i) <= 'z')
					&& (multiWordString.charAt(i + 1) >= 'A' && multiWordString.charAt(i + 1) <= 'Z')) {
				sentence += "_";
			}
			if (multiWordString.charAt(i) >= 'A' && multiWordString.charAt(i) <= 'Z') {
				sentence += multiWordString.charAt(i);
			}
		}
		if (sentence.charAt(0) >= 'a' && sentence.charAt(0) <= 'z') {
			tempString += sentence.charAt(0);
			// tempString= toUpperCase(tempString);
			// System.out.println(Character.toString(sentence.charAt(0)));
			/*
			 * i=1; while (sentence.charAt(i)!=' ') { tempString = tempString +
			 * sentence.charAt(i); i++; }
			 */
			sentence = sentence.replaceFirst(Character.toString(sentence.charAt(0)), tempString);
		}
		return sentence;
	}

	public static void makeDirectory(String dirName) {
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdir();
			System.out.println("Created");
		}

	}

	public static void main(String[] args) {
		System.out.println(toPascalCase("ThisIsMyFirstSentence"));
		String sentence = toSentence(toPascalCase("thisIsMyFirstSentence"));

		System.out.println(sentence);

		sentence = toSentence(toCamelCase("ThisIsMyFirstSentenceId"));
		System.out.println(sentence);

		String[] words = sentence.split(" ");
		String lastWord = words[words.length - 1];
		if (lastWord.equalsIgnoreCase("id")) {
			System.out.println("Trimmed: " + sentence.substring(0, sentence.length() - 3));
		}

		sentence = toUnderscoreWord(toPascalCase("thisIsMyFirstSentenceId"));
		System.out.println(sentence);

	}

	public static String toUrlSentence(String multiWordString) {
		return multiWordString.replaceAll(" ", "+");
	}
}
