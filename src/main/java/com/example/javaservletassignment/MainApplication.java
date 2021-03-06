package com.example.javaservletassignment;

import com.example.javaservletassignment.annotation.Column;
import com.example.javaservletassignment.annotation.Entity;
import com.example.javaservletassignment.annotation.Id;
import com.example.javaservletassignment.util.ConnectionHelper;
import com.example.javaservletassignment.util.SQLConstant;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class MainApplication {
    public static void main(String[] args) {
        // Quét toàn bộ project xem class nào đc đánh dấu là
        Reflections reflections = new Reflections("com.example.javaservletassignment");
        // @Table, trả về 1 set tập hợp các class đc đánh dấu
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Entity.class);
        for (Class<?> clazz : annotated) {
            // thực hiện migrate cho class đó
            doMigrate(clazz);
        }
    }

    static void doMigrate(Class clazz) {
        System.out.println("====================");
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println("Migrating class: " + clazz.getName());
        if (!clazz.isAnnotationPresent(Entity.class)) {
            System.err.println("Class không đc xác định là table. Bỏ qua migration!");
            return;
        }

        // chắc chắn class đã đc đánh dấu annotation là @Table
        // @Table
        // Lấy thông tin annotation ra
        Entity annotationTable = (Entity) clazz.getAnnotation(Entity.class);

        String annotationTableName = annotationTable.tableName();
        String tableName = clazz.getSimpleName().toLowerCase() + "s";
        if (annotationTableName != null && annotationTableName.length() > 0) {
            tableName = annotationTableName;
        }

        stringBuilder.append(SQLConstant.CREATE_TABLE);
        stringBuilder.append(SQLConstant.SPACE);
        stringBuilder.append(tableName);
        stringBuilder.append(SQLConstant.SPACE);
        stringBuilder.append(SQLConstant.OPEN_PARENTHESES);

        // trả về danh sách các thuộc tính.
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String fieldType = fields[i].getType().getSimpleName();
            fields[i].setAccessible(true);
            if (fields[i].isAnnotationPresent(Column.class)) {
                Column annotationColumn = fields[i].getAnnotation(Column.class);
                if (annotationColumn.columnName().length() > 0) {
                    fieldName = annotationColumn.columnName();
                }
                if (annotationColumn.columnType().length() > 0) {
                    fieldType = annotationColumn.columnType();
                }
            }

            stringBuilder.append(fieldName);
            stringBuilder.append(SQLConstant.SPACE);
            stringBuilder.append(fieldType);

            // Check xem trường có phải là khóa chính hay không
            if (fields[i].isAnnotationPresent(Id.class)) {
                stringBuilder.append(SQLConstant.SPACE);
                stringBuilder.append(SQLConstant.PRIMARY_KEY);
                // lấy ra thông tin để check ttinh auto increment
                Id annotationId = fields[i].getAnnotation(Id.class);
                if (annotationId.autoIncrement()) {
                    stringBuilder.append(SQLConstant.SPACE);
                    stringBuilder.append(SQLConstant.AUTO_INCREMENT);
                }
            }
            stringBuilder.append(SQLConstant.COMMA);
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.append(SQLConstant.CLOSE_PARENTHESES);

        Connection cnn = null;
        try {
            cnn = ConnectionHelper.getConnection();
            Statement stt = cnn.createStatement();
            try {
                System.out.println("Try to drop table: '" + tableName + "' before create");
                stt.execute(SQLConstant.DROP_TABLE + SQLConstant.SPACE + tableName);
                System.out.println("Drop table '" + tableName + "' successfully");
            } catch (Exception ex) {
                System.err.println("Drop table failed, errors: " + ex.getMessage());
            }
            System.out.println("Try to execute statement: '" + stringBuilder.toString() + "'");
            stt.execute(stringBuilder.toString());
            System.out.println("Create table success!");
        } catch (SQLException e) {
            System.err.println("Create table failed, errors: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
