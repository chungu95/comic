package com.comic.story_type.mapper;

import com.comic.story_type.domain.StoryTypeCreateRQ;
import com.comic.story_type.domain.StoryTypeRS;
import com.comic.story_type.domain.StoryTypeUpdateRQ;
import com.comic.story_type.entity.StoryType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public interface StoryTypeMapper {
    StoryTypeMapper INSTANCE = Mappers.getMapper(StoryTypeMapper.class);

    StoryTypeRS map(StoryType storyType);

    StoryType map(StoryTypeCreateRQ storyTypeCreateRQ);

    @Mapping(target = "id", ignore = true)
    void merge(@MappingTarget StoryType storyType, StoryTypeUpdateRQ storyTypeUpdateRQ);
}
