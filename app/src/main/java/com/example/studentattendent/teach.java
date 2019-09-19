package com.example.studentattendent;

public class teach {

    String idsub,namesub,classt,classR,TimeT;

    public teach() {
    }

    public teach(String idsub, String namesub, String classt, String classR, String timeT) {
        this.idsub = idsub;
        this.namesub = namesub;
        this.classt = classt;
        this.classR = classR;
        TimeT = timeT;
    }

    public String getIdsub() {
        return idsub;
    }

    public void setIdsub(String idsub) {
        this.idsub = idsub;
    }

    public String getNamesub() {
        return namesub;
    }

    public void setNamesub(String namesub) {
        this.namesub = namesub;
    }

    public String getClasst() {
        return classt;
    }

    public void setClasst(String classt) {
        this.classt = classt;
    }

    public String getClassR() {
        return classR;
    }

    public void setClassR(String classR) {
        this.classR = classR;
    }

    public String getTimeT() {
        return TimeT;
    }

    public void setTimeT(String timeT) {
        TimeT = timeT;
    }
}
