package com.trello.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class TestBase {
  WebDriver driver;
  @BeforeClass
  public void setUp(){
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    openSite("https://trello.com");
    login("elena.telran@yahoo.com", "12345.com");
  }

  public void login(String email, String password) {
    click(By.cssSelector("[href='/login']"));
    type(By.cssSelector("[type=email]"), email);
    type(By.cssSelector("[type=password]"),password);
    click(By.id("login"));
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public void type(By locator, String text){
    driver.findElement(locator).click();
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(text);
  }

  public void openSite(String url) {
    driver.get(url);
  }

  @AfterClass
  public void tearDown(){
driver.quit();
  }

  public boolean isUserLoggedIn() {
    return isElementPresent(By.cssSelector("[data-test-id='header-member-menu-button']"));
  }

  public boolean isElementPresent(By locator) {
    return driver.findElements(locator).size()>0;
  }

  public void clickContinueButton() {
    click(By.cssSelector("[type=submit]"));
  }

  public void fillTeamCreationForm(String teamName, String description) {
    type(By.cssSelector("[data-test-id='header-create-team-name-input']"), teamName);
    type(By.cssSelector("textarea"), description);
  }

  public void selectCreateTeamFromDropDown() {
    click(By.cssSelector("[data-test-id='header-create-team-button']"));
  }

  public void clickOnPlusButtonOnHeader() {
    click(By.cssSelector("[data-test-id='header-create-menu-button']"));
      }

  public void fillBoardCreationForm(String boardName, String s) {
    type(By.cssSelector("[data-test-id='header-create-board-title-input']"), boardName);

    if(isElementPresent(By.cssSelector(".W6rMLOx8U0MrPx"))){
    click(By.cssSelector(".W6rMLOx8U0MrPx"));
    click(By.xpath("//nav[@class='SdlcRrTVPA8Y3K']//li[1]"));//no team
    }

  }

  public void selectCreateBoardFromDropDown() {
    click(By.cssSelector("[data-test-id='header-create-board-button']"));
  }

  public void confirmBoardCreation() {
    click(By.cssSelector("[data-test-id='header-create-board-submit-button']"));
  }

  public String getTeamNameFromTeamPage() {
    return driver.findElement(By.cssSelector("")).getText();
  }

  public void returnToHomePage() throws InterruptedException {
    Thread.sleep(3000);
    click(By.cssSelector("[href='/']"));
    click(By.cssSelector("[href='/']"));
  }

  public int getAfter() {
    click(By.cssSelector("a[href=/]"));
    return getTeamsCount();
  }

  public int getTeamsCount() {
    return driver.findElements(By.xpath("//div[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
  }

  @Test(enabled=false)
  public void testTeamCuncellCreationFromPlusButtonOnHeader(){
    clickOnPlusButtonOnHeader();
    selectCreateTeamFromDropDown();
    fillTeamCreationForm("qa21", "descr qa 21");
    clickXButton();
  }

  public void clickXButton() {

  }
}
