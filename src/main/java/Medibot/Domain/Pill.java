package Medibot.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "pill")
@Getter
@NoArgsConstructor
public class Pill {

    @Id
    private int serialNumber;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "businessName")
    private String businessName;

    @Column(name = "frontSign")
    private String frontSign;

    @Column(name = "backSign")
    private String backSign;

    @Column(name = "shape")
    private String shape;

    @Column(name = "frontColor")
    private String frontColor;

    @Column(name = "backColor")
    private String backColor;

    @Column(name = "frontLine")
    private String frontLine;

    @Column(name = "backLine")
    private String backLine;

    @Column(name = "majorAxis")
    private Double majorAxis;

    @Column(name = "minorAxis")
    private Double minorAxis;

    @Column(name = "thickness")
    private String thickness;

    @Column(name = "classification")
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
