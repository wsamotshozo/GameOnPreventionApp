package com.hu.patients.models;

import java.util.Random;

import com.hu.patients.R;

public class Patient {
	public String name;
	public boolean sex; //false for female  or true for  male
	public boolean fake;//determines whether the information is correct or not
	public int partners;	//number of partners
	//these data type will probably be enums with strings in the future structs
	public String practice; //sexual practice
	public String symptoms;
	public String disease;
	public Patient(){};
	public Patient(String ame, boolean ex, String ractice, String ymptoms, String isease){
		name = ame;
		sex = ex;
		practice = ractice;
		symptoms = ymptoms;
		disease = isease; 
	}
	public Patient setFake(boolean ake, int difficulty)
	{
		if(ake)
		{
			fake = true;
			//disease = "codon Zero";
		}
		else
		{
			fake = false;
			//disease = "Herpes";
		}
		return this;
	}
	public String toString(){
		return	"Name: " + name+
				"\nfake" + fake +
				"\nSex: " + getSex()+
				"\nPractice: " + practice+
				"\nSymptoms: " + symptoms+
				"\nDisease: " + disease;
	}
	public String getSex(){
		if(sex)
		{
			return "male";
		}
		else
		{
			return "female";
		}
	}
}
