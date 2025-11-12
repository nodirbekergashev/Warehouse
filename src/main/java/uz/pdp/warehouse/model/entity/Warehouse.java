package uz.pdp.warehouse.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.warehouse.model.entity.base.BaseEntity;


@Entity
@Table(name = "warehouses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private String location;
    private String description;

    @Column(nullable = false)
    private Integer capacity;

    private Integer currentStock = 0;
}
