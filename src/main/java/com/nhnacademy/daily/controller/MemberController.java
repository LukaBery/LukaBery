package com.nhnacademy.daily.controller;

import com.nhnacademy.daily.model.ClassType;
import com.nhnacademy.daily.model.Locale;
import com.nhnacademy.daily.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class MemberController {

    @PostMapping(value = "/member", consumes = "application/json" , produces = "text/csv")
    public ResponseEntity<Member> showMember(@RequestBody Member member) {
        return ResponseEntity.ok().body(member);
    }

    @GetMapping(value = "/members", consumes = "application/json" , produces = { "application/json", "text/csv" })
    public ResponseEntity<Page<Member>> getMember(Pageable pageable){

        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        System.out.println(page + " " + size);
        Member member = new Member("1", "1", ClassType.A, Locale.KO);
        Member member1 = new Member("2", "2", ClassType.A, Locale.KO);
        List<Member> memberList = new ArrayList<>();
        memberList.add(member);
        memberList.add(member1);
        Page<Member> memberPage = new PageImpl<>(memberList, PageRequest.of(page, size), memberList.size());
        return ResponseEntity.ok(memberPage);
    }
}
