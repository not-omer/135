// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) nonlb 

package com.jagex.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package com.jagex.client:
//			GameShell, a

public class NetworkedGame extends GameShell {
  public void setKey(BigInteger exponent, BigInteger modulus) {
    exp = exponent;
    mod = modulus;
  }

  public int getRanseed() {
    try {
      String s1 = getParameter("ranseed");
      String s2 = s1.substring(0, 10);
      int clientSesssionID = Integer.parseInt(s2);
      if (clientSesssionID == 0x3ade68b1) {
        byte keys[] = new byte[4];
        com.jagex.Util.readFully("uid.dat", keys, 4);
        clientSesssionID = com.jagex.Util.readInt32(keys, 0);
      }
      return clientSesssionID;
    } catch (Exception _ex) {
      return 0;
    }
  }

  public void login(String user, String pass, boolean reconnecting) {
    if (worldFullTimeout > 0) {
      drawLoginStatus(messageTable[6], messageTable[7]);
      try {
        Thread.sleep(2000L);
      } catch (Exception _ex) {
      }
      drawLoginStatus(messageTable[8], messageTable[9]);
      return;
    }
    try {
      username = user;
      user = com.jagex.Util.formatString(user, 20);
      password = pass;
      pass = com.jagex.Util.formatString(pass, 20);
      if (user.trim().length() == 0) {
        drawLoginStatus(messageTable[0], messageTable[1]);
        return;
      }
      if (reconnecting)
        d(messageTable[2], messageTable[3]);
      else
        drawLoginStatus(messageTable[6], messageTable[7]);
      if (appletStarted())
        stream = new Connection(host, this, port);
      else
        stream = new Connection(host, null, port);
      stream.timeout = timeout;
      int ssi = stream.readInt32();
      serverSessionID = ssi;
      System.out.println("Session id: " + ssi);
      if (reconnecting)
        stream.beginFrame(19);
      else
        stream.beginFrame(0);
      stream.putInt16(revision);
      stream.putInt64(com.jagex.Util.encode37(user));
      stream.putLineRSA(pass, ssi, exp, mod);
      stream.putInt32(getRanseed());
      stream.flush();
      stream.read();
      int response = stream.read();
      System.out.println("Login response: " + response);
      if (response == 0) {
        ed = 0;
        resetGame();
        return;
      }
      if (response == 1) {
        ed = 0;
        a();
        return;
      }
      if (reconnecting) {
        user = "";
        pass = "";
        resetCredentials();
        return;
      }
      if (response == 3) {
        drawLoginStatus(messageTable[10], messageTable[11]);
        return;
      }
      if (response == 4) {
        drawLoginStatus(messageTable[4], messageTable[5]);
        return;
      }
      if (response == 5) {
        drawLoginStatus(messageTable[16], messageTable[17]);
        return;
      }
      if (response == 6) {
        drawLoginStatus(messageTable[18], messageTable[19]);
        return;
      }
      if (response == 7) {
        drawLoginStatus(messageTable[20], messageTable[21]);
        return;
      }
      if (response == 11) {
        drawLoginStatus(messageTable[22], messageTable[23]);
        return;
      }
      if (response == 12) {
        drawLoginStatus(messageTable[24], messageTable[25]);
        return;
      }
      if (response == 13) {
        drawLoginStatus(messageTable[14], messageTable[15]);
        return;
      }
      if (response == 14) {
        drawLoginStatus(messageTable[8], messageTable[9]);
        worldFullTimeout = 1500;
        return;
      }
      if (response == 15) {
        drawLoginStatus(messageTable[26], messageTable[27]);
        return;
      }
      if (response == 16) {
        drawLoginStatus(messageTable[28], messageTable[29]);
        return;
      } else {
        drawLoginStatus(messageTable[12], messageTable[13]);
        return;
      }
    } catch (Exception exception) {
      System.out.println(String.valueOf(exception));
    }
    if (ed > 0) {
      try {
        Thread.sleep(5000L);
      } catch (Exception _ex) {
      }
      ed--;
      login(username, password, reconnecting);
    }
    if (reconnecting) {
      username = "";
      password = "";
      resetCredentials();
    } else {
      drawLoginStatus(messageTable[12], messageTable[13]);
    }
  }

  public void disconnect() {
    if (stream != null)
      try {
        stream.beginFrame(1);
        stream.flush();
      } catch (IOException _ex) {
      }
    username = "";
    password = "";
    resetCredentials();
  }

  public void reconnect() {
    System.out.println("Lost connection");
    ed = 10;
    login(username, password, true);
  }

  public void d(String arg0, String arg1) {
    Graphics g1 = getGraphics();
    Font font = new Font("Helvetica", 1, 15);
    int i1 = jj();
    int j1 = qj();
    g1.setColor(Color.black);
    g1.fillRect(i1 / 2 - 140, j1 / 2 - 25, 280, 50);
    g1.setColor(Color.white);
    g1.drawRect(i1 / 2 - 140, j1 / 2 - 25, 280, 50);
    dj(g1, arg0, font, i1 / 2, j1 / 2 - 10);
    dj(g1, arg1, font, i1 / 2, j1 / 2 + 10);
  }

  public void register(String username, String password) {
    if (worldFullTimeout > 0) {
      drawLoginStatus(messageTable[6], messageTable[7]);
      try {
        Thread.sleep(2000L);
      } catch (Exception _ex) {
      }
      drawLoginStatus(messageTable[8], messageTable[9]);
      return;
    }
    try {
      username = com.jagex.Util.formatString(username, 20);
      password = com.jagex.Util.formatString(password, 20);
      drawLoginStatus(messageTable[6], messageTable[7]);
      if (appletStarted())
        stream = new Connection(host, this, port);
      else
        stream = new Connection(host, null, port);
      int ssi = stream.readInt32();
      serverSessionID = ssi;
      System.out.println("Session id: " + ssi);
      int referrerID = 0;
      try {
        if (appletStarted())
          referrerID = Integer.parseInt(getParameter("referid"));
      } catch (Exception _ex) {
      }
      stream.beginFrame(2);
      stream.putInt16(revision);
      stream.putInt64(com.jagex.Util.encode37(username));
      stream.putInt16(referrerID);
      stream.putLineRSA(password, ssi, exp, mod);
      stream.putInt32(getRanseed());
      stream.flush();
      stream.read();
      int response = stream.read();
      stream.close();
      System.out.println("Newplayer response: " + response);
      if (response == 2) {
        u();
        return;
      }
      if (response == 3) {
        drawLoginStatus(messageTable[14], messageTable[15]);
        return;
      }
      if (response == 4) {
        drawLoginStatus(messageTable[4], messageTable[5]);
        return;
      }
      if (response == 5) {
        drawLoginStatus(messageTable[16], messageTable[17]);
        return;
      }
      if (response == 6) {
        drawLoginStatus(messageTable[18], messageTable[19]);
        return;
      }
      if (response == 7) {
        drawLoginStatus(messageTable[20], messageTable[21]);
        return;
      }
      if (response == 11) {
        drawLoginStatus(messageTable[22], messageTable[23]);
        return;
      }
      if (response == 12) {
        drawLoginStatus(messageTable[24], messageTable[25]);
        return;
      }
      if (response == 13) {
        drawLoginStatus(messageTable[14], messageTable[15]);
        return;
      }
      if (response == 14) {
        drawLoginStatus(messageTable[8], messageTable[9]);
        worldFullTimeout = 1500;
        return;
      }
      if (response == 15) {
        drawLoginStatus(messageTable[26], messageTable[27]);
        return;
      }
      if (response == 16) {
        drawLoginStatus(messageTable[28], messageTable[29]);
        return;
      } else {
        drawLoginStatus(messageTable[12], messageTable[13]);
        return;
      }
    } catch (Exception exception) {
      System.out.println(String.valueOf(exception));
    }
    drawLoginStatus(messageTable[12], messageTable[13]);
  }

  public void j() {
    long l1 = System.currentTimeMillis();
    if (stream.ob())
      fd = l1;
    if (l1 - fd > 5000L) {
      fd = l1;
      stream.beginFrame(5);
      stream.encodeFrame();
    }
    try {
      stream.writeFrame(20);
    } catch (IOException _ex) {
      reconnect();
      return;
    }
    if (!w())
      return;
    int i1 = stream.decodeFrame(payload);
    if (i1 > 0)
      handleIncomingFrame(payload[0] & 0xff, i1);
  }

  public void handleIncomingFrame(int frameID, int frameLength) {
    if (frameID == 8) {
      String message = new String(payload, 1, frameLength - 1);
      sendServerMessage(message);
    }
    if (frameID == 9)
      disconnect();
    if (frameID == 10) {
      forbidLogout();
      return;
    }
    if (frameID == 23) {
      friendsCount = com.jagex.Util.unsign(payload[1]);
      for (int i = 0; i < friendsCount; i++) {
        friends[i] = com.jagex.Util.readInt64(payload, 2 + i * 9);
        world[i] = com.jagex.Util.unsign(payload[10 + i * 9]);
      }

      sortFriends();
      return;
    }
    if (frameID == 24) {
      long usernameEncoded = com.jagex.Util.readInt64(payload, 1);
      int loggedIn = payload[9] & 0xff;
      for (int i = 0; i < friendsCount; i++)
        if (friends[i] == usernameEncoded) {
          if (world[i] == 0 && loggedIn != 0)
            sendServerMessage("@pri@" + com.jagex.Util.decode37(usernameEncoded) + " has logged in");
          if (world[i] != 0 && loggedIn == 0)
            sendServerMessage("@pri@" + com.jagex.Util.decode37(usernameEncoded) + " has logged out");
          world[i] = loggedIn;
          frameLength = 0;
          sortFriends();
          return;
        }

      friends[friendsCount] = usernameEncoded;
      world[friendsCount] = loggedIn;
      friendsCount++;
      sendServerMessage("@pri@" + com.jagex.Util.decode37(usernameEncoded) + " has been added to your friends list");
      sortFriends();
      return;
    }
    if (frameID == 26) {
      ignoreCount = com.jagex.Util.unsign(payload[1]);
      for (int i = 0; i < ignoreCount; i++)
        ignoreList[i] = com.jagex.Util.readInt64(payload, 2 + i * 8);
      return;
    }
    if (frameID == 27) {
      blockPublicChat = payload[1];
      blockPrivateChat = payload[2];
      blockTradeRequests = payload[3];
      blockDuelRequests = payload[4];
      return;
    }
    if (frameID == 28) {
      long usernameEncoded = com.jagex.Util.readInt64(payload, 1);
      String message = com.jagex.Util.encodeCensor(payload, 9, frameLength - 9, true);
      sendServerMessage("@pri@" + com.jagex.Util.decode37(usernameEncoded) + ": tells you " + message);
      return;
    } else {
      processIncomingFrame(frameID, frameLength, payload);
      return;
    }
  }

  public void sortFriends() {
    boolean flag = true;
    while (flag) {
      flag = false;
      for (int i1 = 0; i1 < friendsCount - 1; i1++)
        if (world[i1] < world[i1 + 1]) {
          int j1 = world[i1];
          world[i1] = world[i1 + 1];
          world[i1 + 1] = j1;
          long l1 = friends[i1];
          friends[i1] = friends[i1 + 1];
          friends[i1 + 1] = l1;
          flag = true;
        }
    }
  }

  public void x(String user, String pass) {
    user = com.jagex.Util.formatString(user, 20);
    pass = com.jagex.Util.formatString(pass, 20);
    stream.beginFrame(25);
    stream.putLineRSA(user + pass, serverSessionID, exp, mod);
    stream.encodeFrame();
  }

  public void updatePrivacy(int publicChat, int privateChat, int trade, int duel) {
    stream.beginFrame(31);
    stream.putInt8(publicChat);
    stream.putInt8(privateChat);
    stream.putInt8(trade);
    stream.putInt8(duel);
    stream.encodeFrame();
  }

  public void ignore(String username) {
    long encoded = com.jagex.Util.encode37(username);
    stream.beginFrame(29);
    stream.putInt64(encoded);
    stream.encodeFrame();
    for (int i = 0; i < ignoreCount; i++)
      if (ignoreList[i] == encoded)
        return;

    if (ignoreCount >= 50) {
      return;
    } else {
      ignoreList[ignoreCount++] = encoded;
      return;
    }
  }

  public void unignore(long encoded) {
    stream.beginFrame(30);
    stream.putInt64(encoded);
    stream.encodeFrame();
    for (int i = 0; i < ignoreCount; i++)
      if (ignoreList[i] == encoded) {
        ignoreCount--;
        for (int j = i; j < ignoreCount; j++)
          ignoreList[j] = ignoreList[j + 1];

        return;
      }
  }

  public void addFriend(String username) {
    stream.beginFrame(26);
    stream.putInt64(com.jagex.Util.encode37(username));
    stream.encodeFrame();
  }

  public void removeFriend(long user) {
    stream.beginFrame(27);
    stream.putInt64(user);
    stream.encodeFrame();
    for (int i = 0; i < friendsCount; i++) {
      if (friends[i] != user)
        continue;
      friendsCount--;
      for (int j = i; j < friendsCount; j++) {
        friends[j] = friends[j + 1];
        world[j] = world[j + 1];
      }

      break;
    }

    sendServerMessage("@pri@" + com.jagex.Util.decode37(user) + " has been removed from your friends list");
  }

  public void sendPrivateMessage(long username, byte scrambledMessage[], int messageLength) {
    stream.beginFrame(28);
    stream.putInt64(username);
    stream.readBytes(scrambledMessage, 0, messageLength);
    stream.encodeFrame();
  }

  public void sendPublicMessage(byte encodedMessage[], int messageLength) {
    stream.beginFrame(3);
    stream.readBytes(encodedMessage, 0, messageLength);
    stream.encodeFrame();
  }

  public void sendCommand(String command) {
    stream.beginFrame(7);
    stream.putLine(command);
    stream.encodeFrame();
  }

  public void drawLoginStatus(String s1, String s2) {
  }

  public void a() {
  }

  public void resetGame() {
  }

  public void resetCredentials() {
  }

  public void forbidLogout() {
  }

  public void u() {
  }

  public void processIncomingFrame(int frameID, int frameLength, byte payload[]) {
  }

  public void sendServerMessage(String s1) {
  }

  public boolean w() {
    return true;
  }

  public NetworkedGame() {
    host = "127.0.0.1";
    port = 43594;
    username = "";
    password = "";
    payload = new byte[5000];
    friends = new long[100];
    world = new int[100];
    ignoreList = new long[50];
  }

  public static String messageTable[];
  public static int revision = 1;
  public static int timeout;
  public String host;
  public int port;
  String username;
  String password;
  public Connection stream;
  byte payload[];
  int ed;
  long fd;
  public int friendsCount;
  public long friends[];
  public int world[];
  public int ignoreCount;
  public long ignoreList[];
  public int blockPublicChat;
  public int blockPrivateChat;
  public int blockTradeRequests;
  public int blockDuelRequests;
  public BigInteger exp;
  public BigInteger mod;
  public int serverSessionID;
  public int worldFullTimeout;

  static {
    messageTable = new String[50];
    messageTable[0] = "You must enter both a username";
    messageTable[1] = "and a password - Please try again";
    messageTable[2] = "Connection lost! Please wait...";
    messageTable[3] = "Attempting to re-establish";
    messageTable[4] = "That username is already in use.";
    messageTable[5] = "Wait 60 seconds then retry";
    messageTable[6] = "Please wait...";
    messageTable[7] = "Connecting to server";
    messageTable[8] = "Sorry! The server is currently full.";
    messageTable[9] = "Please try again later";
    messageTable[10] = "Invalid username or password.";
    messageTable[11] = "Try again, or create a new account";
    messageTable[12] = "Sorry! Unable to connect to server.";
    messageTable[13] = "Check your internet settings";
    messageTable[14] = "Username already taken.";
    messageTable[15] = "Please choose another username";
    messageTable[16] = "The client has been updated.";
    messageTable[17] = "Please reload this page";
    messageTable[18] = "You may only use 1 character at once.";
    messageTable[19] = "Your ip-address is already in use";
    messageTable[20] = "Login attempts exceeded!";
    messageTable[21] = "Please try again in 5 minutes";
    messageTable[22] = "Account has been temporarily disabled";
    messageTable[23] = "for cheating or abuse";
    messageTable[24] = "Account has been permanently disabled";
    messageTable[25] = "for cheating or abuse";
    messageTable[26] = "You need a members account";
    messageTable[27] = "to login to this server";
    messageTable[28] = "Please login to a members server";
    messageTable[29] = "to access member-only features";
  }
}