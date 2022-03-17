package com.comic.story_type.repository;

import com.comic.core.repository.BaseRepository;
import com.comic.story_type.entity.StoryType;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StoryTypeRepository extends BaseRepository<StoryType, Long> {
}
