package br.com.dimed.mobapi.exception;

public class NenhumRegistroEncotradoException extends MessageException {

	private static final long serialVersionUID = 1L;
	
	private static final String NENHUM_REGISTRO_ENCONTRADO = "nenhum-registro-encontrado";
	
	public NenhumRegistroEncotradoException() {
		super(NENHUM_REGISTRO_ENCONTRADO);
	}
	
	public NenhumRegistroEncotradoException(Throwable throwable) {
		super(throwable, NENHUM_REGISTRO_ENCONTRADO);
	}
	
}
