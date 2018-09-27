package csv;

import java.io.BufferedWriter;
import java.io.IOException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSVUtility {

	public static void main(String[] args) throws IOException {
		String lines = calculateCommission("", "", 0.125);	
	}
	
	public String getMessage(String name) {
		String msg = "Hello " + name;
		return msg;
	}
	
	@SuppressWarnings({ "null", "resource" })
	public static String calculateCommission(String filePath, String destinationPath, double rate) {
		String result = "";
		String SAMPLE_CSV_FILE_PATH = "";
		if (filePath == "") {
			SAMPLE_CSV_FILE_PATH = "C:/temp/MOBILEAPP.csv";			
		} else {
			SAMPLE_CSV_FILE_PATH = filePath;
		}
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH)); 
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
					.withFirstRecordAsHeader()
					.withIgnoreHeaderCase()
					.withTrim());
			generateCSV(destinationPath, csvParser, rate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@SuppressWarnings("resource")
	private static void generateCSV(String destinationPath, CSVParser csvParser, double rate) {
		String SAMPLE_CSV_FILE_PATH = "";
		if (destinationPath == "") {
			SAMPLE_CSV_FILE_PATH = "C:/temp/MOBILEAPP_cal.csv";			
		} else {
			SAMPLE_CSV_FILE_PATH = destinationPath;
		}
		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH));
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("USER PHONE NUMBER", "USER ID NUMBER", "USER FIRST NAME", "USER LAST NAME" 
                    		,"USER JOB", "REGISTRATIONS COUNT", "COMMISSION"));
			
			for (CSVRecord csvRecord : csvParser) {
				// Accessing Values by Column Index
                String userPhoneNo = csvRecord.get(0);
                String userIdNo = csvRecord.get(1);
                String userFirstName = csvRecord.get(2);
                String userLastName = csvRecord.get(3);
                String userJob = csvRecord.get(4);
                String userRegisCount = csvRecord.get(5);
                
                double commission = Integer.parseInt(userRegisCount) * rate;
               
                csvPrinter.printRecord(userPhoneNo, userIdNo, userFirstName, 
                		userLastName, userJob, userRegisCount, commission);
                
			}
			csvPrinter.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
