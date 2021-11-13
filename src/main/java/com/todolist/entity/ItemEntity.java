package com.todolist.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

import static com.todolist.util.AppConstants.DATE_FORMAT;

@Table(name = "Items")
@Entity
@Getter
@Setter
@ToString
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tittle")
    private String Tittle;

    @Column(name = "latitude")
    private Double Latitude;

    @Column(name = "longitude")
    private Double Longitude;

    @Column(name = "urlImage")
    private String UrlImage;

    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private Date date;

    @Column(name = "status")
    private Boolean Status = false;
}
