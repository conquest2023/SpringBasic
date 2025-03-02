package org.example.demo1.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.demo1.dto.PageRequestDTO;
import org.example.demo1.dto.TodoDTO;
import org.example.demo1.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private  final TodoService todoService;
    @RequestMapping("/list")
    public  void list(@Valid PageRequestDTO pageRequestDTO,BindingResult bindingResult, Model model){
        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()){
            pageRequestDTO=PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO",todoService.getList(pageRequestDTO));
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public  void register(){

        log.info("todo register...");

    }
    @PostMapping("/register")
    public  String registerPost(@Valid TodoDTO todoDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes
                                ){
        log.info("POST todo register...");
        if(bindingResult.hasErrors()){
            log.info("has Errors.........");
            redirectAttributes.addAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        log.info(todoDTO);

        todoService.register(todoDTO);
        return "redirect:/todo/list";


    }
    @GetMapping({"/read","/modify"})
    public  void  read(Long tno,PageRequestDTO pageRequestDTO,Model model){
        TodoDTO todoDTO=todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto",todoDTO);
    }

    @PostMapping("/remove")
    public  String  remove(Long tno,PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){

        log.info("-----------removoe---------");
        log.info("tno:"+tno);
        todoService.remove(tno);
        redirectAttributes.addAttribute("page",1);
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "redirect:/todo/list";
    }


    @PostMapping("/modify")
    public  String modify(@Valid TodoDTO todoDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                            PageRequestDTO pageRequestDTO){

        if(bindingResult.hasErrors()){

            log.info("has errors....");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            redirectAttributes.addAttribute("tno",todoDTO.getTno());

            return "redirect/todo/modify";

        }
        log.info(todoDTO);
        todoService.modify(todoDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getSize());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return  "redirect:/todo/read";
    }
}
