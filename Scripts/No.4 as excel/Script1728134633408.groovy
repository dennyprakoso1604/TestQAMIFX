import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.io.FileOutputStream as FileOutputStream
import java.io.IOException as IOException
import java.time.LocalDateTime as LocalDateTime

def response = WS.sendRequest(findTestObject('Users'))

Workbook workbook = new XSSFWorkbook()

Sheet sheet = workbook.createSheet('testCase')

Row headerRow = sheet.createRow(0)

headerRow.createCell(0).setCellValue('First Name')
headerRow.createCell(1).setCellValue('Last Name')
headerRow.createCell(2).setCellValue('Email')
def totalItem = WS.getElementPropertyValue(response, 'data').size()
int number = 1

for(i=0;i<totalItem;i++) {

	Row row = sheet.createRow(number++)
	row.createCell(0).setCellValue(WS.getElementPropertyValue(response, "data[${i}].first_name"))
	row.createCell(1).setCellValue(WS.getElementPropertyValue(response, "data[${i}].last_name"))
	row.createCell(2).setCellValue(WS.getElementPropertyValue(response, "data[${i}].email"))
}

sheet.autoSizeColumn(0)
sheet.autoSizeColumn(1)
sheet.autoSizeColumn(2)

fileName = "./TestNo4AsExcel.xlsx"

FileOutputStream fileOut = new FileOutputStream(fileName)
try {
	workbook.write(fileOut)
}
catch (IOException e) {
	e.printStackTrace()
}
finally {
	workbook.close()
}
