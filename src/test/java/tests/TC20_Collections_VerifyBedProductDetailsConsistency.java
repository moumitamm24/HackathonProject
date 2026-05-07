package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CollectionsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ExcelUtil;

import java.util.ArrayList;
import java.util.List;

public class TC20_Collections_VerifyBedProductDetailsConsistency extends BaseTest
{
    private static final Logger log = LogManager.getLogger(TC20_Collections_VerifyBedProductDetailsConsistency.class);

    @Test
    public void VerifyBedProductDetailsConsistency(){
        CollectionsPage cp = new CollectionsPage(driver);
        cp.hoverOnNewArrivalsMenu();
        cp.ClickOnBedroomRoomOasisCollection();
        cp.clickAllFilter();
        cp.clickOnSortBy();
        cp.clickPriceHightoLow();
        cp.setCloseButton();

        List<String[]> bedData01 = cp.getNonStorageBedData();

        String mostExpensiveBedName = bedData01.get(1)[0];
        String mostExpensiveBedPrice = bedData01.get(1)[1];

        log.info("Most Expensive Bed: {}", mostExpensiveBedName);
        log.info("Most Expensive Bed Price: {}", mostExpensiveBedPrice);

        List<String[]> excelData = new ArrayList<>();
        excelData.add(new String[]{"Bed Name", "Price"});
        excelData.add(new String[]{mostExpensiveBedName, mostExpensiveBedPrice});

        ExcelUtil.writeToExcel(
                "test-output/results/MostExpensiveBed.xlsx",
                "Bed Data",
                excelData
        );

        cp.clickFirstBed();

        Assert.assertNotNull(
                mostExpensiveBedName,
                "Most expensive bed name is null"
        );
        Assert.assertFalse(
                mostExpensiveBedName.trim().isEmpty(),
                "Most expensive bed name is empty"
        );
    }
}
