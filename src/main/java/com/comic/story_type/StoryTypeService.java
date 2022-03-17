package com.comic.story_type;

import com.comic.core.query.Pageable;
import com.comic.core.query.PagingQuery;
import com.comic.story_type.domain.StoryTypeCreateRQ;
import com.comic.story_type.domain.StoryTypeRS;
import com.comic.story_type.domain.StoryTypeUpdateRQ;
import com.comic.story_type.entity.StoryType;
import com.comic.story_type.exception.StoryTypeException;
import com.comic.story_type.mapper.StoryTypeMapper;
import com.comic.story_type.repository.StoryTypeRepository;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@RequiredArgsConstructor
public class StoryTypeService {
    private final StoryTypeRepository storyTypeRepository;

    @Transactional
    public StoryTypeRS create(StoryTypeCreateRQ storyTypeCreateRQ) {
        StoryType storyType = StoryTypeMapper.INSTANCE.map(storyTypeCreateRQ);
        return StoryTypeMapper.INSTANCE.map(storyTypeRepository.save(storyType));
    }

    @Transactional
    public StoryTypeRS update(Long id, StoryTypeUpdateRQ storyTypeUpdateRQ) {
        StoryType storyType = getById(id);
        StoryTypeMapper.INSTANCE.merge(storyType, storyTypeUpdateRQ);
        return StoryTypeMapper.INSTANCE.map(storyTypeRepository.save(storyType));
    }

    @Transactional
    public void delete(Long id) {
        StoryType storyType = getById(id);
        storyType.setIsDeleted(true);
        storyTypeRepository.save(storyType);
    }

    public Pageable<StoryTypeRS> search(PagingQuery pagingQuery) {
        return storyTypeRepository.search(pagingQuery, StoryTypeMapper.INSTANCE::map);
    }

    private StoryType getById(Long id) {
        return storyTypeRepository.findByIdOptional(id).orElseThrow(StoryTypeException::storyTypeNotFound);
    }
}
