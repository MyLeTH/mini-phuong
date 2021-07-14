import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.concurrent.locks.Condition

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.InternalData
import com.kms.katalon.core.testdata.ExcelData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import internal.GlobalVariable

public class Checkpoint_keyword {
	@Keyword
	def CheckTitle(String title_name) {
		TestObject title = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//h4[contains(.,'${title_name}')]")
		WebUI.verifyElementVisible(title)
	}
	@Keyword
	def CheckProfile(String data_file) {
		ExcelData data = findTestData(data_file)
		for (def index : (1..data.getRowNumbers() - 1)) {
			String field = data.getValue("FieldName", index)
			String field_info = data.getValue("FieldInfo", index)
			TestObject profile = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//label[contains(.,'${field}')]//following-sibling::div/input[@value='${field_info}']")
			WebUI.verifyElementVisible(profile)
		}
	}
	@Keyword
	def CheckHeaderName(String inputHeader) {
		//add a list header "inputHeader" split by ";"
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement HeaderRow = driver.findElement(By.xpath("//table/thead/tr"))
		List<WebElement> headers = HeaderRow.findElements(By.tagName('th'))
		String returnValue = ""
		for (int column = 1; column < headers.size(); column++) {
			String celltext = headers.get(column).getText()
			if (column == 1) {
				returnValue = returnValue + celltext
			}
			else
				returnValue = returnValue + ";" +celltext
		}
		WebUI.verifyEqual(returnValue,inputHeader)
	}
}
