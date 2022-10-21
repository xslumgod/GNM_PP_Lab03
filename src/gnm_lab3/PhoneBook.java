/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gnm_lab3;

/**
 *
 * @author dmitriy
 */
/**
 * Класс, соответсвующий записи в таблице Phones
 *
 */
public class PhoneBook {

    int id;            // Код записи
    String Name_person;  // Бренд
    String Surname;
    String MiddleName;
    String Address;
    String Number;// Модель
    
    
    public PhoneBook() {
        this.id = 0;
        this.Name_person = "";
        this.Surname = "";
        this.MiddleName = "";
        this.Address = "";
        this.Number = "";
    }
    
    public PhoneBook(String Name, String Surname, String MiddleName, String Address, String Number) {
        this.id = 0;
        this.Name_person= Name;
        this.Surname = Surname;
        this.MiddleName = MiddleName;
        this.Address = Address;
        this.Number = Number;
    }

    public String getName() {
        return Name_person;
    }


    public void setName(String Name) {
        this.Name_person = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PhoneBook{" + "id=" + id + ", Name=" + Name_person + ", Surname=" + Surname + ", MiddleName=" + MiddleName + ", Address=" + Address + ", Number=" + Number + '}';
    }

    
}



