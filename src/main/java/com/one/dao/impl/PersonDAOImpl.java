package com.one.dao.impl;

import com.mongodb.WriteResult;
import com.one.dao.PersonDAO;
import com.one.entity.Person;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class PersonDAOImpl implements PersonDAO {

	private MongoOperations mongoTemplate;
	private static final String PERSON_COLLECTION = "person";
	
	public PersonDAOImpl(MongoOperations mongoTemplate){
		this.mongoTemplate=mongoTemplate;
	}
	
	@Override
	public void create(Person p) {
		this.mongoTemplate.insert(p, PERSON_COLLECTION);
	}

	@Override
	public Person readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoTemplate.findOne(query, Person.class, PERSON_COLLECTION);
	}

	@Override
	public void update(Person p) {
		this.mongoTemplate.save(p, PERSON_COLLECTION);
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoTemplate.remove(query, Person.class, PERSON_COLLECTION);
		return 1;
	}

}
