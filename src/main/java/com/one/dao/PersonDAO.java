package com.one.dao;

import com.one.entity.Person;

public interface PersonDAO {

	public void create(Person p);
	
	public Person readById(String id);
	
	public void update(Person p);
	
	public int deleteById(String id);
}
