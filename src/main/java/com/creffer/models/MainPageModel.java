package com.creffer.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "main_page")
public class MainPageModel {

    @Column(name = "main_page_id")
    @Id
    @GenericGenerator(name = "uuid_gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid_gen")
    private UUID pageId;

    public MainPageModel() {
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
        MainPageModel model = (MainPageModel) o;
        return Objects.equals(pageId, model.pageId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pageId);
    }
}
