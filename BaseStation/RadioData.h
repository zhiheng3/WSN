// $Id$

#ifndef RADIODATA_H
#define RADIODATA_H

enum {
  AM_SENSORDATAMSG = 6,
  AM_BASESTATIONMSG = 7,
  AM_ACKMSG = 8
};

typedef nx_struct SensorDataMsg{
    nx_uint8_t typeCode; //0xa0
    nx_uint8_t fromId;
    nx_uint8_t toId;
    nx_uint16_t seqNo;
    nx_uint16_t temperature;
    nx_uint16_t humidity;
    nx_uint16_t illuminance;
    nx_uint32_t timeStamp;
}SensorDataMsg;

typedef nx_struct BaseStationMsg{
    nx_uint8_t typeCode; //0xb1
    nx_uint16_t frequency;
    nx_uint32_t startTime;
}BaseStationMsg;

typedef nx_struct ACKMsg{
    nx_uint8_t typeCode; //0xc2
    nx_uint8_t fromId;
    nx_uint8_t toId;
    nx_uint16_t seqNo;
}ACKMsg;

#endif
