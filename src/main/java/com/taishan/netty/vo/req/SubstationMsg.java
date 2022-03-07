package com.taishan.netty.vo.req;

import com.taishan.netty.vo.DataPacket;
import lombok.Data;

import java.util.List;

/**
 * 分站发送数据包
 *
 * TODO 层级不对，
 *  子站-》检测仪器-》通道
 */
@Data
public class SubstationMsg extends DataPacket {

    /**
     * 子站数量
     */
    private Short substationQty;

    /**
     * 子站数据List
     */
    private List<SubstationData> substationDataList;

    /**
     * 请求报文重写
     */
    @Override
    protected void parseBody() {
    }
}
