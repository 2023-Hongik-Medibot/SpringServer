package Medibot.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("pageable_count")
    private int pageableCount;

    @JsonProperty("is_end")
    private boolean isEnd;

    @JsonProperty("same_name")
    private SameName sameName;

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageableCount() {
        return pageableCount;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public SameName getSameName() {
        return sameName;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "totalCount=" + totalCount +
                ", pageableCount=" + pageableCount +
                ", isEnd=" + isEnd +
                ", sameName=" + sameName +
                '}';
    }
}
