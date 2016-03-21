// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

// Referenced classes of package com.jagex:
//			n

public class Util {
  public static InputStream openStream(String arg0) throws IOException {
    Object obj;
    if (ohb == null) {
      obj = new FileInputStream(arg0);
    } else {
      URL url = new URL(ohb, arg0);
      obj = url.openStream();
    }
    return ((InputStream) (obj));
  }

  public static void readFully(String arg0, byte arg1[], int arg2) throws IOException {
    InputStream inputstream = openStream(arg0);
    DataInputStream datainputstream = new DataInputStream(inputstream);
    try {
      datainputstream.readFully(arg1, 0, arg2);
    } catch (EOFException _ex) {
    }
    datainputstream.close();
  }

  public static void putInt24(byte buffer[], int position, int val) {
    buffer[position] = (byte) (val >> 24);
    buffer[position + 1] = (byte) (val >> 16);
    buffer[position + 2] = (byte) (val >> 8);
    buffer[position + 3] = (byte) val;
  }

  public static void putInt64(byte buffer[], int position, long val) {
    buffer[position] = (byte) (int) (val >> 56);
    buffer[position + 1] = (byte) (int) (val >> 48);
    buffer[position + 2] = (byte) (int) (val >> 40);
    buffer[position + 3] = (byte) (int) (val >> 32);
    buffer[position + 4] = (byte) (int) (val >> 24);
    buffer[position + 5] = (byte) (int) (val >> 16);
    buffer[position + 6] = (byte) (int) (val >> 8);
    buffer[position + 7] = (byte) (int) val;
  }

  public static int unsign(byte val) {
    return val & 0xff;
  }

  public static int readInt16(byte buffer[], int position) {
    return ((buffer[position] & 0xff) << 8) + (buffer[position + 1] & 0xff);
  }

  public static int readInt32(byte buffer[], int position) {
    return ((buffer[position] & 0xff) << 24) + ((buffer[position + 1] & 0xff) << 16)
        + ((buffer[position + 2] & 0xff) << 8) + (buffer[position + 3] & 0xff);
  }

  public static long readInt64(byte buffer[], int position) {
    return (((long) readInt32(buffer, position) & 0xffffffffL) << 32)
        + ((long) readInt32(buffer, position + 4) & 0xffffffffL);
  }

  public static int readSignedInt16(byte buffer[], int position) {
    int i = unsign(buffer[position]) * 256 + unsign(buffer[position + 1]);
    if (i > 32767)
      i -= 0x10000;
    return i;
  }

  public static int readInt32A(byte arg0[], int arg1) {
    if ((arg0[arg1] & 0xff) < 128)
      return arg0[arg1];
    else
      return ((arg0[arg1] & 0xff) - 128 << 24) + ((arg0[arg1 + 1] & 0xff) << 16) + ((arg0[arg1 + 2] & 0xff) << 8)
          + (arg0[arg1 + 3] & 0xff);
  }

  public static int readBits(byte buf[], int bitPosition, int arg2) {
    int i = bitPosition >> 3;
    int j = 8 - (bitPosition & 7);
    int k = 0;
    for (; arg2 > j; j = 8) {
      k += (buf[i++] & bitMask[j]) << arg2 - j;
      arg2 -= j;
    }

    if (arg2 == j)
      k += buf[i] & bitMask[j];
    else
      k += buf[i] >> j - arg2 & bitMask[arg2];
    return k;
  }

  public static String formatString(String arg0, int arg1) {
    String s = "";
    for (int i = 0; i < arg1; i++)
      if (i >= arg0.length()) {
        s = s + " ";
      } else {
        char c = arg0.charAt(i);
        if (c >= 'a' && c <= 'z')
          s = s + c;
        else if (c >= 'A' && c <= 'Z')
          s = s + c;
        else if (c >= '0' && c <= '9')
          s = s + c;
        else
          s = s + '_';
      }

    return s;
  }

  public static String inetNumberToASCII(int ip) {
    return (ip >> 24 & 0xff) + "." + (ip >> 16 & 0xff) + "." + (ip >> 8 & 0xff) + "." + (ip & 0xff);
  }

  public static long encode47(String arg0) {
    arg0 = arg0.trim();
    arg0 = arg0.toLowerCase();
    long l = 0L;
    int i = 0;
    for (int j = 0; j < arg0.length(); j++) {
      char c = arg0.charAt(j);
      if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9') {
        char c1 = c;
        l = l * 47L * (l - (long) (c1 * 6) - (long) (i * 7));
        l += (c1 - 32) + i * c1;
        i++;
      }
    }

    return l;
  }

  public static long encode37(String arg0) {
    String s = "";
    for (int i = 0; i < arg0.length(); i++) {
      char c = arg0.charAt(i);
      if (c >= 'a' && c <= 'z')
        s = s + c;
      else if (c >= 'A' && c <= 'Z')
        s = s + (char) ((c + 97) - 65);
      else if (c >= '0' && c <= '9')
        s = s + c;
      else
        s = s + ' ';
    }

    s = s.trim();
    if (s.length() > 12)
      s = s.substring(0, 12);
    long l = 0L;
    for (int j = 0; j < s.length(); j++) {
      char c1 = s.charAt(j);
      l *= 37L;
      if (c1 >= 'a' && c1 <= 'z')
        l += (1 + c1) - 97;
      else if (c1 >= '0' && c1 <= '9')
        l += (27 + c1) - 48;
    }

    return l;
  }

  public static String decode37(long arg0) {
    if (arg0 < 0L)
      return "invalid_name";
    String s = "";
    while (arg0 != 0L) {
      int i = (int) (arg0 % 37L);
      arg0 /= 37L;
      if (i == 0)
        s = " " + s;
      else if (i < 27) {
        if (arg0 % 37L == 0L)
          s = (char) ((i + 65) - 1) + s;
        else
          s = (char) ((i + 97) - 1) + s;
      } else {
        s = (char) ((i + 48) - 27) + s;
      }
    }
    return s;
  }

  public static byte[] mn(String arg0) throws IOException {
    int i = 0;
    int j = 0;
    int k = 0;
    byte abyte0[] = null;
    while (i < 2)
      try {
        if (i == 1)
          arg0 = arg0.toUpperCase();
        InputStream inputstream = openStream(arg0);
        DataInputStream datainputstream = new DataInputStream(inputstream);
        byte abyte2[] = new byte[6];
        datainputstream.readFully(abyte2, 0, 6);
        j = ((abyte2[0] & 0xff) << 16) + ((abyte2[1] & 0xff) << 8) + (abyte2[2] & 0xff);
        k = ((abyte2[3] & 0xff) << 16) + ((abyte2[4] & 0xff) << 8) + (abyte2[5] & 0xff);
        int l = 0;
        abyte0 = new byte[k];
        int i1;
        for (; l < k; l += i1) {
          i1 = k - l;
          if (i1 > 1000)
            i1 = 1000;
          datainputstream.readFully(abyte0, l, i1);
        }

        i = 2;
        datainputstream.close();
      } catch (IOException _ex) {
        i++;
      }
    if (k != j) {
      byte abyte1[] = new byte[j];
      BZipDecompressor.xj(abyte1, j, abyte0, k, 0);
      return abyte1;
    } else {
      return abyte0;
    }
  }

  public static int getFileIndex(String arg0, byte arg1[]) {
    int i = readInt16(arg1, 0);
    int j = 0;
    arg0 = arg0.toUpperCase();
    for (int k = 0; k < arg0.length(); k++)
      j = (j * 61 + arg0.charAt(k)) - 32;

    int l = 2 + i * 10;
    for (int i1 = 0; i1 < i; i1++) {
      int j1 = (arg1[i1 * 10 + 2] & 0xff) * 0x1000000 + (arg1[i1 * 10 + 3] & 0xff) * 0x10000
          + (arg1[i1 * 10 + 4] & 0xff) * 256 + (arg1[i1 * 10 + 5] & 0xff);
      int k1 = (arg1[i1 * 10 + 9] & 0xff) * 0x10000 + (arg1[i1 * 10 + 10] & 0xff) * 256 + (arg1[i1 * 10 + 11] & 0xff);
      if (j1 == j)
        return l;
      l += k1;
    }

    return 0;
  }

  public static int getFilePosition(String file, byte data[]) {
    int i = readInt16(data, 0);
    int j = 0;
    file = file.toUpperCase();
    for (int k = 0; k < file.length(); k++)
      j = (j * 61 + file.charAt(k)) - 32;

    for (int i1 = 0; i1 < i; i1++) {
      int j1 = (data[i1 * 10 + 2] & 0xff) * 0x1000000 + (data[i1 * 10 + 3] & 0xff) * 0x10000
          + (data[i1 * 10 + 4] & 0xff) * 256 + (data[i1 * 10 + 5] & 0xff);
      int k1 = (data[i1 * 10 + 6] & 0xff) * 0x10000 + (data[i1 * 10 + 7] & 0xff) * 256 + (data[i1 * 10 + 8] & 0xff);
      if (j1 == j)
        return k1;
    }

    return 0;
  }

  public static byte[] in(String arg0, int arg1, byte arg2[]) {
    return co(arg0, arg1, arg2, null);
  }

  public static byte[] co(String arg0, int arg1, byte arg2[], byte arg3[]) {
    int i = (arg2[0] & 0xff) * 256 + (arg2[1] & 0xff);
    int j = 0;
    arg0 = arg0.toUpperCase();
    for (int k = 0; k < arg0.length(); k++)
      j = (j * 61 + arg0.charAt(k)) - 32;

    int l = 2 + i * 10;
    for (int i1 = 0; i1 < i; i1++) {
      int j1 = (arg2[i1 * 10 + 2] & 0xff) * 0x1000000 + (arg2[i1 * 10 + 3] & 0xff) * 0x10000
          + (arg2[i1 * 10 + 4] & 0xff) * 256 + (arg2[i1 * 10 + 5] & 0xff);
      int k1 = (arg2[i1 * 10 + 6] & 0xff) * 0x10000 + (arg2[i1 * 10 + 7] & 0xff) * 256 + (arg2[i1 * 10 + 8] & 0xff);
      int l1 = (arg2[i1 * 10 + 9] & 0xff) * 0x10000 + (arg2[i1 * 10 + 10] & 0xff) * 256 + (arg2[i1 * 10 + 11] & 0xff);
      if (j1 == j) {
        if (arg3 == null)
          arg3 = new byte[k1 + arg1];
        if (k1 != l1) {
          BZipDecompressor.xj(arg3, k1, arg2, l1, l);
        } else {
          for (int i2 = 0; i2 < k1; i2++)
            arg3[i2] = arg2[l + i2];

        }
        return arg3;
      }
      l += l1;
    }

    return null;
  }

  public static int encodeMessage(String arg0) {
    int i = 0;
    try {
      if (arg0.length() > 80)
        arg0 = arg0.substring(0, 80);
      arg0 = arg0.toLowerCase() + " ";
      if (arg0.startsWith("@red@")) {
        uhb[i++] = -1;
        uhb[i++] = 0;
        arg0 = arg0.substring(5);
      }
      if (arg0.startsWith("@gre@")) {
        uhb[i++] = -1;
        uhb[i++] = 1;
        arg0 = arg0.substring(5);
      }
      if (arg0.startsWith("@blu@")) {
        uhb[i++] = -1;
        uhb[i++] = 2;
        arg0 = arg0.substring(5);
      }
      if (arg0.startsWith("@cya@")) {
        uhb[i++] = -1;
        uhb[i++] = 3;
        arg0 = arg0.substring(5);
      }
      if (arg0.startsWith("@ran@")) {
        uhb[i++] = -1;
        uhb[i++] = 4;
        arg0 = arg0.substring(5);
      }
      if (arg0.startsWith("@whi@")) {
        uhb[i++] = -1;
        uhb[i++] = 5;
        arg0 = arg0.substring(5);
      }
      if (arg0.startsWith("@bla@")) {
        uhb[i++] = -1;
        uhb[i++] = 6;
        arg0 = arg0.substring(5);
      }
      if (arg0.startsWith("@ora@")) {
        uhb[i++] = -1;
        uhb[i++] = 7;
        arg0 = arg0.substring(5);
      }
      if (arg0.startsWith("@yel@")) {
        uhb[i++] = -1;
        uhb[i++] = 8;
        arg0 = arg0.substring(5);
      }
      if (arg0.startsWith("@mag@")) {
        uhb[i++] = -1;
        uhb[i++] = 9;
        arg0 = arg0.substring(5);
      }
      String s = "";
      for (int j = 0; j < arg0.length(); j++) {
        char c = arg0.charAt(j);
        if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '\'') {
          s = s + c;
        } else {
          int k = xn(c);
          if (s.length() > 0) {
            for (int l = 0; l < shb; l++) {
              if (!s.equals(thb[l]))
                continue;
              if (k == 36 && l < 165) {
                uhb[i++] = (byte) (l + 90);
                k = -1;
              } else if (k == 36) {
                uhb[i++] = (byte) (l / 256 + 50);
                uhb[i++] = (byte) (l & 0xff);
                k = -1;
              } else {
                uhb[i++] = (byte) (l / 256 + 70);
                uhb[i++] = (byte) (l & 0xff);
              }
              s = "";
              break;
            }

          }
          for (int i1 = 0; i1 < s.length(); i1++)
            uhb[i++] = (byte) xn(s.charAt(i1));

          s = "";
          if (k != -1 && j < arg0.length() - 1)
            uhb[i++] = (byte) k;
        }
      }

    } catch (Exception _ex) {
    }
    return i;
  }

  private static int xn(char arg0) {
    if (arg0 >= 'a' && arg0 <= 'z')
      return arg0 - 97;
    if (arg0 >= '0' && arg0 <= '9')
      return (arg0 + 26) - 48;
    if (arg0 == ' ')
      return 36;
    if (arg0 == '!')
      return 37;
    if (arg0 == '?')
      return 38;
    if (arg0 == '.')
      return 39;
    if (arg0 == ',')
      return 40;
    if (arg0 == ':')
      return 41;
    if (arg0 == ';')
      return 42;
    if (arg0 == '(')
      return 43;
    if (arg0 == ')')
      return 44;
    if (arg0 == '-')
      return 45;
    if (arg0 == '&')
      return 46;
    if (arg0 == '*')
      return 47;
    if (arg0 == '\\')
      return 48;
    return arg0 != '\'' ? 36 : 49;
  }

  public static String encodeCensor(byte arg0[], int arg1, int arg2, boolean arg3) {
    try {
      String s = "";
      String s1 = "";
      for (int i = arg1; i < arg1 + arg2; i++) {
        int j = arg0[i] & 0xff;
        if (j < 50)
          s = s + whb[j];
        else if (j < 70) {
          i++;
          s = s + thb[(j - 50) * 256 + (arg0[i] & 0xff)] + " ";
        } else if (j < 90) {
          i++;
          s = s + thb[(j - 70) * 256 + (arg0[i] & 0xff)];
        } else if (j < 255) {
          s = s + thb[j - 90] + " ";
        } else {
          i++;
          int k = arg0[i] & 0xff;
          if (k == 0)
            s1 = "@red@";
          if (k == 1)
            s1 = "@gre@";
          if (k == 2)
            s1 = "@blu@";
          if (k == 3)
            s1 = "@cya@";
          if (k == 4)
            s1 = "@ran@";
          if (k == 5)
            s1 = "@whi@";
          if (k == 6)
            s1 = "@bla@";
          if (k == 7)
            s1 = "@ora@";
          if (k == 8)
            s1 = "@yel@";
          if (k == 9)
            s1 = "@mag@";
        }
      }

      if (arg3) {
        for (int l = 0; l < 2; l++) {
          String s3 = s;
          s = hn(s);
          if (s.equals(s3))
            break;
        }

      }
      if (s.length() > 80)
        s = s.substring(0, 80);
      s = s.toLowerCase();
      String s2 = s1;
      boolean flag = true;
      for (int i1 = 0; i1 < s.length(); i1++) {
        char c = s.charAt(i1);
        if (c >= 'a' && c <= 'z' && flag) {
          flag = false;
          c = (char) ((c + 65) - 97);
        }
        if (c == '.' || c == '!' || c == '?')
          flag = true;
        s2 = s2 + c;
      }

      return s2;
    } catch (Exception _ex) {
      return "eep!";
    }
  }

  private static String hn(String arg0) {
    try {
      int i = arg0.length();
      arg0.toLowerCase().getChars(0, i, vhb, 0);
      for (int j = 0; j < i; j++) {
        char c = vhb[j];
        for (int k = 0; k < qhb; k++) {
          String s = rhb[k];
          char c1 = s.charAt(0);
          if (dn(c1, c, 0)) {
            int l = 1;
            int i1 = s.length();
            char c2 = s.charAt(1);
            int j1 = 0;
            if (i1 >= 6)
              j1 = 1;
            for (int k1 = j + 1; k1 < i; k1++) {
              char c3 = vhb[k1];
              if (dn(c2, c3, i1)) {
                if (++l >= i1) {
                  boolean flag = false;
                  for (int l1 = j; l1 <= k1; l1++) {
                    if (arg0.charAt(l1) < 'A' || arg0.charAt(l1) > 'Z')
                      continue;
                    flag = true;
                    break;
                  }

                  if (flag) {
                    String s1 = "";
                    for (int i2 = 0; i2 < arg0.length(); i2++) {
                      char c4 = arg0.charAt(i2);
                      if (i2 >= j && i2 <= k1 && c4 != ' ' && (c4 < 'a' || c4 > 'z'))
                        s1 = s1 + "*";
                      else
                        s1 = s1 + c4;
                    }

                    arg0 = s1;
                  }
                  break;
                }
                c1 = c2;
                c2 = s.charAt(l);
                continue;
              }
              if (!qn(c1, c3, i1) && --j1 < 0)
                break;
            }

          }
        }

      }

      return arg0;
    } catch (Exception _ex) {
      return "wibble!";
    }
  }

  private static boolean dn(char arg0, char arg1, int arg2) {
    if (arg0 == arg1)
      return true;
    if (arg0 == 'i' && (arg1 == 'y' || arg1 == '1' || arg1 == '!' || arg1 == ':' || arg1 == ';'))
      return true;
    if (arg0 == 's' && (arg1 == '5' || arg1 == 'z'))
      return true;
    if (arg0 == 'e' && arg1 == '3')
      return true;
    if (arg0 == 'a' && arg1 == '4')
      return true;
    if (arg0 == 'o' && (arg1 == '0' || arg1 == '*'))
      return true;
    if (arg0 == 'u' && arg1 == 'v')
      return true;
    if (arg0 == 'c' && (arg1 == '(' || arg1 == 'k'))
      return true;
    if (arg0 == 'k' && (arg1 == '(' || arg1 == 'c'))
      return true;
    if (arg0 == 'w' && arg1 == 'v')
      return true;
    return arg2 >= 4 && arg0 == 'i' && arg1 == 'l';
  }

  private static boolean qn(char arg0, char arg1, int arg2) {
    if (arg0 == arg1)
      return true;
    if (arg1 < 'a' || arg1 > 'u' && arg1 != 'y')
      return true;
    if (arg0 == 'i' && arg1 == 'y')
      return true;
    if (arg0 == 'c' && arg1 == 'k')
      return true;
    if (arg0 == 'k' && arg1 == 'c')
      return true;
    return arg2 >= 5 && (arg0 == 'a' || arg0 == 'e' || arg0 == 'i' || arg0 == 'o' || arg0 == 'u' || arg0 == 'y')
        && (arg1 == 'a' || arg1 == 'e' || arg1 == 'i' || arg1 == 'o' || arg1 == 'u' || arg1 == 'y');
  }

  public Util() {
  }

  public static URL ohb = null;
  private static int bitMask[] = { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535,
      0x1ffff, 0x3ffff, 0x7ffff, 0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff,
      0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff, -1 };
  public static int qhb = 1;
  public static String rhb[] = { "bum" };
  public static int shb = 1;
  public static String thb[] = { "hello" };
  public static byte uhb[] = new byte[200];
  static char vhb[] = new char[1000];
  private static char whb[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
      'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '!', '?',
      '.', ',', ':', ';', '(', ')', '-', '&', '*', '\\', '\'' };
}