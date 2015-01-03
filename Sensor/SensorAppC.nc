// $Id$

/**
 *
 * @author Zhao Zhiheng
 * @date Jan 2, 2015
 */
#include <Timer.h>
#include "RadioData.h"

configuration SensorAppC{
}
implementation{
    components MainC, LedsC;
    components SensorC as App;
    components new TimerMilliC() as Timer0;
	components new TimerMilliC() as Timer1;
    components ActiveMessageC;
    components new AMSenderC(AM_SENSORDATAMSG);
    components new AMReceiverC(AM_SENSORDATAMSG);

    //Sensor
    components new SensirionSht11C() as Sensor1;
    components new HamamatsuS1087ParC() as Sensor2;

    App.Boot -> MainC;
    App.Leds -> LedsC;
    App.Timer0 -> Timer0;
	App.Timer1 -> Timer1;
    App.Packet -> AMSenderC;
    App.AMPacket -> AMSenderC;
    App.AMControl -> ActiveMessageC;
    App.AMSend -> AMSenderC;
	App.AMSend1 -> AMSenderC;
    App.Receive -> AMReceiverC;

    //Sensor
    App.ReadTemp -> Sensor1.Temperature;
    App.ReadHum -> Sensor1.Humidity;
    App.ReadHama -> Sensor2;
}
