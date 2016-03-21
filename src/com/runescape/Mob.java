package com.runescape;// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

public class Mob {
  public Mob() {
    waypointsX = new int[10];
    waypointsY = new int[10];
    equipment = new int[12];
    combatLevel = -1;
    is = false;
    js = -1;
  }

  public long usernameEncoded;
  public String username;
  public int serverIndex;
  public int status;
  public int gr;
  public int hr;
  public int ir;
  public int jr;
  public int kr;
  public int nextDirection;
  public int mr;
  public int currentWaypoint;
  public int waypointsX[];
  public int waypointsY[];
  public int equipment[];
  public String latestChat;
  public int sr;
  public int tr;
  public int ur;
  public int damageReceived;
  public int currentHP;
  public int baseHP;
  public int yr;
  public int combatLevel;
  public int hairColor;
  public int torsoColor;
  public int legColor;
  public int skinColor;
  public int projectileID;
  public int fs;
  public int projectileServerIndex;
  public int hs;
  public boolean is;
  public int js;
  public int skulled;
}