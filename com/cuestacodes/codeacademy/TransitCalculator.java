package com.cuestacodes.codeacademy;

class TransitCalculator {
  double[] fareOptions = {2.75, 33.00, 127.00};

  String[] fareTypes = {"Pay-per-ride", "7-Day Unlimited", "30-Day Unlimited"};

  int numberOfDays;

  int numberOfRides;

  double[] ridePrices = new double [3];

  public TransitCalculator(int numOfDays, int numOfRides){
    numberOfDays = numOfDays;
    numberOfRides = numOfRides;
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
        bestFare = ridePrices[i];

        bestFareType = fareTypes[i];
      }
    }

    String bestFareFind =  "You should get the " + bestFareType + " at $" + bestFare + " per ride";

    return bestFareFind;
  }

  public static void main(String[] args) {
    int daysTravelling;
    int individualRides;

    // testing commuters
    TransitCalculator person1 = new TransitCalculator(22, 50);
    System.out.println(person1.getBestFare());

    TransitCalculator person2 = new TransitCalculator(26, 54);
    System.out.println(person2.getBestFare());

    TransitCalculator person3 = new TransitCalculator(5, 12);
    System.out.println(person3.getBestFare());

    TransitCalculator person4 = new TransitCalculator(14, 28);
    System.out.println(person4.getBestFare());
  }
}
