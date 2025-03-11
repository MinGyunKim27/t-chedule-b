package com.example.tchedule.model;  // 🚨 패키지 경로 확인!

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "places")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private double latitude;
    private double longitude;

    @Column(unique = true)
    private String placeId;
}
