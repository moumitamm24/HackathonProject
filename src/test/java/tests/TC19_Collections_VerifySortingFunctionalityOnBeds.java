package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pages.CollectionsPage;

import java.util.List;

public class TC19_Collections_VerifySortingFunctionalityOnBeds extends BaseTest {
    private static final Logger log = LogManager.getLogger(TC19_Collections_VerifySortingFunctionalityOnBeds.class);

    @Test
    public void verifySortingFunctionalityOnBeds() {

        CollectionsPage cp = new CollectionsPage(driver);
        cp.hoverOnNewArrivalsMenu();
        cp.ClickOnBedroomRoomOasisCollection();
        cp.clickAllFilter();
        cp.clickOnSortBy();
        cp.clickPriceLowToHigh();
        cp.setCloseButton();

        List<String[]> bedData = cp.getNonStorageBedData();

        Assert.assertTrue(
                bedData.size() > 1,
                "No bed data found after sorting"
        );

        int limit = Math.min(10, bedData.size() - 1);
        for (int i = 1; i <= limit; i++) {
            log.info("Cheapest Bed {} -> Name: {}, Price: {}",
                    i, bedData.get(i)[0], bedData.get(i)[1]);
        }

        //Assertion 2: cheapest bed has valid name & price
        String cheapestBedName = bedData.get(1)[0];
        String cheapestBedPrice = bedData.get(1)[1];

        Assert.assertNotNull(cheapestBedName, "Cheapest bed name is null");
        Assert.assertFalse(cheapestBedName.trim().isEmpty(), "Cheapest bed name is empty");

        Assert.assertNotNull(cheapestBedPrice, "Cheapest bed price is null");
        Assert.assertFalse(cheapestBedPrice.trim().isEmpty(), "Cheapest bed price is empty");
    }

}
