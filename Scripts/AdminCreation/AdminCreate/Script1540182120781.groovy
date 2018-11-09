import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

/*---------Login as Admin-----------*/

WebUI.callTestCase(findTestCase('AdminCreation/AdminLogin'), [('url') : 'https://www.phptravels.net/admin', ('username') : 'admin@phptravels.com'
        , ('password') : 'demoadmin'], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Common/a_link', [('link') : '#ACCOUNTS']))

WebUI.click(findTestObject('Common/a_link', [('link') : 'https://www.phptravels.net/admin/accounts/admins/']))

WebUI.click(findTestObject('Common/button_type', [('type') : 'submit']))

/*---------Establish DB connection-----------*/

def dbdata = findTestData('DB/Admin_details')

def dbobject = findTestData('DB/Object_repo_admin')

/*-----Filling up form using data from db------*/

int i
for(i=1; i<= dbobject.getColumnNumbers(); i++)
{
	
	WebUI.setText(findTestObject('Common/textbox_name', [('name') : dbobject.getValue(i,1)]), dbdata.getValue(i,1))
}

WebUI.click(findTestObject('AdminCreation/dropdown_input',[('value') : 's2id_autogen1']))
	
WebUI.waitForElementClickable(findTestObject('AdminCreation/dropdown_search', [('value') : dbdata.getValue(i,1)]), 10)
	
WebUI.click(findTestObject('AdminCreation/dropdown_search', [('value') : dbdata.getValue(i++,1)]))
	
WebUI.delay(3)
	
WebUI.click(findTestObject('AdminCreation/div_checkbox_newsletter', [('value') : dbdata.getValue(i,1)]))
	
WebUI.selectOptionByValue(findTestObject('AdminCreation/dropdown_select', [('name') : 'status']), 'yes', false)

/*---------Establish DB connection for Chckboxes-----------*/

def dbcheckbox =findTestData('DB/Checkbox_Admin')

/*---------Check the checkboxes----------------*/

for(int x=1; x<=dbcheckbox.getRowNumbers(); x++)
{
	for(int y=1; y<=dbcheckbox.getColumnNumbers(); y++)
	{
		WebUI.click(findTestObject('Common/checkbox_value', [('value') : dbcheckbox.getValue(y,x)]))
	}
}

/*----Submit the form and verify no validation box is displayed------*/

WebUI.click(findTestObject('Common/button_Submit'))

WebUI.verifyElementNotPresent(findTestObject('Common/alert_validation'), 1)
   

/*----Verify the new admin is created------*/

int j=1
		
String fname = dbdata.getValue(j++,1)
	
String lname = dbdata.getValue(j++,1)
	
String fullname = fname +' '+ lname
	
String username = dbdata.getValue(j++,1)
	
String password = dbdata.getValue(j++,1)
	
WebUI.verifyElementPresent(findTestObject('Common/verify_admin', [('value') : fname]), 10)
	
WebUI.click(findTestObject('Common/a_Logout'))
	

/*--------Login with the newly created admin-------*/
	
WebUI.setText(findTestObject('Common/textbox_type', [('type') : 'text']), username)
	
WebUI.setText(findTestObject('Common/textbox_type', [('type') : 'password']), password)
	
WebUI.click(findTestObject('Common/button_type', [('type') : 'submit']))
	
WebUI.verifyElementText(findTestObject('AdminLogin/span_text', [('text') : fullname]), fullname)
	
WebUI.click(findTestObject('Common/a_Logout'))

WebUI.closeBrowser()

