package com.composefunction.stepdef;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef extends Login{

	JavascriptExecutor js;
	String url = "https://gmail.com/";
	WebElement attachFile;

	@Given("^User lands on Gmail Homepage$")
	public void user_lands_on_Gmail_Homepage() {
		System.out.println("LogIn");
		Login.setUp();
	}

	@When("^User clicks on Maximize button, Minimize button and Close button on Compose Mail window$")
	public void user_clicks_on_Maximize_button_Minimize_button_and_Close_button_on_Compose_Mail_window() {
		WebElement composeMail = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Compose')]")));

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		composeMail.click();

		//pop-out mail
		driver.findElement(By.xpath("//img[@data-tooltip='Full screen (Shift for pop-out)']")).click();
		//mimimize mail
		driver.findElement(By.xpath("//img[@data-tooltip='Minimise']")).click();
		//Save and close
		driver.findElement(By.xpath("//img[@data-tooltip='Save & close']")).click();
	}

	@Then("^Compose Mail Window should be closed$")
	public void compose_Mail_Window_should_be_closed() {
		driver.quit();
		System.out.println("Compose mail window closed");
	}

	@Given("^User lands on Gmail Homepage and clicks on Compose button$")
	public void user_lands_on_Gmail_Homepage_and_clicks_on_Compose_button() {
		Login.setUp();
		WebElement composeMail = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Compose')]")));

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		composeMail.click();
	}

	@When("^User enters multiple/single gmail/non-gmail id in recipents text and Clicks on Send Button$")
	public void user_enters_multiple_single_gmail_non_gmail_id_in_recipents_text_and_Clicks_on_Send_Button() {
		WebElement toRecipent = driver.findElement(By.xpath("//textarea[@name='to']"));
		toRecipent.sendKeys("sk3847721@gmail.com; testmail232020@gmail.com");
		driver.findElement(By.xpath("//span[contains(text(),'Cc')]")).click();
		driver.findElement(By.xpath("//textarea[@name='cc']")).sendKeys("test123@gmail.com; test123@yahoo.co.in");
		driver.findElement(By.xpath("//span[contains(text(),'Bcc')]")).click();
		driver.findElement(By.xpath("//textarea[@name='bcc']")).sendKeys("testmail232020@gmail.com");

		//send mail without subject and body
		driver.findElement(By.xpath("//div[@data-tooltip='Send ‪(Ctrl-Enter)‬']")).click();

	}

	@Then("^User is displayed with popup message and he accepts the message and mail should be sent$")
	public void user_is_displayed_with_popup_message_and_he_accepts_the_message_and_mail_should_be_sent() throws InterruptedException {
		Thread.sleep(400);
		Alert alert = driver.switchTo().alert();
		String expectedAlertMessagae = "Send this message without a subject or text in the body?";
		String actualAlertMessage = driver.switchTo().alert().getText();

		Assert.assertEquals(expectedAlertMessagae, actualAlertMessage);
		alert.accept();
		driver.quit();
		System.out.println("Mail sent successfully");
	}


	@Given("^User clicks on Compose button and enters multiple/single gmail/non-gmail id in recipents, subject and body$")
	public void user_clicks_on_Compose_button_and_enters_multiple_single_gmail_non_gmail_id_in_recipents_subject_and_body() {
		Login.setUp();
		WebElement composeMail = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Compose')]")));

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		composeMail.click();

		WebElement toRecipent = driver.findElement(By.xpath("//textarea[@name='to']"));
		toRecipent.sendKeys("sk3847721@gmail.com; testmail232020@gmail.com");
		driver.findElement(By.xpath("//span[contains(text(),'Cc')]")).click();
		driver.findElement(By.xpath("//textarea[@name='cc']")).sendKeys("test123@gmail.com; test123@yahoo.co.in");
		driver.findElement(By.xpath("//span[contains(text(),'Bcc')]")).click();
		driver.findElement(By.xpath("//textarea[@name='bcc']")).sendKeys("testmail232020@gmail.com");

		driver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys("Test E-mail with attachment");

		driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys("Hi, " + "\n \n"
				+ "This is an auto-generated test mail" + "\n");

	}

	@When("^User clicks on Attach File icon and attaches file$")
	public void user_clicks_on_Attach_File_icon_and_attaches_file() {
		//file attached not reached 25mb
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\ComposeFunction_Gmail\\compose-function-gmail\\GmailComposeFunctionality\\filesAttached\\email.txt");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\ComposeFunction_Gmail\\compose-function-gmail\\GmailComposeFunctionality\\filesAttached\\Fruit.java");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\ComposeFunction_Gmail\\compose-function-gmail\\GmailComposeFunctionality\\filesAttached\\flower.jpg");

	}

	@Then("^when size increases (\\d+)mb then user gets a pop up to attach file as drive link$")
	public void when_size_increases_mb_then_user_gets_a_pop_up_to_attach_file_as_drive_link(int arg1) {
		//file attachment reached max limit i.e. 25mb
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\ComposeFunction_Gmail\\compose-function-gmail\\GmailComposeFunctionality\\filesAttached\\Englishguide.pdf");
	}

	@Then("^User clicks on Cancel button and clicks on Send button$")
	public void user_clicks_on_Cancel_button_and_clicks_on_Send_button_and_mail_should_be_sent() throws InterruptedException {
		//cancel to upload to attach ad drive link
		driver.findElement(By.xpath("//button[@name='cancel']")).click();

		//Wait wait1 = new FluentWait(driver).withTimeout(40,TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		//wait for file to be uploaded and send the mail
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@data-tooltip='Send ‪(Ctrl-Enter)‬']")).click();
		Thread.sleep(5000);
	}

	@Then("^Verify that mail should be present in Sent Items sections$")
	public void verify_that_mail_should_be_present_in_Sent_Items_sections() throws InterruptedException {
		driver.findElement(By.xpath("//a[@aria-label='Sent']")).click();
		Thread.sleep(1000);
		driver.quit();
	}


	@Given("^User clicks on Compose button and enter recipents, subject and body$")
	public void user_clicks_on_Compose_button_and_enter_recipents_subject_and_body() {
		Login.setUp();
		WebElement composeMail = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Compose')]")));

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		composeMail.click();

		WebElement toRecipent = driver.findElement(By.xpath("//textarea[@name='to']"));
		toRecipent.sendKeys("sk3847721@gmail.com; testmail232020@gmail.com");
		driver.findElement(By.xpath("//span[contains(text(),'Cc')]")).click();
		driver.findElement(By.xpath("//textarea[@name='cc']")).sendKeys("test123@gmail.com; test123@yahoo.co.in");
		driver.findElement(By.xpath("//span[contains(text(),'Bcc')]")).click();
		driver.findElement(By.xpath("//textarea[@name='bcc']")).sendKeys("testmail232020@gmail.com");

		driver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys("Test E-mail with unsupported file type");

		driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys("Hi, " + "\n \n"
				+ "This is an auto-generated test mail" + "\n");
	}

	@When("^User clicks on Attach File icon and attaches unsupported file$")
	public void user_clicks_on_Attach_File_icon_and_attaches_unsupported_file() throws InterruptedException {
		//attach supported file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\ComposeFunction_Gmail\\compose-function-gmail\\GmailComposeFunctionality\\filesAttached\\chromedriver_win.zip ");
	}

	@Then("^User gets error message and discards the mail$")
	public void user_gets_error_message_and_discards_the_mail() throws InterruptedException {
		Thread.sleep(14000);
		driver.findElement(By.xpath("//div[@data-tooltip='Discard draft ‪(Ctrl-Shift-D)‬']")).click();
		driver.quit();
	}


	@Given("^User clicks on Compose and enter recipents$")
	public void user_clicks_on_Compose_and_enter_recipents() {
		Login.setUp();
		WebElement composeMail = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Compose')]")));

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		composeMail.click();

		WebElement toRecipent = driver.findElement(By.xpath("//textarea[@name='to']"));
		toRecipent.sendKeys("testmail232020@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys("Test E-mail with link attached");

		driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys("Hi, " + "\n \n"
				+ "This is an auto-generated test mail" + "\n");
	}

	@When("^User clicks on Insert Link and attaches external link to mail$")
	public void user_clicks_on_Insert_Link_and_attaches_external_link_to_mail() {
		//Click on Insert Link
		driver.findElement(By.xpath("//div[@data-tooltip='Insert link ‪(Ctrl-K)‬']")).click();
		driver.findElement(By.xpath("//input[@id='linkdialog-text']")).sendKeys("Click here to visit google..");
		driver.findElement(By.xpath("//input[@id='linkdialog-onweb-tab-input']")).sendKeys("https://www.google.com" +"\n ");

	}

	@Then("^User sends the mail and mail is sent$")
	public void user_sends_the_mail_and_mail_is_sent() throws InterruptedException {
		Thread.sleep(200);
		driver.findElement(By.xpath("//div[@data-tooltip='Send ‪(Ctrl-Enter)‬']")).click();
		driver.quit();
	}


	@Given("^User clicks on Compose button then new mail window is launched$")
	public void user_clicks_on_Compose_button_then_new_mail_window_is_launched() {
		Login.setUp();
		WebElement composeMail = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Compose')]")));

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		composeMail.click();

		WebElement toRecipent = driver.findElement(By.xpath("//textarea[@name='to']"));
		toRecipent.sendKeys("testmail232020@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys("Test E-mail with emoticons");

	}

	@When("^User clicks Insert Emoji icon and attaches emoticons$")
	public void user_clicks_Insert_Emoji_icon_and_attaches_emoticons() throws InterruptedException {
		driver.findElement(By.xpath("//div[@data-tooltip='Insert emoji ‪(Ctrl-Shift-2)‬']")).click();
		driver.findElement(By.xpath("//button[@title='Show face emoticons']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@aria-label='grinning face with big eyes']")).click();
		driver.findElement(By.xpath("//button[@title='Close the emoticon pane']")).click();
	}

	@Then("^User closes the mail window and mail is saved to Drafts and count is incremented$")
	public void user_closes_the_mail_window_and_mail_is_saved_to_Drafts_and_count_is_incremented() {
		driver.findElement(By.xpath("//img[@data-tooltip='Save & close']")).click();
		driver.quit();
	}


	@Given("^User clicks on Compose and a new mail window is opened$")
	public void user_clicks_on_Compose_and_a_new_mail_window_is_opened() {
		Login.setUp();
		WebElement composeMail = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Compose')]")));

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		composeMail.click();

		driver.findElement(By.xpath("//input[@placeholder='Subject']")).sendKeys("Test E-mail without recipents");

		driver.findElement(By.xpath("//div[@aria-label='Message Body']")).sendKeys("Hi, " + "\n \n"
				+ "This is an auto-generated test mail" + "\n");

	}

	@When("^User clicks on Send Button after entering subject and body$")
	public void user_clicks_on_Send_Button_after_entering_subject_and_body() {
		driver.findElement(By.xpath("//div[@data-tooltip='Send ‪(Ctrl-Enter)‬']")).click();
	}

	@Then("^User gets pop up message to specify recipent$")
	public void user_gets_pop_up_message_to_specify_recipent() throws InterruptedException {
		Thread.sleep(800);
		driver.findElement(By.xpath("//button[@name='ok']")).click();
		driver.quit();
	}

}
