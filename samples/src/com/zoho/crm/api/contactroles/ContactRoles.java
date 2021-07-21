package samples.src.com.zoho.crm.api.contactroles;

import java.lang.reflect.Field;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import com.zoho.crm.api.contactroles.ActionResponse;

import com.zoho.crm.api.ParameterMap;

import com.zoho.crm.api.contactroles.APIException;

import com.zoho.crm.api.contactroles.ActionHandler;

import com.zoho.crm.api.contactroles.ActionWrapper;

import com.zoho.crm.api.contactroles.BodyWrapper;

import com.zoho.crm.api.contactroles.ContactRole;

import com.zoho.crm.api.contactroles.ContactRoleWrapper;

import com.zoho.crm.api.contactroles.ContactRolesOperations;

import com.zoho.crm.api.contactroles.ContactRolesOperations.DeleteContactRolesParam;

import com.zoho.crm.api.contactroles.ContactRolesOperations.GetAllContactRolesOfDealParam;

import com.zoho.crm.api.contactroles.RecordActionHandler;

import com.zoho.crm.api.contactroles.RecordActionWrapper;

import com.zoho.crm.api.contactroles.RecordBodyWrapper;

import com.zoho.crm.api.contactroles.RecordResponseHandler;

import com.zoho.crm.api.contactroles.RecordResponseWrapper;

import com.zoho.crm.api.contactroles.ResponseHandler;

import com.zoho.crm.api.contactroles.ResponseWrapper;

import com.zoho.crm.api.contactroles.SuccessResponse;

import com.zoho.crm.api.exception.SDKException;

import com.zoho.crm.api.record.Info;

import com.zoho.crm.api.users.User;

import com.zoho.crm.api.util.APIResponse;

import com.zoho.crm.api.util.Model;

public class ContactRoles
{
	/**
	 * <h3> Get Contact Roles </h3>
	 * This method is used to get all the Contact Roles and print the response.
	 * @throws Exception
	 */
	public static void getContactRoles() throws Exception
	{
		//Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();
		
		//Call getContactRoles method
		APIResponse<ResponseHandler> response = contactRolesOperations.getContactRoles();
		
		if(response != null)
		{
			//Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());
			
			if(Arrays.asList(204,304).contains(response.getStatusCode()))
			{
				System.out.println(response.getStatusCode() == 204? "No Content" : "Not Modified");
				return;
			}
			
			//Check if expected response is received
			if(response.isExpected())
			{
				//Get object from response
				ResponseHandler responseHandler = response.getObject();
				
				if(responseHandler instanceof ResponseWrapper)
				{
					//Get the received ResponseWrapper instance
					ResponseWrapper responseWrapper = (ResponseWrapper) responseHandler;
					
					//Get the list of obtained ContactRole instances
					List<ContactRole> contactRoles = responseWrapper.getContactRoles();
					
					for(ContactRole contactRole : contactRoles)
					{
						//Get the ID of each ContactRole
						System.out.println("ContactRole ID: " + contactRole.getId());
						
						//Get the name of each ContactRole
						System.out.println("ContactRole Name: " + contactRole.getName());
						
						//Get the sequence number each ContactRole
						System.out.println("ContactRole SequenceNumber: " + contactRole.getSequenceNumber());
					}
				}
				//Check if the request returned an exception
				else if(responseHandler instanceof APIException)
				{
					//Get the received APIException instance
					APIException exception = (APIException) responseHandler;
					
					//Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());
					
					//Get the Code
					System.out.println("Code: " + exception.getCode().getValue());
					
					System.out.println("Details: " );
					
					//Get the details map
					for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						//Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}
					
					//Get the Message
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else if(response.getStatusCode() != 204 )
			{//If response is not as expected
				
				//Get model object from response
				Model responseObject = response.getModel();
				
				//Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();
				
				//Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();
				
				for(Field field : fields)
				{
					field.setAccessible(true);
					
					//Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}
	
	/**
	 * <h3> Create Contact Roles </h3>
	 * This method is used to create Contact Roles and print the response.
	 * @throws Exception
	 */
	public static void createContactRoles() throws Exception
	{
		//Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();
		
		//Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();
		
		//List of ContactRole instances
		List<ContactRole> contactRoles = new ArrayList<ContactRole>();
		
		for(int i = 1; i <= 5; i++)
		{
			//Get instance of ContactRole Class
			ContactRole contactRole = new ContactRole();
			
			//Set name of the Contact Role
			contactRole.setName("contactRole12" + i);
			
			//Set sequence number of the Contact Role
			contactRole.setSequenceNumber(i);
			
			//Add ContactRole instance to the list
			contactRoles.add(contactRole);
		}
		
		//Set the list to contactRoles in BodyWrapper instance
		bodyWrapper.setContactRoles(contactRoles);
		
		//Call createContactRoles method that takes BodyWrapper instance as parameter 
		APIResponse<ActionHandler> response = contactRolesOperations.createContactRoles(bodyWrapper);
		
		if(response != null)
		{
			//Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());
			
			//Check if expected response is received
			if(response.isExpected())
			{
				//Get object from response
				ActionHandler actionHandler = response.getObject();
				
				if(actionHandler instanceof ActionWrapper)
				{
					//Get the received ActionWrapper instance
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					
					//Get the list of obtained ActionResponse instances
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();
					
					for(ActionResponse actionResponse : actionResponses)
					{
						//Check if the request is successful
						if(actionResponse instanceof SuccessResponse)
						{
							//Get the received SuccessResponse instance
							SuccessResponse successResponse = (SuccessResponse)actionResponse;
							
							//Get the Status
							System.out.println("Status: " + successResponse.getStatus().getValue());
							
							//Get the Code
							System.out.println("Code: " + successResponse.getCode().getValue());
							
							System.out.println("Details: " );
							
							//Get the details map
							for(Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							
							//Get the Message
							System.out.println("Message: " + successResponse.getMessage().getValue());
						}
						//Check if the request returned an exception
						else if(actionResponse instanceof APIException)
						{
							//Get the received APIException instance
							APIException exception = (APIException) actionResponse;
							
							//Get the Status
							System.out.println("Status: " + exception.getStatus().getValue());
							
							//Get the Code
							System.out.println("Code: " + exception.getCode().getValue());
							
							System.out.println("Details: " );
							
							//Get the details map
							for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							
							//Get the Message
							System.out.println("Message: " + exception.getMessage().getValue());
						}
					}
				}
				//Check if the request returned an exception
				else if(actionHandler instanceof APIException)
				{
					//Get the received APIException instance
					APIException exception = (APIException) actionHandler;
					
					//Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());
					
					//Get the Code
					System.out.println("Code: " + exception.getCode().getValue());
					
					System.out.println("Details: " );
					
					//Get the details map
					for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						//Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}
					
					//Get the Message
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{//If response is not as expected
				
				//Get model object from response
				Model responseObject = response.getModel();
				
				//Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();
				
				//Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();
				
				for(Field field : fields)
				{
					//Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}
	
	/**
	 * <h3> Update Contact Roles </h3>
	 * This method is used to update Contact Roles and print the response.
	 * @throws Exception
	 */
	public static void updateContactRoles() throws Exception
	{
		//Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();
		
		//Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();
		
		//List of ContactRole instances
		List<ContactRole> contactRolesList = new ArrayList<ContactRole>();

		//Get instance of ContactRole Class
		ContactRole cr1 = new ContactRole();
		
		//Set ID to the ContactRole instance
		cr1.setId(347706110803003l);
		
		//Set name to the ContactRole instance
		cr1.setName("Edited1");
		
		//Add ContactRole instance to the list
		contactRolesList.add(cr1);
		
		//Get instance of ContactRole Class
		ContactRole cr2 = new ContactRole();
		
		//Set ID to the ContactRole instance
		cr2.setId(347706110803001l);
		
		cr2.setSequenceNumber(1);
		
		//Set name to the ContactRole instance
		cr2.setName("Edited2");
		
		//Add ContactRole instance to the list
		contactRolesList.add(cr2);
		
		//Set the list to contactRoles in BodyWrapper instance
		bodyWrapper.setContactRoles(contactRolesList);
		
		//Call updateContactRoles method that takes BodyWrapper instance as parameter
		APIResponse<ActionHandler> response = contactRolesOperations.updateContactRoles(bodyWrapper);
		
		if(response != null)
		{
			//Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());
			
			//Check if expected response is received
			if(response.isExpected())
			{
				//Get object from response
				ActionHandler actionHandler = response.getObject();
				
				if(actionHandler instanceof ActionWrapper)
				{
					//Get the received ActionWrapper instance
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					
					//Get the list of obtained ActionResponse instances
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();
					
					for(ActionResponse actionResponse : actionResponses)
					{
						//Check if the request is successful
						if(actionResponse instanceof SuccessResponse)
						{
							//Get the received SuccessResponse instance
							SuccessResponse successResponse = (SuccessResponse)actionResponse;
							
							//Get the Status
							System.out.println("Status: " + successResponse.getStatus().getValue());
							
							//Get the Code
							System.out.println("Code: " + successResponse.getCode().getValue());
							
							System.out.println("Details: " );
							
							//Get the details map
							for(Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							
							//Get the Message
							System.out.println("Message: " + successResponse.getMessage().getValue());
						}
						//Check if the request returned an exception
						else if(actionResponse instanceof APIException)
						{
							//Get the received APIException instance
							APIException exception = (APIException) actionResponse;
							
							//Get the Status
							System.out.println("Status: " + exception.getStatus().getValue());
							
							//Get the Code
							System.out.println("Code: " + exception.getCode().getValue());
							
							System.out.println("Details: " );
							
							//Get the details map
							for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							
							//Get the Message
							System.out.println("Message: " + exception.getMessage().getValue());
						}
					}
				}
				//Check if the request returned an exception
				else if(actionHandler instanceof APIException)
				{
					//Get the received APIException instance
					APIException exception = (APIException) actionHandler;
					
					//Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());
					
					//Get the Code
					System.out.println("Code: " + exception.getCode().getValue());
					
					System.out.println("Details: " );
					
					//Get the details map
					for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						//Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}
					
					//Get the Message
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{//If response is not as expected
				
				//Get model object from response
				Model responseObject = response.getModel();
				
				//Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();
				
				//Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();
				
				for(Field field : fields)
				{
					//Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}	
		}
	}
	
	/**
	 * <h3> Delete Contact Roles </h3>
	 * This method is used to delete Contact Roles and print the response.
	 * @param contactRoleIds - The List of ContactRole IDs to be deleted.
	 * @throws Exception
	 */
	public static void deleteContactRoles(List<String> contactRoleIds) throws Exception
	{
		//example
		//ArrayList<Long> contactRoleIds = new ArrayList<Long>(Arrays.asList(34770615208001l,34770615208002l));
		
		//Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();
		
		//Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();
		
		for( String id : contactRoleIds)
		{
			paramInstance.add(DeleteContactRolesParam.IDS, id);
		}
		
		//Call deleteContactRoles method that takes paramInstance as parameter 
		APIResponse<ActionHandler> response = contactRolesOperations.deleteContactRoles(paramInstance);
		
		if(response != null)
		{
			//Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());
			
			//Check if expected response is received
			if(response.isExpected())
			{
				//Get object from response
				ActionHandler actionHandler = response.getObject();
				
				if(actionHandler instanceof ActionWrapper)
				{
					//Get the received ActionWrapper instance
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					
					//Get the list of obtained ContactRole instances
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();
					
					for(ActionResponse actionResponse : actionResponses)
					{
						//Check if the request is successful
						if(actionResponse instanceof SuccessResponse)
						{
							//Get the received SuccessResponse instance
							SuccessResponse successResponse = (SuccessResponse)actionResponse;
							
							//Get the Status
							System.out.println("Status: " + successResponse.getStatus().getValue());
							
							//Get the Code
							System.out.println("Code: " + successResponse.getCode().getValue());
							
							System.out.println("Details: " );
							
							//Get the details map
							for(Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							
							//Get the Message
							System.out.println("Message: " + successResponse.getMessage().getValue());
						}
						//Check if the request returned an exception
						else if(actionResponse instanceof APIException)
						{
							//Get the received APIException instance
							APIException exception = (APIException) actionResponse;
							
							//Get the Status
							System.out.println("Status: " + exception.getStatus().getValue());
							
							//Get the Code
							System.out.println("Code: " + exception.getCode().getValue());
							
							System.out.println("Details: " );
							
							//Get the details map
							for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							
							//Get the Message
							System.out.println("Message: " + exception.getMessage().getValue());
						}
					}
				}
				//Check if the request returned an exception
				else if(actionHandler instanceof APIException)
				{
					//Get the received APIException instance
					APIException exception = (APIException) actionHandler;
					
					//Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());
					
					//Get the Code
					System.out.println("Code: " + exception.getCode().getValue());
					
					System.out.println("Details: " );
					
					//Get the details map
					for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						//Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}
					
					//Get the Message
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{//If response is not as expected
				
				//Get model object from response
				Model responseObject = response.getModel();
				
				//Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();
				
				//Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();
				
				for(Field field : fields)
				{
					//Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}
	
	/**
	 * <h3> Get Contact Role </h3>
	 * This method is used to get single Contact Role with ID and print the response.
	 * @param contactRoleId - The ID of the ContactRole to be obtained
	 * @throws Exception
	 */
	public static void getContactRole(Long contactRoleId) throws Exception
	{
		//example
		//Long contactRoleId = 34770615177004l;
		
		//Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();
		
		//Call getContactRole method that takes contactRoleId as parameter
		APIResponse<ResponseHandler> response = contactRolesOperations.getContactRole(contactRoleId);
		
		if(response != null)
		{
			//Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());
			
			if(Arrays.asList(204,304).contains(response.getStatusCode()))
			{
				System.out.println(response.getStatusCode() == 204? "No Content" : "Not Modified");
				return;
			}
			
			//Check if expected response is received
			if(response.isExpected())
			{
				//Get object from response
				ResponseHandler responseHandler = response.getObject();
				
				if(responseHandler instanceof ResponseWrapper)
				{
					//Get the received ResponseWrapper instance
					ResponseWrapper responseWrapper = (ResponseWrapper) responseHandler;
					
					//Get the list of obtained ContactRole instances
					List<ContactRole> contactRoles = responseWrapper.getContactRoles();
					
					for(ContactRole contactRole : contactRoles)
					{
						//Get the ID of each ContactRole
						System.out.println("ContactRole ID: " + contactRole.getId());
						
						//Get the name of each ContactRole
						System.out.println("ContactRole Name: " + contactRole.getName());
						
						//Get the sequence number each ContactRole
						System.out.println("ContactRole SequenceNumber: " + contactRole.getSequenceNumber());
					}
				}
				//Check if the request returned an exception
				else if(responseHandler instanceof APIException)
				{
					//Get the received APIException instance
					APIException exception = (APIException) responseHandler;
					
					//Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());
					
					//Get the Code
					System.out.println("Code: " + exception.getCode().getValue());
					
					System.out.println("Details: " );
					
					//Get the details map
					for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						//Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}
					
					//Get the Message
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{//If response is not as expected
				
				//Get model object from response
				Model responseObject = response.getModel();
				
				//Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();
				
				//Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();
				
				for(Field field : fields)
				{
					//Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}
	
	/**
	 * <h3> Update Contact Role </h3>
	 * This method is used to update single Contact Role with ID and print the response.
	 * @param contactRoleId The ID of the ContactRole to be updated
	 * @throws Exception
	 */
	public static void updateContactRole(Long contactRoleId) throws Exception
	{
		//ID of the ContactRole to be updated
		//Long contactRoleId = 5255085067923l;
		
		//Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();
		
		//Get instance of BodyWrapper Class that will contain the request body
		BodyWrapper bodyWrapper = new BodyWrapper();
		
		//List of ContactRole instances
		List<ContactRole> contactRolesList = new ArrayList<ContactRole>();

		//Get instance of ContactRole Class
		ContactRole cr1 = new ContactRole();
		
		//Set name to the ContactRole instance
		cr1.setName("contactRole4");
		
		//Set sequence number to the ContactRole instance
		cr1.setSequenceNumber(2);
		
		//Add ContactRole instance to the list
		contactRolesList.add(cr1);
		
		//Set the list to contactRoles in BodyWrapper instance
		bodyWrapper.setContactRoles(contactRolesList);
		
		//Call updateContactRole method that takes contactRoleId and BodyWrapper instance as parameters
		APIResponse<ActionHandler> response = contactRolesOperations.updateContactRole(contactRoleId, bodyWrapper);
		
		if(response != null)
		{
			//Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());
			
			//Check if expected response is received
			if(response.isExpected())
			{
				//Get object from response
				ActionHandler actionHandler = response.getObject();
				
				if(actionHandler instanceof ActionWrapper)
				{
					//Get the received ActionWrapper instance
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					
					//Get the list of obtained ActionResponse instances
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();
					
					for(ActionResponse actionResponse : actionResponses)
					{
						//Check if the request is successful
						if(actionResponse instanceof SuccessResponse)
						{
							//Get the received SuccessResponse instance
							SuccessResponse successResponse = (SuccessResponse)actionResponse;
							
							//Get the Status
							System.out.println("Status: " + successResponse.getStatus().getValue());
							
							//Get the Code
							System.out.println("Code: " + successResponse.getCode().getValue());
							
							System.out.println("Details: " );
							
							//Get the details map
							for(Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							
							//Get the Message
							System.out.println("Message: " + successResponse.getMessage().getValue());
						}
						//Check if the request returned an exception
						else if(actionResponse instanceof APIException)
						{
							//Get the received APIException instance
							APIException exception = (APIException) actionResponse;
							
							//Get the Status
							System.out.println("Status: " + exception.getStatus().getValue());
							
							//Get the Code
							System.out.println("Code: " + exception.getCode().getValue());
							
							System.out.println("Details: " );
							
							//Get the details map
							for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							
							//Get the Message
							System.out.println("Message: " + exception.getMessage().getValue());
						}
					}
				}
				//Check if the request returned an exception
				else if(actionHandler instanceof APIException)
				{
					//Get the received APIException instance
					APIException exception = (APIException) actionHandler;
					
					//Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());
					
					//Get the Code
					System.out.println("Code: " + exception.getCode().getValue());
					
					System.out.println("Details: " );
					
					//Get the details map
					for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						//Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}
					
					//Get the Message
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{//If response is not as expected
				
				//Get model object from response
				Model responseObject = response.getModel();
				
				//Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();
				
				//Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();
				
				for(Field field : fields)
				{
					//Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}
	
	/**
	 * <h3> Delete Contact Role </h3>
	 * This method is used to delete single Contact Role with ID and print the response.
	 * @param contactRoleId ID of the ContactRole to be deleted
	 * @throws Exception
	 */
	public static void deleteContactRole(Long contactRoleId) throws Exception
	{
		//ID of the ContactRole to be updated
		//Long contactRoleId = 5255085067923l;
		
		//Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();
		
		//Call deleteContactRole which takes contactRoleId as parameter
		APIResponse<ActionHandler> response = contactRolesOperations.deleteContactRole(contactRoleId);
		
		if(response != null)
		{
			//Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());
			
			//Check if expected response is received
			if(response.isExpected())
			{
				//Get object from response
				ActionHandler actionHandler = response.getObject();
				
				if(actionHandler instanceof ActionWrapper)
				{
					//Get the received ActionWrapper instance
					ActionWrapper actionWrapper = (ActionWrapper) actionHandler;
					
					//Get the list of obtained ActionResponse instances
					List<ActionResponse> actionResponses = actionWrapper.getContactRoles();
					
					for(ActionResponse actionResponse : actionResponses)
					{
						//Check if the request is successful
						if(actionResponse instanceof SuccessResponse)
						{
							//Get the received SuccessResponse instance
							SuccessResponse successResponse = (SuccessResponse)actionResponse;
							
							//Get the Status
							System.out.println("Status: " + successResponse.getStatus().getValue());
							
							//Get the Code
							System.out.println("Code: " + successResponse.getCode().getValue());
							
							System.out.println("Details: " );
							
							//Get the details map
							for(Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							
							//Get the Message
							System.out.println("Message: " + successResponse.getMessage().getValue());
						}
						//Check if the request returned an exception
						else if(actionResponse instanceof APIException)
						{
							//Get the received APIException instance
							APIException exception = (APIException) actionResponse;
							
							//Get the Status
							System.out.println("Status: " + exception.getStatus().getValue());
							
							//Get the Code
							System.out.println("Code: " + exception.getCode().getValue());
							
							System.out.println("Details: " );
							
							//Get the details map
							for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
							
							//Get the Message
							System.out.println("Message: " + exception.getMessage().getValue());
						}
					}
				}
				//Check if the request returned an exception
				else if(actionHandler instanceof APIException)
				{
					//Get the received APIException instance
					APIException exception = (APIException) actionHandler;
					
					//Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());
					
					//Get the Code
					System.out.println("Code: " + exception.getCode().getValue());
					
					System.out.println("Details: " );
					
					//Get the details map
					for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						//Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}
					
					//Get the Message
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{//If response is not as expected
				
				//Get model object from response
				Model responseObject = response.getModel();
				
				//Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();
				
				//Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();
				
				for(Field field : fields)
				{
					//Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

	/**
	 * <h3> Get All ContactRoles Of Deal </h3>
	 * @param dealId ID of the Deals
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SDKException 
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
	public static void getAllContactRolesOfDeal(long dealId) throws IllegalArgumentException, IllegalAccessException, SDKException
	{
		//Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();
		
		//Get instance of ParameterMap Class
		ParameterMap paramInstance = new ParameterMap();

//        paramInstance.add(GetAllContactRolesOfDealParam.IDS, "");
		
		//Call getAllContactRolesOfDeal method that takes Param instance as parameter 
		APIResponse<RecordResponseHandler> response = contactRolesOperations.getAllContactRolesOfDeal(dealId, paramInstance);
		
		if(response != null)
		{
            //Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());
			
			if(Arrays.asList(204,304).contains(response.getStatusCode()))
			{
				System.out.println(response.getStatusCode() == 204? "No Content" : "Not Modified");
				return;
			}
			
			//Check if expected response is received
			if(response.isExpected())
			{
				//Get the object from response
				RecordResponseHandler responseHandler = response.getObject();
				
				if(responseHandler instanceof RecordResponseWrapper)
				{
					//Get the received RecordResponseWrapper instance
					RecordResponseWrapper responseWrapper = (RecordResponseWrapper) responseHandler;
					
					//Get the obtained Record instances
					List<com.zoho.crm.api.record.Record> records = responseWrapper.getData();
					
					for(com.zoho.crm.api.record.Record record : records)
					{					
						//Get the ID of each Record
						System.out.println("Record ID: " + record.getId());
						
						//Get the createdBy User instance of each Record
						User createdBy = record.getCreatedBy();
						
						//Check if createdBy is not null
						if(createdBy != null)
						{
							//Get the ID of the createdBy User
							System.out.println("Record Created By User-ID: " + createdBy.getId());
							
							//Get the name of the createdBy User
							System.out.println("Record Created By User-Name: " + createdBy.getName());
							
							//Get the Email of the createdBy User
							System.out.println("Record Created By User-Email: " + createdBy.getEmail());
						}
						
						//Get the CreatedTime of each Record
						System.out.println("Record CreatedTime: " + record.getCreatedTime());
						
						//Get the modifiedBy User instance of each Record
						User modifiedBy = record.getModifiedBy();
						
						//Check if modifiedBy is not null
						if(modifiedBy != null)
						{
							//Get the ID of the modifiedBy User
							System.out.println("Record Modified By User-ID: " + modifiedBy.getId());
							
							//Get the name of the modifiedBy User
							System.out.println("Record Modified By User-Name: " + modifiedBy.getName());
							
							//Get the Email of the modifiedBy User
							System.out.println("Record Modified By User-Email: " + modifiedBy.getEmail());
						}
						
						//Get the ModifiedTime of each Record
                        System.out.println("Record CreatedTime: " + record.getModifiedTime());
						
						//To get particular field value 
						System.out.println("Record Field Value: " + record.getKeyValue("Last_Name"));// FieldApiName
						
						System.out.println("Record KeyValues:");
						
						//Get the KeyValue map
						for(Map.Entry<String, Object> entry : record.getKeyValues().entrySet())
						{
							String keyName = entry.getKey();
							
							Object value = entry.getValue();
							
							if(value instanceof List)
							{
								System.out.println("Record KeyName : " + keyName);
								
								List<?> dataList = (List<?>) value;
								
								for(Object data : dataList)
								{
									if(data instanceof Map)
									{
										System.out.println("Record KeyName : " + keyName  + " - Value : ");
										
										for(Map.Entry<String, Object> mapValue : ((HashMap<String,Object>) data).entrySet())
										{
											System.out.println(mapValue.getKey()  + " : " + mapValue.getValue());
										}
									}
									else
									{
										System.out.println(data);
									}
								}
							}
							else if (value instanceof Map)
							{
								System.out.println("Record KeyName : " + keyName  + " - Value : ");
								
								for(Map.Entry<String, Object> mapValue : ((HashMap<String,Object>) value).entrySet())
								{
									System.out.println(mapValue.getKey()  + " : " + mapValue.getValue());
								}
							}
							else
							{
								System.out.println("Record KeyName : " + keyName  + " - Value : " + value);
							}
						}
                    }

					//Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();
					
					//Check if info is not null
					if(info != null)
					{
						if(info.getCount() != null)
						{
							//Get the Count of the Info
							System.out.println("Record Info Count: " + info.getCount().toString());
						}
	
						if(info.getMoreRecords() != null)
						{
							//Get the MoreRecords of the Info
							System.out.println("Record Info MoreRecords: " + info.getMoreRecords().toString());
						}
					}
				}
				//Check if the request returned an exception
				else if(responseHandler instanceof APIException)
				{
					//Get the received APIException instance
					APIException exception = (APIException) responseHandler;
					
					//Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());
					
					//Get the Code
					System.out.println("Code: " + exception.getCode().getValue());
					
					System.out.println("Details: " );
					
					//Get the details map
					for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						//Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}
					
					//Get the Message
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{//If response is not as expected
				
				//Get model object from response
				Model responseObject = response.getModel();
				
				//Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();
				
				//Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();
				
				for(Field field : fields)
				{
					//Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

    @SuppressWarnings("unchecked")
	public static void getContactRoleOfDeal(long contactId, long dealId) throws IllegalArgumentException, IllegalAccessException, SDKException
	{
		//Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();
		
		//Call getContactRoleOfDeal method that takes Param instance as parameter 
		APIResponse<RecordResponseHandler> response = contactRolesOperations.getContactRoleOfDeal(contactId, dealId);
		
		if(response != null)
		{
            //Get the status code from response
			System.out.println("Status Code: " + response.getStatusCode());
			
			if(Arrays.asList(204,304).contains(response.getStatusCode()))
			{
				System.out.println(response.getStatusCode() == 204? "No Content" : "Not Modified");
				return;
			}
			
			//Check if expected response is received
			if(response.isExpected())
			{
				//Get the object from response
				RecordResponseHandler responseHandler = response.getObject();
				
				if(responseHandler instanceof RecordResponseWrapper)
				{
					//Get the received RecordResponseWrapper instance
					RecordResponseWrapper responseWrapper = (RecordResponseWrapper) responseHandler;
					
					//Get the obtained Record instances
					List<com.zoho.crm.api.record.Record> records = responseWrapper.getData();
					
					for(com.zoho.crm.api.record.Record record : records)
					{					
						//Get the ID of each Record
						System.out.println("Record ID: " + record.getId());
						
						//Get the createdBy User instance of each Record
						User createdBy = record.getCreatedBy();
						
						//Check if createdBy is not null
						if(createdBy != null)
						{
							//Get the ID of the createdBy User
							System.out.println("Record Created By User-ID: " + createdBy.getId());
							
							//Get the name of the createdBy User
							System.out.println("Record Created By User-Name: " + createdBy.getName());
							
							//Get the Email of the createdBy User
							System.out.println("Record Created By User-Email: " + createdBy.getEmail());
						}
						
						//Get the CreatedTime of each Record
						System.out.println("Record CreatedTime: " + record.getCreatedTime());
						
						//Get the modifiedBy User instance of each Record
						User modifiedBy = record.getModifiedBy();
						
						//Check if modifiedBy is not null
						if(modifiedBy != null)
						{
							//Get the ID of the modifiedBy User
							System.out.println("Record Modified By User-ID: " + modifiedBy.getId());
							
							//Get the name of the modifiedBy User
							System.out.println("Record Modified By User-Name: " + modifiedBy.getName());
							
							//Get the Email of the modifiedBy User
							System.out.println("Record Modified By User-Email: " + modifiedBy.getEmail());
						}
						
						//Get the ModifiedTime of each Record
                        System.out.println("Record CreatedTime: " + record.getModifiedTime());
						
						//To get particular field value 
						System.out.println("Record Field Value: " + record.getKeyValue("Last_Name"));// FieldApiName
						
						System.out.println("Record KeyValues:");
						
						//Get the KeyValue map
						for(Map.Entry<String, Object> entry : record.getKeyValues().entrySet())
						{
							String keyName = entry.getKey();
							
							Object value = entry.getValue();
							
							if(value instanceof List)
							{
								System.out.println("Record KeyName : " + keyName);
								
								List<?> dataList = (List<?>) value;
								
								for(Object data : dataList)
								{
									if(data instanceof Map)
									{
										System.out.println("Record KeyName : " + keyName  + " - Value : ");
										
										for(Map.Entry<String, Object> mapValue : ((HashMap<String,Object>) data).entrySet())
										{
											System.out.println(mapValue.getKey()  + " : " + mapValue.getValue());
										}
									}
									else
									{
										System.out.println(data);
									}
								}
							}
							else if (value instanceof Map)
							{
								System.out.println("Record KeyName : " + keyName  + " - Value : ");
								
								for(Map.Entry<String, Object> mapValue : ((HashMap<String,Object>) value).entrySet())
								{
									System.out.println(mapValue.getKey()  + " : " + mapValue.getValue());
								}
							}
							else
							{
								System.out.println("Record KeyName : " + keyName  + " - Value : " + value);
							}
						}
                    }

					//Get the Object obtained Info instance
					Info info = responseWrapper.getInfo();
					
					//Check if info is not null
					if(info != null)
					{
						if(info.getCount() != null)
						{
							//Get the Count of the Info
							System.out.println("Record Info Count: " + info.getCount().toString());
						}
	
						if(info.getMoreRecords() != null)
						{
							//Get the MoreRecords of the Info
							System.out.println("Record Info MoreRecords: " + info.getMoreRecords().toString());
						}
					}
				}
				//Check if the request returned an exception
				else if(responseHandler instanceof APIException)
				{
					//Get the received APIException instance
					APIException exception = (APIException) responseHandler;
					
					//Get the Status
					System.out.println("Status: " + exception.getStatus().getValue());
					
					//Get the Code
					System.out.println("Code: " + exception.getCode().getValue());
					
					System.out.println("Details: " );
					
					//Get the details map
					for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
					{
						//Get each value in the map
						System.out.println(entry.getKey() + ": " + entry.getValue());
					}
					
					//Get the Message
					System.out.println("Message: " + exception.getMessage().getValue());
				}
			}
			else
			{//If response is not as expected
				
				//Get model object from response
				Model responseObject = response.getModel();
				
				//Get the response object's class
				Class<? extends Model> clas = responseObject.getClass();
				
				//Get all declared fields of the response class
				Field[] fields = clas.getDeclaredFields();
				
				for(Field field : fields)
				{
					//Get each value
					System.out.println(field.getName() + ":" + field.get(responseObject));
				}
			}
		}
	}

    public static void addContactRoleToDeal(long contactId, long dealId) throws SDKException
	{
		//Get instance of ContactRolesOperations Class
		ContactRolesOperations contactRolesOperations = new ContactRolesOperations();

		//Get instance of BodyWrapper Class that will contain the request body
		RecordBodyWrapper bodyWrapper = new RecordBodyWrapper();

		//Get instance of ContactRole Class
        ContactRoleWrapper contactRole = new ContactRoleWrapper();

        //Set name of the Contact Role
        contactRole.setContactRole("contactRole1");

		//Set the list to contactRoles in BodyWrapper instance
		bodyWrapper.setData(new ArrayList<ContactRoleWrapper>(Arrays.asList(contactRole)));

		//Call createContactRoles method that takes BodyWrapper instance as parameter
		APIResponse<RecordActionHandler> response = contactRolesOperations.addContactRoleToDeal(contactId, dealId, bodyWrapper);

        if(response != null)
        {
            //Get the status code from response
            System.out.println("Status code" + response.getStatusCode());

            //Get object from response
            RecordActionHandler actionHandler = response.getObject();

            if(actionHandler instanceof RecordActionWrapper)
            {
                //Get the received ActionWrapper instance
            	RecordActionWrapper actionWrapper = (RecordActionWrapper) actionHandler;

                //Get the list of obtained action responses
                List<ActionResponse> actionResponses = actionWrapper.getData();

                for(ActionResponse actionResponse : actionResponses)
                {
                    //Check if the request is successful
                    if(actionResponse instanceof SuccessResponse)
                    {
                        //Get the received SuccessResponse instance
                    	SuccessResponse successResponse = (SuccessResponse) actionResponse;

                        //Get the Status
                        System.out.println("Status: " + successResponse.getStatus().getValue());

                        //Get the Code
                        System.out.println("Code: " + successResponse.getCode().getValue());

                        System.out.println("Details: " );

                        if(successResponse.getDetails() != null)
                        {
                        	//Get the details map
							for(Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
                        }

                        //Get the Message
                        System.out.println("Message: " + successResponse.getMessage().getValue());
                    }
                    //Check if the request returned an exception
					else if(actionResponse instanceof APIException)
					{
						//Get the received APIException instance
						APIException exception = (APIException) actionResponse;
						
						//Get the Status
						System.out.println("Status: " + exception.getStatus().getValue());
						
						//Get the Code
						System.out.println("Code: " + exception.getCode().getValue());
						
						System.out.println("Details: " );
						
						//Get the details map
						for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
						{
							//Get each value in the map
							System.out.println(entry.getKey() + ": " + entry.getValue());
						}
						
						//Get the Message
						System.out.println("Message: " + exception.getMessage().getValue());
					}
                }
            }
            //Check if the request returned an exception
			else if(actionHandler instanceof APIException)
			{
				//Get the received APIException instance
				APIException exception = (APIException) actionHandler;
				
				//Get the Status
				System.out.println("Status: " + exception.getStatus().getValue());
				
				//Get the Code
				System.out.println("Code: " + exception.getCode().getValue());
				
				System.out.println("Details: " );
				
				//Get the details map
				for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
				{
					//Get each value in the map
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}
				
				//Get the Message
				System.out.println("Message: " + exception.getMessage().getValue());
			}
        }
	}

    public static void removeContactRoleFromDeal(long contactId, long dealId) throws SDKException
	{
		//Get instance of ContactRolesOperations Class
    	ContactRolesOperations contactRolesOperations = new ContactRolesOperations();

		//Call createContactRoles method that takes BodyWrapper instance as parameter
    	APIResponse<RecordActionHandler> response = contactRolesOperations.removeContactRoleFromDeal(contactId, dealId);

        if(response != null)
        {
            //Get the status code from response
            System.out.println("Status code" + response.getStatusCode());

            //Get object from response
            RecordActionHandler actionHandler = response.getObject();

            if(actionHandler instanceof RecordActionWrapper)
            {
                //Get the received ActionWrapper instance
            	RecordActionWrapper actionWrapper = (RecordActionWrapper) actionHandler;

                //Get the list of obtained action responses
            	List<ActionResponse> actionResponses = actionWrapper.getData();

                for(ActionResponse actionResponse : actionResponses)
                {
                    //Check if the request is successful
                    if(actionResponse instanceof SuccessResponse)
                    {
                        //Get the received SuccessResponse instance
                    	SuccessResponse successResponse = (SuccessResponse) actionResponse;

                        //Get the Status
                        System.out.println("Status: " + successResponse.getStatus().getValue());

                        //Get the Code
                        System.out.println("Code: " + successResponse.getCode().getValue());

                        System.out.println("Details: " );

                        if(successResponse.getDetails() != null)
                        {
                        	//Get the details map
							for(Map.Entry<String, Object> entry : successResponse.getDetails().entrySet())
							{
								//Get each value in the map
								System.out.println(entry.getKey() + ": " + entry.getValue());
							}
                        }

                        //Get the Message
                        System.out.println("Message: " + successResponse.getMessage().getValue());
                    }
                    //Check if the request returned an exception
					else if(actionResponse instanceof APIException)
					{
						//Get the received APIException instance
						APIException exception = (APIException) actionResponse;
						
						//Get the Status
						System.out.println("Status: " + exception.getStatus().getValue());
						
						//Get the Code
						System.out.println("Code: " + exception.getCode().getValue());
						
						System.out.println("Details: " );
						
						//Get the details map
						for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
						{
							//Get each value in the map
							System.out.println(entry.getKey() + ": " + entry.getValue());
						}
						
						//Get the Message
						System.out.println("Message: " + exception.getMessage().getValue());
					}
                }
            }
            //Check if the request returned an exception
			else if(actionHandler instanceof APIException)
			{
				//Get the received APIException instance
				APIException exception = (APIException) actionHandler;
				
				//Get the Status
				System.out.println("Status: " + exception.getStatus().getValue());
				
				//Get the Code
				System.out.println("Code: " + exception.getCode().getValue());
				
				System.out.println("Details: " );
				
				//Get the details map
				for(Map.Entry<String, Object> entry : exception.getDetails().entrySet())
				{
					//Get each value in the map
					System.out.println(entry.getKey() + ": " + entry.getValue());
				}
				
				//Get the Message
				System.out.println("Message: " + exception.getMessage().getValue());
			}
        }
	}
}
