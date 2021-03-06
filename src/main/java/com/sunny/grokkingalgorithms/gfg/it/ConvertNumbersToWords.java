package com.sunny.grokkingalgorithms.gfg.it;

import java.util.Stack;

/**
 * Created by sundas on 2/21/2018.
 */
public class ConvertNumbersToWords {

  /*
  GIven a number convert it into a word form like
  123 = One hundred twenty three
  or 44500 = Forty four thousand five hundred
   */


  /**
   *
   *
   * @param number
   * @return
   */
  public static String convertNumberToWordsRecursive(int number){
    /*
    A better solution using recursion
     */
    String[] base = new String[]{"one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve",
        "thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    String[] decades = new String[]{"ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
    if(number < 0){
      return "minus" + convertNumberToWordsRecursive(Math.abs(number));
    }
    if(number < 20){
      return base[number - 1];
    }
    // Need to worry if remainder is zero
    else if(number < 100){
      return decades[number/10 - 1] + ((number%10 == 0)?"":base[number%10 - 1]);
    }
    else if(number < 1000){
      return base[number/100 - 1] + "hundred" + ((number%100 == 0)?"":convertNumberToWordsRecursive(number%100));
    }
    else if(number < 100000){
      return convertNumberToWordsRecursive(number/1000) + "thousand" + ((number%1000 == 0)?"":convertNumberToWordsRecursive(number%1000));
    }
    else if(number < 10000000){
      return convertNumberToWordsRecursive(number/100000) + "lakhs" + ((number%100000 == 0)?"":convertNumberToWordsRecursive(number%100000));
    }
    return convertNumberToWordsRecursive(number/1000000) + "crores" + ((number%1000000 == 0)?"":convertNumberToWordsRecursive(number%1000000));
  }

  /**
   *
   * @param number
   * @return
   */
  public static String convertNumberToStringsAlternate(int number){
    StringBuilder converted = new StringBuilder();
    String strRepOfNumber = String.valueOf(number);
    String[] base = new String[]{"one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve",
        "thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    String[] decades = new String[]{"ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
    if(number < 20){
      converted.append(base[number - 1]);
    }
    else {
      int index = 0;
      int powerCount = strRepOfNumber.length();
      while(index < strRepOfNumber.length()){
        char curNumber = strRepOfNumber.charAt(index);
        int numValue = Character.getNumericValue(curNumber);
        if(numValue > 0) {
          if (powerCount >= 4) {
            if(powerCount == 5){
              converted.append(decades[numValue - 1]);
            }
            else if(powerCount == 6){
              converted.append(base[numValue - 1] + "hundred");
            }
            else{
              converted.append(base[numValue - 1]);
            }
            if(powerCount == 4){
              converted.append("thousand");
            }
          } else if (powerCount == 3) {
            converted.append(base[numValue - 1] + "hundred");
          } else if (powerCount == 2) {
            converted.append(decades[numValue - 1]);
          } else if (powerCount == 1) {
            converted.append(base[numValue - 1]);
          }
        }
        if(powerCount == 4 && numValue == 0){
          converted.append("thousand");
        }
        powerCount--;
        index++;
      }
    }
    return converted.toString();
  }

  /**
   * A hack way to convert integer to words within a range
   *
   * @param number
   * @return
   */
  public static String convertNumberToWords(int number){
    /*
    Base dictionary to for conversion
     */
    String[] base = new String[]{"one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve",
        "thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    //Need these since we need their string values
    String[] decades = new String[]{"ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
    StringBuilder converted = new StringBuilder();
    //Range check
    if(number > 99000 || number <= 0){
      System.out.println("Conversion not supported for number = " + number);
      return null;
    }
    //This information can be fetched from the dictionary directly
    if(number < 20){
      converted.append(base[number - 1]);
    }
    else{
      // Stack since with this method the string is produced in reverse
      Stack<String> conversionTracker = new Stack<>();
      int powerCount = 0;
      int dividend = number;
      int divisor = 10;
      boolean thousandFlag = false;
      /*
      What is the idea here
      with every division we are adding 10.pow(powercount)
      which will initially be 1 , then 10 , 100 etc
      for example:
      123/10 -> remainder 3 quotient 12
      12/10 -> remainder 2 quotient 1
      1/10 -> 1
       I need to find a better way to do this and minimise the if else loops
       */
      while(dividend >= divisor){
        int quotient = dividend/divisor;
        int remainder = dividend%divisor;
        if(powerCount == 0){
          if(remainder > 0){
            conversionTracker.add(base[remainder - 1]);
          }
        }
        else if(powerCount == 1){
          if(remainder > 0){
            conversionTracker.add(decades[remainder - 1]);
          }
        }
        else if(powerCount == 2){
          if(remainder > 0){
            conversionTracker.add(base[remainder - 1] + "hundred");
          }
        }
        else if(powerCount == 3){
          if(remainder > 0){
            conversionTracker.add(base[remainder - 1] + "thousand");
            thousandFlag = true;
          }
        }
        else if(powerCount == 4){
          if(remainder > 0){
            String value = decades[remainder - 1];
            if(!thousandFlag){
              value += "thousand";
              thousandFlag = true;
            }
            conversionTracker.add(value);
          }
        }
        dividend = quotient;
        powerCount++;
      }
      // To handle cases where dividend is less than divisor (i.e. 10)
      if(dividend > 0){
        if(powerCount == 1){
          conversionTracker.add(decades[dividend - 1]);
        }
        else if(powerCount == 2){
          conversionTracker.add(base[dividend - 1] + "hundred");
        }
        else if(powerCount == 3){
          conversionTracker.add(base[dividend - 1] + "thousand");
        }
        else if(powerCount == 4){
          String value = decades[dividend - 1];
          if(!thousandFlag){
            value += "thousand";
            thousandFlag = true;
          }
          conversionTracker.add(value);
        }
        else if(powerCount > 4){

        }
      }
      while(!conversionTracker.isEmpty()){
        converted.append(conversionTracker.pop());
      }

  }
    return converted.toString();
  }


  public static void main(String[] args) throws InterruptedException {
    System.out.println(convertNumberToWords(123));
    System.out.println(convertNumberToWords(44500));
    System.out.println(convertNumberToWords(1234));
    System.out.println(convertNumberToWords(1));
    System.out.println(convertNumberToWords(50));
    System.out.println(convertNumberToWords(11));
    System.out.println(convertNumberToWords(20));
    System.out.println(convertNumberToWords(21));
    System.out.println(convertNumberToWords(31));
    System.out.println(convertNumberToWords(91));
    System.out.println(convertNumberToWords(90000));
    System.out.println(convertNumberToWords(91000));
    System.out.println(convertNumberToWords(100));
    System.out.println(convertNumberToWords(1000));
    System.out.println(convertNumberToWords(999));
    System.out.println(convertNumberToWords(10000));
    System.out.println(convertNumberToWords(9000));
    System.out.println(convertNumberToWords(100000));
    System.out.println(convertNumberToWords(11000));
    System.out.println(convertNumberToWords(1100));
    System.out.println(convertNumberToWords(110));
    System.out.println(convertNumberToWords(21000));

    System.out.println("########################################### new method ########");
    System.out.println(convertNumberToStringsAlternate(999));
    System.out.println(convertNumberToStringsAlternate(123));
    System.out.println(convertNumberToStringsAlternate(10));
    System.out.println(convertNumberToStringsAlternate(100));
    System.out.println(convertNumberToStringsAlternate(999));
    System.out.println(convertNumberToStringsAlternate(21));
    System.out.println(convertNumberToStringsAlternate(30));
    System.out.println(convertNumberToStringsAlternate(31));
    System.out.println(convertNumberToStringsAlternate(91));
    System.out.println(convertNumberToStringsAlternate(1100));
    System.out.println(convertNumberToStringsAlternate(12000));
    System.out.println(convertNumberToStringsAlternate(44500));
    System.out.println(convertNumberToStringsAlternate(100000));
    System.out.println(convertNumberToStringsAlternate(10000));
    System.out.println(convertNumberToStringsAlternate(1000));
    System.out.println(convertNumberToStringsAlternate(40000));
    System.out.println("############################################### recursive solution #####");
    System.out.println(convertNumberToWordsRecursive(42));
    System.out.println(convertNumberToWordsRecursive(99));
    System.out.println(convertNumberToWordsRecursive(10));
    System.out.println(convertNumberToWordsRecursive(21));
    System.out.println(convertNumberToWordsRecursive(30));
    System.out.println(convertNumberToWordsRecursive(21));
    System.out.println(convertNumberToWordsRecursive(100));
    System.out.println(convertNumberToWordsRecursive(101));
    System.out.println(convertNumberToWordsRecursive(999));
    System.out.println(convertNumberToWordsRecursive(556));
    System.out.println(convertNumberToWordsRecursive(44500));
    System.out.println(convertNumberToWordsRecursive(1000));
    System.out.println(convertNumberToWordsRecursive(99000));
    System.out.println(convertNumberToWordsRecursive(75461));
    System.out.println(convertNumberToWordsRecursive(10000));
    System.out.println(convertNumberToWordsRecursive(11000));
    System.out.println(convertNumberToWordsRecursive(21011));
    System.out.println(convertNumberToWordsRecursive(100000));
    System.out.println(convertNumberToWordsRecursive(110000));
    System.out.println(convertNumberToWordsRecursive(990000));
    System.out.println(convertNumberToWordsRecursive(999999));
    System.out.println(convertNumberToWordsRecursive(112001));
    System.out.println(convertNumberToWordsRecursive(10000000));
    System.out.println(convertNumberToWordsRecursive(-44500));
    /*System.out.println(convertNumberToStringsAlternate(123));
    System.out.println(convertNumberToStringsAlternate(10));
    System.out.println(convertNumberToStringsAlternate(100));
    System.out.println(convertNumberToStringsAlternate(999));
    System.out.println(convertNumberToStringsAlternate(21));
    System.out.println(convertNumberToStringsAlternate(30));
    System.out.println(convertNumberToStringsAlternate(31));
    System.out.println(convertNumberToStringsAlternate(91));*/
    // general JMX entries
    /*Map<String, String> memoryMap = new HashMap<>();
    OperatingSystemMXBean osMxBean = ManagementFactory.getOperatingSystemMXBean();

    int count = 0;
    while(count < 2){
      double cpu = osMxBean.getSystemLoadAverage();
      System.out.println(cpu);
      memoryMap.put("cpuLoad", ""+cpu);
      count++;
      Thread.sleep(1000);
    }


    System.out.println( ManagementFactoryHelper.getOperatingSystemMXBean().getSystemLoadAverage());


    ThreadMXBean threadmxBean = ManagementFactory.getThreadMXBean();
    int threadCount = threadmxBean.getThreadCount();
    memoryMap.put("cpuRunningThreads", String.valueOf(threadCount));

    MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
    MemoryUsage memHeapUsage = memBean.getHeapMemoryUsage();
    MemoryUsage nonHeapUsage = memBean.getNonHeapMemoryUsage();
    memoryMap.put("heapInit", String.valueOf(memHeapUsage.getInit()));
    memoryMap.put("heapMax", String.valueOf(memHeapUsage.getMax()));
    memoryMap.put("heapCommit", String.valueOf(memHeapUsage.getCommitted()));
    memoryMap.put("heapUsed", String.valueOf(memHeapUsage.getUsed()));
    memoryMap.put("nonHeapInit", String.valueOf(nonHeapUsage.getInit()));
    memoryMap.put("nonHeapMax", String.valueOf(nonHeapUsage.getMax()));
    memoryMap.put("nonHeapCommit", String.valueOf(nonHeapUsage.getCommitted()));
    memoryMap.put("nonHeapUsed", String.valueOf(nonHeapUsage.getUsed()));
    System.out.println(memoryMap);*/
  }

}
