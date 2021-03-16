import java.util.ArrayList;
import java.util.Objects;

public class Information {
    private String name;
    private String middleName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String sex;
    // arraylist de perechi <limba straina, nivel de competenta>
    private ArrayList<Pair<String, String>> knownLanguages;

    public Information(String name, String middleName, String email, String phoneNumber, String dateOfBirth, String sex,
                       ArrayList<Pair<String, String>> knownLanguages) {
        this.name = name;
        this.middleName = middleName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.knownLanguages = knownLanguages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Information that = (Information) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(knownLanguages, that.knownLanguages);
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public ArrayList<Pair<String, String>> getKnownLanguages() {
        return knownLanguages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setKnownLanguages(ArrayList<Pair<String, String>> knownLanguages) {
        this.knownLanguages = knownLanguages;
    }
}
