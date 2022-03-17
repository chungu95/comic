package com.comic.category.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryCreateRQ {
    @NotBlank
    private String name;

    private String description;
}
