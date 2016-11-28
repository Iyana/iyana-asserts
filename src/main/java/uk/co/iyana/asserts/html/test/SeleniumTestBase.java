/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.html.test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import uk.co.iyana.asserts.test.TestBase;

/**
 *
 * @author fgyara
 */
public class SeleniumTestBase extends TestBase {
    private final WebDriver driver;
    private final boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();
    
    public SeleniumTestBase(String testPrefix, String seleniumDriver) {
        super(testPrefix);
        this.driver  = SeleniumDriverFactory.getWebDriver(seleniumDriver);
    }
    
    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        getDriver().quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }    

    /**
     * @return the driver
     */
    public WebDriver getDriver() {
        return driver;
    }
    
}
