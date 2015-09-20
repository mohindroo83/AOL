
import com.aol.tests.util.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

/**
 * Created by ashishmohindroo on 17/09/15.
 */
public class AOLSearch {
    SeleniumWrapper wrapper = new SeleniumWrapper();


    @BeforeSuite
    public void setUp() {
        wrapper.initializeDriver();
    }


    @AfterSuite
    public void tearDown() {
        wrapper.getDriver().close();
        wrapper.getDriver().quit();
    }

    @Test
    public void AolTestScenario1() {
        wrapper.goTo("http://www.aol.com");
        wrapper.checkIfLinkPresent("ghnav-news").checkIfLinkPresent("ghnav-sports").checkIfLinkPresent("ghnav-entertainment");
        wrapper.search("AOL Autos").clickSearchBtn();
        wrapper.clickSearchResult("http://autos.aol.com/sitemap/");
        wrapper.checkLogoLink("AOL Autos");
    }


}
