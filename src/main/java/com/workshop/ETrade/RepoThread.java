package com.workshop.ETrade;

import org.springframework.data.mongodb.repository.MongoRepository;

public class RepoThread<T,V> extends Thread{

    MongoRepository<T,V> mongoRepository;
    T data;
    
    public RepoThread (MongoRepository<T,V> mr, T data) {
        mongoRepository = mr;
        this.data = data;
    }

    public void run() {
        mongoRepository.save(data);
    }

}
