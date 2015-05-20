package com.juxdun.analysisTM.analysis.dao;

import java.util.List;

import com.juxdun.analysisTM.analysis.entities.Detail;

public interface DetailDao {

	void batchInsertDetails(List<Detail> details);
}
