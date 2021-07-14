import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.ExcelData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.Keys as Keys

import internal.GlobalVariable

public class Util_keywords {
	@Keyword
	def InputFieldsByDataFile(String data_file) {
		ExcelData data = findTestData(data_file)
		for (def index : (1..data.getRowNumbers() - 1)) {
			String field = data.getValue("FieldName", index)
			String field_info = data.getValue("FieldInfo", index)
			TestObject profile = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//label[contains(.,'${field}')]//following-sibling::div/input")
			WebUI.sendKeys(profile, field_info)
		}
	}
	@Keyword
	def GetHeaderValue() {
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement HeaderRow = driver.findElement(By.xpath("//table/thead/tr"))
		List<WebElement> headers = HeaderRow.findElements(By.tagName('th'))
		String returnValue = ""
		for (int column = 1; column < headers.size(); column++) {
			String celltext = headers.get(column).getText()
			returnValue = returnValue + ";" +celltext
		}
		return(returnValue)
	}
	@Keyword 
	def SearchByValue(String value) {
		WebUI.setText(findTestObject('Mini-Phuong/BasePage/Search_txt'), value)
		WebUI.sendKeys(findTestObject('Mini-Phuong/BasePage/Search_txt'), Keys.chord(Keys.ENTER))
	}
}
