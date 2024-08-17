package com.acintyo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDto {
	
	private String employeeId;
	
	private String oldPassword;
	
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{validation.pattern.newPassword}")
	//@Pattern(regexp = "", message = "{validation.pattern.password}")
	@NotBlank(message = "{validation.pattern.newPassword.notBlank}")
	private String newPassword;

}
