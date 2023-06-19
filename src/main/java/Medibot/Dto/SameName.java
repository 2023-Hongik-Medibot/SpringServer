package Medibot.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SameName {
    private List<String> region;
    private String keyword;

    @JsonProperty("selected_region")
    private String selectedRegion;

    public List<String> getRegion() {
        return region;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getSelectedRegion() {
        return selectedRegion;
    }

    @Override
    public String toString() {
        return "SameName{" +
                "region=" + region +
                ", keyword='" + keyword + '\'' +
                ", selectedRegion='" + selectedRegion + '\'' +
                '}';
    }
}
