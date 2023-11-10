package playwrightsessions;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.FilePayload;

public class FileUpload {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create(); 
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		
		Page page = browser.newPage();
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
	      
	      
	      //below is the script to upload multiple files together.
	      page.setInputFiles("//input[@class='dropify form-control']", new Path[] {Paths.get("Add first file location"), Paths.get("Add second file location")});
	      ////below is the code to detach the Multiple uploaded file successfully!
	      page.setInputFiles("//input[@class='dropify form-control']", new Path[0]);
	      
	      //Below is the code to create the file in the runtime and Upload it
	      page.setInputFiles("//input[@class='dropify form-control']", new FilePayload("naveen,text", 
	    		  "text/plain", "this is Akhil here".
	    		  getBytes(StandardCharsets.UTF_8)));
	      
	}

}
