package com.encheres.encheres.classes;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.encheres.encheres.classes.*;

public class ObjetBDD {
    public static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://containers-us-west-114.railway.app:5996/railway";
            String user = "postgres";
            String password = "gXKcWUHc4v3mmO4ffWr6@containers-us-west-114.railway.app:5996/railway\";\r\n"
            		+ "            String user = \"postgres\";";
            Connection connect = DriverManager.getConnection(url, user, password);
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPrimaryKey() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getAnnotation(DBColumn.class).primaryKey() == true) {
                return fields[i].getName();
            }
        }
        return "";
    }

    public Class[] StatementSetterParams(Field target) {
        Class[] params = new Class[2];
        params[0] = int.class;
        params[1] = target.getType();
        return params;
    }

    public void SetValueToObjectField(ResultSet rs, Object object, Field field) throws Exception {
        try {
            String SetValueMethodName = "set" + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);
            String ResultSetMethodName = "get" + field.getType().getSimpleName().substring(0, 1).toUpperCase()
                    + field.getType().getSimpleName().substring(1);
            Method SetValueMethod = this.getClass().getMethod(SetValueMethodName, field.getType());
            Method ResultSetMethod = rs.getClass().getMethod(ResultSetMethodName, String.class);
            SetValueMethod.invoke(object, ResultSetMethod.invoke(rs, field.getName()));
        } catch (Exception e) {
            throw e;
        }
    }

    public void create(Connection connect) throws Exception {
        connect.setAutoCommit(false);
        Field[] fields = this.getClass().getDeclaredFields();
        String columns = "";
        String values = "";
        int count = 1;
        PreparedStatement pstateM = null;
        try {
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getAnnotation(DBColumn.class).primaryKey() == false) {
                    columns = columns + fields[i].getName();
                    values = values + "?";
                    if (i != (fields.length - 1)) {
                        columns = columns + ",";
                        values = values + ",";
                    }
                }
            }
            String table = this.getClass().getAnnotation(TableName.class).table();
            if (table.compareTo("") == 0)
                table = this.getClass().getSimpleName();
            pstateM = connect.prepareStatement("insert into " + table + "(" + columns + ") values(" + values + ")");
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getAnnotation(DBColumn.class).primaryKey() == false) {
                    String StatementMethodName = "set"
                            + fields[i].getType().getSimpleName().substring(0, 1).toUpperCase()
                            + fields[i].getType().getSimpleName().substring(1);
                    String GetValueMethodName = "get" + fields[i].getName().substring(0, 1).toUpperCase()
                            + fields[i].getName().substring(1);
                    Method StatementMethod = pstateM.getClass().getMethod(StatementMethodName,
                            StatementSetterParams(fields[i]));
                    StatementMethod.setAccessible(true);
                    Method GetValueMethod = this.getClass().getMethod(GetValueMethodName);
                    StatementMethod.invoke(pstateM, count, GetValueMethod.invoke(this));
                    count++;
                }
            }
            pstateM.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (pstateM != null)
                pstateM.close();
        }
    }

    public void delete(Connection connect) throws Exception {
        PreparedStatement pstateM = null;
        try {
            String table = this.getClass().getAnnotation(TableName.class).table();
            if (table.compareTo("") == 0)
                table = this.getClass().getSimpleName();
            String primaryKey = getPrimaryKey();
            pstateM = connect.prepareStatement("delete from " + table + " where " + primaryKey + "= ?");
            String GetValueMethodName = "get" + primaryKey.substring(0, 1).toUpperCase() + primaryKey.substring(1);
            Method GetValueMethod = this.getClass().getMethod(GetValueMethodName);
            pstateM.setInt(1, (int) GetValueMethod.invoke(this));
            pstateM.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (pstateM != null)
                pstateM.close();
        }
    }

    public ArrayList<Object> findSpecific(Connection connect, String cible) throws Exception {
        PreparedStatement pstateM = null;
        ResultSet rs = null;
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            String table = this.getClass().getAnnotation(TableName.class).table();
            if (table.compareTo("") == 0)
                table = this.getClass().getSimpleName();
            pstateM = connect.prepareStatement("select * from " + table + " where " + cible + "= ?");
            String GetValueMethodName = "get" + cible.substring(0, 1).toUpperCase() + cible.substring(1);
            Method GetValueMethod = this.getClass().getMethod(GetValueMethodName);
            pstateM.setInt(1, (int) GetValueMethod.invoke(this));
            rs = pstateM.executeQuery();
            ArrayList<Object> elements = new ArrayList<Object>();

            while (rs.next()) {
                Object element = this.getClass().newInstance();
                for (int i = 0; i < fields.length; i++) {
                    SetValueToObjectField(rs, element, fields[i]);
                }
                elements.add(element);
            }
            return elements;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (pstateM != null)
                pstateM.close();
        }
    }

    public ArrayList<Object> find(Connection connect) throws Exception {
        PreparedStatement pstateM = null;
        ResultSet rs = null;
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            ArrayList<Object> response = new ArrayList<Object>();
            String table = this.getClass().getAnnotation(TableName.class).table();
            if (table.compareTo("") == 0)
                table = this.getClass().getSimpleName();
            pstateM = connect.prepareStatement("select * from " + table);
            rs = pstateM.executeQuery();
            while (rs.next()) {
                Object insertion = this.getClass().newInstance();
                for (int i = 0; i < fields.length; i++) {
                    SetValueToObjectField(rs, insertion, fields[i]);
                }
                response.add(insertion);
            }
            return response;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null)
                rs.close();
            if (pstateM != null)
                pstateM.close();
        }
    }

    public void update(Connection connect) throws Exception {
        Field[] fields = this.getClass().getDeclaredFields();
        PreparedStatement pstateM = null;
        String setting = "";
        try {
            String primaryKey = "";
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getAnnotation(DBColumn.class).primaryKey() == false) {
                    setting = setting + fields[i].getName() + "= ?";
                    if (i != (fields.length - 1)) {
                        setting = setting + ",";
                    }
                } else {
                    primaryKey = fields[i].getName();
                }
            }
            String table = this.getClass().getAnnotation(TableName.class).table();
            if (table.compareTo("") == 0)
                table = this.getClass().getSimpleName();
            pstateM = connect.prepareStatement("update " + table + " set " + setting + " where " + primaryKey + "= ?");
            int count = 1;
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getName() != primaryKey) {
                    String StatementMethodName = "set"
                            + fields[i].getType().getSimpleName().substring(0, 1).toUpperCase()
                            + fields[i].getType().getSimpleName().substring(1);
                    Class[] params = new Class[2];
                    Method StatementMethod = pstateM.getClass().getMethod(StatementMethodName,
                            StatementSetterParams(fields[i]));
                    String GetValueMethodName = "get" + fields[i].getName().substring(0, 1).toUpperCase()
                            + fields[i].getName().substring(1);
                    Method GetValueMethod = this.getClass().getMethod(GetValueMethodName);
                    StatementMethod.setAccessible(true);
                    StatementMethod.invoke(pstateM, count, GetValueMethod.invoke(this));
                    count++;
                }
            }
            String GetIdMethodName = "get" + primaryKey.substring(0, 1).toUpperCase() + primaryKey.substring(1);
            Method GetIdMethod = this.getClass().getMethod(GetIdMethodName);
            Field fieldId = this.getClass().getDeclaredField(primaryKey);
            String StatementMethodName = "set" + fieldId.getType().getSimpleName().substring(0, 1).toUpperCase()
                    + fieldId.getType().getSimpleName().substring(1);
            Method StatementMethod = pstateM.getClass().getMethod(StatementMethodName, StatementSetterParams(fieldId));
            StatementMethod.setAccessible(true);
            StatementMethod.invoke(pstateM, count, GetIdMethod.invoke(this));
            pstateM.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (pstateM != null)
                pstateM.close();
        }
    }
}
