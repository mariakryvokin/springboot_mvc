package com.kryvokin.onlineshop.model.propertueditor;

import com.kryvokin.onlineshop.model.Role;

import java.beans.PropertyEditorSupport;

public class RoleEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Role role = new Role();
        role.setName(text);
        setValue(role);
    }
}
