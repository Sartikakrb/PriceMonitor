package com.sartika.PriceMonitor.Common;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class NestedRowMapper<T> implements RowMapper<T> {
    private Class<T> mappedClass;

    public NestedRowMapper(Class<T> mappedClass) {
        this.mappedClass = mappedClass;
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {

        T mappedObject = BeanUtils.instantiate(this.mappedClass);
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);

        bw.setAutoGrowNestedPaths(true);

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int index = 1; index <= columnCount; index++) {

            try {
                Class<?> requiredType = Class.forName(metaData.getColumnClassName(index));

                String column = JdbcUtils.lookupColumnName(metaData, index);
                Object value = JdbcUtils.getResultSetValue(rs, index, requiredType);
                if (java.sql.Timestamp.class.equals(requiredType) || java.util.Date.class.equals(
                        requiredType)) {
                    value = (value != null) ? dateFormat.format(dateFormat.parse(value + "")) : "";
                }

                bw.setPropertyValue(column, value);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mappedObject;
    }
}
