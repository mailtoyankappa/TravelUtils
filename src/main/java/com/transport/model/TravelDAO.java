package com.transport.model;

import java.util.List;

import com.transport.service.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TravelDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Travel> list() {
		String sql = "SELECT * FROM SALES";

		List<Travel> listSale = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(Travel.class));

		return listSale;
	}
	
	public void save(Travel sale) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("sales").usingColumns("item", "quantity", "amount");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
		
		insertActor.execute(param);		
	}
	
	public Travel get(int id) {
		String sql = "SELECT * FROM SALES WHERE id = ?";
		Object[] args = {id};
		Travel sale = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Travel.class));
		return sale;
	}
	
	public void update(Travel sale) {
		String sql = "UPDATE SALES SET item=:item, quantity=:quantity, amount=:amount WHERE id=:id";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);		
	}
	
	public void delete(int id) {
		String sql = "DELETE FROM SALES WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
}
