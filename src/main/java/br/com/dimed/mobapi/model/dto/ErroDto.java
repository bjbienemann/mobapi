package br.com.dimed.mobapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author mayara.goldacker
 *
 */
public class ErroDto {

	private String mensagem;
	
	@JsonInclude(Include.NON_NULL)
	private String causa;
	
	public ErroDto(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public ErroDto( String mensagem, String causa) {
		
		this.mensagem = mensagem;
		this.causa = causa;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public String getCausa() {
		return causa;
	}

}
