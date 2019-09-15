package com.example.studentattendent;

public class news {
    private String teachpost,topic,massagepost,datepost,ima;

    public news() {
    }

    public news(String teachpost,String topic, String massagepost, String datepost, String ima) {
        this.teachpost = teachpost;
        this.topic = topic;
        this.massagepost = massagepost;
        this.datepost = datepost;
        this.ima = ima;
    }

    public String getTeachpost() {
        return teachpost;
    }

    public void setTeachpost(String teachpost) {
        this.teachpost = teachpost;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMassagepost() {
        return massagepost;
    }

    public void setMassagepost(String massagepost) {
        this.massagepost = massagepost;
    }

    public String getDatepost() {
        return datepost;
    }

    public void setDatepost(String datepost) {
        this.datepost = datepost;
    }

    public String getIma() {
        return ima;
    }

    public void setIma(String ima) {
        this.ima = ima;
    }
}
