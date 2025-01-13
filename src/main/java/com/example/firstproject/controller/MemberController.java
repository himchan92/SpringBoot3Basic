package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signup() {
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm form) {
        Member member = form.toEntity();
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        //DB에서 id해당 데이터 조회
        Member member = memberRepository.findById(id).orElse(null);
        
        //모델에 담아 뷰에 전달
        model.addAttribute("member", member);

        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        //전체 데이터 가져오기
        Iterable<Member> memberList = memberRepository.findAll();

        //모델에 담기
        model.addAttribute("memberList", memberList);

        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", member);

        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form) {
        log.info(form.toString());
        //DTO -> Entity 변경
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());

        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);

        if(target != null) {
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/" + memberEntity.getId();
    }
}
