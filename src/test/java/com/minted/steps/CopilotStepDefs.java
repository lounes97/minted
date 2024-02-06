package com.minted.steps;

import com.minted.pages.SalesforcePage;
import com.minted.pages.ZendeskPage;
import com.minted.utility.BrowserUtils;
import com.minted.utility.ConfigurationReader;
import com.minted.utility.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CopilotStepDefs {

    SalesforcePage salesforcePage = new SalesforcePage();
    ZendeskPage zendeskPage = new ZendeskPage();
    int frameWidth1;
    int frameWidth2;

    String url = Driver.getDriver().getTitle();

    @When("user enters the username")
    public void user_enters_the_username() {

        if (url.contains("Salesforce")) {
            salesforcePage.username.sendKeys(ConfigurationReader.getProperty("salesForceUserName"));
        } else {
            zendeskPage.zenDeskUserName.sendKeys(ConfigurationReader.getProperty("zenDeskUserName"));
        }
    }

    @When("user enters the password")
    public void user_enters_the_password() {
        if (url.contains("Salesforce")) {
            salesforcePage.password.sendKeys(ConfigurationReader.getProperty("salesForcePassword"));
        } else {
            zendeskPage.zenDeskPassword.sendKeys(ConfigurationReader.getProperty("zenDeskPassword"));
        }

    }

    @Then("user clicks on login button")
    public void user_clicks_on_login_button() {

        if (url.contains("Salesforce")) {
            salesforcePage.loginButton.click();
        } else {
            zendeskPage.zenDeskLoginBtn.click();
        }


    }


    @And("when the user clicks on the Kodif icon, the coPilot window pops up")
    public void whenTheUserClicksOnTheKodifIconTheCoPilotWindowPopsUp() {
        BrowserUtils.waitForVisibility(salesforcePage.coPilotIcon, 20);
        salesforcePage.coPilotIcon.click();
    }

    @When("the user selects the maximize option the coPilot window should expand")
    public void theUserSelectsTheMaximizeOptionTheCoPilotWindowShouldExpand() {
        BrowserUtils.waitFor(5);
        frameWidth1 = salesforcePage.copilotFrame.getSize().getWidth();

        salesforcePage.maximizeAndMinimizeButton.click();
        frameWidth2 = salesforcePage.copilotFrame.getSize().getWidth();

        Assert.assertTrue(frameWidth2 > frameWidth1);


    }

    @And("the user selects the minimize option the coPilot window should minimize to the taskbar")
    public void theUserSelectsTheMinimizeOptionTheCoPilotWindowShouldMinimizeToTheTaskbar() {

        salesforcePage.maximizeAndMinimizeButton.click();
        frameWidth1 = salesforcePage.copilotFrame.getSize().getWidth();
        Assert.assertTrue(frameWidth2 > frameWidth1);
    }

    List<WebElement> elements;
    int optionIndex;

    @When("user clicks on command button he should see at least one elements")
    public void userClicksOnOptionButtonHeShouldSeeAtLeastOneOptions() {

        BrowserUtils.waitForClickablility(salesforcePage.commandButton, 10);
        salesforcePage.commandButton.click();
        elements = Driver.getDriver().findElements(By.xpath("//div[contains(@class,'kodif-menu-item')]"));
        System.out.println("elements.size() = " + elements.size());
        Assert.assertTrue(elements.size() >= 1);
        boolean containSummarize = false;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().contains("/ask-kb")) {
                containSummarize = true;
                optionIndex = i;
                System.out.println("elements.get(i).getText() = \n\n\n" + elements.get(i).getText() + "\n\n\n***********             ************");
                break;
            }
        }
        Assert.assertTrue(containSummarize);

    }

    @When("user search something on the searching bar")
    public void userClicksOnOneOfTheOptionsSearchingBarShouldDisappear() {
        salesforcePage.searchingBar.sendKeys("I need help" + Keys.ENTER);

        Assert.assertFalse(salesforcePage.searchingBar.isEnabled());


    }
    @When("user clicks on different ticket the default message should appear on the coPilot window")
    public void userClicksOnDifferentTicketTheDefaultMessageShouldAppearOnTheCoPilotWindow() {
        //this will search for a random question on the searching bar from the copilot and then wait and
        //switch to another ticket, this have to disable copilot while been on cases board and then appear again
        //whenever a ticket is selected
        salesforcePage.searchingBar.sendKeys("I need help" + Keys.ENTER);
        BrowserUtils.waitFor(10);
        if (url.contains("Salesforce")) {
            salesforcePage.casesBox.click();
            BrowserUtils.waitForClickablility(salesforcePage.caseNum,10);

            salesforcePage.caseNum.click();

            salesforcePage.ticketShiftDefaultText.isDisplayed();

        } else {
            zendeskPage.zendeskSearchBoxOnMain.click();

            zendeskPage.zendeskSearchBox.sendKeys("2522"+Keys.ENTER);
            BrowserUtils.waitFor(10);

            salesforcePage.ticketShiftDefaultText.isDisplayed();
        }


    }
    @When("user clicks on Suggestion option the insert button will appear under the suggestion text")
    public void userClicksOnSuggestionOptionTheInsertButtonWillAppearUnderTheSuggestionText() {
        BrowserUtils.waitForClickablility(salesforcePage.commandButton, 10);
        salesforcePage.commandButton.click();
        salesforcePage.SuggestionOption.click();

    }

    @And("user clicks on insert button which will copy and paste the text to the post section")
    public void userClicksOnInsertButtonWhichWillCopyAndPasteTheTextToThePostSection() {
        BrowserUtils.waitForClickablility(salesforcePage.InsertBtn, 10);
        salesforcePage.InsertBtn.click();
        String copilotText = salesforcePage.copilotTicketText.getText();
        String actualText = salesforcePage.suggestionPostText.getText();
        Assert.assertTrue(copilotText.equals(actualText));

    }

    @When("user clicks on Disposition option the insert button will appear under the disposition info")
    public void userClicksOnDispositionOptionTheInsertButtonWillAppearUnderTheDispositionInfo() {
        BrowserUtils.waitForClickablility(salesforcePage.commandButton, 10);
        salesforcePage.commandButton.click();
        salesforcePage.DispositionOption.click();
    }

    @And("user clicks on insert button which will copy and paste the info to Case Notes section")
    public void userClicksOnInsertButtonWhichWillCopyAndPasteTheInfoToCaseNotesSection() {
        BrowserUtils.waitForClickablility(salesforcePage.InsertBtn, 10);
        salesforcePage.InsertBtn.click();
        String dispositionText = salesforcePage.DispositionText.getText();

        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//div[@data-aura-class='forcePageBlockItem forcePageBlockItemEdit']"));

        for (WebElement element : elements) {
            // Find all the textareas within each div element
           // List<WebElement> textareas = element.findElements(By.tagName("textarea"));

            // Process the textareas as needed
            //for (WebElement textarea : textareas) {
                // You can interact with each textarea, e.g., get its text, clear it, or enter text into it
//                String textareaText = textarea.getText();
//                System.out.println("Textarea Text: " + textareaText);

                // Or you can perform other actions on the textareas
                // For example:
                // textarea.clear();
                // textarea.sendKeys("New text for the textarea");
           // }


            // Check if the element is visible.

                // Use the JavascriptExecutor class to make the element visible.

                JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
                js.executeScript("arguments[0].style.display = 'block';", element);
            String text = (String) js.executeScript("return arguments[0].innerText;", element);




            // Get the text from the element.

            System.out.println("text = " + text);

        }

//        List<String> caseNotesContent = BrowserUtils.getElementsText(elements);
//        BrowserUtils.waitFor(10);
//        for (String element : caseNotesContent) {
//            if (element.isBlank()){
//                continue;
//            }
//            Assert.assertTrue(dispositionText.contains(element));
//        }



    }
}
