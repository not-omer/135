// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex.client;

// Referenced classes of package com.jagex.client:
//			h, Graphics2D, r, t

public class Scene {
  public Scene(Surface arg0, int arg1, int arg2, int arg3) {
    cm = 50;
    dm = new int[cm];
    em = new int[cm][256];
    hm = 5;
    clipFar3D = 1000;
    clipFar2D = 1000;
    fogFalloff = 20;
    fogDistance = 10;
    om = false;
    pm = 1.1000000000000001D;
    qm = 1;
    rm = false;
    vm = 100;
    objectsUnderCursor = new Model[vm];
    playersUnderCursor = new int[vm];
    width = 512;
    clipX = 256;
    clipY = 192;
    baseX = 256;
    baseY = 256;
    viewDistance = 8;
    en = 4;
    wo = new int[40];
    xo = new int[40];
    yo = new int[40];
    zo = new int[40];
    ap = new int[40];
    bp = new int[40];
    cp = false;
    ro = arg0;
    clipX = arg0.yj / 2;
    clipY = arg0.zj / 2;
    so = arg0.ek;
    modelCount = 0;
    mn = arg1;
    models = new Model[mn];
    on = new int[mn];
    pn = 0;
    qn = new Polygon[arg2];
    for (int k = 0; k < arg2; k++)
      qn[k] = new Polygon();

    spriteCount = 0;
    view = new Model(arg3 * 2, arg3);
    spriteID = new int[arg3];
    spriteWidth = new int[arg3];
    spriteHeight = new int[arg3];
    spriteX = new int[arg3];
    spriteY = new int[arg3];
    spriteZ = new int[arg3];
    zn = new int[arg3];
    if (po == null)
      po = new byte[17691];
    fn = 0;
    gn = 0;
    hn = 0;
    in = 0;
    jn = 0;
    kn = 0;
    for (int l = 0; l < 256; l++) {
      nm[l] = (int) (Math.sin((double) l * 0.02454369D) * 32768D);
      nm[l + 256] = (int) (Math.cos((double) l * 0.02454369D) * 32768D);
    }

    for (int i1 = 0; i1 < 1024; i1++) {
      mm[i1] = (int) (Math.sin((double) i1 * 0.00613592315D) * 32768D);
      mm[i1 + 1024] = (int) (Math.cos((double) i1 * 0.00613592315D) * 32768D);
    }

  }

  public void addModel(Model model) {
    if (modelCount < mn) {
      on[modelCount] = 0;
      models[modelCount++] = model;
    }
  }

  public void removeModel(Model model) {
    for (int k = 0; k < modelCount; k++)
      if (models[k] == model) {
        modelCount--;
        for (int l = k; l < modelCount; l++) {
          models[l] = models[l + 1];
          on[l] = on[l + 1];
        }
      }
  }

  public void si() {
    bi();
    for (int k = 0; k < modelCount; k++)
      models[k] = null;

    modelCount = 0;
  }

  public void bi() {
    spriteCount = 0;
    view.xe();
  }

  public void ki(int arg0) {
    spriteCount -= arg0;
    view.le(arg0, arg0 * 2);
    if (spriteCount < 0)
      spriteCount = 0;
  }

  public int addSprite(int id, int x, int y, int z, int width, int height, int arg6) {
    spriteID[spriteCount] = id;
    spriteX[spriteCount] = x;
    spriteY[spriteCount] = y;
    spriteZ[spriteCount] = z;
    spriteWidth[spriteCount] = width;
    spriteHeight[spriteCount] = height;
    zn[spriteCount] = 0;
    int k = view.de(x, y, z);
    int l = view.de(x, y - height, z);
    int ai1[] = { k, l };
    view.ne(2, ai1, 0, 0);
    view.vh[spriteCount] = arg6;
    view.wh[spriteCount++] = 0;
    return spriteCount - 1;
  }

  public void mh(int arg0) {
    view.wh[arg0] = 1;
  }

  public void ni(int arg0, int arg1) {
    zn[arg0] = arg1;
  }

  public void wh(int arg0, int arg1) {
    sm = arg0 - baseX;
    tm = arg1;
    um = 0;
    rm = true;
  }

  public int ri() {
    return um;
  }

  public int[] getPlayersUnderCursor() {
    return playersUnderCursor;
  }

  public Model[] getObjectsUnderCursor() {
    return objectsUnderCursor;
  }

  public void ei(int bx, int by, int cx, int cy, int w, int distance) {
    clipX = cx;
    clipY = cy;
    baseX = bx;
    baseY = by;
    width = w;
    viewDistance = distance;
    scanlines = new Scanline[cy + by];
    for (int k = 0; k < cy + by; k++)
      scanlines[k] = new Scanline();
  }

  private void qh(Polygon arg0[], int arg1, int arg2) {
    if (arg1 < arg2) {
      int k = arg1 - 1;
      int l = arg2 + 1;
      int i1 = (arg1 + arg2) / 2;
      Polygon r1 = arg0[i1];
      arg0[i1] = arg0[arg1];
      arg0[arg1] = r1;
      int j1 = r1.fnb;
      while (k < l) {
        do
          l--;
        while (arg0[l].fnb < j1);
        do
          k++;
        while (arg0[k].fnb > j1);
        if (k < l) {
          Polygon r2 = arg0[k];
          arg0[k] = arg0[l];
          arg0[l] = r2;
        }
      }
      qh(arg0, arg1, l);
      qh(arg0, l + 1, arg2);
    }
  }

  public void hh(int arg0, Polygon arg1[], int arg2) {
    for (int k = 0; k <= arg2; k++) {
      arg1[k].lnb = false;
      arg1[k].mnb = k;
      arg1[k].nnb = -1;
    }

    int l = 0;
    do {
      while (arg1[l].lnb)
        l++;
      if (l == arg2)
        return;
      Polygon r1 = arg1[l];
      r1.lnb = true;
      int i1 = l;
      int j1 = l + arg0;
      if (j1 >= arg2)
        j1 = arg2 - 1;
      for (int k1 = j1; k1 >= i1 + 1; k1--) {
        Polygon r2 = arg1[k1];
        if (r1.xmb < r2.zmb && r2.xmb < r1.zmb && r1.ymb < r2.anb && r2.ymb < r1.anb && r1.mnb != r2.nnb && !ih(r1, r2)
            && gh(r2, r1)) {
          vi(arg1, i1, k1);
          if (arg1[k1] != r2)
            k1++;
          i1 = jp;
          r2.nnb = r1.mnb;
        }
      }

    } while (true);
  }

  public boolean vi(Polygon arg0[], int arg1, int arg2) {
    do {
      Polygon r1 = arg0[arg1];
      for (int k = arg1 + 1; k <= arg2; k++) {
        Polygon r2 = arg0[k];
        if (!ih(r2, r1))
          break;
        arg0[arg1] = r2;
        arg0[k] = r1;
        arg1 = k;
        if (arg1 == arg2) {
          jp = arg1;
          kp = arg1 - 1;
          return true;
        }
      }

      Polygon r3 = arg0[arg2];
      for (int l = arg2 - 1; l >= arg1; l--) {
        Polygon r4 = arg0[l];
        if (!ih(r3, r4))
          break;
        arg0[arg2] = r4;
        arg0[l] = r3;
        arg2 = l;
        if (arg1 == arg2) {
          jp = arg2 + 1;
          kp = arg2;
          return true;
        }
      }

      if (arg1 + 1 >= arg2) {
        jp = arg1;
        kp = arg2;
        return false;
      }
      if (!vi(arg0, arg1 + 1, arg2)) {
        jp = arg1;
        return false;
      }
      arg2 = kp;
    } while (true);
  }

  public void qi(int arg0, int arg1, int arg2) {
    int k = -in + 1024 & 0x3ff;
    int l = -jn + 1024 & 0x3ff;
    int i1 = -kn + 1024 & 0x3ff;
    if (i1 != 0) {
      int j1 = mm[i1];
      int i2 = mm[i1 + 1024];
      int l2 = arg1 * j1 + arg0 * i2 >> 15;
      arg1 = arg1 * i2 - arg0 * j1 >> 15;
      arg0 = l2;
    }
    if (k != 0) {
      int k1 = mm[k];
      int j2 = mm[k + 1024];
      int i3 = arg1 * j2 - arg2 * k1 >> 15;
      arg2 = arg1 * k1 + arg2 * j2 >> 15;
      arg1 = i3;
    }
    if (l != 0) {
      int l1 = mm[l];
      int k2 = mm[l + 1024];
      int j3 = arg2 * l1 + arg0 * k2 >> 15;
      arg2 = arg2 * k2 - arg0 * l1 >> 15;
      arg0 = j3;
    }
    if (arg0 < dp)
      dp = arg0;
    if (arg0 > ep)
      ep = arg0;
    if (arg1 < fp)
      fp = arg1;
    if (arg1 > gp)
      gp = arg1;
    if (arg2 < hp)
      hp = arg2;
    if (arg2 > ip)
      ip = arg2;
  }

  // TODO probably not the right name for this
  public void render() {
    cp = ro.wk;
    int k3 = clipX * clipFar3D >> viewDistance;
    int l3 = clipY * clipFar3D >> viewDistance;
    dp = 0;
    ep = 0;
    fp = 0;
    gp = 0;
    hp = 0;
    ip = 0;
    qi(-k3, -l3, clipFar3D);
    qi(-k3, l3, clipFar3D);
    qi(k3, -l3, clipFar3D);
    qi(k3, l3, clipFar3D);
    qi(-clipX, -clipY, 0);
    qi(-clipX, clipY, 0);
    qi(clipX, -clipY, 0);
    qi(clipX, clipY, 0);
    dp += fn;
    ep += fn;
    fp += gn;
    gp += gn;
    hp += hn;
    ip += hn;
    models[modelCount] = view;
    view.jh = 2;
    for (int k = 0; k < modelCount; k++)
      models[k].he(fn, gn, hn, in, jn, kn, viewDistance, hm);

    models[modelCount].he(fn, gn, hn, in, jn, kn, viewDistance, hm);
    pn = 0;
    for (int i4 = 0; i4 < modelCount; i4++) {
      Model h1 = models[i4];
      if (h1.kh) {
        for (int l = 0; l < h1.faceCount; l++) {
          int j4 = h1.faceVertexCount[l];
          int ai2[] = h1.yg[l];
          boolean flag = false;
          for (int i5 = 0; i5 < j4; i5++) {
            int k1 = h1.rg[ai2[i5]];
            if (k1 <= hm || k1 >= clipFar3D)
              continue;
            flag = true;
            break;
          }

          if (flag) {
            int j2 = 0;
            for (int i6 = 0; i6 < j4; i6++) {
              int l1 = h1.sg[ai2[i6]];
              if (l1 > -clipX)
                j2 |= 1;
              if (l1 < clipX)
                j2 |= 2;
              if (j2 == 3)
                break;
            }

            if (j2 == 3) {
              int k2 = 0;
              for (int j7 = 0; j7 < j4; j7++) {
                int i2 = h1.tg[ai2[j7]];
                if (i2 > -clipY)
                  k2 |= 1;
                if (i2 < clipY)
                  k2 |= 2;
                if (k2 == 3)
                  break;
              }

              if (k2 == 3) {
                Polygon r2 = qn[pn];
                r2.dnb = h1;
                r2.enb = l;
                xi(pn);
                int j9;
                if (r2.jnb < 0)
                  j9 = h1.faceBack[l];
                else
                  j9 = h1.faceFront[l];
                if (j9 != 0xbc614e) {
                  int l2 = 0;
                  for (int j10 = 0; j10 < j4; j10++)
                    l2 += h1.rg[ai2[j10]];

                  r2.fnb = l2 / j4 + h1.ih;
                  r2.knb = j9;
                  pn++;
                }
              }
            }
          }
        }
      }
    }

    Model h2 = view;
    if (h2.kh) {
      for (int i1 = 0; i1 < h2.faceCount; i1++) {
        int ai1[] = h2.yg[i1];
        int l4 = ai1[0];
        int j5 = h2.sg[l4];
        int j6 = h2.tg[l4];
        int k7 = h2.rg[l4];
        if (k7 > hm && k7 < clipFar2D) {
          int k8 = (spriteWidth[i1] << viewDistance) / k7;
          int k9 = (spriteHeight[i1] << viewDistance) / k7;
          if (j5 - k8 / 2 <= clipX && j5 + k8 / 2 >= -clipX && j6 - k9 <= clipY && j6 >= -clipY) {
            Polygon r3 = qn[pn];
            r3.dnb = h2;
            r3.enb = i1;
            zi(pn);
            r3.fnb = (k7 + h2.rg[ai1[1]]) / 2;
            pn++;
          }
        }
      }

    }
    if (pn == 0)
      return;
    gm = pn;
    qh(qn, 0, pn - 1);
    hh(100, qn, pn);
    for (int k4 = 0; k4 < pn; k4++) {
      Polygon r1 = qn[k4];
      Model h3 = r1.dnb;
      int j1 = r1.enb;
      if (h3 == view) {
        int ai3[] = h3.yg[j1];
        int k6 = ai3[0];
        int l7 = h3.sg[k6];
        int l8 = h3.tg[k6];
        int l9 = h3.rg[k6];
        int k10 = (spriteWidth[j1] << viewDistance) / l9;
        int i11 = (spriteHeight[j1] << viewDistance) / l9;
        int k11 = l8 - h3.tg[ai3[1]];
        int l11 = ((h3.sg[ai3[1]] - l7) * k11) / i11;
        l11 = h3.sg[ai3[1]] - l7;
        int j12 = l7 - k10 / 2;
        int l12 = (baseY + l8) - i11;
        ro.dg(j12 + baseX, l12, k10, i11, spriteID[j1], l11, (256 << viewDistance) / l9);
        if (rm && um < vm) {
          j12 += (zn[j1] << viewDistance) / l9;
          if (tm >= l12 && tm <= l12 + i11 && sm >= j12 && sm <= j12 + k10 && !h3.ai && h3.wh[j1] == 0) {
            objectsUnderCursor[um] = h3;
            playersUnderCursor[um] = j1;
            um++;
          }
        }
      } else {
        int i9 = 0;
        int l10 = 0;
        int j11 = h3.faceVertexCount[j1];
        int ai4[] = h3.yg[j1];
        if (h3.gouraudShade[j1] != 0xbc614e)
          if (r1.jnb < 0)
            l10 = h3.tj - h3.gouraudShade[j1];
          else
            l10 = h3.tj + h3.gouraudShade[j1];
        for (int i12 = 0; i12 < j11; i12++) {
          int i3 = ai4[i12];
          zo[i12] = h3.pg[i3];
          ap[i12] = h3.qg[i3];
          bp[i12] = h3.rg[i3];
          if (h3.gouraudShade[j1] == 0xbc614e)
            if (r1.jnb < 0)
              l10 = (h3.tj - h3.ug[i3]) + h3.vg[i3];
            else
              l10 = h3.tj + h3.ug[i3] + h3.vg[i3];
          if (h3.rg[i3] >= hm) {
            wo[i9] = h3.sg[i3];
            xo[i9] = h3.tg[i3];
            yo[i9] = l10;
            if (h3.rg[i3] > fogDistance)
              yo[i9] += (h3.rg[i3] - fogDistance) / fogFalloff;
            i9++;
          } else {
            int i10;
            if (i12 == 0)
              i10 = ai4[j11 - 1];
            else
              i10 = ai4[i12 - 1];
            if (h3.rg[i10] >= hm) {
              int i8 = h3.rg[i3] - h3.rg[i10];
              int k5 = h3.pg[i3] - ((h3.pg[i3] - h3.pg[i10]) * (h3.rg[i3] - hm)) / i8;
              int l6 = h3.qg[i3] - ((h3.qg[i3] - h3.qg[i10]) * (h3.rg[i3] - hm)) / i8;
              wo[i9] = (k5 << viewDistance) / hm;
              xo[i9] = (l6 << viewDistance) / hm;
              yo[i9] = l10;
              i9++;
            }
            if (i12 == j11 - 1)
              i10 = ai4[0];
            else
              i10 = ai4[i12 + 1];
            if (h3.rg[i10] >= hm) {
              int j8 = h3.rg[i3] - h3.rg[i10];
              int l5 = h3.pg[i3] - ((h3.pg[i3] - h3.pg[i10]) * (h3.rg[i3] - hm)) / j8;
              int i7 = h3.qg[i3] - ((h3.qg[i3] - h3.qg[i10]) * (h3.rg[i3] - hm)) / j8;
              wo[i9] = (l5 << viewDistance) / hm;
              xo[i9] = (i7 << viewDistance) / hm;
              yo[i9] = l10;
              i9++;
            }
          }
        }

        for (int k12 = 0; k12 < j11; k12++) {
          if (yo[k12] < 0)
            yo[k12] = 0;
          else if (yo[k12] > 255)
            yo[k12] = 255;
          if (r1.knb >= 0)
            if (io[r1.knb] == 1)
              yo[k12] <<= 9;
            else
              yo[k12] <<= 6;
        }

        fh(0, 0, 0, 0, i9, wo, xo, yo, h3, j1);
        if (vo > uo)
          ph(0, 0, j11, zo, ap, bp, r1.knb, h3);
      }
    }

    rm = false;
  }

  private void fh(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5[], int arg6[], int arg7[], Model arg8,
      int arg9) {
    if (arg4 == 3) {
      int k = arg6[0] + baseY;
      int k1 = arg6[1] + baseY;
      int k2 = arg6[2] + baseY;
      int k3 = arg5[0];
      int l4 = arg5[1];
      int j6 = arg5[2];
      int l7 = arg7[0];
      int j9 = arg7[1];
      int j10 = arg7[2];
      int j11 = (baseY + clipY) - 1;
      int l11 = 0;
      int j12 = 0;
      int l12 = 0;
      int j13 = 0;
      int l13 = 0xbc614e;
      int j14 = 0xff439eb2;
      if (k2 != k) {
        j12 = (j6 - k3 << 8) / (k2 - k);
        j13 = (j10 - l7 << 8) / (k2 - k);
        if (k < k2) {
          l11 = k3 << 8;
          l12 = l7 << 8;
          l13 = k;
          j14 = k2;
        } else {
          l11 = j6 << 8;
          l12 = j10 << 8;
          l13 = k2;
          j14 = k;
        }
        if (l13 < 0) {
          l11 -= j12 * l13;
          l12 -= j13 * l13;
          l13 = 0;
        }
        if (j14 > j11)
          j14 = j11;
      }
      int l14 = 0;
      int j15 = 0;
      int l15 = 0;
      int j16 = 0;
      int l16 = 0xbc614e;
      int j17 = 0xff439eb2;
      if (k1 != k) {
        j15 = (l4 - k3 << 8) / (k1 - k);
        j16 = (j9 - l7 << 8) / (k1 - k);
        if (k < k1) {
          l14 = k3 << 8;
          l15 = l7 << 8;
          l16 = k;
          j17 = k1;
        } else {
          l14 = l4 << 8;
          l15 = j9 << 8;
          l16 = k1;
          j17 = k;
        }
        if (l16 < 0) {
          l14 -= j15 * l16;
          l15 -= j16 * l16;
          l16 = 0;
        }
        if (j17 > j11)
          j17 = j11;
      }
      int l17 = 0;
      int j18 = 0;
      int l18 = 0;
      int j19 = 0;
      int l19 = 0xbc614e;
      int j20 = 0xff439eb2;
      if (k2 != k1) {
        j18 = (j6 - l4 << 8) / (k2 - k1);
        j19 = (j10 - j9 << 8) / (k2 - k1);
        if (k1 < k2) {
          l17 = l4 << 8;
          l18 = j9 << 8;
          l19 = k1;
          j20 = k2;
        } else {
          l17 = j6 << 8;
          l18 = j10 << 8;
          l19 = k2;
          j20 = k1;
        }
        if (l19 < 0) {
          l17 -= j18 * l19;
          l18 -= j19 * l19;
          l19 = 0;
        }
        if (j20 > j11)
          j20 = j11;
      }
      uo = l13;
      if (l16 < uo)
        uo = l16;
      if (l19 < uo)
        uo = l19;
      vo = j14;
      if (j17 > vo)
        vo = j17;
      if (j20 > vo)
        vo = j20;
      int l20 = 0;
      for (arg2 = uo; arg2 < vo; arg2++) {
        if (arg2 >= l13 && arg2 < j14) {
          arg0 = arg1 = l11;
          arg3 = l20 = l12;
          l11 += j12;
          l12 += j13;
        } else {
          arg0 = 0xa0000;
          arg1 = 0xfff60000;
        }
        if (arg2 >= l16 && arg2 < j17) {
          if (l14 < arg0) {
            arg0 = l14;
            arg3 = l15;
          }
          if (l14 > arg1) {
            arg1 = l14;
            l20 = l15;
          }
          l14 += j15;
          l15 += j16;
        }
        if (arg2 >= l19 && arg2 < j20) {
          if (l17 < arg0) {
            arg0 = l17;
            arg3 = l18;
          }
          if (l17 > arg1) {
            arg1 = l17;
            l20 = l18;
          }
          l17 += j18;
          l18 += j19;
        }
        Scanline t7 = scanlines[arg2];
        t7.rnb = arg0;
        t7.snb = arg1;
        t7.tnb = arg3;
        t7.unb = l20;
      }

      if (uo < baseY - clipY)
        uo = baseY - clipY;
    } else if (arg4 == 4) {
      int l = arg6[0] + baseY;
      int l1 = arg6[1] + baseY;
      int l2 = arg6[2] + baseY;
      int l3 = arg6[3] + baseY;
      int i5 = arg5[0];
      int k6 = arg5[1];
      int i8 = arg5[2];
      int k9 = arg5[3];
      int k10 = arg7[0];
      int k11 = arg7[1];
      int i12 = arg7[2];
      int k12 = arg7[3];
      int i13 = (baseY + clipY) - 1;
      int k13 = 0;
      int i14 = 0;
      int k14 = 0;
      int i15 = 0;
      int k15 = 0xbc614e;
      int i16 = 0xff439eb2;
      if (l3 != l) {
        i14 = (k9 - i5 << 8) / (l3 - l);
        i15 = (k12 - k10 << 8) / (l3 - l);
        if (l < l3) {
          k13 = i5 << 8;
          k14 = k10 << 8;
          k15 = l;
          i16 = l3;
        } else {
          k13 = k9 << 8;
          k14 = k12 << 8;
          k15 = l3;
          i16 = l;
        }
        if (k15 < 0) {
          k13 -= i14 * k15;
          k14 -= i15 * k15;
          k15 = 0;
        }
        if (i16 > i13)
          i16 = i13;
      }
      int k16 = 0;
      int i17 = 0;
      int k17 = 0;
      int i18 = 0;
      int k18 = 0xbc614e;
      int i19 = 0xff439eb2;
      if (l1 != l) {
        i17 = (k6 - i5 << 8) / (l1 - l);
        i18 = (k11 - k10 << 8) / (l1 - l);
        if (l < l1) {
          k16 = i5 << 8;
          k17 = k10 << 8;
          k18 = l;
          i19 = l1;
        } else {
          k16 = k6 << 8;
          k17 = k11 << 8;
          k18 = l1;
          i19 = l;
        }
        if (k18 < 0) {
          k16 -= i17 * k18;
          k17 -= i18 * k18;
          k18 = 0;
        }
        if (i19 > i13)
          i19 = i13;
      }
      int k19 = 0;
      int i20 = 0;
      int k20 = 0;
      int i21 = 0;
      int j21 = 0xbc614e;
      int k21 = 0xff439eb2;
      if (l2 != l1) {
        i20 = (i8 - k6 << 8) / (l2 - l1);
        i21 = (i12 - k11 << 8) / (l2 - l1);
        if (l1 < l2) {
          k19 = k6 << 8;
          k20 = k11 << 8;
          j21 = l1;
          k21 = l2;
        } else {
          k19 = i8 << 8;
          k20 = i12 << 8;
          j21 = l2;
          k21 = l1;
        }
        if (j21 < 0) {
          k19 -= i20 * j21;
          k20 -= i21 * j21;
          j21 = 0;
        }
        if (k21 > i13)
          k21 = i13;
      }
      int l21 = 0;
      int i22 = 0;
      int j22 = 0;
      int k22 = 0;
      int l22 = 0xbc614e;
      int i23 = 0xff439eb2;
      if (l3 != l2) {
        i22 = (k9 - i8 << 8) / (l3 - l2);
        k22 = (k12 - i12 << 8) / (l3 - l2);
        if (l2 < l3) {
          l21 = i8 << 8;
          j22 = i12 << 8;
          l22 = l2;
          i23 = l3;
        } else {
          l21 = k9 << 8;
          j22 = k12 << 8;
          l22 = l3;
          i23 = l2;
        }
        if (l22 < 0) {
          l21 -= i22 * l22;
          j22 -= k22 * l22;
          l22 = 0;
        }
        if (i23 > i13)
          i23 = i13;
      }
      uo = k15;
      if (k18 < uo)
        uo = k18;
      if (j21 < uo)
        uo = j21;
      if (l22 < uo)
        uo = l22;
      vo = i16;
      if (i19 > vo)
        vo = i19;
      if (k21 > vo)
        vo = k21;
      if (i23 > vo)
        vo = i23;
      int j23 = 0;
      for (arg2 = uo; arg2 < vo; arg2++) {
        if (arg2 >= k15 && arg2 < i16) {
          arg0 = arg1 = k13;
          arg3 = j23 = k14;
          k13 += i14;
          k14 += i15;
        } else {
          arg0 = 0xa0000;
          arg1 = 0xfff60000;
        }
        if (arg2 >= k18 && arg2 < i19) {
          if (k16 < arg0) {
            arg0 = k16;
            arg3 = k17;
          }
          if (k16 > arg1) {
            arg1 = k16;
            j23 = k17;
          }
          k16 += i17;
          k17 += i18;
        }
        if (arg2 >= j21 && arg2 < k21) {
          if (k19 < arg0) {
            arg0 = k19;
            arg3 = k20;
          }
          if (k19 > arg1) {
            arg1 = k19;
            j23 = k20;
          }
          k19 += i20;
          k20 += i21;
        }
        if (arg2 >= l22 && arg2 < i23) {
          if (l21 < arg0) {
            arg0 = l21;
            arg3 = j22;
          }
          if (l21 > arg1) {
            arg1 = l21;
            j23 = j22;
          }
          l21 += i22;
          j22 += k22;
        }
        Scanline t8 = scanlines[arg2];
        t8.rnb = arg0;
        t8.snb = arg1;
        t8.tnb = arg3;
        t8.unb = j23;
      }

      if (uo < baseY - clipY)
        uo = baseY - clipY;
    } else {
      vo = uo = arg6[0] += baseY;
      for (arg2 = 1; arg2 < arg4; arg2++) {
        int i1;
        if ((i1 = arg6[arg2] += baseY) < uo)
          uo = i1;
        else if (i1 > vo)
          vo = i1;
      }

      if (uo < baseY - clipY)
        uo = baseY - clipY;
      if (vo >= baseY + clipY)
        vo = (baseY + clipY) - 1;
      if (uo >= vo)
        return;
      for (arg2 = uo; arg2 < vo; arg2++) {
        Scanline t1 = scanlines[arg2];
        t1.rnb = 0xa0000;
        t1.snb = 0xfff60000;
      }

      int j1 = arg4 - 1;
      int i2 = arg6[0];
      int i3 = arg6[j1];
      if (i2 < i3) {
        int i4 = arg5[0] << 8;
        int j5 = (arg5[j1] - arg5[0] << 8) / (i3 - i2);
        int l6 = arg7[0] << 8;
        int j8 = (arg7[j1] - arg7[0] << 8) / (i3 - i2);
        if (i2 < 0) {
          i4 -= j5 * i2;
          l6 -= j8 * i2;
          i2 = 0;
        }
        if (i3 > vo)
          i3 = vo;
        for (arg2 = i2; arg2 <= i3; arg2++) {
          Scanline t3 = scanlines[arg2];
          t3.rnb = t3.snb = i4;
          t3.tnb = t3.unb = l6;
          i4 += j5;
          l6 += j8;
        }

      } else if (i2 > i3) {
        int j4 = arg5[j1] << 8;
        int k5 = (arg5[0] - arg5[j1] << 8) / (i2 - i3);
        int i7 = arg7[j1] << 8;
        int k8 = (arg7[0] - arg7[j1] << 8) / (i2 - i3);
        if (i3 < 0) {
          j4 -= k5 * i3;
          i7 -= k8 * i3;
          i3 = 0;
        }
        if (i2 > vo)
          i2 = vo;
        for (arg2 = i3; arg2 <= i2; arg2++) {
          Scanline t4 = scanlines[arg2];
          t4.rnb = t4.snb = j4;
          t4.tnb = t4.unb = i7;
          j4 += k5;
          i7 += k8;
        }

      }
      for (arg2 = 0; arg2 < j1; arg2++) {
        int k4 = arg2 + 1;
        int j2 = arg6[arg2];
        int j3 = arg6[k4];
        if (j2 < j3) {
          int l5 = arg5[arg2] << 8;
          int j7 = (arg5[k4] - arg5[arg2] << 8) / (j3 - j2);
          int l8 = arg7[arg2] << 8;
          int l9 = (arg7[k4] - arg7[arg2] << 8) / (j3 - j2);
          if (j2 < 0) {
            l5 -= j7 * j2;
            l8 -= l9 * j2;
            j2 = 0;
          }
          if (j3 > vo)
            j3 = vo;
          for (int l10 = j2; l10 <= j3; l10++) {
            Scanline t5 = scanlines[l10];
            if (l5 < t5.rnb) {
              t5.rnb = l5;
              t5.tnb = l8;
            }
            if (l5 > t5.snb) {
              t5.snb = l5;
              t5.unb = l8;
            }
            l5 += j7;
            l8 += l9;
          }

        } else if (j2 > j3) {
          int i6 = arg5[k4] << 8;
          int k7 = (arg5[arg2] - arg5[k4] << 8) / (j2 - j3);
          int i9 = arg7[k4] << 8;
          int i10 = (arg7[arg2] - arg7[k4] << 8) / (j2 - j3);
          if (j3 < 0) {
            i6 -= k7 * j3;
            i9 -= i10 * j3;
            j3 = 0;
          }
          if (j2 > vo)
            j2 = vo;
          for (int i11 = j3; i11 <= j2; i11++) {
            Scanline t6 = scanlines[i11];
            if (i6 < t6.rnb) {
              t6.rnb = i6;
              t6.tnb = i9;
            }
            if (i6 > t6.snb) {
              t6.snb = i6;
              t6.unb = i9;
            }
            i6 += k7;
            i9 += i10;
          }

        }
      }

      if (uo < baseY - clipY)
        uo = baseY - clipY;
    }
    if (rm && um < vm && tm >= uo && tm < vo) {
      Scanline t2 = scanlines[tm];
      if (sm >= t2.rnb >> 8 && sm <= t2.snb >> 8 && t2.rnb <= t2.snb && !arg8.ai && arg8.wh[arg9] == 0) {
        objectsUnderCursor[um] = arg8;
        playersUnderCursor[um] = arg9;
        um++;
      }
    }
  }

  private void ph(int arg0, int arg1, int arg2, int arg3[], int arg4[], int arg5[], int arg6, Model arg7) {
    if (arg6 >= 0) {
      if (arg6 >= fo)
        arg6 = 0;
      li(arg6);
      int k = arg3[0];
      int i1 = arg4[0];
      int l1 = arg5[0];
      int k2 = k - arg3[1];
      int i3 = i1 - arg4[1];
      int k3 = l1 - arg5[1];
      arg2--;
      int k5 = arg3[arg2] - k;
      int l6 = arg4[arg2] - i1;
      int i8 = arg5[arg2] - l1;
      if (io[arg6] == 1) {
        int j9 = k5 * i1 - l6 * k << 12;
        int i10 = l6 * l1 - i8 * i1 << (5 - viewDistance) + 7 + 4;
        int k10 = i8 * k - k5 * l1 << (5 - viewDistance) + 7;
        int i11 = k2 * i1 - i3 * k << 12;
        int k11 = i3 * l1 - k3 * i1 << (5 - viewDistance) + 7 + 4;
        int i12 = k3 * k - k2 * l1 << (5 - viewDistance) + 7;
        int k12 = i3 * k5 - k2 * l6 << 5;
        int i13 = k3 * l6 - i3 * i8 << (5 - viewDistance) + 4;
        int k13 = k2 * i8 - k3 * k5 >> viewDistance - 5;
        int i14 = i10 >> 4;
        int k14 = k11 >> 4;
        int i15 = i13 >> 4;
        int k15 = uo - baseY;
        int i16 = width;
        int k16 = baseX + uo * i16;
        byte byte1 = 1;
        j9 += k10 * k15;
        i11 += i12 * k15;
        k12 += k13 * k15;
        if (cp) {
          if ((uo & 1) == 1) {
            uo++;
            j9 += k10;
            i11 += i12;
            k12 += k13;
            k16 += i16;
          }
          k10 <<= 1;
          i12 <<= 1;
          k13 <<= 1;
          i16 <<= 1;
          byte1 = 2;
        }
        if (arg7.sh) {
          for (arg0 = uo; arg0 < vo; arg0 += byte1) {
            Scanline t4 = scanlines[arg0];
            arg1 = t4.rnb >> 8;
            int i17 = t4.snb >> 8;
            int i20 = i17 - arg1;
            if (i20 <= 0) {
              j9 += k10;
              i11 += i12;
              k12 += k13;
              k16 += i16;
            } else {
              int k21 = t4.tnb;
              int i23 = (t4.unb - k21) / i20;
              if (arg1 < -clipX) {
                k21 += (-clipX - arg1) * i23;
                arg1 = -clipX;
                i20 = i17 - arg1;
              }
              if (i17 > clipX) {
                int j17 = clipX;
                i20 = j17 - arg1;
              }
              oi(so, ko[arg6], 0, 0, j9 + i14 * arg1, i11 + k14 * arg1, k12 + i15 * arg1, i10, k11, i13, i20,
                  k16 + arg1, k21, i23 << 2);
              j9 += k10;
              i11 += i12;
              k12 += k13;
              k16 += i16;
            }
          }

          return;
        }
        if (!lo[arg6]) {
          for (arg0 = uo; arg0 < vo; arg0 += byte1) {
            Scanline t5 = scanlines[arg0];
            arg1 = t5.rnb >> 8;
            int k17 = t5.snb >> 8;
            int j20 = k17 - arg1;
            if (j20 <= 0) {
              j9 += k10;
              i11 += i12;
              k12 += k13;
              k16 += i16;
            } else {
              int l21 = t5.tnb;
              int j23 = (t5.unb - l21) / j20;
              if (arg1 < -clipX) {
                l21 += (-clipX - arg1) * j23;
                arg1 = -clipX;
                j20 = k17 - arg1;
              }
              if (k17 > clipX) {
                int l17 = clipX;
                j20 = l17 - arg1;
              }
              th(so, ko[arg6], 0, 0, j9 + i14 * arg1, i11 + k14 * arg1, k12 + i15 * arg1, i10, k11, i13, j20,
                  k16 + arg1, l21, j23 << 2);
              j9 += k10;
              i11 += i12;
              k12 += k13;
              k16 += i16;
            }
          }

          return;
        }
        for (arg0 = uo; arg0 < vo; arg0 += byte1) {
          Scanline t6 = scanlines[arg0];
          arg1 = t6.rnb >> 8;
          int i18 = t6.snb >> 8;
          int k20 = i18 - arg1;
          if (k20 <= 0) {
            j9 += k10;
            i11 += i12;
            k12 += k13;
            k16 += i16;
          } else {
            int i22 = t6.tnb;
            int k23 = (t6.unb - i22) / k20;
            if (arg1 < -clipX) {
              i22 += (-clipX - arg1) * k23;
              arg1 = -clipX;
              k20 = i18 - arg1;
            }
            if (i18 > clipX) {
              int j18 = clipX;
              k20 = j18 - arg1;
            }
            ii(so, 0, 0, 0, ko[arg6], j9 + i14 * arg1, i11 + k14 * arg1, k12 + i15 * arg1, i10, k11, i13, k20,
                k16 + arg1, i22, k23);
            j9 += k10;
            i11 += i12;
            k12 += k13;
            k16 += i16;
          }
        }

        return;
      }
      int k9 = k5 * i1 - l6 * k << 11;
      int j10 = l6 * l1 - i8 * i1 << (5 - viewDistance) + 6 + 4;
      int l10 = i8 * k - k5 * l1 << (5 - viewDistance) + 6;
      int j11 = k2 * i1 - i3 * k << 11;
      int l11 = i3 * l1 - k3 * i1 << (5 - viewDistance) + 6 + 4;
      int j12 = k3 * k - k2 * l1 << (5 - viewDistance) + 6;
      int l12 = i3 * k5 - k2 * l6 << 5;
      int j13 = k3 * l6 - i3 * i8 << (5 - viewDistance) + 4;
      int l13 = k2 * i8 - k3 * k5 >> viewDistance - 5;
      int j14 = j10 >> 4;
      int l14 = l11 >> 4;
      int j15 = j13 >> 4;
      int l15 = uo - baseY;
      int j16 = width;
      int l16 = baseX + uo * j16;
      byte byte2 = 1;
      k9 += l10 * l15;
      j11 += j12 * l15;
      l12 += l13 * l15;
      if (cp) {
        if ((uo & 1) == 1) {
          uo++;
          k9 += l10;
          j11 += j12;
          l12 += l13;
          l16 += j16;
        }
        l10 <<= 1;
        j12 <<= 1;
        l13 <<= 1;
        j16 <<= 1;
        byte2 = 2;
      }
      if (arg7.sh) {
        for (arg0 = uo; arg0 < vo; arg0 += byte2) {
          Scanline t7 = scanlines[arg0];
          arg1 = t7.rnb >> 8;
          int k18 = t7.snb >> 8;
          int l20 = k18 - arg1;
          if (l20 <= 0) {
            k9 += l10;
            j11 += j12;
            l12 += l13;
            l16 += j16;
          } else {
            int j22 = t7.tnb;
            int l23 = (t7.unb - j22) / l20;
            if (arg1 < -clipX) {
              j22 += (-clipX - arg1) * l23;
              arg1 = -clipX;
              l20 = k18 - arg1;
            }
            if (k18 > clipX) {
              int l18 = clipX;
              l20 = l18 - arg1;
            }
            xh(so, ko[arg6], 0, 0, k9 + j14 * arg1, j11 + l14 * arg1, l12 + j15 * arg1, j10, l11, j13, l20, l16 + arg1,
                j22, l23);
            k9 += l10;
            j11 += j12;
            l12 += l13;
            l16 += j16;
          }
        }

        return;
      }
      if (!lo[arg6]) {
        for (arg0 = uo; arg0 < vo; arg0 += byte2) {
          Scanline t8 = scanlines[arg0];
          arg1 = t8.rnb >> 8;
          int i19 = t8.snb >> 8;
          int i21 = i19 - arg1;
          if (i21 <= 0) {
            k9 += l10;
            j11 += j12;
            l12 += l13;
            l16 += j16;
          } else {
            int k22 = t8.tnb;
            int i24 = (t8.unb - k22) / i21;
            if (arg1 < -clipX) {
              k22 += (-clipX - arg1) * i24;
              arg1 = -clipX;
              i21 = i19 - arg1;
            }
            if (i19 > clipX) {
              int j19 = clipX;
              i21 = j19 - arg1;
            }
            pi(so, ko[arg6], 0, 0, k9 + j14 * arg1, j11 + l14 * arg1, l12 + j15 * arg1, j10, l11, j13, i21, l16 + arg1,
                k22, i24);
            k9 += l10;
            j11 += j12;
            l12 += l13;
            l16 += j16;
          }
        }

        return;
      }
      for (arg0 = uo; arg0 < vo; arg0 += byte2) {
        Scanline t9 = scanlines[arg0];
        arg1 = t9.rnb >> 8;
        int k19 = t9.snb >> 8;
        int j21 = k19 - arg1;
        if (j21 <= 0) {
          k9 += l10;
          j11 += j12;
          l12 += l13;
          l16 += j16;
        } else {
          int l22 = t9.tnb;
          int j24 = (t9.unb - l22) / j21;
          if (arg1 < -clipX) {
            l22 += (-clipX - arg1) * j24;
            arg1 = -clipX;
            j21 = k19 - arg1;
          }
          if (k19 > clipX) {
            int l19 = clipX;
            j21 = l19 - arg1;
          }
          sh(so, 0, 0, 0, ko[arg6], k9 + j14 * arg1, j11 + l14 * arg1, l12 + j15 * arg1, j10, l11, j13, j21, l16 + arg1,
              l22, j24);
          k9 += l10;
          j11 += j12;
          l12 += l13;
          l16 += j16;
        }
      }

      return;
    }
    for (int l = 0; l < cm; l++) {
      if (dm[l] == arg6) {
        fm = em[l];
        break;
      }
      if (l == cm - 1) {
        int j1 = (int) (Math.random() * (double) cm);
        dm[j1] = arg6;
        arg6 = -1 - arg6;
        int i2 = (arg6 >> 10 & 0x1f) * 8;
        int l2 = (arg6 >> 5 & 0x1f) * 8;
        int j3 = (arg6 & 0x1f) * 8;
        for (int l3 = 0; l3 < 256; l3++) {
          int l5 = l3 * l3;
          int i7 = (i2 * l5) / 0x10000;
          int j8 = (l2 * l5) / 0x10000;
          int l9 = (j3 * l5) / 0x10000;
          em[j1][255 - l3] = (i7 << 16) + (j8 << 8) + l9;
        }

        fm = em[j1];
      }
    }

    int k1 = width;
    int j2 = baseX + uo * k1;
    byte byte0 = 1;
    if (cp) {
      if ((uo & 1) == 1) {
        uo++;
        j2 += k1;
      }
      k1 <<= 1;
      byte0 = 2;
    }
    if (arg7.th) {
      for (arg0 = uo; arg0 < vo; arg0 += byte0) {
        Scanline t1 = scanlines[arg0];
        arg1 = t1.rnb >> 8;
        int i4 = t1.snb >> 8;
        int i6 = i4 - arg1;
        if (i6 <= 0) {
          j2 += k1;
        } else {
          int j7 = t1.tnb;
          int k8 = (t1.unb - j7) / i6;
          if (arg1 < -clipX) {
            j7 += (-clipX - arg1) * k8;
            arg1 = -clipX;
            i6 = i4 - arg1;
          }
          if (i4 > clipX) {
            int j4 = clipX;
            i6 = j4 - arg1;
          }
          hi(so, -i6, j2 + arg1, 0, fm, j7, k8);
          j2 += k1;
        }
      }

      return;
    }
    if (om) {
      for (arg0 = uo; arg0 < vo; arg0 += byte0) {
        Scanline t2 = scanlines[arg0];
        arg1 = t2.rnb >> 8;
        int k4 = t2.snb >> 8;
        int j6 = k4 - arg1;
        if (j6 <= 0) {
          j2 += k1;
        } else {
          int k7 = t2.tnb;
          int l8 = (t2.unb - k7) / j6;
          if (arg1 < -clipX) {
            k7 += (-clipX - arg1) * l8;
            arg1 = -clipX;
            j6 = k4 - arg1;
          }
          if (k4 > clipX) {
            int l4 = clipX;
            j6 = l4 - arg1;
          }
          jh(so, -j6, j2 + arg1, 0, fm, k7, l8);
          j2 += k1;
        }
      }

      return;
    }
    for (arg0 = uo; arg0 < vo; arg0 += byte0) {
      Scanline t3 = scanlines[arg0];
      arg1 = t3.rnb >> 8;
      int i5 = t3.snb >> 8;
      int k6 = i5 - arg1;
      if (k6 <= 0) {
        j2 += k1;
      } else {
        int l7 = t3.tnb;
        int i9 = (t3.unb - l7) / k6;
        if (arg1 < -clipX) {
          l7 += (-clipX - arg1) * i9;
          arg1 = -clipX;
          k6 = i5 - arg1;
        }
        if (i5 > clipX) {
          int j5 = clipX;
          k6 = j5 - arg1;
        }
        yh(so, -k6, j2 + arg1, 0, fm, l7, i9);
        j2 += k1;
      }
    }

  }

  private static void th(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13) {
    if (arg10 <= 0)
      return;
    int k = 0;
    int l = 0;
    int k1 = 0;
    if (arg6 != 0) {
      arg2 = arg4 / arg6 << 7;
      arg3 = arg5 / arg6 << 7;
    }
    if (arg2 < 0)
      arg2 = 0;
    else if (arg2 > 16256)
      arg2 = 16256;
    arg4 += arg7;
    arg5 += arg8;
    arg6 += arg9;
    if (arg6 != 0) {
      k = arg4 / arg6 << 7;
      l = arg5 / arg6 << 7;
    }
    if (k < 0)
      k = 0;
    else if (k > 16256)
      k = 16256;
    int i1 = k - arg2 >> 4;
    int j1 = l - arg3 >> 4;
    for (int l1 = arg10 >> 4; l1 > 0; l1--) {
      arg2 += arg12 & 0x600000;
      k1 = arg12 >> 23;
      arg12 += arg13;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg2 = (arg2 & 0x3fff) + (arg12 & 0x600000);
      k1 = arg12 >> 23;
      arg12 += arg13;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg2 = (arg2 & 0x3fff) + (arg12 & 0x600000);
      k1 = arg12 >> 23;
      arg12 += arg13;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg2 = (arg2 & 0x3fff) + (arg12 & 0x600000);
      k1 = arg12 >> 23;
      arg12 += arg13;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 = k;
      arg3 = l;
      arg4 += arg7;
      arg5 += arg8;
      arg6 += arg9;
      if (arg6 != 0) {
        k = arg4 / arg6 << 7;
        l = arg5 / arg6 << 7;
      }
      if (k < 0)
        k = 0;
      else if (k > 16256)
        k = 16256;
      i1 = k - arg2 >> 4;
      j1 = l - arg3 >> 4;
    }

    for (int i2 = 0; i2 < (arg10 & 0xf); i2++) {
      if ((i2 & 3) == 0) {
        arg2 = (arg2 & 0x3fff) + (arg12 & 0x600000);
        k1 = arg12 >> 23;
        arg12 += arg13;
      }
      arg0[arg11++] = arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1;
      arg2 += i1;
      arg3 += j1;
    }

  }

  private static void oi(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13) {
    if (arg10 <= 0)
      return;
    int k = 0;
    int l = 0;
    int k1 = 0;
    if (arg6 != 0) {
      arg2 = arg4 / arg6 << 7;
      arg3 = arg5 / arg6 << 7;
    }
    if (arg2 < 0)
      arg2 = 0;
    else if (arg2 > 16256)
      arg2 = 16256;
    arg4 += arg7;
    arg5 += arg8;
    arg6 += arg9;
    if (arg6 != 0) {
      k = arg4 / arg6 << 7;
      l = arg5 / arg6 << 7;
    }
    if (k < 0)
      k = 0;
    else if (k > 16256)
      k = 16256;
    int i1 = k - arg2 >> 4;
    int j1 = l - arg3 >> 4;
    for (int l1 = arg10 >> 4; l1 > 0; l1--) {
      arg2 += arg12 & 0x600000;
      k1 = arg12 >> 23;
      arg12 += arg13;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg2 = (arg2 & 0x3fff) + (arg12 & 0x600000);
      k1 = arg12 >> 23;
      arg12 += arg13;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg2 = (arg2 & 0x3fff) + (arg12 & 0x600000);
      k1 = arg12 >> 23;
      arg12 += arg13;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg2 = (arg2 & 0x3fff) + (arg12 & 0x600000);
      k1 = arg12 >> 23;
      arg12 += arg13;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 = k;
      arg3 = l;
      arg4 += arg7;
      arg5 += arg8;
      arg6 += arg9;
      if (arg6 != 0) {
        k = arg4 / arg6 << 7;
        l = arg5 / arg6 << 7;
      }
      if (k < 0)
        k = 0;
      else if (k > 16256)
        k = 16256;
      i1 = k - arg2 >> 4;
      j1 = l - arg3 >> 4;
    }

    for (int i2 = 0; i2 < (arg10 & 0xf); i2++) {
      if ((i2 & 3) == 0) {
        arg2 = (arg2 & 0x3fff) + (arg12 & 0x600000);
        k1 = arg12 >> 23;
        arg12 += arg13;
      }
      arg0[arg11++] = (arg1[(arg3 & 0x3f80) + (arg2 >> 7)] >>> k1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      arg2 += i1;
      arg3 += j1;
    }

  }

  private static void ii(int arg0[], int arg1, int arg2, int arg3, int arg4[], int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
    if (arg11 <= 0)
      return;
    int k = 0;
    int l = 0;
    arg14 <<= 2;
    if (arg7 != 0) {
      k = arg5 / arg7 << 7;
      l = arg6 / arg7 << 7;
    }
    if (k < 0)
      k = 0;
    else if (k > 16256)
      k = 16256;
    for (int k1 = arg11; k1 > 0; k1 -= 16) {
      arg5 += arg8;
      arg6 += arg9;
      arg7 += arg10;
      arg2 = k;
      arg3 = l;
      if (arg7 != 0) {
        k = arg5 / arg7 << 7;
        l = arg6 / arg7 << 7;
      }
      if (k < 0)
        k = 0;
      else if (k > 16256)
        k = 16256;
      int i1 = k - arg2 >> 4;
      int j1 = l - arg3 >> 4;
      int l1 = arg13 >> 23;
      arg2 += arg13 & 0x600000;
      arg13 += arg14;
      if (k1 < 16) {
        for (int i2 = 0; i2 < k1; i2++) {
          if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
            arg0[arg12] = arg1;
          arg12++;
          arg2 += i1;
          arg3 += j1;
          if ((i2 & 3) == 3) {
            arg2 = (arg2 & 0x3fff) + (arg13 & 0x600000);
            l1 = arg13 >> 23;
            arg13 += arg14;
          }
        }

      } else {
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0x3fff) + (arg13 & 0x600000);
        l1 = arg13 >> 23;
        arg13 += arg14;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0x3fff) + (arg13 & 0x600000);
        l1 = arg13 >> 23;
        arg13 += arg14;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0x3fff) + (arg13 & 0x600000);
        l1 = arg13 >> 23;
        arg13 += arg14;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0x3f80) + (arg2 >> 7)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
      }
    }

  }

  private static void pi(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13) {
    if (arg10 <= 0)
      return;
    int k = 0;
    int l = 0;
    arg13 <<= 2;
    if (arg6 != 0) {
      k = arg4 / arg6 << 6;
      l = arg5 / arg6 << 6;
    }
    if (k < 0)
      k = 0;
    else if (k > 4032)
      k = 4032;
    for (int k1 = arg10; k1 > 0; k1 -= 16) {
      arg4 += arg7;
      arg5 += arg8;
      arg6 += arg9;
      arg2 = k;
      arg3 = l;
      if (arg6 != 0) {
        k = arg4 / arg6 << 6;
        l = arg5 / arg6 << 6;
      }
      if (k < 0)
        k = 0;
      else if (k > 4032)
        k = 4032;
      int i1 = k - arg2 >> 4;
      int j1 = l - arg3 >> 4;
      int l1 = arg12 >> 20;
      arg2 += arg12 & 0xc0000;
      arg12 += arg13;
      if (k1 < 16) {
        for (int i2 = 0; i2 < k1; i2++) {
          arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
          arg2 += i1;
          arg3 += j1;
          if ((i2 & 3) == 3) {
            arg2 = (arg2 & 0xfff) + (arg12 & 0xc0000);
            l1 = arg12 >> 20;
            arg12 += arg13;
          }
        }

      } else {
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0xfff) + (arg12 & 0xc0000);
        l1 = arg12 >> 20;
        arg12 += arg13;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0xfff) + (arg12 & 0xc0000);
        l1 = arg12 >> 20;
        arg12 += arg13;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0xfff) + (arg12 & 0xc0000);
        l1 = arg12 >> 20;
        arg12 += arg13;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1;
      }
    }

  }

  private static void xh(int arg0[], int arg1[], int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13) {
    if (arg10 <= 0)
      return;
    int k = 0;
    int l = 0;
    arg13 <<= 2;
    if (arg6 != 0) {
      k = arg4 / arg6 << 6;
      l = arg5 / arg6 << 6;
    }
    if (k < 0)
      k = 0;
    else if (k > 4032)
      k = 4032;
    for (int k1 = arg10; k1 > 0; k1 -= 16) {
      arg4 += arg7;
      arg5 += arg8;
      arg6 += arg9;
      arg2 = k;
      arg3 = l;
      if (arg6 != 0) {
        k = arg4 / arg6 << 6;
        l = arg5 / arg6 << 6;
      }
      if (k < 0)
        k = 0;
      else if (k > 4032)
        k = 4032;
      int i1 = k - arg2 >> 4;
      int j1 = l - arg3 >> 4;
      int l1 = arg12 >> 20;
      arg2 += arg12 & 0xc0000;
      arg12 += arg13;
      if (k1 < 16) {
        for (int i2 = 0; i2 < k1; i2++) {
          arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
          arg2 += i1;
          arg3 += j1;
          if ((i2 & 3) == 3) {
            arg2 = (arg2 & 0xfff) + (arg12 & 0xc0000);
            l1 = arg12 >> 20;
            arg12 += arg13;
          }
        }

      } else {
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0xfff) + (arg12 & 0xc0000);
        l1 = arg12 >> 20;
        arg12 += arg13;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0xfff) + (arg12 & 0xc0000);
        l1 = arg12 >> 20;
        arg12 += arg13;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0xfff) + (arg12 & 0xc0000);
        l1 = arg12 >> 20;
        arg12 += arg13;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
        arg2 += i1;
        arg3 += j1;
        arg0[arg11++] = (arg1[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) + (arg0[arg11] >> 1 & 0x7f7f7f);
      }
    }

  }

  private static void sh(int arg0[], int arg1, int arg2, int arg3, int arg4[], int arg5, int arg6, int arg7, int arg8,
      int arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
    if (arg11 <= 0)
      return;
    int k = 0;
    int l = 0;
    arg14 <<= 2;
    if (arg7 != 0) {
      k = arg5 / arg7 << 6;
      l = arg6 / arg7 << 6;
    }
    if (k < 0)
      k = 0;
    else if (k > 4032)
      k = 4032;
    for (int k1 = arg11; k1 > 0; k1 -= 16) {
      arg5 += arg8;
      arg6 += arg9;
      arg7 += arg10;
      arg2 = k;
      arg3 = l;
      if (arg7 != 0) {
        k = arg5 / arg7 << 6;
        l = arg6 / arg7 << 6;
      }
      if (k < 0)
        k = 0;
      else if (k > 4032)
        k = 4032;
      int i1 = k - arg2 >> 4;
      int j1 = l - arg3 >> 4;
      int l1 = arg13 >> 20;
      arg2 += arg13 & 0xc0000;
      arg13 += arg14;
      if (k1 < 16) {
        for (int i2 = 0; i2 < k1; i2++) {
          if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
            arg0[arg12] = arg1;
          arg12++;
          arg2 += i1;
          arg3 += j1;
          if ((i2 & 3) == 3) {
            arg2 = (arg2 & 0xfff) + (arg13 & 0xc0000);
            l1 = arg13 >> 20;
            arg13 += arg14;
          }
        }

      } else {
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0xfff) + (arg13 & 0xc0000);
        l1 = arg13 >> 20;
        arg13 += arg14;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0xfff) + (arg13 & 0xc0000);
        l1 = arg13 >> 20;
        arg13 += arg14;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        arg2 = (arg2 & 0xfff) + (arg13 & 0xc0000);
        l1 = arg13 >> 20;
        arg13 += arg14;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
        arg2 += i1;
        arg3 += j1;
        if ((arg1 = arg4[(arg3 & 0xfc0) + (arg2 >> 6)] >>> l1) != 0)
          arg0[arg12] = arg1;
        arg12++;
      }
    }

  }

  private static void jh(int arg0[], int arg1, int arg2, int arg3, int arg4[], int arg5, int arg6) {
    if (arg1 >= 0)
      return;
    arg6 <<= 1;
    arg3 = arg4[arg5 >> 8 & 0xff];
    arg5 += arg6;
    int k = arg1 / 8;
    for (int l = k; l < 0; l++) {
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
    }

    k = -(arg1 % 8);
    for (int i1 = 0; i1 < k; i1++) {
      arg0[arg2++] = arg3;
      if ((i1 & 1) == 1) {
        arg3 = arg4[arg5 >> 8 & 0xff];
        arg5 += arg6;
      }
    }

  }

  private static void hi(int arg0[], int arg1, int arg2, int arg3, int arg4[], int arg5, int arg6) {
    if (arg1 >= 0)
      return;
    arg6 <<= 2;
    arg3 = arg4[arg5 >> 8 & 0xff];
    arg5 += arg6;
    int k = arg1 / 16;
    for (int l = k; l < 0; l++) {
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
    }

    k = -(arg1 % 16);
    for (int i1 = 0; i1 < k; i1++) {
      arg0[arg2++] = arg3 + (arg0[arg2] >> 1 & 0x7f7f7f);
      if ((i1 & 3) == 3) {
        arg3 = arg4[arg5 >> 8 & 0xff];
        arg5 += arg6;
        arg5 += arg6;
      }
    }

  }

  private static void yh(int arg0[], int arg1, int arg2, int arg3, int arg4[], int arg5, int arg6) {
    if (arg1 >= 0)
      return;
    arg6 <<= 2;
    arg3 = arg4[arg5 >> 8 & 0xff];
    arg5 += arg6;
    int k = arg1 / 16;
    for (int l = k; l < 0; l++) {
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg0[arg2++] = arg3;
      arg3 = arg4[arg5 >> 8 & 0xff];
      arg5 += arg6;
    }

    k = -(arg1 % 16);
    for (int i1 = 0; i1 < k; i1++) {
      arg0[arg2++] = arg3;
      if ((i1 & 3) == 3) {
        arg3 = arg4[arg5 >> 8 & 0xff];
        arg5 += arg6;
      }
    }
  }

  public void setCamera(int targetX, int targetY, int targetZ, int yaw, int pitch, int roll, int distance) {
    yaw &= 0x3ff;
    pitch &= 0x3ff;
    roll &= 0x3ff;
    in = 1024 - yaw & 0x3ff;
    jn = 1024 - pitch & 0x3ff;
    kn = 1024 - roll & 0x3ff;
    int k = 0;
    int l = 0;
    int i1 = distance;
    if (yaw != 0) {
      int j1 = mm[yaw];
      int i2 = mm[yaw + 1024];
      int l2 = l * i2 - i1 * j1 >> 15;
      i1 = l * j1 + i1 * i2 >> 15;
      l = l2;
    }
    if (pitch != 0) {
      int k1 = mm[pitch];
      int j2 = mm[pitch + 1024];
      int i3 = i1 * k1 + k * j2 >> 15;
      i1 = i1 * j2 - k * k1 >> 15;
      k = i3;
    }
    if (roll != 0) {
      int l1 = mm[roll];
      int k2 = mm[roll + 1024];
      int j3 = l * l1 + k * k2 >> 15;
      l = l * k2 - k * l1 >> 15;
      k = j3;
    }
    fn = targetX - k;
    gn = targetY - l;
    hn = targetZ - i1;
  }

  private void xi(int arg0) {
    Polygon r1 = qn[arg0];
    Model h1 = r1.dnb;
    int k = r1.enb;
    int ai1[] = h1.yg[k];
    int l = h1.faceVertexCount[k];
    int i1 = h1.ch[k];
    int k1 = h1.pg[ai1[0]];
    int l1 = h1.qg[ai1[0]];
    int i2 = h1.rg[ai1[0]];
    int j2 = h1.pg[ai1[1]] - k1;
    int k2 = h1.qg[ai1[1]] - l1;
    int l2 = h1.rg[ai1[1]] - i2;
    int i3 = h1.pg[ai1[2]] - k1;
    int j3 = h1.qg[ai1[2]] - l1;
    int k3 = h1.rg[ai1[2]] - i2;
    int l3 = k2 * k3 - j3 * l2;
    int i4 = l2 * i3 - k3 * j2;
    int j4 = j2 * j3 - i3 * k2;
    if (i1 == -1) {
      i1 = 0;
      for (; l3 > 25000 || i4 > 25000 || j4 > 25000 || l3 < -25000 || i4 < -25000 || j4 < -25000; j4 >>= 1) {
        i1++;
        l3 >>= 1;
        i4 >>= 1;
      }

      h1.ch[k] = i1;
      h1.bh[k] = (int) ((double) en * Math.sqrt(l3 * l3 + i4 * i4 + j4 * j4));
    } else {
      l3 >>= i1;
      i4 >>= i1;
      j4 >>= i1;
    }
    r1.jnb = k1 * l3 + l1 * i4 + i2 * j4;
    r1.gnb = l3;
    r1.hnb = i4;
    r1.inb = j4;
    int k4 = h1.rg[ai1[0]];
    int l4 = k4;
    int i5 = h1.sg[ai1[0]];
    int j5 = i5;
    int k5 = h1.tg[ai1[0]];
    int l5 = k5;
    for (int i6 = 1; i6 < l; i6++) {
      int j1 = h1.rg[ai1[i6]];
      if (j1 > l4)
        l4 = j1;
      else if (j1 < k4)
        k4 = j1;
      j1 = h1.sg[ai1[i6]];
      if (j1 > j5)
        j5 = j1;
      else if (j1 < i5)
        i5 = j1;
      j1 = h1.tg[ai1[i6]];
      if (j1 > l5)
        l5 = j1;
      else if (j1 < k5)
        k5 = j1;
    }

    r1.bnb = k4;
    r1.cnb = l4;
    r1.xmb = i5;
    r1.zmb = j5;
    r1.ymb = k5;
    r1.anb = l5;
  }

  private void zi(int arg0) {
    Polygon r1 = qn[arg0];
    Model h1 = r1.dnb;
    int k = r1.enb;
    int ai1[] = h1.yg[k];
    int i1 = 0;
    int j1 = 0;
    int k1 = 1;
    int l1 = h1.pg[ai1[0]];
    int i2 = h1.qg[ai1[0]];
    int j2 = h1.rg[ai1[0]];
    h1.bh[k] = 1;
    h1.ch[k] = 0;
    r1.jnb = l1 * i1 + i2 * j1 + j2 * k1;
    r1.gnb = i1;
    r1.hnb = j1;
    r1.inb = k1;
    int k2 = h1.rg[ai1[0]];
    int l2 = k2;
    int i3 = h1.sg[ai1[0]];
    int j3 = i3;
    if (h1.sg[ai1[1]] < i3)
      i3 = h1.sg[ai1[1]];
    else
      j3 = h1.sg[ai1[1]];
    int k3 = h1.tg[ai1[1]];
    int l3 = h1.tg[ai1[0]];
    int l = h1.rg[ai1[1]];
    if (l > l2)
      l2 = l;
    else if (l < k2)
      k2 = l;
    l = h1.sg[ai1[1]];
    if (l > j3)
      j3 = l;
    else if (l < i3)
      i3 = l;
    l = h1.tg[ai1[1]];
    if (l > l3)
      l3 = l;
    else if (l < k3)
      k3 = l;
    r1.bnb = k2;
    r1.cnb = l2;
    r1.xmb = i3 - 20;
    r1.zmb = j3 + 20;
    r1.ymb = k3;
    r1.anb = l3;
  }

  private boolean ih(Polygon arg0, Polygon arg1) {
    if (arg0.xmb >= arg1.zmb)
      return true;
    if (arg1.xmb >= arg0.zmb)
      return true;
    if (arg0.ymb >= arg1.anb)
      return true;
    if (arg1.ymb >= arg0.anb)
      return true;
    if (arg0.bnb >= arg1.cnb)
      return true;
    if (arg1.bnb > arg0.cnb)
      return false;
    Model h1 = arg0.dnb;
    Model h2 = arg1.dnb;
    int k = arg0.enb;
    int l = arg1.enb;
    int ai1[] = h1.yg[k];
    int ai2[] = h2.yg[l];
    int i1 = h1.faceVertexCount[k];
    int j1 = h2.faceVertexCount[l];
    int i3 = h2.pg[ai2[0]];
    int j3 = h2.qg[ai2[0]];
    int k3 = h2.rg[ai2[0]];
    int l3 = arg1.gnb;
    int i4 = arg1.hnb;
    int j4 = arg1.inb;
    int k4 = h2.bh[l];
    int l4 = arg1.jnb;
    boolean flag = false;
    for (int i5 = 0; i5 < i1; i5++) {
      int k1 = ai1[i5];
      int k2 = (i3 - h1.pg[k1]) * l3 + (j3 - h1.qg[k1]) * i4 + (k3 - h1.rg[k1]) * j4;
      if ((k2 >= -k4 || l4 >= 0) && (k2 <= k4 || l4 <= 0))
        continue;
      flag = true;
      break;
    }

    if (!flag)
      return true;
    i3 = h1.pg[ai1[0]];
    j3 = h1.qg[ai1[0]];
    k3 = h1.rg[ai1[0]];
    l3 = arg0.gnb;
    i4 = arg0.hnb;
    j4 = arg0.inb;
    k4 = h1.bh[k];
    l4 = arg0.jnb;
    flag = false;
    for (int j5 = 0; j5 < j1; j5++) {
      int l1 = ai2[j5];
      int l2 = (i3 - h2.pg[l1]) * l3 + (j3 - h2.qg[l1]) * i4 + (k3 - h2.rg[l1]) * j4;
      if ((l2 >= -k4 || l4 <= 0) && (l2 <= k4 || l4 >= 0))
        continue;
      flag = true;
      break;
    }

    if (!flag)
      return true;
    int ai3[];
    int ai4[];
    if (i1 == 2) {
      ai3 = new int[4];
      ai4 = new int[4];
      int k5 = ai1[0];
      int i2 = ai1[1];
      ai3[0] = h1.sg[k5] - 20;
      ai3[1] = h1.sg[i2] - 20;
      ai3[2] = h1.sg[i2] + 20;
      ai3[3] = h1.sg[k5] + 20;
      ai4[0] = ai4[3] = h1.tg[k5];
      ai4[1] = ai4[2] = h1.tg[i2];
    } else {
      ai3 = new int[i1];
      ai4 = new int[i1];
      for (int l5 = 0; l5 < i1; l5++) {
        int k6 = ai1[l5];
        ai3[l5] = h1.sg[k6];
        ai4[l5] = h1.tg[k6];
      }

    }
    int ai5[];
    int ai6[];
    if (j1 == 2) {
      ai5 = new int[4];
      ai6 = new int[4];
      int i6 = ai2[0];
      int j2 = ai2[1];
      ai5[0] = h2.sg[i6] - 20;
      ai5[1] = h2.sg[j2] - 20;
      ai5[2] = h2.sg[j2] + 20;
      ai5[3] = h2.sg[i6] + 20;
      ai6[0] = ai6[3] = h2.tg[i6];
      ai6[1] = ai6[2] = h2.tg[j2];
    } else {
      ai5 = new int[j1];
      ai6 = new int[j1];
      for (int j6 = 0; j6 < j1; j6++) {
        int l6 = ai2[j6];
        ai5[j6] = h2.sg[l6];
        ai6[j6] = h2.tg[l6];
      }

    }
    return !kh(ai3, ai4, ai5, ai6);
  }

  private static boolean gh(Polygon arg0, Polygon arg1) {
    Model h1 = arg0.dnb;
    Model h2 = arg1.dnb;
    int k = arg0.enb;
    int l = arg1.enb;
    int ai1[] = h1.yg[k];
    int ai2[] = h2.yg[l];
    int i1 = h1.faceVertexCount[k];
    int j1 = h2.faceVertexCount[l];
    int k2 = h2.pg[ai2[0]];
    int l2 = h2.qg[ai2[0]];
    int i3 = h2.rg[ai2[0]];
    int j3 = arg1.gnb;
    int k3 = arg1.hnb;
    int l3 = arg1.inb;
    int i4 = h2.bh[l];
    int j4 = arg1.jnb;
    boolean flag = false;
    for (int k4 = 0; k4 < i1; k4++) {
      int k1 = ai1[k4];
      int i2 = (k2 - h1.pg[k1]) * j3 + (l2 - h1.qg[k1]) * k3 + (i3 - h1.rg[k1]) * l3;
      if ((i2 >= -i4 || j4 >= 0) && (i2 <= i4 || j4 <= 0))
        continue;
      flag = true;
      break;
    }

    if (!flag)
      return true;
    k2 = h1.pg[ai1[0]];
    l2 = h1.qg[ai1[0]];
    i3 = h1.rg[ai1[0]];
    j3 = arg0.gnb;
    k3 = arg0.hnb;
    l3 = arg0.inb;
    i4 = h1.bh[k];
    j4 = arg0.jnb;
    flag = false;
    for (int l4 = 0; l4 < j1; l4++) {
      int l1 = ai2[l4];
      int j2 = (k2 - h2.pg[l1]) * j3 + (l2 - h2.qg[l1]) * k3 + (i3 - h2.rg[l1]) * l3;
      if ((j2 >= -i4 || j4 <= 0) && (j2 <= i4 || j4 >= 0))
        continue;
      flag = true;
      break;
    }

    return !flag;
  }

  public void ui(int arg0, int arg1, int arg2) {
    fo = arg0;
    go = new byte[arg0][];
    ho = new int[arg0][];
    io = new int[arg0];
    jo = new long[arg0];
    lo = new boolean[arg0];
    ko = new int[arg0][];
    mo = 0L;
    no = new int[arg1][];
    oo = new int[arg2][];
  }

  public void vh(int arg0, byte arg1[], int arg2[], int arg3) {
    go[arg0] = arg1;
    ho[arg0] = arg2;
    io[arg0] = arg3;
    jo[arg0] = 0L;
    lo[arg0] = false;
    ko[arg0] = null;
    li(arg0);
  }

  public void li(int arg0) {
    if (arg0 < 0)
      return;
    jo[arg0] = mo++;
    if (ko[arg0] != null)
      return;
    if (io[arg0] == 0) {
      for (int k = 0; k < no.length; k++)
        if (no[k] == null) {
          no[k] = new int[16384];
          ko[arg0] = no[k];
          ji(arg0);
          return;
        }

      long l1 = 1L << 30;
      int i1 = 0;
      for (int k1 = 0; k1 < fo; k1++)
        if (k1 != arg0 && io[k1] == 0 && ko[k1] != null && jo[k1] < l1) {
          l1 = jo[k1];
          i1 = k1;
        }

      ko[arg0] = ko[i1];
      ko[i1] = null;
      ji(arg0);
      return;
    }
    for (int l = 0; l < oo.length; l++)
      if (oo[l] == null) {
        oo[l] = new int[0x10000];
        ko[arg0] = oo[l];
        ji(arg0);
        return;
      }

    long l2 = 1L << 30;
    int j1 = 0;
    for (int i2 = 0; i2 < fo; i2++)
      if (i2 != arg0 && io[i2] == 1 && ko[i2] != null && jo[i2] < l2) {
        l2 = jo[i2];
        j1 = i2;
      }

    ko[arg0] = ko[j1];
    ko[j1] = null;
    ji(arg0);
  }

  private void ji(int arg0) {
    char c;
    if (io[arg0] == 0)
      c = '@';
    else
      c = '\200';
    int ai1[] = ko[arg0];
    int k = 0;
    for (int l = 0; l < c; l++) {
      for (int i1 = 0; i1 < c; i1++) {
        int k1 = ho[arg0][go[arg0][i1 + l * c] & 0xff];
        k1 &= 0xf8f8ff;
        if (k1 == 0)
          k1 = 1;
        else if (k1 == 0xf800ff) {
          k1 = 0;
          lo[arg0] = true;
        }
        ai1[k++] = k1;
      }

    }

    for (int j1 = 0; j1 < k; j1++) {
      int l1 = ai1[j1];
      ai1[k + j1] = l1 - (l1 >>> 3) & 0xf8f8ff;
      ai1[k * 2 + j1] = l1 - (l1 >>> 2) & 0xf8f8ff;
      ai1[k * 3 + j1] = l1 - (l1 >>> 2) - (l1 >>> 3) & 0xf8f8ff;
    }

  }

  public void gi(int arg0) {
    if (ko[arg0] == null)
      return;
    int ai1[] = ko[arg0];
    for (int k = 0; k < 64; k++) {
      int l = k + 4032;
      int i1 = ai1[l];
      for (int k1 = 0; k1 < 63; k1++) {
        ai1[l] = ai1[l - 64];
        l -= 64;
      }

      ko[arg0][l] = i1;
    }

    char c = '\u1000';
    for (int j1 = 0; j1 < c; j1++) {
      int l1 = ai1[j1];
      ai1[c + j1] = l1 - (l1 >>> 3) & 0xf8f8ff;
      ai1[c * 2 + j1] = l1 - (l1 >>> 2) & 0xf8f8ff;
      ai1[c * 3 + j1] = l1 - (l1 >>> 2) - (l1 >>> 3) & 0xf8f8ff;
    }

  }

  public int ti(int arg0) {
    if (arg0 == 0xbc614e)
      return 0;
    li(arg0);
    if (arg0 >= 0)
      return ko[arg0][0];
    if (arg0 < 0) {
      arg0 = -(arg0 + 1);
      int k = arg0 >> 10 & 0x1f;
      int l = arg0 >> 5 & 0x1f;
      int i1 = arg0 & 0x1f;
      return (k << 19) + (l << 11) + (i1 << 3);
    } else {
      return 0;
    }
  }

  public void di(int arg0, int arg1, int arg2) {
    if (arg0 == 0 && arg1 == 0 && arg2 == 0)
      arg0 = 32;
    for (int k = 0; k < modelCount; k++)
      models[k].ye(arg0, arg1, arg2);

  }

  public void yi(int arg0, int arg1, int arg2, int arg3, int arg4) {
    if (arg2 == 0 && arg3 == 0 && arg4 == 0)
      arg2 = 32;
    for (int k = 0; k < modelCount; k++)
      models[k].be(arg0, arg1, arg2, arg3, arg4);

  }

  public static int fi(int arg0, int arg1, int arg2) {
    return -1 - (arg0 / 8) * 1024 - (arg1 / 8) * 32 - arg2 / 8;
  }

  public int nh(int arg0, int arg1, int arg2, int arg3, int arg4) {
    if (arg3 == arg1)
      return arg0;
    else
      return arg0 + ((arg2 - arg0) * (arg4 - arg1)) / (arg3 - arg1);
  }

  public boolean ci(int arg0, int arg1, int arg2, int arg3, boolean arg4) {
    if (arg4 && arg0 <= arg2 || arg0 < arg2) {
      if (arg0 > arg3)
        return true;
      if (arg1 > arg2)
        return true;
      if (arg1 > arg3)
        return true;
      return !arg4;
    }
    if (arg0 < arg3)
      return true;
    if (arg1 < arg2)
      return true;
    if (arg1 < arg3)
      return true;
    else
      return arg4;
  }

  public boolean rh(int arg0, int arg1, int arg2, boolean arg3) {
    if (arg3 && arg0 <= arg2 || arg0 < arg2) {
      if (arg1 > arg2)
        return true;
      return !arg3;
    }
    if (arg1 < arg2)
      return true;
    else
      return arg3;
  }

  public boolean kh(int arg0[], int arg1[], int arg2[], int arg3[]) {
    int k = arg0.length;
    int l = arg2.length;
    byte byte0 = 0;
    int k20;
    int i21 = k20 = arg1[0];
    int i1 = 0;
    int l20;
    int j21 = l20 = arg3[0];
    int k1 = 0;
    for (int k21 = 1; k21 < k; k21++)
      if (arg1[k21] < k20) {
        k20 = arg1[k21];
        i1 = k21;
      } else if (arg1[k21] > i21)
        i21 = arg1[k21];

    for (int l21 = 1; l21 < l; l21++)
      if (arg3[l21] < l20) {
        l20 = arg3[l21];
        k1 = l21;
      } else if (arg3[l21] > j21)
        j21 = arg3[l21];

    if (l20 >= i21)
      return false;
    if (k20 >= j21)
      return false;
    int j1;
    int l1;
    boolean flag;
    if (arg1[i1] < arg3[k1]) {
      for (j1 = i1; arg1[j1] < arg3[k1]; j1 = (j1 + 1) % k)
        ;
      for (; arg1[i1] < arg3[k1]; i1 = ((i1 - 1) + k) % k)
        ;
      int i2 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[k1]);
      int i7 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[k1]);
      int j11 = arg2[k1];
      flag = (i2 < j11) | (i7 < j11);
      if (rh(i2, i7, j11, flag))
        return true;
      l1 = (k1 + 1) % l;
      k1 = ((k1 - 1) + l) % l;
      if (i1 == j1)
        byte0 = 1;
    } else {
      for (l1 = k1; arg3[l1] < arg1[i1]; l1 = (l1 + 1) % l)
        ;
      for (; arg3[k1] < arg1[i1]; k1 = ((k1 - 1) + l) % l)
        ;
      int j2 = arg0[i1];
      int k11 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg1[i1]);
      int j16 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg1[i1]);
      flag = (j2 < k11) | (j2 < j16);
      if (rh(k11, j16, j2, !flag))
        return true;
      j1 = (i1 + 1) % k;
      i1 = ((i1 - 1) + k) % k;
      if (k1 == l1)
        byte0 = 2;
    }
    while (byte0 == 0)
      if (arg1[i1] < arg1[j1]) {
        if (arg1[i1] < arg3[k1]) {
          if (arg1[i1] < arg3[l1]) {
            int k2 = arg0[i1];
            int j7 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg1[i1]);
            int l11 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg1[i1]);
            int k16 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg1[i1]);
            if (ci(k2, j7, l11, k16, flag))
              return true;
            i1 = ((i1 - 1) + k) % k;
            if (i1 == j1)
              byte0 = 1;
          } else {
            int l2 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[l1]);
            int k7 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[l1]);
            int i12 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg3[l1]);
            int l16 = arg2[l1];
            if (ci(l2, k7, i12, l16, flag))
              return true;
            l1 = (l1 + 1) % l;
            if (k1 == l1)
              byte0 = 2;
          }
        } else if (arg3[k1] < arg3[l1]) {
          int i3 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[k1]);
          int l7 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[k1]);
          int j12 = arg2[k1];
          int i17 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg3[k1]);
          if (ci(i3, l7, j12, i17, flag))
            return true;
          k1 = ((k1 - 1) + l) % l;
          if (k1 == l1)
            byte0 = 2;
        } else {
          int j3 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[l1]);
          int i8 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[l1]);
          int k12 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg3[l1]);
          int j17 = arg2[l1];
          if (ci(j3, i8, k12, j17, flag))
            return true;
          l1 = (l1 + 1) % l;
          if (k1 == l1)
            byte0 = 2;
        }
      } else if (arg1[j1] < arg3[k1]) {
        if (arg1[j1] < arg3[l1]) {
          int k3 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg1[j1]);
          int j8 = arg0[j1];
          int l12 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg1[j1]);
          int k17 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg1[j1]);
          if (ci(k3, j8, l12, k17, flag))
            return true;
          j1 = (j1 + 1) % k;
          if (i1 == j1)
            byte0 = 1;
        } else {
          int l3 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[l1]);
          int k8 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[l1]);
          int i13 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg3[l1]);
          int l17 = arg2[l1];
          if (ci(l3, k8, i13, l17, flag))
            return true;
          l1 = (l1 + 1) % l;
          if (k1 == l1)
            byte0 = 2;
        }
      } else if (arg3[k1] < arg3[l1]) {
        int i4 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[k1]);
        int l8 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[k1]);
        int j13 = arg2[k1];
        int i18 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg3[k1]);
        if (ci(i4, l8, j13, i18, flag))
          return true;
        k1 = ((k1 - 1) + l) % l;
        if (k1 == l1)
          byte0 = 2;
      } else {
        int j4 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[l1]);
        int i9 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[l1]);
        int k13 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg3[l1]);
        int j18 = arg2[l1];
        if (ci(j4, i9, k13, j18, flag))
          return true;
        l1 = (l1 + 1) % l;
        if (k1 == l1)
          byte0 = 2;
      }
    while (byte0 == 1)
      if (arg1[i1] < arg3[k1]) {
        if (arg1[i1] < arg3[l1]) {
          int k4 = arg0[i1];
          int l13 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg1[i1]);
          int k18 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg1[i1]);
          return rh(l13, k18, k4, !flag);
        }
        int l4 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[l1]);
        int j9 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[l1]);
        int i14 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg3[l1]);
        int l18 = arg2[l1];
        if (ci(l4, j9, i14, l18, flag))
          return true;
        l1 = (l1 + 1) % l;
        if (k1 == l1)
          byte0 = 0;
      } else if (arg3[k1] < arg3[l1]) {
        int i5 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[k1]);
        int k9 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[k1]);
        int j14 = arg2[k1];
        int i19 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg3[k1]);
        if (ci(i5, k9, j14, i19, flag))
          return true;
        k1 = ((k1 - 1) + l) % l;
        if (k1 == l1)
          byte0 = 0;
      } else {
        int j5 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[l1]);
        int l9 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[l1]);
        int k14 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg3[l1]);
        int j19 = arg2[l1];
        if (ci(j5, l9, k14, j19, flag))
          return true;
        l1 = (l1 + 1) % l;
        if (k1 == l1)
          byte0 = 0;
      }
    while (byte0 == 2)
      if (arg3[k1] < arg1[i1]) {
        if (arg3[k1] < arg1[j1]) {
          int k5 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[k1]);
          int i10 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[k1]);
          int l14 = arg2[k1];
          return rh(k5, i10, l14, flag);
        }
        int l5 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg1[j1]);
        int j10 = arg0[j1];
        int i15 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg1[j1]);
        int k19 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg1[j1]);
        if (ci(l5, j10, i15, k19, flag))
          return true;
        j1 = (j1 + 1) % k;
        if (i1 == j1)
          byte0 = 0;
      } else if (arg1[i1] < arg1[j1]) {
        int i6 = arg0[i1];
        int k10 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg1[i1]);
        int j15 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg1[i1]);
        int l19 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg1[i1]);
        if (ci(i6, k10, j15, l19, flag))
          return true;
        i1 = ((i1 - 1) + k) % k;
        if (i1 == j1)
          byte0 = 0;
      } else {
        int j6 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg1[j1]);
        int l10 = arg0[j1];
        int k15 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg1[j1]);
        int i20 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg1[j1]);
        if (ci(j6, l10, k15, i20, flag))
          return true;
        j1 = (j1 + 1) % k;
        if (i1 == j1)
          byte0 = 0;
      }
    if (arg1[i1] < arg3[k1]) {
      int k6 = arg0[i1];
      int l15 = nh(arg2[(k1 + 1) % l], arg3[(k1 + 1) % l], arg2[k1], arg3[k1], arg1[i1]);
      int j20 = nh(arg2[((l1 - 1) + l) % l], arg3[((l1 - 1) + l) % l], arg2[l1], arg3[l1], arg1[i1]);
      return rh(l15, j20, k6, !flag);
    }
    int l6 = nh(arg0[(i1 + 1) % k], arg1[(i1 + 1) % k], arg0[i1], arg1[i1], arg3[k1]);
    int i11 = nh(arg0[((j1 - 1) + k) % k], arg1[((j1 - 1) + k) % k], arg0[j1], arg1[j1], arg3[k1]);
    int i16 = arg2[k1];
    return rh(l6, i11, i16, flag);
  }

  public static final int bm = 0;
  int cm;
  int dm[];
  int em[][];
  int fm[];
  public int gm;
  public int hm;
  public int clipFar3D;
  public int clipFar2D;
  public int fogFalloff;
  public int fogDistance;
  public static int mm[] = new int[2048];
  private static int nm[] = new int[512];
  public boolean om;
  public double pm;
  public int qm;
  private boolean rm;
  private int sm;
  private int tm;
  private int um;
  private int vm;
  private Model objectsUnderCursor[];
  private int playersUnderCursor[];
  private int width;
  private int clipX;
  private int clipY;
  private int baseX;
  private int baseY;
  private int viewDistance;
  private int en;
  private int fn;
  private int gn;
  private int hn;
  private int in;
  private int jn;
  private int kn;
  public int modelCount;
  public int mn;
  public Model models[];
  private int on[];
  private int pn;
  private Polygon qn[];
  private int rn;
  private int spriteCount;
  private int spriteID[];
  private int spriteX[];
  private int spriteY[];
  private int spriteZ[];
  private int spriteWidth[];
  private int spriteHeight[];
  private int zn[];
  public Model view;
  private static final int bo = 16;
  private static final int co = 4;
  private static final int _flddo = 5;
  private static final int eo = 0xbc614e;
  int fo;
  byte go[][];
  int ho[][];
  int io[];
  long jo[];
  int ko[][];
  boolean lo[];
  private static long mo;
  int no[][];
  int oo[][];
  private static byte po[];
  private static int qo[] = new int[256];
  Surface ro;
  public int so[];
  Scanline scanlines[];
  int uo;
  int vo;
  int wo[];
  int xo[];
  int yo[];
  int zo[];
  int ap[];
  int bp[];
  boolean cp;
  static int dp;
  static int ep;
  static int fp;
  static int gp;
  static int hp;
  static int ip;
  int jp;
  int kp;
}