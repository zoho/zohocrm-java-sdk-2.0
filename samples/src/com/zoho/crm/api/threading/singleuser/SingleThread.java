package samples.src.com.zoho.crm.api.threading.singleuser;

import com.zoho.api.authenticator.OAuthToken;

import com.zoho.api.authenticator.Token;

import com.zoho.api.authenticator.store.DBStore;

import com.zoho.api.authenticator.store.TokenStore;

import com.zoho.api.logger.Logger;

import com.zoho.api.logger.Logger.Levels;

import com.zoho.crm.api.Initializer;

import com.zoho.crm.api.RequestProxy;

import com.zoho.crm.api.SDKConfig;

import com.zoho.crm.api.UserSignature;

import com.zoho.crm.api.dc.USDataCenter;

import com.zoho.crm.api.dc.DataCenter.Environment;

import com.zoho.crm.api.exception.SDKException;

import com.zoho.crm.api.record.RecordOperations;

import com.zoho.crm.api.util.APIResponse;

public class SingleThread extends Thread
{
	String moduleAPIName;
	
	public SingleThread(String moduleAPIName)
	{
		this.moduleAPIName = moduleAPIName;
	}
	
	public void run() 
    { 
        try
        { 
        	System.out.println(Initializer.getInitializer().getUser().getEmail());
        	
        	RecordOperations cro = new RecordOperations();
        	
    		@SuppressWarnings("rawtypes")
			APIResponse getResponse = cro.getRecords(this.moduleAPIName, null, null);
  
    		System.out.println(getResponse.getObject());
    		
        } 
        catch (Exception e) 
        { 
            e.printStackTrace();
        } 
    } 
	
	public static void main(String[] args) throws SDKException
	{	
		Logger logger = new Logger.Builder()
        .level(Levels.INFO)
        .filePath("/Users/user_name/Documents/java_sdk_log.log")
        .build();
		
		Environment environment = USDataCenter.PRODUCTION;
		
		UserSignature user = new UserSignature("abc@zoho.com");
		
		TokenStore tokenstore = new DBStore.Builder()
        .host("hostName")
        .databaseName("databaseName")
        .tableName("tableName")
        .userName("userName")
        .password("password")
        .portNumber("portNumber")
        .build();
		
		Token token = new OAuthToken.Builder()
        .clientId("clientId")
        .clientSecret("clientSecret")
        .grantToken("grantToken")
        .redirectURL("redirectURL")
        .build();

		
		String resourcePath = "/Users/user_name/Documents/javasdk-application";
		
//		RequestProxy proxy = new RequestProxy.Builder()
//				.host("proxyHost")
//				.port(80)
//				.user("proxyUser")
//				.password("password")
//				.userDomain("userDomain")
//				.build();
		
		SDKConfig sdkConfig = new SDKConfig.Builder()
		.autoRefreshFields(false)
		.pickListValidation(true)
		.build();
		
		new Initializer.Builder()
		.user(user)
		.environment(environment)
		.token(token)
		.store(tokenstore)
		.SDKConfig(sdkConfig)
		.resourcePath(resourcePath)
		.logger(logger)
		.initialize();
		
		SingleThread stsu = new SingleThread("Leads");
		
		stsu.start();
	}
}
