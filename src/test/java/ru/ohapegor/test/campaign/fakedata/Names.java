package ru.ohapegor.test.campaign.fakedata;

public class Names {

    private String firstName;

    private String lastName;

    private String midName;

    private int sex;

    public Names(String firstName, String lastName, String midName, int sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.midName = midName;
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Names{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", midName='" + midName + '\'' +
                ", sex=" + sex +
                '}';
    }
}
