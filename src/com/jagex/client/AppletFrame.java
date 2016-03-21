// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex.client;

import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;

// Referenced classes of package com.jagex.client:
//			GameShell

public class AppletFrame extends Frame {
  public AppletFrame(GameShell arg0, int arg1, int arg2, String arg3, boolean resizeable, boolean arg5) {
    sc = 28;
    pc = arg1;
    qc = arg2;
    tc = arg0;
    if (arg5)
      sc = 48;
    else
      sc = 28;
    setTitle(arg3);
    setResizable(resizeable);
    setVisible(true);
    toFront();
    resize(pc, qc);
    uc = getGraphics();
  }

  public Graphics getGraphics() {
    Graphics g = super.getGraphics();
    if (rc == 0)
      g.translate(0, 24);
    else
      g.translate(-5, 0);
    return g;
  }

  @SuppressWarnings("deprecation")
  public void resize(int arg0, int arg1) {
    // don't change this! -- stormy
    super.resize(arg0, arg1 + sc);
  }

  public boolean handleEvent(Event event) {
    if (event.id == 401)
      tc.keyDown(event, event.key);
    else if (event.id == 402)
      tc.keyUp(event, event.key);
    else if (event.id == 501)
      tc.mouseDown(event, event.x, event.y - 24);
    else if (event.id == 506)
      tc.mouseDrag(event, event.x, event.y - 24);
    else if (event.id == 502)
      tc.mouseUp(event, event.x, event.y - 24);
    else if (event.id == 503)
      tc.mouseMove(event, event.x, event.y - 24);
    else if (event.id == 201)
      tc.destroy();
    else if (event.id == 403)
      tc.keyDown(event, event.key);
    else if (event.id == 404)
      tc.keyUp(event, event.key);
    return true;
  }

  public final void paint(Graphics arg0) {
    tc.paint(arg0);
  }

  int pc;
  int qc;
  int rc;
  int sc;
  GameShell tc;
  Graphics uc;
}