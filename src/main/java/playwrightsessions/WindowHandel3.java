package playwrightsessions;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class WindowHandel3 {

	public static void main(String[] args) throws InterruptedException {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		
		Thread.sleep(2000);
		
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
    	
		Thread.sleep(3000);
		page.navigate("https://team.hownowindia.com/dashboard/insights/hnp_partners/10/hnp_partner_courses");
		
		
		List<String> contentNames = new ArrayList<>();
        contentNames.add("Administrative Office Procedures");
        contentNames.add("Administrative Support");
        contentNames.add("Adult Learning Mental Skills");
        contentNames.add("Adult Learning Physical Skills");
        contentNames.add("Advanced Google SEO");
        contentNames.add("Advanced Marketing Using Recommendation Algorithms");
        contentNames.add("Affiliate Marketing Training - Secrets Of The Pros");
        contentNames.add("Anger Management");
        contentNames.add("Appreciative Inquiry");
        contentNames.add("Archiving and Records Management");
        contentNames.add("Assertiveness and Self Confidence");
        contentNames.add("Attention Management");
        contentNames.add("Basic Bookkeeping");
        contentNames.add("Being A Likeable Boss");
        contentNames.add("Blog Marketing For Business");
        contentNames.add("Blogging For Profit");
        contentNames.add("Body Language Basics");
        contentNames.add("Body Language in the Workplace");
        contentNames.add("Boost Productivity and Creativity in Meetings");
        contentNames.add("Boost Your Emotional Intelligence");
        contentNames.add("Brainstorming");
        contentNames.add("Budgets And Financial Reports");
        contentNames.add("Business Acumen");
        contentNames.add("Business Ethics");
        contentNames.add("Business Etiquette");
        contentNames.add("Business Etiquette and Professionalism");
        contentNames.add("Business Productivity Training");
        contentNames.add("Business Succession Planning");
        contentNames.add("Business Writing");
        contentNames.add("Call Center Training Essentials");
        contentNames.add("Change Management");
        contentNames.add("Civility In The Workplace");
        contentNames.add("Coaching Sales People");
        contentNames.add("Coaching and Mentoring");
        contentNames.add("Collaborative Business Writing");
        contentNames.add("Communication Essentials");
        contentNames.add("Communication Essentials For Professionals");
        contentNames.add("Communication Strategies");
        contentNames.add("Complete Guide to Writing Business Plans");
        contentNames.add("Complete Guide to Writing Marketing Plans");
        contentNames.add("Complete Guide to Writing a Marketing Plan");
        contentNames.add("Complete Interviewing Skills");
        contentNames.add("Complete Online Marketing & Advertising Course");
        contentNames.add("Complete Personal Development Personal Transformation");
        contentNames.add("Complete Social Media Marketing");
        contentNames.add("Computer Fundamentals");
        contentNames.add("Conducting Annual Employee Reviews");
        contentNames.add("Conflict Resolution");
        contentNames.add("Contact Center Training");
        contentNames.add("Content Creation Masterclass 2.0");
        contentNames.add("Content Marketing Revolution");
        contentNames.add("Continuous Improvement Culture Change");
        contentNames.add("Contract Management");
        contentNames.add("Copywriting : Write Titles Like A Professional Copywriter");
        contentNames.add("Create A Culture Of Team Respect");
        contentNames.add("Create A Culture Of Team Safety");
        contentNames.add("Create A Customer Care Team Culture");
        contentNames.add("Create Digital Marketing & Sales Funnels");
        contentNames.add("Create Stunning Promo Videos in 30 Minutes or Less");
        contentNames.add("Creating a Great Webinar");
        contentNames.add("Creative Problem Solving");
        contentNames.add("Crisis Management");
        contentNames.add("Critical Thinking");
        contentNames.add("Customer Service");
        contentNames.add("Customer Service Skills");
        contentNames.add("Customer Support");
        contentNames.add("Cyber Security");
        contentNames.add("Dealing With Difficult People In Life & Work - Powerful!");
        contentNames.add("Decision Making Mastery");
        contentNames.add("Delivering Constructive Criticism");
        contentNames.add("Developing Business Models");
        contentNames.add("Developing Corporate Behavior");
        contentNames.add("Developing Creativity");
        contentNames.add("Developing New Managers");
        contentNames.add("Developing a Lunch and Learn");
        contentNames.add("Digital Citizenship");
        contentNames.add("Digital Forensics for Cyber Professionals");
        contentNames.add("Digital Security Awareness Training for Business and Home Users");
        contentNames.add("Disabling Distraction");
        contentNames.add("E-Commerce Marketing Strategy For Beginners");
        contentNames.add("Ecommerce Income Mastery");
        contentNames.add("Effective Bookkeeping and Payroll");
        contentNames.add("Effective Human Resource Administration");
        contentNames.add("Email List Building Formula");
        contentNames.add("Email Marketing Made Simple With Wix 2.0");
        contentNames.add("Email Marketing Made Simple with Wix");
        contentNames.add("Email Marketing Revolution");
        contentNames.add("Emotional Intelligence");
        contentNames.add("Employee Motivation");
        contentNames.add("Employee Onboarding");
        contentNames.add("Employee Recognition");
        contentNames.add("Employee Recruitment");
        contentNames.add("Employee Termination Processes");
        contentNames.add("Entrepreneur & Small Business Mindsets for Success");
        contentNames.add("Entrepreneur Accelerator Program");
        contentNames.add("Entrepreneur Masterclass");
        contentNames.add("Entrepreneurship");
        contentNames.add("Entrepreneurship Accelerator Program [From 0 To Successful]");
        contentNames.add("Entrepreneurship Qualities for First-Time Entrepreneurs");
        contentNames.add("Entrepreneurship and Small Business Mindsets for Success 2.0");
        contentNames.add("Event Planning");
        contentNames.add("Excel Productivity");
        contentNames.add("Executive Assistants");
        contentNames.add("Facebook Account-Kit Course");
        contentNames.add("Facilitation Skills");
        contentNames.add("Fast Learner BluePrint");
        contentNames.add("Fundamental Presentation Skills");
        contentNames.add("Fundamental Public Speaking Skills");
        contentNames.add("G Suite Google Docs Introduction");
        contentNames.add("GDPR Office 365 Readiness Course");
        contentNames.add("Get Organized");
        contentNames.add("Goal Setting Mastery - Accomplish More Than You Ever Thought");
        contentNames.add("Goal Setting Skills");
        contentNames.add("Goal Setting and Getting Things Done");
        contentNames.add("Goal Setting for Life, Career and Business");
        contentNames.add("Google Ads Mastery");
        contentNames.add("Google Image SEO");
        contentNames.add("Google Ranking Secrets");
        contentNames.add("Graphic Design Fundamentals");
        contentNames.add("Growth Mindset Training - Become Your Greatest Self!");
        contentNames.add("Handling Difficult Customers");
        contentNames.add("Health and Wellness at Work");
        contentNames.add("Hidden Secrets Of Selling - Part 1");
        contentNames.add("Hidden Secrets Of Selling - Part 2");
        contentNames.add("Hidden Secrets Of Selling - Part 3");
        contentNames.add("Hidden Secrets Of Selling - Part 4");
        contentNames.add("High Performance Teams Inside the Company");
        contentNames.add("High Performance Teams Remote Workforce");
        contentNames.add("High Ticket Sales Secrets");
        contentNames.add("Hiring Strategies");
        contentNames.add("How To Get More YouTube Views");
        contentNames.add("How to Build Your Own Stunning Website Using Wix 2.0");
        contentNames.add("How to Build Your Own Website Using Wix [Beginner Friendly]");
        contentNames.add("How to Grow Your Instagram Page for Beginners");
        contentNames.add("How to Grow on Instagram by Leveraging IGTV & Video Content");
        contentNames.add("How to Make a Professional Thumbnail in 4 Quick Steps");
        contentNames.add("How to Upload a YouTube Video Properly for More Views");
        contentNames.add("How to Use Facebook Advertising to Grow Your Business");
        contentNames.add("Human Resource Management");
        contentNames.add("Improving Mindfulness");
        contentNames.add("Improving Self Awareness");
        contentNames.add("In Person Sales");
        contentNames.add("Increasing Your Happiness");
        contentNames.add("Instagram Marketing Success");
        contentNames.add("Internet Marketing Fundamentals");
        contentNames.add("Interpersonal Skills");
        contentNames.add("Introduction To Data Analytics Using Microsoft Power BI");
        contentNames.add("Introduction to Business Accounting");
        contentNames.add("Introduction to Human Resource Concepts");
        contentNames.add("Introduction to Legal Concepts");
        contentNames.add("Introduction to Marketing");
        contentNames.add("Introduction to Social Media for Business");
        contentNames.add("Job Search Skills");
        contentNames.add("Key Applications");
        contentNames.add("Knowledge Management");
        contentNames.add("Leadership And Influence");
        contentNames.add("Leadership Skills vs. Management");
        contentNames.add("Leadership and Management Training");
        contentNames.add("Lean Business Process Management Change Skills");
        contentNames.add("Lean Process And Six Sigma");
        contentNames.add("Legal Office Administration");
        contentNames.add("Life Coaching Essentials");
        contentNames.add("Link Building for SEO");
        contentNames.add("LinkedIn Marketing");
        contentNames.add("Local SEO - Get More Customers From Google Search");
        contentNames.add("Microsoft Power BI Skills");
     
        for (String contentName : contentNames) {
            processRecord(page, contentName);
        }
        page.close();
        browser.close();
        playwright.close();
	}
        public static void processRecord(Page page, String contentName) throws InterruptedException {
        	
    		page.waitForSelector("//i[@class='fal fa-search fill-search-icon search-icon default-icon-img']");
    		
    		page.click("//i[@class='fal fa-search fill-search-icon search-icon default-icon-img']");
    		
    		Thread.sleep(1000);
    		page.waitForSelector("//input[@placeholder='Search' and @name='search_key' and @class='search-dt-hn show']");
    		
    		page.fill("//input[@placeholder='Search' and @name='search_key' and @class='search-dt-hn show']", contentName);
        	
            page.keyboard().press("Enter");

    		Thread.sleep(3000);
    		page.waitForSelector("(//p[text()='" + contentName + "'])[2]");
    		
    		Thread.sleep(3000);
            page.click("(//p[text()='" + contentName + "'])[2]");
    		
            Thread.sleep(1000);
    		Page scormPlayerPage = page.waitForPopup(() -> {
                page.click("a#hnp-course-preview");   // This click action should open the SCORM player tab
            });

            scormPlayerPage.waitForLoadState();
            Thread.sleep(3000);
            
            // Get the window title
            String windowTitle = scormPlayerPage.title();

            if (windowTitle.contains(contentName)) {
                System.out.println("Window title--> " + windowTitle + ".  Proper Redirection--> " + contentName);
            } else {
                System.out.println("Window title--> " + windowTitle + ".  Wrong Redirection--> " + contentName);
            }
            
            page.navigate("https://team.hownowindia.com/dashboard/insights/hnp_partners/10/hnp_partner_courses");
        }
	}


