package Medibot.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {

    private String id;

    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("category_group_code")
    private String categoryGroupCode;

    @JsonProperty("category_group_name")
    private String categoryGroupName;
    private String phone;

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("road_address_name")
    private String roadAddressName;
    private String x;
    private String y;
    @JsonProperty("place_url")
    private String placeUrl;
    private String distance;

    @JsonProperty("redirect_url")
    @JsonIgnoreProperties(ignoreUnknown=true)
    private String redirectUrl;

    public String getId() {
        return id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryGroupCode() {
        return categoryGroupCode;
    }

    public String getCategoryGroupName() {
        return categoryGroupName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddressName() {
        return addressName;
    }

    public String getRoadAddressName() {
        return roadAddressName;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getPlaceUrl() {
        return placeUrl;
    }

    public String getDistance() {
        return distance;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", placeName='" + placeName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryGroupCode='" + categoryGroupCode + '\'' +
                ", categoryGroupName='" + categoryGroupName + '\'' +
                ", phone='" + phone + '\'' +
                ", addressName='" + addressName + '\'' +
                ", roadAddressName='" + roadAddressName + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", placeUrl='" + placeUrl + '\'' +
                ", distance='" + distance + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                '}';
    }
}
