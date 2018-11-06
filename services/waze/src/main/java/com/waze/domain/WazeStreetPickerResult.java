package com.waze.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class WazeStreetPickerResult {

    private List<String> addressList;

    public WazeStreetPickerResult() {}

    public WazeStreetPickerResult(List<String> addressList) {
        this.addressList = addressList;
    }

    @JsonProperty
    public List<String> getAddressList() {
        return addressList;
    }
}
