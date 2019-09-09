package exercise1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;;

/*
 * Exercise1.1:
 		readFile();
		writeFile();
 * Exercise1.2:
 		standardizeOwner();
  		writeFile1();
 * Exercise1.3:
  		writeFile2();
 * Exercise1.4:
 */
public class Main1 {
	private static final String FILENAME = "C:\\Users\\FPT\\.eclipse\\JavaIO_Socket\\src\\main\\java\\exercise1\\output1.txt";
	static List<Device> listDevice = new ArrayList<Device>();
	static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	static BufferedWriter writer = null;
	static BufferedReader reader;
	public static final char SPACE = ' ';
	public static final char TAB = '\t';
	public static final char BREAK_LINE = '\n';

	public static void readFile() {
		try {
			reader = new BufferedReader(
					new FileReader("C:\\Users\\FPT\\.eclipse\\JavaIO_Socket\\src\\main\\java\\exercise1\\input1.txt"));
			String line = reader.readLine();
			while (line != null) {
				Device device = new Device();
				// System.out.println(line);
				String[] input = line.split(",");
				device.setCode(input[0]);
				device.setName(input[1]);
				device.setOwner(input[2]);
				try {
					device.setInputDate(formatter.parse(input[3]));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				device.setWarrantyYear(Integer.parseInt(input[4]));
				listDevice.add(device);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeFile() {

		try {
			writer = new BufferedWriter(new FileWriter(FILENAME));
			Collections.sort(listDevice, new Sortbyyear());
			// System.out.println(listDevice.size());
			for (int i = 0; i < listDevice.size(); i++) {
				StringBuilder sb = new StringBuilder();
				String str1 = sb.append(listDevice.get(i).getCode()).append(",").append(listDevice.get(i).getName())
						.append(",").append(listDevice.get(i).getOwner()).append(",")
						.append(formatter.format(listDevice.get(i).getInputDate())).append(",")
						.append(listDevice.get(i).getWarrantyYear()).toString();
				System.out.println(str1);
				writer.write(str1);
				writer.newLine();
			}
			writer.write("###\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String standardizeOwner(String str) {
		// remove the extra white space at the top, end
		str = str.trim();

		// cut off the extra white space in between the strings
		str = str.replaceAll("\\s+", " ");

		// normalize the words that will capitalize the first word and proper nouns
		String temp[] = str.split(" ");
		str = "";
		for (int i = 0; i < temp.length; i++) {
			str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1).toLowerCase();
			if (i < temp.length - 1)
				str += " ";
		}
		// System.out.println(str);
		return str;
	}

	public static void writeFile1() {

		try {
			File file = new File(FILENAME);
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			writer = new BufferedWriter(fw);
			Collections.sort(listDevice, new SortbyyearDESC());
			for (int i = 0; i < listDevice.size(); i++) {
				StringBuilder sb = new StringBuilder();
				String ownerName = standardizeOwner(listDevice.get(i).getOwner());
				String str1 = sb.append(listDevice.get(i).getCode()).append(",").append(listDevice.get(i).getName())
						.append(",").append(ownerName).append(",")
						.append(formatter.format(listDevice.get(i).getInputDate())).append(",")
						.append(listDevice.get(i).getWarrantyYear()).toString();
				System.out.println(str1);
				writer.write(str1);
				writer.newLine();
			}
			writer.write("###\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean findCode(String str) {
		String str1 = "TOPICA";
		return str.contains(str1);
	}

	public static int findDate(Date date) throws ParseException {
		String dateMin = "31/10/2018";
		String dateMax = "31/10/2019";
		Date date1 = formatter.parse(dateMin);
		Date date2 = formatter.parse(dateMax);

		// If date>date1 =>i>0
		int i = date.compareTo(date1);

		// If date<date2 =>j<0
		int j = date.compareTo(date2);

		if (i > 0 && j < 0)
			return 1;
		return 0;
	}

	public static void writeFile2() throws ParseException {
		try {
			File file = new File(FILENAME);
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			writer = new BufferedWriter(fw);
			Collections.sort(listDevice, new Sortbyyear());
			for (int i = 0; i < listDevice.size(); i++) {
				StringBuilder sb = new StringBuilder();
				Date inputDate=listDevice.get(i).getInputDate();
				String code=listDevice.get(i).getCode();
				String ownerName = standardizeOwner(listDevice.get(i).getOwner());
				if(findCode(code)==true && findDate(inputDate)==1) {
					String str1 = sb.append(code).append(",").append(listDevice.get(i).getName())
							.append(",").append(ownerName).append(",")
							.append(formatter.format(inputDate)).append(",")
							.append(listDevice.get(i).getWarrantyYear()).toString();
					System.out.println(str1);
					writer.write(str1);
					writer.newLine();
				}
			}
			writer.write("###\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String findOwner() {
		StringBuilder sb = new StringBuilder();
		String name = null;
		for (int i = 0; i < listDevice.size(); i++) {
			name = sb.append(listDevice.get(i).getOwner()).append(" ").toString();
		}
		name = standardizeOwner(name);
		//System.out.println(name);
		Map<String, Integer> wordMap = countWords(name);
		int max = findMax(wordMap);
		String str = getKey(wordMap, max);
		return str;

	}

	private static Map<String, Integer> countWords(String name) {
		// Constructor of wordMap
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		if (name == null) {
			return wordMap;
		}
		int size = name.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			if (name.charAt(i) != SPACE && name.charAt(i) != TAB && name.charAt(i) != BREAK_LINE) {
				// build a word
				sb.append(name.charAt(i));
			} else {
				// add to wordMap
				addWord(wordMap, sb);
				sb = new StringBuilder();
			}
		}
		// add end of word has found to wordMap
		addWord(wordMap, sb);
		return wordMap;
	}

	private static void addWord(Map<String, Integer> wordMap, StringBuilder sb) {
		String word = sb.toString();
		int count = 0;
		if (word.length() == 0) {
			return;
		}
		if (wordMap.containsKey(word)) {
			count = wordMap.get(word) + 1;
			wordMap.put(word, count);
		} else {
			wordMap.put(word, 1);
		}
	}

	public static int findMax(Map<String, Integer> wordMap) {
		int max = Collections.max(wordMap.values());
		return max;
	}

	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}
	public static void writeFile3() throws ParseException {
		try {
			File file = new File(FILENAME);
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			writer = new BufferedWriter(fw);
			String str1=findOwner();
			writer.write(str1);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] argv) throws ParseException {
		readFile();
		writeFile();
		writeFile1();
		writeFile2();
		writeFile3();
		findOwner();
	}
}
