package com.kryvokin.onlineshop.model;

import java.beans.PropertyEditorSupport;

public class RoleEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Role role = new Role();
        role.setName(text);
        setValue(role);
    }
}
