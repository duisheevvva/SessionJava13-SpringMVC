package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.HouseType;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "house_gen")
    @SequenceGenerator(name = "house_gen", sequenceName = "house_seq",allocationSize = 1)
    private Long id;
    @NotNull
    private String address;
    @NotNull
    private int price;
    @NotNull
    private int room;
    @NotNull
    private String country;
    @NotNull
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    private Agency agency ;

    public House(String address, int price, int room, String country, String description, HouseType houseType) {
        this.address = address;
        this.price = price;
        this.room = room;
        this.country = country;
        this.description = description;
        this.houseType = houseType;
    }
}
