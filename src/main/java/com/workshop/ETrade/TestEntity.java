package com.workshop.ETrade;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class TestEntity {

    @Id
    private String id;

    private String testString;

    private List<String> list;

    private TestEntity2 pHistory;



    public TestEntity(String id, String testString) {
        this.id = id;
        this.testString = testString;
        list = new ArrayList<>();
        pHistory = new TestEntity2(testString);
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public TestEntity2 getpHistory() {
        return pHistory;
    }

    public void setpHistory(TestEntity2 pHistory) {
        this.pHistory = pHistory;
    }
}
