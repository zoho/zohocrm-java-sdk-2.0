package samples.main;

import java.io.ByteArrayOutputStream;

import java.io.FileInputStream;

import java.io.InputStream;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.client.methods.HttpUriRequest;

import org.apache.http.client.utils.URIBuilder;

import org.apache.http.conn.ssl.NoopHostnameVerifier;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import org.apache.http.entity.mime.content.ByteArrayBody;

import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.util.EntityUtils;

import com.zoho.crm.sample.initializer.Initialize;

public class Test
{
	public static void main(String[] args) throws Exception 
	{
		Initialize.initialize();
		
		Attachment();
		
		BluePrint();
		
		BulkRead();
		
		BulkWrite();
		
		ContactRoles();
		
		Currency();
		
		CustomView();
		
		Field();
		
		File();
		
		Layout();
		
		Module();
		
		Note();
		
		Notification();
		
		Organization();
		
		Profile();
		
		Query();
		
		Record();
		
		RelatedList();
		
		RelatedRecords();
		
		Role();
		
		ShareRecords();
		
		Tags();
		
		Tax();
		
		Territory();
		
		User();
		
		VariableGroup();
		
		Variable();
		
		TestUpload();
	}
	
	public static void Attachment()
	{
		try
		{
			String moduleAPIName = "leads";
			
			Long recordId = 3477066838056l;
			
			Long attachmentId = 34770610792l;
			
			String absoluteFilePath = "/Users/file/download.png";
			
			String destinationFolder = "/Users/file";
			
			String attachmentURL = "https://5.imimg.com/data5/KJ/UP/MY-8655440/zoho-crm-500x500.png";
			
			List<Long> attachmentIds = new ArrayList<Long>(Arrays.asList(34770610697012l, 34770610705001l, 3477067119061l));
			
			com.zoho.crm.sample.attachments.Attachment.uploadAttachments(moduleAPIName, recordId, absoluteFilePath);
			
			com.zoho.crm.sample.attachments.Attachment.getAttachments(moduleAPIName, recordId);
			
			com.zoho.crm.sample.attachments.Attachment.deleteAttachments(moduleAPIName, recordId, attachmentIds);
			
			com.zoho.crm.sample.attachments.Attachment.downloadAttachment(moduleAPIName, recordId, attachmentId, destinationFolder);
			
			com.zoho.crm.sample.attachments.Attachment.deleteAttachment(moduleAPIName, recordId, attachmentId);
			
			com.zoho.crm.sample.attachments.Attachment.uploadLinkAttachments(moduleAPIName, recordId, attachmentURL);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void BluePrint()
	{
		try
		{
			String moduleAPIName = "Leads";
	
			Long recordId = 3477064381002l;
			
			Long transitionId = 3477060173093l;
			
			com.zoho.crm.sample.blueprint.BluePrint.getBlueprint(moduleAPIName, recordId);
			
			com.zoho.crm.sample.blueprint.BluePrint.updateBlueprint(moduleAPIName, recordId, transitionId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void BulkRead()
	{
		try
		{
			String moduleAPIName = "Leads";
			
			Long jobId = 34770610799001l;
			
			String destinationFolder = "/Users/file";
			
			com.zoho.crm.sample.bulkread.BulkRead.createBulkReadJob(moduleAPIName);
			
			com.zoho.crm.sample.bulkread.BulkRead.getBulkReadJobDetails(jobId);
			
			com.zoho.crm.sample.bulkread.BulkRead.downloadResult(jobId, destinationFolder);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void BulkWrite()
	{
		try
		{
			String absoluteFilePath = "/Users/Leads.zip";
			
			String orgID = "xxxxx";
			
			String moduleAPIName = "Leads";
			
			String fileId  = "34770610801001";
			
			Long jobID = 3477061083l;
			
			String downloadUrl = "https://download-accl.zoho.com/v2/crm/xxxx/bulk-write/3477061083/3477061083.zip";
			
			String destinationFolder = "/Users/file";
			
			com.zoho.crm.sample.bulkwrite.BulkWrite.uploadFile(orgID, absoluteFilePath);
			
			com.zoho.crm.sample.bulkwrite.BulkWrite.createBulkWriteJob(moduleAPIName, fileId);
			
			com.zoho.crm.sample.bulkwrite.BulkWrite.getBulkWriteJobDetails(jobID);
			
			com.zoho.crm.sample.bulkwrite.BulkWrite.downloadBulkWriteResult(downloadUrl, destinationFolder);
		}
		catch(Exception ex)
		{
			
		}
	}

	public static void ContactRoles() 
	{
		try
		{
			Long contactRoleId = 34770610803001l;
			
			ArrayList<String> contactRoleIds = new ArrayList<String>(Arrays.asList("34770610704010","34770610704006","34770610704004"));
			
			com.zoho.crm.sample.contactroles.ContactRoles.getContactRoles();
			
			com.zoho.crm.sample.contactroles.ContactRoles.createContactRoles();
			
			com.zoho.crm.sample.contactroles.ContactRoles.updateContactRoles();
			
			com.zoho.crm.sample.contactroles.ContactRoles.deleteContactRoles(contactRoleIds);
			
			com.zoho.crm.sample.contactroles.ContactRoles.getContactRole(contactRoleId);
			
			com.zoho.crm.sample.contactroles.ContactRoles.updateContactRole(contactRoleId);
			
			com.zoho.crm.sample.contactroles.ContactRoles.deleteContactRole(contactRoleId);
			
			com.zoho.crm.sample.contactroles.ContactRoles.getAllContactRolesOfDeal(3477060207275l);

			com.zoho.crm.sample.contactroles.ContactRoles.getContactRoleOfDeal(3477060208072l, 3477060207275l);

			com.zoho.crm.sample.contactroles.ContactRoles.addContactRoleToDeal(3477060208072l, 3477060207275l);

			com.zoho.crm.sample.contactroles.ContactRoles.removeContactRoleFromDeal(3477060208072l, 3477060207275l);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Currency() 
	{
		try
		{
			Long currencyId = 3477067368016l;
			
			com.zoho.crm.sample.currencies.Currency.getCurrencies();
			
			com.zoho.crm.sample.currencies.Currency.addCurrencies();
			
			com.zoho.crm.sample.currencies.Currency.updateCurrencies();
			
			com.zoho.crm.sample.currencies.Currency.enableMultipleCurrencies();
			
			com.zoho.crm.sample.currencies.Currency.updateBaseCurrency();
			
			com.zoho.crm.sample.currencies.Currency.getCurrency(currencyId);
			
			com.zoho.crm.sample.currencies.Currency.updateCurrency(currencyId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void CustomView() 
	{
		try
		{
			String moduleAPIName = "Leads";
			
			Long customID = 3477060089007l;
			
//			List<String> names = new ArrayList<String>(Arrays.asList("Products", "Tasks", "Vendors", "Calls", "Leads", "Deals", "Campaigns", "Quotes", "Invoices", "Attachments", "Price_Books", "Sales_Orders", "Contacts", "Solutions", "Events", "Purchase_Orders", "Accounts", "Cases", "Notes"));
//			
//			for(String name :names)
//			{
//				com.zoho.crm.sample.customview.CustomView.getCustomViews(name);
//			}
			
			com.zoho.crm.sample.customview.CustomView.getCustomViews(moduleAPIName);
			
			com.zoho.crm.sample.customview.CustomView.getCustomView(moduleAPIName, customID);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Field() 
	{
		try
		{
			String moduleAPIName = "Deals";
			
			Long fieldId = 3477060022011l;
			
//			List<String> names = new ArrayList<String>(Arrays.asList("Products", "Tasks", "Vendors", "Calls", "Leads", "Deals", "Campaigns", "Quotes", "Invoices", "Attachments", "Price_Books", "Sales_Orders", "Contacts", "Solutions", "Events", "Purchase_Orders", "Accounts", "Cases", "Notes"));
//			
//			for(String name :names)
//			{
//				com.zoho.crm.sample.fields.Fields.getFields(name);
//			}
			
			com.zoho.crm.sample.fields.Fields.getFields(moduleAPIName);
			
			com.zoho.crm.sample.fields.Fields.getField(moduleAPIName, fieldId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void File() 
	{
		try
		{
			String destinationFolder =  "/Users/file";
			
			String id = "ae9c7cefa418125ad161ce416136";
			
			com.zoho.crm.sample.file.File.uploadFiles();
			
			com.zoho.crm.sample.file.File.getFile(id, destinationFolder);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void Layout() 
	{
		try
		{
			String moduleAPIName = "Tasks";
			
			Long layoutId = 3477060091055l;
			
//			List<String> names = new ArrayList<String>(Arrays.asList("Products", "Tasks", "Vendors", "Calls", "Leads", "Deals", "Campaigns", "Quotes", "Invoices", "Attachments", "Price_Books", "Sales_Orders", "Contacts", "Solutions", "Events", "Purchase_Orders", "Accounts", "Cases", "Notes"));
//			
//			for(String name : names)
//			{
//				com.zoho.crm.sample.layouts.Layout.getLayouts(name);
//			}
			
			com.zoho.crm.sample.layouts.Layout.getLayouts(moduleAPIName);
			
			com.zoho.crm.sample.layouts.Layout.getLayout(moduleAPIName, layoutId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Module() 
	{
		try
		{
			String moduleAPIName = "apiName1";
			
			Long moduleId = 3477063905003L;
			
			com.zoho.crm.sample.modules.Modules.getModules();
			
			com.zoho.crm.sample.modules.Modules.getModule(moduleAPIName);
			
			com.zoho.crm.sample.modules.Modules.updateModuleByAPIName(moduleAPIName);
			
			com.zoho.crm.sample.modules.Modules.updateModuleById(moduleId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Note() 
	{
		try
		{
			ArrayList<Long> notesId = new ArrayList<Long>(Arrays.asList(34770610696011l,34770610696010l));
			
			Long noteId = 34770610696009l;
			
			com.zoho.crm.sample.notes.Note.getNotes();
			
			com.zoho.crm.sample.notes.Note.createNotes();
			
			com.zoho.crm.sample.notes.Note.updateNotes();
			
			com.zoho.crm.sample.notes.Note.deleteNotes(notesId); 
			
			com.zoho.crm.sample.notes.Note.getNote(noteId);
			
			com.zoho.crm.sample.notes.Note.updateNote(noteId);
			
			com.zoho.crm.sample.notes.Note.deleteNote(noteId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Notification() 
	{
		try
		{
			
			ArrayList<Long> channelIds = new ArrayList<Long>(Arrays.asList(006800211l));
			
			com.zoho.crm.sample.notification.Notification.enableNotifications();
			
			com.zoho.crm.sample.notification.Notification.getNotificationDetails();
			
			com.zoho.crm.sample.notification.Notification.updateNotifications();
			
			com.zoho.crm.sample.notification.Notification.updateNotification();
			
			com.zoho.crm.sample.notification.Notification.disableNotifications(channelIds);
			
			com.zoho.crm.sample.notification.Notification.disableNotification();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void Organization() 
	{
		try
		{
			String absoluteFilePath = "/Users/file/download.png";
			
			com.zoho.crm.sample.organization.Organization.getOrganization();
			
			com.zoho.crm.sample.organization.Organization.uploadOrganizationPhoto(absoluteFilePath);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Profile() 
	{
		try
		{
			Long profileId = 3477060026011l;
			
			com.zoho.crm.sample.profile.Profile.getProfiles();
			
			com.zoho.crm.sample.profile.Profile.getProfile(profileId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Query()
	{
		try
		{
			com.zoho.crm.sample.query.Query.getRecords();
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Record() 
	{
		try
		{
			String moduleAPIName = "leads";
			
			long recordId = 34770610820043l;//34770610783139l;
			
			String externalFieldValue = "TestExternalLead1";
			
			String destinationFolder =  "/Users/file";
			
			String absoluteFilePath = "/Users/file/download.png";
			
			List<String> recordIds = new ArrayList<String>(Arrays.asList("Value", "3477065908017", "3477065908001"));
			
			String jobId = "3477067416301";
			
			List<String> names = new ArrayList<String>(Arrays.asList("Products", "Tasks", "Vendors", "Calls", "Leads", "Deals", "Campaigns", "Quotes", "Invoices", "Attachments", "Price_Books", "Sales_Orders", "Contacts", "Solutions", "Events", "Purchase_Orders", "Accounts", "Cases", "Notes"));
			
			for(String name :names)
			{
				 com.zoho.crm.sample.record.Record.getRecords(name);
			}
			
			com.zoho.crm.sample.record.Record.getRecord(moduleAPIName, recordId, destinationFolder);
			
			com.zoho.crm.sample.record.Record.updateRecord(moduleAPIName, recordId);
			
			com.zoho.crm.sample.record.Record.deleteRecord(moduleAPIName, recordId);
			
			com.zoho.crm.sample.record.Record.getRecordUsingExternalId(moduleAPIName, externalFieldValue, destinationFolder);

			com.zoho.crm.sample.record.Record.updateRecordUsingExternalId(moduleAPIName, externalFieldValue);
			
			com.zoho.crm.sample.record.Record.deleteRecordUsingExternalId(moduleAPIName, externalFieldValue);
			
			com.zoho.crm.sample.record.Record.getRecords(moduleAPIName);
			
			com.zoho.crm.sample.record.Record.createRecords(moduleAPIName);
			
			com.zoho.crm.sample.record.Record.updateRecords(moduleAPIName);
			
			com.zoho.crm.sample.record.Record.deleteRecords(moduleAPIName, recordIds);
			
			com.zoho.crm.sample.record.Record.upsertRecords(moduleAPIName);
			
			com.zoho.crm.sample.record.Record.getDeletedRecords(moduleAPIName);
			
			com.zoho.crm.sample.record.Record.searchRecords(moduleAPIName);
			
			com.zoho.crm.sample.record.Record.convertLead(recordId);
			
			com.zoho.crm.sample.record.Record.getPhoto(moduleAPIName, recordId, destinationFolder);
			
			com.zoho.crm.sample.record.Record.uploadPhoto(moduleAPIName, recordId, absoluteFilePath);
			
			com.zoho.crm.sample.record.Record.deletePhoto(moduleAPIName, recordId);
			
			com.zoho.crm.sample.record.Record.massUpdateRecords(moduleAPIName);
			
			com.zoho.crm.sample.record.Record.getMassUpdateStatus(moduleAPIName, jobId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void RelatedList() 
	{
		try
		{
			String moduleAPIName = "Leads";
			
			Long relatedListId = 3477066819126l;
			
//			List<String> names = new ArrayList<String>(Arrays.asList("Products", "Tasks", "Vendors", "Calls", "Leads", "Deals", "Campaigns", "Quotes", "Invoices", "Attachments", "Price_Books", "Sales_Orders", "Contacts", "Solutions", "Events", "Purchase_Orders", "Accounts", "Cases"));
//			
//			for(String name :names)
//			{
//				com.zoho.crm.sample.relatedlist.RelatedList.getRelatedLists(name);
//			}
			
			com.zoho.crm.sample.relatedlist.RelatedList.getRelatedLists(moduleAPIName);
			
			com.zoho.crm.sample.relatedlist.RelatedList.getRelatedList(moduleAPIName, relatedListId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void RelatedRecords() 
	{
		try
		{
			String moduleAPIName = "leads";
			
			Long recordId = 34770610780113l;
			
			String relatedListAPIName = "products";
			
			Long relatedRecordId = 3477067081077l;
			
			String destinationFolder =  "/Users/file";
			
			List<String> relatedListIds = new ArrayList<String>(Arrays.asList("TestExternalLead121", "3477065919001"));
			
			String externalValue = "TestExternalLead12";

			String externalFieldValue = "TestExternal121";
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.getRelatedRecords(moduleAPIName, recordId, relatedListAPIName);
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.updateRelatedRecords(moduleAPIName, recordId, relatedListAPIName);
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.delinkRecords(moduleAPIName, recordId, relatedListAPIName, relatedListIds);
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.getRelatedRecordsUsingExternalId(moduleAPIName, externalValue, relatedListAPIName);
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.updateRelatedRecordsUsingExternalId(moduleAPIName, externalValue, relatedListAPIName);
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.deleteRelatedRecordsUsingExternalId(moduleAPIName, externalValue, relatedListAPIName, relatedListIds);
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.getRelatedRecord(moduleAPIName, recordId, relatedListAPIName, relatedRecordId, destinationFolder);
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.updateRelatedRecord(moduleAPIName, recordId, relatedListAPIName, relatedRecordId);
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.delinkRecord(moduleAPIName, recordId, relatedListAPIName, relatedRecordId);

			com.zoho.crm.sample.relatedrecords.RelatedRecords.getRelatedRecordUsingExternalId(moduleAPIName, externalValue, relatedListAPIName, externalFieldValue, destinationFolder);
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.updateRelatedRecordUsingExternalId(moduleAPIName, externalValue, relatedListAPIName, externalFieldValue);
			
			com.zoho.crm.sample.relatedrecords.RelatedRecords.deleteRelatedRecordUsingExternalId(moduleAPIName, externalValue, relatedListAPIName, externalFieldValue);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Role()  
	{
		try
		{
			Long roleId = 3477060026008l;
			
			com.zoho.crm.sample.role.Role.getRoles();
			
			com.zoho.crm.sample.role.Role.getRole(roleId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void ShareRecords() 
	{
		try
		{
			String moduleAPIName = "Leads";
			
			long recordId = 3477065623115L;
			
			com.zoho.crm.sample.sharerecords.ShareRecords.getSharedRecordDetails(moduleAPIName, recordId);
			
			com.zoho.crm.sample.sharerecords.ShareRecords.shareRecord(moduleAPIName, recordId);
			
			com.zoho.crm.sample.sharerecords.ShareRecords.updateSharePermissions(moduleAPIName, recordId);
			
			com.zoho.crm.sample.sharerecords.ShareRecords.revokeSharedRecord(moduleAPIName, recordId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Tags() 
	{
		try
		{
			String moduleAPIName = "Leads";
			
			Long tagId = 3477069341003l;
			
			long recordId =  3477065623115L;
					
			ArrayList<String> tagNames = new ArrayList<String>(Arrays.asList("addtag1,addtag12"));
			
			ArrayList<Long> recordIds = new ArrayList<Long>(Arrays.asList(3477065623115L, 3477069341002l));
			
			String conflictId = "3477069341003";
			
			com.zoho.crm.sample.tags.Tag.getTags(moduleAPIName);
			
			com.zoho.crm.sample.tags.Tag.createTags(moduleAPIName);
			
			com.zoho.crm.sample.tags.Tag.updateTags(moduleAPIName);
			
			com.zoho.crm.sample.tags.Tag.updateTag(moduleAPIName, tagId);
			
			com.zoho.crm.sample.tags.Tag.deleteTag(tagId);
			
			com.zoho.crm.sample.tags.Tag.mergeTags(tagId, conflictId);
			
			com.zoho.crm.sample.tags.Tag.addTagsToRecord(moduleAPIName, recordId, tagNames);
			
			com.zoho.crm.sample.tags.Tag.removeTagsFromRecord(moduleAPIName, recordId, tagNames);
			
			com.zoho.crm.sample.tags.Tag.addTagsToMultipleRecords(moduleAPIName, recordIds, tagNames);
			
			com.zoho.crm.sample.tags.Tag.removeTagsFromMultipleRecords(moduleAPIName, recordIds, tagNames);
			
			com.zoho.crm.sample.tags.Tag.getRecordCountForTag(moduleAPIName, tagId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Tax() 
	{
		try
		{
			Long taxId = 3477067293001l;
			
			ArrayList<Long> taxIds = new ArrayList<Long>(Arrays.asList(3477066860010l,3477067074031l,3477067420081l,3477067420082l));
			
			com.zoho.crm.sample.taxes.Tax.getTaxes();
			
			com.zoho.crm.sample.taxes.Tax.createTaxes();
			
			com.zoho.crm.sample.taxes.Tax.updateTaxes();
			
			com.zoho.crm.sample.taxes.Tax.deleteTaxes(taxIds);
			
			com.zoho.crm.sample.taxes.Tax.getTax(taxId);
			
			com.zoho.crm.sample.taxes.Tax.deleteTax(taxId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Territory() 
	{
		try
		{
			Long territoryId = 3477063051397l;
			
			com.zoho.crm.sample.territories.Territory.getTerritories();
			
			com.zoho.crm.sample.territories.Territory.getTerritory(territoryId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void User() 
	{
		try
		{
			Long userId = 34770610831021l;
			
			com.zoho.crm.sample.users.User.getUsers();
			
			com.zoho.crm.sample.users.User.createUser();
			
			com.zoho.crm.sample.users.User.updateUsers();
			
			com.zoho.crm.sample.users.User.getUser(userId);
			
			com.zoho.crm.sample.users.User.updateUser(userId);
			
			com.zoho.crm.sample.users.User.deleteUser(userId);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void VariableGroup() 
	{
		try
		{
			String variableGroupName = "General";
			
			Long variableGroupId = 3477063089001l;
			
			com.zoho.crm.sample.variablegroups.VariableGroup.getVariableGroups();
			
			com.zoho.crm.sample.variablegroups.VariableGroup.getVariableGroupById(variableGroupId);
			
			com.zoho.crm.sample.variablegroups.VariableGroup.getVariableGroupByAPIName(variableGroupName);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void Variable() 
	{
		try
		{
			ArrayList<Long> variableIds = new ArrayList<Long>(Arrays.asList(3477066211003l,3477066211001l));
			
			Long variableId = 3477067284005l;
			
			String variableName = "Variable55";
					
			com.zoho.crm.sample.variables.Variable.getVariables();
			
			com.zoho.crm.sample.variables.Variable.createVariables();
			
			com.zoho.crm.sample.variables.Variable.updateVariables();
			
			com.zoho.crm.sample.variables.Variable.deleteVariables(variableIds);
	
			com.zoho.crm.sample.variables.Variable.getVariableById(variableId);
			
			com.zoho.crm.sample.variables.Variable.updateVariableById(variableId);
			
			com.zoho.crm.sample.variables.Variable.deleteVariable(variableId);
			
			com.zoho.crm.sample.variables.Variable.getVariableForAPIName(variableName);
			
			com.zoho.crm.sample.variables.Variable.updateVariableByAPIName(variableName);
		}
		catch(Exception ex)
		{
			
		}
	}
	
	public static void TestUpload()
	{
		try
		{
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			
			SSLContext sslContext = SSLContext.getDefault();
			
			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
			
			CloseableHttpClient httpclient = httpClientBuilder.setSSLSocketFactory(sslConnectionSocketFactory).build();
			
			URIBuilder uriBuilder = new URIBuilder("https://www.zohoapis.com/crm/v2/files");
			
			HttpUriRequest requestObj = new HttpPost(uriBuilder.build());
			
			HttpEntityEnclosingRequestBase requestBase = (HttpEntityEnclosingRequestBase) requestObj;
			
			// requestObj.addHeader("feature", "bulk-write");
			
			// requestObj.addHeader("X-CRM-ORG", "xxxxx");
			
			requestObj.addHeader("Authorization", "Zoho-oauthtoken 1.xxxxx.xxxx");
			
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
			
			java.io.File file = new java.io.File("/Users/Leads.zip");
			
			@SuppressWarnings("resource")
			InputStream stream = new FileInputStream(file);
		
			byte[] buffer = new byte[8192];
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			int bytesRead;
			while ((bytesRead = stream.read(buffer)) != -1)
			{
			    output.write(buffer, 0, bytesRead);
			}
			
			multipartEntity.addPart("file", new ByteArrayBody(output.toByteArray(), "Leads.zip"));
			
			@SuppressWarnings("resource")
			FileInputStream stream1 = new FileInputStream(file);
		
			buffer = new byte[8192];
			output = new ByteArrayOutputStream();
			
			while ((bytesRead = stream1.read(buffer)) != -1)
			{
			    output.write(buffer, 0, bytesRead);
			}
			
			multipartEntity.addPart("file", new ByteArrayBody(output.toByteArray(), "Leads.zip"));
			
			requestBase.setEntity(multipartEntity.build());
			
			HttpResponse response = httpclient.execute(requestObj);
			
			HttpEntity responseEntity = response.getEntity();
			
			Object responseObject = EntityUtils.toString(responseEntity);
			
			String responseString = responseObject.toString();
			
			System.out.println(responseString);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}