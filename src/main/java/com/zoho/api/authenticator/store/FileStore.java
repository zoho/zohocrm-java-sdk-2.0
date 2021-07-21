package com.zoho.api.authenticator.store;

import java.io.File;

import java.io.FileReader;

import java.io.FileWriter;

import java.util.ArrayList;

import java.util.List;

import com.opencsv.CSVReader;

import com.opencsv.CSVWriter;

import com.zoho.api.authenticator.OAuthToken;

import com.zoho.api.authenticator.Token;

import com.zoho.crm.api.UserSignature;

import com.zoho.crm.api.exception.SDKException;

import com.zoho.crm.api.util.Constants;

public class FileStore implements TokenStore
{
	private String filePath;
	
	private String[] headers = new String[] { Constants.ID, Constants.USER_MAIL, Constants.CLIENT_ID, Constants.CLIENT_SECRET, Constants.REFRESH_TOKEN, Constants.ACCESS_TOKEN, Constants.GRANT_TOKEN, Constants.EXPIRY_TIME, Constants.REDIRECT_URL };
	
	/**
	 * Creates an FileStore class instance with the specified parameters.
	 * @param filePath A String containing the absolute file path to store tokens.
	 * @throws Exception if a problem occurs.
	 */
	public FileStore(String filePath) throws Exception
	{
		this.filePath = filePath;
		
		CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(this.filePath), true));
		
		FileReader fileReader = new FileReader(this.filePath);
		
		if (fileReader.read() == -1)
		{
			csvWriter.writeNext(this.headers);
		}
		
		fileReader.close();
		
		csvWriter.flush();
		
		csvWriter.close();
	}

	@Override
	public Token getToken(UserSignature user, Token token) throws SDKException
	{
		try (CSVReader csvReader = new CSVReader(new FileReader(this.filePath)))
		{
			if(token instanceof OAuthToken)
			{
				OAuthToken oauthToken = (OAuthToken) token;
				
				List<String[]> allContents = csvReader.readAll();
	
				for (int index = 1; index < allContents.size(); index++)
				{
					String[] nextRecord = allContents.get(index);
					
					if (checkTokenExists(user.getEmail(), oauthToken, nextRecord))
					{
						String grantToken = (nextRecord[6] != null && !nextRecord[6].isEmpty())? nextRecord[6] : null;
						
						String redirectURL = (nextRecord[8] != null && !nextRecord[8].isEmpty())? nextRecord[8] : null;
						
						oauthToken.setId(nextRecord[0]);
						
						oauthToken.setUserMail(nextRecord[1]);
						
						oauthToken.setClientId(nextRecord[2]);
						
						oauthToken.setClientSecret(nextRecord[3]);
						
						oauthToken.setRefreshToken(nextRecord[4]);
						
						oauthToken.setAccessToken(nextRecord[5]);
						
						oauthToken.setGrantToken(grantToken);
						
						oauthToken.setExpiresIn(String.valueOf(nextRecord[7]));
						
						oauthToken.setRedirectURL(redirectURL);
						
						return oauthToken;
					}
				}
			}
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.GET_TOKEN_FILE_ERROR, ex);
		}

		return null;
	}

	@Override
	public void saveToken(UserSignature user, Token token) throws SDKException
	{
		try
		{
			String[] data = new String[9];
			
			if(token instanceof OAuthToken)
			{
				OAuthToken oauthToken = (OAuthToken) token;
				
				oauthToken.setUserMail(user.getEmail());
				
				this.deleteToken(oauthToken);
				
				data[0] = oauthToken.getId();
				
				data[1] = user.getEmail();
				
				data[2] = oauthToken.getClientId();
				
				data[3] = oauthToken.getClientSecret();
				
				data[4] = oauthToken.getRefreshToken();
				
				data[5] = oauthToken.getAccessToken();
				
				data[6] = oauthToken.getGrantToken();
				
				data[7] = oauthToken.getExpiresIn();
				
				data[8] = oauthToken.getRedirectURL();
			}
			
			try(CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(this.filePath), true));)
			{
				csvWriter.writeNext(data);
				
				csvWriter.flush();
			}
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.SAVE_TOKEN_FILE_ERROR, ex);
		}
	}

	@Override
	public void deleteToken(Token token) throws SDKException
	{
		try (CSVReader csvReader = new CSVReader(new FileReader(new File(this.filePath)));)
		{
			if(token instanceof OAuthToken)
			{
				OAuthToken oauthToken = (OAuthToken) token;
				
				List<String[]> allContents = csvReader.readAll();
	
				for (int index = 1; index < allContents.size(); index++)
				{
					String[] nextRecord = allContents.get(index);
				
					if (checkTokenExists(oauthToken.getUserMail(), oauthToken, nextRecord))
					{
						allContents.remove(index);
						
						break;
					}
				}
				
				CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(this.filePath)));
	
				csvWriter.writeAll(allContents);
				
				csvWriter.close();
			}
		}
		catch(SDKException ex)
		{
			throw ex;
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.DELETE_TOKEN_FILE_ERROR, ex);
		}
	}

	@Override
	public List<Token> getTokens() throws SDKException
	{
		List<Token> tokens = new ArrayList<Token>();
		
		try (CSVReader csvReader = new CSVReader(new FileReader(this.filePath)))
		{
			List<String[]> allContents = csvReader.readAll();
	
			for (int index = 1; index < allContents.size(); index++)
			{
				String[] nextRecord = allContents.get(index);
				
				String grantToken = (nextRecord[6] != null && !nextRecord[6].isEmpty())? nextRecord[6] : null;
				
				String redirectURL = (nextRecord[8] != null && !nextRecord[8].isEmpty())? nextRecord[8] : null;
				
				OAuthToken oauthToken = new OAuthToken.Builder().clientID(nextRecord[2]).clientSecret(nextRecord[3])
						.refreshToken(nextRecord[4]).grantToken(grantToken).build();
				
				oauthToken.setId(nextRecord[0]);
				
				oauthToken.setUserMail(nextRecord[1]);
																
				oauthToken.setAccessToken(nextRecord[5]);
				
				oauthToken.setExpiresIn(String.valueOf(nextRecord[7]));
				
				oauthToken.setRedirectURL(redirectURL);
				
				tokens.add(oauthToken);
			}
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.GET_TOKENS_FILE_ERROR, ex);
		}

		return tokens;
	}

	@Override
	public void deleteTokens() throws SDKException
	{
		try (CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(this.filePath), false));)
		{
			csvWriter.writeNext(this.headers);
			
			csvWriter.flush();
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.DELETE_TOKENS_FILE_ERROR, ex);
		}
		
	}
	
	private Boolean checkTokenExists(String email, OAuthToken oauthToken, String[] row) throws SDKException
	{
		if(email == null)
		{
			throw new SDKException(Constants.USER_MAIL_NULL_ERROR, Constants.USER_MAIL_NULL_ERROR_MESSAGE);
		}
		
		String clientId = oauthToken.getClientId();
		
		String grantToken = oauthToken.getGrantToken();
		
		String refreshToken = oauthToken.getRefreshToken();

		boolean tokenCheck = grantToken != null ? grantToken.equals(row[6]) : refreshToken.equals(row[4]);
		
		if(row[1].equals(email) && row[2].equals(clientId) && tokenCheck)
		{
			return true;
		}

		return false;
	}

	@Override
	public Token getTokenById(String id, Token token) throws SDKException
	{
		try (CSVReader csvReader = new CSVReader(new FileReader(this.filePath)))
		{
			if(token instanceof OAuthToken)
			{
				OAuthToken oauthToken = (OAuthToken) token;
				
				List<String[]> allContents = csvReader.readAll();
				
				boolean isRowPresent = false;
	
				for (int index = 1; index < allContents.size(); index++)
				{
					String[] nextRecord = allContents.get(index);
					
					if (nextRecord[0].equals(id))
					{
						isRowPresent = true;
						
						String grantToken = (nextRecord[6] != null && !nextRecord[6].isEmpty())? nextRecord[6] : null;
						
						String redirectURL = (nextRecord[8] != null && !nextRecord[8].isEmpty())? nextRecord[8] : null;
						
						oauthToken.setId(nextRecord[0]);
						
						oauthToken.setUserMail(nextRecord[1]);
						
						oauthToken.setClientId(nextRecord[2]);
						
						oauthToken.setClientSecret(nextRecord[3]);
						
						oauthToken.setRefreshToken(nextRecord[4]);
						
						oauthToken.setAccessToken(nextRecord[5]);
						
						oauthToken.setGrantToken(grantToken);
						
						oauthToken.setExpiresIn(String.valueOf(nextRecord[7]));
						
						oauthToken.setRedirectURL(redirectURL);

						return oauthToken;
					}
				}
				
				if(!isRowPresent)
				{
					throw new SDKException(Constants.TOKEN_STORE, Constants.GET_TOKEN_BY_ID_FILE_ERROR);
				}
			}
		}
		catch(SDKException ex)
		{
			throw ex;
		}
		catch (Exception ex)
		{
			throw new SDKException(Constants.TOKEN_STORE, Constants.GET_TOKEN_BY_ID_FILE_ERROR, ex);
		}

		return null;
	}

}
