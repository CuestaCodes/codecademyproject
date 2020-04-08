package com.cuestacodes.codeacademy;

import java.util.ArrayList;

// Java program that calulates the best fare option for a commuter based on days travelling,
// number of rides expected to take during that time, age and if the commuter has a disability
class TransitCalculator {
  double[] fareOptions = {2.75, 33.00, 127.00};

  double[] concFareOptions = {1.35, 16.50, 63.50};

  String[] fareTypes = {"Pay-per-ride", "7-Day Unlimited", "30-Day Unlimited"};

  double[] ridePrices = new double [3];

  int numberOfDays;

  int numberOfRides;

  boolean concession = false;

  public TransitCalculator(int numOfDays, int numOfRides, boolean hasDisability, int age){
    if (numOfDays < 0 || numOfDays > 30 || numOfRides < 0 || age < 0) {
      throw new IllegalArgumentException();
    } else {
      numberOfDays = numOfDays;
      numberOfRides = numOfRides;

      if (hasDisability || age > 65){
        concession = true;
      }
    }
  }

  // calculates overall price per ride using 7-Day Unlimited option
  public double unlimited7Price(){
    int numOfSevenPasses = numberOfDays / 7;

    double priceTotal;

    if (numberOfDays % 7 != 0){
      numOfSevenPasses++;
    }

    if (concession) {
      priceTotal = numOfSevenPasses * concFareOptions[1];
    } else {
      priceTotal = numOfSevenPasses * fareOptions[1];
    }

    double sevenPrice = priceTotal / numberOfRides;

    return sevenPrice;
  }

  // calculates the price per ride for each fare option
  public double[] getRidePrices(){
    if (concession) {
      ridePrices[0] = concFareOptions[0];
      ridePrices[2] = concFareOptions[2] / numberOfRides;
    } else {
      ridePrices[0] = fareOptions[0];
      ridePrices[2] = fareOptions[2] / numberOfRides;
    }

    ridePrices[1] = unlimited7Price();

    return ridePrices;
  }

  // determines and recommends the lowest price at the best fare method
  public String getBestFare(){
    double bestFare = fareOptions[2];

    String bestFareType = fareTypes[2];

    double pricePerRide;

    String concessionAvail = "";

    getRidePrices();

    for (int i = 0 ; i < ridePrices.length ; i++){
      if (ridePrices[i] < bestFare){
        bestFare = Math.round(ridePrices[i] * 100.0) / 100.0;

        bestFareType = fareTypes[i];
      }
    }

    if (concession) {
      concessionAvail = "You qualify for a concession fare. ";
    }

    String bestFareFind = concessionAvail + "You should get the " + bestFareType + " at $" + bestFare + " per ride";

    return bestFareFind;
  }

  public static void main(String[] args) {
    int daysTravelling;
    int individualRides;

    ArrayList<TransitCalculator> commuters = new ArrayList<TransitCalculator>();

    commuters.add(new TransitCalculator(22, 50, false, 67));
    commuters.add(new TransitCalculator(26, 54, false, 25));
    commuters.add(new TransitCalculator(5, 12, true, 35));
    commuters.add(new TransitCalculator(14, 28, false, 29));
    commuters.add(new TransitCalculator(7, 2, false, 65));

    for (int i = 0 ; i < commuters.size() ; i++){
      System.out.println(commuters.get(i).getBestFare());
    }
  }
}
