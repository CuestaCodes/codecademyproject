package com.cuestacodes.codeacademy;

import java.util.ArrayList;

class TransitCalculator {
  double[] fareOptions = {2.75, 33.00, 127.00};

  String[] fareTypes = {"Pay-per-ride", "7-Day Unlimited", "30-Day Unlimited"};

  int numberOfDays;

  int numberOfRides;

  double[] ridePrices = new double [3];

  public TransitCalculator(int numOfDays, int numOfRides){
    if (numOfDays < 0 || numOfDays > 30 || numOfRides < 0) {
      throw new IllegalArgumentException();
   } else {
     numberOfDays = numOfDays;
     numberOfRides = numOfRides;
   }
  }

  // calculates overall price per ride using 7-Day Unlimited option
  public double unlimited7Price(){
    int numOfSevenPasses = numberOfDays / 7;

    if (numberOfDays % 7 != 0){
      numOfSevenPasses++;
    }

    double priceTotal = numOfSevenPasses * fareOptions[1];

    double sevenPrice = priceTotal / numberOfRides;

    return sevenPrice;
  }

  // calculates the price per ride for each fare option
  public double[] getRidePrices(){
    ridePrices[0] = fareOptions[0];
    ridePrices[1] = unlimited7Price();
    ridePrices[2] = fareOptions[2] / numberOfRides;

    return ridePrices;
  }

  // determines and recommends the lowest price at the best fare method
  public String getBestFare(){
    double bestFare = fareOptions[2];

    String bestFareType = fareTypes[2];

    double pricePerRide;

    getRidePrices();

    for (int i = 0 ; i < ridePrices.length ; i++){
      if (ridePrices[i] < bestFare){
        bestFare = Math.round(ridePrices[i] * 100.0) / 100.0;

        bestFareType = fareTypes[i];
      }
    }

    String bestFareFind =  "You should get the " + bestFareType + " at $" + bestFare + " per ride";

    return bestFareFind;
  }

  public static void main(String[] args) {
    int daysTravelling;
    int individualRides;

    // testing commuters with varying days travelled and number of rides during that time
    ArrayList<TransitCalculator> commuters = new ArrayList<TransitCalculator>();

    commuters.add(new TransitCalculator(22,50));
    commuters.add(new TransitCalculator(26,54));
    commuters.add(new TransitCalculator(5,12));
    commuters.add(new TransitCalculator(14,28));
    commuters.add(new TransitCalculator(7,2));

    for (int i = 0 ; i < commuters.size() ; i++){
      System.out.println(commuters.get(i).getBestFare());
    }
  }
}
