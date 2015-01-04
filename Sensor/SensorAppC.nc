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
	components new TimerMilliC() as TimerStart;
	

    components ActiveMessageC;
    components new AMSenderC(AM_SENSORDATAMSG) as AMSender0;
    components new AMReceiverC(AM_SENSORDATAMSG) as AMReceiver0;


	components new AMSenderC(AM_BASESTATIONMSG) as AMSender1;
	components new AMReceiverC(AM_BASESTATIONMSG) as AMReceiver1;


    //Sensor
    components new SensirionSht11C() as Sensor1;
    components new HamamatsuS1087ParC() as Sensor2;

    App.Boot -> MainC;
    App.Leds -> LedsC;
    App.Timer0 -> Timer0;
	App.Timer1 -> Timer1;
	App.TimerStart -> TimerStart;	
	
    App.Packet0 -> AMSender0;
    App.AMPacket0 -> AMSender0;
    App.AMControl-> ActiveMessageC;
    App.AMSend0 -> AMSender0;
    App.Receive0 -> AMReceiver0;
	
	App.Packet1 -> AMSender1;
	App.AMPacket1 -> AMSender1;
	App.AMSend1 -> AMSender1;
	App.Receive1 -> AMReceiver1;


    //Sensor
    App.ReadTemp -> Sensor1.Temperature;
    App.ReadHum -> Sensor1.Humidity;
    App.ReadHama -> Sensor2;
}
