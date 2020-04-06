package com.skepseis.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "quote")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Quote {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String quote;

    private String author;

}
