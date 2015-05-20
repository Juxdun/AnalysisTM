package com.juxdun.analysisTM.analysis.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.juxdun.analysisTM.analysis.dao.DetailDao;
import com.juxdun.analysisTM.analysis.entities.Detail;
import com.juxdun.analysisTM.analysis.entities.Product;

@Repository
public class DetailDaoImpl implements DetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void batchInsertDetails(final List<Detail> details) {
		String sql = "INSERT tm_detail(name, resume, mon_sale_vol, cumu_comment) values(?,?,?,?)";
		int[] retVals = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				Detail c = details.get(index);
				ps.setString(1, c.getName());
				ps.setString(2, c.getResume());
				ps.setString(3, c.getMonSaleVol());
				ps.setString(4, c.getCumuComment());
			}
			
			@Override
			public int getBatchSize() {
				return details.size();
			}
		});
		
		for(int i = 0; i < retVals.length; i++){
			if(retVals[i] < 0)
				System.out.println("处理第" + (i + 1) + "条记录失败");
		}

	}
}
