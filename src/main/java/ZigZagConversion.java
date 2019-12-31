class ZigZagConversion {
  public static void main(String[] args) {
    Assert.assertEquals(convert("PAYPALISHIRING", 3), "PAHNAPLSIIGYIR");
    Assert.assertEquals(convert("PAYPALISHIRING", 4), "PINALSIGYAHRPI");
  }

  public static String convert(String s, int numRows) {
    if (s == null || s.length() <= 1 || numRows == 1) {
      return s;
    }
    int v = (numRows - 1) * 2;
    int nv = (s.length() - 1) % v == 0 ? (s.length() - 1) / v : (s.length() - 1) / v + 1;
    int col = 1 + nv * (numRows - 1);
    int r = 0, i = 1, ic = 0;
    StringBuilder sb = new StringBuilder();
    sb.append(s.charAt(0));
    while (i < s.length()) {
      int p1 = -1, p2 = -1;
      if (r == 0) {
        p1 = v - 1;
      } else if (r == numRows - 1) {
        p1 = numRows - 2;
      } else {
        p1 = r - 1;
        p2 = 2 * (numRows - 2) - p1;
      }
      int idx = 1 + ic * v + p1;
      if (idx < s.length()) {
        sb.append(s.charAt(idx));
        i++;
      }
      if (p2 != -1) {
        idx = 1 + ic * v + p2;
        if (idx < s.length()) {
          sb.append(s.charAt(idx));
          i++;
        }
      }
      if (++ic == nv) {
        r++;
        ic = 0;
      }
    }
    return sb.toString();
  }

  public static String aconvert(String s, int numRows) {
    // V = (numRows-1)<<1
    // (N / V)*(numRows-1)
    if (s == null || s.length() <= 1 || numRows == 1) {
      return s;
    }
    int v = (numRows - 1) * 2;
    int nv = (s.length() - 1) % v == 0 ? (s.length() - 1) / v : (s.length() - 1) / v + 1;
    int col = 1 + nv * (numRows - 1);
    char[][] d = new char[numRows][col];
    for (int i = 0; i < s.length(); i++) {
      if (i == 0) {
        d[0][0] = s.charAt(0);
        continue;
      }
      int n = (i - 1) / v * (numRows - 1);
      int t = (i - 1) % v;
      if (t < numRows - 1) {
        d[t + 1][n] = s.charAt(i);
      } else {
        int p = (t - (numRows - 2));
        d[numRows - 1 - p][n + p] = s.charAt(i);
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < col; j++) {
        if (d[i][j] != 0) {
          sb.append(d[i][j]);
        }
      }
    }
    return sb.toString();
  }
}
