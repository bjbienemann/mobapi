package br.com.dimed.mobapi.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.dimed.mobapi.model.dto.OnibusLinhaDto;
import br.com.dimed.mobapi.model.filter.RaioItinerarioFilter;

@Repository
public class OnibusJdbcTemplate {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Dado uma origem em O=(1.3963 rad, -0.6981 rad) latitude e longitude temos
	 * nossa primeira query ao banco de dados em um raio de 300 km SELECT * FROM
	 * LOCAIS WHERE acos(sin(1.3963) * sin(Lat) + cos(1.3963) * cos(Lat) * cos(Lon -
	 * (-0.6981))) * 6371 <= 300;
	 * 
	 * {@link https://mathmesquita.me/2017/01/16/filtrando-localizacao-em-um-raio.html}
	 * 
	 * @param filter
	 * @return
	 */
	public Page<OnibusLinhaDto> findByRaio(RaioItinerarioFilter filter, Pageable pageable) {
		var args = new Object[] { filter.getLatitude(), filter.getLatitude(), filter.getLongitude(), filter.getRaio() };
		var where = "where acos(sin(?) * sin(i.latitude) + cos(?) * cos(i.latitude) * cos(i.longitude - (?))) * 6371 <= ?";
		var sql = "select count(i.id) from onibus_itinerario i ".concat(where);

		var count = jdbcTemplate.queryForObject(sql, args, Integer.class);

		sql = "select * from onibus_linha l inner join onibus_itinerario i on l.id = i.id_onibus_linha "
				.concat(where)
				.concat(limitOffset(pageable));

		var linhas = jdbcTemplate.query(sql, args, (rs, rowNum) -> novoOnibusLinha(rs));

		return new PageImpl<OnibusLinhaDto>(linhas, pageable, count);
	}

	private String limitOffset(Pageable pageable) {
		return new StringBuilder(" limit ")
				.append(pageable.getPageSize())
				.append(" offset ")
				.append(pageable.getOffset()).toString();
	}

	private OnibusLinhaDto novoOnibusLinha(ResultSet rs) throws SQLException {
		OnibusLinhaDto dto = new OnibusLinhaDto();
		dto.setId(rs.getLong("id"));
		dto.setCodigo(rs.getString("codigo"));
		dto.setNome(rs.getString("nome"));

		return dto;
	}

}
