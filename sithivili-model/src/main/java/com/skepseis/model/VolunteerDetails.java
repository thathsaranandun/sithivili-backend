package com.skepseis.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "volunteer_details")
@EntityListeners(AuditingEntityListener.class)
@Data
public class VolunteerDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @OneToOne
    private Volunteer volunteer;
    private String name;
    private String nic;
    private String dateOfBirth;
    private String mobile;
}
