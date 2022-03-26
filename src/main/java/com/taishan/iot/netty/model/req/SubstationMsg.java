package com.taishan.iot.netty.model.req;

import com.taishan.iot.netty.model.DataPacket;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分站发送数据包
 * <p>
 * 子站-》检测仪器-》通道
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
    private List<SubstationData> substationDataList = new ArrayList<>();

    public SubstationMsg() {
    }

    public SubstationMsg(ByteBuf byteBuf) {
        super(byteBuf);
    }

    /**
     * 请求报文重写
     */
    @Override
    protected void parseBody() {
        //子站数量
        substationQty = this.payload.readUnsignedByte();
        //循环子站
        for (int substationIndex = 0; substationQty > substationIndex; substationIndex++) {
            //子站数据
            SubstationData substationData = new SubstationData();
            Short substationAddr = this.payload.readUnsignedByte();
            substationData.setSubstationAddr(substationAddr);

            //检测仪数量
            Short detectorQty = this.payload.readUnsignedByte();
            substationData.setDetectorQty(detectorQty);

            for (int detectorIndex = 0; detectorQty > detectorIndex; detectorIndex++) {
                //检测仪
                DetectorData detectorData = new DetectorData();
                if (detectorIndex != 0) {
                    //读取子站地址，检测仪器数量字段，暂时用不到
                    this.payload.readUnsignedByte();
                    this.payload.readUnsignedByte();
                }
                detectorData.setDetectorAddr(this.payload.readUnsignedByte());
                detectorData.setYear(this.payload.readUnsignedByte());
                detectorData.setMonth(this.payload.readUnsignedByte());
                detectorData.setDay(this.payload.readUnsignedByte());
                detectorData.setHour(this.payload.readUnsignedByte());
                detectorData.setMinute(this.payload.readUnsignedByte());
                detectorData.setSecond(this.payload.readUnsignedByte());
                detectorData.setTypeAndQty(this.payload.readUnsignedByte());
                detectorData.setNodeTypeAndSensorQty();
                detectorData.setMainBoardPower(this.payload.readUnsignedByte());
                detectorData.setWirelessPower(this.payload.readUnsignedByte());

                for (int sensorIndex = 0; detectorData.getSensorQty() > sensorIndex; sensorIndex++) {
                    SensorData sensorData = new SensorData();
                    sensorData.setNumAndType(this.payload.readUnsignedByte());
                    sensorData.setSensorNumAndSensorType();
                    sensorData.setComputing(this.payload.readUnsignedByte());
                    sensorData.setDecimalPointAndSign();
                    sensorData.setValue(this.payload.readUnsignedShort() / sensorData.getDenominator());
                    detectorData.getSensorDataList().add(sensorData);
                }

                substationData.getDetectorDataList().add(detectorData);
            }
            substationDataList.add(substationData);
        }
    }
}
