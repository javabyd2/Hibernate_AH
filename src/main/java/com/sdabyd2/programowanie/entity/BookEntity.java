package com.sdabyd2.programowanie.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity  //adnotacja dla hibernate'a
@Table(name = "books")  //ustawia nazwę tabeli
@Setter
@Getter
public class BookEntity { //na koniec należy zmapowac klasę na tabelę w XML I DODAC PROPERTIS W XML ABY STWORZYC TABELE

    @Column(nullable = false, length = 128) //ustawia nazwe kolumny i jej własciowości
    private String title;
    @Column(length = 128, nullable = false)
    private String author;
    private Date published;
    @Column(unique = true, nullable = false)
    private String ISBN;
    @Column(length = 50)
    private String category;
    private Integer pageCount;
    @Column(length = 128)
    private String publisher;
    @Column(precision = 2)
    private BigDecimal price;
    private Integer onStock;
    @Id //to oznaczenie klucza głównego tabeli
    @GeneratedValue(strategy = GenerationType.IDENTITY) //ustawienie autoinkrementacji
    private int id;


}
