package org.company.app.data.entity;

import java.util.Date;

public class ClientEntity {
    private int ID;
    private String FirstName;
    private String LastName;
    private String Patronymic;
    private Date Birthday;
    private Date RegistrationDate;
    private String Email;
    private String Phone;
    private char GenderCode;
    private String PhotoPath;

    public ClientEntity(int ID, String firstName, String lastName, String patronymic, Date birthday, Date registrationDate, String email, String phone, char genderCode, String photoPath) {
        this.ID = ID;
        FirstName = firstName;
        LastName = lastName;
        Patronymic = patronymic;
        Birthday = birthday;
        RegistrationDate = registrationDate;
        Email = email;
        Phone = phone;
        GenderCode = genderCode;
        PhotoPath = photoPath;
    }

    public ClientEntity(String firstName, String lastName, String patronymic, Date birthday, Date registrationDate, String email, String phone, char genderCode, String photoPath) {
        this(-1, firstName, lastName, patronymic, birthday, registrationDate, email, phone, genderCode, photoPath);
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "ID=" + ID +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Patronymic='" + Patronymic + '\'' +
                ", Birthday=" + Birthday +
                ", RegistrationDate=" + RegistrationDate +
                ", Email='" + Email + '\'' +
                ", Phone='" + Phone + '\'' +
                ", GenderCode=" + GenderCode +
                ", PhotoPath='" + PhotoPath + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public void setPatronymic(String patronymic) {
        Patronymic = patronymic;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        RegistrationDate = registrationDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public char getGenderCode() {
        return GenderCode;
    }

    public void setGenderCode(char genderCode) {
        GenderCode = genderCode;
    }

    public String getPhotoPath() {
        return PhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        PhotoPath = photoPath;
    }
}
