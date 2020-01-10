
import java.util.HashMap;
import java.util.Map;

class RomanToInteger {
  public static void main(String[] args) {
    Assert.assertEquals(romanToInt("MCMXCIV"), 1994);
    Assert.assertEquals(romanToInt("III"), 3);
    Assert.assertEquals(romanToInt("IV"), 4);
    Assert.assertEquals(romanToInt("IX"), 9);
    Assert.assertEquals(romanToInt("LVIII"), 58);
  }

  public static int romanToInt(String s) {
    final Map<String, Integer> map = new HashMap<>();
    map.put("I", 1);
    map.put("IV", 4);
    map.put("V", 5);
    map.put("IX", 9);
    map.put("X", 10);
    map.put("XL", 40);
    map.put("L", 50);
    map.put("XC", 90);
    map.put("C", 100);
    map.put("CD", 400);
    map.put("D", 500);
    map.put("CM", 900);
    map.put("M", 1000);
    int val = 0;
    int i = 0;
    while (i < s.length()) {
      if (i < s.length() - 1
          && map.get(s.substring(i, i + 1)) < map.get(s.substring(i + 1, i + 2))) {
        val += map.get(s.substring(i, i + 2));
        i += 2;
      } else {
        val += map.get(s.substring(i, i + 1));
        i++;
      }
    }
    return val;
  }
}
