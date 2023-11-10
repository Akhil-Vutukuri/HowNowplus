package playwrightsessions;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class WindowHandel4 {
	static Page page;
	static String expInstanceName;
	static String prodInstanceName;
	static String prodSubDomainName;
	static String expSubDomainName;
	static String HNPpartner;
	
	public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Thread.sleep(2000);
    	page = browser.newPage();
    	page.setViewportSize(1920, 1080);
		
   // 	explogin("hownowindia.com","team");
    	page.navigate("https://hownowindia.com/users/sign_in");
		page.getByPlaceholder("Your-Workspace-URL").click();
		page.getByPlaceholder("Your-Workspace-URL").fill("team");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get Started")).click();
		page.getByPlaceholder("Email Address").click();
		page.getByPlaceholder("Email Address").fill("akhil.vutukuri@gethownow.com");
		page.getByPlaceholder("Password").click();
		page.getByPlaceholder("Password").fill("Hownow123");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
    	naviagteToHownowPlus("Alpha Development");
    	
		Page popup = page.waitForPopup(() -> {}); //Switching the tab to the new Page.
		
	    String Url = popup.url();
	
	    List<String> contentNames = new ArrayList<>();
	    contentNames.add("MONEY MARKETS Masterclass: Introduction to Money Markets");
	    contentNames.add("MONEY MARKETS Masterclass: T Bills Introduction");
	    contentNames.add("AN INTRODUCTION TO FINANCIAL MARKETS MASTERCLASS:  Role And Purpose Of Markets ​");
	    contentNames.add("AN INTRODUCTION TO FINANCIAL MARKETS MASTERCLASS:  How Banks Make Money In Financial Markets ​");
	    contentNames.add("AN INTRODUCTION TO FINANCIAL MARKETS MASTERCLASS:  Navigating The Industry: Participants Needs And Horizons ​");
	    contentNames.add("AN INTRODUCTION TO FINANCIAL MARKETS MASTERCLASS:  Navigating The Industry: Institutions And Corporates ​");
	    contentNames.add("AN INTRODUCTION TO FINANCIAL MARKETS MASTERCLASS:  Corporates In DCM & ECM​");
	    contentNames.add("AN INTRODUCTION TO FINANCIAL MARKETS MASTERCLASS:  Trading Platforms And Order Execution​");
	    contentNames.add("AN INTRODUCTION TO FINANCIAL MARKETS MASTERCLASS:  Regulation In Financial Markets Banks & Basel III​");
	   
	    
        for (String contentName : contentNames) {
            processRecord(popup, contentName, Url);
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
        public static void processRecord(Page popup, String contentName, String Url) throws InterruptedException {
        	        	
        	popup.waitForSelector("//div[@class='srch-actnbtn-wrapper']//i[@class='fal fa-search fill-search-icon search-icon default-icon-img']").click();
        	    		
    		Thread.sleep(1000);
    		popup.waitForSelector("//input[@placeholder='Search' and @name='search_key' and @class='search-dt-hn show']").fill(contentName);
    		
            popup.keyboard().press("Enter");
            
    		Thread.sleep(2000);
    		if(popup.isVisible("//body[1]/section[1]/main[1]/div[2]/section[4]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[2]")) {
    			popup.waitForSelector("//body[1]/section[1]/main[1]/div[2]/section[4]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[2]").click();
    		
            Thread.sleep(1000);
    		Page scormPlayerPage = popup.waitForPopup(() -> {
    			popup.waitForSelector("a#hnp-course-preview").click();   // This click action should open the SCORM player tab
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

            // Define the threshold for a successful match
            int matchThreshold = 2; // Increase the count to increase the accuracy.

            // Count the number of matching words
            int matchingWordCount = countMatchingWords(words1, words2);

            // Check if the matching word count meets the threshold
            boolean result = matchingWordCount >= matchThreshold;
           // System.out.println("Result: " + result);
            
            // ANSI escape code for bold text (Not Mandatory)
    		String boldText = "\u001B[1m"; //-> for a bold console output
    		
    		// ANSI escape code to reset text formatting (not Mandatory)
    		String resetText = "\u001B[0m"; //-> for a bold console output
            
            if (result) {
                System.out.println("Content Name--> " + contentName + "| Window title--> " + windowTitle +"|| RESULT----> "+ boldText + result + resetText + ", Matching words: "+ boldText+matchingWordCount+resetText);
            } else {
                System.out.println("Content Name--> " + contentName + "| Window title--> " + windowTitle +"|| RESULT----> "+ boldText + result + resetText + ", Matching words: "+ boldText+matchingWordCount+resetText);
            }
            
            popup .navigate(Url);
            }
    		else {
    			System.out.println("Course Not found: " + contentName);
    			popup .navigate(Url);
    		}
        }
        
        //Method to Compare the words and return the Count
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
        
        public static void explogin(String expInstanceName, String expSubDomainName) {
            page.waitForLoadState(LoadState.LOAD);
        	page.navigate("https://"+expInstanceName+"/users/sign_in");
    		page.getByPlaceholder("Your-Workspace-URL").click();
    		page.getByPlaceholder("Your-Workspace-URL").fill(expSubDomainName);
    		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get Started")).click();
    		page.getByPlaceholder("Email Address").click();
    		page.getByPlaceholder("Email Address").fill("akhil.vutukuri@gethownow.com");
    		page.getByPlaceholder("Password").click();
    		page.getByPlaceholder("Password").fill("Hownow123");
    		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        }
        
        public static void prodlogin(String prodInstanceName, String prodSubDomainName) {
            page.waitForLoadState(LoadState.LOAD);
        	page.navigate("https://"+expInstanceName+"/users/sign_in");
    		page.getByPlaceholder("Your-Workspace-URL").click();
    		page.getByPlaceholder("Your-Workspace-URL").fill(expSubDomainName);
    		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get Started")).click();
    		page.getByPlaceholder("Email Address").click();
    		page.getByPlaceholder("Email Address").fill("akhil.vutukuri@gethownow.com");
    		page.getByPlaceholder("Password").click();
    		page.getByPlaceholder("Password").fill("Hownow123");
    		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        }
        
        public static void naviagteToHownowPlus(String HNPpartner){
        	
        	page.waitForSelector("//i[@class='fak fa-dashboard-icon']").click(); //clicking on Dashboard
   		 
    		page.waitForSelector("//i[@class='fak fa-lg fa-apps-icon']").click(); //Clicking on the Apps icon
    		
    		page.waitForSelector("//li[normalize-space()='HowNow+']").click(); //Clicking on the hownow+
    		
    		page.waitForSelector("//p[text()='"+HNPpartner+"']/ancestor::a/following-sibling::div/a[@class='app-action-btn']").click(); //Clicking on the configure button of a HowNow+ partner.	
        }
}


