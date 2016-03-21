package com.runescape;// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

import com.jagex.Util;

public class Config {
  public static int matchModel(String arg0) {
    if (arg0.equalsIgnoreCase("na"))
      return 0;
    for (int i = 0; i < rmb; i++)
      if (kjb[i].equalsIgnoreCase(arg0))
        return i;

    kjb[rmb++] = arg0;
    return rmb - 1;
  }

  public static int rp() {
    int i = umb[wmb] & 0xff;
    wmb++;
    return i;
  }

  public static int qp() {
    int i = Util.readInt16(umb, wmb);
    wmb += 2;
    return i;
  }

  public static int pp() {
    int i = Util.readInt32(umb, wmb);
    wmb += 4;
    if (i > 0x5f5e0ff)
      i = 0x5f5e0ff - i;
    return i;
  }

  public static String tp() {
    String s;
    for (s = ""; tmb[vmb] != 0; s = s + (char) tmb[vmb++])
      ;
    vmb++;
    return s;
  }

  public static void np(byte arg0[], boolean arg1) {
    tmb = Util.in("string.dat", 0, arg0);
    vmb = 0;
    umb = Util.in("integer.dat", 0, arg0);
    wmb = 0;
    ijb = qp();
    kjb = new String[ijb];
    ljb = new String[ijb];
    mjb = new String[ijb];
    njb = new int[ijb];
    ojb = new int[ijb];
    stackableItems = new int[ijb];
    qjb = new int[ijb];
    rjb = new int[ijb];
    sjb = new int[ijb];
    tjb = new int[ijb];
    ujb = new int[ijb];
    for (int i = 0; i < ijb; i++)
      kjb[i] = tp();

    for (int j = 0; j < ijb; j++)
      ljb[j] = tp();

    for (int k = 0; k < ijb; k++)
      mjb[k] = tp();

    for (int l = 0; l < ijb; l++) {
      njb[l] = qp();
      if (njb[l] + 1 > jjb)
        jjb = njb[l] + 1;
    }

    for (int i1 = 0; i1 < ijb; i1++)
      ojb[i1] = pp();

    for (int j1 = 0; j1 < ijb; j1++)
      stackableItems[j1] = rp();

    for (int k1 = 0; k1 < ijb; k1++)
      qjb[k1] = rp();

    for (int l1 = 0; l1 < ijb; l1++)
      rjb[l1] = qp();

    for (int i2 = 0; i2 < ijb; i2++)
      sjb[i2] = pp();

    for (int j2 = 0; j2 < ijb; j2++)
      tjb[j2] = rp();

    for (int k2 = 0; k2 < ijb; k2++)
      ujb[k2] = rp();

    for (int l2 = 0; l2 < ijb; l2++)
      if (!arg1 && ujb[l2] == 1) {
        kjb[l2] = "Members object";
        ljb[l2] = "You need to be a member to use this object";
        ojb[l2] = 0;
        mjb[l2] = "";
        qjb[0] = 0;
        rjb[l2] = 0;
        tjb[l2] = 1;
      }

    vjb = qp();
    npcNames = new String[vjb];
    xjb = new String[vjb];
    yjb = new String[vjb];
    zjb = new int[vjb];
    akb = new int[vjb];
    bkb = new int[vjb];
    ckb = new int[vjb];
    dkb = new int[vjb];
    ekb = new int[vjb][12];
    fkb = new int[vjb];
    gkb = new int[vjb];
    hkb = new int[vjb];
    ikb = new int[vjb];
    jkb = new int[vjb];
    kkb = new int[vjb];
    lkb = new int[vjb];
    mkb = new int[vjb];
    nkb = new int[vjb];
    for (int i3 = 0; i3 < vjb; i3++)
      npcNames[i3] = tp();

    for (int j3 = 0; j3 < vjb; j3++)
      xjb[j3] = tp();

    for (int k3 = 0; k3 < vjb; k3++)
      zjb[k3] = rp();

    for (int l3 = 0; l3 < vjb; l3++)
      akb[l3] = rp();

    for (int i4 = 0; i4 < vjb; i4++)
      bkb[i4] = rp();

    for (int j4 = 0; j4 < vjb; j4++)
      ckb[j4] = rp();

    for (int k4 = 0; k4 < vjb; k4++)
      dkb[k4] = rp();

    for (int l4 = 0; l4 < vjb; l4++) {
      for (int i5 = 0; i5 < 12; i5++) {
        ekb[l4][i5] = rp();
        if (ekb[l4][i5] == 255)
          ekb[l4][i5] = -1;
      }

    }

    for (int j5 = 0; j5 < vjb; j5++)
      fkb[j5] = pp();

    for (int k5 = 0; k5 < vjb; k5++)
      gkb[k5] = pp();

    for (int l5 = 0; l5 < vjb; l5++)
      hkb[l5] = pp();

    for (int i6 = 0; i6 < vjb; i6++)
      ikb[i6] = pp();

    for (int j6 = 0; j6 < vjb; j6++)
      jkb[j6] = qp();

    for (int k6 = 0; k6 < vjb; k6++)
      kkb[k6] = qp();

    for (int l6 = 0; l6 < vjb; l6++)
      lkb[l6] = rp();

    for (int i7 = 0; i7 < vjb; i7++)
      mkb[i7] = rp();

    for (int j7 = 0; j7 < vjb; j7++)
      nkb[j7] = rp();

    for (int k7 = 0; k7 < vjb; k7++)
      yjb[k7] = tp();

    okb = qp();
    pkb = new String[okb];
    qkb = new String[okb];
    for (int l7 = 0; l7 < okb; l7++)
      pkb[l7] = tp();

    for (int i8 = 0; i8 < okb; i8++)
      qkb[i8] = tp();

    rkb = qp();
    skb = new String[rkb];
    tkb = new int[rkb];
    ukb = new int[rkb];
    vkb = new int[rkb];
    wkb = new int[rkb];
    xkb = new int[rkb];
    for (int j8 = 0; j8 < rkb; j8++)
      skb[j8] = tp();

    for (int k8 = 0; k8 < rkb; k8++)
      tkb[k8] = pp();

    for (int l8 = 0; l8 < rkb; l8++)
      ukb[l8] = rp();

    for (int i9 = 0; i9 < rkb; i9++)
      vkb[i9] = rp();

    for (int j9 = 0; j9 < rkb; j9++)
      wkb[j9] = rp();

    for (int k9 = 0; k9 < rkb; k9++)
      xkb[k9] = rp();

    ykb = qp();
    zkb = new String[ykb];
    alb = new String[ykb];
    blb = new String[ykb];
    clb = new String[ykb];
    dlb = new int[ykb];
    elb = new int[ykb];
    flb = new int[ykb];
    glb = new int[ykb];
    hlb = new int[ykb];
    for (int l9 = 0; l9 < ykb; l9++)
      zkb[l9] = tp();

    for (int i10 = 0; i10 < ykb; i10++)
      alb[i10] = tp();

    for (int j10 = 0; j10 < ykb; j10++)
      blb[j10] = tp();

    for (int k10 = 0; k10 < ykb; k10++)
      clb[k10] = tp();

    for (int l10 = 0; l10 < ykb; l10++)
      dlb[l10] = matchModel(tp());

    for (int i11 = 0; i11 < ykb; i11++)
      elb[i11] = rp();

    for (int j11 = 0; j11 < ykb; j11++)
      flb[j11] = rp();

    for (int k11 = 0; k11 < ykb; k11++)
      glb[k11] = rp();

    for (int l11 = 0; l11 < ykb; l11++)
      hlb[l11] = rp();

    ilb = qp();
    jlb = new String[ilb];
    klb = new String[ilb];
    llb = new String[ilb];
    mlb = new String[ilb];
    nlb = new int[ilb];
    olb = new int[ilb];
    plb = new int[ilb];
    qlb = new int[ilb];
    rlb = new int[ilb];
    for (int i12 = 0; i12 < ilb; i12++)
      jlb[i12] = tp();

    for (int j12 = 0; j12 < ilb; j12++)
      klb[j12] = tp();

    for (int k12 = 0; k12 < ilb; k12++)
      llb[k12] = tp();

    for (int l12 = 0; l12 < ilb; l12++)
      mlb[l12] = tp();

    for (int i13 = 0; i13 < ilb; i13++)
      nlb[i13] = qp();

    for (int j13 = 0; j13 < ilb; j13++)
      olb[j13] = pp();

    for (int k13 = 0; k13 < ilb; k13++)
      plb[k13] = pp();

    for (int l13 = 0; l13 < ilb; l13++)
      qlb[l13] = rp();

    for (int i14 = 0; i14 < ilb; i14++)
      rlb[i14] = rp();

    slb = qp();
    tlb = new int[slb];
    ulb = new int[slb];
    for (int j14 = 0; j14 < slb; j14++)
      tlb[j14] = rp();

    for (int k14 = 0; k14 < slb; k14++)
      ulb[k14] = rp();

    vlb = qp();
    wlb = new int[vlb];
    xlb = new int[vlb];
    ylb = new int[vlb];
    for (int l14 = 0; l14 < vlb; l14++)
      wlb[l14] = pp();

    for (int i15 = 0; i15 < vlb; i15++)
      xlb[i15] = rp();

    for (int j15 = 0; j15 < vlb; j15++)
      ylb[j15] = rp();

    zlb = qp();
    amb = qp();
    bmb = new String[amb];
    cmb = new String[amb];
    dmb = new int[amb];
    emb = new int[amb];
    fmb = new int[amb];
    gmb = new int[amb][];
    hmb = new int[amb][];
    for (int k15 = 0; k15 < amb; k15++)
      bmb[k15] = tp();

    for (int l15 = 0; l15 < amb; l15++)
      cmb[l15] = tp();

    for (int i16 = 0; i16 < amb; i16++)
      dmb[i16] = rp();

    for (int j16 = 0; j16 < amb; j16++)
      emb[j16] = rp();

    for (int k16 = 0; k16 < amb; k16++)
      fmb[k16] = rp();

    for (int l16 = 0; l16 < amb; l16++) {
      int i17 = rp();
      gmb[l16] = new int[i17];
      for (int k17 = 0; k17 < i17; k17++)
        gmb[l16][k17] = qp();

    }

    for (int j17 = 0; j17 < amb; j17++) {
      int l17 = rp();
      hmb[j17] = new int[l17];
      for (int j18 = 0; j18 < l17; j18++)
        hmb[j17][j18] = rp();

    }

    imb = qp();
    jmb = new String[imb];
    kmb = new String[imb];
    lmb = new int[imb];
    mmb = new int[imb];
    for (int i18 = 0; i18 < imb; i18++)
      jmb[i18] = tp();

    for (int k18 = 0; k18 < imb; k18++)
      kmb[k18] = tp();

    for (int l18 = 0; l18 < imb; l18++)
      lmb[l18] = rp();

    for (int i19 = 0; i19 < imb; i19++)
      mmb[i19] = rp();

    byte abyte0[] = Util.in("words.txt", 0, arg0);
    mp(abyte0, 0);
    byte abyte1[] = Util.in("badwords.txt", 0, arg0);
    sp(abyte1, 0);
    tmb = null;
    umb = null;
  }

  public static void mp(byte arg0[], int arg1) {
    do {
      try {
        String s;
        for (s = ""; arg0[arg1] != 13; s = s + (char) arg0[arg1++])
          ;
        arg1++;
        if (arg0[arg1] == 10)
          arg1++;
        if (s.equals("-EOF-") || (s == null || s.length() <= 0))
          break;
        omb[nmb++] = s;
        continue;
      } catch (Exception _ex) {
      }
      break;
    } while (true);
    Util.shb = nmb;
    Util.thb = omb;
  }

  public static void sp(byte arg0[], int arg1) {
    do {
      try {
        String s;
        for (s = ""; arg0[arg1] != 13; s = s + (char) arg0[arg1++])
          ;
        arg1++;
        if (arg0[arg1] == 10)
          arg1++;
        if (s.equals("-EOF-") || (s == null || s.length() <= 0))
          break;
        qmb[pmb++] = s;
        continue;
      } catch (Exception _ex) {
      }
      break;
    } while (true);
    String vowels[] = { "a", "e", "i", "o", "u" };
    int i = pmb;
    for (int j = 0; j < i; j++) {
      String s1 = qmb[j];
      if (s1.length() >= 5) {
        for (int k = 1; k < s1.length() - 1; k++) {
          char c = s1.charAt(k);
          if (c == 'a' || c == 'e' || c == i || c == 'o' || c == 'u' || c == 'y') {
            for (int l = 0; l < 5; l++) {
              String s2 = vowels[l];
              if (s2.charAt(0) != c) {
                String s5 = s1.substring(0, k) + s2 + s1.substring(k + 1);
                qmb[pmb++] = s5;
                s5 = s1.substring(0, k) + s2 + c + s1.substring(k + 1);
                qmb[pmb++] = s5;
              }
            }

            String s3 = s1.substring(0, k) + s1.substring(k + 1);
            qmb[pmb++] = s3;
          }
          char c1 = s1.charAt(k + 1);
          String s4 = s1.substring(0, k) + c1 + c + s1.substring(k + 2);
          qmb[pmb++] = s4;
        }

      }
    }

    Util.qhb = pmb;
    Util.rhb = qmb;
  }

  public Config() {
  }

  public final int hjb = 0xbc614e;
  public static int ijb;
  public static int jjb;
  public static String kjb[];
  public static String ljb[];
  public static String mjb[];
  public static int njb[];
  public static int ojb[];
  public static int stackableItems[];
  public static int qjb[];
  public static int rjb[];
  public static int sjb[];
  public static int tjb[];
  public static int ujb[];
  public static int vjb;
  public static String npcNames[];
  public static String xjb[];
  public static String yjb[];
  public static int zjb[];
  public static int akb[];
  public static int bkb[];
  public static int ckb[];
  public static int dkb[];
  public static int ekb[][];
  public static int fkb[];
  public static int gkb[];
  public static int hkb[];
  public static int ikb[];
  public static int jkb[];
  public static int kkb[];
  public static int lkb[];
  public static int mkb[];
  public static int nkb[];
  public static int okb;
  public static String pkb[];
  public static String qkb[];
  public static int rkb;
  public static String skb[];
  public static int tkb[];
  public static int ukb[];
  public static int vkb[];
  public static int wkb[];
  public static int xkb[];
  public static int ykb;
  public static String zkb[];
  public static String alb[];
  public static String blb[];
  public static String clb[];
  public static int dlb[];
  public static int elb[];
  public static int flb[];
  public static int glb[];
  public static int hlb[];
  public static int ilb;
  public static String jlb[];
  public static String klb[];
  public static String llb[];
  public static String mlb[];
  public static int nlb[];
  public static int olb[];
  public static int plb[];
  public static int qlb[];
  public static int rlb[];
  public static int slb;
  public static int tlb[];
  public static int ulb[];
  public static int vlb;
  public static int wlb[];
  public static int xlb[];
  public static int ylb[];
  public static int zlb;
  public static int amb;
  public static String bmb[];
  public static String cmb[];
  public static int dmb[];
  public static int emb[];
  public static int fmb[];
  public static int gmb[][];
  public static int hmb[][];
  public static int imb;
  public static String jmb[];
  public static String kmb[];
  public static int lmb[];
  public static int mmb[];
  public static int nmb;
  public static String omb[] = new String[5000];
  public static int pmb;
  public static String qmb[] = new String[5000];
  public static int rmb;
  public static String smb[] = new String[200];
  static byte tmb[];
  static byte umb[];
  static int vmb;
  static int wmb;
}