// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex.client;

import java.io.InputStream;

import sun.audio.AudioPlayer;

public class AudioInputStream extends InputStream {
  public AudioInputStream() {
    AudioPlayer.player.start(this);
  }

  public void stop() {
    AudioPlayer.player.stop(this);
  }

  public void play(byte buf[], int pos, int arg2) {
    buffer = buf;
    position = pos;
    length = pos + arg2;
  }

  public int read(byte dest[], int offset, int length) {
    for (int i = 0; i < length; i++)
      if (position < length)
        dest[offset + i] = buffer[position++];
      else
        dest[offset + i] = -1;

    return length;
  }

  public int read() {
    byte buffer[] = new byte[1];
    read(buffer, 0, 1);
    return buffer[0];
  }

  byte buffer[];
  int position;
  int length;
}