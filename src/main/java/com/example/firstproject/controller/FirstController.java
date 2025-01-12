package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "테스트");
        return "greetings"; //greetings.mustache 경로
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }

    @GetMapping("random-quote")
    public String randomQuote(Model model) {
        String[] quotes = {
                "행복은 습관이다 그것을 몸에 지녀라 - 허버드",
                "고개를 숙이지 마시오. 세상을 정면으로 보시오 - 헬렌",
                "당신이 할수 있다고 믿은 할수 없다고 믿는 믿는 데로 될것이다. - 헨리"
        };

        int randInt = (int)(Math.random() * quotes.length);
        model.addAttribute("randomQuote", quotes[randInt]);
        return "quote";
    }
}
