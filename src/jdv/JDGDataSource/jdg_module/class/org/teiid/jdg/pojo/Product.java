package org.teiid.jdg.pojo;
/**
* Maps a relational database table Product to a java object, Product
*
* 
*
* @author	ReverseEngineer
*/
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoField;

public class Product implements Serializable {

	
	public java.lang.Integer m_Itemid;

	
	public java.lang.Double m_Price;

	
	public java.lang.String m_Name;

	
	public java.lang.String m_Description;


	@ProtoField(number = 1, required = true)
	public java.lang.Integer getItemid( ) { 

	public void setItemid( java.lang.Integer itemid) { 

	@ProtoField(number = 4, required = false)
	public java.lang.Double getPrice( ) { 

	public void setPrice( java.lang.Double price) { 

	@ProtoField(number = 2, required = false)
	public java.lang.String getName( ) { 

	public void setName( java.lang.String name) { 

	@ProtoField(number = 3, required = false)
	public java.lang.String getDescription( ) { 

	public void setDescription( java.lang.String description) { 
	public String toString()  {
		StringBuffer output = new StringBuffer();
		output.append("itemid:");
		output.append(getItemid());
		output.append("\n");
		output.append("price:");
		output.append(getPrice());
		output.append("\n");
		output.append("name:");
		output.append(getName());
		output.append("\n");
		output.append("description:");
		output.append(getDescription());
		output.append("\n");

		return output.toString();
	}

} // class Product