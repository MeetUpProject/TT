package com.meetup.meetup.controller;

import com.meetup.meetup.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor // 생성자 주입으로 자동 주입 처리
public class PageController {

    private final PostService postService; // final로 선언된 필드, 생성자 주입됨

    @GetMapping
    public String redirectToList() {
        return "redirect:/post/list";
    }

    @GetMapping("/list")
    public String listPage() {
        return "post_list";
    }

    @GetMapping("/write")
    public String writePage() {
        return "post_write";
    }

    @GetMapping("/view/{id}")
    public String viewPage(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "post_view";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.read(id)); // postService 사용
        return "post_edit";
    }

    @GetMapping("/post/delete/{id}")
    public String deleteConfirm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.read(id)); // postService 사용
        return "post_delete";
    }

    @GetMapping("/{id}")
    public String detailPage(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.read(id)); // postService 사용
        return "post_detail";
    }
}

