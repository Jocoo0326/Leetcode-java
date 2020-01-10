class IntegerToRoman {
  public static void main(String[] args) {
    Assert.assertEquals(intToRoman(1994), "MCMXCIV");
    Assert.assertEquals(intToRoman(3), "III");
    Assert.assertEquals(intToRoman(4), "IV");
    Assert.assertEquals(intToRoman(9), "IX");
    Assert.assertEquals(intToRoman(58), "LVIII");
  }

  public static String intToRoman(int num) {
    final String[] m = {"", "M", "MM", "MMM"};
    final String[] c = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    final String[] x = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    final String[] i = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    return m[num / 1000] + c[(num % 1000) / 100] + x[(num % 100) / 10] + i[num % 10];
  }

  public static String intToRomanOrg(int num) {
    final int org = num;
    final int[] values = {1000, 500, 100, 50, 10, 5, 1};
    final char[] symbols = "MDCLXVI".toCharArray();
    int[] val = new int[values.length];
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < values.length;) {
      int d = num / values[i];
      if (i == 1) {
        if (num / 100 == 4) {
          sb.append("CD");
          num = num % 100;
          i += 2;
          continue;
        }
        if (num / 100 == 9) {
          sb.append("CM");
          num = num % 100;
          i += 2;
          continue;
        }
      } else if (i == 3) {
        if (num / 10 == 4) {
          sb.append("XL");
          num = num % 10;
          i += 2;
          continue;
        }
        if (num / 10 == 9) {
          sb.append("XC");
          num = num % 10;
          i += 2;
          continue;
        }
      } else if (i == 5) {
        if (num == 4) {
          sb.append("IV");
          break;
        }
        if (num == 9) {
          sb.append("IX");
          break;
        }
      }
      for (int j = 0; j < d; j++) {
        sb.append(symbols[i]);
      }
      num = num % values[i++];
    }

    return sb.toString();
  }
}
