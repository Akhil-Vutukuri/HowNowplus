package playwrightsessions;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FileDownload {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create(); 
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		Page page = browser.newPage();
		
		page. navigate("URL of the page where you want to download");
		
		//below code will wait until the download is done 
		Download download = page.waitForDownload(() ->{		
		page.click("a:text('file_name')");
		});
		
		System.out.println(download.failure()); //This will give you the reason of the download failure  (if the download is failed for some reason).
		
		download.cancel(); //this will cancel the download.
		System.out.println(download. url()); //this is to check the URL of the downloaded file.
		System.out.println(download.page().title()); //this will give you the page location and page title.
		
		String path = download.path().toString(); //We are storing the file path in a string and printing it.
		System.out.println(path);
		
		download.saveAs(Paths.get("Destination_Location_URL")); //here we are saving the downloaded file in the desired location.
		System.out.println(download.suggestedFilename()); //here we are Verifying the file name.
	}
}
