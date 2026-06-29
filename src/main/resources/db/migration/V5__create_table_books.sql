CREATE TABLE tb_books (

    id UUID PRIMARY KEY,
    code VARCHAR(13) UNIQUE,
    title VARCHAR(255) NOT NULL,
    format VARCHAR(20) NOT NULL,
    page_count INTEGER,
    edition INTEGER,
    summary TEXT,
    language VARCHAR(5),
    publication_year INTEGER NOT NULL ,
    publisher_id UUID NOT NULL,

    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_publisher FOREIGN KEY (publisher_id) REFERENCES tb_publishers(id) ON DELETE CASCADE
);