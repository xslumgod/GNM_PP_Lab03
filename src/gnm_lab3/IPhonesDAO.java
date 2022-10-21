package gnm_lab3;
/**
 *
 * @author dmitriy
 */

import javax.sql.DataSource;
import java.util.List;


/**
 * Интерфейс работы с таблицой Phones
 *
 */
public interface IPhonesDAO {
    void setDataSource(DataSource ds); // Установка связи с данныими
    void insert(PhoneBook phones); // Вставка новой записи
    void append(String Name, String Surname, String MiddleName, String Address, String Number); // Добавление новой записи
    void append(String Name, String Surname, String Address, String Number); // Добавление новой записи без отчества
    void deleteByName(String Name);
    void deleteByAddress(String Address);// Удаление записи по имении
    void delete(String Name, String Surname); // Удаление записи с указанным брендом и моделью
    void deleteAll(); // Удаление всех запией
    void update(String oldAddress, String newAddress); // Изменение записей в таблице
    List<PhoneBook> NameSelect (String scannedName1, String scannedName2);
    List<PhoneBook> NumberSelect (String scannedNumber, String scannedNumber2);
    PhoneBook findByAddress(String Address); // Получение записи с заданной ценой
    List<PhoneBook> findByName(String Name); // Получение записей с заданным брендом 
    List<PhoneBook> select(String Name, String Surname); // Получение записей с заданным брендом и моделью
    List<PhoneBook> selectAll(); // Получение всех записей
}