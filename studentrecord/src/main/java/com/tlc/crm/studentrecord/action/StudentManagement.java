package com.tlc.crm.studentrecord.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonArray;
import com.tlc.commons.json.JsonObject;
import com.tlc.crm.common.action.secure.CrmConfigAction;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.studentrecord.api.validation.Validator;
import com.tlc.crm.studentrecord.model.Student;
import com.tlc.crm.studentrecord.api.StudentManager;
import com.tlc.validator.type.Group.Create;
import com.tlc.validator.type.Group.Update;
import com.tlc.web.WebAction;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Providing API service to create, update, select and delete student details.
 *
 * @author ThalaimalaiPandiyanT
 */
@WebAction(path = "/student/mgmt")
public class StudentManagement extends CrmConfigAction<Student> {

    /**
     * Construct json object.
     *
     * @param model
     */
    @Override
    public JsonObject construct(final Student model) {
        final JsonObject jsonObject = Json.object();

        jsonObject.put("id", model.id());
        jsonObject.put("rollNumber", model.getRollNumber());
        jsonObject.put("name", model.getName());
        jsonObject.put("departmentName", model.getDepartmentName());
        jsonObject.put("email", model.getEmail());

        return jsonObject;
    }

    /**
     * Get instance of config manager.
     */
    @Override
    public ConfigManager getConfigManager() {
        return StudentManager.getInstance();
    }

    /**
     * Construct student from json object.
     *
     * @param jsonObject
     */
    @Override
    public Student construct(final JsonObject jsonObject) {
        final Long id = jsonObject.optLong("id", 0);
        final String rollNumber = jsonObject.optString("rollNumber", null);
        final String name = jsonObject.optString("name", null);
        final String departmentName = jsonObject.optString("departmentName", null);
        final String email = jsonObject.optString("email", null);
        final String type = jsonObject.getString("type");

        final Student student = new Student(id, rollNumber, name, departmentName, email);

        if ("create".equals(type) || "update".equals(type)) {
            Validator.validate(student, getGroups(type));
        }
        return student;
    }

    /**
     * Construct arrays of student details from json object.
     *
     * @param jsonObject
     */
    @Override
    public Collection<Student> constructArray(final JsonObject jsonObject) {
        final Collection<Student> studentList = new ArrayList<>();
        final JsonArray jsonArray = jsonObject.getJsonArray("data");

        for (int index = 0; index < jsonArray.size(); index++) {
            final JsonObject dataObject = jsonArray.getJsonObject(index);
            final Long id = dataObject.optLong("id", 0);
            final String rollNumber = dataObject.optString("rollNumber", null);
            final String name = dataObject.optString("name", null);
            final String departmentName = dataObject.optString("departmentName", null);
            final String email = dataObject.optString("email", null);
            final String type = jsonObject.getString("type");

            final Student student = new Student(id, rollNumber, name, departmentName, email);

            if ("create".equals(type) || "update".equals(type)) {

                try {
                    Validator.validate(student, getGroups(type));
                    studentList.add(student);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
        return studentList;
    }

    /**
     * Get the groups for validation.
     *
     * @param type
     * @return
     */
    private Class getGroups(final String type) {

        if ("create".equals(type)) {
            return Create.class;
        } else
            return Update.class;
    }
}
