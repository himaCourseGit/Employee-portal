package com.acintyo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRequestDto {
	
	@NotBlank(message = "{admin.userName.notBlank}")
    private String userName;
	
	@NotBlank(message = "{admin.userName.password}")
	private String password;

}
