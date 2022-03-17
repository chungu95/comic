package com.comic.story_type.exception;

import com.comic.common.exception.ServiceErrorCode;
import com.comic.common.exception.ServiceException;

import javax.ws.rs.core.Response;
import java.util.HashMap;

public class StoryTypeException extends ServiceException {
    private static final String STORY_TYPE_NOT_FOUND = "001";

    private StoryTypeException(String ordinal) {
        super(new HashMap<>(), ServiceErrorCode.STORY_TYPE, ordinal, Response.Status.BAD_REQUEST);
    }

    public static StoryTypeException storyTypeNotFound() {
        return new StoryTypeException(STORY_TYPE_NOT_FOUND);
    }
}
