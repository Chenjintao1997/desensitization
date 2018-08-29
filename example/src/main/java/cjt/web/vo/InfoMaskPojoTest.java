package cjt.web.vo;

import java.io.Serializable;

public class InfoMaskPojoTest implements Serializable{
    private String userName;
    private String phone;
    private String idCard;
    private String sex;
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "InfoMaskPojoTest{" +
                "userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
