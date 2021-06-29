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

import com.zoho.crm.api.util.ModuleFieldsHandler;

import samples.src.com.zoho.crm.api.initializer.Initialize;

public class Test
{
	public static void main(String[] args) throws Exception 
	{
		Initialize.initialize();

		moduleFieldsHandler();
		
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
	}

	public static moduleFieldsHandler() throws Exception
	{
		// Refresh Fields of a single module
		ModuleFieldsHandler.refreshFields("Leads");

		// Refresh Fields of all the modules
		ModuleFieldsHandler.refreshAllModules();

		// Delete the fields JSON file of the current user
		ModuleFieldsHandler.deleteFieldsFile();

		// Delete the fields JSON file of the all the users
		ModuleFieldsHandler.deleteAllFieldFiles();
	}
	
	public static void Attachment() throws Exception
	{
		String moduleAPIName = "Leads";
		
		Long recordId = 34770617271071l;
		
		Long attachmentId = 34770617935005l;
		
		String absoluteFilePath = "/Users/abc-XXX/Desktop/py.html";
		
		String destinationFolder = "/Users/abc-XXX/Desktop";
		
		String attachmentURL = "https://5.imimg.com/data5/KJ/UP/MY-8655440/zoho-crm-500x500.png";
		
		List<Long> attachmentIds = new ArrayList<Long>(Arrays.asList(34770617323003l, 34770617338001l, 34770617119061l));
		
		samples.src.com.zoho.crm.api.attachments.Attachment.uploadAttachments(moduleAPIName, recordId, absoluteFilePath);
		
		samples.src.com.zoho.crm.api.attachments.Attachment.getAttachments(moduleAPIName, recordId);
		
		samples.src.com.zoho.crm.api.attachments.Attachment.deleteAttachments(moduleAPIName, recordId, attachmentIds);
		
		samples.src.com.zoho.crm.api.attachments.Attachment.downloadAttachment(moduleAPIName, recordId, attachmentId, destinationFolder);
		
		samples.src.com.zoho.crm.api.attachments.Attachment.deleteAttachment(moduleAPIName, recordId, attachmentId);
		
		samples.src.com.zoho.crm.api.attachments.Attachment.uploadLinkAttachments(moduleAPIName, recordId, attachmentURL);
	}
	
	public static void BluePrint() throws Exception 
	{
		String moduleAPIName = "Leads";

		Long recordId = 34770614381002l;
		
		Long transitionId = 34770610173093l;
		
		samples.src.com.zoho.crm.api.blueprint.BluePrint.getBlueprint(moduleAPIName, recordId);
		
		samples.src.com.zoho.crm.api.blueprint.BluePrint.updateBlueprint(moduleAPIName, recordId, transitionId);
	}
	
	public static void BulkRead() throws Exception 
	{
		String moduleAPIName = "Events";
		
		Long jobId = 34770617346001l;
		
		String destinationFolder = "/Users/abc-XXX/Desktop";
		
		samples.src.com.zoho.crm.api.bulkread.BulkRead.createBulkReadJob(moduleAPIName);
		
		samples.src.com.zoho.crm.api.bulkread.BulkRead.getBulkReadJobDetails(jobId);
		
		samples.src.com.zoho.crm.api.bulkread.BulkRead.downloadResult(jobId, destinationFolder);
	}
	
	public static void BulkWrite() throws Exception 
	{
		String absoluteFilePath = "/Users/abc-XXX/Desktop";
		
		String orgID = "673573045";
		
		String moduleAPIName = "Leads";
		
		String fileId  = "3770617359003";
		
		Long jobID = 34770617341009l;
		
		String downloadUrl = "https://download-accl.zoho.com/v2/crm/673573045/bulk-write/3477061739/347706173410.zip";
		
		String destinationFolder = "/Users/abc-XXX/Desktop";
		
		samples.src.com.zoho.crm.api.bulkwrite.BulkWrite.uploadFile(orgID, absoluteFilePath);
		
		samples.src.com.zoho.crm.api.bulkwrite.BulkWrite.createBulkWriteJob(moduleAPIName, fileId);
		
		samples.src.com.zoho.crm.api.bulkwrite.BulkWrite.getBulkWriteJobDetails(jobID);
		
		samples.src.com.zoho.crm.api.bulkwrite.BulkWrite.downloadBulkWriteResult(downloadUrl, destinationFolder);
	}

	public static void ContactRoles() throws Exception 
	{
		Long contactRoleId = 34770617360003l;
		
		ArrayList<Long> contactRoleIds = new ArrayList<Long>(Arrays.asList(34770617368001l,34770617368002l,34770617368003l));
		
		samples.src.com.zoho.crm.api.contactroles.ContactRoles.getContactRoles();
		
		samples.src.com.zoho.crm.api.contactroles.ContactRoles.createContactRoles();
			
		samples.src.com.zoho.crm.api.contactroles.ContactRoles.updateContactRoles();
			
		samples.src.com.zoho.crm.api.contactroles.ContactRoles.deleteContactRoles(contactRoleIds);
		
		samples.src.com.zoho.crm.api.contactroles.ContactRoles.getContactRole(contactRoleId);
			
		samples.src.com.zoho.crm.api.contactroles.ContactRoles.updateContactRole(contactRoleId);
			
		samples.src.com.zoho.crm.api.contactroles.ContactRoles.deleteContactRole(contactRoleId);
	}
	
	public static void Currency() throws Exception 
	{
		Long currencyId = 34770617368016l;
		
		samples.src.com.zoho.crm.api.currencies.Currency.getCurrencies();
		
		samples.src.com.zoho.crm.api.currencies.Currency.addCurrencies();
		
		samples.src.com.zoho.crm.api.currencies.Currency.updateCurrencies();
		
		samples.src.com.zoho.crm.api.currencies.Currency.enableMultipleCurrencies();
		
		samples.src.com.zoho.crm.api.currencies.Currency.updateBaseCurrency();
		
		samples.src.com.zoho.crm.api.currencies.Currency.getCurrency(currencyId);
		
		samples.src.com.zoho.crm.api.currencies.Currency.updateCurrency(currencyId);
	}
	
	public static void CustomView() throws Exception 
	{
		String moduleAPIName = "Contacts";
		
		Long customID = 34770615783235l;
		
		samples.src.com.zoho.crm.api.customview.CustomView.getCustomViews(moduleAPIName);
		
		samples.src.com.zoho.crm.api.customview.CustomView.getCustomView(moduleAPIName, customID);
	}
	
	public static void Field() throws Exception 
	{
		String moduleAPIName = "Deals";
		
		Long fieldId = 34770610022011l;
		
		samples.src.com.zoho.crm.api.fields.Fields.getFields(moduleAPIName);
		
		samples.src.com.zoho.crm.api.fields.Fields.getField(moduleAPIName, fieldId);
	}
	
	public static void File() throws Exception
	{
		String destinationFolder =  "/Users/abc-XXX/Desktop";
		
		String id = "ae9c7cefa418aec1d6a5cc2d9ab35c3272bb86455474b146ac457320f1c58919";
		
		samples.src.com.zoho.crm.api.file.File.uploadFiles();
		
		samples.src.com.zoho.crm.api.file.File.getFile(id, destinationFolder);
	}
	
	public static void Layout() throws Exception
	{
		String moduleAPIName = "Calls";
		
		Long layoutId = 34770610091005l;
		
		samples.src.com.zoho.crm.api.layouts.Layout.getLayouts(moduleAPIName);
		
		samples.src.com.zoho.crm.api.layouts.Layout.getLayout(moduleAPIName, layoutId);
	}
	
	public static void Module() throws Exception
	{
		String moduleAPIName = "CustomModule";
		
		Long moduleId = 34770610485367l;
		
		samples.src.com.zoho.crm.api.modules.Modules.getModules();
		
		samples.src.com.zoho.crm.api.modules.Modules.getModule(moduleAPIName);
		
		samples.src.com.zoho.crm.api.modules.Modules.updateModuleByAPIName(moduleAPIName);
		
		samples.src.com.zoho.crm.api.modules.Modules.updateModuleById(moduleId);
	}
	
	public static void Note() throws Exception 
	{
		ArrayList<Long> notesId = new ArrayList<Long>(Arrays.asList(34770617376026l,34770617376025l));
		
		Long noteId = 34770617261027l;
		
		samples.src.com.zoho.crm.api.notes.Note.getNotes();
		
		samples.src.com.zoho.crm.api.notes.Note.createNotes();
		
		samples.src.com.zoho.crm.api.notes.Note.updateNotes();
		
		samples.src.com.zoho.crm.api.notes.Note.deleteNotes(notesId); 
		
		samples.src.com.zoho.crm.api.notes.Note.getNote(noteId);
		
		samples.src.com.zoho.crm.api.notes.Note.updateNote(noteId);
		
		samples.src.com.zoho.crm.api.notes.Note.deleteNote(noteId);
	}
	
	public static void Notification() throws Exception 
	{
		ArrayList<Long> channelIds = new ArrayList<Long>(Arrays.asList(1006800212l));
		
		samples.src.com.zoho.crm.api.notification.Notification.enableNotifications();
		
		samples.src.com.zoho.crm.api.notification.Notification.getNotificationDetails();
		
		samples.src.com.zoho.crm.api.notification.Notification.updateNotifications();
		
		samples.src.com.zoho.crm.api.notification.Notification.updateNotification();
		
		samples.src.com.zoho.crm.api.notification.Notification.disableNotifications(channelIds);
		
		samples.src.com.zoho.crm.api.notification.Notification.disableNotification();
	}
	
	public static void Organization() throws Exception 
	{
		String absoluteFilePath = "/Users/abc-XXX/Desktop";
		
		samples.src.com.zoho.crm.api.organization.Organization.getOrganization();
		
		samples.src.com.zoho.crm.api.organization.Organization.uploadOrganizationPhoto(absoluteFilePath);
	}
	
	public static void Profile() throws Exception 
	{
		Long profileId = 34770610026011l;
		
		samples.src.com.zoho.crm.api.profile.Profile.getProfiles();
		
		samples.src.com.zoho.crm.api.profile.Profile.getProfile(profileId);
	}
	
	public static void Query() throws Exception 
	{
		samples.src.com.zoho.crm.api.query.Query.getRecords();
	}
	
	public static void Record() throws Exception 
	{
		String moduleAPIName = "Contacts";
		
		long recordId = 34770617427001l;
		
		String destinationFolder =  "/Users/abc-XXX/Desktop";
		
		String absoluteFilePath = "/Users/abc-XXX/Desktop";
		
		List<Long> recordIds = new ArrayList<Long>(Arrays.asList(34770617397002l,34770615908017l,34770615908001l));
		
		String jobId = "34770617416301";
		
		samples.src.com.zoho.crm.api.record.Record.getRecord(moduleAPIName, recordId, destinationFolder);
		
		samples.src.com.zoho.crm.api.record.Record.updateRecord(moduleAPIName, recordId);
		
		samples.src.com.zoho.crm.api.record.Record.deleteRecord(moduleAPIName, recordId);
		
		samples.src.com.zoho.crm.api.record.Record.getRecords(moduleAPIName);
		
		samples.src.com.zoho.crm.api.record.Record.createRecords(moduleAPIName);
		
		samples.src.com.zoho.crm.api.record.Record.updateRecords(moduleAPIName);
		
		samples.src.com.zoho.crm.api.record.Record.deleteRecords(moduleAPIName, recordIds);
		
		samples.src.com.zoho.crm.api.record.Record.upsertRecords(moduleAPIName);
		
		samples.src.com.zoho.crm.api.record.Record.getDeletedRecords(moduleAPIName);
		
		samples.src.com.zoho.crm.api.record.Record.searchRecords(moduleAPIName);
		
		samples.src.com.zoho.crm.api.record.Record.convertLead(recordId);
		
		samples.src.com.zoho.crm.api.record.Record.getPhoto(moduleAPIName, recordId, destinationFolder);
		
		samples.src.com.zoho.crm.api.record.Record.uploadPhoto(moduleAPIName, recordId, absoluteFilePath);
		
		samples.src.com.zoho.crm.api.record.Record.deletePhoto(moduleAPIName, recordId);
		
		samples.src.com.zoho.crm.api.record.Record.massUpdateRecords(moduleAPIName);
		
		samples.src.com.zoho.crm.api.record.Record.getMassUpdateStatus(moduleAPIName, jobId);
	}
	
	public static void RelatedList() throws Exception 
	{
		String moduleAPIName = "Deals";
		
		Long relatedListId = 34770616819126l;
		
		samples.src.com.zoho.crm.api.relatedlist.RelatedList.getRelatedLists(moduleAPIName);
		
		samples.src.com.zoho.crm.api.relatedlist.RelatedList.getRelatedList(moduleAPIName, relatedListId);
	}
	
	public static void RelatedRecords() throws Exception 
	{
		String moduleAPIName = "Products";
		
		Long recordId = 34770617247012l;
		
		String relatedListAPIName = "Price_Books";
		
		Long relatedRecordId = 34770617246019l;
		
		String destinationFolder =  "/Users/abc-XXX/Desktop";
		
		List<Long> relatedListIds = new ArrayList<Long>(Arrays.asList(34770617246019l, 34770615919001l));
		
		samples.src.com.zoho.crm.api.relatedrecords.RelatedRecords.getRelatedRecords(moduleAPIName, recordId, relatedListAPIName);
			
		samples.src.com.zoho.crm.api.relatedrecords.RelatedRecords.updateRelatedRecords(moduleAPIName, recordId, relatedListAPIName);
			
		samples.src.com.zoho.crm.api.relatedrecords.RelatedRecords.delinkRecords(moduleAPIName, recordId, relatedListAPIName, relatedListIds);
		
		samples.src.com.zoho.crm.api.relatedrecords.RelatedRecords.getRelatedRecord(moduleAPIName, recordId, relatedListAPIName, relatedRecordId, destinationFolder);
		
		samples.src.com.zoho.crm.api.relatedrecords.RelatedRecords.updateRelatedRecord(moduleAPIName, recordId, relatedListAPIName, relatedRecordId);
		
		samples.src.com.zoho.crm.api.relatedrecords.RelatedRecords.delinkRecord(moduleAPIName, recordId, relatedListAPIName, relatedRecordId);
	}
	
	public static void Role() throws Exception 
	{
		Long roleId = 34770610026008l;
		
		samples.src.com.zoho.crm.api.role.Role.getRoles();
		
		samples.src.com.zoho.crm.api.role.Role.getRole(roleId);
	}
	
	public static void ShareRecords() throws Exception 
	{
		String moduleAPIName = "Leads";
		
		long recordId = 34770615623115L;
		
		samples.src.com.zoho.crm.api.sharerecords.ShareRecords.getSharedRecordDetails(moduleAPIName, recordId);
		
		samples.src.com.zoho.crm.api.sharerecords.ShareRecords.shareRecord(moduleAPIName, recordId);
		
		samples.src.com.zoho.crm.api.sharerecords.ShareRecords.updateSharePermissions(moduleAPIName, recordId);
		
		samples.src.com.zoho.crm.api.sharerecords.ShareRecords.revokeSharedRecord(moduleAPIName, recordId);
	}
	
	public static void Tags() throws Exception 
	{
		String moduleAPIName = "Leads";
		
		Long tagId = 34770617023001l;
		
		long recordId =  34770615623115L;
				
		ArrayList<String> tagNames = new ArrayList<String>(Arrays.asList("addtag1,addtag12"));
		
		ArrayList<Long> recordIds = new ArrayList<Long>(Arrays.asList(34770615623115l, 34770617421029l));
		
		String conflictId = "34770616947003";
		
		samples.src.com.zoho.crm.api.tags.Tag.getTags(moduleAPIName);
		
		samples.src.com.zoho.crm.api.tags.Tag.createTags(moduleAPIName);
		
		samples.src.com.zoho.crm.api.tags.Tag.updateTags(moduleAPIName);
		
		samples.src.com.zoho.crm.api.tags.Tag.updateTag(moduleAPIName, tagId);
		
		samples.src.com.zoho.crm.api.tags.Tag.deleteTag(tagId);
		
		samples.src.com.zoho.crm.api.tags.Tag.mergeTags(tagId, conflictId);
		
		samples.src.com.zoho.crm.api.tags.Tag.addTagsToRecord(moduleAPIName, recordId, tagNames);
		
		samples.src.com.zoho.crm.api.tags.Tag.removeTagsFromRecord(moduleAPIName, recordId, tagNames);
		
		samples.src.com.zoho.crm.api.tags.Tag.addTagsToMultipleRecords(moduleAPIName, recordIds, tagNames);
		
		samples.src.com.zoho.crm.api.tags.Tag.removeTagsFromMultipleRecords(moduleAPIName, recordIds, tagNames);
		
		samples.src.com.zoho.crm.api.tags.Tag.getRecordCountForTag(moduleAPIName, tagId);
	}
	
	public static void Tax() throws Exception 
	{
		Long taxId = 34770617293001l;
		
		ArrayList<Long> taxIds = new ArrayList<Long>(Arrays.asList(34770616860010l,34770617074031l,34770617420081l,34770617420082l));
		
		samples.src.com.zoho.crm.api.taxes.Tax.getTaxes();
		
		samples.src.com.zoho.crm.api.taxes.Tax.createTaxes();
		
		samples.src.com.zoho.crm.api.taxes.Tax.updateTaxes();
		
		samples.src.com.zoho.crm.api.taxes.Tax.deleteTaxes(taxIds);
		
		samples.src.com.zoho.crm.api.taxes.Tax.getTax(taxId);
		
		samples.src.com.zoho.crm.api.taxes.Tax.deleteTax(taxId);
	}
	
	public static void Territory() throws Exception 
	{
		Long territoryId = 34770613051397l;
		
		samples.src.com.zoho.crm.api.territories.Territory.getTerritories();
		
		samples.src.com.zoho.crm.api.territories.Territory.getTerritory(territoryId);
	}
	
	public static void User() throws Exception 
	{
		Long userId = 34770617439001l;
		
		samples.src.com.zoho.crm.api.users.User.getUsers();
		
		samples.src.com.zoho.crm.api.users.User.createUser();
		
		samples.src.com.zoho.crm.api.users.User.updateUsers();
		
		samples.src.com.zoho.crm.api.users.User.getUser(34770617941006l);
		
		samples.src.com.zoho.crm.api.users.User.updateUser(userId);
		
		samples.src.com.zoho.crm.api.users.User.deleteUser(userId);
	}
	
	public static void VariableGroup() throws Exception 
	{
		String variableGroupName = "General";
		
		Long variableGroupId = 34770613089001l;
		
		samples.src.com.zoho.crm.api.variablegroups.VariableGroup.getVariableGroups();
		
		samples.src.com.zoho.crm.api.variablegroups.VariableGroup.getVariableGroupById(variableGroupId);
		
		samples.src.com.zoho.crm.api.variablegroups.VariableGroup.getVariableGroupByAPIName(variableGroupName);
	}
	
	public static void Variable() throws Exception 
	{
		ArrayList<Long> variableIds = new ArrayList<Long>(Arrays.asList(34770616211003l,34770616211001l));
		
		Long variableId = 34770617284005l;
		
		String variableName = "Variable55";
				
		samples.src.com.zoho.crm.api.variables.Variable.getVariables();
		
		samples.src.com.zoho.crm.api.variables.Variable.createVariables();
		
		samples.src.com.zoho.crm.api.variables.Variable.updateVariables();
		
		samples.src.com.zoho.crm.api.variables.Variable.deleteVariables(variableIds);

		samples.src.com.zoho.crm.api.variables.Variable.getVariableById(variableId);
		
		samples.src.com.zoho.crm.api.variables.Variable.updateVariableById(variableId);
		
		samples.src.com.zoho.crm.api.variables.Variable.deleteVariable(variableId);
		
		samples.src.com.zoho.crm.api.variables.Variable.getVariableForAPIName(variableName);
		
		samples.src.com.zoho.crm.api.variables.Variable.updateVariableByAPIName(variableName);
	}
}