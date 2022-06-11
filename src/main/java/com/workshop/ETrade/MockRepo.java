package com.workshop.ETrade;

import com.workshop.ETrade.Persistance.Users.StoreBasketDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MockRepo<T,V> implements MongoRepository<T,V> {
    @Override
    public <S extends T> S save(S entity) {
        return null;
    }

    public StoreBasketDTO findByStoreAndUser(String store, String user) {
        return null;
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<T> findById(V v) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(V v) {
        return false;
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Iterable<T> findAllById(Iterable<V> vs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(V v) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends V> vs) {

    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<T> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends T> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends T> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends T, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
