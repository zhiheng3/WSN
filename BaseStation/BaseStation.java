import java.io.IOException;

import net.tinyos.message.*;
import net.tinyos.packet.*;
import net.tinyos.util.*;

import java.util.*;
public class BaseStation implements MessageListener {

  private MoteIF moteIF;
  
  public BaseStation(MoteIF moteIF) {
    this.moteIF = moteIF;
    this.moteIF.registerListener(new BaseStationMsg(), this);
  }

  public void sendPackets(short typecode,int frequency,long starttime) {
    //int counter = 0;
    BaseStationMsg payload = new BaseStationMsg();
    
    try { 
      //while(true){
        payload.set_typeCode(typecode);
        payload.set_frequency(frequency);
        payload.set_startTime(starttime);
        moteIF.send(0, payload);
        System.out.println("Send Packet # "+typecode+" # "+frequency+" # "+starttime+" #");
        try {Thread.sleep(1000);}
        catch (InterruptedException exception) {}        
      //}
      //System.out.println("Sending packet " + counter);

      
    }
    catch (IOException exception) {
      System.err.println("Exception thrown when sending packets. Exiting.");
      System.err.println(exception);
    }
  }

  public void messageReceived(int to, Message message) {
    
    //SensorDataMsg msg = (SensorDataMsg)message;
    //System.out.println("Received packet sequence number " + msg.get_typeCode()+" "+msg.get_fromId()+" ");
  }
  
  private static void usage() {
    System.err.println("usage: BaseStation [-comm <source>]");
  }

  private void getInput(){
    int freq;
    short type = 0xb1;
    Scanner input = new Scanner(System.in);
    long starttime = System.currentTimeMillis() % 1000000000;
    try{
        while(true){
          freq = input.nextShort();
          System.out.println("Frequency is "+freq);
          if(freq > 0){
            sendPackets(type,freq,starttime);
          }
        }
    }catch(Exception e){
      System.out.println("Error occurs input");
      
    }

  }
  
  public static void main(String[] args) throws Exception {
    String source = null;
    if (args.length == 2) {
      if (!args[0].equals("-comm")) {
        usage();
        System.exit(1);
      }
      source = args[1];
    }
    else if (args.length != 0) {
      usage();
      System.exit(1);
    }
    
    PhoenixSource phoenix;
    
    if (source == null) {
      phoenix = BuildSource.makePhoenix(PrintStreamMessenger.err);
    }
    else {
      phoenix = BuildSource.makePhoenix(source, PrintStreamMessenger.err);
    }

    MoteIF mif = new MoteIF(phoenix);
    BaseStation broadcast = new BaseStation(mif);
    
    //for(short i = 0; i < 8;i++){
    //  broadcast.sendPackets(a,i,c);
    //}
    broadcast.getInput();
    //broadcast.sendPackets(a,b,c);
    //broadcast.sendPackets(a,b,c);
  }


}
