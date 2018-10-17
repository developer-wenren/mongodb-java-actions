package com.one;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.one.entity.User;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Hello world!
 * @author One
 */
public class MongoDbLab {
    public static void main2(String[] args) {
        Document doc = new Document();
        User u = new User();
        u.setId(2);
        u.setName("Wenren");
        u.setEmployee(true);
        doc.append("_id", u.getId());
        doc.append("name", u.getName());
        doc.append("isEmployee", u.isEmployee());
        MongoClient mongoClient = new MongoClient("47.96.24.46", 27017);
        MongoDatabase twoDB = mongoClient.getDatabase("two");
        MongoCollection<Document> one = twoDB.getCollection("one");
        one.insertOne(doc);
    }

    public static void main(String[] args) {
        // 连接 远程 MonogDB 数据库
        User u = new User();
        u.setId(2);
        u.setName("Wenren");
        u.setEmployee(true);

        // 新增记录
        createUser(u);
        // 修改记录
        updateUser(u);
        // 查看记录
        findUser(u);
        // 删除记录
        deleteUser(u);
    }

    private static Document createDocumentUser(User user) {
        Document doc = new Document();
        doc.append("_id", user.getId());
        doc.append("name", user.getName());
        doc.append("isEmployee", user.isEmployee());
        return doc;
    }

    /**
     * 新增单个用户
     * @param user
     */
    private static void createUser(User user) {
        // 连接 远程 MonogDB 数据库
        MongoClient mongoClient = new MongoClient("47.96.24.46", 27017);
        MongoDatabase oneDB = mongoClient.getDatabase("one");
        MongoCollection<Document> users = oneDB.getCollection("users");
        Document doc = createDocumentUser(user);
        users.insertOne(doc);
        System.out.println(doc);
        // Document{{_id=2, name=Wenren, isEmployee=true}}
    }

    /**
     * 删除单个用户
     * @param user
     */
    private static void deleteUser(User user) {
        MongoClient mongoClient = new MongoClient("47.96.24.46", 27017);
        MongoDatabase oneDB = mongoClient.getDatabase("one");
        MongoCollection<Document> users = oneDB.getCollection("users");
        // 删除记录
        Bson delfilter = Filters.eq("_id", user.getId());
        DeleteResult deleteResult = users.deleteOne(delfilter);
        System.out.println(deleteResult);
        // AcknowledgedDeleteResult{deletedCount=1}
    }

    /**
     * 更新单个用户
     * @param user
     */
    private static void updateUser(User user) {
        // 连接 远程 MonogDB 数据库
        MongoClient mongoClient = new MongoClient("47.96.24.46", 27017);
        MongoDatabase oneDB = mongoClient.getDatabase("one");
        MongoCollection<Document> users = oneDB.getCollection("users");
        // 修改记录
        Bson upfilter = Filters.eq("_id", 2);
        Document newUser = createDocumentUser(user);
        UpdateResult updateResult = users.replaceOne(upfilter, newUser);
        System.out.println(updateResult);
        //AcknowledgedUpdateResult{matchedCount=1, modifiedCount=1, upsertedId=null}
    }

    /**
     * 查询单个用户
     * @param user
     */
    private static void findUser(User user) {
        // 连接 远程 MonogDB 数据库
        MongoClient mongoClient = new MongoClient("47.96.24.46", 27017);
        MongoDatabase oneDB = mongoClient.getDatabase("one");
        MongoCollection<Document> users = oneDB.getCollection("users");
        FindIterable<Document> documents = users.find();
        for (Document document : documents) {
            System.out.println(document);
        }
//        Document{{_id=5bc526b545ad181b7ac548f6, user=null}}
//        Document{{_id=2, name=Wenren, isEmployee=true}}
    }

}
