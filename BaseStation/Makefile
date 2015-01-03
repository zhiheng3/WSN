COMPONENT=BaseStationC
CFLAGS += -DCC2420_NO_ACKNOWLEDGEMENTS
CFLAGS += -DCC2420_NO_ADDRESS_RECOGNITION
CFLAGS += -DTASKLET_IS_TASK

BUILD_EXTRA_DEPS += BaseStation.class
BUILD_EXTRA_DEPS += SensorDataMsg.class
BUILD_EXTRA_DEPS += BaseStationMsg.class
BUILD_EXTRA_DEPS += ACKMsg.class

BaseStation.class:$(wildcard *.java) SensorDataMsg.java BaseStationMsg.java BaseStation.java
	javac *java

SensorDataMsg.class:SensorDataMsg.java
	javac SensorDataMsg.java
SensorDataMsg.java:
	mig java -target=null -java-classname=SensorDataMsg RadioData.h SensorDataMsg -o $@



BaseStationMsg.class: BaseStationMsg.java
	javac BaseStationMsg.java
BaseStationMsg.java:
	mig java -target=null -java-classname=BaseStationMsg RadioData.h BaseStationMsg -o $@


ACKMsg.class:ACKMsg.java
	javac ACKMsg.java
ACKMsg.java:
	mig java -target=null -java-classname=ACKMsg RadioData.h ACKMsg -o $@

include $(MAKERULES)

