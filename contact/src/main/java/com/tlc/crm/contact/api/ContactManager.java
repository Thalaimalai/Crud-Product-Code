package com.tlc.crm.contact.api;

import com.tlc.crm.common.config.AuditEntry;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.sql.api.dml.Table;

import java.util.Collection;

/**
 * @author Abishek
 * @version 1.0
 */
public class ContactManager implements ConfigManager<Contact>
{
    private static class Instance
    {
        private static final ContactManager INSTANCE = new ContactManager();
    }

    private ContactManager() {}

    public static ContactManager getInstance()
    {
        return Instance.INSTANCE;
    }

    @Override
    public void create(Contact model)
    {
        System.out.println("Create called");
        System.out.println("Fg :"+ Table.get("FGOrganisation"));
        System.out.println(Table.get("StudentRecord"));
    }

    @Override
    public void create(Collection<Contact> model) {

    }

    @Override
    public void update(Contact model)
    {

    }

    @Override
    public void delete(Contact model)
    {

    }

    @Override
    public boolean exists(Contact model)
    {
        return false;
    }

    @Override
    public void delete(Collection<Contact> models)
    {

    }

    @Override
    public Contact partialGet(Long id)
    {
        return null;
    }

    @Override
    public Contact get(Long id)
    {
        return null;
    }

    @Override
    public Collection<Contact> get(Collection<Long> id)
    {
        return null;
    }

    @Override
    public AuditEntry auditEntry(Contact model)
    {
        return null;
    }

}
