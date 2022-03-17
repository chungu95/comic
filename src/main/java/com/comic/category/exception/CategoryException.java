package com.comic.category.exception;

import com.comic.common.exception.ServiceErrorCode;
import com.comic.common.exception.ServiceException;

import javax.ws.rs.core.Response;
import java.util.HashMap;

public class CategoryException extends ServiceException {
    private static final String CATEGORY_NOT_FOUND = "001";

    private CategoryException(String ordinal) {
        super(new HashMap<>(), ServiceErrorCode.CATEGORY, ordinal, Response.Status.BAD_REQUEST);
    }

    public static CategoryException categoryNotFound() {
        return new CategoryException(CATEGORY_NOT_FOUND);
    }
}
