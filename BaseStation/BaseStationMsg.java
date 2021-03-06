/**
 * This class is automatically generated by mig. DO NOT EDIT THIS FILE.
 * This class implements a Java interface to the 'BaseStationMsg'
 * message type.
 */

public class BaseStationMsg extends net.tinyos.message.Message {

    /** The default size of this message type in bytes. */
    public static final int DEFAULT_MESSAGE_SIZE = 7;

    /** The Active Message type associated with this message. */
    public static final int AM_TYPE = 7;

    /** Create a new BaseStationMsg of size 7. */
    public BaseStationMsg() {
        super(DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /** Create a new BaseStationMsg of the given data_length. */
    public BaseStationMsg(int data_length) {
        super(data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new BaseStationMsg with the given data_length
     * and base offset.
     */
    public BaseStationMsg(int data_length, int base_offset) {
        super(data_length, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new BaseStationMsg using the given byte array
     * as backing store.
     */
    public BaseStationMsg(byte[] data) {
        super(data);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new BaseStationMsg using the given byte array
     * as backing store, with the given base offset.
     */
    public BaseStationMsg(byte[] data, int base_offset) {
        super(data, base_offset);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new BaseStationMsg using the given byte array
     * as backing store, with the given base offset and data length.
     */
    public BaseStationMsg(byte[] data, int base_offset, int data_length) {
        super(data, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new BaseStationMsg embedded in the given message
     * at the given base offset.
     */
    public BaseStationMsg(net.tinyos.message.Message msg, int base_offset) {
        super(msg, base_offset, DEFAULT_MESSAGE_SIZE);
        amTypeSet(AM_TYPE);
    }

    /**
     * Create a new BaseStationMsg embedded in the given message
     * at the given base offset and length.
     */
    public BaseStationMsg(net.tinyos.message.Message msg, int base_offset, int data_length) {
        super(msg, base_offset, data_length);
        amTypeSet(AM_TYPE);
    }

    /**
    /* Return a String representation of this message. Includes the
     * message type name and the non-indexed field values.
     */
    public String toString() {
      String s = "Message <BaseStationMsg> \n";
      try {
        s += "  [typeCode=0x"+Long.toHexString(get_typeCode())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [frequency=0x"+Long.toHexString(get_frequency())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      try {
        s += "  [startTime=0x"+Long.toHexString(get_startTime())+"]\n";
      } catch (ArrayIndexOutOfBoundsException aioobe) { /* Skip field */ }
      return s;
    }

    // Message-type-specific access methods appear below.

    /////////////////////////////////////////////////////////
    // Accessor methods for field: typeCode
    //   Field type: short, unsigned
    //   Offset (bits): 0
    //   Size (bits): 8
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'typeCode' is signed (false).
     */
    public static boolean isSigned_typeCode() {
        return false;
    }

    /**
     * Return whether the field 'typeCode' is an array (false).
     */
    public static boolean isArray_typeCode() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'typeCode'
     */
    public static int offset_typeCode() {
        return (0 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'typeCode'
     */
    public static int offsetBits_typeCode() {
        return 0;
    }

    /**
     * Return the value (as a short) of the field 'typeCode'
     */
    public short get_typeCode() {
        return (short)getUIntBEElement(offsetBits_typeCode(), 8);
    }

    /**
     * Set the value of the field 'typeCode'
     */
    public void set_typeCode(short value) {
        setUIntBEElement(offsetBits_typeCode(), 8, value);
    }

    /**
     * Return the size, in bytes, of the field 'typeCode'
     */
    public static int size_typeCode() {
        return (8 / 8);
    }

    /**
     * Return the size, in bits, of the field 'typeCode'
     */
    public static int sizeBits_typeCode() {
        return 8;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: frequency
    //   Field type: int, unsigned
    //   Offset (bits): 8
    //   Size (bits): 16
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'frequency' is signed (false).
     */
    public static boolean isSigned_frequency() {
        return false;
    }

    /**
     * Return whether the field 'frequency' is an array (false).
     */
    public static boolean isArray_frequency() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'frequency'
     */
    public static int offset_frequency() {
        return (8 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'frequency'
     */
    public static int offsetBits_frequency() {
        return 8;
    }

    /**
     * Return the value (as a int) of the field 'frequency'
     */
    public int get_frequency() {
        return (int)getUIntBEElement(offsetBits_frequency(), 16);
    }

    /**
     * Set the value of the field 'frequency'
     */
    public void set_frequency(int value) {
        setUIntBEElement(offsetBits_frequency(), 16, value);
    }

    /**
     * Return the size, in bytes, of the field 'frequency'
     */
    public static int size_frequency() {
        return (16 / 8);
    }

    /**
     * Return the size, in bits, of the field 'frequency'
     */
    public static int sizeBits_frequency() {
        return 16;
    }

    /////////////////////////////////////////////////////////
    // Accessor methods for field: startTime
    //   Field type: long, unsigned
    //   Offset (bits): 24
    //   Size (bits): 32
    /////////////////////////////////////////////////////////

    /**
     * Return whether the field 'startTime' is signed (false).
     */
    public static boolean isSigned_startTime() {
        return false;
    }

    /**
     * Return whether the field 'startTime' is an array (false).
     */
    public static boolean isArray_startTime() {
        return false;
    }

    /**
     * Return the offset (in bytes) of the field 'startTime'
     */
    public static int offset_startTime() {
        return (24 / 8);
    }

    /**
     * Return the offset (in bits) of the field 'startTime'
     */
    public static int offsetBits_startTime() {
        return 24;
    }

    /**
     * Return the value (as a long) of the field 'startTime'
     */
    public long get_startTime() {
        return (long)getUIntBEElement(offsetBits_startTime(), 32);
    }

    /**
     * Set the value of the field 'startTime'
     */
    public void set_startTime(long value) {
        setUIntBEElement(offsetBits_startTime(), 32, value);
    }

    /**
     * Return the size, in bytes, of the field 'startTime'
     */
    public static int size_startTime() {
        return (32 / 8);
    }

    /**
     * Return the size, in bits, of the field 'startTime'
     */
    public static int sizeBits_startTime() {
        return 32;
    }

}
