package br.com.dimed.mobapi.config;

import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dimed.mobapi.service.IntegracaoService;

@Component
public class IntegracaoCallback implements Callback {
	
	private static final Logger log = LoggerFactory.getLogger(IntegracaoCallback.class);
	
	@Autowired
	private IntegracaoService integracaoService;

	@Override
	public boolean supports(Event event, Context context) {
		return event.equals(Event.AFTER_MIGRATE) || event.equals(Event.AFTER_MIGRATE_ERROR);
	}

	@Override
	public boolean canHandleInTransaction(Event event, Context context) {
		return true;
	}

	@Override
	public void handle(Event event, Context context) {
		if (event.equals(Event.AFTER_MIGRATE)) {
			integracaoService.importar();
		} else {
			log.error("Falhou");
		}
	}
	
}
