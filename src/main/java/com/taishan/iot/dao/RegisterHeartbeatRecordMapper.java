package com.taishan.iot.dao;

import com.taishan.iot.model.entity.RegisterHeartbeatRecord;

import java.util.List;

public interface RegisterHeartbeatRecordMapper {
    int insert(RegisterHeartbeatRecord row);

    List<RegisterHeartbeatRecord> selectAll();
}
