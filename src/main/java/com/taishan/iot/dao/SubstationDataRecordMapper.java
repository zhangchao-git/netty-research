package com.taishan.iot.dao;

import com.taishan.iot.model.entity.SubstationDataRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubstationDataRecordMapper {

    int insertList(@Param("rows") List<SubstationDataRecord> rows);

    List<SubstationDataRecord> selectAll();
}
