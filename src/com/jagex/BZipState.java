// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex;

class BZipState {
  BZipState() {
    nb = new int[256];
    pb = new int[257];
    qb = new int[257];
    tb = new boolean[256];
    ub = new boolean[16];
    vb = new byte[256];
    wb = new byte[4096];
    xb = new int[16];
    yb = new byte[18002];
    mtfTable = new byte[18002];
    ac = new byte[6][258];
    bc = new int[6][258];
    cc = new int[6][258];
    dc = new int[6][258];
    ec = new int[6];
  }

  final int j = 4096;
  final int k = 16;
  final int l = 258;
  final int m = 23;
  final int n = 0;
  final int o = 1;
  final int p = 6;
  final int q = 50;
  final int r = 4;
  final int s = 18002;
  byte t[];
  int u;
  int v;
  int w;
  int x;
  byte y[];
  int z;
  int ab;
  int bb;
  int cb;
  byte db;
  int eb;
  boolean blockRandomized;
  int gb;
  int hb;
  int ib;
  int jb;
  int kb;
  int lb;
  int mb;
  int nb[];
  int ob;
  int pb[];
  int qb[];
  public static int rb[];
  int sb;
  boolean tb[];
  boolean ub[];
  byte vb[];
  byte wb[];
  int xb[];
  byte yb[];
  byte mtfTable[];
  byte ac[][];
  int bc[][];
  int cc[][];
  int dc[][];
  int ec[];
  int fc;
}