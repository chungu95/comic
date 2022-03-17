package com.comic.story_type.entity;

import com.comic.core.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "story_types")
public class StoryType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "story_types_seq")
    @SequenceGenerator(name = "story_types_seq", allocationSize = 10)
    private Long id;
    private String name;
    private String description;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
