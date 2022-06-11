package com.workshop.ETrade.Persistance.Stores;

import java.util.List;

public class MapDBobjDTO {
    public String key;
    public List<String> val;

    public MapDBobjDTO(String key, List<String> val) {
        this.key = key;
        this.val = val;
    }
}
