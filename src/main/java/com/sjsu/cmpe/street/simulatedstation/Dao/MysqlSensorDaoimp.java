package com.sjsu.cmpe.street.simulatedstation.Dao;


import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


@Repository("mysql")

public class MysqlSensorDaoimp implements SensorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Sensor> getAllSensors() {
       // SELECT column_name(s) FROM table_name;
        final String sql = "SELECT id, name, Type, Location FROM sensors";
        List<Sensor> sensors = jdbcTemplate.query(sql, new RowMapper<Sensor>() {

            @Override
            public Sensor mapRow(ResultSet resultSet, int i) throws SQLException {
                Sensor sensor = new Sensor();
                {
                    sensor.setId(resultSet.getInt("id"));
                    sensor.setName(resultSet.getString("name"));
                    sensor.setType(resultSet.getString("type"));
                    sensor.setLocation(resultSet.getString("location"));
                    return sensor;
                } }
        });
        return sensors;
    }

    @Override
    public Sensor getSensorById(int id) {
        return null;
    }

    @Override

    public void removeSensorById(int id) {

    }

    @Override
    public void updateSensor(Sensor sensor) {

    }

    @Override
    public void insertSensor(Sensor sensor) {

    }

}
