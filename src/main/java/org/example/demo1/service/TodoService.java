package org.example.demo1.service;

import org.example.demo1.domain.TodoVO;
import org.example.demo1.dto.PageRequestDTO;
import org.example.demo1.dto.PageResponseDTO;
import org.example.demo1.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    void  register(TodoDTO todoDTO);

    List<TodoDTO> getAll();

    TodoDTO getOne(Long tno);

    void  remove(long tno);

    void  modify(TodoDTO todoDTO);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);
}
