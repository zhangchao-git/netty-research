package com.taishan.iot.netty.server.handler;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.taishan.iot.dao.SubstationDataMapper;
import com.taishan.iot.dao.SubstationDataRecordMapper;
import com.taishan.iot.model.entity.SubstationDataRecord;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@ChannelHandler.Sharable
public class SubstationMsgHandler extends SimpleChannelInboundHandler<SubstationMsg> {

    @Autowired
    private SubstationDataRecordMapper substationDataRecordMapper;

    @Autowired
    private SubstationDataMapper substationDataMapper;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SubstationMsg msg) throws Exception {

        List<SubstationDataRecord> recordList = new ArrayList<>();
        //存储数据
        for (SubstationData substationData : msg.getSubstationDataList()) {
            for (DetectorData detectorData : substationData.getDetectorDataList()) {
                //TODO 拼写20XX年
                String dateTimeStr = "20" + StrUtil.padPre(detectorData.getYear(), 2, "0") + "-" + StrUtil.padPre(detectorData.getMonth(), 2, "0") + "-" + StrUtil.padPre(detectorData.getDay(), 2, "0") + " "
                        + StrUtil.padPre(detectorData.getHour(), 2, "0") + ":" + StrUtil.padPre(detectorData.getMinute(), 2, "0") + ":" + StrUtil.padPre(detectorData.getSecond(), 2, "0");
                log.error(dateTimeStr);
                Date uploadDateTime = DateUtil.parse(dateTimeStr, DatePattern.NORM_DATETIME_FORMAT);
                for (SensorData sensorData : detectorData.getSensorDataList()) {

                    SubstationDataRecord data = new SubstationDataRecord(
                            msg.getHeader().getSendAddr(), substationData.getSubstationAddr(), substationData.getDetectorQty(),
                            detectorData.getDetectorAddr(), uploadDateTime, detectorData.getNodeType(),
                            detectorData.getSensorQty(), detectorData.getMainBoardPower(), detectorData.getWirelessPower(),
                            sensorData.getSensorNum(), sensorData.getSensorType(), sensorData.getValue(), DateUtil.date()
                    );
                    recordList.add(data);
                }
            }
        }

        substationDataRecordMapper.insertList(recordList);
        substationDataMapper.deleteBySendAddr(msg.getHeader().getSendAddr());
        substationDataMapper.insertList(recordList);

        CommonByteResp commonDateResp = new CommonByteResp(msg);
        ctx.writeAndFlush(commonDateResp);
    }
}
