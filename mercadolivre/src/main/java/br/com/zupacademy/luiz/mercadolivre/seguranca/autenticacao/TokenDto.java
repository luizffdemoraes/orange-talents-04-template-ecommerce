package br.com.zupacademy.luiz.mercadolivre.seguranca.autenticacao;

public class TokenDto {
    private final String token;
    private final String tipo;

    public TokenDto(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}