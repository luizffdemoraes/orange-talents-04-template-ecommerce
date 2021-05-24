package br.com.zupacademy.luiz.mercadolivre.usuario;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	private Instant instante = Instant.now();

	@Email
	@Column(unique = true, nullable = false)
	private String login;

	@Column(nullable = false)
	private String senha;

	@Deprecated
	public Usuario() {
	}

	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", instante=" + instante + ", login=" + login + ", senha=" + senha + "]";
	}

	public Long getId() {
		return id;
	}	

}
