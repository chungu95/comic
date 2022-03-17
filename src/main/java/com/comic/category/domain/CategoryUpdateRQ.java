package com.comic.category.domain;

import javax.validation.constraints.NotBlank;

public class CategoryUpdateRQ {
    @NotBlank
    private String name;
    private String description;
}
