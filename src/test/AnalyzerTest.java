package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import kr.ac.jbnu.se.awp.gitplay4.core.CsvAnalyzer;

public class AnalyzerTest {

	CsvAnalyzer analyzer;

	@Before
	public void setUp() throws IOException, CsvException {
		analyzer = new CsvAnalyzer("C:\\Users\\selab\\Downloads\\death-rates-from-air-pollution.csv", true);
	}

	@Test
	public void testIsNumeric() throws IOException, CsvException {
		assertFalse(analyzer.isNumericColumn(0));
		assertFalse(analyzer.isNumericColumn(1));
		assertTrue(analyzer.isNumericColumn(2));
		assertTrue(analyzer.isNumericColumn(3));
	}
	
	@Test
	public void testColumnPrint() {
		analyzer.print();
	}
}
