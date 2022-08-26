package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;


    //생성자 주입 -> 주요 사용을 권장
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

    //필드 주입 -> 뭔가를 수정하기가 어렵기 때문에 잘 사용하지 않음
    //    @Autowired private  MemberService memberService;

    //Setter 주입 (Art+INS) -> 중간에 변경을 잘못했을 경우에 문제가 생길 수가 있음
//    private  MemberService memberService;
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//      }
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();//Art+Enter
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
