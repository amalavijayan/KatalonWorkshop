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

WebUI.click(findTestObject('Common/a_link', [('link') : 'https://www.phptravels.net/admin/accounts/suppliers/']))

WebUI.click(findTestObject('Common/button_type', [('type') : 'submit']))


def data_xls =TestDataFactory.findTestData('ExcelFile/SupplierDetails')

/*-----Filling up form using data from excel------*/

int i=1

WebUI.setText(findTestObject('Common/textbox_name', [('name') : 'fname']), data_xls.getValue(i++,1))
	
WebUI.setText(findTestObject('Common/textbox_name', [('name') : 'lname']), data_xls.getValue(i++,1))
	
WebUI.setText(findTestObject('Common/textbox_name', [('name') : 'email']), data_xls.getValue(i++,1))
	
WebUI.setText(findTestObject('Common/textbox_name', [('name') : 'password']), data_xls.getValue(i++,1))
	
WebUI.setText(findTestObject('Common/textbox_name', [('name') : 'mobile']), data_xls.getValue(i++,1))
	
WebUI.setText(findTestObject('Common/textbox_name', [('name') : 'address1']), data_xls.getValue(i++,1))
	
WebUI.setText(findTestObject('Common/textbox_name', [('name') : 'address2']), data_xls.getValue(i++,1))
	
WebUI.click(findTestObject('AdminCreation/dropdown_input',[('value') : 's2id_autogen1']))
	
WebUI.waitForElementClickable(findTestObject('AdminCreation/input_class'), 50)
	
WebUI.click(findTestObject('AdminCreation/input_class'))
	
WebUI.sendKeys(findTestObject('AdminCreation/input_class'), data_xls.getValue(i,1))
	
WebUI.delay(3)
	
WebUI.click(findTestObject('AdminCreation/dropdown_search', [('value') : data_xls.getValue(i,1)]))
	
WebUI.delay(3)
	
WebUI.selectOptionByValue(findTestObject('AdminCreation/dropdown_select', [('name') : 'applyfor']), data_xls.getValue(++i,1), false)
	
WebUI.setText(findTestObject('Common/textbox_name', [('name') : 'itemname']), data_xls.getValue(++i,1))
	
WebUI.click(findTestObject('AdminCreation/div_checkbox_newsletter', [('value') : '1']))

WebUI.click(findTestObject('SupplierCreation/assign_dropdown', [('id') : 's2id_autogen3']))

WebUI.selectOptionByValue(findTestObject('SupplierCreation/select_assign',[('name') : 'hotels[]']),data_xls.getValue(++i,1),false)

WebUI.click(findTestObject('SupplierCreation/assign_dropdown', [('id') : 's2id_autogen5']))

WebUI.selectOptionByValue(findTestObject('SupplierCreation/select_assign',[('name') : 'tours[]']),data_xls.getValue(++i,1),false)

WebUI.delay(3)

WebUI.click(findTestObject('SupplierCreation/assign_dropdown', [('id') : 's2id_autogen7']))

WebUI.selectOptionByValue(findTestObject('SupplierCreation/select_assign',[('name') : 'cars[]']), data_xls.getValue(++i,1), false)

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'addHotels']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'addTours']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'addCars']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'addbooking']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'addlocations']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'editHotels']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'editTours']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'editCars']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'editbooking']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'editlocations']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'deleteHotels']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'deleteTours']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'deleteCars']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'deletebooking']))

WebUI.click(findTestObject('Common/checkbox_value', [('value') : 'deletelocations']))

WebUI.click(findTestObject('AdminCreation/button_Submit'))

WebUI.verifyElementNotPresent(findTestObject('AdminCreation/alert_validation'), 1)

/*----Verify the admin is created and Logout------*/

WebUI.delay(3)

WebUI.verifyElementPresent(findTestObject('AdminCreation/verify_admin', [('value') : 'Amala Test']), 10)
	
WebUI.click(findTestObject('AdminCreation/a_Logout')) 
	

WebUI.closeBrowser()

