package com.crud.bookshop.controller.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
	
	@NotEmpty
	private String username;
	
	@NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;
    
}
