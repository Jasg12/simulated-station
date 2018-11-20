package com.sjsu.cmpe.street.simulatedstation.Dao;

import com.sjsu.cmpe.street.simulatedstation.Entity.Sensor;
import com.sjsu.cmpe.street.simulatedstation.Entity.Smart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


@Repository("mongodb")
public class MysqlSmartDoa implements SmartDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Smart> getAllSmartNodes() {
        // SELECT column_name(s) FROM table_name;
        final String sql = "SELECT id, name, installationdate, Location FROM smart";
        List<Smart> smart = jdbcTemplate.query(sql, new RowMapper<Smart>() {


            @Override
            public Smart mapRow(ResultSet resultSet, int i) throws SQLException {
                Smart smart = new Smart();
                {
                    smart.setId(resultSet.getInt("id"));
                    smart.setName(resultSet.getString("name"));
                    smart.setInstallationdate(resultSet.getInt("installationdate"));
                    smart.setLocation(resultSet.getString("location"));
                    return smart;
                }
            }

        });
        return smart;
    }

    @Override
    public Smart getSmartNodesById(int id) {
        return null;
    }

    @Override
    public void removeSmartNodesById(int id) {

    }

    @Override
    public void updateSmartNodesById(Smart smart) {

    }

    @Override
    public void insertSmartNodesById(Smart smart) {

    }
}
