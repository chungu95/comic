package com.comic.story_type.domain;

import javax.validation.constraints.NotBlank;

public class StoryTypeUpdateRQ {
    @NotBlank
    private String name;
    private String description;
}
