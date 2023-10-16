package org.example.ind11api.mapper;

/**
 * T - entity type
 * R - DTO type
 *
 * @param <T>
 * @param <R>
 */
public interface EntityMapper<T, R> {

    T toEntity(R dto);

    R toDto(T entity);
}
