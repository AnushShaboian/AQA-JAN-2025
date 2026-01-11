#TODO: Homework * : Create table Phones : PhoneId, PhoneName, PhonePrice
#TODO:- using selenium go to allo.ua, search for a phone
#TODO:- get phone name
#TODO:- get phone price
#TODO:- store phone name and phone price to DB
#TODO:- print that phone to console

  Feature: Test interaction between Web and SQL
    @allo
    Scenario: Get phones info from allo.ua and put it in DB
      Given I opened the search results for the query “телефон” on the Allo.ua website
      When I store the main characteristics of the first phone in DB
      Then I have new record in DB
