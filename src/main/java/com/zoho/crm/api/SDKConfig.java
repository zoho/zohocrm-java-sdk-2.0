package com.zoho.crm.api;


/**
 * The class to configure the SDK.
 */
public class SDKConfig
{
	/**
	 * The Builder class to build SDKConfig
	 */
	public static class Builder 
	{
		private boolean autoRefreshFields = false;
		
		private boolean pickListValidation = true;
		
		private int connectionTimeout = 0;
		
		private int requestTimeout = 0;
		
		private int socketTimeout = 0;
		
		public Builder()
		{
		}
		
		/**
		 * This is a setter method to set autoRefreshFields.
		 * @param autoRefreshFields A boolean
		 * @return An instance of Builder
		 */
		public Builder setAutoRefreshFields(boolean autoRefreshFields)
		{
			this.autoRefreshFields = autoRefreshFields;
			
			return this;
		}
		
		/**
		 * This is a setter method to set pickListValidation.
		 * @param pickListValidation A boolean
		 * @return An instance of Builder
		 */
		public Builder setPickListValidation(boolean pickListValidation)
		{
			this.pickListValidation = pickListValidation;
			
			return this;
		}
		
		/**
		 * This is a setter method to set connectionTimeout.
		 * @param connectionTimeout A int
		 * @return An instance of Builder
		 */
		public Builder connectionTimeout(int connectionTimeout)
		{
			this.connectionTimeout = connectionTimeout > 0 ? connectionTimeout : 0;
			
			return this;
		}
		
		/**
		 * This is a setter method to set requestTimeout.
		 * @param requestTimeout A int
		 * @return An instance of Builder
		 */
		public Builder requestTimeout(int requestTimeout)
		{
			this.requestTimeout = requestTimeout > 0 ? requestTimeout : 0;
			
			return this;
		}
		
		/**
		 * This is a setter method to set socketTimeout.
		 * @param socketTimeout A int
		 * @return An instance of Builder
		 */
		public Builder socketTimeout(int socketTimeout)
		{
			this.socketTimeout = socketTimeout > 0 ? socketTimeout : 0;
			
			return this;
		}
		
		/**
		 * The method to build the SDKConfig instance
		 * @return An instance of SDKConfig
		 */
		public SDKConfig build()
		{
			return new SDKConfig(autoRefreshFields, pickListValidation, connectionTimeout, requestTimeout, socketTimeout);
		}
	}

	private boolean autoRefreshFields;
	
	private boolean pickListValidation;
	
	private int connectionTimeout;
	
	private int requestTimeout;
	
	private int socketTimeout;
	
	/**
	 * Creates an instance of SDKConfig with the given parameters
	 * @param autoRefreshFields A boolean representing autoRefreshFields
	 * @param pickListValidation A boolean representing pickListValidation
	 * @param connectionTimeout A long representing connectionTimeout
	 * @param requestTimeout A long representing requestTimeout
	 * @param socketTimeout A long representing socketTimeout
	 */
	SDKConfig(boolean autoRefreshFields, boolean pickListValidation, int connectionTimeout, int requestTimeout, int socketTimeout)
	{
		this.autoRefreshFields = autoRefreshFields;
		
		this.pickListValidation = pickListValidation;
		
		this.connectionTimeout = connectionTimeout;
		
		this.requestTimeout = requestTimeout;
		
		this.socketTimeout = socketTimeout;
	}

	/**
	 * This is a getter method to get autoRefreshFields.
	 * @return A boolean representing autoRefreshFields
	 */
	public boolean getAutoRefreshFields()
	{
		return autoRefreshFields;
	}

	/**
	 * This is a getter method to get pickListValidation.
	 * @return A boolean representing pickListValidation
	 */
	public boolean getPickListValidation()
	{
		return pickListValidation;
	}
	
	/**
	 * This is a getter method to get connectionTimeout.
	 * @return A int representing connectionTimeout
	 */
	public int connectionTimeout()
	{
		return connectionTimeout;
	}
	
	/**
	 * This is a getter method to get requestTimeout.
	 * @return A int representing requestTimeout
	 */
	public int requestTimeout()
	{
		return requestTimeout;
	}
	
	/**
	 * This is a getter method to get socketTimeout.
	 * @return A int representing socketTimeout
	 */
	public int socketTimeout()
	{
		return socketTimeout;
	}
}
