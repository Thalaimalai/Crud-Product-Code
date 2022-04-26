package com.tlc.crm.studentrecord.api;

import com.tlc.commons.code.ErrorCode;
import com.tlc.crm.common.config.AuditEntry;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.studentrecord.model.Student;
import com.tlc.crm.studentrecord.status.StudentErrorCodes;
import com.tlc.sql.SQLAccess;
import com.tlc.sql.api.DataContainer;
import com.tlc.sql.api.Row;
import com.tlc.sql.api.dml.*;
import com.tlc.sql.api.ds.OrgDataStore;
import java.util.*;

/**
 * Manages student related DB actions.
 *
 * @author ThalaimalaiPandiyanT
 */
public class StudentManager implements ConfigManager<Student> {

    private static final Table TABLE = Table.get("StudentRecord");
    private static final OrgDataStore ORG_DATA_STORE = getOrgDataStore();

    private static OrgDataStore getOrgDataStore() {
        return SQLAccess.get().getOrgDataStore(1L);
    }

    /**
     * Creates the instance for student manager.
     */
    private static class Instance {
        private static final StudentManager INSTANCE = new StudentManager();
    }

    private StudentManager() {
    }

    public static StudentManager getInstance() {
        return StudentManager.Instance.INSTANCE;
    }

    /**
     * Create a new student detail in a database.
     *
     * @param model
     */
    @Override
    public void create(final Student model) {}

    /**
     * Create a collection of student detail in a database.
     *
     * @param model
     */
    @Override
    public void create(final Collection<Student> model) {
        final DataContainer dataContainer = DataContainer.create();

        for (Student student : model) {
            final Row row = new Row(TABLE);

            setColumns(row, student);
            dataContainer.addNewRow(row);
            ORG_DATA_STORE.commitChanges(dataContainer);
        }
    }

    /**
     * Update the student details.
     *
     * @param model
     */
    @Override
    public void update(final Student model) {
        final Row row = new Row(TABLE, model.id());
        final DataContainer dataContainer = DataContainer.create();

        if (exists(model)) {
            setColumns(row, model);
            dataContainer.updateRow(row);
            ORG_DATA_STORE.commitChanges(dataContainer);
        } else {
            throw ErrorCode.get(StudentErrorCodes.INVALID_ID);
        }
    }

    /**
     * Set the column with its value in a row.
     *
     * @param row
     * @param model
     */
    private void setColumns(final Row row, final Student model) {
        row.set("ROLLNUMBER", model.getRollNumber());
        row.set("NAME", model.getName());
        row.set("DEPARTMENT_NAME", model.getDepartmentName());
        row.set("EMAIL", model.getEmail());
    }

    /**
     * Delete the student details in a database.
     *
     * @param model
     */
    @Override
    public void delete(final Student model) {

        if (exists(model)) {
            delete(List.of(model));
        } else {
            throw ErrorCode.get(StudentErrorCodes.INVALID_ID);
        }
    }

    /**
     * Checks whether the student detail exists or not.
     *
     * @param model
     */
    @Override
    public boolean exists(final Student model) {
        return ORG_DATA_STORE.get(TABLE, model.id()) != null ? true : false;
    }

    /**
     * Delete collection of student details.
     *
     * @param models
     */
    @Override
    public void delete(final Collection<Student> models) {
        final Collection<Long> studentId = new HashSet<>();

        models.forEach(model -> studentId.add(model.id()));
        ORG_DATA_STORE.delete(TABLE, studentId);
    }

    /**
     * Get id of the student.
     *
     * @param id
     */
    @Override
    public Student partialGet(final Long id) {
        final Student student = new Student();

        student.setId(id);
        return student;
    }

    /**
     * Get the student detail of particular id.
     *
     * @param id
     */
    @Override
    public Student get(final Long id) {
        final Student student = new Student();
        final Row row = ORG_DATA_STORE.get(TABLE, id);

        student.setId(row.getPKValue());
        student.setRollNumber(row.get("ROLLNUMBER").toString());
        student.setName(row.get("NAME"));
        student.setDepartmentName(row.get("DEPARTMENT_NAME"));
        student.setEmail(row.get("EMAIL"));

        return student;
    }

    /**
     * Fetch the details of the student.
     *
     * @param ids
     */
    @Override
    public Collection<Student> get(final Collection<Long> ids) {
        return null;
    }

    /**
     * Get audit entry of student.
     *
     * @param model
     */
    @Override
    public AuditEntry auditEntry(Student model) {
        return null;
    }
}
