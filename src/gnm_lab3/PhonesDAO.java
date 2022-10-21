/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gnm_lab3;

/**
 *
 * @author dmitriy
 */
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import java.util.List;

/**
 * Реализация интерфейса работы с таблицей Person
 *
 */
 public class PhonesDAO implements IPhonesDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(PhoneBook phones) { // Реализация вставки новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO phonesbook (Name_person, Surname, MiddleName, Address, Number) VALUES(?,?,?,?,?)",
                new Object[]{phones.getName(), phones.getSurname(), phones.getMiddleName(), phones.getAddress(),phones.getNumber()});
    }

    @Override
    public void append(String Name, String Surname, String MiddleName, String Address, String Number) {  // Реализация добавления новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO phonesbook (Name_person, Surname, MiddleName, Address, Number) VALUES(?,?,?,?,?)", 
                new Object[]{Name, Surname, MiddleName, Address, Number});
    }

    @Override
    public void append(String Name, String Surname, String Address, String Number) {  // Реализация добавления новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO phonesbook (Name_person, Surname, Address, Number) VALUES(?,?,?,?)", 
                new Object[]{Name, Surname, Address,Number});
    }
    

    
    @Override
    public void deleteByName(String Name) {  // Реализация удаления записей по модели
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM phonesbook WHERE Name_person LIKE ?", new Object[]{'%' + Name + '%'});
    }
    
     @Override
    public void deleteByAddress(String Address) {  // Реализация удаления записей по модели
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM phonesbook WHERE Address LIKE ?", new Object[]{'%' + Address + '%'});
    }

    @Override
    public void delete(final String Name, final String Surname) {  // Реализация удаления записей с указанным брендом и моделью
        TransactionTemplate tt = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        tt.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate jt = new JdbcTemplate(dataSource);
                    jt.update("DELETE from phonesbook where Name_person = ? AND Surname = ?", new Object[]{Name, Surname});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE from phonesbook");
    }

    @Override
    public void update(String oldAddress, String newAddress) {  // Изменение записей в таблице
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE phonesbook SET Address = ? WHERE Address = ?", new Object[]{newAddress, oldAddress});
    }

    @Override
    public PhoneBook findByAddress(String address) { // Реализация поиска записи с заданной ценой
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<PhoneBook> phones = jt.query("SELECT * FROM phonesbook WHERE Address = ?",
                new Object[]{address}, new PhonesRowMapper());
        return phones.size() > 0 ? phones.get(0) : null;
    }
    
    @Override
    public List<PhoneBook> NameSelect(String scannedName1, String scannedName2) { // Реализация поиска записи с заданными ценами
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        String s = "["+scannedName1+"-"+scannedName2+"]";
        System.out.println(s);
        return jt.query("SELECT * FROM phonesbook WHERE Name_person REGEXP ?", 
                new Object[]{s}, new PhonesRowMapper());
    }   
    
    @Override
    public List<PhoneBook> NumberSelect(String scannedNumber, String scannedNumber2) { // Реализация поиска записи с заданными ценами
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("SELECT * FROM phonesbook WHERE Number >= ? AND Number < ?", 
                new Object[]{scannedNumber, scannedNumber2}, new PhonesRowMapper());
    }
    
    @Override
    public List<PhoneBook> findByName(String Name) {  // Реализация поиска записей по бренду
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM phonesbook WHERE Name_person LIKE ?";
        List<PhoneBook> phoneslist = jt.query(sql, new Object[]{'%' + Name + '%'}, new PhonesRowMapper());
        return phoneslist;
    }

    @Override
    public List<PhoneBook> select(String Name, String Surname) {  // Реализация получения записей с заданным брендом и моделью
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select  * from phonesbook where Name_person = ? AND Surname = ?",
                new Object[]{Name, Surname}, new PhonesRowMapper());
    }

    @Override
    public List<PhoneBook> selectAll() {  // Реализация получения всех записей
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select * from phonesbook", new PhonesRowMapper());
    }

}
