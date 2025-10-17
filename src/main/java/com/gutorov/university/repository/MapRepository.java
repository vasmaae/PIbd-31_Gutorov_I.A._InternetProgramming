package com.gutorov.university.repository;

import com.gutorov.university.entity.BaseEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public abstract class MapRepository<E extends BaseEntity> implements CommonRepository<E, UUID> {
    private final ConcurrentNavigableMap<UUID, E> entities = new ConcurrentSkipListMap<>();

    protected MapRepository() {
    }

    private boolean isNew(E entity) {
        return Objects.isNull(entity.getId());
    }

    private E create(E entity) {
        final UUID id = UUID.randomUUID();
        entity.setId(id);
        entities.put(id, entity);
        return entity;
    }

    private E update(E entity) {
        if (findById(entity.getId()).isEmpty())
            return null;
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public Optional<E> findById(UUID id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public E save(E entity) {
        if (isNew(entity))
            return create(entity);
        return update(entity);
    }

    @Override
    public void delete(E entity) {
        if (findById(entity.getId()).isEmpty())
            return;
        entities.remove(entity.getId());
    }

    @Override
    public void deleteAll() {
        entities.clear();
    }
}
