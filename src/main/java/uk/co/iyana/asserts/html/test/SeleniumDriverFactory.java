/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.html.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author fgyara
 */
public class SeleniumDriverFactory {
    
    public static WebDriver getWebDriver(String webDriverName) {
        switch(webDriverName) {
            case "firefox":
                return new FirefoxDriver();
            case "chrome":
                return new ChromeDriver();
                
            default:
                throw new IllegalArgumentException("Supported drivers are chrome and firefox");
        }
    }
}
