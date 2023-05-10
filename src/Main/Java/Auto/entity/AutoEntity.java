package Auto.entity;

import lombok.Data;

@Data
public class AutoEntity {
    private Long id;
    private String type;
    private String brand;
    private String model;
    private Integer year;
    private String country;
    private String condition;
    private Double mileage;
    private Double price;
    private String info;
}
