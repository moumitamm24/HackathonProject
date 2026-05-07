package tests;

import base.BaseTest;
import pages.StudyChairPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
public class TC26_StudyChair_TopThreeHighestRecommendedTest extends BaseTest
{
    private static final Logger log =
            LogManager.getLogger(TC26_StudyChair_TopThreeHighestRecommendedTest.class);

    @Test
    public void verifyTopThreeHighestRecommendedChairs()
    {
        //  Initialize Page Object
        StudyChairPage studyChairPage = new StudyChairPage(driver);

        // Navigation + sorting handled by Page methods
        studyChairPage.navigateToStudyChairs();
        log.info("Navigated to Study Chairs page");

        studyChairPage.sortByPopularity();
        log.info("Sorted study chairs by popularity");

        // Fetch top 3 chairs (including same-price logic)
        Map<String, List<String>> chairMap =
                studyChairPage.getTopThreeChairsWithSamePrice();

        log.info("Fetched top three highest recommended chairs");

        // VALIDATION 1: At least 3 chairs captured
        int totalChairs = chairMap.values()
                .stream()
                .mapToInt(List::size)
                .sum();

        Assert.assertTrue(
                totalChairs >= 3,
                "Less than 3 study chairs captured"
        );

        log.info("Validation 1 passed: Total chairs captured = {}", totalChairs);

        // VALIDATION 2: Each price bucket has at least one chair
        for (Map.Entry<String, List<String>> entry : chairMap.entrySet()) {
            Assert.assertTrue(
                    entry.getValue().size() >= 1,
                    "No chairs found for price: " + entry.getKey()
            );
            log.info(
                    "Price {} has {} chair(s)",
                    entry.getKey(), entry.getValue().size()
            );
        }

        //  VALIDATION 3: Result map should not be empty
        Assert.assertFalse(
                chairMap.isEmpty(),
                "Chair map is empty"
        );

        //  Log chair details (for report / demo)
        for (Map.Entry<String, List<String>> entry : chairMap.entrySet()) {
            log.info("Price: {}", entry.getKey());
            for (String chairName : entry.getValue()) {
                log.info("Chair Name: {}", chairName);
            }
        }
    }
}
