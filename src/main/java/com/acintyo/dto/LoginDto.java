
package com.acintyo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

public class LoginDto {

	@NotBlank(message = "{validation.notblank.employeeId}")
	private String employeeIdORemail;

	@NotBlank(message = "{validation.notblank.password}")
	//@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$", message = "{validation.pattern.employeeId}")
	//@Pattern(regexp = "", message = "{validation.pattern.password}")
	private String password;

}
