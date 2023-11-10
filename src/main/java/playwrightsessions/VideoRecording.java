package playwrightsessions;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class VideoRecording {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create(); 
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		
		//we are supposed to create the browser context object to start the video recording and save in a path directory.
		//we need to cose the browser context and then the video will be saved in the given location.
		BrowserContext brbrowser = browser.newContext(new Browser
										.NewContextOptions()
											.setRecordVideoDir(Paths.get("MyVideos/")).setRecordVideoSize(1920, 1080)); //setting the size of the video
		Page page = brbrowser.newPage();
		
		page.navigate("https://hownowindia.com/users/sign_in");
	      page.getByPlaceholder("Your-Workspace-URL").click();
	      page.getByPlaceholder("Your-Workspace-URL").fill("team");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Get Started")).click();
	      page.getByPlaceholder("Email Address").click();
	      page.getByPlaceholder("Email Address").fill("akhil.vutukuri@gethownow.com");
	      page.getByPlaceholder("Password").click();
	      page.getByPlaceholder("Password").fill("Hownow123");
	      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
		
	      page.click("//div[@data-toggle='dropdown']");
	      
	      page.click("//a[text()='My Profile']");
	      
	      page.click("//div[@class='full-width dynmaic-image full-height vertical-align-top member-image-border-radius']");
	      
	      //below is the script to upload one file.
	      page.setInputFiles("//input[@class='dropify form-control']",Paths.get("C:\\Users\\akhil\\OneDrive\\Pictures\\Screenshots\\Screenshot 2023-10-20 201815.png"));
	      page.click("//button[@type='submit']"); 
	      //below is the code to detach the uploaded file successfully!
	      page.setInputFiles("//input[@class='dropify form-control']", new Path[0]);
	      
	      brbrowser.close();
	      page.close();
	      playwright.close();
	}

}
