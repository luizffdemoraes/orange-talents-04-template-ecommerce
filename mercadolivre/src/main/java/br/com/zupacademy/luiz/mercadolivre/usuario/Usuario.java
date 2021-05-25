package br.com.zupacademy.luiz.mercadolivre.usuario;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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

	public Usuario(@Email @NotBlank String login,
			@Valid @NotNull SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(login), "Login não pode ser em branco");
		Assert.notNull(senhaLimpa, "O objeto do tipo senha limpa não pode ser nulo");
		
		this.login = login;
		this.senha = senhaLimpa.hash();
	}

	public Long getId() {
		return id;
	}

}
