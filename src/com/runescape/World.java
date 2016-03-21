package com.runescape;// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

import com.jagex.Util;
import com.jagex.client.Surface;
import com.jagex.client.Model;
import com.jagex.client.Scene;

import java.io.IOException;

public class World {
  public World(Scene arg0, Surface arg1) {
    xhb = false;
    yhb = true;
    bib = 750;
    eib = new int[256];
    nib = new byte[4][2304];
    oib = new byte[4][2304];
    terrainHeight = new byte[4][2304];
    terrainColor = new byte[4][2304];
    rib = new byte[4][2304];
    tileDecoration = new byte[4][2304];
    tib = new byte[4][2304];
    uib = new int[4][2304];
    vib = 96;
    wib = 96;
    xib = new int[vib * wib * 2];
    yib = new int[vib * wib * 2];
    zib = new int[vib][wib];
    ajb = new int[vib][wib];
    bjb = new int[vib][wib];
    cjb = false;
    djb = new Model[64];
    ejb = new Model[4][64];
    fjb = new Model[4][64];
    aib = arg0;
    zhb = arg1;
    for (int k = 0; k < 64; k++)
      eib[k] = Scene.fi(255 - k * 4, 255 - (int) ((double) k * 1.75D), 255 - k * 4);

    for (int l = 0; l < 64; l++)
      eib[l + 64] = Scene.fi(l * 3, 144, 0);

    for (int i1 = 0; i1 < 64; i1++)
      eib[i1 + 128] = Scene.fi(192 - (int) ((double) i1 * 1.5D), 144 - (int) ((double) i1 * 1.5D), 0);

    for (int j1 = 0; j1 < 64; j1++)
      eib[j1 + 192] = Scene.fi(96 - (int) ((double) j1 * 1.5D), 48 + (int) ((double) j1 * 1.5D), 0);

  }

  public int route(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6[], int arg7[], boolean arg8) {
    for (int k = 0; k < vib; k++) {
      for (int l = 0; l < wib; l++)
        zib[k][l] = 0;
    }

    int i1 = 0;
    int j1 = 0;
    int k1 = arg0;
    int l1 = arg1;
    zib[arg0][arg1] = 99;
    arg6[i1] = arg0;
    arg7[i1++] = arg1;
    int i2 = arg6.length;
    boolean flag = false;
    while (j1 != i1) {
      k1 = arg6[j1];
      l1 = arg7[j1];
      j1 = (j1 + 1) % i2;
      if (k1 >= arg2 && k1 <= arg4 && l1 >= arg3 && l1 <= arg5) {
        flag = true;
        break;
      }
      if (arg8) {
        if (k1 > 0 && k1 - 1 >= arg2 && k1 - 1 <= arg4 && l1 >= arg3 && l1 <= arg5 && (ajb[k1 - 1][l1] & 8) == 0) {
          flag = true;
          break;
        }
        if (k1 < vib - 1 && k1 + 1 >= arg2 && k1 + 1 <= arg4 && l1 >= arg3 && l1 <= arg5 && (ajb[k1 + 1][l1] & 2) == 0) {
          flag = true;
          break;
        }
        if (l1 > 0 && k1 >= arg2 && k1 <= arg4 && l1 - 1 >= arg3 && l1 - 1 <= arg5 && (ajb[k1][l1 - 1] & 4) == 0) {
          flag = true;
          break;
        }
        if (l1 < wib - 1 && k1 >= arg2 && k1 <= arg4 && l1 + 1 >= arg3 && l1 + 1 <= arg5 && (ajb[k1][l1 + 1] & 1) == 0) {
          flag = true;
          break;
        }
      }
      if (k1 > 0 && zib[k1 - 1][l1] == 0 && (ajb[k1 - 1][l1] & 0x78) == 0) {
        arg6[i1] = k1 - 1;
        arg7[i1] = l1;
        i1 = (i1 + 1) % i2;
        zib[k1 - 1][l1] = 2;
      }
      if (k1 < vib - 1 && zib[k1 + 1][l1] == 0 && (ajb[k1 + 1][l1] & 0x72) == 0) {
        arg6[i1] = k1 + 1;
        arg7[i1] = l1;
        i1 = (i1 + 1) % i2;
        zib[k1 + 1][l1] = 8;
      }
      if (l1 > 0 && zib[k1][l1 - 1] == 0 && (ajb[k1][l1 - 1] & 0x74) == 0) {
        arg6[i1] = k1;
        arg7[i1] = l1 - 1;
        i1 = (i1 + 1) % i2;
        zib[k1][l1 - 1] = 1;
      }
      if (l1 < wib - 1 && zib[k1][l1 + 1] == 0 && (ajb[k1][l1 + 1] & 0x71) == 0) {
        arg6[i1] = k1;
        arg7[i1] = l1 + 1;
        i1 = (i1 + 1) % i2;
        zib[k1][l1 + 1] = 4;
      }
      if (k1 > 0 && l1 > 0 && (ajb[k1][l1 - 1] & 0x74) == 0 && (ajb[k1 - 1][l1] & 0x78) == 0
          && (ajb[k1 - 1][l1 - 1] & 0x7c) == 0 && zib[k1 - 1][l1 - 1] == 0) {
        arg6[i1] = k1 - 1;
        arg7[i1] = l1 - 1;
        i1 = (i1 + 1) % i2;
        zib[k1 - 1][l1 - 1] = 3;
      }
      if (k1 < vib - 1 && l1 > 0 && (ajb[k1][l1 - 1] & 0x74) == 0 && (ajb[k1 + 1][l1] & 0x72) == 0
          && (ajb[k1 + 1][l1 - 1] & 0x76) == 0 && zib[k1 + 1][l1 - 1] == 0) {
        arg6[i1] = k1 + 1;
        arg7[i1] = l1 - 1;
        i1 = (i1 + 1) % i2;
        zib[k1 + 1][l1 - 1] = 9;
      }
      if (k1 > 0 && l1 < wib - 1 && (ajb[k1][l1 + 1] & 0x71) == 0 && (ajb[k1 - 1][l1] & 0x78) == 0
          && (ajb[k1 - 1][l1 + 1] & 0x79) == 0 && zib[k1 - 1][l1 + 1] == 0) {
        arg6[i1] = k1 - 1;
        arg7[i1] = l1 + 1;
        i1 = (i1 + 1) % i2;
        zib[k1 - 1][l1 + 1] = 6;
      }
      if (k1 < vib - 1 && l1 < wib - 1 && (ajb[k1][l1 + 1] & 0x71) == 0 && (ajb[k1 + 1][l1] & 0x72) == 0
          && (ajb[k1 + 1][l1 + 1] & 0x73) == 0 && zib[k1 + 1][l1 + 1] == 0) {
        arg6[i1] = k1 + 1;
        arg7[i1] = l1 + 1;
        i1 = (i1 + 1) % i2;
        zib[k1 + 1][l1 + 1] = 12;
      }
    }
    if (!flag)
      return -1;
    j1 = 0;
    arg6[j1] = k1;
    arg7[j1++] = l1;
    int k2;
    for (int j2 = k2 = zib[k1][l1]; k1 != arg0 || l1 != arg1; j2 = zib[k1][l1]) {
      if (j2 != k2) {
        k2 = j2;
        arg6[j1] = k1;
        arg7[j1++] = l1;
      }
      if ((j2 & 2) != 0)
        k1++;
      else if ((j2 & 8) != 0)
        k1--;
      if ((j2 & 1) != 0)
        l1++;
      else if ((j2 & 4) != 0)
        l1--;
    }

    return j1;
  }

  public void gp(int arg0, int arg1, int arg2) {
    ajb[arg0][arg1] |= arg2;
  }

  public void zo(int arg0, int arg1, int arg2) {
    ajb[arg0][arg1] &= 65535 - arg2;
  }

  public void hp(int arg0, int arg1, int arg2, int arg3) {
    if (arg0 < 0 || arg1 < 0 || arg0 >= vib - 1 || arg1 >= wib - 1)
      return;
    if (Config.qlb[arg3] == 1) {
      if (arg2 == 0) {
        ajb[arg0][arg1] |= 1;
        if (arg1 > 0)
          gp(arg0, arg1 - 1, 4);
      } else if (arg2 == 1) {
        ajb[arg0][arg1] |= 2;
        if (arg0 > 0)
          gp(arg0 - 1, arg1, 8);
      } else if (arg2 == 2)
        ajb[arg0][arg1] |= 0x10;
      else if (arg2 == 3)
        ajb[arg0][arg1] |= 0x20;
      no(arg0, arg1, 1, 1);
    }
  }

  public void fo(int arg0, int arg1, int arg2, int arg3) {
    if (arg0 < 0 || arg1 < 0 || arg0 >= vib - 1 || arg1 >= wib - 1)
      return;
    if (Config.qlb[arg3] == 1) {
      if (arg2 == 0) {
        ajb[arg0][arg1] &= 0xfffe;
        if (arg1 > 0)
          zo(arg0, arg1 - 1, 4);
      } else if (arg2 == 1) {
        ajb[arg0][arg1] &= 0xfffd;
        if (arg0 > 0)
          zo(arg0 - 1, arg1, 8);
      } else if (arg2 == 2)
        ajb[arg0][arg1] &= 0xffef;
      else if (arg2 == 3)
        ajb[arg0][arg1] &= 0xffdf;
      no(arg0, arg1, 1, 1);
    }
  }

  public void vo(int arg0, int arg1, int arg2) {
    if (arg0 < 0 || arg1 < 0 || arg0 >= vib - 1 || arg1 >= wib - 1)
      return;
    if (Config.glb[arg2] == 1 || Config.glb[arg2] == 2) {
      int k = io(arg0, arg1);
      int l;
      int i1;
      if (k == 0 || k == 4) {
        l = Config.elb[arg2];
        i1 = Config.flb[arg2];
      } else {
        i1 = Config.elb[arg2];
        l = Config.flb[arg2];
      }
      for (int j1 = arg0; j1 < arg0 + l; j1++) {
        for (int k1 = arg1; k1 < arg1 + i1; k1++)
          if (Config.glb[arg2] == 1)
            ajb[j1][k1] |= 0x40;
          else if (k == 0) {
            ajb[j1][k1] |= 2;
            if (j1 > 0)
              gp(j1 - 1, k1, 8);
          } else if (k == 2) {
            ajb[j1][k1] |= 4;
            if (k1 < wib - 1)
              gp(j1, k1 + 1, 1);
          } else if (k == 4) {
            ajb[j1][k1] |= 8;
            if (j1 < vib - 1)
              gp(j1 + 1, k1, 2);
          } else if (k == 6) {
            ajb[j1][k1] |= 1;
            if (k1 > 0)
              gp(j1, k1 - 1, 4);
          }

      }

      no(arg0, arg1, l, i1);
    }
  }

  public void fp(int arg0, int arg1, int arg2) {
    if (arg0 < 0 || arg1 < 0 || arg0 >= vib - 1 || arg1 >= wib - 1)
      return;
    if (Config.glb[arg2] == 1 || Config.glb[arg2] == 2) {
      int k = io(arg0, arg1);
      int l;
      int i1;
      if (k == 0 || k == 4) {
        l = Config.elb[arg2];
        i1 = Config.flb[arg2];
      } else {
        i1 = Config.elb[arg2];
        l = Config.flb[arg2];
      }
      for (int j1 = arg0; j1 < arg0 + l; j1++) {
        for (int k1 = arg1; k1 < arg1 + i1; k1++)
          if (Config.glb[arg2] == 1)
            ajb[j1][k1] &= 0xffbf;
          else if (k == 0) {
            ajb[j1][k1] &= 0xfffd;
            if (j1 > 0)
              zo(j1 - 1, k1, 8);
          } else if (k == 2) {
            ajb[j1][k1] &= 0xfffb;
            if (k1 < wib - 1)
              zo(j1, k1 + 1, 1);
          } else if (k == 4) {
            ajb[j1][k1] &= 0xfff7;
            if (j1 < vib - 1)
              zo(j1 + 1, k1, 2);
          } else if (k == 6) {
            ajb[j1][k1] &= 0xfffe;
            if (k1 > 0)
              zo(j1, k1 - 1, 4);
          }

      }

      no(arg0, arg1, l, i1);
    }
  }

  public void no(int arg0, int arg1, int arg2, int arg3) {
    if (arg0 < 1 || arg1 < 1 || arg0 + arg2 >= vib || arg1 + arg3 >= wib)
      return;
    for (int k = arg0; k <= arg0 + arg2; k++) {
      for (int l = arg1; l <= arg1 + arg3; l++)
        if ((lp(k, l) & 0x63) != 0 || (lp(k - 1, l) & 0x59) != 0 || (lp(k, l - 1) & 0x56) != 0
            || (lp(k - 1, l - 1) & 0x6c) != 0)
          to(k, l, 35);
        else
          to(k, l, 0);
    }
  }

  public void to(int arg0, int arg1, int arg2) {
    int k = arg0 / 12;
    int l = arg1 / 12;
    int i1 = (arg0 - 1) / 12;
    int j1 = (arg1 - 1) / 12;
    ip(k, l, arg0, arg1, arg2);
    if (k != i1)
      ip(i1, l, arg0, arg1, arg2);
    if (l != j1)
      ip(k, j1, arg0, arg1, arg2);
    if (k != i1 && l != j1)
      ip(i1, j1, arg0, arg1, arg2);
  }

  public void ip(int arg0, int arg1, int arg2, int arg3, int arg4) {
    Model h1 = djb[arg0 + arg1 * 8];
    for (int k = 0; k < h1.vertexCount; k++)
      if (h1.verticesX[k] == arg2 * 128 && h1.verticesZ[k] == arg3 * 128) {
        h1.xd(k, arg4);
        return;
      }

  }

  public int lp(int arg0, int arg1) {
    if (arg0 < 0 || arg1 < 0 || arg0 >= vib || arg1 >= wib)
      return 0;
    else
      return ajb[arg0][arg1];
  }

  public int calculateElevation(int arg0, int arg1) {
    int k = arg0 >> 7;
    int l = arg1 >> 7;
    int i1 = arg0 & 0x7f;
    int j1 = arg1 & 0x7f;
    if (k < 0 || l < 0 || k >= vib - 1 || l >= wib - 1)
      return 0;
    int k1;
    int l1;
    int i2;
    if (i1 <= 128 - j1) {
      k1 = uo(k, l);
      l1 = uo(k + 1, l) - k1;
      i2 = uo(k, l + 1) - k1;
    } else {
      k1 = uo(k + 1, l + 1);
      l1 = uo(k, l + 1) - k1;
      i2 = uo(k + 1, l) - k1;
      i1 = 128 - i1;
      j1 = 128 - j1;
    }
    int j2 = k1 + (l1 * i1) / 128 + (i2 * j1) / 128;
    return j2;
  }

  public int uo(int arg0, int arg1) {
    if (arg0 < 0 || arg0 >= 96 || arg1 < 0 || arg1 >= 96)
      return 0;
    byte byte0 = 0;
    if (arg0 >= 48 && arg1 < 48) {
      byte0 = 1;
      arg0 -= 48;
    } else if (arg0 < 48 && arg1 >= 48) {
      byte0 = 2;
      arg1 -= 48;
    } else if (arg0 >= 48 && arg1 >= 48) {
      byte0 = 3;
      arg0 -= 48;
      arg1 -= 48;
    }
    return (nib[byte0][arg0 * 48 + arg1] & 0xff) * 3;
  }

  public int go(int arg0, int arg1) {
    if (arg0 < 0 || arg0 >= 96 || arg1 < 0 || arg1 >= 96)
      return 0;
    byte byte0 = 0;
    if (arg0 >= 48 && arg1 < 48) {
      byte0 = 1;
      arg0 -= 48;
    } else if (arg0 < 48 && arg1 >= 48) {
      byte0 = 2;
      arg1 -= 48;
    } else if (arg0 >= 48 && arg1 >= 48) {
      byte0 = 3;
      arg0 -= 48;
      arg1 -= 48;
    }
    return oib[byte0][arg0 * 48 + arg1] & 0xff;
  }

  public int wo(int arg0, int arg1, int arg2) {
    if (arg0 < 0 || arg0 >= 96 || arg1 < 0 || arg1 >= 96)
      return 0;
    byte byte0 = 0;
    if (arg0 >= 48 && arg1 < 48) {
      byte0 = 1;
      arg0 -= 48;
    } else if (arg0 < 48 && arg1 >= 48) {
      byte0 = 2;
      arg1 -= 48;
    } else if (arg0 >= 48 && arg1 >= 48) {
      byte0 = 3;
      arg0 -= 48;
      arg1 -= 48;
    }
    return tileDecoration[byte0][arg0 * 48 + arg1] & 0xff;
  }

  public void so(int arg0, int arg1, int arg2) {
    if (arg0 < 0 || arg0 >= 96 || arg1 < 0 || arg1 >= 96)
      return;
    byte byte0 = 0;
    if (arg0 >= 48 && arg1 < 48) {
      byte0 = 1;
      arg0 -= 48;
    } else if (arg0 < 48 && arg1 >= 48) {
      byte0 = 2;
      arg1 -= 48;
    } else if (arg0 >= 48 && arg1 >= 48) {
      byte0 = 3;
      arg0 -= 48;
      arg1 -= 48;
    }
    tileDecoration[byte0][arg0 * 48 + arg1] = (byte) arg2;
  }

  public int cp(int arg0, int arg1, int arg2) {
    int k = wo(arg0, arg1, arg2);
    if (k == 0)
      return -1;
    int l = Config.xlb[k - 1];
    return l != 2 ? 0 : 1;
  }

  public int mo(int arg0, int arg1, int arg2, int arg3) {
    int k = wo(arg0, arg1, arg2);
    if (k == 0)
      return arg3;
    else
      return Config.wlb[k - 1];
  }

  public int lo(int arg0, int arg1) {
    if (arg0 < 0 || arg0 >= 96 || arg1 < 0 || arg1 >= 96)
      return 0;
    byte byte0 = 0;
    if (arg0 >= 48 && arg1 < 48) {
      byte0 = 1;
      arg0 -= 48;
    } else if (arg0 < 48 && arg1 >= 48) {
      byte0 = 2;
      arg1 -= 48;
    } else if (arg0 >= 48 && arg1 >= 48) {
      byte0 = 3;
      arg0 -= 48;
      arg1 -= 48;
    }
    return uib[byte0][arg0 * 48 + arg1];
  }

  public int bp(int arg0, int arg1) {
    if (arg0 < 0 || arg0 >= 96 || arg1 < 0 || arg1 >= 96)
      return 0;
    byte byte0 = 0;
    if (arg0 >= 48 && arg1 < 48) {
      byte0 = 1;
      arg0 -= 48;
    } else if (arg0 < 48 && arg1 >= 48) {
      byte0 = 2;
      arg1 -= 48;
    } else if (arg0 >= 48 && arg1 >= 48) {
      byte0 = 3;
      arg0 -= 48;
      arg1 -= 48;
    }
    return rib[byte0][arg0 * 48 + arg1];
  }

  public int io(int arg0, int arg1) {
    if (arg0 < 0 || arg0 >= 96 || arg1 < 0 || arg1 >= 96)
      return 0;
    byte byte0 = 0;
    if (arg0 >= 48 && arg1 < 48) {
      byte0 = 1;
      arg0 -= 48;
    } else if (arg0 < 48 && arg1 >= 48) {
      byte0 = 2;
      arg1 -= 48;
    } else if (arg0 >= 48 && arg1 >= 48) {
      byte0 = 3;
      arg0 -= 48;
      arg1 -= 48;
    }
    return tib[byte0][arg0 * 48 + arg1];
  }

  public boolean ep(int arg0, int arg1) {
    return bp(arg0, arg1) > 0 || bp(arg0 - 1, arg1) > 0 || bp(arg0 - 1, arg1 - 1) > 0 || bp(arg0, arg1 - 1) > 0;
  }

  public boolean dp(int arg0, int arg1) {
    return bp(arg0, arg1) > 0 && bp(arg0 - 1, arg1) > 0 && bp(arg0 - 1, arg1 - 1) > 0 && bp(arg0, arg1 - 1) > 0;
  }

  public int po(int arg0, int arg1) {
    if (arg0 < 0 || arg0 >= 96 || arg1 < 0 || arg1 >= 96)
      return 0;
    byte byte0 = 0;
    if (arg0 >= 48 && arg1 < 48) {
      byte0 = 1;
      arg0 -= 48;
    } else if (arg0 < 48 && arg1 >= 48) {
      byte0 = 2;
      arg1 -= 48;
    } else if (arg0 >= 48 && arg1 >= 48) {
      byte0 = 3;
      arg0 -= 48;
      arg1 -= 48;
    }
    return terrainColor[byte0][arg0 * 48 + arg1] & 0xff;
  }

  public int yo(int arg0, int arg1) {
    if (arg0 < 0 || arg0 >= 96 || arg1 < 0 || arg1 >= 96)
      return 0;
    byte byte0 = 0;
    if (arg0 >= 48 && arg1 < 48) {
      byte0 = 1;
      arg0 -= 48;
    } else if (arg0 < 48 && arg1 >= 48) {
      byte0 = 2;
      arg1 -= 48;
    } else if (arg0 >= 48 && arg1 >= 48) {
      byte0 = 3;
      arg0 -= 48;
      arg1 -= 48;
    }
    return terrainHeight[byte0][arg0 * 48 + arg1] & 0xff;
  }

  public void eo(int arg0, int arg1, int arg2, int chunk) {
    String s = "m" + arg2 + arg0 / 10 + arg0 % 10 + arg1 / 10 + arg1 % 10;
    int k;
    try {
      if (landscape != null) {
        byte abyte0[] = Util.in(s + ".hei", 0, landscape);
        if (abyte0 == null && memberLandscape != null)
          abyte0 = Util.in(s + ".hei", 0, memberLandscape);
        if (abyte0 != null && abyte0.length > 0) {
          int l = 0;
          int i2 = 0;
          for (int l2 = 0; l2 < 2304;) {
            int l3 = abyte0[l++] & 0xff;
            if (l3 < 128) {
              nib[chunk][l2++] = (byte) l3;
              i2 = l3;
            }
            if (l3 >= 128) {
              for (int j5 = 0; j5 < l3 - 128; j5++)
                nib[chunk][l2++] = (byte) i2;

            }
          }

          i2 = 64;
          for (int i4 = 0; i4 < 48; i4++) {
            for (int k5 = 0; k5 < 48; k5++) {
              i2 = nib[chunk][k5 * 48 + i4] + i2 & 0x7f;
              nib[chunk][k5 * 48 + i4] = (byte) (i2 * 2);
            }

          }

          i2 = 0;
          for (int l5 = 0; l5 < 2304;) {
            int i7 = abyte0[l++] & 0xff;
            if (i7 < 128) {
              oib[chunk][l5++] = (byte) i7;
              i2 = i7;
            }
            if (i7 >= 128) {
              for (int k8 = 0; k8 < i7 - 128; k8++)
                oib[chunk][l5++] = (byte) i2;

            }
          }

          i2 = 35;
          for (int j7 = 0; j7 < 48; j7++) {
            for (int l8 = 0; l8 < 48; l8++) {
              i2 = oib[chunk][l8 * 48 + j7] + i2 & 0x7f;
              oib[chunk][l8 * 48 + j7] = (byte) (i2 * 2);
            }

          }

        } else {
          for (int i1 = 0; i1 < 2304; i1++) {
            nib[chunk][i1] = 0;
            oib[chunk][i1] = 0;
          }

        }
        abyte0 = Util.in(s + ".dat", 0, maps);
        if (abyte0 == null && memberMaps != null)
          abyte0 = Util.in(s + ".dat", 0, memberMaps);
        if (abyte0 == null || abyte0.length == 0)
          throw new IOException();
        int j1 = 0;
        for (int j2 = 0; j2 < 2304;) {
          int i3 = abyte0[j1++] & 0xff;
          if (i3 < 128) {
            terrainHeight[chunk][j2++] = (byte) i3;
          } else {
            for (int j4 = 0; j4 < i3 - 128; j4++)
              terrainHeight[chunk][j2++] = 0;
          }
        }

        for (int j3 = 0; j3 < 2304;) {
          int k4 = abyte0[j1++] & 0xff;
          if (k4 < 128) {
            terrainColor[chunk][j3++] = (byte) k4;
          } else {
            for (int i6 = 0; i6 < k4 - 128; i6++)
              terrainColor[chunk][j3++] = 0;
          }
        }

        for (int l4 = 0; l4 < 2304;) {
          int j6 = abyte0[j1++] & 0xff;
          if (j6 < 128) {
            uib[chunk][l4++] = j6;
          } else {
            for (int k7 = 0; k7 < j6 - 128; k7++)
              uib[chunk][l4++] = 0;

          }
        }

        for (int k6 = 0; k6 < 2304;) {
          int l7 = abyte0[j1++] & 0xff;
          if (l7 < 128)
            uib[chunk][k6++] = l7 + 12000;
          else
            k6 += l7 - 128;
        }

        for (int i8 = 0; i8 < 2304;) {
          int i9 = abyte0[j1++] & 0xff;
          if (i9 < 128) {
            rib[chunk][i8++] = (byte) i9;
          } else {
            for (int l9 = 0; l9 < i9 - 128; l9++)
              rib[chunk][i8++] = 0;
          }
        }

        int j9 = 0;
        for (int i10 = 0; i10 < 2304;) {
          int k10 = abyte0[j1++] & 0xff;
          if (k10 < 128) {
            tileDecoration[chunk][i10++] = (byte) k10;
            j9 = k10;
          } else {
            for (int j11 = 0; j11 < k10 - 128; j11++)
              tileDecoration[chunk][i10++] = (byte) j9;
          }
        }

        for (int l10 = 0; l10 < 2304;) {
          int k11 = abyte0[j1++] & 0xff;
          if (k11 < 128) {
            tib[chunk][l10++] = (byte) k11;
          } else {
            for (int j12 = 0; j12 < k11 - 128; j12++)
              tib[chunk][l10++] = 0;
          }
        }

        abyte0 = Util.in(s + ".loc", 0, maps);
        if (abyte0 != null && abyte0.length > 0) {
          int k1 = 0;
          for (int l11 = 0; l11 < 2304;) {
            int k12 = abyte0[k1++] & 0xff;
            if (k12 < 128)
              uib[chunk][l11++] = k12 + 48000;
            else
              l11 += k12 - 128;
          }

          return;
        }
      } else {
        byte abyte1[] = new byte[20736];
        Util.readFully("../gamedata/maps/" + s + ".jm", abyte1, 20736);
        int l1 = 0;
        int k2 = 0;
        for (int k3 = 0; k3 < 2304; k3++) {
          l1 = l1 + abyte1[k2++] & 0xff;
          nib[chunk][k3] = (byte) l1;
        }

        l1 = 0;
        for (int i5 = 0; i5 < 2304; i5++) {
          l1 = l1 + abyte1[k2++] & 0xff;
          oib[chunk][i5] = (byte) l1;
        }

        for (int l6 = 0; l6 < 2304; l6++)
          terrainHeight[chunk][l6] = abyte1[k2++];

        for (int j8 = 0; j8 < 2304; j8++)
          terrainColor[chunk][j8] = abyte1[k2++];

        for (int k9 = 0; k9 < 2304; k9++) {
          uib[chunk][k9] = (abyte1[k2] & 0xff) * 256 + (abyte1[k2 + 1] & 0xff);
          k2 += 2;
        }

        for (int j10 = 0; j10 < 2304; j10++)
          rib[chunk][j10] = abyte1[k2++];

        for (int i11 = 0; i11 < 2304; i11++)
          tileDecoration[chunk][i11] = abyte1[k2++];

        for (int i12 = 0; i12 < 2304; i12++)
          tib[chunk][i12] = abyte1[k2++];

      }
      return;
    } catch (IOException _ex) {
      k = 0;
    }
    for (; k < 2304; k++) {
      nib[chunk][k] = 0;
      oib[chunk][k] = 0;
      terrainHeight[chunk][k] = 0;
      terrainColor[chunk][k] = 0;
      uib[chunk][k] = 0;
      rib[chunk][k] = 0;
      tileDecoration[chunk][k] = 0;
      if (arg2 == 0)
        tileDecoration[chunk][k] = -6;
      if (arg2 == 3)
        tileDecoration[chunk][k] = 8;
      tib[chunk][k] = 0;
    }

  }

  public void kp() {
    if (yhb)
      aib.si();
    for (int k = 0; k < 64; k++) {
      djb[k] = null;
      for (int l = 0; l < 4; l++)
        ejb[l][k] = null;

      for (int i1 = 0; i1 < 4; i1++)
        fjb[i1][k] = null;

    }

    System.gc();
  }

  public void xo(int arg0, int arg1, int arg2) {
    kp();
    int k = (arg0 + 24) / 48;
    int l = (arg1 + 24) / 48;
    ko(arg0, arg1, arg2, true);
    if (arg2 == 0) {
      ko(arg0, arg1, 1, false);
      ko(arg0, arg1, 2, false);
      eo(k - 1, l - 1, arg2, 0);
      eo(k, l - 1, arg2, 1);
      eo(k - 1, l, arg2, 2);
      eo(k, l, arg2, 3);
      ro();
    }
  }

  public void ro() {
    for (int k = 0; k < 96; k++) {
      for (int l = 0; l < 96; l++)
        if (wo(k, l, 0) == 250)
          if (k == 47 && wo(k + 1, l, 0) != 250 && wo(k + 1, l, 0) != 2)
            so(k, l, 9);
          else if (l == 47 && wo(k, l + 1, 0) != 250 && wo(k, l + 1, 0) != 2)
            so(k, l, 9);
          else
            so(k, l, 2);

    }

  }

  public void jo(int arg0, int arg1, int arg2, int arg3, int arg4) {
    int k = arg0 * 3;
    int l = arg1 * 3;
    int i1 = aib.ti(arg3);
    int j1 = aib.ti(arg4);
    i1 = i1 >> 1 & 0x7f7f7f;
    j1 = j1 >> 1 & 0x7f7f7f;
    if (arg2 == 0) {
      zhb.rg(k, l, 3, i1);
      zhb.rg(k, l + 1, 2, i1);
      zhb.rg(k, l + 2, 1, i1);
      zhb.rg(k + 2, l + 1, 1, j1);
      zhb.rg(k + 1, l + 2, 2, j1);
      return;
    }
    if (arg2 == 1) {
      zhb.rg(k, l, 3, j1);
      zhb.rg(k + 1, l + 1, 2, j1);
      zhb.rg(k + 2, l + 2, 1, j1);
      zhb.rg(k, l + 1, 1, i1);
      zhb.rg(k, l + 2, 2, i1);
    }
  }

  public void ko(int arg0, int arg1, int arg2, boolean arg3) {
    int k = (arg0 + 24) / 48;
    int l = (arg1 + 24) / 48;
    eo(k - 1, l - 1, arg2, 0);
    eo(k, l - 1, arg2, 1);
    eo(k - 1, l, arg2, 2);
    eo(k, l, arg2, 3);
    ro();
    if (gjb == null)
      gjb = new Model(vib * wib * 2 + 256, vib * wib * 2 + 256, true, true, false, false, true);
    if (arg3) {
      zhb.lf();
      for (int i1 = 0; i1 < 96; i1++) {
        for (int k1 = 0; k1 < 96; k1++)
          ajb[i1][k1] = 0;

      }

      Model h1 = gjb;
      h1.xe();
      for (int i2 = 0; i2 < 96; i2++) {
        for (int l2 = 0; l2 < 96; l2++) {
          int l3 = -uo(i2, l2);
          if (wo(i2, l2, arg2) > 0 && Config.xlb[wo(i2, l2, arg2) - 1] == 4)
            l3 = 0;
          if (wo(i2 - 1, l2, arg2) > 0 && Config.xlb[wo(i2 - 1, l2, arg2) - 1] == 4)
            l3 = 0;
          if (wo(i2, l2 - 1, arg2) > 0 && Config.xlb[wo(i2, l2 - 1, arg2) - 1] == 4)
            l3 = 0;
          if (wo(i2 - 1, l2 - 1, arg2) > 0 && Config.xlb[wo(i2 - 1, l2 - 1, arg2) - 1] == 4)
            l3 = 0;
          int i5 = h1.oe(i2 * 128, l3, l2 * 128);
          int i7 = (int) (Math.random() * 10D) - 5;
          h1.xd(i5, i7);
        }

      }

      for (int i3 = 0; i3 < 95; i3++) {
        for (int i4 = 0; i4 < 95; i4++) {
          int j5 = go(i3, i4);
          int j7 = eib[j5];
          int l9 = j7;
          int j12 = j7;
          int k14 = 0;
          if (arg2 == 1 || arg2 == 2) {
            j7 = 0xbc614e;
            l9 = 0xbc614e;
            j12 = 0xbc614e;
          }
          if (wo(i3, i4, arg2) > 0) {
            int k16 = wo(i3, i4, arg2);
            int k5 = Config.xlb[k16 - 1];
            int l18 = cp(i3, i4, arg2);
            j7 = l9 = Config.wlb[k16 - 1];
            if (k5 == 4) {
              j7 = 1;
              l9 = 1;
              if (k16 == 12) {
                j7 = 31;
                l9 = 31;
              }
            }
            if (k5 == 5) {
              if (lo(i3, i4) > 0 && lo(i3, i4) < 24000)
                if (mo(i3 - 1, i4, arg2, j12) != 0xbc614e && mo(i3, i4 - 1, arg2, j12) != 0xbc614e) {
                  j7 = mo(i3 - 1, i4, arg2, j12);
                  k14 = 0;
                } else if (mo(i3 + 1, i4, arg2, j12) != 0xbc614e && mo(i3, i4 + 1, arg2, j12) != 0xbc614e) {
                  l9 = mo(i3 + 1, i4, arg2, j12);
                  k14 = 0;
                } else if (mo(i3 + 1, i4, arg2, j12) != 0xbc614e && mo(i3, i4 - 1, arg2, j12) != 0xbc614e) {
                  l9 = mo(i3 + 1, i4, arg2, j12);
                  k14 = 1;
                } else if (mo(i3 - 1, i4, arg2, j12) != 0xbc614e && mo(i3, i4 + 1, arg2, j12) != 0xbc614e) {
                  j7 = mo(i3 - 1, i4, arg2, j12);
                  k14 = 1;
                }
            } else if (k5 != 2 || lo(i3, i4) > 0 && lo(i3, i4) < 24000)
              if (cp(i3 - 1, i4, arg2) != l18 && cp(i3, i4 - 1, arg2) != l18) {
                j7 = j12;
                k14 = 0;
              } else if (cp(i3 + 1, i4, arg2) != l18 && cp(i3, i4 + 1, arg2) != l18) {
                l9 = j12;
                k14 = 0;
              } else if (cp(i3 + 1, i4, arg2) != l18 && cp(i3, i4 - 1, arg2) != l18) {
                l9 = j12;
                k14 = 1;
              } else if (cp(i3 - 1, i4, arg2) != l18 && cp(i3, i4 + 1, arg2) != l18) {
                j7 = j12;
                k14 = 1;
              }
            if (Config.ylb[k16 - 1] != 0)
              ajb[i3][i4] |= 0x40;
            if (Config.xlb[k16 - 1] == 2)
              ajb[i3][i4] |= 0x80;
          }
          jo(i3, i4, k14, j7, l9);
          int l16 = ((uo(i3 + 1, i4 + 1) - uo(i3 + 1, i4)) + uo(i3, i4 + 1)) - uo(i3, i4);
          if (j7 != l9 || l16 != 0) {
            int ai[] = new int[3];
            int ai7[] = new int[3];
            if (k14 == 0) {
              if (j7 != 0xbc614e) {
                ai[0] = i4 + i3 * vib + vib;
                ai[1] = i4 + i3 * vib;
                ai[2] = i4 + i3 * vib + 1;
                int k21 = h1.ne(3, ai, 0xbc614e, j7);
                xib[k21] = i3;
                yib[k21] = i4;
                h1.vh[k21] = 0x30d40 + k21;
              }
              if (l9 != 0xbc614e) {
                ai7[0] = i4 + i3 * vib + 1;
                ai7[1] = i4 + i3 * vib + vib + 1;
                ai7[2] = i4 + i3 * vib + vib;
                int l21 = h1.ne(3, ai7, 0xbc614e, l9);
                xib[l21] = i3;
                yib[l21] = i4;
                h1.vh[l21] = 0x30d40 + l21;
              }
            } else {
              if (j7 != 0xbc614e) {
                ai[0] = i4 + i3 * vib + 1;
                ai[1] = i4 + i3 * vib + vib + 1;
                ai[2] = i4 + i3 * vib;
                int i22 = h1.ne(3, ai, 0xbc614e, j7);
                xib[i22] = i3;
                yib[i22] = i4;
                h1.vh[i22] = 0x30d40 + i22;
              }
              if (l9 != 0xbc614e) {
                ai7[0] = i4 + i3 * vib + vib;
                ai7[1] = i4 + i3 * vib;
                ai7[2] = i4 + i3 * vib + vib + 1;
                int j22 = h1.ne(3, ai7, 0xbc614e, l9);
                xib[j22] = i3;
                yib[j22] = i4;
                h1.vh[j22] = 0x30d40 + j22;
              }
            }
          } else if (j7 != 0xbc614e) {
            int ai1[] = new int[4];
            ai1[0] = i4 + i3 * vib + vib;
            ai1[1] = i4 + i3 * vib;
            ai1[2] = i4 + i3 * vib + 1;
            ai1[3] = i4 + i3 * vib + vib + 1;
            int k19 = h1.ne(4, ai1, 0xbc614e, j7);
            xib[k19] = i3;
            yib[k19] = i4;
            h1.vh[k19] = 0x30d40 + k19;
          }
        }

      }

      for (int j4 = 1; j4 < 95; j4++) {
        for (int l5 = 1; l5 < 95; l5++)
          if (wo(j4, l5, arg2) > 0 && Config.xlb[wo(j4, l5, arg2) - 1] == 4) {
            int k7 = Config.wlb[wo(j4, l5, arg2) - 1];
            int i10 = h1.oe(j4 * 128, -uo(j4, l5), l5 * 128);
            int k12 = h1.oe((j4 + 1) * 128, -uo(j4 + 1, l5), l5 * 128);
            int l14 = h1.oe((j4 + 1) * 128, -uo(j4 + 1, l5 + 1), (l5 + 1) * 128);
            int i17 = h1.oe(j4 * 128, -uo(j4, l5 + 1), (l5 + 1) * 128);
            int ai2[] = { i10, k12, l14, i17 };
            int l19 = h1.ne(4, ai2, k7, 0xbc614e);
            xib[l19] = j4;
            yib[l19] = l5;
            h1.vh[l19] = 0x30d40 + l19;
            jo(j4, l5, 0, k7, k7);
          } else if (wo(j4, l5, arg2) == 0 || Config.xlb[wo(j4, l5, arg2) - 1] != 3) {
            if (wo(j4, l5 + 1, arg2) > 0 && Config.xlb[wo(j4, l5 + 1, arg2) - 1] == 4) {
              int l7 = Config.wlb[wo(j4, l5 + 1, arg2) - 1];
              int j10 = h1.oe(j4 * 128, -uo(j4, l5), l5 * 128);
              int l12 = h1.oe((j4 + 1) * 128, -uo(j4 + 1, l5), l5 * 128);
              int i15 = h1.oe((j4 + 1) * 128, -uo(j4 + 1, l5 + 1), (l5 + 1) * 128);
              int j17 = h1.oe(j4 * 128, -uo(j4, l5 + 1), (l5 + 1) * 128);
              int ai3[] = { j10, l12, i15, j17 };
              int i20 = h1.ne(4, ai3, l7, 0xbc614e);
              xib[i20] = j4;
              yib[i20] = l5;
              h1.vh[i20] = 0x30d40 + i20;
              jo(j4, l5, 0, l7, l7);
            }
            if (wo(j4, l5 - 1, arg2) > 0 && Config.xlb[wo(j4, l5 - 1, arg2) - 1] == 4) {
              int i8 = Config.wlb[wo(j4, l5 - 1, arg2) - 1];
              int k10 = h1.oe(j4 * 128, -uo(j4, l5), l5 * 128);
              int i13 = h1.oe((j4 + 1) * 128, -uo(j4 + 1, l5), l5 * 128);
              int j15 = h1.oe((j4 + 1) * 128, -uo(j4 + 1, l5 + 1), (l5 + 1) * 128);
              int k17 = h1.oe(j4 * 128, -uo(j4, l5 + 1), (l5 + 1) * 128);
              int ai4[] = { k10, i13, j15, k17 };
              int j20 = h1.ne(4, ai4, i8, 0xbc614e);
              xib[j20] = j4;
              yib[j20] = l5;
              h1.vh[j20] = 0x30d40 + j20;
              jo(j4, l5, 0, i8, i8);
            }
            if (wo(j4 + 1, l5, arg2) > 0 && Config.xlb[wo(j4 + 1, l5, arg2) - 1] == 4) {
              int j8 = Config.wlb[wo(j4 + 1, l5, arg2) - 1];
              int l10 = h1.oe(j4 * 128, -uo(j4, l5), l5 * 128);
              int j13 = h1.oe((j4 + 1) * 128, -uo(j4 + 1, l5), l5 * 128);
              int k15 = h1.oe((j4 + 1) * 128, -uo(j4 + 1, l5 + 1), (l5 + 1) * 128);
              int l17 = h1.oe(j4 * 128, -uo(j4, l5 + 1), (l5 + 1) * 128);
              int ai5[] = { l10, j13, k15, l17 };
              int k20 = h1.ne(4, ai5, j8, 0xbc614e);
              xib[k20] = j4;
              yib[k20] = l5;
              h1.vh[k20] = 0x30d40 + k20;
              jo(j4, l5, 0, j8, j8);
            }
            if (wo(j4 - 1, l5, arg2) > 0 && Config.xlb[wo(j4 - 1, l5, arg2) - 1] == 4) {
              int k8 = Config.wlb[wo(j4 - 1, l5, arg2) - 1];
              int i11 = h1.oe(j4 * 128, -uo(j4, l5), l5 * 128);
              int k13 = h1.oe((j4 + 1) * 128, -uo(j4 + 1, l5), l5 * 128);
              int l15 = h1.oe((j4 + 1) * 128, -uo(j4 + 1, l5 + 1), (l5 + 1) * 128);
              int i18 = h1.oe(j4 * 128, -uo(j4, l5 + 1), (l5 + 1) * 128);
              int ai6[] = { i11, k13, l15, i18 };
              int l20 = h1.ne(4, ai6, k8, 0xbc614e);
              xib[l20] = j4;
              yib[l20] = l5;
              h1.vh[l20] = 0x30d40 + l20;
              jo(j4, l5, 0, k8, k8);
            }
          }

      }

      h1.se(true, 40, 48, -50, -10, -50);
      djb = gjb.ud(0, 0, 1536, 1536, 8, 64, 233, false);
      for (int i6 = 0; i6 < 64; i6++)
        aib.addModel(djb[i6]);

      for (int l8 = 0; l8 < 96; l8++) {
        for (int j11 = 0; j11 < 96; j11++)
          bjb[l8][j11] = uo(l8, j11);

      }

    }
    gjb.xe();
    int j1 = 0x606060;
    for (int l1 = 0; l1 < 95; l1++) {
      for (int j2 = 0; j2 < 95; j2++) {
        int j3 = po(l1, j2);
        if (j3 > 0 && (Config.rlb[j3 - 1] == 0 || xhb)) {
          jp(gjb, j3 - 1, l1, j2, l1 + 1, j2);
          if (arg3 && Config.qlb[j3 - 1] != 0) {
            ajb[l1][j2] |= 1;
            if (j2 > 0)
              gp(l1, j2 - 1, 4);
          }
          if (arg3)
            zhb.rg(l1 * 3, j2 * 3, 3, j1);
        }
        j3 = yo(l1, j2);
        if (j3 > 0 && (Config.rlb[j3 - 1] == 0 || xhb)) {
          jp(gjb, j3 - 1, l1, j2, l1, j2 + 1);
          if (arg3 && Config.qlb[j3 - 1] != 0) {
            ajb[l1][j2] |= 2;
            if (l1 > 0)
              gp(l1 - 1, j2, 8);
          }
          if (arg3)
            zhb.vg(l1 * 3, j2 * 3, 3, j1);
        }
        j3 = lo(l1, j2);
        if (j3 > 0 && j3 < 12000 && (Config.rlb[j3 - 1] == 0 || xhb)) {
          jp(gjb, j3 - 1, l1, j2, l1 + 1, j2 + 1);
          if (arg3 && Config.qlb[j3 - 1] != 0)
            ajb[l1][j2] |= 0x20;
          if (arg3) {
            zhb.lg(l1 * 3, j2 * 3, j1);
            zhb.lg(l1 * 3 + 1, j2 * 3 + 1, j1);
            zhb.lg(l1 * 3 + 2, j2 * 3 + 2, j1);
          }
        }
        if (j3 > 12000 && j3 < 24000 && (Config.rlb[j3 - 12001] == 0 || xhb)) {
          jp(gjb, j3 - 12001, l1 + 1, j2, l1, j2 + 1);
          if (arg3 && Config.qlb[j3 - 12001] != 0)
            ajb[l1][j2] |= 0x10;
          if (arg3) {
            zhb.lg(l1 * 3 + 2, j2 * 3, j1);
            zhb.lg(l1 * 3 + 1, j2 * 3 + 1, j1);
            zhb.lg(l1 * 3, j2 * 3 + 2, j1);
          }
        }
      }

    }

    if (arg3)
      zhb.kf(bib - 1, 0, 0, 285, 285);
    gjb.se(false, 60, 24, -50, -10, -50);
    ejb[arg2] = gjb.ud(0, 0, 1536, 1536, 8, 64, 338, true);
    for (int k2 = 0; k2 < 64; k2++)
      aib.addModel(ejb[arg2][k2]);

    for (int k3 = 0; k3 < 95; k3++) {
      for (int k4 = 0; k4 < 95; k4++) {
        int j6 = po(k3, k4);
        if (j6 > 0)
          ap(j6 - 1, k3, k4, k3 + 1, k4);
        j6 = yo(k3, k4);
        if (j6 > 0)
          ap(j6 - 1, k3, k4, k3, k4 + 1);
        j6 = lo(k3, k4);
        if (j6 > 0 && j6 < 12000)
          ap(j6 - 1, k3, k4, k3 + 1, k4 + 1);
        if (j6 > 12000 && j6 < 24000)
          ap(j6 - 12001, k3 + 1, k4, k3, k4 + 1);
      }

    }

    for (int l4 = 1; l4 < 95; l4++) {
      for (int k6 = 1; k6 < 95; k6++) {
        int i9 = bp(l4, k6);
        if (i9 > 0) {
          int k11 = l4;
          int l13 = k6;
          int i16 = l4 + 1;
          int j18 = k6;
          int i19 = l4 + 1;
          int i21 = k6 + 1;
          int k22 = l4;
          int i23 = k6 + 1;
          int k23 = 0;
          int i24 = bjb[k11][l13];
          int k24 = bjb[i16][j18];
          int i25 = bjb[i19][i21];
          int k25 = bjb[k22][i23];
          if (i24 > 0x13880)
            i24 -= 0x13880;
          if (k24 > 0x13880)
            k24 -= 0x13880;
          if (i25 > 0x13880)
            i25 -= 0x13880;
          if (k25 > 0x13880)
            k25 -= 0x13880;
          if (i24 > k23)
            k23 = i24;
          if (k24 > k23)
            k23 = k24;
          if (i25 > k23)
            k23 = i25;
          if (k25 > k23)
            k23 = k25;
          if (k23 >= 0x13880)
            k23 -= 0x13880;
          if (i24 < 0x13880)
            bjb[k11][l13] = k23;
          else
            bjb[k11][l13] -= 0x13880;
          if (k24 < 0x13880)
            bjb[i16][j18] = k23;
          else
            bjb[i16][j18] -= 0x13880;
          if (i25 < 0x13880)
            bjb[i19][i21] = k23;
          else
            bjb[i19][i21] -= 0x13880;
          if (k25 < 0x13880)
            bjb[k22][i23] = k23;
          else
            bjb[k22][i23] -= 0x13880;
        }
      }

    }

    gjb.xe();
    for (int l6 = 1; l6 < 95; l6++) {
      for (int j9 = 1; j9 < 95; j9++) {
        int l11 = bp(l6, j9);
        if (l11 > 0) {
          int i14 = l6;
          int j16 = j9;
          int k18 = l6 + 1;
          int j19 = j9;
          int j21 = l6 + 1;
          int l22 = j9 + 1;
          int j23 = l6;
          int l23 = j9 + 1;
          int j24 = l6 * 128;
          int l24 = j9 * 128;
          int j25 = j24 + 128;
          int l25 = l24 + 128;
          int i26 = j24;
          int j26 = l24;
          int k26 = j25;
          int l26 = l25;
          int i27 = bjb[i14][j16];
          int j27 = bjb[k18][j19];
          int k27 = bjb[j21][l22];
          int l27 = bjb[j23][l23];
          int i28 = Config.tlb[l11 - 1];
          if (dp(i14, j16) && i27 < 0x13880) {
            i27 += i28 + 0x13880;
            bjb[i14][j16] = i27;
          }
          if (dp(k18, j19) && j27 < 0x13880) {
            j27 += i28 + 0x13880;
            bjb[k18][j19] = j27;
          }
          if (dp(j21, l22) && k27 < 0x13880) {
            k27 += i28 + 0x13880;
            bjb[j21][l22] = k27;
          }
          if (dp(j23, l23) && l27 < 0x13880) {
            l27 += i28 + 0x13880;
            bjb[j23][l23] = l27;
          }
          if (i27 >= 0x13880)
            i27 -= 0x13880;
          if (j27 >= 0x13880)
            j27 -= 0x13880;
          if (k27 >= 0x13880)
            k27 -= 0x13880;
          if (l27 >= 0x13880)
            l27 -= 0x13880;
          byte byte0 = 16;
          if (!ep(i14 - 1, j16))
            j24 -= byte0;
          if (!ep(i14 + 1, j16))
            j24 += byte0;
          if (!ep(i14, j16 - 1))
            l24 -= byte0;
          if (!ep(i14, j16 + 1))
            l24 += byte0;
          if (!ep(k18 - 1, j19))
            j25 -= byte0;
          if (!ep(k18 + 1, j19))
            j25 += byte0;
          if (!ep(k18, j19 - 1))
            j26 -= byte0;
          if (!ep(k18, j19 + 1))
            j26 += byte0;
          if (!ep(j21 - 1, l22))
            k26 -= byte0;
          if (!ep(j21 + 1, l22))
            k26 += byte0;
          if (!ep(j21, l22 - 1))
            l25 -= byte0;
          if (!ep(j21, l22 + 1))
            l25 += byte0;
          if (!ep(j23 - 1, l23))
            i26 -= byte0;
          if (!ep(j23 + 1, l23))
            i26 += byte0;
          if (!ep(j23, l23 - 1))
            l26 -= byte0;
          if (!ep(j23, l23 + 1))
            l26 += byte0;
          l11 = Config.ulb[l11 - 1];
          i27 = -i27;
          j27 = -j27;
          k27 = -k27;
          l27 = -l27;
          if (lo(l6, j9) > 12000 && lo(l6, j9) < 24000 && bp(l6 - 1, j9 - 1) == 0) {
            int ai8[] = new int[3];
            ai8[0] = gjb.oe(k26, k27, l25);
            ai8[1] = gjb.oe(i26, l27, l26);
            ai8[2] = gjb.oe(j25, j27, j26);
            gjb.ne(3, ai8, l11, 0xbc614e);
          } else if (lo(l6, j9) > 12000 && lo(l6, j9) < 24000 && bp(l6 + 1, j9 + 1) == 0) {
            int ai9[] = new int[3];
            ai9[0] = gjb.oe(j24, i27, l24);
            ai9[1] = gjb.oe(j25, j27, j26);
            ai9[2] = gjb.oe(i26, l27, l26);
            gjb.ne(3, ai9, l11, 0xbc614e);
          } else if (lo(l6, j9) > 0 && lo(l6, j9) < 12000 && bp(l6 + 1, j9 - 1) == 0) {
            int ai10[] = new int[3];
            ai10[0] = gjb.oe(i26, l27, l26);
            ai10[1] = gjb.oe(j24, i27, l24);
            ai10[2] = gjb.oe(k26, k27, l25);
            gjb.ne(3, ai10, l11, 0xbc614e);
          } else if (lo(l6, j9) > 0 && lo(l6, j9) < 12000 && bp(l6 - 1, j9 + 1) == 0) {
            int ai11[] = new int[3];
            ai11[0] = gjb.oe(j25, j27, j26);
            ai11[1] = gjb.oe(k26, k27, l25);
            ai11[2] = gjb.oe(j24, i27, l24);
            gjb.ne(3, ai11, l11, 0xbc614e);
          } else if (i27 == j27 && k27 == l27) {
            int ai12[] = new int[4];
            ai12[0] = gjb.oe(j24, i27, l24);
            ai12[1] = gjb.oe(j25, j27, j26);
            ai12[2] = gjb.oe(k26, k27, l25);
            ai12[3] = gjb.oe(i26, l27, l26);
            gjb.ne(4, ai12, l11, 0xbc614e);
          } else if (i27 == l27 && j27 == k27) {
            int ai13[] = new int[4];
            ai13[0] = gjb.oe(i26, l27, l26);
            ai13[1] = gjb.oe(j24, i27, l24);
            ai13[2] = gjb.oe(j25, j27, j26);
            ai13[3] = gjb.oe(k26, k27, l25);
            gjb.ne(4, ai13, l11, 0xbc614e);
          } else {
            boolean flag = true;
            if (bp(l6 - 1, j9 - 1) > 0)
              flag = false;
            if (bp(l6 + 1, j9 + 1) > 0)
              flag = false;
            if (!flag) {
              int ai14[] = new int[3];
              ai14[0] = gjb.oe(j25, j27, j26);
              ai14[1] = gjb.oe(k26, k27, l25);
              ai14[2] = gjb.oe(j24, i27, l24);
              gjb.ne(3, ai14, l11, 0xbc614e);
              int ai16[] = new int[3];
              ai16[0] = gjb.oe(i26, l27, l26);
              ai16[1] = gjb.oe(j24, i27, l24);
              ai16[2] = gjb.oe(k26, k27, l25);
              gjb.ne(3, ai16, l11, 0xbc614e);
            } else {
              int ai15[] = new int[3];
              ai15[0] = gjb.oe(j24, i27, l24);
              ai15[1] = gjb.oe(j25, j27, j26);
              ai15[2] = gjb.oe(i26, l27, l26);
              gjb.ne(3, ai15, l11, 0xbc614e);
              int ai17[] = new int[3];
              ai17[0] = gjb.oe(k26, k27, l25);
              ai17[1] = gjb.oe(i26, l27, l26);
              ai17[2] = gjb.oe(j25, j27, j26);
              gjb.ne(3, ai17, l11, 0xbc614e);
            }
          }
        }
      }

    }

    gjb.se(true, 50, 50, -50, -10, -50);
    fjb[arg2] = gjb.ud(0, 0, 1536, 1536, 8, 64, 169, true);
    for (int k9 = 0; k9 < 64; k9++)
      aib.addModel(fjb[arg2][k9]);

    for (int i12 = 0; i12 < 96; i12++) {
      for (int j14 = 0; j14 < 96; j14++)
        if (bjb[i12][j14] >= 0x13880)
          bjb[i12][j14] -= 0x13880;

    }

  }

  public void qo(Model arg0[]) {
    for (int k = 0; k < vib - 2; k++) {
      for (int l = 0; l < wib - 2; l++)
        if (lo(k, l) > 48000 && lo(k, l) < 60000) {
          int i1 = lo(k, l) - 48001;
          int j1 = io(k, l);
          int k1;
          int l1;
          if (j1 == 0 || j1 == 4) {
            k1 = Config.elb[i1];
            l1 = Config.flb[i1];
          } else {
            l1 = Config.elb[i1];
            k1 = Config.flb[i1];
          }
          vo(k, l, i1);
          Model h1 = arg0[Config.dlb[i1]].ue(false, true, false, false);
          int i2 = ((k + k + k1) * 128) / 2;
          int k2 = ((l + l + l1) * 128) / 2;
          h1.zd(i2, -calculateElevation(i2, k2), k2);
          h1.ke(0, io(k, l) * 32, 0);
          aib.addModel(h1);
          h1.be(48, 48, -50, -10, -50);
          if (k1 > 1 || l1 > 1) {
            for (int i3 = k; i3 < k + k1; i3++) {
              for (int j3 = l; j3 < l + l1; j3++)
                if ((i3 > k || j3 > l) && lo(i3, j3) - 48001 == i1) {
                  int j2 = i3;
                  int l2 = j3;
                  byte byte0 = 0;
                  if (j2 >= 48 && l2 < 48) {
                    byte0 = 1;
                    j2 -= 48;
                  } else if (j2 < 48 && l2 >= 48) {
                    byte0 = 2;
                    l2 -= 48;
                  } else if (j2 >= 48 && l2 >= 48) {
                    byte0 = 3;
                    j2 -= 48;
                    l2 -= 48;
                  }
                  uib[byte0][j2 * 48 + l2] = 0;
                }

            }

          }
        }

    }

  }

  public void jp(Model arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
    to(arg2, arg3, 40);
    to(arg4, arg5, 40);
    int k = Config.nlb[arg1];
    int l = Config.olb[arg1];
    int i1 = Config.plb[arg1];
    int j1 = arg2 * 128;
    int k1 = arg3 * 128;
    int l1 = arg4 * 128;
    int i2 = arg5 * 128;
    int j2 = arg0.oe(j1, -bjb[arg2][arg3], k1);
    int k2 = arg0.oe(j1, -bjb[arg2][arg3] - k, k1);
    int l2 = arg0.oe(l1, -bjb[arg4][arg5] - k, i2);
    int i3 = arg0.oe(l1, -bjb[arg4][arg5], i2);
    int ai[] = { j2, k2, l2, i3 };
    int j3 = arg0.ne(4, ai, l, i1);
    if (Config.rlb[arg1] == 5) {
      arg0.vh[j3] = 30000 + arg1;
      return;
    } else {
      arg0.vh[j3] = 0;
      return;
    }
  }

  public void ap(int arg0, int arg1, int arg2, int arg3, int arg4) {
    int k = Config.nlb[arg0];
    if (bjb[arg1][arg2] < 0x13880)
      bjb[arg1][arg2] += 0x13880 + k;
    if (bjb[arg3][arg4] < 0x13880)
      bjb[arg3][arg4] += 0x13880 + k;
  }

  boolean xhb;
  boolean yhb;
  Surface zhb;
  Scene aib;
  int bib;
  final int cib = 0xbc614e;
  final int dib = 128;
  int eib[];
  int fib;
  int gib[];
  int hib[];
  int iib[];
  byte landscape[];
  byte maps[];
  byte memberLandscape[];
  byte memberMaps[];
  byte nib[][];
  byte oib[][];
  byte terrainHeight[][];
  byte terrainColor[][];
  byte rib[][];
  byte tileDecoration[][];
  byte tib[][];
  int uib[][];
  int vib;
  int wib;
  int xib[];
  int yib[];
  int zib[][];
  int ajb[][];
  int bjb[][];
  boolean cjb;
  Model djb[];
  Model ejb[][];
  Model fjb[][];
  Model gjb;
}