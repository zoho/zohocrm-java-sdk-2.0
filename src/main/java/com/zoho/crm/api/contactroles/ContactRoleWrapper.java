package com.zoho.crm.api.contactroles;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;

public class ContactRoleWrapper implements Model
{
	private String contactRole;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of contactRole
	 * @return A String representing the contactRole
	 */
	public String getContactRole()
	{
		return  this.contactRole;

	}

	/**
	 * The method to set the value to contactRole
	 * @param contactRole A String representing the contactRole
	 */
	public void setContactRole(String contactRole)
	{
		 this.contactRole = contactRole;

		 this.keyModified.put("Contact_Role", 1);

	}

	/**
	 * The method to check if the user has modified the given key
	 * @param key A String representing the key
	 * @return An Integer representing the modification
	 */
	public Integer isKeyModified(String key)
	{
		if((( this.keyModified.containsKey(key))))
		{
			return  this.keyModified.get(key);

		}
		return null;

	}

	/**
	 * The method to mark the given key as modified
	 * @param key A String representing the key
	 * @param modification An Integer representing the modification
	 */
	public void setKeyModified(String key, Integer modification)
	{
		 this.keyModified.put(key, modification);

	}
}