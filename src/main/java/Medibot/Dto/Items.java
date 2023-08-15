package Medibot.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Items {

    private String id;

    @JsonProperty("entpName")
    private String entpName;

    @JsonProperty("itemName")
    private String itemName;

    @JsonProperty("itemSeq")
    private String itemSeq;

    @JsonProperty("efcyQesitm")
    private String efcyQesitm;

    @JsonProperty("useMethodQesitm")
    private String useMethodQesitm;

    @JsonProperty("atpnWarnQesitm")
    private String atpnWarnQesitm;

    @JsonProperty("atpnQesitm")
    private String atpnQesitm;

    @JsonProperty("intrcQesitm")
    private String intrcQesitm;

    @JsonProperty("seQesitm")
    private String seQesitm;

    @JsonProperty("depositMethodQesitm")
    private String depositMethodQesitm;

    @JsonProperty("openDe")
    private String openDe;

    @JsonProperty("updateDe")
    private String updateDe;

    @JsonProperty("itemImage")
    private String itemImage;

    @JsonProperty("bizrno")
    private String bizrno;

    public String getId() {
        return id;
    }

    public String getEntpName() {
        return entpName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemSeq() {
        return itemSeq;
    }

    public String getEfcyQesitm() {
        return efcyQesitm;
    }

    public String getUseMethodQesitm() {
        return useMethodQesitm;
    }

    public String getAtpnWarnQesitm() {
        return atpnWarnQesitm;
    }

    public String getAtpnQesitm() {
        return atpnQesitm;
    }

    public String getIntrcQesitm() {
        return intrcQesitm;
    }

    public String getSeQesitm() {
        return seQesitm;
    }

    public String getDepositMethodQesitm() {
        return depositMethodQesitm;
    }

    public String getOpenDe() {
        return openDe;
    }

    public String getUpdateDe() {
        return updateDe;
    }

    public String getItemImage() {
        return itemImage;
    }

    public String getBizrno() {
        return bizrno;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id='" + id + '\'' +
                ", entpName='" + entpName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemSeq='" + itemSeq + '\'' +
                ", efcyQesitm='" + efcyQesitm + '\'' +
                ", useMethodQesitm='" + useMethodQesitm + '\'' +
                ", atpnWarnQesitm='" + atpnWarnQesitm + '\'' +
                ", atpnQesitm='" + atpnQesitm + '\'' +
                ", intrcQesitm='" + intrcQesitm + '\'' +
                ", seQesitm='" + seQesitm + '\'' +
                ", depositMethodQesitm='" + depositMethodQesitm + '\'' +
                ", openDe='" + openDe + '\'' +
                ", updateDe='" + updateDe + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", bizrno='" + bizrno + '\'' +
                '}';
    }
}
