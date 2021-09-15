package samples.src.com.zoho.crm.api.threading.multiuser;

import com.zoho.api.authenticator.OAuthToken;

import com.zoho.api.authenticator.Token;

import com.zoho.api.authenticator.store.DBStore;

import com.zoho.api.authenticator.store.TokenStore;

import com.zoho.api.logger.Logger;

import com.zoho.crm.api.Initializer;

import com.zoho.crm.api.RequestProxy;

import com.zoho.crm.api.SDKConfig;

import com.zoho.crm.api.UserSignature;

import com.zoho.crm.api.dc.USDataCenter;

import com.zoho.crm.api.dc.DataCenter.Environment;

import com.zoho.crm.api.exception.SDKException;

import com.zoho.crm.api.record.RecordOperations;

import com.zoho.crm.api.util.APIResponse;

public class MultiThread extends Thread
{
	Environment environment;
	
	UserSignature user;
	
	Token token;
	
	String moduleAPIName;
	
	RequestProxy userProxy;
	
	SDKConfig sdkConfig;
	
	public MultiThread(UserSignature user, Environment environment, Token token, String moduleAPIName, SDKConfig config, RequestProxy proxy)
	{
		this.environment= environment;
		
		this.user = user;
		
		this.token = token;
		
		this.moduleAPIName = moduleAPIName;
		
		this.sdkConfig = config;
		
		this.userProxy = proxy;
	}
	
	public void run() 
    { 
        try
        { 
        	new Initializer.Builder()
        	.user(user)
        	.environment(environment)
        	.token(token)
        	.SDKConfig(sdkConfig)
        	.requestProxy(userProxy)
        	.switchUser();
        	
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
		
		UserSignature user1 = new UserSignature("abc@zoho.com");
		
		TokenStore tokenstore = new DBStore.Builder()
        .host("hostName")
        .databaseName("databaseName")
        .tableName("tableName")
        .userName("userName")
        .password("password")
        .portNumber("portNumber")
        .build();
		
		Token token1 = new OAuthToken.Builder()
        .clientID("clientId")
        .clientSecret("clientSecret")
        .grantToken("grantToken")
        .redirectURL("redirectURL")
        .build();
		
		String resourcePath = "/Users/user_name/Documents/javasdk-application";
		
		SDKConfig user1Config = new SDKConfig.Builder()
		.autoRefreshFields(false)
		.pickListValidation(true)
		.build();
		
		new Initializer.Builder()
		.user(user1)
		.environment(environment)
		.token(token1)
		.store(tokenstore)
		.SDKConfig(user1Config)
		.resourcePath(resourcePath)
		.logger(loggerInstance)
		.initialize();
    	
		MultiThread multiThread = new MultiThread(user1, environment, token1, "Students", user1Config, null);
		
		multiThread.start();
		
		Environment environment1 = USDataCenter.PRODUCTION;
		
		UserSignature user2 = new UserSignature("xyz@zoho.com");
		
		Token token2 = new OAuthToken.Builder()
        .clientID("clientId")
        .clientSecret("clientSecret")
        .grantToken("grantToken")
        .redirectURL("redirectURL")
        .build();
		
		RequestProxy user2Proxy = new RequestProxy.Builder()
				.host("proxyHost")
				.port(80)
				.user("proxyUser")
				.password("password")
				.userDomain("userDomain")
				.build();
		
		SDKConfig user2Config = new SDKConfig.Builder()
		.autoRefreshFields(true)
		.pickListValidation(false)
		.build();
		
		multiThread = new MultiThread(user2, environment1, token2, "Leads", user2Config, user2Proxy);
		
		multiThread.start();
		
	}
}

