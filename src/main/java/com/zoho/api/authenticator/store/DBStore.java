package com.zoho.api.authenticator.store;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;

import com.zoho.api.authenticator.OAuthToken;

import com.zoho.api.authenticator.Token;

import com.zoho.crm.api.UserSignature;

import com.zoho.crm.api.exception.SDKException;

import com.zoho.crm.api.util.Constants;

public class DBStore implements TokenStore
{
	private String userName;
	
	private String portNumber;
	
	private String password;
	
	private String host;
	
	private String databaseName;
	
	private String connectionString;
	
	private String tableName;
	
	/**
	 * Creates a DBStore class instance with the specified parameters.
	 * @param host A String containing the DataBase host name.
	 * @param databaseName A String containing the DataBase name.
	 * @param userName A String containing the DataBase user name.
	 * @param password A String containing the DataBase password.
	 * @param portNumber A String containing the DataBase port number.
	 */
	private DBStore(String host, String databaseName, String tableName, String userName, String password, String portNumber)
	{
		this.host = host;
		
		this.databaseName = databaseName;
		
		this.tableName = tableName;
		
		this.userName = userName;
		
		this.password = password;
		
		this.portNumber = portNumber;
		
		this.connectionString = Constants.MYSQL_DRIVER + this.host + ":" + this.portNumber + "/" + this.databaseName + "?allowPublicKeyRetrieval=true&useSSL=false";
	}
	
	
	public static class Builder
	{
		private String userName = Constants.MYSQL_USER_NAME;
		
		private String portNumber = Constants.MYSQL_PORT_NUMBER;
		
		private String password = "";
		
		private String host = Constants.MYSQL_HOST;
		
		private String databaseName = Constants.MYSQL_DATABASE_NAME;
		
		private String tableName = Constants.MYSQL_TABLE_NAME;
		
		public Builder userName(String userName)
		{
			this.userName = userName;
			
			return this;
		}
		
		public Builder portNumber(String portNumber)
		{
			this.portNumber = portNumber;
			
			return this;
		}
		
		public Builder password(String password)
		{
			this.password = password;
			
			return this;
		}
		
		public Builder host(String host)
		{
			this.host = host;
			
			return this;
		}
		
		public Builder databaseName(String databaseName)
		{
			this.databaseName = databaseName;
			
			return this;
		}
		
		public Builder tableName(String tableName)
		{
			this.tableName = tableName;
			
			return this;
		}
		
		public DBStore build()
		{
			return new DBStore(this.host, this.databaseName, this.tableName, this.userName, this.password, this.portNumber);
		}
	}


	@Override
	public Token getToken(UserSignature user, Token token) throws SDKException
	{
		try
		{
			Class.forName(Constants.JDBC_DRIVER_NAME);
			
			try(Connection connection = DriverManager.getConnection(connectionString, userName, password);)
			{
				if(token instanceof OAuthToken)
				{
					
					try(Statement statement = connection.createStatement();)
					{
						OAuthToken oauthToken = (OAuthToken) token;
								
						String query = constructDBQuery(user.getEmail(),oauthToken, false);
						
						try(ResultSet result = statement.executeQuery(query);)
						{
							while (result.next())
							{								
								oauthToken.setId(result.getString(1));
								
								oauthToken.setUserMail(result.getString(2));
								
								oauthToken.setClientId(result.getString(3));
								
								oauthToken.setClientSecret(result.getString(4));
								
								oauthToken.setRefreshToken(result.getString(5));
								
								oauthToken.setAccessToken(result.getString(6));
								
								oauthToken.setGrantToken(result.getString(7));
								
								oauthToken.setExpiresIn(String.valueOf(result.getString(8)));
								
								oauthToken.setRedirectURL(result.getString(9));
								
								return oauthToken;
							}
						}
					}
				}
			}
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.GET_TOKEN_DB_ERROR, ex);
		}

		return null;
	}


	@Override
	public void saveToken(UserSignature user, Token token) throws SDKException
	{
		try
		{
			Class.forName(Constants.JDBC_DRIVER_NAME);
			
			try(Connection connection = DriverManager.getConnection(this.connectionString, this.userName, this.password);)
			{
				if(token instanceof OAuthToken)
				{
					OAuthToken oauthToken = (OAuthToken) token;
					
					oauthToken.setUserMail(user.getEmail());
					
					this.deleteToken(oauthToken);
					
					try(PreparedStatement statement = connection.prepareStatement("insert into " + this.tableName + "(id,user_mail,client_id,client_secret,refresh_token,access_token,grant_token,expiry_time,redirect_url) values(?,?,?,?,?,?,?,?,?)");)
					{
						statement.setString(1, oauthToken.getId());
						
						statement.setString(2, user.getEmail());
				
						statement.setString(3, oauthToken.getClientId());
						
						statement.setString(4, oauthToken.getClientSecret());
						
						statement.setString(5, oauthToken.getRefreshToken());
						
						statement.setString(6, oauthToken.getAccessToken());
						
						statement.setString(7, oauthToken.getGrantToken());
						
						statement.setString(8, oauthToken.getExpiresIn());
						
						statement.setString(9, oauthToken.getRedirectURL());
						
						statement.executeUpdate();
					}
				}
			}
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.SAVE_TOKEN_DB_ERROR, ex);
		}
	}


	@Override
	public void deleteToken(Token token) throws SDKException
	{
		try
		{
			Class.forName(Constants.JDBC_DRIVER_NAME);
			
			try(Connection connection = DriverManager.getConnection(this.connectionString, this.userName, this.password);)
			{
				if(token instanceof OAuthToken)
				{
					String query = constructDBQuery(((OAuthToken) token).getUserMail(), (OAuthToken) token, true);
					
					try(PreparedStatement statement = connection.prepareStatement(query);)
					{
						statement.executeUpdate();
					}
				}
			}
		}
		catch(SDKException ex)
		{
			throw ex;
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.DELETE_TOKEN_DB_ERROR, ex);
		}
	}


	@Override
	public List<Token> getTokens() throws SDKException
	{
		List<Token> tokens = new ArrayList<Token>();
		
		try
		{
			Class.forName(Constants.JDBC_DRIVER_NAME);
			
			try(Connection connection = DriverManager.getConnection(connectionString, userName, password);)
			{
				try(Statement statement = connection.createStatement();)
				{
					String query = "select * from " + this.tableName + ";";
					
					try(ResultSet result = statement.executeQuery(query);)
					{
						while (result.next())
						{
							
							OAuthToken oauthToken = new OAuthToken.Builder().clientID(result.getString(3)).clientSecret(result.getString(4)).grantToken(result.getString(7)).refreshToken(result.getString(5)).build() ;
							
							oauthToken.setId(result.getString(1));
							
							oauthToken.setUserMail(result.getString(2));
																												
							oauthToken.setAccessToken(result.getString(6));
														
							oauthToken.setExpiresIn(String.valueOf(result.getString(8)));
							
							oauthToken.setRedirectURL(result.getString(9));
							
							tokens.add(oauthToken);
						}
					}
				}
			}
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.GET_TOKENS_DB_ERROR, ex);
		}

		return tokens;
	}


	@Override
	public void deleteTokens() throws SDKException
	{
		try
		{
			Class.forName(Constants.JDBC_DRIVER_NAME);
			
			try(Connection connection = DriverManager.getConnection(this.connectionString, this.userName, this.password);)
			{
				String query = "delete from " + this.tableName;
				
				try(PreparedStatement statement = connection.prepareStatement(query);)
				{
					statement.executeUpdate();
				}
			}
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.DELETE_TOKENS_DB_ERROR, ex);
		}
		
	}
	
	private String constructDBQuery(String email, OAuthToken token, boolean isDelete) throws SDKException
	{
		if(email == null)
		{
			throw new SDKException(Constants.USER_MAIL_NULL_ERROR, Constants.USER_MAIL_NULL_ERROR_MESSAGE);
		}
		
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append(isDelete ? "delete from " : "select * from ");
		
		queryBuilder.append(this.tableName).append(" where user_mail='").append(email);
		
		queryBuilder.append("' and client_id='").append(token.getClientId()).append("' and ");

		if (token.getGrantToken() != null)
		{
			queryBuilder.append("grant_token='").append(token.getGrantToken()).append("'");
		}
		else
		{
			queryBuilder.append("refresh_token='").append(token.getRefreshToken()).append("'");
		}

		return queryBuilder.toString();
	}


	@Override
	public Token getTokenById(String id, Token token) throws SDKException
	{
		try
		{
			Class.forName(Constants.JDBC_DRIVER_NAME);
			
			try(Connection connection = DriverManager.getConnection(connectionString, userName, password);)
			{
				if(token instanceof OAuthToken)
				{
					OAuthToken oauthToken = (OAuthToken) token;
					
					try(Statement statement = connection.createStatement();)
					{
						String query = "select * from " + this.tableName + " where id='" + id + "'";
						
						try(ResultSet result = statement.executeQuery(query);)
						{
							if(result.next() == false)
							{
								throw new SDKException(Constants.TOKEN_STORE, Constants.GET_TOKEN_BY_ID_DB_ERROR);
							}
							
							do
							{
								oauthToken.setId(result.getString(1));
								
								oauthToken.setUserMail(result.getString(2));
								
								oauthToken.setClientId(result.getString(3));
								
								oauthToken.setClientSecret(result.getString(4));
								
								oauthToken.setRefreshToken(result.getString(5));
								
								oauthToken.setAccessToken(result.getString(6));
								
								oauthToken.setGrantToken(result.getString(7));
								
								oauthToken.setExpiresIn(String.valueOf(result.getString(8)));
								
								oauthToken.setRedirectURL(result.getString(9));
								
								return oauthToken;
							} while(result.next());
						}
					}
				}
			}
		}
		catch(SDKException ex)
		{
			throw ex;
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.GET_TOKEN_BY_ID_DB_ERROR, ex);
		}

		return null;
	}
}
