package com.hiberus.uster.interfaces.manager.facade.internal.assembler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAssembler<E, DTO> {

    public abstract E fromDto(DTO dto);

    public abstract DTO toDto(final E entity);

    public List<DTO> toDtoList(List<E> entities) {
        final List<DTO> dtoList = new ArrayList<>(entities.size());
        for (E e : entities) {
            dtoList.add(toDto(e));
        }
        return dtoList;
    }

    public Page<DTO> toDtoPage(Page<E> page) {
        final Pageable pageable = page.getPageable();
        final Page<DTO> dtoPage = new PageImpl<>(
                toDtoList(page.getContent()),
                pageable,
                page.getTotalElements()
        );
        return dtoPage;
    }
}
