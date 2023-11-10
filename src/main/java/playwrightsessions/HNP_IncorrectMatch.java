package playwrightsessions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class HNP_IncorrectMatch {
	static Page page;
	public static void main(String[] args) throws InterruptedException, IOException {
        long startTime = System.currentTimeMillis();

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    	page = browser.newPage();
    	page.setViewportSize(1920, 1080);
		
    	//Enter hownow.app for production / hownowindia.com for experimental, Similarly got subDomain as well.
    	login("hownow.app","qateam");
    	naviagteToHownowPlus("Alpha Development");
		Page hnTab = page.waitForPopup(() -> {}); //Switching to the new Page/Tab.
	    String Url = hnTab.url();
	   
	    
	    String excelFilePath = "C:\\Users\\akhil\\OneDrive\\Desktop\\New Microsoft Excel Worksheet (5).xlsx"; 
        FileInputStream excelFile = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheetAt(0); //Getting the 1st sheet.
        
        List<String> contentNames1 = readContentNamesFromExcel(sheet); 
        for (String contentName : contentNames1) {
            processRecord(hnTab, contentName, Url);
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        String formattedTime = formatExecutionTime(executionTime);
        System.out.println("Execution time: " + formattedTime);

        page.close();
        browser.close();
        playwright.close();
	}
	
		//Method to verify the SCORM files.
        public static void processRecord(Page hnTab, String contentName, String Url) throws InterruptedException {
        	        	
        	hnTab.waitForSelector("//div[@class='srch-actnbtn-wrapper']//i[@class='fal fa-search fill-search-icon search-icon default-icon-img']").click();
        	    		
        	hnTab.waitForSelector("//input[@placeholder='Search' and @name='search_key' and @class='search-dt-hn show']").fill(contentName);
    		
        	hnTab.keyboard().press("Enter");
            
    		Thread.sleep(1000);
    		if(hnTab.isVisible("//body[1]/section[1]/main[1]/div[2]/section[4]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[2]")) {
    			hnTab.click("//body[1]/section[1]/main[1]/div[2]/section[4]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[2]");
    		
            Thread.sleep(1000);
    		Page scormPlayerPage = hnTab.waitForPopup(() -> {
    			hnTab.waitForSelector("a#hnp-course-preview").click();   // This click action should open the SCORM player tab
            });

            scormPlayerPage.waitForLoadState();
            Thread.sleep(2000);
            
            // Get the window title of the SCORM page title.
            String windowTitle = scormPlayerPage.title();
            
            // Convert both strings to lower case (always preferred when comparing the String)
            String lowerStr1 = windowTitle.toLowerCase();
            String lowerStr2 = contentName.toLowerCase();

            // Splitting the string into words
            String[] words1 = lowerStr1.split("\\W+");
            String[] words2 = lowerStr2.split("\\W+");

            // Defining the threshold for a successful match
            int matchThreshold = 2; // Increase the count to increase the accuracy.

            // Count the number of matching words
            int matchingWordCount = countMatchingWords(words1, words2);

            // Check if the matching word count meets the threshold
            boolean result = matchingWordCount >= matchThreshold;
           // System.out.println("Result: " + result);
            
            // ANSI escape code for bold text (Not Mandatory)
    		String boldText = "\u001B[1m"; //-> for a bold console output
    		String resetText = "\u001B[0m"; //-> for a bold console output
            
            if (result) {
                System.out.println("Content Name--> " + contentName + "| Window title--> " + windowTitle +"|| RESULT----> "+ boldText + result + resetText + ", Matching words: "+ boldText+matchingWordCount+resetText);
            } else {
                System.out.println("Content Name--> " + contentName + "| Window title--> " + windowTitle +"|| RESULT----> "+ boldText + result + resetText + ", Matching words: "+ boldText+matchingWordCount+resetText);
            } 
            hnTab.navigate(Url);
            }
    		else {
    			System.out.println("Course Not found: " + contentName);
    			hnTab.navigate(Url);
    		}
        }
        
        //Method to Compare the words and return the Count for mismatched  content titles.
        public static int countMatchingWords(String[] words1, String[] words2) {
            int count = 0;
            for (String word : words1) {
                for (String compareWord : words2) {
                    if (compareWord.equals(word)) {
                        count++;
                        break;
                    }
                }
            }
            return count;
        }
        
        //Method to Convert the Milliseconds to a proper time format.
        public static String formatExecutionTime(long executionTime) {
            // Calculate hours, minutes, and seconds
            long seconds = (executionTime / 1000) % 60;
            long minutes = (executionTime / (1000 * 60)) % 60;
            long hours = (executionTime / (1000 * 60 * 60));
            if(hours == 0) {
            	return String.format("%dm %dec", minutes, seconds); // Format the time as "m:sec" when hours is 0
            }
            else {
            	return String.format("%dhr %dm %dsec", hours, minutes, seconds); // Format the time as "hr:m:sec"
            }
        }
        
        //Method to read the data from the excel sheet.
        public static List<String> readContentNamesFromExcel(XSSFSheet sheet) {
            List<String> contentNames = new ArrayList<>();

            for (Row row : sheet) {
                Cell cell = row.getCell(0); // Assuming contentNames are in the first column (index 0)
                if (cell != null) {
                    contentNames.add(cell.getStringCellValue()); // Assuming contentNames are strings
                }
            }
            return contentNames;
        }
        
        //Method to login to the experimental instance.
        public static void login(String instanceName, String subDomainName) {
            page.waitForLoadState(LoadState.LOAD);
        	page.navigate("https://"+instanceName+"/users/sign_in");
    		page.getByPlaceholder("Your-Workspace-URL").click();
    		page.getByPlaceholder("Your-Workspace-URL").fill(subDomainName);
    		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get Started")).click();
    		page.getByPlaceholder("Email Address").click();
    		page.getByPlaceholder("Email Address").fill("akhil.vutukuri@gethownow.com");
    		page.getByPlaceholder("Password").click();
    		page.getByPlaceholder("Password").fill("Hownow123");
    		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        }
        
        //Method to Navigate to the HN+ Partner page
        public static void naviagteToHownowPlus(String hnpPartnerName){
        	page.waitForSelector("//i[@class='fak fa-dashboard-icon']").click(); //clicking on Dashboard
    		page.waitForSelector("//i[@class='fak fa-lg fa-apps-icon']").click(); //Clicking on the Apps icon		
    		page.waitForSelector("//li[normalize-space()='HowNow+']").click(); //Clicking on the hownow+  		
    		page.waitForSelector("//p[text()='"+hnpPartnerName+"']/ancestor::a/following-sibling::div/a[@class='app-action-btn']").click(); //Clicking on the configure button of a HowNow+ partner.	
        }
}


