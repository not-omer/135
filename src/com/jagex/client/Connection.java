// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex.client;

import com.jagex.Buffer;

import java.applet.Applet;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connection extends Buffer implements Runnable {
  public Connection(String host, Applet applet, int port) throws IOException {
    closed = false;
    threadClosed = true;
    if (applet != null)
      socket = new Socket(InetAddress.getByName(applet.getCodeBase().getHost()), port);
    else
      socket = new Socket(InetAddress.getByName(host), port);
    socket.setSoTimeout(30000);
    socket.setTcpNoDelay(true);
    in = socket.getInputStream();
    out = socket.getOutputStream();
  }

  public void close() {
    super.close();
    closed = true;
    try {
      if (in != null)
        in.close();
      if (out != null)
        out.close();
      if (socket != null)
        socket.close();
    } catch (IOException _ex) {
      System.out.println("Error closing stream");
    }
    if (twriter != null) {
      threadClosed = true;
      synchronized (this) {
        notify();
      }
      twriter = null;
    }
    buffer = null;
  }

  public int read() throws IOException {
    if (closed)
      return 0;
    else
      return in.read();
  }

  public int available() throws IOException {
    if (closed)
      return 0;
    else
      return in.available();
  }

  public void writeTo(int len, int offset, byte dest[]) throws IOException {
    if (closed)
      return;
    int j = 0;
    int k;
    for (; j < len; j += k)
      if ((k = in.read(dest, j + offset, len - j)) <= 0)
        throw new IOException("EOF");
  }

  public void readFrom(byte src[], int offset, int len) throws IOException {
    if (closed)
      return;
    if (buffer == null)
      buffer = new byte[5000];
    synchronized (this) {
      for (int j = 0; j < len; j++) {
        buffer[g] = src[j + offset];
        g = (g + 1) % 5000;
        if (g == (f + 4900) % 5000)
          throw new IOException("buffer overflow");
      }

      if (twriter == null) {
        threadClosed = false;
        twriter = new Thread(this);
        twriter.setDaemon(true);
        twriter.setPriority(4);
        twriter.start();
      }
      notify();
    }
  }

  public void run() {
    while (twriter != null && !threadClosed) {
      int j;
      int k;
      synchronized (this) {
        if (g == f)
          try {
            wait();
          } catch (InterruptedException _ex) {
          }
        if (twriter == null || threadClosed)
          return;
        k = f;
        if (g >= f)
          j = g - f;
        else
          j = 5000 - f;
      }
      if (j > 0) {
        try {
          out.write(buffer, k, j);
        } catch (IOException ioexception) {
          super.wd = true;
          super.xd = "Twriter:" + ioexception;
        }
        f = (f + j) % 5000;
        try {
          if (g == f)
            out.flush();
        } catch (IOException exception) {
          super.wd = true;
          super.xd = "Twriter:" + exception;
        }
      }
    }
  }

  private InputStream in;
  private OutputStream out;
  private Socket socket;
  private boolean closed;
  private byte buffer[];
  private int f;
  private int g;
  private Thread twriter;
  private boolean threadClosed;
}