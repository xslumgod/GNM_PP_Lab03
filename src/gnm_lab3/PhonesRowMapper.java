/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gnm_lab3;

/**
 *
 * @author dmitriy
 */
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Класс загрузки данных в объект Phones из считанной записи таблицы БД
 *
 */
public class PhonesRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        PhonesResultSetExtractor extractor = new PhonesResultSetExtractor();
        return extractor.extractData(rs);
    }

    /**
     * Класс загрузки данных в объект данных из считанной записи таблицы
     *
     */
    class PhonesResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            PhoneBook phones = new PhoneBook();
            phones.setId(rs.getInt("id"));
            phones.setName(rs.getString("Name_person"));
            phones.setSurname(rs.getString("Surname"));
            phones.setMiddleName(rs.getString("MiddleName"));
            phones.setAddress(rs.getString("Address"));
            phones.setNumber(rs.getString("Number"));
            return phones;
        }
    }
}