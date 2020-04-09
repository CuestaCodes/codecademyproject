package com.cuestacodes.codecademy;

/**
 * Java program that calulates the best fare option for a commuter based on days travelling,
 * number of rides expected to take during that time, age and if the commuter has a disability.
 */

class TransitCalculator {
  static double[] nonConcfareOptions = {2.75, 33.00, 127.00};

  static double[] concFareOptions = {1.35, 16.50, 63.50};

  static String[] fareTypes = {"Pay-per-ride", "7-Day Unlimited", "30-Day Unlimited"};

  double[] fareOptions = nonConcfareOptions;

  double[] ridePrices = new double[3];

  int numOfDays;

  int numOfRides;

  boolean concession = false;

  public TransitCalculator(int numOfDays, int numOfRides, boolean hasDisability, int age){
    if (numOfDays < 0 || numOfDays > 30 || numOfRides < 0 || age < 0) {
      throw new IllegalArgumentException();
    } else {
      this.numOfDays = numOfDays;
      this.numOfRides = numOfRides;

      if (hasDisability || age > 65){
        concession = true;
        this.fareOptions = concFareOptions;
      }
    }
  }

  // returns the price per ride for each fare option
  public double[] getRidePrices(){
    ridePrices[0] = fareOptions[0];
    ridePrices[1] = unlimited7Price();
    ridePrices[2] = fareOptions[2] / numOfRides;

    return ridePrices;
  }

  // calculates overall price per ride using 7-Day Unlimited option
  public double unlimited7Price(){
    int numOfSevenPasses = numOfDays / 7;

    if (numOfDays % 7 != 0){
      numOfSevenPasses++;
    }

    double sevenPrice = numOfSevenPasses * fareOptions[1] / numOfRides;

    return sevenPrice;
  }

  // determines and recommends the lowest price at the best fare method
  public String getBestFare(){
    double bestFare = fareOptions[2];

    double pricePerRide;

    String bestFareType = fareTypes[2];

    String concessionAvail = "You do not qualify for a concession fair. ";

    getRidePrices();

    for (int i = 0 ; i < ridePrices.length ; i++){
      if (ridePrices[i] < bestFare){
        bestFare = Math.round(ridePrices[i] * 100.0) / 100.0;

        bestFareType = fareTypes[i];
      }
    }

    if (concession) {
      concessionAvail = "You do qualify for a concession fair. ";
    }

    String bestFareFind = concessionAvail + "You should get the " + bestFareType + " at $" + bestFare + " per ride.";

    return bestFareFind;
  }

  public static void main(String[] args) {
    int daysTravelling = 5;
    int individualRides = 12;
    boolean hasDisability = false;
    int age = 67;

    TransitCalculator commuter = new TransitCalculator(daysTravelling, individualRides, hasDisability, age);

    // System output: You do qualify for a concession fair. You should get the Pay-per-ride at $1.35 per ride.
    System.out.println(commuter.getBestFare());
  }
}
