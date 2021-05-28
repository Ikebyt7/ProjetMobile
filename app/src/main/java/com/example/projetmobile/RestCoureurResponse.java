package com.example.projetmobile;

import java.util.List;

abstract public class RestCoureurResponse {
    private Integer count;
    private String next;
    private List<Coureur> results;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public List<Coureur> getResults() {
        return results;
    }
}
