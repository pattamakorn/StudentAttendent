package com.example.studentattendent;

public class stimetable {
    String idsubject,namesubject,subjectteacher,classroom,teachtime;

    public stimetable() {
    }

    public stimetable(String idsubject, String namesubject, String subjectteacher, String classroom, String teachtime) {
        this.idsubject = idsubject;
        this.namesubject = namesubject;
        this.subjectteacher = subjectteacher;
        this.classroom = classroom;
        this.teachtime = teachtime;
    }

    public String getIdsubject() {
        return idsubject;
    }

    public void setIdsubject(String idsubject) {
        this.idsubject = idsubject;
    }

    public String getNamesubject() {
        return namesubject;
    }

    public void setNamesubject(String namesubject) {
        this.namesubject = namesubject;
    }

    public String getSubjectteacher() {
        return subjectteacher;
    }

    public void setSubjectteacher(String subjectteacher) {
        this.subjectteacher = subjectteacher;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeachtime() {
        return teachtime;
    }

    public void setTeachtime(String teachtime) {
        this.teachtime = teachtime;
    }
}
