package Medibot.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Body {
    @JsonProperty("pageNo")
    private int pageNo;

    @JsonProperty("totalCount")
    private int totalCount;

    @JsonProperty("numOfRows")
    private int numOfRows;

    @JsonProperty("items")
    private List<Items> items;

    public int getPageNo() {
        return pageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public List<Items> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Body{" +
                "pageNo=" + pageNo +
                ", totalCount=" + totalCount +
                ", numOfRows=" + numOfRows +
                ", items=" + items +
                '}';
    }
}
