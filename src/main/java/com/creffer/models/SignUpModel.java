package com.creffer.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "sign_up_page")
public class SignUpModel {
    @Column(name = "sign_up_page_id")
    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    private UUID pageId;

    public SignUpModel() {
    }

    public UUID getPageId() {
        return pageId;
    }

    public void setPageId(UUID pageId) {
        this.pageId = pageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpModel that = (SignUpModel) o;
        return Objects.equals(pageId, that.pageId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pageId);
    }
}
