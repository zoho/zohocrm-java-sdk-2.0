/**
Copyright (c) 2021, ZOHO CORPORATION PRIVATE LIMITED 
All rights reserved. 
 
   Licensed under the Apache License, Version 2.0 (the "License"); 
   you may not use this file except in compliance with the License. 
   You may obtain a copy of the License at 
 
       http://www.apache.org/licenses/LICENSE-2.0 
 
   Unless required by applicable law or agreed to in writing, software 
   distributed under the License is distributed on an "AS IS" BASIS, 
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
   See the License for the specific language governing permissions and 
   limitations under the License. 
**/

package com.zoho.crm.api;

import java.io.BufferedReader;

import java.io.File;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.nio.file.Files;

import java.nio.file.Paths;

import java.util.logging.Level;

import java.util.logging.Logger;

import org.json.JSONObject;

import com.zoho.api.authenticator.Token;

import com.zoho.api.authenticator.store.FileStore;

import com.zoho.api.authenticator.store.TokenStore;

import com.zoho.api.logger.SDKLogger;

import com.zoho.api.logger.Logger.Levels;

import com.zoho.crm.api.dc.DataCenter.Environment;

import com.zoho.crm.api.exception.SDKException;

import com.zoho.crm.api.util.Constants;

import com.zoho.crm.api.util.Utility;

public class Initializer
{
	private static final Logger LOGGER = Logger.getLogger(SDKLogger.class.getName());
	
	private static final ThreadLocal<Initializer> LOCAL = new ThreadLocal<Initializer>();
	
	private static Initializer initializer;

	private Environment environment;
	
	private TokenStore store;
	
	private UserSignature user;
	
	private Token token;

	public static JSONObject jsonDetails;
	
	private String resourcePath;
	
	private RequestProxy requestProxy;
	
	private SDKConfig sdkConfig;
	
	private Initializer()
	{
	}
	
	private static void initialize(UserSignature user, Environment environment, Token token, TokenStore store, SDKConfig sdkConfig, String resourcePath, com.zoho.api.logger.Logger logger, RequestProxy proxy) throws SDKException
	{
		try
		{
			SDKLogger.initialize(logger);

			try
			{
				if(jsonDetails == null || jsonDetails.length() == 0)
				{
					jsonDetails = getJSONDetails();
				}
			}
			catch (IOException e)
			{
				throw new SDKException(Constants.JSON_DETAILS_ERROR, e);
			}

			initializer = new Initializer();

			initializer.user = user;
			
			initializer.environment = environment;
			
			initializer.token = token;
			
			initializer.store = store;
			
			initializer.sdkConfig = sdkConfig;
			
			initializer.resourcePath = resourcePath;
			
			initializer.requestProxy = proxy;
			
			LOGGER.log(Level.INFO, Constants.INITIALIZATION_SUCCESSFUL.concat(initializer.toString()));
		}
		catch(SDKException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new SDKException(Constants.INITIALIZATION_EXCEPTION, e);
		}
	}
	
	/**
	 * This method to get POJO class information details.
	 * @return A JSONObject representing the class information details.
	 * @throws IOException if a problem occurs.
	 * @throws SDKException 
	 */
	private static JSONObject getJSONDetails() throws IOException, SDKException
	{
		String line, fileContent = "";

		try (InputStream is = Initializer.class.getResourceAsStream(Constants.JSON_DETAILS_FILE_PATH);InputStreamReader isr = new InputStreamReader(is); BufferedReader br = new BufferedReader(isr);)
		{
			while ((line = br.readLine()) != null)
			{
				fileContent += line;
			}
		}
		catch (Exception e)
		{
			throw new SDKException(Constants.EXCEPTION_JSONDETAILS, e);
		}

		return new JSONObject(fileContent);
	}

	
	/**
	 * The method to switch the different user in SDK environment.
	 * @param user A User class instance represents the CRM user.
	 * @param environment A Environment class instance containing the CRM API base URL and Accounts URL.
	 * @param token A Token class instance containing the OAuth client application information.
	 * @param sdkConfig A SDKConfig class instance containing the configuration.
	 * @param proxy An RequestProxy class instance containing the proxy properties of the user.
	 * @throws SDKException 
	 */
	private static void switchUser(UserSignature user, Environment environment, Token token, SDKConfig sdkConfig, RequestProxy proxy) throws SDKException
	{
		Initializer initializer = new Initializer();
		
		initializer.user = user;
		
		initializer.environment = environment;
		
		initializer.token = token;
		
		initializer.store = Initializer.initializer.store;
		
		initializer.sdkConfig = sdkConfig;
		
		initializer.resourcePath = Initializer.initializer.resourcePath;
		
		initializer.requestProxy = proxy;
		
		LOCAL.set(initializer);
		
		LOGGER.log(Level.INFO, Constants.INITIALIZATION_SWITCHED.concat(initializer.toString()));
	}
	
	/**
	 * This method to get record field information details.
	 * @param filePath A String containing the class information details file path.
	 * @return A JSONObject representing the class information details.
	 * @throws IOException if a problem occurs.
	 */
	public static JSONObject getJSON(String filePath) throws IOException
	{
		return new JSONObject(new String(Files.readAllBytes(Paths.get(filePath))));
	}

	/**
	 * This method to get Initializer class instance.
	 * @return A Initializer class instance representing the SDK configuration details.
	 */
	public static Initializer getInitializer()
	{
		if (LOCAL.get() != null)
		{
			return LOCAL.get();
		}

		return initializer;
	}
	
	/**
	 * This is a getter method to get API environment.
	 * @return A Environment representing the API environment.
	 */
	public Environment getEnvironment()
	{
		return environment;
	}

	/**
	 * This is a getter method to get API Token Store.
	 * @return A TokenStore class instance containing the token store information.
	 */
	public TokenStore getStore()
	{
		return store;
	}

	/**
	 * This is a getter method to get CRM User.
	 * @return A UserSignature class instance representing the CRM user.
	 */
	public UserSignature getUser()
	{
		return user;
	}

	/**
	 * This is a getter method to get OAuth client application information.
	 * @return A Token class instance representing the OAuth client application information.
	 */
	public Token getToken()
	{
		return token;
	}
	
	public String getResourcePath()
	{
		return resourcePath;
	}
	
	/**
	 * This is a getter method to get Proxy information.
	 * @return A RequestProxy class instance representing the API Proxy information.
	 */
	public RequestProxy getRequestProxy()
	{
		return requestProxy;
	}
	
	/**
	 * This is a getter method to get the SDK Configuration
	 * @return A SDKConfig instance representing the configuration
	 */
	public SDKConfig getSDKConfig()
	{
		return sdkConfig;
	}

	@Override
	protected void finalize() throws Throwable
	{
		LOCAL.remove();
	}
	
	public String toString()
	{
		return new StringBuilder().append(Constants.FOR_EMAIL_ID).append(getInitializer().getUser().getEmail())
				.append(Constants.IN_ENVIRONMENT)
				.append(getInitializer().getEnvironment().getUrl()).append(".").toString();
	}
	
	public static class Builder
	{
		private Environment environment;
		
		private TokenStore store;
		
		private UserSignature user;
		
		private Token token;
		
		private String resourcePath;
		
		private RequestProxy requestProxy;
		
		private SDKConfig sdkConfig;
		
		private com.zoho.api.logger.Logger logger;
		
		private String errorMessage = (Initializer.initializer != null)? Constants.SWITCH_USER_ERROR : Constants.INITIALIZATION_ERROR;
				
		public Builder()
		{
			if(Initializer.getInitializer() != null)
			{
				Initializer previousInitializer = Initializer.getInitializer();
				
				user = previousInitializer.user;
				
				environment = previousInitializer.environment;
				
				token = previousInitializer.token;
				
				sdkConfig = previousInitializer.sdkConfig;
				
			}
		}
		
		public void initialize() throws Exception
		{
			Utility.assertNotNull(user, errorMessage, Constants.USER_SIGNATURE_ERROR_MESSAGE);
			
			Utility.assertNotNull(environment, errorMessage, Constants.ENVIRONMENT_ERROR_MESSAGE);
			
			Utility.assertNotNull(token, errorMessage, Constants.TOKEN_ERROR_MESSAGE);

			if(store == null)
			{
				store = new FileStore(System.getProperty("user.dir") + File.separator + Constants.TOKEN_FILE);
			}

			if(sdkConfig == null)
			{
				sdkConfig = new SDKConfig.Builder().build();
			}

			if(resourcePath == null)
			{
				resourcePath = System.getProperty("user.dir");
			}

			if(logger == null)
			{
				logger = new com.zoho.api.logger.Logger.Builder().level(Levels.INFO).filePath(System.getProperty("user.dir") + File.separator + Constants.LOG_FILE_NAME).build();
			}
			
			Initializer.initialize(user, environment, token, store, sdkConfig, resourcePath, logger, requestProxy);
		}
		
		public void switchUser() throws SDKException
		{
			Utility.assertNotNull(Initializer.initializer, Constants.SDK_UNINITIALIZATION_ERROR, Constants.SDK_UNINITIALIZATION_MESSAGE);

			Initializer.switchUser(user, environment, token, sdkConfig, requestProxy);
		}
		
		public Builder logger(com.zoho.api.logger.Logger logger)
		{
			this.logger = logger;
			
			return this;
		}
		
		public Builder token(Token token) throws SDKException
		{
			Utility.assertNotNull(token, errorMessage, Constants.TOKEN_ERROR_MESSAGE);
			
			this.token = token;
			
			return this;
		}
		
		public Builder SDKConfig(SDKConfig sdkConfig) throws SDKException
		{
			this.sdkConfig = sdkConfig;
			
			return this;
		}
		
		public Builder requestProxy(RequestProxy requestProxy)
		{
			this.requestProxy = requestProxy;
			
			return this;
		}
		
		public Builder resourcePath(String resourcePath) throws SDKException
		{
			if(resourcePath != null && !new File(resourcePath).isDirectory())
			{
				throw new SDKException(errorMessage, Constants.RESOURCE_PATH_INVALID_ERROR_MESSAGE);
			}
			
			this.resourcePath = resourcePath;
			
			return this;
		}
		
		public Builder user(UserSignature user) throws SDKException
		{
			Utility.assertNotNull(user, errorMessage, Constants.USER_SIGNATURE_ERROR_MESSAGE);
			
			this.user = user;
			
			return this;
		}
		
		public Builder store(TokenStore store) throws SDKException
		{
			this.store = store;
			
			return this;
		}
		
		public Builder environment(Environment environment) throws SDKException
		{
			Utility.assertNotNull(environment, errorMessage, Constants.ENVIRONMENT_ERROR_MESSAGE);
			
			this.environment = environment;
			
			return this;
		}
	}
}