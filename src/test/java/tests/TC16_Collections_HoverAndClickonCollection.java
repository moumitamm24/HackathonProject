package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.CollectionsPage;

public class TC16_Collections_HoverAndClickonCollection extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC16_Collections_HoverAndClickonCollection.class);

    @Test
    public void hoverOnNewArrivals()
    {
        CollectionsPage cp = new CollectionsPage(driver);

        // Hovering on New Arrivals menu
        cp.hoverOnNewArrivalsMenu();

        boolean isDisplayed = cp.OasisCollectionSubmenu.isDisplayed();
        log.info("Is Oasis Collection visible: {}", isDisplayed);

        Assert.assertTrue(
                isDisplayed,
                "Oasis Collection is NOT visible on hovering New Arrivals menu"
        );

        // Clicking on Oasis Collection
        cp.clickOnOasisCollection();

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.toLowerCase().contains("oasis"),
                "User is NOT navigated to Oasis Collection page"
        );

        // Simple validation: Shop By Category should NOT be visible
        Assert.assertEquals(
                cp.shopByCategoryHeaders.size(),
                0,
                "Shop By Category section should NOT be visible on Oasis Collection page"
        );
    }
}