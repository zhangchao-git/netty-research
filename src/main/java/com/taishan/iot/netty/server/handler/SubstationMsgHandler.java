package com.taishan.iot.netty.server.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.taishan.iot.dao.TestDao;
import com.taishan.iot.model.entity.Test;
import com.taishan.iot.netty.model.req.DetectorData;
import com.taishan.iot.netty.model.req.SensorData;
import com.taishan.iot.netty.model.req.SubstationData;
import com.taishan.iot.netty.model.req.SubstationMsg;
import com.taishan.iot.netty.model.resp.CommonByteResp;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class SubstationMsgHandler extends SimpleChannelInboundHandler<SubstationMsg> {

    @Autowired
    private TestDao testDao;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SubstationMsg msg) throws Exception {
        log.error(" 分站数据包：" + msg.toString() + "\n");
        //存储数据
        Test test = new Test();
        test.setCrdate(DateUtil.date());
        StringBuffer sql = new StringBuffer();
        sql.append("子站数量:" + msg.getSubstationQty());
        for (SubstationData substationData : msg.getSubstationDataList()) {
            for (DetectorData detectorData : substationData.getDetectorDataList()) {
                for (SensorData sensorData : detectorData.getSensorDataList()) {
                    String mes = StrUtil.format("子站地址:{}, 监测仪数量:{}, 监测仪地址:{}, 年:{}, 月:{}, 日:{}, 时:{}, 分:{}, 秒:{}, 节点类型:{}, 通道数量:{}, 主板电量:{},	" +
                                    "无线电量:{}, 通道编号:{}, 传感器类型:{}, 小数点:{}, 正负号:{}, 测量值{}",
                            substationData.getSubstationAddr(), substationData.getDetectorQty(), detectorData.getDetectorAddr(),
                            detectorData.getYear(), detectorData.getMonth(), detectorData.getDay(),
                            detectorData.getHour(), detectorData.getMinute(), detectorData.getSecond(),
                            detectorData.getNodeType(), detectorData.getSensorQty(), detectorData.getMainBoardPower(), detectorData.getWirelessPower(),
                            sensorData.getSensorNum(), sensorData.getSensorType(), sensorData.getDecimalPoint(), sensorData.getSign(), sensorData.getValue()
                    );
                    sql.append(mes);
                    System.out.println(mes);
                }
            }
        }

        test.setData(sql.toString());
        testDao.insert(test);

        CommonByteResp commonDateResp = new CommonByteResp(msg);
        ctx.writeAndFlush(commonDateResp);
    }
}
