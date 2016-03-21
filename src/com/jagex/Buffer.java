// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex;

import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package com.jagex:
//			o

public class Buffer {
  public void close() {
  }

  public int read() throws IOException {
    return 0;
  }

  public int available() throws IOException {
    return 0;
  }

  public void writeTo(int i, int j, byte abyte0[]) throws IOException {
  }

  public void readFrom(byte abyte0[], int i, int j) throws IOException {
  }

  public int zb(byte arg0[], int arg1, int arg2) throws IOException {
    return 0;
  }

  public int readInt8() throws IOException {
    return read();
  }

  public int readInt16() throws IOException {
    int i = readInt8();
    int j = readInt8();
    return i * 256 + j;
  }

  public int readInt32() throws IOException {
    int i = readInt16();
    int j = readInt16();
    return i * 0x10000 + j;
  }

  public void copyBuffer(int arg0, byte arg1[]) throws IOException {
    writeTo(arg0, 0, arg1);
  }

  public int decodeFrame(byte payload[]) {
    try {
      ne++;
      if (timeout > 0 && ne > timeout) {
        wd = true;
        xd = "time-out";
        timeout += timeout;
        return 0;
      }
      if (length == 0 && available() >= 2) {
        length = read();
        if (length >= 160)
          length = (length - 160) * 256 + read();
      }
      if (length > 0 && available() >= length) {
        if (length >= 160) {
          copyBuffer(length, payload);
        } else {
          payload[length - 1] = (byte) read();
          if (length > 1)
            copyBuffer(length - 1, payload);
        }
        int i = length;
        length = 0;
        ne = 0;
        return i;
      }
    } catch (IOException ioexception) {
      wd = true;
      xd = ioexception.getMessage();
    }
    return 0;
  }

  public void putInt8(int val) {
    buffer[position++] = (byte) val;
  }

  public void putInt16(int val) {
    buffer[position++] = (byte) (val >> 8);
    buffer[position++] = (byte) val;
  }

  public void nb(int val, int offset) {
    buffer[pe + offset] = (byte) (val >> 8);
    buffer[pe + offset + 1] = (byte) val;
  }

  public void putInt32(int val) {
    buffer[position++] = (byte) (val >> 24);
    buffer[position++] = (byte) (val >> 16);
    buffer[position++] = (byte) (val >> 8);
    buffer[position++] = (byte) val;
  }

  public void putInt64(long val) {
    putInt32((int) (val >> 32));
    putInt32((int) (val & -1L));
  }

  @SuppressWarnings("deprecation")
  public void putLine(String line) {
    line.getBytes(0, line.length(), buffer, position);
    position += line.length();
  }

  public void readBytes(byte src[], int arg1, int arg2) {
    for (int i = 0; i < arg2; i++)
      buffer[position++] = src[arg1 + i];
  }

  public void putInt64RSA(long arg0, int arg1, BigInteger arg2, BigInteger arg3) {
    byte abyte0[] = new byte[15];
    abyte0[0] = (byte) (int) (1.0D + Math.random() * 127D);
    abyte0[1] = (byte) (int) (Math.random() * 256D);
    abyte0[2] = (byte) (int) (Math.random() * 256D);
    Util.putInt24(abyte0, 3, arg1);
    Util.putInt64(abyte0, 7, arg0);
    BigInteger biginteger = new BigInteger(1, abyte0);
    BigInteger biginteger1 = biginteger.modPow(arg2, arg3);
    byte abyte1[] = biginteger1.toByteArray();
    buffer[position++] = (byte) abyte1.length;
    for (int i = 0; i < abyte1.length; i++)
      buffer[position++] = abyte1[i];
  }

  public void putLineRSA(String arg0, int arg1, BigInteger arg2, BigInteger arg3) {
    byte abyte0[] = arg0.getBytes();
    int i = abyte0.length;
    byte abyte1[] = new byte[15];
    for (int j = 0; j < i; j += 7) {
      abyte1[0] = (byte) (int) (1.0D + Math.random() * 127D);
      abyte1[1] = (byte) (int) (Math.random() * 256D);
      abyte1[2] = (byte) (int) (Math.random() * 256D);
      abyte1[3] = (byte) (int) (Math.random() * 256D);
      Util.putInt24(abyte1, 4, arg1);
      for (int k = 0; k < 7; k++)
        if (j + k < i)
          abyte1[8 + k] = abyte0[j + k];
        else
          abyte1[8 + k] = 32;

      BigInteger biginteger = new BigInteger(1, abyte1);
      BigInteger biginteger1 = biginteger.modPow(arg2, arg3);
      byte abyte2[] = biginteger1.toByteArray();
      buffer[position++] = (byte) abyte2.length;
      for (int l = 0; l < abyte2.length; l++)
        buffer[position++] = abyte2[l];
    }
  }

  public void beginFrame(int opcode) {
    if (pe > (vd * 4) / 5)
      try {
        writeFrame(0);
      } catch (IOException ioexception) {
        wd = true;
        xd = ioexception.getMessage();
      }
    if (buffer == null)
      buffer = new byte[vd];
    buffer[pe + 2] = (byte) opcode;
    buffer[pe + 3] = 0;
    position = pe + 3;
    re = 8;
  }

  public void encodeFrame() {
    if (re != 8)
      position++;
    int i = position - pe - 2;
    if (i >= 160) {
      buffer[pe] = (byte) (160 + i / 256);
      buffer[pe + 1] = (byte) (i & 0xff);
    } else {
      buffer[pe] = (byte) i;
      position--;
      buffer[pe + 1] = buffer[position];
    }
    if (vd <= 10000) {
      int j = buffer[pe + 2] & 0xff;
      td[j]++;
      ud[j] += position - pe;
    }
    pe = position;
  }

  public void flush() throws IOException {
    encodeFrame();
    writeFrame(0);
  }

  public void writeFrame(int i) throws IOException {
    if (wd) {
      pe = 0;
      position = 3;
      wd = false;
      throw new IOException(xd);
    }
    yd++;
    if (yd < i)
      return;
    if (pe > 0) {
      yd = 0;
      readFrom(buffer, 0, pe);
    }
    pe = 0;
    position = 3;
  }

  public boolean ob() {
    return pe > 0;
  }

  public Buffer() {
    vd = 5000;
    wd = false;
    xd = "";
    position = 3;
    re = 8;
  }

  public static int td[] = new int[256];
  public static int ud[] = new int[256];
  protected int vd;
  protected boolean wd;
  protected String xd;
  protected int yd;
  final int zd = 61;
  final int ae = 59;
  final int be = 42;
  final int ce = 43;
  final int de = 44;
  final int ee = 45;
  final int fe = 46;
  final int ge = 47;
  final int he = 92;
  final int ie = 32;
  final int je = 124;
  final int ke = 34;
  static char le[];
  protected int length;
  public int ne;
  public int timeout;
  private int pe;
  private int position;
  private int re;
  private byte buffer[];

  static {
    le = new char[256];
    for (int i = 0; i < 256; i++)
      le[i] = (char) i;

    le[61] = '=';
    le[59] = ';';
    le[42] = '*';
    le[43] = '+';
    le[44] = ',';
    le[45] = '-';
    le[46] = '.';
    le[47] = '/';
    le[92] = '\\';
    le[124] = '|';
    le[33] = '!';
    le[34] = '"';
  }
}