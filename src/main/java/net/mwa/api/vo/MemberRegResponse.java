package net.mwa.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.Api;
import lombok.Data;
import net.mwa.common.APICommonResponse;

@Api("response")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberRegResponse extends APICommonResponse {

	private Long memberId;
}
