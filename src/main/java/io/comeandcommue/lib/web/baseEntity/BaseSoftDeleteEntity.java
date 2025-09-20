package io.comeandcommue.lib.web.baseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.Instant;

@Getter
@MappedSuperclass
public class BaseSoftDeleteEntity extends BaseEntity {
    @Column(name="deleted_at")
    private Instant deletedAt;
}
