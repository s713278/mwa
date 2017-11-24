package net.mwa.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import net.mwa.vo.MemberDetailsVO;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchMemberResponse extends APICommonResponse {

	private MemberDetailsVO memberDetailsVO;
}
