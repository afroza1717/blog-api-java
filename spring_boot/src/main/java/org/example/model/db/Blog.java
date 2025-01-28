package org.example.model.db;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;


@Entity
@Table(name = "DB_Blog")
@Data
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLOG_SEQ")
    @SequenceGenerator(name = "BLOG_SEQ", sequenceName = "BLOG_SEQ", allocationSize = 1)
    private long id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String is_publish;
    @NotNull
    private String paragraph;

    @Lob
    private byte[] image;

}