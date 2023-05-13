package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Weather {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column (name = "air_temperature")
    private int airtemperature;

    @Column (name = "water_temperature")
    private int watertemperature;
}
