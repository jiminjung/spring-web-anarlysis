package kr.ac.jbnu.se.awp.gitplay4.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.tribes.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import kr.ac.jbnu.se.awp.gitplay4.model.Attribute;

public class CsvAnalyzer {

	private Attribute col[];
	private int numCol;
	private int numRow;

	public CsvAnalyzer(String path, Boolean hasHeader) throws IOException, CsvException {
		String line = "";

		Reader reader = new FileReader(path);
		CSVReader csvReader = new CSVReader(reader);
		List<String[]> list = new ArrayList<>();
		list = csvReader.readAll();
		reader.close();
		csvReader.close();
		
		if(list.get(0) != null) {
			numCol = list.get(0).length;
		}
	
		col = new Attribute[numCol];
		for(int i = 0; i < numCol; i++) {
			col[i] = new Attribute();
		}
		
		if (hasHeader == true) {
			String[] headers = list.get(0);
			for (int i = 0; i < numCol; i++) {
				col[i].setName(headers[i]);
			}
		}
		else {
			String[] firstRow = list.get(0);
			for (int i = 0; i < numCol; i++) {
				col[i].setName("column" + (i + 1));
				col[i].addData(firstRow[i]);
			}
		}
		
		list.remove(0);
		
		for(String[] row : list) {
			for (int i = 0; i < numCol; i++) {
				col[i].addData(row[i]);
			}
		}
	}

	public int getNumOfColumn() {
		return numCol;
	}

	public int getNumOfRow() {
		return numRow;
	}

	public String getColumnName(int index) {
		return col[index].getName();
	}

	public String[] getColumnNames() {
		String[] columnNames = new String[numCol];
		for (int i = 0; i < numCol; i++) {
			String beforeEncoding = col[i].getName();
//			ByteBuffer buffer = StandardCharsets.UTF_8.encode(beforeEncoding);
//			columnNames[i] = StandardCharsets.UTF_8.decode(buffer).toString();

//			byte[] bytes = beforeEncoding.getBytes();
//			columnNames[i] = new String(bytes, StandardCharsets.UTF_8);
//			assertNotEquals(columnNames[i],beforeEncoding);
			System.out.println(columnNames[i]);
			columnNames[i] = col[i].getName();

		}

		return columnNames;
	}
	
	public boolean isNumericColumn(int column) {
		for (int i = 0; i < this.col[column].getSize(); i++) {
			String data = this.col[column].getData(i);
			if (data == null)
				continue;
			if (!isNumeric(data)) {
				System.out.println(data);
				return false;
			}
		}
		return true;
	}

	public double getMinOf(int column) {
		double min = Double.MAX_VALUE;

		for (int i = 0; i < this.col[column].getSize(); i++) {
			String data = this.col[column].getData(i);
			if (data == null)
				continue;
			if (isNumeric(data)) {
				double num = Double.parseDouble(data);
				if (min > num)
					min = num;
			} else
				return Double.NaN;
		}

		return min;
	}

	public double getMaxOf(int column) {
		double max = Double.MIN_VALUE;

		for (int i = 0; i < this.col[column].getSize(); i++) {
			String data = this.col[column].getData(i);
			if (data == null)
				continue;
			if (isNumeric(data)) {
				double num = Double.parseDouble(data);
				if (max < num)
					max = num;
			} else
				return Double.NaN;
		}

		return max;
	}
	
	private int getNumberOf(String columnName) {
		int result = 0;
		for(Attribute column : col) {
			if(column.getName().equals(columnName)) {
				return result;
			}
			result++;
		}
		return -1;
	}

	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// for test
	public void print() {
		for(Attribute attr : col) {
			for(int i = 0; i < attr.getSize(); i++) {
				System.out.println(attr.getData(i));
			}
		}
	}
	
	private void assertNotEquals(String beforeEncoding, String string) {
		// TODO Auto-generated method stub

	}
}
