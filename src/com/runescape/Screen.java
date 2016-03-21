package com.runescape;// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

import com.jagex.client.Surface;

import java.awt.Component;

public class Screen extends Surface {
  public Screen(int arg0, int arg1, int arg2, Component arg3) {
    super(arg0, arg1, arg2, arg3);
    boolean flag = false;
    if (flag)
      new Surface(arg0, arg1, arg2, arg3);
  }

  public void dg(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
    if (arg4 >= 50000) {
      ls.yl(arg0, arg1, arg2, arg3, arg4 - 50000, arg5, arg6);
      return;
    }
    if (arg4 >= 40000) {
      ls.vm(arg0, arg1, arg2, arg3, arg4 - 40000, arg5, arg6);
      return;
    }
    if (arg4 >= 20000) {
      ls.an(arg0, arg1, arg2, arg3, arg4 - 20000, arg5, arg6);
      return;
    }
    if (arg4 >= 5000) {
      ls.ml(arg0, arg1, arg2, arg3, arg4 - 5000, arg5, arg6);
      return;
    } else {
      super.nf(arg0, arg1, arg2, arg3, arg4);
      return;
    }
  }

  public Client ls;
}