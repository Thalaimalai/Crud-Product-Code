package com.tlc.crm.common.config;

import com.tlc.validator.TlcModel;

import java.util.Collection;

/**
 * @author Abishek
 * @version 1.0
 */
public interface ConfigManager<T extends TlcModel>
{
    void create(T model);

    void create(Collection<T> model);

    void update(T model);

    void delete(T model);

    boolean exists(T model);

    void delete(Collection<T> models);

    T partialGet(Long id);

    T get(Long id);

    Collection<T> get(Collection<Long> id);

    AuditEntry auditEntry(T model);
}
