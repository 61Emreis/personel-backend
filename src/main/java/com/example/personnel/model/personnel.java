package com.example.personnel.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class personnel {

    @SuppressWarnings("unused")
	private Long id;

    @NotBlank(message = "Ad boş olamaz")
    private String firstName;

    @NotBlank(message = "Soyad boş olamaz")
    private String lastName;

    @Email(message = "Geçerli bir email adresi girin")
    private String email;

    @NotBlank(message = "Departman boş olamaz")
    private String department;

	public Object getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFirstName(Object firstName2) {
		// TODO Auto-generated method stub
		
	}

	public void setEmail(Object email2) {
		// TODO Auto-generated method stub
		
	}

	public void setDepartment(Object department2) {
		// TODO Auto-generated method stub
		
	}

	public void setId(Long id2) {
		// TODO Auto-generated method stub
		
	}
}
