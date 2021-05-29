package com.example.projetmobile;

import java.util.List;

public class RestCoureurResponse {
    private Integer count;
    private List<Coureur> results;

    public Integer getCount() {
        return count;
    }

    public List<Coureur> getResults() {
        return results;
    }
}
