package br.com.dimed.mobapi.service;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import br.com.dimed.mobapi.exception.MessageException;
import br.com.dimed.mobapi.model.dto.TaxiDto;
import br.com.dimed.mobapi.properties.ApiProperties;

@Service
@Scope("singleton")
public class TaxiService {
	
	private static final char SEPARATOR = '#';
	private static final String TAXI_TXT = "taxi.txt";

	@Autowired
	private ApiProperties apiProperties;
	
	private List<TaxiDto> taxis;
	
	@PostConstruct
	public void carregar() {
		Path path = Paths.get(apiProperties.getHome(), TAXI_TXT);
		try (var reader = new FileReader(path.toFile(), StandardCharsets.UTF_8)) {
			taxis = new CsvToBeanBuilder<TaxiDto>(reader)
					.withSeparator(SEPARATOR)
					.withType(TaxiDto.class)
					.build().parse();
			
		} catch (Exception e) {
			throw new MessageException("taxi.erro-ao-carregar-arquivo");
		}
	}
	
	public void salvar(TaxiDto dto) {
		Optional<TaxiDto> findFirst = taxis.stream()
				.filter(t -> t.equals(dto))
				.findFirst();
		
		if (findFirst.isPresent()) {
			BeanUtils.copyProperties(dto, findFirst.get(), "nomePonto");
		} else {
			if (Objects.isNull(dto.getDataHora())) {
				dto.setDataHora(LocalDateTime.now());
			}
			
			taxis.add(dto);
		}
		
		salvar();
	}
	
	private void salvar() {
		Path path = Paths.get(apiProperties.getHome(), TAXI_TXT);
		try (var writer = new OutputStreamWriter(new FileOutputStream(path.toFile()), StandardCharsets.UTF_8)) {
			StatefulBeanToCsv<TaxiDto> beanToCsv = new StatefulBeanToCsvBuilder<TaxiDto>(writer)
				.withSeparator(SEPARATOR)
				.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.build();
			
			beanToCsv.write(taxis);
		} catch (Exception e) {
			throw new MessageException("taxi.erro-ao-salvar-arquivo");
		}
	}
	
	public List<TaxiDto> filtrar(String nome) {
		if (Objects.isNull(nome) || nome.isBlank()) {
			return taxis;
		}
		
		return taxis.stream().filter(t -> t.getNomePonto().toLowerCase()
				.contains(nome.toLowerCase()))
				.collect(Collectors.toList());
	}
	
}
