package samples.src.com.zoho.crm.api.threading.singleuser;

import com.zoho.api.authenticator.OAuthToken;

import com.zoho.api.authenticator.Token;

import com.zoho.api.authenticator.store.FileStore;

import com.zoho.api.authenticator.store.TokenStore;

import com.zoho.api.logger.Logger;

import com.zoho.api.logger.Logger.Levels;

import com.zoho.crm.api.Initializer;

import com.zoho.crm.api.SDKConfig;

import com.zoho.crm.api.UserSignature;

import com.zoho.crm.api.dc.USDataCenter;

import com.zoho.crm.api.dc.DataCenter.Environment;

import com.zoho.crm.api.record.RecordOperations;

import com.zoho.crm.api.util.APIResponse;

public class MultiThread extends Thread
{
	String moduleAPIName;
	
	public MultiThread(String moduleAPIName)
	{
		this.moduleAPIName = moduleAPIName;
	}
	
	public void run() 
    { 
        try
        { 
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
	
	public static void main(String[] args) throws Exception
	{
		
		Logger logger = new Logger.Builder()
        .level(Levels.INFO)
        .filePath("/Users/user_name/Documents/java_sdk_log.log")
        .build();
		
		Environment environment = USDataCenter.PRODUCTION;
		
		UserSignature user1 = new UserSignature("abc@zoho.com");
		
		TokenStore tokenstore = new FileStore("/Users/user_name/Documents/java_sdk_tokens.txt");
		
		Token token = new OAuthToken.Builder()
        .clientID("clientId")
        .clientSecret("clientSecret")
        .grantToken("grantToken")
        .redirectURL("redirectURL")
        .build();
		
		String resourcePath = "/Users/user_name/Documents/javasdk-application";
		
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
		
		MultiThread mtsu = new MultiThread("Deals");
		
		mtsu.start();
		 
		mtsu = new MultiThread("Leads");
		
		mtsu.start();
	}
}