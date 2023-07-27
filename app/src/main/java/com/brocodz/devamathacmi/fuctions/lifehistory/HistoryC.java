package com.brocodz.devamathacmi.fuctions.lifehistory;

public class HistoryC {
    String title;
    String story;
    String intros;

    public HistoryC() {
    }

    public HistoryC(String title, String story, String intros) {
        this.title = title;
        this.story = story;
        this.intros = intros;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getIntros() {
        return intros;
    }

    public void setIntros(String intros) {
        this.intros = intros;
    }
}
