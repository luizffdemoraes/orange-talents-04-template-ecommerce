package br.com.zupacademy.luiz.mercadolivre.produto.imagem;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderFake {

	public Set<String> enviar(List<MultipartFile> imagens) {
		return imagens.stream()
				.map(imagem -> "http://bucket.io" 
						+ imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}
	
}
