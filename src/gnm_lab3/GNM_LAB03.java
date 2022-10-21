package gnm_lab3;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.List;
import java.util.Scanner;

class GNM_LAB03 {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с бинами
//
            PhonesDAO phonesDAO = (PhonesDAO) context.getBean("phonesDAO"); // Загрузка бина доступа к таблице клиентов 
//
            phonesDAO.deleteAll(); // Удаление всех записей
            
            PhoneBook phones = new PhoneBook("Nikita", "Gorbachev", "Mihaylovich", "Panfilova","+7774589765"); // Создание нового объекта таблицы телефонов 
            phonesDAO.insert(phones); // Вставить новый объект (запись) в таблицу телефонов

            phonesDAO.insert(new PhoneBook("Zhas", "Ibraev", "Tulegenovich", "Antonova","+7774589587")); // Вставить новый объект (запись) в таблицу телефонов
            phonesDAO.insert(new PhoneBook("Dmitriy", "Los", "Vladimirovich", "Mira","+7774589865")); // Вставить новый объект (запись) в таблицу телефонов

            List<PhoneBook> phones1 = phonesDAO.findByName("Zhas"); // Поиск записи по цене телефона
            System.out.println(phones1 != null ? phones1 : "Нет данных"); // Вывод на экран найденной записи


            List<PhoneBook> phoneslist = (List<PhoneBook>) phonesDAO.findByAddress("Panf"); // Поиск записей по фрагменту модели
            System.out.println(phoneslist != null ? phoneslist : "Нет данных");

            phonesDAO.append("Ivan", "Ivanov", "Andreevich", "Istaya", "+77083596870"); // Добавлние записей
            phonesDAO.append("Petr", "Pavlov", "Sergeevich", "Kamzina", "+77083598670");
            Scanner sc = new Scanner(System.in);
            System.out.print("Name - ");
            String scannedName = sc.nextLine();
            System.out.print("Surname - ");
            String scannedSurname = sc.nextLine();
            System.out.print("MiddleName - ");
            String scannedMiddleName = sc.nextLine();
            System.out.print("Address - ");
            String scannedAddress = sc.nextLine();
            System.out.print("Number - ");
            String scannedNumber = sc.nextLine();
            phonesDAO.append(scannedName, scannedSurname, scannedMiddleName, scannedAddress, scannedNumber);
            
            phonesDAO.deleteByName("Dmitr"); // Удаление записей по фрагменту модели
            phonesDAO.deleteByAddress("Kamz");
            phonesDAO.delete("Dmitriy", "Los"); // Удаление записи по бренду и модели

            phonesDAO.update("Panfilova", "Sadovaya"); // Изменение записей в таблице

            System.out.println("Данные в таблице БД:");

            List<PhoneBook> list = phonesDAO.selectAll();
            for (PhoneBook myPhonesBook : list) {
                System.out.println(myPhonesBook.getName() + " " + myPhonesBook.getSurname() + " " + myPhonesBook.getMiddleName()+ " " + myPhonesBook.getAddress()+ " " + myPhonesBook.getNumber());
            }


            System.out.println("Вывод записей с именем Никита и фамилией Горбачёв:");

            list = phonesDAO.select("Nikita", "Gorbachev");
            for (PhoneBook myPhonesBook : list) {
                System.out.println(myPhonesBook.getName() + " " + myPhonesBook.getSurname() + " " + myPhonesBook.getMiddleName()+ " " + myPhonesBook.getAddress()+ " " + myPhonesBook.getNumber());
            }
            
            System.out.println("Вывод записей с именем Жас и фамилией Ибраев: (без отчества)");
            
            list = phonesDAO.select("Zhas", "Ibraev");
            for (PhoneBook myPhonesBook : list) {
                System.out.println(myPhonesBook.getName() + " " + myPhonesBook.getSurname() + " " + myPhonesBook.getAddress()+ " " + myPhonesBook.getNumber());
            }
            
            System.out.println("Введите диапазон букв алфавита");
            System.out.println("Буква 1 - ");
            String scannedName1 = sc.nextLine();
            System.out.println("Буква 2 - ");
            String scannedName2 = sc.nextLine();
            list = phonesDAO.NameSelect(scannedName1, scannedName2);
                        for (PhoneBook myPhonesBook : list) {
                System.out.println(myPhonesBook.getName() + " " + myPhonesBook.getSurname() + " " + myPhonesBook.getAddress()+ " " + myPhonesBook.getNumber());
            }

                        
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
}