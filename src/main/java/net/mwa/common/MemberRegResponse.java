package net.mwa.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import net.mwa.vo.MemberReg;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberRegResponse extends APICommonResponse {

	private MemberReg memberReg;
}
