package com.one;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.one.dao.PersonDAO;
import com.one.entity.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * @author One
 * @description
 * @date 2018/10/17
 */
public class SpringXMLMongoDbLab {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        PersonDAO personDao = ctx.getBean("personDao", PersonDAO.class);
        Person person = new Person();
        person.setId("3");
        person.setName("One");
        person.setAddress("浙江宁波");
        personDao.create(person);
        System.out.println("新增完成 : " +person);

        Person findPerson = personDao.readById("3");
        System.out.println("查询完成 :" + findPerson);
        Person newPerson = new Person();
        newPerson.setId("3");
        newPerson.setName("one");
        newPerson.setAddress("杭州");
        personDao.update(newPerson);
        System.out.println(" 更新完成 :" + newPerson);

        int i = personDao.deleteById("3");
        System.out.println("删除成功 : " + i);

    }

    private static void updatePerson(MongoTemplate mongoTemplate) {
        Query query = new Query().addCriteria(Criteria.where("id").is("1"));
        DBObject dbObject = new BasicDBObject().append("address","北京").append("name","haha");
        Update update = new BasicUpdate(dbObject);
        WriteResult writeResult = mongoTemplate.upsert(query, update, Person.class, "person");
        System.out.println("更新完成:" + writeResult);
        // { "_id" : "1", "address" : "北京" }
    }

    private static void deletePerson(MongoTemplate mongoTemplate) {
        Query query = new Query().addCriteria(Criteria.where("id").is("1"));
        WriteResult writeResult = mongoTemplate.remove(query, Person.class, "person");
        System.out.println("删除完成: " + writeResult);
    }

    private static void findPerson(MongoTemplate mongoTemplate) {
        Query query = new Query().addCriteria(Criteria.where("id").is("1"));
        List<Person> persons = mongoTemplate.find(query, Person.class, "person");
        System.out.println("查询完成: "+persons);
        // [Person{id='1', name='闻人', address='北京西城'}]
    }

    public static void createPerson(MongoTemplate template) {
        Person person = new Person();
        person.setId("1");
        person.setName("闻人");
        person.setAddress("北京西城");
        template.insert(person);
        System.out.println(" 新增完成: "+person);
    }
}
