package br.com.zupacademy.luiz.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.luiz.mercadolivre.seguranca.validacao.UnicoValor;

import java.io.Serializable;

public class UsuarioRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Email
	@NotBlank
	@UnicoValor(targetClass = Usuario.class, field = "login", message = "Já existe um E-mail cadastrado.")
	private String login;

	@Length(min = 6)
	@NotBlank
	private String senha;

	public Usuario toModel() {
		return new Usuario(this.login, new SenhaLimpa(this.senha));
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

}
