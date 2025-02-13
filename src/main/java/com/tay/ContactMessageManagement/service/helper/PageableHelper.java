package com.tay.ContactMessageManagement.service.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageableHelper {

    /**
     *
     * @param page Index number of page
     * @param size How many items should be fetched per page
     * @param type  Type of direction (ASC or DESC)
     * @param prop Which property will be used for sorting.
     * @return Pageable object for DB fetch.
     */
    public Pageable createPageable(int page, int size, String type, String prop){
        return PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(type), prop));
    }
}
