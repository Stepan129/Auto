package Auto.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class AutoPatch {

    private String type;
    private String brand;
    private String model;
    private Integer year;
    private String country;
    private String condition;
    private Double mileage;
    private Double price;
    private String info;


    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean empty = true;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isTypeUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isBrandUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isModelUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isYearUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isCountryUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isConditionUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isMileageUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isPriceUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isInfoUpdated;

    public void setType(String type) {
        empty = false;
        isTypeUpdated = true;

        this.type = type;
    }

    public void setBrand(String brand) {
        empty = false;
        isBrandUpdated = true;

        this.brand = brand;
    }

    public void setModel(String model) {
        empty = false;
        isModelUpdated = true;

        this.model = model;
    }

    public void setYear(Integer year) {
        empty = false;
        isYearUpdated = true;

        this.year = year;
    }

    public void setCountry(String country) {
        empty = false;
        isCountryUpdated = true;

        this.country = country;
    }

    public void setCondition(String condition) {
        empty = false;
        isConditionUpdated = true;

        this.condition = condition;
    }
    public void setCondition(Double mileage) {
        empty = false;
        isMileageUpdated = true;

        this.mileage = mileage;
    }
    public void setPrice(Double price) {
        empty = false;
        isPriceUpdated = true;

        this.price = price;
    }
    public void setInfo(String info) {
        empty = false;
        isConditionUpdated = true;

        this.info = info;
    }
}
