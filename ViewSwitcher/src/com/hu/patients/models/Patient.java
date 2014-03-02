package com.hu.patients.models;

import java.util.Random;

import com.hu.patients.R;

public class Patient {
	public String name;
	public boolean sex; // false for female or true for male
	public boolean fake;// determines whether the information is correct or not
	public int partners; // number of partners
	// these data type will probably be enums with strings in the future structs
	public String practice; // sexual practice
	public String symptoms;
	public String disease;
	public String reason;
	public String treatment;
	public int points;
	public int waste;

	public Patient(String ame, boolean ex, String ractice, String ymptoms,
			String isease, String reatment) {
		name = ame;
		sex = ex;
		practice = ractice;
		symptoms = ymptoms;
		disease = isease;
		reason = "";
		fake = false;
	}
	public Patient(String ame, boolean ex, String ractice, String ymptoms,
			String isease, boolean fake) {
		name = ame;
		sex = ex;
		practice = ractice;
		symptoms = ymptoms;
		disease = isease;
		reason = "";
		fake = true;
	}

	public Patient(String ame, boolean ex, String ractice, String ymptoms,
			String isease,String reatment, String eason) {
		name = ame;
		sex = ex;
		practice = ractice;
		symptoms = ymptoms;
		disease = isease;
		reason = eason;
		fake = true;
	}

	/*
	 * Output the String values based of the object based on the info
	 */
	public String toString(int info){
switch(info)
{
case -1: return	"Name: " + name+
		"\nFake: " + fake +
		"\nSex: " + getSex()+
		"\nPractice: " + practice+
		"\nSymptoms: " + symptoms+
		"\nDisease: " + disease;
case 0: return	"Name: " + name+
		"\nSex: " + getSex()+
		"\nPractice: " + practice+
		"\nDisease: " + disease;
case 1: return	"Name: " + name+
		"\nSex: " + getSex()+
		"\nPractice: " + practice+
		"\nDisease: " + disease;
case 2: return	"Name: " + name+
		"\nSex: " + getSex()+
		"\nPractice: " + practice+
		"\nDisease: " + disease +
		"\nSymptoms: " + symptoms;
case 3: return	"Name: " + name+
				"\nSex: " + getSex()+
				"\nPractice: " + practice+
				"\nDisease: " + disease+
				"\nSymptoms: " + symptoms;
default: return "Name:" + name;
}
	}

	public String getSex() {
		if (sex) {
			return "male";
		} else {
			return "female";
		}
	}
}
