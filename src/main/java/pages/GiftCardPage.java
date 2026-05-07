package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ConfigReader;
import utils.JSUtils;
import utils.WaitUtils;

public class GiftCardPage {

    private static final Logger log =
            LogManager.getLogger(GiftCardPage.class);

    private final WebDriver driver;

    @FindBy(xpath = "//a[contains(@class,'close-reveal-modal')]")
    private WebElement popupClose;

    @FindBy(xpath = "//a[contains(text(),'Gift Cards')]")
    private WebElement giftCardsLink;

    @FindBy(xpath = "//img[contains(@src,'urban')]")
    private WebElement birthdayEGVImage;

    // Presence locator (declared OUTSIDE methods)
    private static final By BIRTHDAY_EGV_IMAGE_LOCATOR =
            By.xpath("//img[contains(@src,'urban')]");

    @FindBy(css = "input[name='firstname']")
    private WebElement senderFirstName;

    @FindBy(css = "input[name='lastname']")
    private WebElement senderLastName;

    @FindBy(css = "input[name='email']")
    private WebElement senderEmail;

    @FindBy(css = "input[name='telephone']")
    private WebElement mobileNumber;

    @FindBy(css = "textarea[name='giftMessage']")
    private WebElement messageBox;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement sameAsSenderCheckbox;

    @FindBy(xpath = "//div[contains(text(),'Email')]")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//div[contains(text(),'Mobile')]")
    private WebElement mobileErrorMessage;

    public GiftCardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToGiftCardPage() {

        try {
            WaitUtils.waitForVisibility(driver, popupClose);
            popupClose.click();
        } catch (Exception e) {
            log.info("No popup displayed");
        }

        String parentWindow = driver.getWindowHandle();
        giftCardsLink.click();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        WaitUtils.waitForPresence(driver, BIRTHDAY_EGV_IMAGE_LOCATOR);

        log.info("Gift Card page loaded");
    }

    public boolean isGiftCardPageDisplayed() {
        return driver.getCurrentUrl().toLowerCase().contains("gift");
    }

    public void selectBirthdayGiftCard() {

        JSUtils.scrollToMiddle(driver);
        WaitUtils.waitForVisibility(driver, birthdayEGVImage);
        JSUtils.clickUsingJS(driver, birthdayEGVImage);

        log.info("Birthday E‑Gift Card selected");
    }

    public boolean isBirthdayGiftCardSelected() {
        return driver.getCurrentUrl().toLowerCase().contains("woohoo");
    }

    public void enterValidSenderAndReceiverDetails() {

        WaitUtils.waitForVisibility(driver, senderFirstName);

        senderFirstName.clear();
        senderFirstName.sendKeys(ConfigReader.get("firstname"));

        senderLastName.clear();
        senderLastName.sendKeys(ConfigReader.get("lastname"));

        senderEmail.clear();
        senderEmail.sendKeys(ConfigReader.get("email"));

        mobileNumber.clear();
        mobileNumber.sendKeys(ConfigReader.get("mobile"));

        JSUtils.clickUsingJS(driver, sameAsSenderCheckbox);

        messageBox.clear();
        messageBox.sendKeys(ConfigReader.get("message"));

        log.info("Valid sender and receiver details entered");
    }

    public boolean isFormAccepted() {
        return driver.findElements(
                By.xpath("//div[contains(text(),'Please')]")
        ).isEmpty();
    }

    public void enterInvalidSenderEmail() {

        WaitUtils.waitForVisibility(driver, senderFirstName);

        senderFirstName.clear();
        senderFirstName.sendKeys(ConfigReader.get("firstname"));

        senderLastName.clear();
        senderLastName.sendKeys(ConfigReader.get("lastname"));

        senderEmail.clear();
        senderEmail.sendKeys(ConfigReader.get("invalidEmail"));

        mobileNumber.clear();
        mobileNumber.sendKeys(ConfigReader.get("mobile"));

        JSUtils.clickUsingJS(driver, sameAsSenderCheckbox);

        messageBox.clear();
        messageBox.sendKeys(ConfigReader.get("invalidMessage"));

        JSUtils.blurElement(driver, senderEmail);
    }

    public boolean isInvalidEmailErrorDisplayed() {
        WaitUtils.waitForVisibility(driver, emailErrorMessage);
        return emailErrorMessage.isDisplayed();
    }

    public String getInvalidEmailErrorText() {
        return emailErrorMessage.getText();
    }

    public void enterInvalidMobileNumber() {

        WaitUtils.waitForVisibility(driver, senderFirstName);

        senderFirstName.clear();
        senderFirstName.sendKeys(ConfigReader.get("firstname"));

        senderLastName.clear();
        senderLastName.sendKeys(ConfigReader.get("lastname"));

        senderEmail.clear();
        senderEmail.sendKeys(ConfigReader.get("email"));

        mobileNumber.clear();
        mobileNumber.sendKeys(ConfigReader.get("invalidMobile"));

        JSUtils.clickUsingJS(driver, sameAsSenderCheckbox);

        messageBox.clear();
        messageBox.sendKeys(ConfigReader.get("invalidMessage"));

        JSUtils.blurElement(driver, mobileNumber);
    }

    public boolean isInvalidMobileErrorDisplayed() {
        WaitUtils.waitForVisibility(driver, mobileErrorMessage);
        return mobileErrorMessage.isDisplayed();
    }

    public String getInvalidMobileErrorText() {
        return mobileErrorMessage.getText();
    }
}

