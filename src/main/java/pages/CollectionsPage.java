package pages;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CollectionsPage
{
    WebDriver driver;
    Actions actions;

    public CollectionsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }
    private static final Logger log = LogManager.getLogger(CollectionsPage.class);

    // New Arrivals menu
    @FindBy(xpath = "//span[text()='New Arrivals']")
    public WebElement newArrivalsSubmenu;

    @FindBy(xpath = "//a[text()='Oasis Collection']")
    public WebElement OasisCollectionSubmenu;

    // Oasis sub‑menu items
    @FindBy(xpath = "//a[normalize-space()='Discover Oasis']//following-sibling::a")
    public List<WebElement> itemsDiv;

    // Bedroom under Oasis
    @FindBy(xpath = "//a[contains(text(), 'Bedroom')]")
    public WebElement bedroomsSubMenu;

    //Strong Filter
    @FindBy(xpath = "//div[@role='button' and @aria-label='Storage Type filter']")
    public WebElement bedFilter;

    @FindBy(xpath = "//div[contains(text(), 'Non Storage')]")
    public WebElement nonStorageOption;

    @FindBy(xpath = "//h3[@class='XxwSy']")
    public List<WebElement> bedProductNames;

    @FindBy(xpath = "//div[contains(@class, 'UYQNp')]")
    public List<WebElement> bedProductPrices;

    @FindBy(xpath = "//div[contains(text(), 'ALL FILTERS')]")
    public WebElement AllFilter;

    @FindBy(xpath = "//div[@aria-label='Sort']")
    public WebElement sortByFilter;

    @FindBy(xpath = "//div[contains(text(), 'Price Low to High')]")
    public WebElement priceLowToHighFilter;

    @FindBy(xpath = "//h3[@class='o0mbO']")
    public List<WebElement> bedProductCards;

    @FindBy(xpath = "//div[@class='o0mbO']")
    public WebElement firstBed;

    @FindBy(xpath = "//div[@aria-label='Close filter drawer']")
    public WebElement closeButton;

    @FindBy(xpath = "//div[class='h47F4']")
    public WebElement productPageName;

    @FindBy(xpath = "//div[contains(@class,'QwNR')]")  // or contains(text(),'₹')
    public WebElement productPagePrice;

    @FindBy(xpath = "//div[contains(text(), 'Price High to Low')]")
    public WebElement hightoLow;

    @FindBy(xpath = "//h2[@class='IQ3Xa']")
    public List<WebElement> shopByCategoryHeaders;

    public void setCloseButton()
    {
        closeButton.click();
    }
    public void hoverOnNewArrivalsMenu()
    {
        actions.moveToElement(newArrivalsSubmenu).perform();
    }
    public void clickOnOasisCollection()
    {
        OasisCollectionSubmenu.click();
    }
    public void printSubMenuItems()
    {
        log.info("Sub menu items count: {}", itemsDiv.size());
        log.info("{");
        for (WebElement item : itemsDiv) {
            log.info(item.getText());
        }
        log.info("}");
    }
    public List<String[]> getOasisSubMenuData()
    {
        List<String[]> data = new ArrayList<>();
        // Header row
        data.add(new String[] { "Sub Menu Item" });

        for (WebElement item : itemsDiv) {
            String text = item.getText().trim();
            if (!text.isEmpty()) {
                data.add(new String[] { text });
            }
        }
        return data;
    }
    public void ClickOnBedroomRoomOasisCollection()
    {
        bedroomsSubMenu.click();
    }
    public void clickOnBedsStorage()
    {
        bedFilter.click();
    }
    public void clickNonStorage()
    {
        nonStorageOption.click();
    }
    public void printNonStorageBedResults()
    {
        int count = Math.min(bedProductNames.size(), bedProductPrices.size());

        log.info("Total Non-Storage Beds Found: {}", count);

        for (int i = 0; i < count; i++) {
            String name = bedProductNames.get(i).getText();
            String price = bedProductPrices.get(i).getText();

            log.info("Bed {} -> Name: {}, Price: ₹{}", i + 1, name, price);
        }
    }
    public List<String[]> getNonStorageBedData()
    {
        List<String[]> bedData = new ArrayList<>();
        // Header row
        bedData.add(new String[] { "Bed Name", "Price" });

        int count = Math.min(bedProductNames.size(), bedProductPrices.size());

        for (int i = 0; i < count; i++) {
            String name = bedProductNames.get(i).getText();
            String price = "₹" + bedProductPrices.get(i).getText();

            bedData.add(new String[] { name, price });
        }
        return bedData;
    }
    public void clickAllFilter()
    {
        AllFilter.click();
    }
    public void clickOnSortBy()
    {
        sortByFilter.click();
    }
    public void clickPriceLowToHigh()
    {
        priceLowToHighFilter.click();
    }
    public void clickFirstBed()
    {
        firstBed.click();
    }
    public void clickPriceHightoLow()
    {
        hightoLow.click();
    }
}