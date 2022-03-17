package com.comic.story_type.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StoryTypeCreateRQ {
    @NotBlank
    private String name;

    private String description;
}
