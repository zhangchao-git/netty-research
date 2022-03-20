package com.taishan.iot.netty.model.req;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 子站数据
 */
@Data
public class SubstationData {

    /**
     * 子站地址
     */
    private Short substationAddr;

    /**
     * 检测仪数量
     */
    private Short detectorQty;

    /**
     * 检测仪数据
     */
    private List<DetectorData> detectorDataList = new ArrayList<>();
}
