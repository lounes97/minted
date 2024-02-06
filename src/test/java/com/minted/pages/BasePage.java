package com.minted.pages;

import com.minted.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(xpath = "//input[@class='input r4 wide mb16 mt8 username']" )
    public WebElement username;

    @FindBy(xpath = "//input[@class='input r4 wide mb16 mt8 password']")
    public WebElement password;

    @FindBy(xpath = "//input[@class='button r4 wide primary']")
    public WebElement loginButton;


    @FindBy(xpath = "//div[contains(@class,'chakra-slide')]/button")
    public WebElement coPilotIcon;
    //button[contains(@class,'chakra-button react-draggable')]

    @FindBy(xpath = "//p[@data-test-id='kodif-intro-msg']")
    public WebElement ticketShiftDefaultText;
}