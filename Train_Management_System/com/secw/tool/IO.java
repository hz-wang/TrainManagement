package com.secw.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Title		: IO.java
 * Description	: This class is to do input and output streams
 * @author Zhang Yufeng, Wang Haozhe
 */
public class IO{

	/**
	 * The readData method of IO class
	 * This method is used to read data from files.
	 * @param path
	 * @return result
	 * @throws IOException
	 */
	public static ArrayList<String> readData(String path) throws IOException {
		String line = "";
		ArrayList<String> result = new ArrayList<String>();
		
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("File doesn\'t exist!"); //TODO gui output
		} else {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(
					fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			while ((line = bufferedReader.readLine()) != null) {
				result.add(line);
			}
			fileInputStream.close();
			inputStreamReader.close();
			bufferedReader.close();
		}
		
		return result;
	}

	/**
	 * The writeData method of IO class
	 * This method is used to write data to files.
	 * @param path
	 * @param result
	 * @throws IOException
	 */
	public static void writeData(String path, ArrayList<String> result) throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter(path)); //TODO Hasn't been done
		for (int i = 0; i < result.size(); i++) {
			printWriter.println(result.get(i));
		}
		printWriter.close();
	}


}
