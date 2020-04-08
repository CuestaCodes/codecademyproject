package com.cuestacodes.codeacademy;

class TransitCalculator {
  static double singleRide = 2.75;

  static double sevenDayUnlimited = 33.00;

  static double thirtyDayUnlimited = 127.00;

  int numberOfDays;

  int numberOfRides;

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

    double priceTotal = numOfSevenPasses * sevenDayUnlimited;

    return priceTotal / numberOfRides;
  }

  public double[] getRidePrices(){
    double[] ridePrices = new double [3];

    ridePrices[0] = numberOfRides * singleRide;
    ridePrices[1] = unlimted7Price();
    ridePrices[2] = thirtyDayUnlimited;
  }

  public String getBestFare(){
    double bestFare = thirtyDayUnlimited;

    double pricePerRide;

    for (double ridePrice : ridePrices ){
      pricePerRide = ridePrice / numberOfRides;

      if (pricePerRide < bestFare){
        bestFare = pricePerRide;
      }
    }

    System.out.println("You should get the ");
  }

  public static void main(String[] args) {
    int daysTravelling;
    int individualRides;

    // test commuters
    TransitCalculator person1 = new TransitCalculator(22, 50);

    System.out.println(person1.unlimited7Price());
  }
}
