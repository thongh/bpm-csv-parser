package csv;

import java.io.IOException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVUtility {

	public static void main(String[] args) throws IOException {
		String lines = calculateCommission("");	
	}
	
	public String getMessage(String name) {
		String msg = "Hello " + name;
		return msg;
	}
	
	@SuppressWarnings({ "null", "resource" })
	public static String calculateCommission(String filePath) {
		String result = "";
		String SAMPLE_CSV_FILE_PATH = "";
		if (filePath == "") {
			SAMPLE_CSV_FILE_PATH = "C:/temp/users.csv";			
		} else {
			SAMPLE_CSV_FILE_PATH = filePath;
		}
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH)); 
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			for (CSVRecord csvRecord : csvParser) {
				// Accessing Values by Column Index
                String id = csvRecord.get(0);
                String name = csvRecord.get(1);
                String amount = csvRecord.get(2);
                String cardNo = csvRecord.get(3);

                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Id : " + id);
                System.out.println("Name : " + name);
                System.out.println("Amount : " + amount);
                System.out.println("CardNo : " + cardNo);
                System.out.println("---------------\n\n");
                
                result = result + id;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
