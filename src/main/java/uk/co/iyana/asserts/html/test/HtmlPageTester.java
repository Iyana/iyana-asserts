/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.html.test;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import uk.co.iyana.asserts.html.dto.AssertAttribute;
import uk.co.iyana.asserts.html.dto.AssertElement;
import uk.co.iyana.asserts.html.dto.AssertTable;
import uk.co.iyana.asserts.html.dto.Page;
import uk.co.iyana.asserts.html.dto.PageTestData;
import uk.co.iyana.asserts.prettyprint.PrettyPrint;

/**
 *
 * @author fgyara
 */
public class HtmlPageTester {
    
    
    public static void testPage(WebDriver driver, String baseUrl, PageTestData testData) {

        // navigate to the page
        Page testPage = testData.getPage();
        PrettyPrint.print("Page: " + testPage.getName());
        driver.get(baseUrl + testPage.getUrl());
        
        // assert each element on the page
        testElements(testData, driver);
        
        // assert each table on the page
        testTables(testData, driver);
    }

    private static void testElements(PageTestData testData, WebDriver driver) {
        for (AssertElement assertElement : testData.getAssertElements()) {
            PrettyPrint.print("Element: " + assertElement.getName(), 1);

            if (assertElement.isMultiValue()) {
                testMultiValueElement(assertElement, driver);
            } else {
                testSingleValueElement(assertElement, driver);
            }
        }
    }
    
    
    private static void testSingleValueElement(AssertElement assertElement, WebDriver driver) {
        // find the element
        WebElement webElement = driver.findElement(By.xpath(assertElement.getXpath()));
        if (webElement == null) {
            Assert.fail("Could not assert element " + assertElement.getName());
        }

        // check each of the attributes
        for (AssertAttribute assertAttribute : assertElement.getAssertAttributes()) {
            String attributeValue = webElement.getAttribute(assertAttribute.getName());
            PrettyPrint.print("Attribute: " + assertAttribute.getName(), 2);
            PrettyPrint.print("expected value: " + assertAttribute.getValue(), 3);
            PrettyPrint.print("actual value: " + attributeValue.trim(), 3);

            Assert.assertEquals( attributeValue.trim(), assertAttribute.getValue(), "Attribute " + assertElement.getName() + "." + assertAttribute.getName() + " has incorrect value");
        }
    }

    private static void testMultiValueElement(AssertElement assertElement, WebDriver driver) {
        // find the element
        List<WebElement> webElementList = driver.findElements(By.xpath(assertElement.getXpath()));

        if (assertElement.getCount() != null) {
            PrettyPrint.print("expected size: " + assertElement.getCount(), 3);
            PrettyPrint.print("actual size: " + webElementList.size(), 3);
            Assert.assertEquals( webElementList.size(), (int)assertElement.getCount(), "Did not find expected number of elements");
        }
        
        // check each of the attributes
        for (AssertAttribute assertAttribute : assertElement.getAssertAttributes()) {
            // collect the values
            List<String> values = new ArrayList<>();
            for (WebElement webElement: webElementList) {
                values.add(webElement.getAttribute(assertAttribute.getName()).trim());
            }
            
            PrettyPrint.print("Attribute: " + assertAttribute.getName(), 2);
            PrettyPrint.print("expected value: " + assertAttribute.getValues().toString(), 3);
            PrettyPrint.print("actual value: " + values.toString(), 3);

            Assert.assertEquals( values, assertAttribute.getValues(), "Attribute " + assertElement.getName() + "." + assertAttribute.getName() + " has incorrect value list");
        }
    }
    
    
    private static void testTables(PageTestData testData, WebDriver driver) {
        for (AssertTable assertTable : testData.getAssertTables()) {
            PrettyPrint.print("Table: " + assertTable.getName(), 1);

            // find the titles
            String titleXpath = assertTable.getXpath() + "//th";
            List<WebElement> thList = driver.findElements(By.xpath(titleXpath));
            List<String> titles = new ArrayList<>();
            for (WebElement th : thList) {
                titles.add(th.getText().trim());
            }
            PrettyPrint.print("Expected titles:" + assertTable.getTitles(), 2);
            PrettyPrint.print("Actual titles:" + titles, 2);
            Assert.assertEquals( titles, assertTable.getTitles(), "Titles mismatched");
            
        }        
    }
    
}
