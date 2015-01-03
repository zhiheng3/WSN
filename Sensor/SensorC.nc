// $Id$

/**
 *
 * @author Zhao Zhiheng
 * @date Jan 2, 2015
 */
#include <Timer.h>
#include "RadioData.h"
#include "printf.h"

module SensorC{
    uses interface Boot;
    uses interface Leds;
    uses interface Timer<TMilli> as Timer0;
	uses interface Timer<TMilli> as Timer1;

    uses interface Packet;
    uses interface AMPacket;
    uses interface AMSend;
    uses interface Receive;
    uses interface SplitControl as AMControl;

    //Sensor
    uses interface Read<uint16_t> as ReadTemp;
	uses interface Read<uint16_t> as ReadHum;
	uses interface Read<uint16_t> as ReadHama;
}
implementation {
    uint16_t temperature;
    uint16_t humidity;
    uint16_t illuminance;

    uint16_t period0;
    uint16_t seqNo;
	uint16_t ackNo;
    message_t pkt;
	message_t pkt2;
	message_t pkt3;
	ACKMsg* ack;
    bool busy = FALSE;

    void restart(){
        temperature = 0;
        humidity = 0;
        illuminance = 0;
		busy = FALSE;
		ackNo=0;
        call Leds.led0On();
        call Leds.led1On();
        call Leds.led2Off();

        call ReadTemp.read();
        call ReadHum.read();
        call ReadHama.read();

        call Timer0.startPeriodic(period0);
    }

    bool resetTimer0(){
        call Timer0.startPeriodic(period0);
        return TRUE;
    }

    bool setSensorData(SensorDataMsg* btrpkt){  
        btrpkt->typeCode = 0xa0;
        btrpkt->fromId = TOS_NODE_ID;
		if (TOS_NODE_ID == 1)
        	btrpkt->toId = 0;
		else btrpkt->toId = 1;
        btrpkt->seqNo = seqNo;
        btrpkt->temperature = temperature;
        btrpkt->humidity = humidity;
        btrpkt->illuminance = illuminance;
        btrpkt->timeStamp = call Timer0.getNow();
        return TRUE;
    }
    
    event void Boot.booted(){
        period0 = 1000;
        call AMControl.start();
    }

    event void AMControl.startDone(error_t err){
        if (err == SUCCESS){
            restart();
        }
        else{
            call AMControl.start();
        }
    }

    event void AMControl.stopDone(error_t err){
    }

    event void Timer0.fired() {
       if (temperature == 0 || humidity == 0 || illuminance == 0){
        //    return ;
        }
        seqNo++;
		while (busy)
		{};
        if (!busy){
            SensorDataMsg* btrpkt = (SensorDataMsg*)(call Packet.getPayload(&pkt, sizeof(SensorDataMsg)));
            if (btrpkt == NULL){
                return ;
            }
            setSensorData(btrpkt);
            if (call AMSend.send(AM_BROADCAST_ADDR, &pkt, sizeof(SensorDataMsg)) == SUCCESS){
                busy = TRUE;
                call Leds.led2On();
				if(TOS_NODE_ID==2){
					//call Timer1.startOneShot(200);
				}
            }        
        }

    }

	event void Timer1.fired() {
        if(seqNo <= ackNo)
			return;
		while (busy)
		{};
            if (call AMSend.send(AM_BROADCAST_ADDR, &pkt, sizeof(SensorDataMsg)) == SUCCESS){
                busy = TRUE;
                call Leds.led2On();
				//call Timer1.startOneShot(20);
            }        
    }
    
    event void AMSend.sendDone(message_t* msg, error_t err){
        //if (&pkt == msg || &pkt2 == msg){
printf("1\n");printfflush();
            busy = FALSE;
            call Leds.led2Off();
        //}
    }
	
	bool transSensorData(SensorDataMsg* btrpkt,SensorDataMsg* sourse){  
        btrpkt->typeCode = sourse->typeCode;
        btrpkt->fromId = sourse->fromId;
       	btrpkt->toId = 0;
        btrpkt->seqNo = sourse->seqNo;
        btrpkt->temperature = sourse->temperature;
        btrpkt->humidity = sourse->humidity;
        btrpkt->illuminance = sourse->illuminance;
        btrpkt->timeStamp = sourse->timeStamp;
        return TRUE;
    }    
	
	bool setAckData(ACKMsg* ack0, uint8_t toId,uint16_t seq_No){  
        ack0->typeCode = 0xc2;
        ack0->fromId = TOS_NODE_ID;
		ack0->toId = toId;
        ack0->seqNo = seq_No;
        return TRUE;
    }

    event message_t* Receive.receive(message_t* msg, void* payload, uint8_t len){
		printf("-\n");printfflush();
        if (len == sizeof(SensorDataMsg)){
			SensorDataMsg* message =(SensorDataMsg*) payload;
			
			if ((TOS_NODE_ID == 1)&&(message->toId == 1)){
				SensorDataMsg* btrpkt = (SensorDataMsg*)(call Packet.getPayload(&pkt2, sizeof(SensorDataMsg)));
printf("-0\n");printfflush();
            	if (btrpkt == NULL){
                	return msg;
            	}
printf("-1\n");printfflush();
				transSensorData(btrpkt, message);
				while (busy)
				{};
					//return;
				if (call AMSend.send(AM_BROADCAST_ADDR, &pkt2, sizeof(SensorDataMsg)) == SUCCESS){
                	busy = TRUE;
					call Leds.led2On();
printf("0\n");printfflush();
      			}
				call Leds.led0Off();
/*
				while (busy)
				{};
printf("2\n");printfflush();
				ack = (ACKMsg*)(call Packet.getPayload(&pkt3, sizeof(ACKMsg)));
            	if (ack == NULL){
                	return msg;
            	}
            	setAckData(ack,2,message->seqNo);
				
            	if (call AMSend.send(AM_BROADCAST_ADDR, &pkt3, sizeof(ACKMsg)) == SUCCESS){
                	busy = TRUE;
            	}  
*/ 

				
			} 
			
        }
		
		if (len == sizeof(ACKMsg)){
			ACKMsg* message =(ACKMsg*) payload;
			if ((TOS_NODE_ID == 2)&&(message->toId == 2)){
				if(message->seqNo > ackNo){
					ackNo = message->seqNo;
				}
			}
		}
        return msg;
    }

	

    event void ReadTemp.readDone(error_t result, uint16_t data){
        if (result == SUCCESS){
            temperature = data;
        }
        else{
            call Leds.led1Off();
        }
        call ReadTemp.read();
    }

    event void ReadHum.readDone(error_t result, uint16_t data){
        if (result == SUCCESS){
            humidity = data;
        }
        else{
            call Leds.led1Off();
        }
        call ReadHum.read();
    }

    event void ReadHama.readDone(error_t result, uint16_t data){
        if (result == SUCCESS){
            illuminance = data;
        }
        else{
            call Leds.led1Off();
        }
        call ReadHama.read();
    }
}
