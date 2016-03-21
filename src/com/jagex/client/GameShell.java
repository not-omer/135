// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex.client;

import com.jagex.BZipDecompressor;
import com.jagex.Util;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.io.DataInputStream;
import java.io.IOException;

// Referenced classes of package com.jagex.client:
//			AppletFrame, Graphics2D

public class GameShell extends Applet implements Runnable {
  public void cj() {
  }

  public synchronized void hj() {
  }

  public void shutdown() {
  }

  public synchronized void nj() {
  }

  public void vj() {
  }

  public final void start(int arg0, int arg1, String arg2, boolean resizeable) {
    appletStarted = false;
    System.out.println("Started application");
    lp = arg0;
    mp = arg1;
    rp = new AppletFrame(this, arg0, arg1, arg2, resizeable, false);
    xp = 1;
    np = new Thread(this);
    np.start();
    np.setPriority(1);
  }

  public final boolean appletStarted() {
    return appletStarted;
  }

  public final Graphics getGraphics() {
    if (rp == null)
      return super.getGraphics();
    else
      return rp.getGraphics();
  }

  public final int jj() {
    return lp;
  }

  public final int qj() {
    return mp;
  }

  public final Image createImage(int arg0, int arg1) {
    if (rp == null)
      return super.createImage(arg0, arg1);
    else
      return rp.createImage(arg0, arg1);
  }

  public Frame mj() {
    return rp;
  }

  public final void pj(int arg0) {
    op = 1000 / arg0;
  }

  public final void rj(int arg0) {
    pp = arg0;
  }

  public final void tj() {
    for (int j = 0; j < 10; j++)
      qp[j] = 0L;

  }

  public synchronized boolean keyDown(Event arg0, int arg1) {
    uj(arg1);
    vq = arg1;
    wq = arg1;
    lastMouseAction = 0;
    if (arg1 == 1006)
      kq = true;
    if (arg1 == 1007)
      lq = true;
    if (arg1 == 1004)
      mq = true;
    if (arg1 == 1005)
      nq = true;
    if ((char) arg1 == ' ')
      oq = true;
    if ((char) arg1 == 'n' || (char) arg1 == 'm')
      pq = true;
    if ((char) arg1 == 'N' || (char) arg1 == 'M')
      pq = true;
    if ((char) arg1 == '{')
      iq = true;
    if ((char) arg1 == '}')
      jq = true;
    if ((char) arg1 == '\u03F0')
      xq = !xq;
    boolean flag = false;
    for (int j = 0; j < hq.length(); j++) {
      if (arg1 != hq.charAt(j))
        continue;
      flag = true;
      break;
    }

    if (flag && yq.length() < 20)
      yq += (char) arg1;
    if (flag && ar.length() < 80)
      ar += (char) arg1;
    if (arg1 == 8 && yq.length() > 0)
      yq = yq.substring(0, yq.length() - 1);
    if (arg1 == 8 && ar.length() > 0)
      ar = ar.substring(0, ar.length() - 1);
    if (arg1 == 10 || arg1 == 13) {
      zq = yq;
      br = ar;
    }
    return true;
  }

  public void uj(int j) {
  }

  public synchronized boolean keyUp(Event arg0, int arg1) {
    vq = 0;
    if (arg1 == 1006)
      kq = false;
    if (arg1 == 1007)
      lq = false;
    if (arg1 == 1004)
      mq = false;
    if (arg1 == 1005)
      nq = false;
    if ((char) arg1 == ' ')
      oq = false;
    if ((char) arg1 == 'n' || (char) arg1 == 'm')
      pq = false;
    if ((char) arg1 == 'N' || (char) arg1 == 'M')
      pq = false;
    if ((char) arg1 == '{')
      iq = false;
    if ((char) arg1 == '}')
      jq = false;
    return true;
  }

  public synchronized boolean mouseMove(Event arg0, int arg1, int arg2) {
    mouseX = arg1;
    mouseY = arg2 + yOffset;
    mouseButtonDown = 0;
    lastMouseAction = 0;
    return true;
  }

  public synchronized boolean mouseUp(Event arg0, int arg1, int arg2) {
    mouseX = arg1;
    mouseY = arg2 + yOffset;
    mouseButtonDown = 0;
    return true;
  }

  public synchronized boolean mouseDown(Event arg0, int arg1, int arg2) {
    mouseX = arg1;
    mouseY = arg2 + yOffset;
    if (arg0.metaDown())
      mouseButtonDown = 2;
    else
      mouseButtonDown = 1;
    uq = mouseButtonDown;
    lastMouseAction = 0;
    ij(mouseButtonDown, arg1, arg2);
    return true;
  }

  public void ij(int j, int l, int i1) {
  }

  public synchronized boolean mouseDrag(Event event, int xC, int yC) {
    mouseX = xC;
    mouseY = yC + yOffset;
    if (event.metaDown())
      mouseButtonDown = 2;
    else
      mouseButtonDown = 1;
    return true;
  }

  public final void init() {
    appletStarted = true;
    System.out.println("Started applet");
    lp = getSize().width;
    mp = getSize().height;
    xp = 1;
    Util.ohb = getCodeBase();
    np = new Thread(this);
    np.start();
  }

  public final void start() {
    if (tp >= 0)
      tp = 0;
  }

  public final void stop() {
    if (tp >= 0)
      tp = 4000 / op;
  }

  @SuppressWarnings("deprecation")
  public final void destroy() {
    tp = -1;
    try {
      Thread.sleep(5000L);
    } catch (Exception _ex) {
    }
    if (tp == -1) {
      System.out.println("5 seconds expired, forcing kill");
      ej();
      if (np != null) {
        np.stop();
        np = null;
      }
    }
  }

  public final void ej() {
    tp = -2;
    System.out.println("Closing program");
    shutdown();
    try {
      Thread.sleep(1000L);
    } catch (Exception _ex) {
    }
    if (rp != null)
      rp.dispose();
    if (!appletStarted)
      System.exit(0);
  }

  public final void run() {
    if (xp == 1) {
      xp = 2;
      gq = getGraphics();
      gj();
      lj(0, "Loading...");
      cj();
      xp = 0;
    }
    int j = 0;
    int i1 = 256;
    int j1 = 1;
    int k1 = 0;
    for (int i2 = 0; i2 < 10; i2++)
      qp[i2] = System.currentTimeMillis();

    while (tp >= 0) {
      if (tp > 0) {
        tp--;
        if (tp == 0) {
          ej();
          np = null;
          return;
        }
      }
      int j2 = i1;
      int k2 = j1;
      i1 = 300;
      j1 = 1;
      long l1 = System.currentTimeMillis();
      if (qp[j] == 0L) {
        i1 = j2;
        j1 = k2;
      } else if (l1 > qp[j])
        i1 = (int) ((long) (2560 * op) / (l1 - qp[j]));
      if (i1 < 25)
        i1 = 25;
      if (i1 > 256) {
        i1 = 256;
        j1 = (int) ((long) op - (l1 - qp[j]) / 10L);
        if (j1 < qq)
          j1 = qq;
      }
      try {
        Thread.sleep(j1);
      } catch (InterruptedException _ex) {
      }
      qp[j] = l1;
      j = (j + 1) % 10;
      if (j1 > 1) {
        for (int l2 = 0; l2 < 10; l2++)
          if (qp[l2] != 0L)
            qp[l2] += j1;

      }
      int i3 = 0;
      while (k1 < 256) {
        hj();
        k1 += i1;
        if (++i3 > pp) {
          k1 = 0;
          up += 6;
          if (up > 25) {
            up = 0;
            xq = true;
          }
          break;
        }
      }
      up--;
      k1 &= 0xff;
      nj();
    }
    if (tp == -1)
      ej();
    np = null;
  }

  public final void update(Graphics arg0) {
    paint(arg0);
  }

  public final void paint(Graphics arg0) {
    if (xp == 2 && fq != null) {
      lj(aq, bq);
      return;
    }
    if (xp == 0)
      vj();
  }

  public void gj() {
    try {
      byte abyte0[] = Util.mn("./cache/jagex.jag");
      byte abyte1[] = Util.in("logo.tga", 0, abyte0);
      fq = fj(abyte1);
      Surface.ef(Util.in("h11p.jf", 0, abyte0));
      Surface.ef(Util.in("h12b.jf", 0, abyte0));
      Surface.ef(Util.in("h12p.jf", 0, abyte0));
      Surface.ef(Util.in("h13b.jf", 0, abyte0));
      Surface.ef(Util.in("h14b.jf", 0, abyte0));
      Surface.ef(Util.in("h16b.jf", 0, abyte0));
      Surface.ef(Util.in("h20b.jf", 0, abyte0));
      Surface.ef(Util.in("h24b.jf", 0, abyte0));
      return;
    } catch (IOException _ex) {
      System.out.println("Error loading com.jagex.dat");
    }
  }

  public void lj(int arg0, String arg1) {
    int j = (lp - 281) / 2;
    int l = (mp - 148) / 2;
    gq.setColor(Color.black);
    gq.fillRect(0, 0, lp, mp);
    if (!zp)
      gq.drawImage(fq, j, l, this);
    j += 2;
    l += 90;
    aq = arg0;
    bq = arg1;
    gq.setColor(new Color(132, 132, 132));
    if (zp)
      gq.setColor(new Color(220, 0, 0));
    gq.drawRect(j - 2, l - 2, 280, 23);
    gq.fillRect(j, l, (277 * arg0) / 100, 20);
    gq.setColor(new Color(198, 198, 198));
    if (zp)
      gq.setColor(new Color(255, 255, 255));
    dj(gq, arg1, cq, j + 138, l + 10);
    if (!zp) {
      dj(gq, "Created by JAGeX - visit www.com.jagex.com", dq, j + 138, l + 30);
      dj(gq, "\2512001-2002 Andrew Gower and Jagex Ltd", dq, j + 138, l + 44);
    } else {
      gq.setColor(new Color(132, 132, 152));
      dj(gq, "\2512001-2002 Andrew Gower and Jagex Ltd", eq, j + 138, mp - 20);
    }
    if (yp != null) {
      gq.setColor(Color.white);
      dj(gq, yp, dq, j + 138, l - 120);
    }
  }

  public void kj(int arg0, String arg1) {
    int j = (lp - 281) / 2;
    int l = (mp - 148) / 2;
    j += 2;
    l += 90;
    aq = arg0;
    bq = arg1;
    int i1 = (277 * arg0) / 100;
    gq.setColor(new Color(132, 132, 132));
    if (zp)
      gq.setColor(new Color(220, 0, 0));
    gq.fillRect(j, l, i1, 20);
    gq.setColor(Color.black);
    gq.fillRect(j + i1, l, 277 - i1, 20);
    gq.setColor(new Color(198, 198, 198));
    if (zp)
      gq.setColor(new Color(255, 255, 255));
    dj(gq, arg1, cq, j + 138, l + 10);
  }

  public void dj(Graphics arg0, String arg1, Font arg2, int arg3, int arg4) {
    Object obj;
    if (rp == null)
      obj = this;
    else
      obj = rp;
    FontMetrics fontmetrics = ((Component) (obj)).getFontMetrics(arg2);
    fontmetrics.stringWidth(arg1);
    arg0.setFont(arg2);
    arg0.drawString(arg1, arg3 - fontmetrics.stringWidth(arg1) / 2, arg4 + fontmetrics.getHeight() / 4);
  }

  public Image fj(byte arg0[]) {
    int j = arg0[13] * 256 + arg0[12];
    int l = arg0[15] * 256 + arg0[14];
    byte abyte0[] = new byte[256];
    byte abyte1[] = new byte[256];
    byte abyte2[] = new byte[256];
    for (int i1 = 0; i1 < 256; i1++) {
      abyte0[i1] = arg0[20 + i1 * 3];
      abyte1[i1] = arg0[19 + i1 * 3];
      abyte2[i1] = arg0[18 + i1 * 3];
    }

    IndexColorModel indexcolormodel = new IndexColorModel(8, 256, abyte0, abyte1, abyte2);
    byte abyte3[] = new byte[j * l];
    int j1 = 0;
    for (int k1 = l - 1; k1 >= 0; k1--) {
      for (int l1 = 0; l1 < j; l1++)
        abyte3[j1++] = arg0[786 + l1 + k1 * j];

    }

    MemoryImageSource memoryimagesource = new MemoryImageSource(j, l, indexcolormodel, abyte3, 0, j);
    Image image = createImage(memoryimagesource);
    return image;
  }

  public byte[] unpackArchive(String arg0, String arg1, int arg2) throws IOException {
    int j = 0;
    int l = 0;
    int i1 = 0;
    byte abyte0[] = null;
    while (j < 2)
      try {
        kj(arg2, "Loading " + arg1 + " - 0%");
        if (j == 1)
          arg0 = arg0.toUpperCase();
        java.io.InputStream inputstream = Util.openStream(arg0);
        DataInputStream datainputstream = new DataInputStream(inputstream);
        byte abyte2[] = new byte[6];
        datainputstream.readFully(abyte2, 0, 6);
        l = ((abyte2[0] & 0xff) << 16) + ((abyte2[1] & 0xff) << 8) + (abyte2[2] & 0xff);
        i1 = ((abyte2[3] & 0xff) << 16) + ((abyte2[4] & 0xff) << 8) + (abyte2[5] & 0xff);
        kj(arg2, "Loading " + arg1 + " - 5%");
        int j1 = 0;
        abyte0 = new byte[i1];
        while (j1 < i1) {
          int k1 = i1 - j1;
          if (k1 > 1000)
            k1 = 1000;
          datainputstream.readFully(abyte0, j1, k1);
          j1 += k1;
          kj(arg2, "Loading " + arg1 + " - " + (5 + (j1 * 95) / i1) + "%");
        }
        j = 2;
        datainputstream.close();
      } catch (IOException _ex) {
        j++;
      }
    kj(arg2, "Unpacking " + arg1);
    if (i1 != l) {
      byte abyte1[] = new byte[l];
      BZipDecompressor.xj(abyte1, l, abyte0, i1, 0);
      return abyte1;
    } else {
      return abyte0;
    }
  }

  public GameShell() {
    lp = 512;
    mp = 384;
    op = 20;
    pp = 1000;
    qp = new long[10];
    xp = 1;
    zp = false;
    bq = "Loading";
    cq = new Font("TimesRoman", 0, 15);
    dq = new Font("Helvetica", 1, 13);
    eq = new Font("Helvetica", 0, 12);
    iq = false;
    jq = false;
    kq = false;
    lq = false;
    mq = false;
    nq = false;
    oq = false;
    pq = false;
    qq = 1;
    xq = false;
    yq = "";
    zq = "";
    ar = "";
    br = "";
  }

  private int lp;
  private int mp;
  private Thread np;
  private int op;
  private int pp;
  private long qp[];
  static AppletFrame rp = null;
  private boolean appletStarted;
  private int tp;
  private int up;
  public int yOffset;
  public int lastMouseAction;
  public int xp;
  public String yp;
  private boolean zp;
  private int aq;
  private String bq;
  private Font cq;
  private Font dq;
  private Font eq;
  private Image fq;
  private Graphics gq;
  private static String hq = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
  public boolean iq;
  public boolean jq;
  public boolean kq;
  public boolean lq;
  public boolean mq;
  public boolean nq;
  public boolean oq;
  public boolean pq;
  public int qq;
  public int mouseX;
  public int mouseY;
  public int mouseButtonDown;
  public int uq;
  public int vq;
  public int wq;
  public boolean xq;
  public String yq;
  public String zq;
  public String ar;
  public String br;
}