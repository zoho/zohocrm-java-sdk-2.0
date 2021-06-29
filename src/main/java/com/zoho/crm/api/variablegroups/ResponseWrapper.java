package com.zoho.crm.api.variablegroups;

import com.zoho.crm.api.util.Model;
import java.util.HashMap;
import java.util.List;

public class ResponseWrapper implements Model, ResponseHandler
{
	private List<VariableGroup> variableGroups;

	private HashMap<String, Integer> keyModified = new HashMap<String, Integer>();


	/**
	 * The method to get the value of variableGroups
	 * @return An instance of List<VariableGroup>
	 */
	public List<VariableGroup> getVariableGroups()
	{
		return  this.variableGroups;

	}

	/**
	 * The method to set the value to variableGroups
	 * @param variableGroups An instance of List<VariableGroup>
	 */
	public void setVariableGroups(List<VariableGroup> variableGroups)
	{
		 this.variableGroups = variableGroups;

		 this.keyModified.put("variable_groups", 1);

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