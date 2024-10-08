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

WebUI.openBrowser('https://parabank.parasoft.com/parabank/index.htm')

WebUI.setText(findTestObject('Object Repository/Page_ParaBank  Welcome  Online Banking/input_Username_username'), 'adminQu')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_ParaBank  Welcome  Online Banking/input_Password_password'), 
    'U9w/kQNKnT4=')

WebUI.click(findTestObject('Object Repository/Page_ParaBank  Welcome  Online Banking/input_Password_button'))

WebUI.click(findTestObject('Object Repository/Page_ParaBank  Accounts Overview/a_Accounts Overview'))

def rows = WebUI.findWebElements(findTestObject('Page_ParaBank  Accounts Overview/Table_Utama'), 10)

def rowSize = rows.size()

def total = 0

def minusAccount = 0

for (i = 1; i < rowSize; i++) {
    GlobalVariable.row = i

    def balance = WebUI.getText(findTestObject('Page_ParaBank  Accounts Overview/Table_account_Overview'))

    def balanceWithoutDollar = balance.replaceAll('\\$', '').toBigDecimal()

    if (balanceWithoutDollar < 0) {
        minusAccount += 1
    }
    
    total += balanceWithoutDollar
}

GlobalVariable.row = rowSize

def balanceTotal = WebUI.getText(findTestObject('Page_ParaBank  Accounts Overview/Table_account_Overview'))

balanceTotal = balanceTotal.replaceAll('\\$', '').toBigDecimal()

if (total == balanceTotal) {
    println("Total balance = $total and the account with minus balance is $minusAccount")

    println("Total is Match with total in Web :$balanceTotal ") //WebUI.comment("Total Is not match ")
} else {
    assert false
}

WebUI.closeBrowser()

