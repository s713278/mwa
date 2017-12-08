package net.mwa.member.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mwa.common.APICommonResponse;
import net.mwa.common.SearchMemberRequest;
import net.mwa.common.SearchMemberResponse;
import net.mwa.service.MemberService;
import net.mwa.vo.MemberDetailsVO;

@Controller("api/v1/member")
public class MemberController {

	private static Logger logger = Logger.getLogger(MemberController.class.getName());

	@Autowired
	private MemberService memberService;

	@PostMapping(value = "/sum")
	public @ResponseBody int getTotal(int a, int b) {

		return a + b;
	}

	@PostMapping("/add")
	public @ResponseBody APICommonResponse addMember(@Valid @RequestBody MemberDetailsVO memberReg) {
		return memberService.save(memberReg);
	}

	@PostMapping("/update")
	public @ResponseBody APICommonResponse updateMember(@RequestBody MemberDetailsVO memberReg) {
		return memberService.save(memberReg);
	}

	@GetMapping(value = "/listAll")
	public @ResponseBody Iterable<MemberDetailsVO> listAllMembers() {
		return memberService.listAllMemebrs();
	}

	@GetMapping(value = "/details")
	public @ResponseBody APICommonResponse getMemberDetails(@RequestBody SearchMemberRequest request) {
		SearchMemberResponse response = (SearchMemberResponse) memberService.getMemberDetails(request);
		return response;
	}
	
	
}
