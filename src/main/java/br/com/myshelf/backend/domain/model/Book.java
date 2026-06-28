package br.com.myshelf.backend.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_books")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String isbn;
    private String title;
    private String format;

    @Column(name = "page_count")
    private int pages;

    private int edition;
    private String summary;
    private String language;
    private int publicationYear;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_books_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public static Book createBook(String isbn,
                                  String title,
                                  String format,
                                  int pages,
                                  int edition,
                                  String summary,
                                  String language,
                                  int publicationYear,
                                  Publisher publisher,
                                  Set<Author> authors,
                                  Set<Genre> genres) {
        return Book.builder()
                .isbn(isbn)
                .title(title)
                .format(format)
                .pages(pages)
                .edition(edition)
                .summary(summary)
                .language(language)
                .publicationYear(publicationYear)
                .publisher(publisher)
                .authors(authors)
                .genres(genres)
                .build();
    }

}
