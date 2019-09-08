package com.trello.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamCreationTests extends  TestBase{
  @Test
  public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
    int before = getTeamsCount();
    clickOnPlusButtonOnHeader();
    selectCreateTeamFromDropDown();
    String teamName = "qa21";
    fillTeamCreationForm("qa21", "descr qa 21");
    clickContinueButton();
    String createdTeamName = getTeamNameFromTeamPage();

    returnToHomePage();
    int after = getAfter();
   // Assert.assertEquals(after, before+1);
    Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());


    //Assert.assertTrue(isUserLoggedIn());
  }
  @Test
  public void testTeamCreationFromLeftMenu(){
    click(By.cssSelector(".icon-add.icon-sm"));
  }

}
