// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex.client;

import com.jagex.Util;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Surface implements ImageProducer, ImageObserver {
  public Surface(int arg0, int arg1, int arg2, Component arg3) {
    wk = false;
    al = false;
    tk = arg1;
    vk = arg0;
    bk = yj = arg0;
    ck = zj = arg1;
    ak = arg0 * arg1;
    ek = new int[arg0 * arg1];
    ik = new int[arg2][];
    rk = new boolean[arg2];
    jk = new byte[arg2][];
    kk = new int[arg2][];
    lk = new int[arg2];
    mk = new int[arg2];
    pk = new int[arg2];
    qk = new int[arg2];
    nk = new int[arg2];
    ok = new int[arg2];
    if (arg0 > 1 && arg1 > 1 && arg3 != null) {
      dk = new DirectColorModel(32, 0xff0000, 65280, 255);
      int j = yj * zj;
      for (int k = 0; k < j; k++)
        ek[k] = 0;

      hk = arg3.createImage(this);
      vf();
      arg3.prepareImage(hk, arg3);
      vf();
      arg3.prepareImage(hk, arg3);
      vf();
      arg3.prepareImage(hk, arg3);
    }
  }

  public synchronized void wg(int arg0, int arg1) {
    if (yj > bk)
      yj = bk;
    if (zj > ck)
      zj = ck;
    yj = arg0;
    zj = arg1;
    ak = arg0 * arg1;
  }

  public synchronized void addConsumer(ImageConsumer arg0) {
    fk = arg0;
    arg0.setDimensions(yj, zj);
    arg0.setProperties(null);
    arg0.setColorModel(dk);
    arg0.setHints(14);
  }

  public synchronized boolean isConsumer(ImageConsumer arg0) {
    return fk == arg0;
  }

  public synchronized void removeConsumer(ImageConsumer arg0) {
    if (fk == arg0)
      fk = null;
  }

  public void startProduction(ImageConsumer arg0) {
    addConsumer(arg0);
  }

  public void requestTopDownLeftRightResend(ImageConsumer arg0) {
    System.out.println("TDLR");
  }

  public synchronized void vf() {
    if (fk == null) {
      return;
    } else {
      fk.setPixels(0, 0, yj, zj, dk, ek, 0, yj);
      fk.imageComplete(2);
      return;
    }
  }

  public void sf(int arg0, int arg1, int arg2, int arg3) {
    if (arg0 < 0)
      arg0 = 0;
    if (arg1 < 0)
      arg1 = 0;
    if (arg2 > yj)
      arg2 = yj;
    if (arg3 > zj)
      arg3 = zj;
    uk = arg0;
    sk = arg1;
    vk = arg2;
    tk = arg3;
  }

  public void tf() {
    uk = 0;
    sk = 0;
    vk = yj;
    tk = zj;
  }

  public void jf(Graphics arg0, int arg1, int arg2) {
    vf();
    arg0.drawImage(hk, arg1, arg2, this);
  }

  public void lf() {
    int j = yj * zj;
    if (!wk) {
      for (int k = 0; k < j; k++)
        ek[k] = 0;

      return;
    }
    int l = 0;
    for (int i1 = -zj; i1 < 0; i1 += 2) {
      for (int j1 = -yj; j1 < 0; j1++)
        ek[l++] = 0;
      l += yj;
    }
  }

  public void zf(int arg0, int arg1, int arg2, int arg3, int arg4) {
    int j = 256 - arg4;
    int k = (arg3 >> 16 & 0xff) * arg4;
    int l = (arg3 >> 8 & 0xff) * arg4;
    int i1 = (arg3 & 0xff) * arg4;
    int i2 = arg1 - arg2;
    if (i2 < 0)
      i2 = 0;
    int j2 = arg1 + arg2;
    if (j2 >= zj)
      j2 = zj - 1;
    byte byte0 = 1;
    if (wk) {
      byte0 = 2;
      if ((i2 & 1) != 0)
        i2++;
    }
    for (int k2 = i2; k2 <= j2; k2 += byte0) {
      int l2 = k2 - arg1;
      int i3 = (int) Math.sqrt(arg2 * arg2 - l2 * l2);
      int j3 = arg0 - i3;
      if (j3 < 0)
        j3 = 0;
      int k3 = arg0 + i3;
      if (k3 >= yj)
        k3 = yj - 1;
      int l3 = j3 + k2 * yj;
      for (int i4 = j3; i4 <= k3; i4++) {
        int j1 = (ek[l3] >> 16 & 0xff) * j;
        int k1 = (ek[l3] >> 8 & 0xff) * j;
        int l1 = (ek[l3] & 0xff) * j;
        int j4 = ((k + j1 >> 8) << 16) + ((l + k1 >> 8) << 8) + (i1 + l1 >> 8);
        ek[l3++] = j4;
      }

    }

  }

  public void uf(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    if (arg0 < uk) {
      arg2 -= uk - arg0;
      arg0 = uk;
    }
    if (arg1 < sk) {
      arg3 -= sk - arg1;
      arg1 = sk;
    }
    if (arg0 + arg2 > vk)
      arg2 = vk - arg0;
    if (arg1 + arg3 > tk)
      arg3 = tk - arg1;
    int j = 256 - arg5;
    int k = (arg4 >> 16 & 0xff) * arg5;
    int l = (arg4 >> 8 & 0xff) * arg5;
    int i1 = (arg4 & 0xff) * arg5;
    int i2 = yj - arg2;
    byte byte0 = 1;
    if (wk) {
      byte0 = 2;
      i2 += yj;
      if ((arg1 & 1) != 0) {
        arg1++;
        arg3--;
      }
    }
    int j2 = arg0 + arg1 * yj;
    for (int k2 = 0; k2 < arg3; k2 += byte0) {
      for (int l2 = -arg2; l2 < 0; l2++) {
        int j1 = (ek[j2] >> 16 & 0xff) * j;
        int k1 = (ek[j2] >> 8 & 0xff) * j;
        int l1 = (ek[j2] & 0xff) * j;
        int i3 = ((k + j1 >> 8) << 16) + ((l + k1 >> 8) << 8) + (i1 + l1 >> 8);
        ek[j2++] = i3;
      }
      j2 += i2;
    }
  }

  public void hg(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    if (arg0 < uk) {
      arg2 -= uk - arg0;
      arg0 = uk;
    }
    if (arg0 + arg2 > vk)
      arg2 = vk - arg0;
    int j = arg5 >> 16 & 0xff;
    int k = arg5 >> 8 & 0xff;
    int l = arg5 & 0xff;
    int i1 = arg4 >> 16 & 0xff;
    int j1 = arg4 >> 8 & 0xff;
    int k1 = arg4 & 0xff;
    int l1 = yj - arg2;
    byte byte0 = 1;
    if (wk) {
      byte0 = 2;
      l1 += yj;
      if ((arg1 & 1) != 0) {
        arg1++;
        arg3--;
      }
    }
    int i2 = arg0 + arg1 * yj;
    for (int j2 = 0; j2 < arg3; j2 += byte0)
      if (j2 + arg1 >= sk && j2 + arg1 < tk) {
        int k2 = ((j * j2 + i1 * (arg3 - j2)) / arg3 << 16) + ((k * j2 + j1 * (arg3 - j2)) / arg3 << 8)
            + (l * j2 + k1 * (arg3 - j2)) / arg3;
        for (int l2 = -arg2; l2 < 0; l2++)
          ek[i2++] = k2;

        i2 += l1;
      } else {
        i2 += yj;
      }

  }

  public void yf(int arg0, int arg1, int arg2, int arg3, int arg4) {
    if (arg0 < uk) {
      arg2 -= uk - arg0;
      arg0 = uk;
    }
    if (arg1 < sk) {
      arg3 -= sk - arg1;
      arg1 = sk;
    }
    if (arg0 + arg2 > vk)
      arg2 = vk - arg0;
    if (arg1 + arg3 > tk)
      arg3 = tk - arg1;
    int j = yj - arg2;
    byte byte0 = 1;
    if (wk) {
      byte0 = 2;
      j += yj;
      if ((arg1 & 1) != 0) {
        arg1++;
        arg3--;
      }
    }
    int k = arg0 + arg1 * yj;
    for (int l = -arg3; l < 0; l += byte0) {
      for (int i1 = -arg2; i1 < 0; i1++)
        ek[k++] = arg4;
      k += j;
    }
  }

  public void qf(int arg0, int arg1, int arg2, int arg3, int arg4) {
    rg(arg0, arg1, arg2, arg4);
    rg(arg0, (arg1 + arg3) - 1, arg2, arg4);
    vg(arg0, arg1, arg3, arg4);
    vg((arg0 + arg2) - 1, arg1, arg3, arg4);
  }

  public void rg(int arg0, int arg1, int arg2, int arg3) {
    if (arg1 < sk || arg1 >= tk)
      return;
    if (arg0 < uk) {
      arg2 -= uk - arg0;
      arg0 = uk;
    }
    if (arg0 + arg2 > vk)
      arg2 = vk - arg0;
    int j = arg0 + arg1 * yj;
    for (int k = 0; k < arg2; k++)
      ek[j + k] = arg3;

  }

  public void vg(int arg0, int arg1, int arg2, int arg3) {
    if (arg0 < uk || arg0 >= vk)
      return;
    if (arg1 < sk) {
      arg2 -= sk - arg1;
      arg1 = sk;
    }
    if (arg1 + arg2 > vk)
      arg2 = tk - arg1;
    int j = arg0 + arg1 * yj;
    for (int k = 0; k < arg2; k++)
      ek[j + k * yj] = arg3;

  }

  public void lg(int arg0, int arg1, int arg2) {
    if (arg0 < uk || arg1 < sk || arg0 >= vk || arg1 >= tk) {
      return;
    } else {
      ek[arg0 + arg1 * yj] = arg2;
      return;
    }
  }

  public void ff() {
    int l = yj * zj;
    for (int k = 0; k < l; k++) {
      int j = ek[k] & 0xffffff;
      ek[k] = (j >>> 1 & 0x7f7f7f) + (j >>> 2 & 0x3f3f3f) + (j >>> 3 & 0x1f1f1f) + (j >>> 4 & 0xf0f0f);
    }

  }

  public void sg(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    for (int j = arg2; j < arg2 + arg4; j++) {
      for (int k = arg3; k < arg3 + arg5; k++) {
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        for (int l1 = j - arg0; l1 <= j + arg0; l1++)
          if (l1 >= 0 && l1 < yj) {
            for (int i2 = k - arg1; i2 <= k + arg1; i2++)
              if (i2 >= 0 && i2 < zj) {
                int j2 = ek[l1 + yj * i2];
                l += j2 >> 16 & 0xff;
                i1 += j2 >> 8 & 0xff;
                j1 += j2 & 0xff;
                k1++;
              }

          }

        ek[j + yj * k] = (l / k1 << 16) + (i1 / k1 << 8) + j1 / k1;
      }

    }

  }

  public static int kg(int arg0, int arg1, int arg2) {
    return (arg0 << 16) + (arg1 << 8) + arg2;
  }

  public void jg() {
    for (int j = 0; j < ik.length; j++) {
      ik[j] = null;
      lk[j] = 0;
      mk[j] = 0;
      jk[j] = null;
      kk[j] = null;
    }

  }

  public void og(int arg0, byte arg1[], byte arg2[], int arg3) {
    int j = Util.readInt16(arg1, 0);
    int k = Util.readInt16(arg2, j);
    j += 2;
    int l = Util.readInt16(arg2, j);
    j += 2;
    int i1 = arg2[j++] & 0xff;
    int ai[] = new int[i1];
    ai[0] = 0xff00ff;
    for (int j1 = 0; j1 < i1 - 1; j1++) {
      ai[j1 + 1] = ((arg2[j] & 0xff) << 16) + ((arg2[j + 1] & 0xff) << 8) + (arg2[j + 2] & 0xff);
      j += 3;
    }

    int k1 = 2;
    for (int l1 = arg0; l1 < arg0 + arg3; l1++) {
      nk[l1] = arg2[j++] & 0xff;
      ok[l1] = arg2[j++] & 0xff;
      lk[l1] = Util.readInt16(arg2, j);
      j += 2;
      mk[l1] = Util.readInt16(arg2, j);
      j += 2;
      int i2 = arg2[j++] & 0xff;
      int j2 = lk[l1] * mk[l1];
      jk[l1] = new byte[j2];
      kk[l1] = ai;
      pk[l1] = k;
      qk[l1] = l;
      ik[l1] = null;
      rk[l1] = false;
      if (nk[l1] != 0 || ok[l1] != 0)
        rk[l1] = true;
      if (i2 == 0) {
        for (int k2 = 0; k2 < j2; k2++) {
          jk[l1][k2] = arg1[k1++];
          if (jk[l1][k2] == 0)
            rk[l1] = true;
        }

      } else if (i2 == 1) {
        for (int l2 = 0; l2 < lk[l1]; l2++) {
          for (int i3 = 0; i3 < mk[l1]; i3++) {
            jk[l1][l2 + i3 * lk[l1]] = arg1[k1++];
            if (jk[l1][l2 + i3 * lk[l1]] == 0)
              rk[l1] = true;
          }

        }

      }
    }

  }

  public void ig(byte arg0[], int arg1, int arg2, boolean arg3, int arg4, int arg5, boolean arg6) {
    int j = (arg0[13 + arg1] & 0xff) * 256 + (arg0[12 + arg1] & 0xff);
    int k = (arg0[15 + arg1] & 0xff) * 256 + (arg0[14 + arg1] & 0xff);
    int l = -1;
    int ai[] = new int[256];
    for (int i1 = 0; i1 < 256; i1++) {
      ai[i1] = 0xff000000 + ((arg0[arg1 + 20 + i1 * 3] & 0xff) << 16) + ((arg0[arg1 + 19 + i1 * 3] & 0xff) << 8)
          + (arg0[arg1 + 18 + i1 * 3] & 0xff);
      if (ai[i1] == -65281)
        l = i1;
    }

    if (l == -1)
      arg3 = false;
    if (arg6 && arg3)
      ai[l] = ai[0];
    int j1 = j / arg4;
    int k1 = k / arg5;
    int ai1[] = new int[j1 * k1];
    for (int l1 = 0; l1 < arg5; l1++) {
      for (int i2 = 0; i2 < arg4; i2++) {
        int j2 = 0;
        for (int k2 = k1 * l1; k2 < k1 * (l1 + 1); k2++) {
          for (int l2 = j1 * i2; l2 < j1 * (i2 + 1); l2++)
            if (arg6)
              ai1[j2++] = arg0[arg1 + 786 + l2 + (k - k2 - 1) * j] & 0xff;
            else
              ai1[j2++] = ai[arg0[arg1 + 786 + l2 + (k - k2 - 1) * j] & 0xff];

        }

        if (arg6)
          hf(ai1, j1, k1, arg2++, arg3, ai, l);
        else
          hf(ai1, j1, k1, arg2++, arg3, null, -65281);
      }

    }

  }

  private void hf(int arg0[], int arg1, int arg2, int arg3, boolean arg4, int arg5[], int arg6) {
    int j = 0;
    int k = 0;
    int l = arg1;
    int i1 = arg2;
    if (arg4) {
      label0: for (int j1 = 0; j1 < arg2; j1++) {
        for (int i2 = 0; i2 < arg1; i2++) {
          int i3 = arg0[i2 + j1 * arg1];
          if (i3 == arg6)
            continue;
          k = j1;
          break label0;
        }

      }

      label1: for (int j2 = 0; j2 < arg1; j2++) {
        for (int j3 = 0; j3 < arg2; j3++) {
          int j4 = arg0[j2 + j3 * arg1];
          if (j4 == arg6)
            continue;
          j = j2;
          break label1;
        }

      }

      label2: for (int k3 = arg2 - 1; k3 >= 0; k3--) {
        for (int k4 = 0; k4 < arg1; k4++) {
          int k5 = arg0[k4 + k3 * arg1];
          if (k5 == arg6)
            continue;
          i1 = k3 + 1;
          break label2;
        }

      }

      label3: for (int l4 = arg1 - 1; l4 >= 0; l4--) {
        for (int l5 = 0; l5 < arg2; l5++) {
          int i6 = arg0[l4 + l5 * arg1];
          if (i6 == arg6)
            continue;
          l = l4 + 1;
          break label3;
        }

      }

    }
    lk[arg3] = l - j;
    mk[arg3] = i1 - k;
    rk[arg3] = arg4;
    nk[arg3] = j;
    ok[arg3] = k;
    pk[arg3] = arg1;
    qk[arg3] = arg2;
    if (arg5 == null) {
      ik[arg3] = new int[(l - j) * (i1 - k)];
      int k1 = 0;
      for (int k2 = k; k2 < i1; k2++) {
        for (int l3 = j; l3 < l; l3++) {
          int i5 = arg0[l3 + k2 * arg1];
          if (arg4) {
            if (i5 == arg6)
              i5 = 0;
            if (i5 == 0xff000000)
              i5 = 0xff010101;
          }
          ik[arg3][k1++] = i5 & 0xffffff;
        }

      }

      return;
    }
    jk[arg3] = new byte[(l - j) * (i1 - k)];
    kk[arg3] = arg5;
    int l1 = 0;
    for (int l2 = k; l2 < i1; l2++) {
      for (int i4 = j; i4 < l; i4++) {
        int j5 = arg0[i4 + l2 * arg1];
        if (arg4)
          if (j5 == arg6)
            j5 = 0;
          else if (j5 == 0)
            j5 = arg6;
        jk[arg3][l1++] = (byte) j5;
      }

    }

  }

  public void fg(int arg0) {
    int j = lk[arg0] * mk[arg0];
    int ai[] = ik[arg0];
    int ai1[] = new int[32768];
    for (int k = 0; k < j; k++) {
      int l = ai[k];
      ai1[((l & 0xf80000) >> 9) + ((l & 0xf800) >> 6) + ((l & 0xf8) >> 3)]++;
    }

    int ai2[] = new int[256];
    ai2[0] = 0xff00ff;
    int ai3[] = new int[256];
    for (int i1 = 0; i1 < 32768; i1++) {
      int j1 = ai1[i1];
      if (j1 > ai3[255]) {
        for (int k1 = 1; k1 < 256; k1++) {
          if (j1 <= ai3[k1])
            continue;
          for (int i2 = 255; i2 > k1; i2--) {
            ai2[i2] = ai2[i2 - 1];
            ai3[i2] = ai3[i2 - 1];
          }

          ai2[k1] = ((i1 & 0x7c00) << 9) + ((i1 & 0x3e0) << 6) + ((i1 & 0x1f) << 3) + 0x40404;
          ai3[k1] = j1;
          break;
        }

      }
      ai1[i1] = -1;
    }

    byte abyte0[] = new byte[j];
    for (int l1 = 0; l1 < j; l1++) {
      int j2 = ai[l1];
      int k2 = ((j2 & 0xf80000) >> 9) + ((j2 & 0xf800) >> 6) + ((j2 & 0xf8) >> 3);
      int l2 = ai1[k2];
      if (l2 == -1) {
        int i3 = 0x3b9ac9ff;
        int j3 = j2 >> 16 & 0xff;
        int k3 = j2 >> 8 & 0xff;
        int l3 = j2 & 0xff;
        for (int i4 = 0; i4 < 256; i4++) {
          int j4 = ai2[i4];
          int k4 = j4 >> 16 & 0xff;
          int l4 = j4 >> 8 & 0xff;
          int i5 = j4 & 0xff;
          int j5 = (j3 - k4) * (j3 - k4) + (k3 - l4) * (k3 - l4) + (l3 - i5) * (l3 - i5);
          if (j5 < i3) {
            i3 = j5;
            l2 = i4;
          }
        }

        ai1[k2] = l2;
      }
      abyte0[l1] = (byte) l2;
    }

    jk[arg0] = abyte0;
    kk[arg0] = ai2;
    ik[arg0] = null;
  }

  public void zg(int arg0) {
    if (jk[arg0] == null)
      return;
    int j = lk[arg0] * mk[arg0];
    byte abyte0[] = jk[arg0];
    int ai[] = kk[arg0];
    int ai1[] = new int[j];
    for (int k = 0; k < j; k++) {
      int l = ai[abyte0[k] & 0xff];
      if (l == 0)
        l = 1;
      else if (l == 0xff00ff)
        l = 0;
      ai1[k] = l;
    }

    ik[arg0] = ai1;
    jk[arg0] = null;
    kk[arg0] = null;
  }

  public void kf(int arg0, int arg1, int arg2, int arg3, int arg4) {
    lk[arg0] = arg3;
    mk[arg0] = arg4;
    rk[arg0] = false;
    nk[arg0] = 0;
    ok[arg0] = 0;
    pk[arg0] = arg3;
    qk[arg0] = arg4;
    int j = arg3 * arg4;
    int k = 0;
    ik[arg0] = new int[j];
    for (int l = arg1; l < arg1 + arg3; l++) {
      for (int i1 = arg2; i1 < arg2 + arg4; i1++)
        ik[arg0][k++] = ek[l + i1 * yj];

    }

  }

  public void rf(int arg0, int arg1, int arg2, int arg3, int arg4) {
    lk[arg0] = arg3;
    mk[arg0] = arg4;
    rk[arg0] = false;
    nk[arg0] = 0;
    ok[arg0] = 0;
    pk[arg0] = arg3;
    qk[arg0] = arg4;
    int j = arg3 * arg4;
    int k = 0;
    ik[arg0] = new int[j];
    for (int l = arg2; l < arg2 + arg4; l++) {
      for (int i1 = arg1; i1 < arg1 + arg3; i1++)
        ik[arg0][k++] = ek[i1 + l * yj];

    }

  }

  public void xg(int arg0, int arg1, int arg2) {
    if (rk[arg2]) {
      arg0 += nk[arg2];
      arg1 += ok[arg2];
    }
    int j = arg0 + arg1 * yj;
    int k = 0;
    int l = mk[arg2];
    int i1 = lk[arg2];
    int j1 = yj - i1;
    int k1 = 0;
    if (arg1 < sk) {
      int l1 = sk - arg1;
      l -= l1;
      arg1 = sk;
      k += l1 * i1;
      j += l1 * yj;
    }
    if (arg1 + l >= tk)
      l -= ((arg1 + l) - tk) + 1;
    if (arg0 < uk) {
      int i2 = uk - arg0;
      i1 -= i2;
      arg0 = uk;
      k += i2;
      j += i2;
      k1 += i2;
      j1 += i2;
    }
    if (arg0 + i1 >= vk) {
      int j2 = ((arg0 + i1) - vk) + 1;
      i1 -= j2;
      k1 += j2;
      j1 += j2;
    }
    if (i1 <= 0 || l <= 0)
      return;
    byte byte0 = 1;
    if (wk) {
      byte0 = 2;
      j1 += yj;
      k1 += lk[arg2];
      if ((arg1 & 1) != 0) {
        j += yj;
        l--;
      }
    }
    if (ik[arg2] == null) {
      cg(ek, jk[arg2], kk[arg2], k, j, i1, l, j1, k1, byte0);
      return;
    } else {
      gf(ek, ik[arg2], 0, k, j, i1, l, j1, k1, byte0);
      return;
    }
  }

  public void nf(int arg0, int arg1, int arg2, int arg3, int arg4) {
    try {
      int j = lk[arg4];
      int k = mk[arg4];
      int l = 0;
      int i1 = 0;
      int j1 = (j << 16) / arg2;
      int k1 = (k << 16) / arg3;
      if (rk[arg4]) {
        int l1 = pk[arg4];
        int j2 = qk[arg4];
        j1 = (l1 << 16) / arg2;
        k1 = (j2 << 16) / arg3;
        arg0 += ((nk[arg4] * arg2 + l1) - 1) / l1;
        arg1 += ((ok[arg4] * arg3 + j2) - 1) / j2;
        if ((nk[arg4] * arg2) % l1 != 0)
          l = (l1 - (nk[arg4] * arg2) % l1 << 16) / arg2;
        if ((ok[arg4] * arg3) % j2 != 0)
          i1 = (j2 - (ok[arg4] * arg3) % j2 << 16) / arg3;
        arg2 = (arg2 * (lk[arg4] - (l >> 16))) / l1;
        arg3 = (arg3 * (mk[arg4] - (i1 >> 16))) / j2;
      }
      int i2 = arg0 + arg1 * yj;
      int k2 = yj - arg2;
      if (arg1 < sk) {
        int l2 = sk - arg1;
        arg3 -= l2;
        arg1 = 0;
        i2 += l2 * yj;
        i1 += k1 * l2;
      }
      if (arg1 + arg3 >= tk)
        arg3 -= ((arg1 + arg3) - tk) + 1;
      if (arg0 < uk) {
        int i3 = uk - arg0;
        arg2 -= i3;
        arg0 = 0;
        i2 += i3;
        l += j1 * i3;
        k2 += i3;
      }
      if (arg0 + arg2 >= vk) {
        int j3 = ((arg0 + arg2) - vk) + 1;
        arg2 -= j3;
        k2 += j3;
      }
      byte byte0 = 1;
      if (wk) {
        byte0 = 2;
        k2 += yj;
        k1 += k1;
        if ((arg1 & 1) != 0) {
          i2 += yj;
          arg3--;
        }
      }
      pf(ek, ik[arg4], 0, l, i1, i2, k2, arg2, arg3, j1, k1, j, byte0);
      return;
    } catch (Exception _ex) {
      System.out.println("error in sprite clipping routine");
    }
  }

  public void qg(int arg0, int arg1, int arg2, int arg3) {
    if (rk[arg2]) {
      arg0 += nk[arg2];
      arg1 += ok[arg2];
    }
    int j = arg0 + arg1 * yj;
    int k = 0;
    int l = mk[arg2];
    int i1 = lk[arg2];
    int j1 = yj - i1;
    int k1 = 0;
    if (arg1 < sk) {
      int l1 = sk - arg1;
      l -= l1;
      arg1 = sk;
      k += l1 * i1;
      j += l1 * yj;
    }
    if (arg1 + l >= tk)
      l -= ((arg1 + l) - tk) + 1;
    if (arg0 < uk) {
      int i2 = uk - arg0;
      i1 -= i2;
      arg0 = uk;
      k += i2;
      j += i2;
      k1 += i2;
      j1 += i2;
    }
    if (arg0 + i1 >= vk) {
      int j2 = ((arg0 + i1) - vk) + 1;
      i1 -= j2;
      k1 += j2;
      j1 += j2;
    }
    if (i1 <= 0 || l <= 0)
      return;
    byte byte0 = 1;
    if (wk) {
      byte0 = 2;
      j1 += yj;
      k1 += lk[arg2];
      if ((arg1 & 1) != 0) {
        j += yj;
        l--;
      }
    }
    if (ik[arg2] == null) {
      ch(ek, jk[arg2], kk[arg2], k, j, i1, l, j1, k1, byte0, arg3);
      return;
    } else {
      gg(ek, ik[arg2], 0, k, j, i1, l, j1, k1, byte0, arg3);
      return;
    }
  }

  public void pg(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    try {
      int j = lk[arg4];
      int k = mk[arg4];
      int l = 0;
      int i1 = 0;
      int j1 = (j << 16) / arg2;
      int k1 = (k << 16) / arg3;
      if (rk[arg4]) {
        int l1 = pk[arg4];
        int j2 = qk[arg4];
        j1 = (l1 << 16) / arg2;
        k1 = (j2 << 16) / arg3;
        arg0 += ((nk[arg4] * arg2 + l1) - 1) / l1;
        arg1 += ((ok[arg4] * arg3 + j2) - 1) / j2;
        if ((nk[arg4] * arg2) % l1 != 0)
          l = (l1 - (nk[arg4] * arg2) % l1 << 16) / arg2;
        if ((ok[arg4] * arg3) % j2 != 0)
          i1 = (j2 - (ok[arg4] * arg3) % j2 << 16) / arg3;
        arg2 = (arg2 * (lk[arg4] - (l >> 16))) / l1;
        arg3 = (arg3 * (mk[arg4] - (i1 >> 16))) / j2;
      }
      int i2 = arg0 + arg1 * yj;
      int k2 = yj - arg2;
      if (arg1 < sk) {
        int l2 = sk - arg1;
        arg3 -= l2;
        arg1 = 0;
        i2 += l2 * yj;
        i1 += k1 * l2;
      }
      if (arg1 + arg3 >= tk)
        arg3 -= ((arg1 + arg3) - tk) + 1;
      if (arg0 < uk) {
        int i3 = uk - arg0;
        arg2 -= i3;
        arg0 = 0;
        i2 += i3;
        l += j1 * i3;
        k2 += i3;
      }
      if (arg0 + arg2 >= vk) {
        int j3 = ((arg0 + arg2) - vk) + 1;
        arg2 -= j3;
        k2 += j3;
      }
      byte byte0 = 1;
      if (wk) {
        byte0 = 2;
        k2 += yj;
        k1 += k1;
        if ((arg1 & 1) != 0) {
          i2 += yj;
          arg3--;
        }
      }
      xf(ek, ik[arg4], 0, l, i1, i2, k2, arg2, arg3, j1, k1, j, byte0, arg5);
      return;
    } catch (Exception _ex) {
      System.out.println("error in sprite clipping routine");
    }
  }

  public void mg(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    try {
      int j = lk[arg4];
      int k = mk[arg4];
      int l = 0;
      int i1 = 0;
      int j1 = (j << 16) / arg2;
      int k1 = (k << 16) / arg3;
      if (rk[arg4]) {
        int l1 = pk[arg4];
        int j2 = qk[arg4];
        j1 = (l1 << 16) / arg2;
        k1 = (j2 << 16) / arg3;
        arg0 += ((nk[arg4] * arg2 + l1) - 1) / l1;
        arg1 += ((ok[arg4] * arg3 + j2) - 1) / j2;
        if ((nk[arg4] * arg2) % l1 != 0)
          l = (l1 - (nk[arg4] * arg2) % l1 << 16) / arg2;
        if ((ok[arg4] * arg3) % j2 != 0)
          i1 = (j2 - (ok[arg4] * arg3) % j2 << 16) / arg3;
        arg2 = (arg2 * (lk[arg4] - (l >> 16))) / l1;
        arg3 = (arg3 * (mk[arg4] - (i1 >> 16))) / j2;
      }
      int i2 = arg0 + arg1 * yj;
      int k2 = yj - arg2;
      if (arg1 < sk) {
        int l2 = sk - arg1;
        arg3 -= l2;
        arg1 = 0;
        i2 += l2 * yj;
        i1 += k1 * l2;
      }
      if (arg1 + arg3 >= tk)
        arg3 -= ((arg1 + arg3) - tk) + 1;
      if (arg0 < uk) {
        int i3 = uk - arg0;
        arg2 -= i3;
        arg0 = 0;
        i2 += i3;
        l += j1 * i3;
        k2 += i3;
      }
      if (arg0 + arg2 >= vk) {
        int j3 = ((arg0 + arg2) - vk) + 1;
        arg2 -= j3;
        k2 += j3;
      }
      byte byte0 = 1;
      if (wk) {
        byte0 = 2;
        k2 += yj;
        k1 += k1;
        if ((arg1 & 1) != 0) {
          i2 += yj;
          arg3--;
        }
      }
      eh(ek, ik[arg4], 0, l, i1, i2, k2, arg2, arg3, j1, k1, j, byte0, arg5);
      return;
    } catch (Exception _ex) {
      System.out.println("error in sprite clipping routine");
    }
  }

  // previously non static -- stormy
  private static void gf(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9) {
    int j = -(arg5 >> 2);
    arg5 = -(arg5 & 3);
    for (int k = -arg6; k < 0; k += arg9) {
      for (int l = j; l < 0; l++) {
        arg2 = arg1[arg3++];
        if (arg2 != 0)
          arg0[arg4++] = arg2;
        else
          arg4++;
        arg2 = arg1[arg3++];
        if (arg2 != 0)
          arg0[arg4++] = arg2;
        else
          arg4++;
        arg2 = arg1[arg3++];
        if (arg2 != 0)
          arg0[arg4++] = arg2;
        else
          arg4++;
        arg2 = arg1[arg3++];
        if (arg2 != 0)
          arg0[arg4++] = arg2;
        else
          arg4++;
      }

      for (int i1 = arg5; i1 < 0; i1++) {
        arg2 = arg1[arg3++];
        if (arg2 != 0)
          arg0[arg4++] = arg2;
        else
          arg4++;
      }

      arg4 += arg7;
      arg3 += arg8;
    }

  }

  // previously non static -- stormy
  private static void cg(int arg0[], byte arg1[], int arg2[], int arg3, int arg4, int arg5, int arg6, int arg7,
      int arg8, int arg9) {
    int j = -(arg5 >> 2);
    arg5 = -(arg5 & 3);
    for (int k = -arg6; k < 0; k += arg9) {
      for (int l = j; l < 0; l++) {
        byte byte0 = arg1[arg3++];
        if (byte0 != 0)
          arg0[arg4++] = arg2[byte0 & 0xff];
        else
          arg4++;
        byte0 = arg1[arg3++];
        if (byte0 != 0)
          arg0[arg4++] = arg2[byte0 & 0xff];
        else
          arg4++;
        byte0 = arg1[arg3++];
        if (byte0 != 0)
          arg0[arg4++] = arg2[byte0 & 0xff];
        else
          arg4++;
        byte0 = arg1[arg3++];
        if (byte0 != 0)
          arg0[arg4++] = arg2[byte0 & 0xff];
        else
          arg4++;
      }

      for (int i1 = arg5; i1 < 0; i1++) {
        byte byte1 = arg1[arg3++];
        if (byte1 != 0)
          arg0[arg4++] = arg2[byte1 & 0xff];
        else
          arg4++;
      }

      arg4 += arg7;
      arg3 += arg8;
    }

  }

  // previously non static -- stormy
  private static void pf(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12) {
    try {
      int j = arg3;
      for (int k = -arg8; k < 0; k += arg12) {
        int l = (arg4 >> 16) * arg11;
        for (int i1 = -arg7; i1 < 0; i1++) {
          arg2 = arg1[(arg3 >> 16) + l];
          if (arg2 != 0)
            arg0[arg5++] = arg2;
          else
            arg5++;
          arg3 += arg9;
        }

        arg4 += arg10;
        arg3 = j;
        arg5 += arg6;
      }

      return;
    } catch (Exception _ex) {
      System.out.println("error in plot_scale");
    }
  }

  // previously non static -- stormy
  private static void gg(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10) {
    int j = 256 - arg10;
    for (int k = -arg6; k < 0; k += arg9) {
      for (int l = -arg5; l < 0; l++) {
        arg2 = arg1[arg3++];
        if (arg2 != 0) {
          int i1 = arg0[arg4];
          arg0[arg4++] = ((arg2 & 0xff00ff) * arg10 + (i1 & 0xff00ff) * j & 0xff00ff00)
              + ((arg2 & 0xff00) * arg10 + (i1 & 0xff00) * j & 0xff0000) >> 8;
        } else {
          arg4++;
        }
      }

      arg4 += arg7;
      arg3 += arg8;
    }

  }

  // previously non static -- stormy
  private static void ch(int arg0[], byte arg1[], int arg2[], int arg3, int arg4, int arg5, int arg6, int arg7,
      int arg8, int arg9, int arg10) {
    int j = 256 - arg10;
    for (int k = -arg6; k < 0; k += arg9) {
      for (int l = -arg5; l < 0; l++) {
        int i1 = arg1[arg3++];
        if (i1 != 0) {
          i1 = arg2[i1 & 0xff];
          int j1 = arg0[arg4];
          arg0[arg4++] = ((i1 & 0xff00ff) * arg10 + (j1 & 0xff00ff) * j & 0xff00ff00)
              + ((i1 & 0xff00) * arg10 + (j1 & 0xff00) * j & 0xff0000) >> 8;
        } else {
          arg4++;
        }
      }

      arg4 += arg7;
      arg3 += arg8;
    }

  }

  // previously non static -- stormy
  private static void xf(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13) {
    int j = 256 - arg13;
    try {
      int k = arg3;
      for (int l = -arg8; l < 0; l += arg12) {
        int i1 = (arg4 >> 16) * arg11;
        for (int j1 = -arg7; j1 < 0; j1++) {
          arg2 = arg1[(arg3 >> 16) + i1];
          if (arg2 != 0) {
            int k1 = arg0[arg5];
            arg0[arg5++] = ((arg2 & 0xff00ff) * arg13 + (k1 & 0xff00ff) * j & 0xff00ff00)
                + ((arg2 & 0xff00) * arg13 + (k1 & 0xff00) * j & 0xff0000) >> 8;
          } else {
            arg5++;
          }
          arg3 += arg9;
        }

        arg4 += arg10;
        arg3 = k;
        arg5 += arg6;
      }

      return;
    } catch (Exception _ex) {
      System.out.println("error in tran_scale");
    }
  }

  // previously non static -- stormy
  private static void eh(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13) {
    int j = arg13 >> 16 & 0xff;
    int k = arg13 >> 8 & 0xff;
    int l = arg13 & 0xff;
    try {
      int i1 = arg3;
      for (int j1 = -arg8; j1 < 0; j1 += arg12) {
        int k1 = (arg4 >> 16) * arg11;
        for (int l1 = -arg7; l1 < 0; l1++) {
          arg2 = arg1[(arg3 >> 16) + k1];
          if (arg2 != 0) {
            int i2 = arg2 >> 16 & 0xff;
            int j2 = arg2 >> 8 & 0xff;
            int k2 = arg2 & 0xff;
            if (i2 == j2 && j2 == k2)
              arg0[arg5++] = ((i2 * j >> 8) << 16) + ((j2 * k >> 8) << 8) + (k2 * l >> 8);
            else
              arg0[arg5++] = arg2;
          } else {
            arg5++;
          }
          arg3 += arg9;
        }

        arg4 += arg10;
        arg3 = i1;
        arg5 += arg6;
      }

      return;
    } catch (Exception _ex) {
      System.out.println("error in plot_scale");
    }
  }

  public void of(int arg0, int arg1, int arg2, int arg3, int arg4) {
    int j = yj;
    int k = zj;
    if (bl == null) {
      bl = new int[512];
      for (int l = 0; l < 256; l++) {
        bl[l] = (int) (Math.sin((double) l * 0.02454369D) * 32768D);
        bl[l + 256] = (int) (Math.cos((double) l * 0.02454369D) * 32768D);
      }

    }
    int i1 = -pk[arg2] / 2;
    int j1 = -qk[arg2] / 2;
    if (rk[arg2]) {
      i1 += nk[arg2];
      j1 += ok[arg2];
    }
    int k1 = i1 + lk[arg2];
    int l1 = j1 + mk[arg2];
    int i2 = k1;
    int j2 = j1;
    int k2 = i1;
    int l2 = l1;
    arg3 &= 0xff;
    int i3 = bl[arg3] * arg4;
    int j3 = bl[arg3 + 256] * arg4;
    int k3 = arg0 + (j1 * i3 + i1 * j3 >> 22);
    int l3 = arg1 + (j1 * j3 - i1 * i3 >> 22);
    int i4 = arg0 + (j2 * i3 + i2 * j3 >> 22);
    int j4 = arg1 + (j2 * j3 - i2 * i3 >> 22);
    int k4 = arg0 + (l1 * i3 + k1 * j3 >> 22);
    int l4 = arg1 + (l1 * j3 - k1 * i3 >> 22);
    int i5 = arg0 + (l2 * i3 + k2 * j3 >> 22);
    int j5 = arg1 + (l2 * j3 - k2 * i3 >> 22);
    int k5 = l3;
    int l5 = l3;
    if (j4 < k5)
      k5 = j4;
    else if (j4 > l5)
      l5 = j4;
    if (l4 < k5)
      k5 = l4;
    else if (l4 > l5)
      l5 = l4;
    if (j5 < k5)
      k5 = j5;
    else if (j5 > l5)
      l5 = j5;
    if (k5 < sk)
      k5 = sk;
    if (l5 > tk)
      l5 = tk;
    if (cl == null || cl.length != k + 1) {
      cl = new int[k + 1];
      dl = new int[k + 1];
      el = new int[k + 1];
      fl = new int[k + 1];
      gl = new int[k + 1];
      hl = new int[k + 1];
    }
    for (int i6 = k5; i6 <= l5; i6++) {
      cl[i6] = 0x5f5e0ff;
      dl[i6] = 0xfa0a1f01;
    }

    int i7 = 0;
    int k7 = 0;
    int i8 = 0;
    int j8 = lk[arg2];
    int k8 = mk[arg2];
    i1 = 0;
    j1 = 0;
    i2 = j8 - 1;
    j2 = 0;
    k1 = j8 - 1;
    l1 = k8 - 1;
    k2 = 0;
    l2 = k8 - 1;
    if (j5 != l3) {
      i7 = (i5 - k3 << 8) / (j5 - l3);
      i8 = (l2 - j1 << 8) / (j5 - l3);
    }
    int j6;
    int k6;
    int l6;
    int l7;
    if (l3 > j5) {
      l6 = i5 << 8;
      l7 = l2 << 8;
      j6 = j5;
      k6 = l3;
    } else {
      l6 = k3 << 8;
      l7 = j1 << 8;
      j6 = l3;
      k6 = j5;
    }
    if (j6 < 0) {
      l6 -= i7 * j6;
      l7 -= i8 * j6;
      j6 = 0;
    }
    if (k6 > k - 1)
      k6 = k - 1;
    for (int l8 = j6; l8 <= k6; l8++) {
      cl[l8] = dl[l8] = l6;
      l6 += i7;
      el[l8] = fl[l8] = 0;
      gl[l8] = hl[l8] = l7;
      l7 += i8;
    }

    if (j4 != l3) {
      i7 = (i4 - k3 << 8) / (j4 - l3);
      k7 = (i2 - i1 << 8) / (j4 - l3);
    }
    int j7;
    if (l3 > j4) {
      l6 = i4 << 8;
      j7 = i2 << 8;
      j6 = j4;
      k6 = l3;
    } else {
      l6 = k3 << 8;
      j7 = i1 << 8;
      j6 = l3;
      k6 = j4;
    }
    if (j6 < 0) {
      l6 -= i7 * j6;
      j7 -= k7 * j6;
      j6 = 0;
    }
    if (k6 > k - 1)
      k6 = k - 1;
    for (int i9 = j6; i9 <= k6; i9++) {
      if (l6 < cl[i9]) {
        cl[i9] = l6;
        el[i9] = j7;
        gl[i9] = 0;
      }
      if (l6 > dl[i9]) {
        dl[i9] = l6;
        fl[i9] = j7;
        hl[i9] = 0;
      }
      l6 += i7;
      j7 += k7;
    }

    if (l4 != j4) {
      i7 = (k4 - i4 << 8) / (l4 - j4);
      i8 = (l1 - j2 << 8) / (l4 - j4);
    }
    if (j4 > l4) {
      l6 = k4 << 8;
      j7 = k1 << 8;
      l7 = l1 << 8;
      j6 = l4;
      k6 = j4;
    } else {
      l6 = i4 << 8;
      j7 = i2 << 8;
      l7 = j2 << 8;
      j6 = j4;
      k6 = l4;
    }
    if (j6 < 0) {
      l6 -= i7 * j6;
      l7 -= i8 * j6;
      j6 = 0;
    }
    if (k6 > k - 1)
      k6 = k - 1;
    for (int j9 = j6; j9 <= k6; j9++) {
      if (l6 < cl[j9]) {
        cl[j9] = l6;
        el[j9] = j7;
        gl[j9] = l7;
      }
      if (l6 > dl[j9]) {
        dl[j9] = l6;
        fl[j9] = j7;
        hl[j9] = l7;
      }
      l6 += i7;
      l7 += i8;
    }

    if (j5 != l4) {
      i7 = (i5 - k4 << 8) / (j5 - l4);
      k7 = (k2 - k1 << 8) / (j5 - l4);
    }
    if (l4 > j5) {
      l6 = i5 << 8;
      j7 = k2 << 8;
      l7 = l2 << 8;
      j6 = j5;
      k6 = l4;
    } else {
      l6 = k4 << 8;
      j7 = k1 << 8;
      l7 = l1 << 8;
      j6 = l4;
      k6 = j5;
    }
    if (j6 < 0) {
      l6 -= i7 * j6;
      j7 -= k7 * j6;
      j6 = 0;
    }
    if (k6 > k - 1)
      k6 = k - 1;
    for (int k9 = j6; k9 <= k6; k9++) {
      if (l6 < cl[k9]) {
        cl[k9] = l6;
        el[k9] = j7;
        gl[k9] = l7;
      }
      if (l6 > dl[k9]) {
        dl[k9] = l6;
        fl[k9] = j7;
        hl[k9] = l7;
      }
      l6 += i7;
      j7 += k7;
    }

    int l9 = k5 * j;
    int ai[] = ik[arg2];
    for (int i10 = k5; i10 < l5; i10++) {
      int j10 = cl[i10] >> 8;
      int k10 = dl[i10] >> 8;
      if (k10 - j10 <= 0) {
        l9 += j;
      } else {
        int l10 = el[i10] << 9;
        int i11 = ((fl[i10] << 9) - l10) / (k10 - j10);
        int j11 = gl[i10] << 9;
        int k11 = ((hl[i10] << 9) - j11) / (k10 - j10);
        if (j10 < uk) {
          l10 += (uk - j10) * i11;
          j11 += (uk - j10) * k11;
          j10 = uk;
        }
        if (k10 > vk)
          k10 = vk;
        if (!wk || (i10 & 1) == 0)
          if (!rk[arg2])
            ag(ek, ai, 0, l9 + j10, l10, j11, i11, k11, j10 - k10, j8);
          else
            eg(ek, ai, 0, l9 + j10, l10, j11, i11, k11, j10 - k10, j8);
        l9 += j;
      }
    }

  }

  private void ag(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
    for (arg2 = arg8; arg2 < 0; arg2++) {
      ek[arg3++] = arg1[(arg4 >> 17) + (arg5 >> 17) * arg9];
      arg4 += arg6;
      arg5 += arg7;
    }

  }

  private void eg(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
    for (int j = arg8; j < 0; j++) {
      arg2 = arg1[(arg4 >> 17) + (arg5 >> 17) * arg9];
      if (arg2 != 0)
        ek[arg3++] = arg2;
      else
        arg3++;
      arg4 += arg6;
      arg5 += arg7;
    }

  }

  public void dg(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
    nf(arg0, arg1, arg2, arg3, arg4);
  }

  public void wf(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, boolean arg8) {
    try {
      if (arg5 == 0)
        arg5 = 0xffffff;
      if (arg6 == 0)
        arg6 = 0xffffff;
      int j = lk[arg4];
      int k = mk[arg4];
      int l = 0;
      int i1 = 0;
      int j1 = arg7 << 16;
      int k1 = (j << 16) / arg2;
      int l1 = (k << 16) / arg3;
      int i2 = -(arg7 << 16) / arg3;
      if (rk[arg4]) {
        int j2 = pk[arg4];
        int l2 = qk[arg4];
        k1 = (j2 << 16) / arg2;
        l1 = (l2 << 16) / arg3;
        int k3 = nk[arg4];
        int l3 = ok[arg4];
        if (arg8)
          k3 = j2 - lk[arg4] - k3;
        arg0 += ((k3 * arg2 + j2) - 1) / j2;
        int i4 = ((l3 * arg3 + l2) - 1) / l2;
        arg1 += i4;
        j1 += i4 * i2;
        if ((k3 * arg2) % j2 != 0)
          l = (j2 - (k3 * arg2) % j2 << 16) / arg2;
        if ((l3 * arg3) % l2 != 0)
          i1 = (l2 - (l3 * arg3) % l2 << 16) / arg3;
        arg2 = ((((lk[arg4] << 16) - l) + k1) - 1) / k1;
        arg3 = ((((mk[arg4] << 16) - i1) + l1) - 1) / l1;
      }
      int k2 = arg1 * yj;
      j1 += arg0 << 16;
      if (arg1 < sk) {
        int i3 = sk - arg1;
        arg3 -= i3;
        arg1 = sk;
        k2 += i3 * yj;
        i1 += l1 * i3;
        j1 += i2 * i3;
      }
      if (arg1 + arg3 >= tk)
        arg3 -= ((arg1 + arg3) - tk) + 1;
      int j3 = k2 / yj & 1;
      if (!wk)
        j3 = 2;
      if (arg6 == 0xffffff) {
        if (ik[arg4] != null)
          if (!arg8) {
            dh(ek, ik[arg4], 0, l, i1, k2, arg2, arg3, k1, l1, j, arg5, j1, i2, j3);
            return;
          } else {
            dh(ek, ik[arg4], 0, (lk[arg4] << 16) - l - 1, i1, k2, arg2, arg3, -k1, l1, j, arg5, j1, i2, j3);
            return;
          }
        if (!arg8) {
          _mthif(ek, jk[arg4], kk[arg4], 0, l, i1, k2, arg2, arg3, k1, l1, j, arg5, j1, i2, j3);
          return;
        } else {
          _mthif(ek, jk[arg4], kk[arg4], 0, (lk[arg4] << 16) - l - 1, i1, k2, arg2, arg3, -k1, l1, j, arg5, j1, i2, j3);
          return;
        }
      }
      if (ik[arg4] != null)
        if (!arg8) {
          bg(ek, ik[arg4], 0, l, i1, k2, arg2, arg3, k1, l1, j, arg5, arg6, j1, i2, j3);
          return;
        } else {
          bg(ek, ik[arg4], 0, (lk[arg4] << 16) - l - 1, i1, k2, arg2, arg3, -k1, l1, j, arg5, arg6, j1, i2, j3);
          return;
        }
      if (!arg8) {
        bh(ek, jk[arg4], kk[arg4], 0, l, i1, k2, arg2, arg3, k1, l1, j, arg5, arg6, j1, i2, j3);
        return;
      } else {
        bh(ek, jk[arg4], kk[arg4], 0, (lk[arg4] << 16) - l - 1, i1, k2, arg2, arg3, -k1, l1, j, arg5, arg6, j1, i2, j3);
        return;
      }
    } catch (Exception _ex) {
      System.out.println("error in sprite clipping routine");
    }
  }

  private void dh(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
    int i1 = arg11 >> 16 & 0xff;
    int j1 = arg11 >> 8 & 0xff;
    int k1 = arg11 & 0xff;
    try {
      int l1 = arg3;
      for (int i2 = -arg7; i2 < 0; i2++) {
        int j2 = (arg4 >> 16) * arg10;
        int k2 = arg12 >> 16;
        int l2 = arg6;
        if (k2 < uk) {
          int i3 = uk - k2;
          l2 -= i3;
          k2 = uk;
          arg3 += arg8 * i3;
        }
        if (k2 + l2 >= vk) {
          int j3 = (k2 + l2) - vk;
          l2 -= j3;
        }
        arg14 = 1 - arg14;
        if (arg14 != 0) {
          for (int k3 = k2; k3 < k2 + l2; k3++) {
            arg2 = arg1[(arg3 >> 16) + j2];
            if (arg2 != 0) {
              int j = arg2 >> 16 & 0xff;
              int k = arg2 >> 8 & 0xff;
              int l = arg2 & 0xff;
              if (j == k && k == l)
                arg0[k3 + arg5] = ((j * i1 >> 8) << 16) + ((k * j1 >> 8) << 8) + (l * k1 >> 8);
              else
                arg0[k3 + arg5] = arg2;
            }
            arg3 += arg8;
          }

        }
        arg4 += arg9;
        arg3 = l1;
        arg5 += yj;
        arg12 += arg13;
      }

      return;
    } catch (Exception _ex) {
      System.out.println("error in transparent sprite plot routine");
    }
  }

  private void bg(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13, int arg14, int arg15) {
    int i1 = arg11 >> 16 & 0xff;
    int j1 = arg11 >> 8 & 0xff;
    int k1 = arg11 & 0xff;
    int l1 = arg12 >> 16 & 0xff;
    int i2 = arg12 >> 8 & 0xff;
    int j2 = arg12 & 0xff;
    try {
      int k2 = arg3;
      for (int l2 = -arg7; l2 < 0; l2++) {
        int i3 = (arg4 >> 16) * arg10;
        int j3 = arg13 >> 16;
        int k3 = arg6;
        if (j3 < uk) {
          int l3 = uk - j3;
          k3 -= l3;
          j3 = uk;
          arg3 += arg8 * l3;
        }
        if (j3 + k3 >= vk) {
          int i4 = (j3 + k3) - vk;
          k3 -= i4;
        }
        arg15 = 1 - arg15;
        if (arg15 != 0) {
          for (int j4 = j3; j4 < j3 + k3; j4++) {
            arg2 = arg1[(arg3 >> 16) + i3];
            if (arg2 != 0) {
              int j = arg2 >> 16 & 0xff;
              int k = arg2 >> 8 & 0xff;
              int l = arg2 & 0xff;
              if (j == k && k == l)
                arg0[j4 + arg5] = ((j * i1 >> 8) << 16) + ((k * j1 >> 8) << 8) + (l * k1 >> 8);
              else if (j == 255 && k == l)
                arg0[j4 + arg5] = ((j * l1 >> 8) << 16) + ((k * i2 >> 8) << 8) + (l * j2 >> 8);
              else
                arg0[j4 + arg5] = arg2;
            }
            arg3 += arg8;
          }

        }
        arg4 += arg9;
        arg3 = k2;
        arg5 += yj;
        arg13 += arg14;
      }

      return;
    } catch (Exception _ex) {
      System.out.println("error in transparent sprite plot routine");
    }
  }

  private void _mthif(int arg0[], byte arg1[], int arg2[], int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13, int arg14, int arg15) {
    int i1 = arg12 >> 16 & 0xff;
    int j1 = arg12 >> 8 & 0xff;
    int k1 = arg12 & 0xff;
    try {
      int l1 = arg4;
      for (int i2 = -arg8; i2 < 0; i2++) {
        int j2 = (arg5 >> 16) * arg11;
        int k2 = arg13 >> 16;
        int l2 = arg7;
        if (k2 < uk) {
          int i3 = uk - k2;
          l2 -= i3;
          k2 = uk;
          arg4 += arg9 * i3;
        }
        if (k2 + l2 >= vk) {
          int j3 = (k2 + l2) - vk;
          l2 -= j3;
        }
        arg15 = 1 - arg15;
        if (arg15 != 0) {
          for (int k3 = k2; k3 < k2 + l2; k3++) {
            arg3 = arg1[(arg4 >> 16) + j2] & 0xff;
            if (arg3 != 0) {
              arg3 = arg2[arg3];
              int j = arg3 >> 16 & 0xff;
              int k = arg3 >> 8 & 0xff;
              int l = arg3 & 0xff;
              if (j == k && k == l)
                arg0[k3 + arg6] = ((j * i1 >> 8) << 16) + ((k * j1 >> 8) << 8) + (l * k1 >> 8);
              else
                arg0[k3 + arg6] = arg3;
            }
            arg4 += arg9;
          }

        }
        arg5 += arg10;
        arg4 = l1;
        arg6 += yj;
        arg13 += arg14;
      }

      return;
    } catch (Exception _ex) {
      System.out.println("error in transparent sprite plot routine");
    }
  }

  private void bh(int arg0[], byte arg1[], int arg2[], int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13, int arg14, int arg15, int arg16) {
    int i1 = arg12 >> 16 & 0xff;
    int j1 = arg12 >> 8 & 0xff;
    int k1 = arg12 & 0xff;
    int l1 = arg13 >> 16 & 0xff;
    int i2 = arg13 >> 8 & 0xff;
    int j2 = arg13 & 0xff;
    try {
      int k2 = arg4;
      for (int l2 = -arg8; l2 < 0; l2++) {
        int i3 = (arg5 >> 16) * arg11;
        int j3 = arg14 >> 16;
        int k3 = arg7;
        if (j3 < uk) {
          int l3 = uk - j3;
          k3 -= l3;
          j3 = uk;
          arg4 += arg9 * l3;
        }
        if (j3 + k3 >= vk) {
          int i4 = (j3 + k3) - vk;
          k3 -= i4;
        }
        arg16 = 1 - arg16;
        if (arg16 != 0) {
          for (int j4 = j3; j4 < j3 + k3; j4++) {
            arg3 = arg1[(arg4 >> 16) + i3] & 0xff;
            if (arg3 != 0) {
              arg3 = arg2[arg3];
              int j = arg3 >> 16 & 0xff;
              int k = arg3 >> 8 & 0xff;
              int l = arg3 & 0xff;
              if (j == k && k == l)
                arg0[j4 + arg6] = ((j * i1 >> 8) << 16) + ((k * j1 >> 8) << 8) + (l * k1 >> 8);
              else if (j == 255 && k == l)
                arg0[j4 + arg6] = ((j * l1 >> 8) << 16) + ((k * i2 >> 8) << 8) + (l * j2 >> 8);
              else
                arg0[j4 + arg6] = arg3;
            }
            arg4 += arg9;
          }

        }
        arg5 += arg10;
        arg4 = k2;
        arg6 += yj;
        arg14 += arg15;
      }

      return;
    } catch (Exception _ex) {
      System.out.println("error in transparent sprite plot routine");
    }
  }

  public static int ef(byte arg0[]) {
    fonts[zk] = arg0;
    return zk++;
  }

  public void yg(String arg0, int arg1, int arg2, int arg3, int arg4) {
    drawString(arg0, arg1 - df(arg0, arg3), arg2, arg3, arg4);
  }

  public void drawTextCenter(String arg0, int arg1, int arg2, int arg3, int arg4) {
    drawString(arg0, arg1 - df(arg0, arg3) / 2, arg2, arg3, arg4);
  }

  public void ah(String arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    try {
      int j = 0;
      byte abyte0[] = fonts[arg3];
      int k = 0;
      int l = 0;
      for (int i1 = 0; i1 < arg0.length(); i1++) {
        if (arg0.charAt(i1) == '@' && i1 + 4 < arg0.length() && arg0.charAt(i1 + 4) == '@')
          i1 += 4;
        else if (arg0.charAt(i1) == '~' && i1 + 4 < arg0.length() && arg0.charAt(i1 + 4) == '~')
          i1 += 4;
        else
          j += abyte0[yk[arg0.charAt(i1)] + 7];
        if (arg0.charAt(i1) == ' ')
          l = i1;
        if (j > arg5) {
          if (l <= k)
            l = i1;
          drawTextCenter(arg0.substring(k, l), arg1, arg2, arg3, arg4);
          j = 0;
          k = i1 = l + 1;
          arg2 += ng(arg3);
        }
      }

      if (j > 0) {
        drawTextCenter(arg0.substring(k), arg1, arg2, arg3, arg4);
        return;
      }
    } catch (Exception exception) {
      System.out.println("centrepara: " + exception);
      exception.printStackTrace();
    }
  }

  public void drawString(String arg0, int arg1, int arg2, int font, int textColor) {
    try {
      byte abyte0[] = fonts[font];
      for (int j = 0; j < arg0.length(); j++)
        if (arg0.charAt(j) == '@' && j + 4 < arg0.length() && arg0.charAt(j + 4) == '@') {
          if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("red"))
            textColor = 0xff0000;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("lre"))
            textColor = 0xff9040;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("yel"))
            textColor = 0xffff00;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("gre"))
            textColor = 65280;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("blu"))
            textColor = 255;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("cya"))
            textColor = 65535;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("mag"))
            textColor = 0xff00ff;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("whi"))
            textColor = 0xffffff;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("bla"))
            textColor = 0;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("dre"))
            textColor = 0xc00000;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("ora"))
            textColor = 0xff9040;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("ran"))
            textColor = (int) (Math.random() * 16777215D);
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("or1"))
            textColor = 0xffb000;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("or2"))
            textColor = 0xff7000;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("or3"))
            textColor = 0xff3000;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("gr1"))
            textColor = 0xc0ff00;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("gr2"))
            textColor = 0x80ff00;
          else if (arg0.substring(j + 1, j + 4).equalsIgnoreCase("gr3"))
            textColor = 0x40ff00;
          j += 4;
        } else if (arg0.charAt(j) == '~' && j + 4 < arg0.length() && arg0.charAt(j + 4) == '~') {
          char c = arg0.charAt(j + 1);
          char c1 = arg0.charAt(j + 2);
          char c2 = arg0.charAt(j + 3);
          if (c >= '0' && c <= '9' && c1 >= '0' && c1 <= '9' && c2 >= '0' && c2 <= '9')
            arg1 = Integer.parseInt(arg0.substring(j + 1, j + 4));
          j += 4;
        } else {
          int k = yk[arg0.charAt(j)];
          if (al && textColor != 0)
            tg(k, arg1 + 1, arg2, 0, abyte0);
          if (al && textColor != 0)
            tg(k, arg1, arg2 + 1, 0, abyte0);
          tg(k, arg1, arg2, textColor, abyte0);
          arg1 += abyte0[k + 7];
        }

      return;
    } catch (Exception exception) {
      System.out.println("drawstring: " + exception);
      exception.printStackTrace();
      return;
    }
  }

  private void tg(int arg0, int arg1, int arg2, int arg3, byte arg4[]) {
    int j = arg1 + arg4[arg0 + 5];
    int k = arg2 - arg4[arg0 + 6];
    int l = arg4[arg0 + 3];
    int i1 = arg4[arg0 + 4];
    int j1 = arg4[arg0] * 16384 + arg4[arg0 + 1] * 128 + arg4[arg0 + 2];
    int k1 = j + k * yj;
    int l1 = yj - l;
    int i2 = 0;
    if (k < sk) {
      int j2 = sk - k;
      i1 -= j2;
      k = sk;
      j1 += j2 * l;
      k1 += j2 * yj;
    }
    if (k + i1 >= tk)
      i1 -= ((k + i1) - tk) + 1;
    if (j < uk) {
      int k2 = uk - j;
      l -= k2;
      j = uk;
      j1 += k2;
      k1 += k2;
      i2 += k2;
      l1 += k2;
    }
    if (j + l >= vk) {
      int l2 = ((j + l) - vk) + 1;
      l -= l2;
      i2 += l2;
      l1 += l2;
    }
    if (l > 0 && i1 > 0)
      cf(ek, arg4, arg3, j1, k1, l, i1, l1, i2);
  }

  // previously non static -- stormy
  private static void cf(int arg0[], byte arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8) {
    try {
      int j = -(arg5 >> 2);
      arg5 = -(arg5 & 3);
      for (int k = -arg6; k < 0; k++) {
        for (int l = j; l < 0; l++) {
          if (arg1[arg3++] != 0)
            arg0[arg4++] = arg2;
          else
            arg4++;
          if (arg1[arg3++] != 0)
            arg0[arg4++] = arg2;
          else
            arg4++;
          if (arg1[arg3++] != 0)
            arg0[arg4++] = arg2;
          else
            arg4++;
          if (arg1[arg3++] != 0)
            arg0[arg4++] = arg2;
          else
            arg4++;
        }

        for (int i1 = arg5; i1 < 0; i1++)
          if (arg1[arg3++] != 0)
            arg0[arg4++] = arg2;
          else
            arg4++;

        arg4 += arg7;
        arg3 += arg8;
      }

      return;
    } catch (Exception exception) {
      System.out.println("plotletter: " + exception);
      exception.printStackTrace();
      return;
    }
  }

  public int ng(int arg0) {
    if (arg0 == 0)
      return fonts[arg0][8] - 2;
    else
      return fonts[arg0][8] - 1;
  }

  public int df(String arg0, int arg1) {
    int j = 0;
    byte abyte0[] = fonts[arg1];
    for (int k = 0; k < arg0.length(); k++)
      if (arg0.charAt(k) == '@' && k + 4 < arg0.length() && arg0.charAt(k + 4) == '@')
        k += 4;
      else if (arg0.charAt(k) == '~' && k + 4 < arg0.length() && arg0.charAt(k + 4) == '~')
        k += 4;
      else
        j += abyte0[yk[arg0.charAt(k)] + 7];

    return j;
  }

  public boolean imageUpdate(Image arg0, int arg1, int arg2, int j, int k, int l) {
    return true;
  }

  public static final int xj = 0;
  public int yj;
  public int zj;
  public int ak;
  public int bk;
  public int ck;
  ColorModel dk;
  public int ek[];
  ImageConsumer fk;
  public Image hk;
  public int ik[][];
  public byte jk[][];
  public int kk[][];
  public int lk[];
  public int mk[];
  public int nk[];
  public int ok[];
  public int pk[];
  public int qk[];
  public boolean rk[];
  private int sk;
  private int tk;
  private int uk;
  private int vk;
  public boolean wk;
  static byte fonts[][] = new byte[50][];
  static int yk[];
  static int zk;
  public boolean al;
  int bl[];
  int cl[];
  int dl[];
  int el[];
  int fl[];
  int gl[];
  int hl[];
  public static final int il = 0;
  public static final int jl = 0xffffff;
  public static final int kl = 0xff0000;
  public static final int ll = 0xc00000;
  public static final int ml = 65280;
  public static final int nl = 255;
  public static final int ol = 0xffff00;
  public static final int pl = 65535;
  public static final int ql = 0xff00ff;
  public static final int rl = 0xc0c0c0;
  public static final int sl = 0x808080;
  public static final int tl = 0x404040;
  public static final int ul = 0xff8000;
  public static final int vl = 0;
  public static final int wl = 1;
  public static final int xl = 3;
  public static final int yl = 4;
  public static final int zl = 5;
  public static final int am = 7;

  static {
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
    yk = new int[256];
    for (int j = 0; j < 256; j++) {
      int k = s.indexOf(j);
      if (k == -1)
        k = 74;
      yk[j] = k * 9;
    }

  }
}
