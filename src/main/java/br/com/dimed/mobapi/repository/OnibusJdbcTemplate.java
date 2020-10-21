package br.com.dimed.mobapi.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.dimed.mobapi.model.dto.OnibusLinhaDto;
import br.com.dimed.mobapi.model.filter.RaioItinerarioFilter;

@Repository
public class OnibusJdbcTemplate {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Dado uma origem em O=(1.3963 rad, -0.6981 rad) latitude e longitude
	 * temos nossa primeira query ao banco de dados em um raio de 300 km
	 * SELECT * FROM LOCAIS WHERE acos(sin(1.3963) * sin(Lat) + cos(1.3963) * cos(Lat) * cos(Lon - (-0.6981))) * 6371 <= 300;
	 * 
	 * {@link https://mathmesquita.me/2017/01/16/filtrando-localizacao-em-um-raio.html}
	 * 
	 * @param filter
	 * @return
	 */
	public List<OnibusLinhaDto> findByRaio(RaioItinerarioFilter filter) {
		var args = new Object[] {filter.getLatitude(), filter.getLatitude(), filter.getLongitude(), filter.getRaio()};
		var sql = "select * from onibus_linha l inner join onibus_itinerario i on l.id = i.id_onibus_linha " + 
				"where acos(sin(?) * sin(i.latitude) + cos(?) * cos(i.latitude) * cos(i.longitude - (?))) * 6371 <= ?";
		
		return jdbcTemplate.query(sql, args, (rs, rowNum) -> novoOnibusLinha(rs));
	}

	private OnibusLinhaDto novoOnibusLinha(ResultSet rs) throws SQLException {
		OnibusLinhaDto dto = new OnibusLinhaDto();
		dto.setId(rs.getLong("id"));
		dto.setCodigo(rs.getString("codigo"));
		dto.setNome(rs.getString("nome"));
		
		return dto;
	}
	
}
