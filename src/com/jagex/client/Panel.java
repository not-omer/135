// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex.client;

// Referenced classes of package com.jagex.client:
//			Graphics2D

public class Panel {
  public Panel(Surface face, int controls) {
    tf = -1;
    hg = true;
    graphics = face;
    we = controls;
    xe = new boolean[controls];
    ye = new boolean[controls];
    ze = new boolean[controls];
    af = new boolean[controls];
    ff = new boolean[controls];
    bf = new int[controls];
    cf = new int[controls];
    df = new int[controls];
    ef = new int[controls];
    gf = new int[controls];
    hf = new int[controls];
    _fldif = new int[controls];
    jf = new int[controls];
    kf = new int[controls];
    lf = new int[controls];
    mf = new int[controls];
    nf = new String[controls];
    of = new String[controls][];
    vf = wc(114, 114, 176);
    wf = wc(14, 14, 62);
    xf = wc(200, 208, 232);
    yf = wc(96, 129, 184);
    zf = wc(53, 95, 115);
    ag = wc(117, 142, 171);
    bg = wc(98, 122, 158);
    cg = wc(86, 100, 136);
    dg = wc(135, 146, 179);
    eg = wc(97, 112, 151);
    fg = wc(88, 102, 136);
    gg = wc(84, 93, 120);
  }

  public int wc(int arg0, int arg1, int arg2) {
    return Surface.kg((kg * arg0) / 114, (lg * arg1) / 114, (mg * arg2) / 176);
  }

  public void nd() {
    rf = 0;
  }

  public void pd(int arg0, int arg1, int arg2, int arg3) {
    pf = arg0;
    qf = arg1;
    sf = arg3;
    if (arg2 != 0)
      rf = arg2;
    if (arg2 == 1) {
      for (int j = 0; j < ve; j++) {
        if (xe[j] && _fldif[j] == 10 && pf >= gf[j] && qf >= hf[j] && pf <= gf[j] + jf[j] && qf <= hf[j] + kf[j])
          af[j] = true;
        if (xe[j] && _fldif[j] == 14 && pf >= gf[j] && qf >= hf[j] && pf <= gf[j] + jf[j] && qf <= hf[j] + kf[j])
          df[j] = 1 - df[j];
      }

    }
    if (arg3 == 1)
      uf++;
    else
      uf = 0;
    if (arg2 == 1 || uf > 20) {
      for (int k = 0; k < ve; k++)
        if (xe[k] && _fldif[k] == 15 && pf >= gf[k] && qf >= hf[k] && pf <= gf[k] + jf[k] && qf <= hf[k] + kf[k])
          af[k] = true;

      uf -= 5;
    }
  }

  public boolean rd(int arg0) {
    if (xe[arg0] && af[arg0]) {
      af[arg0] = false;
      return true;
    } else {
      return false;
    }
  }

  public void od(int arg0) {
    if (arg0 == 0)
      return;
    if (tf != -1 && nf[tf] != null && xe[tf]) {
      int j = nf[tf].length();
      if (arg0 == 8 && j > 0)
        nf[tf] = nf[tf].substring(0, j - 1);
      if ((arg0 == 10 || arg0 == 13) && j > 0)
        af[tf] = true;
      String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
      if (j < lf[tf]) {
        for (int k = 0; k < s.length(); k++)
          if (arg0 == s.charAt(k))
            nf[tf] += (char) arg0;

      }
      if (arg0 == 9) {
        do
          tf = (tf + 1) % ve;
        while (_fldif[tf] != 5 && _fldif[tf] != 6);
        return;
      }
    }
  }

  public void hc() {
    for (int j = 0; j < ve; j++)
      if (xe[j])
        if (_fldif[j] == 0)
          drawLabel(j, gf[j], hf[j], nf[j], mf[j]);
        else if (_fldif[j] == 1)
          drawLabel(j, gf[j] - graphics.df(nf[j], mf[j]) / 2, hf[j], nf[j], mf[j]);
        else if (_fldif[j] == 2)
          hd(gf[j], hf[j], jf[j], kf[j]);
        else if (_fldif[j] == 3)
          uc(gf[j], hf[j], jf[j]);
        else if (_fldif[j] == 4)
          cd(j, gf[j], hf[j], jf[j], kf[j], mf[j], of[j], cf[j], bf[j]);
        else if (_fldif[j] == 5 || _fldif[j] == 6)
          rc(j, gf[j], hf[j], jf[j], kf[j], nf[j], mf[j]);
        else if (_fldif[j] == 7)
          oc(j, gf[j], hf[j], mf[j], of[j]);
        else if (_fldif[j] == 8)
          bc(j, gf[j], hf[j], mf[j], of[j]);
        else if (_fldif[j] == 9)
          fc(j, gf[j], hf[j], jf[j], kf[j], mf[j], of[j], cf[j], bf[j]);
        else if (_fldif[j] == 11)
          fd(gf[j], hf[j], jf[j], kf[j]);
        else if (_fldif[j] == 12)
          xc(gf[j], hf[j], mf[j]);
        else if (_fldif[j] == 14)
          bd(j, gf[j], hf[j], jf[j], kf[j]);

    rf = 0;
  }

  protected void bd(int arg0, int arg1, int arg2, int arg3, int arg4) {
    graphics.yf(arg1, arg2, arg3, arg4, 0xffffff);
    graphics.rg(arg1, arg2, arg3, dg);
    graphics.vg(arg1, arg2, arg4, dg);
    graphics.rg(arg1, (arg2 + arg4) - 1, arg3, gg);
    graphics.vg((arg1 + arg3) - 1, arg2, arg4, gg);
    if (df[arg0] == 1) {
      for (int j = 0; j < arg4; j++) {
        graphics.rg(arg1 + j, arg2 + j, 1, 0);
        graphics.rg((arg1 + arg3) - 1 - j, arg2 + j, 1, 0);
      }
    }
  }

  protected void drawLabel(int arg0, int arg1, int arg2, String label, int arg4) {
    int j = arg2 + graphics.ng(arg4) / 3;
    drawString(arg0, arg1, j, label, arg4);
  }

  protected void drawString(int arg0, int arg1, int arg2, String string, int arg4) {
    int j;
    if (ff[arg0])
      j = 0xffffff;
    else
      j = 0;
    graphics.drawString(string, arg1, arg2, arg4, j);
  }

  protected void rc(int arg0, int arg1, int arg2, int arg3, int arg4, String arg5, int arg6) {
    if (ze[arg0]) {
      int j = arg5.length();
      arg5 = "";
      for (int l = 0; l < j; l++)
        arg5 = arg5 + "X";
    }
    if (_fldif[arg0] == 5) {
      if (rf == 1 && pf >= arg1 && qf >= arg2 - arg4 / 2 && pf <= arg1 + arg3 && qf <= arg2 + arg4 / 2)
        tf = arg0;
    } else if (_fldif[arg0] == 6) {
      if (rf == 1 && pf >= arg1 - arg3 / 2 && qf >= arg2 - arg4 / 2 && pf <= arg1 + arg3 / 2 && qf <= arg2 + arg4 / 2)
        tf = arg0;
      arg1 -= graphics.df(arg5, arg6) / 2;
    }
    if (tf == arg0)
      arg5 = arg5 + "*";
    int k = arg2 + graphics.ng(arg6) / 3;
    drawString(arg0, arg1, k, arg5, arg6);
  }

  public void hd(int arg0, int arg1, int arg2, int arg3) {
    graphics.sf(arg0, arg1, arg0 + arg2, arg1 + arg3);
    graphics.hg(arg0, arg1, arg2, arg3, gg, dg);
    if (ig) {
      for (int j = arg0 - (arg1 & 0x3f); j < arg0 + arg2; j += 128) {
        for (int k = arg1 - (arg1 & 0x1f); k < arg1 + arg3; k += 128)
          graphics.qg(j, k, 6 + jg, 128);
      }
    }
    graphics.rg(arg0, arg1, arg2, dg);
    graphics.rg(arg0 + 1, arg1 + 1, arg2 - 2, dg);
    graphics.rg(arg0 + 2, arg1 + 2, arg2 - 4, eg);
    graphics.vg(arg0, arg1, arg3, dg);
    graphics.vg(arg0 + 1, arg1 + 1, arg3 - 2, dg);
    graphics.vg(arg0 + 2, arg1 + 2, arg3 - 4, eg);
    graphics.rg(arg0, (arg1 + arg3) - 1, arg2, gg);
    graphics.rg(arg0 + 1, (arg1 + arg3) - 2, arg2 - 2, gg);
    graphics.rg(arg0 + 2, (arg1 + arg3) - 3, arg2 - 4, fg);
    graphics.vg((arg0 + arg2) - 1, arg1, arg3, gg);
    graphics.vg((arg0 + arg2) - 2, arg1 + 1, arg3 - 2, gg);
    graphics.vg((arg0 + arg2) - 3, arg1 + 2, arg3 - 4, fg);
    graphics.tf();
  }

  public void fd(int arg0, int arg1, int arg2, int arg3) {
    graphics.yf(arg0, arg1, arg2, arg3, 0);
    graphics.qf(arg0, arg1, arg2, arg3, ag);
    graphics.qf(arg0 + 1, arg1 + 1, arg2 - 2, arg3 - 2, bg);
    graphics.qf(arg0 + 2, arg1 + 2, arg2 - 4, arg3 - 4, cg);
    graphics.xg(arg0, arg1, 2 + jg);
    graphics.xg((arg0 + arg2) - 7, arg1, 3 + jg);
    graphics.xg(arg0, (arg1 + arg3) - 7, 4 + jg);
    graphics.xg((arg0 + arg2) - 7, (arg1 + arg3) - 7, 5 + jg);
  }

  protected void xc(int arg0, int arg1, int arg2) {
    graphics.xg(arg0, arg1, arg2);
  }

  protected void uc(int arg0, int arg1, int arg2) {
    graphics.rg(arg0, arg1, arg2, 0);
  }

  protected void cd(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, String arg6[], int arg7, int arg8) {
    int j = arg4 / graphics.ng(arg5);
    if (arg8 > arg7 - j)
      arg8 = arg7 - j;
    if (arg8 < 0)
      arg8 = 0;
    bf[arg0] = arg8;
    if (j < arg7) {
      int k = (arg1 + arg3) - 12;
      int i1 = ((arg4 - 27) * j) / arg7;
      if (i1 < 6)
        i1 = 6;
      int k1 = ((arg4 - 27 - i1) * arg8) / (arg7 - j);
      if (sf == 1 && pf >= k && pf <= k + 12) {
        if (qf > arg2 && qf < arg2 + 12 && arg8 > 0)
          arg8--;
        if (qf > (arg2 + arg4) - 12 && qf < arg2 + arg4 && arg8 < arg7 - j)
          arg8++;
        bf[arg0] = arg8;
      }
      if (sf == 1 && (pf >= k && pf <= k + 12 || pf >= k - 12 && pf <= k + 24 && ye[arg0])) {
        if (qf > arg2 + 12 && qf < (arg2 + arg4) - 12) {
          ye[arg0] = true;
          int i2 = qf - arg2 - 12 - i1 / 2;
          arg8 = (i2 * arg7) / (arg4 - 24);
          if (arg8 > arg7 - j)
            arg8 = arg7 - j;
          if (arg8 < 0)
            arg8 = 0;
          bf[arg0] = arg8;
        }
      } else {
        ye[arg0] = false;
      }
      k1 = ((arg4 - 27 - i1) * arg8) / (arg7 - j);
      ld(arg1, arg2, arg3, arg4, k1, i1);
    }
    int l = arg4 - j * graphics.ng(arg5);
    int j1 = arg2 + (graphics.ng(arg5) * 5) / 6 + l / 2;
    for (int l1 = arg8; l1 < arg7; l1++) {
      drawString(arg0, arg1 + 2, j1, arg6[l1], arg5);
      j1 += graphics.ng(arg5) - ng;
      if (j1 >= arg2 + arg4)
        return;
    }

  }

  protected void ld(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    int j = (arg0 + arg2) - 12;
    graphics.qf(j, arg1, 12, arg3, 0);
    graphics.xg(j + 1, arg1 + 1, jg);
    graphics.xg(j + 1, (arg1 + arg3) - 12, 1 + jg);
    graphics.rg(j, arg1 + 13, 12, 0);
    graphics.rg(j, (arg1 + arg3) - 13, 12, 0);
    graphics.hg(j + 1, arg1 + 14, 11, arg3 - 27, vf, wf);
    graphics.yf(j + 3, arg4 + arg1 + 14, 7, arg5, yf);
    graphics.vg(j + 2, arg4 + arg1 + 14, arg5, xf);
    graphics.vg(j + 2 + 8, arg4 + arg1 + 14, arg5, zf);
  }

  protected void oc(int arg0, int arg1, int arg2, int arg3, String arg4[]) {
    int j = 0;
    int k = arg4.length;
    for (int l = 0; l < k; l++) {
      j += graphics.df(arg4[l], arg3);
      if (l < k - 1)
        j += graphics.df("  ", arg3);
    }

    int i1 = arg1 - j / 2;
    int j1 = arg2 + graphics.ng(arg3) / 3;
    for (int k1 = 0; k1 < k; k1++) {
      int l1;
      if (ff[arg0])
        l1 = 0xffffff;
      else
        l1 = 0;
      if (pf >= i1 && pf <= i1 + graphics.df(arg4[k1], arg3) && qf <= j1 && qf > j1 - graphics.ng(arg3)) {
        if (ff[arg0])
          l1 = 0x808080;
        else
          l1 = 0xffffff;
        if (rf == 1) {
          df[arg0] = k1;
          af[arg0] = true;
        }
      }
      if (df[arg0] == k1)
        if (ff[arg0])
          l1 = 0xff0000;
        else
          l1 = 0xc00000;
      graphics.drawString(arg4[k1], i1, j1, arg3, l1);
      i1 += graphics.df(arg4[k1] + "  ", arg3);
    }

  }

  protected void bc(int arg0, int arg1, int arg2, int arg3, String arg4[]) {
    int j = arg4.length;
    int k = arg2 - (graphics.ng(arg3) * (j - 1)) / 2;
    for (int l = 0; l < j; l++) {
      int i1;
      if (ff[arg0])
        i1 = 0xffffff;
      else
        i1 = 0;
      int j1 = graphics.df(arg4[l], arg3);
      if (pf >= arg1 - j1 / 2 && pf <= arg1 + j1 / 2 && qf - 2 <= k && qf - 2 > k - graphics.ng(arg3)) {
        if (ff[arg0])
          i1 = 0x808080;
        else
          i1 = 0xffffff;
        if (rf == 1) {
          df[arg0] = l;
          af[arg0] = true;
        }
      }
      if (df[arg0] == l)
        if (ff[arg0])
          i1 = 0xff0000;
        else
          i1 = 0xc00000;
      graphics.drawString(arg4[l], arg1 - j1 / 2, k, arg3, i1);
      k += graphics.ng(arg3);
    }
  }

  protected void fc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, String arg6[], int arg7, int arg8) {
    int j = arg4 / graphics.ng(arg5);
    if (j < arg7) {
      int k = (arg1 + arg3) - 12;
      int i1 = ((arg4 - 27) * j) / arg7;
      if (i1 < 6)
        i1 = 6;
      int k1 = ((arg4 - 27 - i1) * arg8) / (arg7 - j);
      if (sf == 1 && pf >= k && pf <= k + 12) {
        if (qf > arg2 && qf < arg2 + 12 && arg8 > 0)
          arg8--;
        if (qf > (arg2 + arg4) - 12 && qf < arg2 + arg4 && arg8 < arg7 - j)
          arg8++;
        bf[arg0] = arg8;
      }
      if (sf == 1 && (pf >= k && pf <= k + 12 || pf >= k - 12 && pf <= k + 24 && ye[arg0])) {
        if (qf > arg2 + 12 && qf < (arg2 + arg4) - 12) {
          ye[arg0] = true;
          int i2 = qf - arg2 - 12 - i1 / 2;
          arg8 = (i2 * arg7) / (arg4 - 24);
          if (arg8 < 0)
            arg8 = 0;
          if (arg8 > arg7 - j)
            arg8 = arg7 - j;
          bf[arg0] = arg8;
        }
      } else {
        ye[arg0] = false;
      }
      k1 = ((arg4 - 27 - i1) * arg8) / (arg7 - j);
      ld(arg1, arg2, arg3, arg4, k1, i1);
    } else {
      arg8 = 0;
      bf[arg0] = 0;
    }
    ef[arg0] = -1;
    int l = arg4 - j * graphics.ng(arg5);
    int j1 = arg2 + (graphics.ng(arg5) * 5) / 6 + l / 2;
    for (int l1 = arg8; l1 < arg7; l1++) {
      int j2;
      if (ff[arg0])
        j2 = 0xffffff;
      else
        j2 = 0;
      if (pf >= arg1 + 2 && pf <= arg1 + 2 + graphics.df(arg6[l1], arg5) && qf - 2 <= j1
          && qf - 2 > j1 - graphics.ng(arg5)) {
        if (ff[arg0])
          j2 = 0x808080;
        else
          j2 = 0xffffff;
        ef[arg0] = l1;
        if (rf == 1) {
          df[arg0] = l1;
          af[arg0] = true;
        }
      }
      if (df[arg0] == l1 && hg)
        j2 = 0xff0000;
      graphics.drawString(arg6[l1], arg1 + 2, j1, arg5, j2);
      j1 += graphics.ng(arg5);
      if (j1 >= arg2 + arg4)
        return;
    }

  }

  public int kc(int arg0, int arg1, String arg2, int arg3, boolean arg4) {
    _fldif[ve] = 0;
    xe[ve] = true;
    af[ve] = false;
    mf[ve] = arg3;
    ff[ve] = arg4;
    gf[ve] = arg0;
    hf[ve] = arg1;
    nf[ve] = arg2;
    return ve++;
  }

  public int createCentredLabel(int arg0, int arg1, String label, int arg3, boolean arg4) {
    _fldif[ve] = 1;
    xe[ve] = true;
    af[ve] = false;
    mf[ve] = arg3;
    ff[ve] = arg4;
    gf[ve] = arg0;
    hf[ve] = arg1;
    nf[ve] = label;
    return ve++;
  }

  public int ad(int arg0, int arg1, int arg2, int arg3) {
    _fldif[ve] = 2;
    xe[ve] = true;
    af[ve] = false;
    gf[ve] = arg0 - arg2 / 2;
    hf[ve] = arg1 - arg3 / 2;
    jf[ve] = arg2;
    kf[ve] = arg3;
    return ve++;
  }

  public int jc(int arg0, int arg1, int arg2, int arg3) {
    _fldif[ve] = 11;
    xe[ve] = true;
    af[ve] = false;
    gf[ve] = arg0 - arg2 / 2;
    hf[ve] = arg1 - arg3 / 2;
    jf[ve] = arg2;
    kf[ve] = arg3;
    return ve++;
  }

  public int lc(int arg0, int arg1, int arg2) {
    int j = graphics.lk[arg2];
    int k = graphics.mk[arg2];
    _fldif[ve] = 12;
    xe[ve] = true;
    af[ve] = false;
    gf[ve] = arg0 - j / 2;
    hf[ve] = arg1 - k / 2;
    jf[ve] = j;
    kf[ve] = k;
    mf[ve] = arg2;
    return ve++;
  }

  public int dc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6) {
    _fldif[ve] = 4;
    xe[ve] = true;
    af[ve] = false;
    gf[ve] = arg0;
    hf[ve] = arg1;
    jf[ve] = arg2;
    kf[ve] = arg3;
    ff[ve] = arg6;
    mf[ve] = arg4;
    lf[ve] = arg5;
    cf[ve] = 0;
    bf[ve] = 0;
    of[ve] = new String[arg5];
    return ve++;
  }

  public int ec(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6, boolean arg7) {
    _fldif[ve] = 5;
    xe[ve] = true;
    ze[ve] = arg6;
    af[ve] = false;
    mf[ve] = arg4;
    ff[ve] = arg7;
    gf[ve] = arg0;
    hf[ve] = arg1;
    jf[ve] = arg2;
    kf[ve] = arg3;
    lf[ve] = arg5;
    nf[ve] = "";
    return ve++;
  }

  public int yc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6, boolean arg7) {
    _fldif[ve] = 6;
    xe[ve] = true;
    ze[ve] = arg6;
    af[ve] = false;
    mf[ve] = arg4;
    ff[ve] = arg7;
    gf[ve] = arg0;
    hf[ve] = arg1;
    jf[ve] = arg2;
    kf[ve] = arg3;
    lf[ve] = arg5;
    nf[ve] = "";
    return ve++;
  }

  public int cc(int arg0, int arg1, String arg2[], int arg3, boolean arg4) {
    _fldif[ve] = 8;
    xe[ve] = true;
    af[ve] = false;
    mf[ve] = arg3;
    ff[ve] = arg4;
    gf[ve] = arg0;
    hf[ve] = arg1;
    of[ve] = arg2;
    df[ve] = 0;
    return ve++;
  }

  public int qc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, boolean arg6) {
    _fldif[ve] = 9;
    xe[ve] = true;
    af[ve] = false;
    mf[ve] = arg4;
    ff[ve] = arg6;
    gf[ve] = arg0;
    hf[ve] = arg1;
    jf[ve] = arg2;
    kf[ve] = arg3;
    lf[ve] = arg5;
    of[ve] = new String[arg5];
    cf[ve] = 0;
    bf[ve] = 0;
    df[ve] = -1;
    ef[ve] = -1;
    return ve++;
  }

  public int md(int arg0, int arg1, int arg2, int arg3) {
    _fldif[ve] = 10;
    xe[ve] = true;
    af[ve] = false;
    gf[ve] = arg0 - arg2 / 2;
    hf[ve] = arg1 - arg3 / 2;
    jf[ve] = arg2;
    kf[ve] = arg3;
    return ve++;
  }

  public int sc(int arg0, int arg1, int arg2) {
    _fldif[ve] = 14;
    xe[ve] = true;
    af[ve] = false;
    gf[ve] = arg0 - arg2 / 2;
    hf[ve] = arg1 - arg2 / 2;
    jf[ve] = arg2;
    kf[ve] = arg2;
    return ve++;
  }

  public void mc(int arg0) {
    cf[arg0] = 0;
  }

  public void zc(int arg0) {
    bf[arg0] = 0;
    ef[arg0] = -1;
  }

  public void dd(int arg0, int arg1, String arg2) {
    of[arg0][arg1] = arg2;
    if (arg1 + 1 > cf[arg0])
      cf[arg0] = arg1 + 1;
  }

  public void gc(int arg0, String arg1, boolean arg2) {
    int j = cf[arg0]++;
    if (j >= lf[arg0]) {
      j--;
      cf[arg0]--;
      for (int k = 0; k < j; k++)
        of[arg0][k] = of[arg0][k + 1];

    }
    of[arg0][j] = arg1;
    if (arg2)
      bf[arg0] = 0xf423f;
  }

  public void kd(int arg0, String arg1) {
    nf[arg0] = arg1;
  }

  public String pc(int arg0) {
    if (nf[arg0] == null)
      return "null";
    else
      return nf[arg0];
  }

  public void ed(int arg0) {
    xe[arg0] = true;
  }

  public void qd(int arg0) {
    xe[arg0] = false;
  }

  public void nc(int arg0) {
    tf = arg0;
  }

  public int tc(int arg0) {
    return df[arg0];
  }

  public int ic(int arg0) {
    int j = ef[arg0];
    return j;
  }

  public void vc(int arg0, int arg1) {
    df[arg0] = arg1;
  }

  protected Surface graphics;
  int ve;
  int we;
  public boolean xe[];
  public boolean ye[];
  public boolean ze[];
  public boolean af[];
  public int bf[];
  public int cf[];
  public int df[];
  public int ef[];
  boolean ff[];
  int gf[];
  int hf[];
  int _fldif[];
  int jf[];
  int kf[];
  int lf[];
  int mf[];
  String nf[];
  String of[][];
  int pf;
  int qf;
  int rf;
  int sf;
  int tf;
  int uf;
  int vf;
  int wf;
  int xf;
  int yf;
  int zf;
  int ag;
  int bg;
  int cg;
  int dg;
  int eg;
  int fg;
  int gg;
  public boolean hg;
  public static boolean ig = true;
  public static int jg;
  public static int kg = 114;
  public static int lg = 114;
  public static int mg = 176;
  public static int ng;
}