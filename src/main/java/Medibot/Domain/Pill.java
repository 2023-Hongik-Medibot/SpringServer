package Medibot.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Pill {

    @Id
    private int serialNumber;

    private String itemName;

    private String businessName;

    private String frontSign;

    private String backSign;

    private String shape;

    private String frontColor;

    private String backColor;

    private String frontLine;

    private String backLine;

    private Double majorAxis;

    private Double minorAxis;

    private String thickness;

    private String classification;

    @Builder
    public Pill(int serialNumber, String itemName, String businessName, String frontSign, String backSign, String shape, String frontColor, String backColor, String frontLine, String backLine, Double majorAxis, Double minorAxis, String thickness, String classification) {
        this.serialNumber = serialNumber;
        this.itemName = itemName;
        this.businessName = businessName;
        this.frontSign = frontSign;
        this.backSign = backSign;
        this.shape = shape;
        this.frontColor = frontColor;
        this.backColor = backColor;
        this.frontLine = frontLine;
        this.backLine = backLine;
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
        this.thickness = thickness;
        this.classification = classification;
    }
}
