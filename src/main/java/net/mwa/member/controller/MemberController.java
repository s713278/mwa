package net.mwa.member.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.mwa.api.vo.MemberRegRequest;
import net.mwa.common.APICommonResponse;
import net.mwa.common.SearchMemberRequest;
import net.mwa.common.SearchMemberResponse;
import net.mwa.service.MemberService;
import net.mwa.vo.UserDetailsVO;

@Controller("api/v1/member")
@Api(value="MemberController",description="Operations pertaining to memeber services")
public class MemberController {

	private static Logger logger = Logger.getLogger(MemberController.class.getName());

	@Autowired
	private MemberService memberService;

	@PostMapping(value = "/sum")
	public @ResponseBody int getTotal(int a, int b) {

		return a + b;
	}

	@PostMapping("/add")
	public @ResponseBody APICommonResponse addMember(@Valid @RequestBody MemberRegRequest request ) {
		return memberService.save(request);
	}

	@PostMapping("/update")
	public @ResponseBody APICommonResponse updateMember(@RequestBody MemberRegRequest  request) {
		return memberService.save(request);
	}

	@GetMapping(value = "/listAll")
	public @ResponseBody Iterable<UserDetailsVO> listAllMembers() {
		return memberService.listAllMemebrs();
	}

	@GetMapping(value = "/details")
	public @ResponseBody APICommonResponse getMemberDetails(@RequestBody SearchMemberRequest request) {
		SearchMemberResponse response = (SearchMemberResponse) memberService.getMemberDetails(request);
		return response;
	}
	
	
}
