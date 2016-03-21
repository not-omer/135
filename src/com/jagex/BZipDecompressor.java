// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex;

// Referenced classes of package com.jagex:
//			b

public class BZipDecompressor {
  public static int xj(byte arg0[], int arg1, byte arg2[], int arg3, int arg4) {
    BZipState b1 = new BZipState();
    b1.t = arg2;
    b1.u = arg4;
    b1.y = arg0;
    b1.z = 0;
    b1.v = arg3;
    b1.ab = arg1;
    b1.hb = 0;
    b1.gb = 0;
    b1.w = 0;
    b1.x = 0;
    b1.bb = 0;
    b1.cb = 0;
    b1.jb = 0;
    yj(b1);
    arg1 -= b1.ab;
    return arg1;
  }

  private static void zj(BZipState block) {
    byte byte4 = block.db;
    int i = block.eb;
    int j = block.ob;
    int k = block.mb;
    int ai[] = BZipState.rb;
    int l = block.lb;
    byte abyte0[] = block.y;
    int i1 = block.z;
    int j1 = block.ab;
    int k1 = j1;
    int l1 = block.fc + 1;
    label0: do {
      if (i > 0) {
        do {
          if (j1 == 0)
            break label0;
          if (i == 1)
            break;
          abyte0[i1] = byte4;
          i--;
          i1++;
          j1--;
        } while (true);
        if (j1 == 0) {
          i = 1;
          break;
        }
        abyte0[i1] = byte4;
        i1++;
        j1--;
      }
      boolean flag = true;
      while (flag) {
        flag = false;
        if (j == l1) {
          i = 0;
          break label0;
        }
        byte4 = (byte) k;
        l = ai[l];
        byte byte0 = (byte) (l & 0xff);
        l >>= 8;
        j++;
        if (byte0 != k) {
          k = byte0;
          if (j1 == 0) {
            i = 1;
          } else {
            abyte0[i1] = byte4;
            i1++;
            j1--;
            flag = true;
            continue;
          }
          break label0;
        }
        if (j != l1)
          continue;
        if (j1 == 0) {
          i = 1;
          break label0;
        }
        abyte0[i1] = byte4;
        i1++;
        j1--;
        flag = true;
      }
      i = 2;
      l = ai[l];
      byte byte1 = (byte) (l & 0xff);
      l >>= 8;
      if (++j != l1)
        if (byte1 != k) {
          k = byte1;
        } else {
          i = 3;
          l = ai[l];
          byte byte2 = (byte) (l & 0xff);
          l >>= 8;
          if (++j != l1)
            if (byte2 != k) {
              k = byte2;
            } else {
              l = ai[l];
              byte byte3 = (byte) (l & 0xff);
              l >>= 8;
              j++;
              i = (byte3 & 0xff) + 4;
              l = ai[l];
              k = (byte) (l & 0xff);
              l >>= 8;
              j++;
            }
        }
    } while (true);
    int i2 = block.bb;
    block.bb += k1 - j1;
    if (block.bb < i2)
      block.cb++;
    block.db = byte4;
    block.eb = i;
    block.ob = j;
    block.mb = k;
    BZipState.rb = ai;
    block.lb = l;
    block.y = abyte0;
    block.z = i1;
    block.ab = j1;
  }

  private static void yj(BZipState block) {
    int k8 = 0;
    int ai[] = null;
    int ai1[] = null;
    int ai2[] = null;
    block.ib = 1;
    if (BZipState.rb == null)
      BZipState.rb = new int[block.ib * 0x186a0];
    boolean flag19 = true;
    while (flag19) {
      byte head = readInt8(block);
      if (head == 23)
        return;
      head = readInt8(block);
      head = readInt8(block);
      head = readInt8(block);
      head = readInt8(block);
      head = readInt8(block);
      block.jb++;
      head = readInt8(block);
      head = readInt8(block);
      head = readInt8(block);
      head = readInt8(block);
      head = bk(block);
      if (head != 0)
        block.blockRandomized = true;
      else
        block.blockRandomized = false;
      if (block.blockRandomized)
        System.out.println("PANIC! RANDOMISED BLOCK!");
      block.kb = 0;
      head = readInt8(block);
      block.kb = block.kb << 8 | head & 0xff;
      head = readInt8(block);
      block.kb = block.kb << 8 | head & 0xff;
      head = readInt8(block);
      block.kb = block.kb << 8 | head & 0xff;
      for (int j = 0; j < 16; j++) {
        byte byte1 = bk(block);
        if (byte1 == 1)
          block.ub[j] = true;
        else
          block.ub[j] = false;
      }

      for (int k = 0; k < 256; k++)
        block.tb[k] = false;

      for (int l = 0; l < 16; l++)
        if (block.ub[l]) {
          for (int i3 = 0; i3 < 16; i3++) {
            byte byte2 = bk(block);
            if (byte2 == 1)
              block.tb[l * 16 + i3] = true;
          }
        }

      constructMappingTables(block);
      int size = block.sb + 2;
      int groups = ck(3, block);
      int selectors = ck(15, block);
      for (int i = 0; i < selectors; i++) {
        int count = 0;
        do {
          byte terminator = bk(block);
          if (terminator == 0)
            break;
          count++;
        } while (true);
        block.mtfTable[i] = (byte) count;
      }

      byte abyte0[] = new byte[6];
      for (byte byte16 = 0; byte16 < groups; byte16++)
        abyte0[byte16] = byte16;

      for (int j1 = 0; j1 < selectors; j1++) {
        byte byte17 = block.mtfTable[j1];
        byte byte15 = abyte0[byte17];
        for (; byte17 > 0; byte17--)
          abyte0[byte17] = abyte0[byte17 - 1];

        abyte0[0] = byte15;
        block.yb[j1] = byte15;
      }

      for (int k3 = 0; k3 < groups; k3++) {
        int l6 = ck(5, block);
        for (int k1 = 0; k1 < size; k1++) {
          do {
            byte byte4 = bk(block);
            if (byte4 == 0)
              break;
            byte4 = bk(block);
            if (byte4 == 0)
              l6++;
            else
              l6--;
          } while (true);
          block.ac[k3][k1] = (byte) l6;
        }

      }

      for (int l3 = 0; l3 < groups; l3++) {
        byte byte8 = 32;
        int i = 0;
        for (int l1 = 0; l1 < size; l1++) {
          if (block.ac[l3][l1] > i)
            i = block.ac[l3][l1];
          if (block.ac[l3][l1] < byte8)
            byte8 = block.ac[l3][l1];
        }

        dk(block.bc[l3], block.cc[l3], block.dc[l3], block.ac[l3], byte8, i, size);
        block.ec[l3] = byte8;
      }

      int l4 = block.sb + 1;
      int i5 = -1;
      int j5 = 0;
      for (int i2 = 0; i2 <= 255; i2++)
        block.nb[i2] = 0;

      int j9 = 4095;
      for (int l8 = 15; l8 >= 0; l8--) {
        for (int i9 = 15; i9 >= 0; i9--) {
          block.wb[j9] = (byte) (l8 * 16 + i9);
          j9--;
        }

        block.xb[l8] = j9 + 1;
      }

      int i6 = 0;
      if (j5 == 0) {
        i5++;
        j5 = 50;
        byte byte12 = block.yb[i5];
        k8 = block.ec[byte12];
        ai = block.bc[byte12];
        ai2 = block.dc[byte12];
        ai1 = block.cc[byte12];
      }
      j5--;
      int i7 = k8;
      int l7;
      byte byte9;
      for (l7 = ck(i7, block); l7 > ai[i7]; l7 = l7 << 1 | byte9) {
        i7++;
        byte9 = bk(block);
      }

      for (int k5 = ai2[l7 - ai1[i7]]; k5 != l4;)
        if (k5 == 0 || k5 == 1) {
          int j6 = -1;
          int k6 = 1;
          do {
            if (k5 == 0)
              j6 += k6;
            else if (k5 == 1)
              j6 += 2 * k6;
            k6 *= 2;
            if (j5 == 0) {
              i5++;
              j5 = 50;
              byte byte13 = block.yb[i5];
              k8 = block.ec[byte13];
              ai = block.bc[byte13];
              ai2 = block.dc[byte13];
              ai1 = block.cc[byte13];
            }
            j5--;
            int j7 = k8;
            int i8;
            byte byte10;
            for (i8 = ck(j7, block); i8 > ai[j7]; i8 = i8 << 1 | byte10) {
              j7++;
              byte10 = bk(block);
            }

            k5 = ai2[i8 - ai1[j7]];
          } while (k5 == 0 || k5 == 1);
          j6++;
          byte byte5 = block.vb[block.wb[block.xb[0]] & 0xff];
          block.nb[byte5 & 0xff] += j6;
          for (; j6 > 0; j6--) {
            BZipState.rb[i6] = byte5 & 0xff;
            i6++;
          }

        } else {
          int j11 = k5 - 1;
          byte byte6;
          if (j11 < 16) {
            int j10 = block.xb[0];
            byte6 = block.wb[j10 + j11];
            for (; j11 > 3; j11 -= 4) {
              int k11 = j10 + j11;
              block.wb[k11] = block.wb[k11 - 1];
              block.wb[k11 - 1] = block.wb[k11 - 2];
              block.wb[k11 - 2] = block.wb[k11 - 3];
              block.wb[k11 - 3] = block.wb[k11 - 4];
            }

            for (; j11 > 0; j11--)
              block.wb[j10 + j11] = block.wb[(j10 + j11) - 1];

            block.wb[j10] = byte6;
          } else {
            int l10 = j11 / 16;
            int i11 = j11 % 16;
            int k10 = block.xb[l10] + i11;
            byte6 = block.wb[k10];
            for (; k10 > block.xb[l10]; k10--)
              block.wb[k10] = block.wb[k10 - 1];

            block.xb[l10]++;
            for (; l10 > 0; l10--) {
              block.xb[l10]--;
              block.wb[block.xb[l10]] = block.wb[(block.xb[l10 - 1] + 16) - 1];
            }

            block.xb[0]--;
            block.wb[block.xb[0]] = byte6;
            if (block.xb[0] == 0) {
              int i10 = 4095;
              for (int k9 = 15; k9 >= 0; k9--) {
                for (int l9 = 15; l9 >= 0; l9--) {
                  block.wb[i10] = block.wb[block.xb[k9] + l9];
                  i10--;
                }

                block.xb[k9] = i10 + 1;
              }

            }
          }
          block.nb[block.vb[byte6 & 0xff] & 0xff]++;
          BZipState.rb[i6] = block.vb[byte6 & 0xff] & 0xff;
          i6++;
          if (j5 == 0) {
            i5++;
            j5 = 50;
            byte byte14 = block.yb[i5];
            k8 = block.ec[byte14];
            ai = block.bc[byte14];
            ai2 = block.dc[byte14];
            ai1 = block.cc[byte14];
          }
          j5--;
          int k7 = k8;
          int j8;
          byte byte11;
          for (j8 = ck(k7, block); j8 > ai[k7]; j8 = j8 << 1 | byte11) {
            k7++;
            byte11 = bk(block);
          }

          k5 = ai2[j8 - ai1[k7]];
        }

      block.eb = 0;
      block.db = 0;
      block.pb[0] = 0;
      for (int j2 = 1; j2 <= 256; j2++)
        block.pb[j2] = block.nb[j2 - 1];

      for (int k2 = 1; k2 <= 256; k2++)
        block.pb[k2] += block.pb[k2 - 1];

      for (int l2 = 0; l2 < i6; l2++) {
        byte byte7 = (byte) (BZipState.rb[l2] & 0xff);
        BZipState.rb[block.pb[byte7 & 0xff]] |= l2 << 8;
        block.pb[byte7 & 0xff]++;
      }

      block.lb = BZipState.rb[block.kb] >> 8;
      block.ob = 0;
      block.lb = BZipState.rb[block.lb];
      block.mb = (byte) (block.lb & 0xff);
      block.lb >>= 8;
      block.ob++;
      block.fc = i6;
      zj(block);
      if (block.ob == block.fc + 1 && block.eb == 0)
        flag19 = true;
      else
        flag19 = false;
    }
  }

  private static byte readInt8(BZipState arg0) {
    return (byte) ck(8, arg0);
  }

  private static byte bk(BZipState arg0) {
    return (byte) ck(1, arg0);
  }

  private static int ck(int arg0, BZipState arg1) {
    int i;
    do {
      if (arg1.hb >= arg0) {
        int j = arg1.gb >> arg1.hb - arg0 & (1 << arg0) - 1;
        arg1.hb -= arg0;
        i = j;
        break;
      }
      arg1.gb = arg1.gb << 8 | arg1.t[arg1.u] & 0xff;
      arg1.hb += 8;
      arg1.u++;
      arg1.v--;
      arg1.w++;
      if (arg1.w == 0)
        arg1.x++;
    } while (true);
    return i;
  }

  private static void constructMappingTables(BZipState arg0) {
    arg0.sb = 0;
    for (int i = 0; i < 256; i++)
      if (arg0.tb[i]) {
        arg0.vb[arg0.sb] = (byte) i;
        arg0.sb++;
      }

  }

  private static void dk(int arg0[], int arg1[], int arg2[], byte arg3[], int arg4, int arg5, int arg6) {
    int i = 0;
    for (int j = arg4; j <= arg5; j++) {
      for (int i2 = 0; i2 < arg6; i2++)
        if (arg3[i2] == j) {
          arg2[i] = i2;
          i++;
        }

    }

    for (int k = 0; k < 23; k++)
      arg1[k] = 0;

    for (int l = 0; l < arg6; l++)
      arg1[arg3[l] + 1]++;

    for (int i1 = 1; i1 < 23; i1++)
      arg1[i1] += arg1[i1 - 1];

    for (int j1 = 0; j1 < 23; j1++)
      arg0[j1] = 0;

    int j2 = 0;
    for (int k1 = arg4; k1 <= arg5; k1++) {
      j2 += arg1[k1 + 1] - arg1[k1];
      arg0[k1] = j2 - 1;
      j2 <<= 1;
    }

    for (int l1 = arg4 + 1; l1 <= arg5; l1++)
      arg1[l1] = (arg0[l1 - 1] + 1 << 1) - arg1[l1];

  }

  public BZipDecompressor() {
  }

  static final int ms = 1;
  static final int ns = 2;
  static final int os = 10;
  static final int ps = 14;
  static final int qs = 0;
  static final int rs = 4;
  static final int ss = 4096;
  static final int ts = 16;
  static final int us = 258;
  static final int vs = 23;
  static final int ws = 0;
  static final int xs = 1;
  static final int ys = 6;
  static final int zs = 50;
  static final int at = 4;
  static final int bt = 18002;
}
