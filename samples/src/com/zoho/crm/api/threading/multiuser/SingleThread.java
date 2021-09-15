package samples.src.com.zoho.crm.api.threading.multiuser;

import com.zoho.api.authenticator.OAuthToken;

import com.zoho.api.authenticator.Token;

import com.zoho.api.authenticator.store.DBStore;

import com.zoho.api.authenticator.store.TokenStore;

import com.zoho.api.logger.Logger;

import com.zoho.crm.api.Initializer;

import com.zoho.crm.api.SDKConfig;

import com.zoho.crm.api.UserSignature;

import com.zoho.crm.api.dc.USDataCenter;

import com.zoho.crm.api.dc.DataCenter.Environment;

import com.zoho.crm.api.record.RecordOperations;

import com.zoho.crm.api.util.APIResponse;

public class SingleThread
{
	Environment environment;
	
	UserSignature user;
	
	Token token;
	
	String moduleAPIName;
	
	SDKConfig sdkConfig;
	
	public SingleThread( String moduleAPIName)
	{
		this.moduleAPIName = moduleAPIName;
	}
	
	public SingleThread(UserSignature user, Environment environment, Token token,  String moduleAPIName, SDKConfig config)
	{
		this.environment= environment;
		
		this.user = user;
		
		this.token = token;
		
		this.moduleAPIName = moduleAPIName;
		
		this.sdkConfig = config;
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
	
	public static void main(String[] args) throws Exception
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
		
		SDKConfig sdkConfig = new SDKConfig.Builder()
		.autoRefreshFields(false)
		.pickListValidation(true)
		.build();
		
		new Initializer.Builder()
		.user(user1)
		.environment(environment)
		.token(token1)
		.store(tokenstore)
		.SDKConfig(sdkConfig)
		.resourcePath(resourcePath)
		.logger(loggerInstance)
		.initialize();
		
		SingleThread singleThread = new SingleThread(user1, environment, token1, "Students", sdkConfig);
		
		singleThread.run();
		
		Environment environment1 = EUDataCenter.PRODUCTION;
		
		UserSignature user2 = new UserSignature("xyz@zoho.com");
		
		Token token2 = new OAuthToken.Builder()
        .clientID("clientId")
        .clientSecret("clientSecret")
        .grantToken("grantToken")
        .redirectURL("redirectURL")
        .build();
		
		singleThread = new SingleThread(user2, environment1, token2, "Leads", sdkConfig);
		
		singleThread.run();
	}
}
