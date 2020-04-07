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

  public double unlimited7Price(){
    int numOfSevenPasses = numberOfDays / 7;

    if (numberOfDays % 7 != 0){
      numOfSevenPasses++;
    }

    return numOfSevenPasses * sevenDayUnlimited;
  }

  public static void main(String[] args) {
    int daysTravelling;
    int individualRides;
  }
}
