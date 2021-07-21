package com.zoho.crm.api.relatedrecords;

import com.zoho.crm.api.Header;
import com.zoho.crm.api.HeaderMap;
import com.zoho.crm.api.Param;
import com.zoho.crm.api.ParameterMap;
import com.zoho.crm.api.exception.SDKException;
import com.zoho.crm.api.util.APIResponse;
import com.zoho.crm.api.util.CommonAPIHandler;
import com.zoho.crm.api.util.Utility;
import java.time.OffsetDateTime;
import com.zoho.crm.api.util.Constants;

public class RelatedRecordsOperations
{
	private String moduleAPIName;

	private String relatedListAPIName;

	private String xExternal;

	/**
	 * Creates an instance of RelatedRecordsOperations with the given parameters
	 * @param relatedListAPIName A String representing the relatedListAPIName
	 * @param moduleAPIName A String representing the moduleAPIName
	 * @param xExternal A String representing the xExternal
	 */
	public RelatedRecordsOperations(String relatedListAPIName, String moduleAPIName, String xExternal)
	{
		 this.relatedListAPIName = relatedListAPIName;

		 this.moduleAPIName = moduleAPIName;

		 this.xExternal = xExternal;


	}

	/**
	 * The method to get related records
	 * @param recordId A Long representing the recordId
	 * @param paramInstance An instance of ParameterMap
	 * @param headerInstance An instance of HeaderMap
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getRelatedRecords(Long recordId, ParameterMap paramInstance, HeaderMap headerInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(recordId.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordsHeader"),  this.xExternal);

		handlerInstance.setParam(paramInstance);

		handlerInstance.setHeader(headerInstance);

		Utility.getRelatedLists( this.relatedListAPIName,  this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to update related records
	 * @param recordId A Long representing the recordId
	 * @param request An instance of BodyWrapper
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> updateRelatedRecords(Long recordId, BodyWrapper request) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(recordId.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_PUT);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_UPDATE);

		handlerInstance.setContentType("application/json");

		handlerInstance.setRequest(request);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.UpdateRelatedRecordsHeader"),  this.xExternal);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.UpdateRelatedRecordsHeader"),  this.xExternal);

		Utility.getRelatedLists( this.relatedListAPIName,  this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}

	/**
	 * The method to delink records
	 * @param recordId A Long representing the recordId
	 * @param paramInstance An instance of ParameterMap
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> delinkRecords(Long recordId, ParameterMap paramInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(recordId.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_DELETE);

		handlerInstance.setCategoryMethod(Constants.REQUEST_METHOD_DELETE);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.DelinkRecordsHeader"),  this.xExternal);

		handlerInstance.setParam(paramInstance);

		Utility.getFields( this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}

	/**
	 * The method to get related records using external id
	 * @param externalValue A String representing the externalValue
	 * @param paramInstance An instance of ParameterMap
	 * @param headerInstance An instance of HeaderMap
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getRelatedRecordsUsingExternalId(String externalValue, ParameterMap paramInstance, HeaderMap headerInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(externalValue.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordsUsingExternalIDHeader"),  this.xExternal);

		handlerInstance.setParam(paramInstance);

		handlerInstance.setHeader(headerInstance);

		Utility.getRelatedLists( this.relatedListAPIName,  this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to update related records using external id
	 * @param externalValue A String representing the externalValue
	 * @param request An instance of BodyWrapper
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> updateRelatedRecordsUsingExternalId(String externalValue, BodyWrapper request) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(externalValue.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_PUT);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_UPDATE);

		handlerInstance.setContentType("application/json");

		handlerInstance.setRequest(request);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.UpdateRelatedRecordsUsingExternalIDHeader"),  this.xExternal);

		Utility.getRelatedLists( this.relatedListAPIName,  this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}

	/**
	 * The method to delete related records using external id
	 * @param externalValue A String representing the externalValue
	 * @param paramInstance An instance of ParameterMap
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> deleteRelatedRecordsUsingExternalId(String externalValue, ParameterMap paramInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(externalValue.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_DELETE);

		handlerInstance.setCategoryMethod(Constants.REQUEST_METHOD_DELETE);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.DeleteRelatedRecordsUsingExternalIDHeader"),  this.xExternal);

		handlerInstance.setParam(paramInstance);

		Utility.getRelatedLists( this.relatedListAPIName,  this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}

	/**
	 * The method to get related record
	 * @param relatedRecordId A Long representing the relatedRecordId
	 * @param recordId A Long representing the recordId
	 * @param headerInstance An instance of HeaderMap
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getRelatedRecord(Long relatedRecordId, Long recordId, HeaderMap headerInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(recordId.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(relatedRecordId.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordHeader"),  this.xExternal);

		handlerInstance.setHeader(headerInstance);

		Utility.getRelatedLists( this.relatedListAPIName,  this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to update related record
	 * @param relatedRecordId A Long representing the relatedRecordId
	 * @param recordId A Long representing the recordId
	 * @param request An instance of BodyWrapper
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> updateRelatedRecord(Long relatedRecordId, Long recordId, BodyWrapper request) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(recordId.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(relatedRecordId.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_PUT);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_UPDATE);

		handlerInstance.setContentType("application/json");

		handlerInstance.setRequest(request);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.UpdateRelatedRecordHeader"),  this.xExternal);

		Utility.getRelatedLists( this.relatedListAPIName,  this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}

	/**
	 * The method to delink record
	 * @param relatedRecordId A Long representing the relatedRecordId
	 * @param recordId A Long representing the recordId
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> delinkRecord(Long relatedRecordId, Long recordId) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(recordId.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(relatedRecordId.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_DELETE);

		handlerInstance.setCategoryMethod(Constants.REQUEST_METHOD_DELETE);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.DelinkRecordHeader"),  this.xExternal);

		Utility.getFields( this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}

	/**
	 * The method to get related record using external id
	 * @param externalFieldValue A String representing the externalFieldValue
	 * @param externalValue A String representing the externalValue
	 * @param headerInstance An instance of HeaderMap
	 * @return An instance of APIResponse<ResponseHandler>
	 * @throws SDKException
	 */
	public APIResponse<ResponseHandler> getRelatedRecordUsingExternalId(String externalFieldValue, String externalValue, HeaderMap headerInstance) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(externalValue.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(externalFieldValue.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_GET);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_READ);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordUsingExternalIDHeader"),  this.xExternal);

		handlerInstance.setHeader(headerInstance);

		Utility.getRelatedLists( this.relatedListAPIName,  this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ResponseHandler.class, "application/json");

	}

	/**
	 * The method to update related record using external id
	 * @param externalFieldValue A String representing the externalFieldValue
	 * @param externalValue A String representing the externalValue
	 * @param request An instance of BodyWrapper
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> updateRelatedRecordUsingExternalId(String externalFieldValue, String externalValue, BodyWrapper request) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(externalValue.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(externalFieldValue.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_PUT);

		handlerInstance.setCategoryMethod(Constants.REQUEST_CATEGORY_UPDATE);

		handlerInstance.setContentType("application/json");

		handlerInstance.setRequest(request);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.UpdateRelatedRecordUsingExternalIDHeader"),  this.xExternal);

		Utility.getRelatedLists( this.relatedListAPIName,  this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}

	/**
	 * The method to delete related record using external id
	 * @param externalFieldValue A String representing the externalFieldValue
	 * @param externalValue A String representing the externalValue
	 * @return An instance of APIResponse<ActionHandler>
	 * @throws SDKException
	 */
	public APIResponse<ActionHandler> deleteRelatedRecordUsingExternalId(String externalFieldValue, String externalValue) throws SDKException
	{
		CommonAPIHandler handlerInstance = new CommonAPIHandler();

		String apiPath = new String();

		apiPath = apiPath.concat("/crm/v2/");

		apiPath = apiPath.concat( this.moduleAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(externalValue.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat( this.relatedListAPIName.toString());

		apiPath = apiPath.concat("/");

		apiPath = apiPath.concat(externalFieldValue.toString());

		handlerInstance.setAPIPath(apiPath);

		handlerInstance.setHttpMethod(Constants.REQUEST_METHOD_DELETE);

		handlerInstance.setCategoryMethod(Constants.REQUEST_METHOD_DELETE);

		handlerInstance.addHeader(new Header<String>("X-EXTERNAL", "com.zoho.crm.api.RelatedRecords.DeleteRelatedRecordUsingExternalIDHeader"),  this.xExternal);

		Utility.getRelatedLists( this.relatedListAPIName,  this.moduleAPIName, handlerInstance);

		return handlerInstance.apiCall(ActionHandler.class, "application/json");

	}
	public static class GetRelatedRecordsHeader
	{
		public static final Header<OffsetDateTime> IF_MODIFIED_SINCE = new Header<OffsetDateTime>("If-Modified-Since", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordsHeader");

	}

	public static class GetRelatedRecordsParam
	{
		public static final Param<Integer> PAGE = new Param<Integer>("page", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordsParam");

		public static final Param<Integer> PER_PAGE = new Param<Integer>("per_page", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordsParam");

	}

	public static class UpdateRelatedRecordsHeader
	{
	}

	public static class DelinkRecordsHeader
	{
	}

	public static class DelinkRecordsParam
	{
		public static final Param<String> IDS = new Param<String>("ids", "com.zoho.crm.api.RelatedRecords.DelinkRecordsParam");

	}

	public static class GetRelatedRecordsUsingExternalIDHeader
	{
		public static final Header<OffsetDateTime> IF_MODIFIED_SINCE = new Header<OffsetDateTime>("If-Modified-Since", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordsUsingExternalIDHeader");

	}

	public static class GetRelatedRecordsUsingExternalIDParam
	{
		public static final Param<Integer> PAGE = new Param<Integer>("page", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordsUsingExternalIDParam");

		public static final Param<Integer> PER_PAGE = new Param<Integer>("per_page", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordsUsingExternalIDParam");

	}

	public static class UpdateRelatedRecordsUsingExternalIDHeader
	{
	}

	public static class DeleteRelatedRecordsUsingExternalIDHeader
	{
	}

	public static class DeleteRelatedRecordsUsingExternalIDParam
	{
		public static final Param<String> IDS = new Param<String>("ids", "com.zoho.crm.api.RelatedRecords.DeleteRelatedRecordsUsingExternalIDParam");

	}

	public static class GetRelatedRecordHeader
	{
		public static final Header<OffsetDateTime> IF_MODIFIED_SINCE = new Header<OffsetDateTime>("If-Modified-Since", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordHeader");

	}

	public static class UpdateRelatedRecordHeader
	{
	}

	public static class DelinkRecordHeader
	{
	}

	public static class GetRelatedRecordUsingExternalIDHeader
	{
		public static final Header<OffsetDateTime> IF_MODIFIED_SINCE = new Header<OffsetDateTime>("If-Modified-Since", "com.zoho.crm.api.RelatedRecords.GetRelatedRecordUsingExternalIDHeader");

	}

	public static class UpdateRelatedRecordUsingExternalIDHeader
	{
	}

	public static class DeleteRelatedRecordUsingExternalIDHeader
	{
	}
}