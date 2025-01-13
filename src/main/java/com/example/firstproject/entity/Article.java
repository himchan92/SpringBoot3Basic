package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db id auto increment
    private Long id;

    @Column
    private String title;

    @Column
    private String content;
}
