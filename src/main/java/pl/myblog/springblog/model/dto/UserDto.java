package pl.myblog.springblog.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

    @NotBlank(message = "Insert your name")
    private String name;
    @NotBlank(message = "Insert your lastname")
    private String lastname;
    @NotBlank(message = "Insert your emial")
    @Email(message = "Your email is not valid")
    private String email;
    @Length(min = 6, message = "Your password requires at least 6 characters")
    @Pattern(regexp = "([A-Z]+.*[0-9]+)|([0-9]+.*[A-Z])", message = "Your password must have one capital letter and one digit")
    private String password;

}
