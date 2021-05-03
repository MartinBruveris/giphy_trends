package com.data.retrieve.dataretrieve_service;

import java.util.ArrayList;
import java.util.List;

public class GiphyDataWrapper {
    private List<GiphyDTO> data;

    public GiphyDataWrapper(){
        data = new ArrayList<>();
    }

    public void setData(List<GiphyDTO> data) {
        this.data = data;
    }

    public List<GiphyDTO> getData(){
        return this.data;
    }
}
