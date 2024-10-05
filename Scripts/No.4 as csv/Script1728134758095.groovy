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
import java.io.File
import java.io.FileWriter
import java.io.BufferedWriter

def response = WS.sendRequest(findTestObject('Users'))

fileName = "./TestNo4AsCsv.csv"
File csvFile = new File(fileName)
BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))

def dataHeader = ["First Name", "Last Name", "Email"]
writer.write(dataHeader.join(","))
writer.newLine()

def totalItem = WS.getElementPropertyValue(response, 'data').size()
int number = 1

for(i=0;i<totalItem;i++) {
	def dataBody = [WS.getElementPropertyValue(response, "data[${i}].first_name"), WS.getElementPropertyValue(response, "data[${i}].last_name"), WS.getElementPropertyValue(response, "data[${i}].email")]
	writer.write(dataBody.join(","))
	writer.newLine()
}

writer.close()

