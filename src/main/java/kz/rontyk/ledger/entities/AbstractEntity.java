package kz.rontyk.ledger.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@MappedSuperclass
public abstract class AbstractEntity<T extends Serializable>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;


    @PrePersist
    protected void onCreate(){
        this.updatedAt = this.createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = Instant.now();
    }
}