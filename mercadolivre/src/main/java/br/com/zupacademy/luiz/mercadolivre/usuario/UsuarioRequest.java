package br.com.zupacademy.luiz.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import java.util.Base64;

public class UsuarioRequest {

	@Email
	@NotBlank
	private String login;

	@Length(min = 6)
	@NotBlank
	private String senha;

	public Usuario toModel() {
		String senhaCodificada = Base64.getEncoder().encodeToString(senha.getBytes());
		return new Usuario(login, senhaCodificada);

	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

}
