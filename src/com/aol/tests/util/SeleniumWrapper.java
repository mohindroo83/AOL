package com.aol.tests.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashishmohindroo on 17/09/15.
 */
public class SeleniumWrapper {

    private static WebDriver driver = null;
    private static WebDriverWait wait;

    private static String currentHandle;


    public static void initializeDriver() {

        if (driver == null) {

            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, 25000);
        }

    }

    public static WebDriver getDriver() {
        return driver;
    }


    public void goTo(String url) {

        getDriver().get(url);

    }

    public SeleniumWrapper checkIfLinkPresent(String Id) {
        wait.pollingEvery(1, TimeUnit.SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.id(Id)));
        return this;
    }

    public SeleniumWrapper search(String keywords) {

        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aol-header-query")));
        e.sendKeys(keywords);
        return this;
    }

    public void clickSearchBtn() {
        getDriver().findElement(By.id("aol-header-search-button")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result-count")));
    }

    public void clickSearchResult(String searchUrl) {
        getDriver().findElement(By.xpath(".//*[@id='w']//h3/a[@href='" + searchUrl + "']")).click();
        currentHandle = getCurrentWindowHandle();
    }

    public void checkLogoLink(String logoString) {

        Set<String> handles = getDriver().getWindowHandles();
        if (handles.size() > 2) {
            Assert.fail("More than 2 windows opened");
        } else {
            handles.remove(currentHandle);
            Iterator<String>  itr = handles.iterator();
            String handle = itr.next();
            getDriver().switchTo().window(handle);
        }
        wait.pollingEvery(1,TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='GH_brand']/a[.='" + logoString + "']")));
        getDriver().switchTo().window(currentHandle);

    }

    public String getCurrentWindowHandle() {
        return getDriver().getWindowHandle();
    }

}
