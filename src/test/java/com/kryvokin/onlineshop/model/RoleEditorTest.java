package com.kryvokin.onlineshop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleEditorTest {

    private RoleEditor roleEditor = new RoleEditor();
    private Role role = new Role();

    @Test
    void packageTest(){
        assertEquals(roleEditor.getClass().getPackage(), role.getClass().getPackage());
    }

    @Test
    void nameTest(){
        assertEquals(RoleEditor.class.getSimpleName(),Role.class.getSimpleName().concat("Editor"));
    }

}