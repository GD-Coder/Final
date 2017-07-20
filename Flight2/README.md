# Flight Reservation Application
You are tasked with implementing a fight booking and monitoring application from scratch. You will use all tools at your disposal to create a Single Page Application front end that will be supported by a Spring based application hosting REST endpoints. You will be required to handle user profiles, track live updating flight data, provide the capacity to book flights, verify correct information, provide the user with informative messages in the case of an error, visualize the flight paths of various flights, create a pathing algorithm to allow users to book collections of viable flights with viable layovers in order to reach their destination in the shortest time possible, and allow a user to review their flight history.

## Instructions

### GENERAL DESCRIPTION

The intent of this project is to create an interface for users to book flights.

The idea of a flight is greatly simplified from reality.

In this world, the flights will only go to four cities: Orlando, Miami, Tallahassee, and Jacksonville

A new set of five flights is generated every "day"

A day is defined in the FlightService

A day lasts as long as the @Scheduled annotation indicates, feel free to change this value to assist in your testing

A flight has a origin, destination, flight time, and offset

The origin is the city the flight departs from

The destination is the city the flight lands in

The flight time is the number of hours the flight spends in the air

The offset is the number of hours the flight waits from the beginning of the day until the flight departs


### SET UP

Create a database named "ftd_flight"

Run the project to generate tables

Data should be populated into the "location" table based on the entries in the "data.sql" file

### PROJECT REQUIREMENTS

Create a website where any visitor can see the up to date list of available flights

The list of available flights must updated in real time

Allow a user to create a profile and log in to that profile

If a user logs in, allow them to search for flights between an origin and destination city

The results of this search must be updated in real time

The app must find a flight or series of flights (in the case of layovers) that will get the user from the origin to the destination the fastest

A layover of zero is not valid

If there is no possible route, the user must be made aware that they cannot travel to that destination from their origin at this time

Once an itinerary is presented to the user, the user must be given the option to book the itinerary

The user must have a page that displays all of their previously booked itineraries, the total flight time, and the total layover time

The user must be given the option to view their booked itinerary on a map as provided by the MapController

In the scenario of a multi-flight itinerary, lines must be displayed with a different color for each branch of the journey

Information must be provided on the map template page that shows the user the order the line segments occur in, the flight time for that segment, and the layover time in each particular city

### Starting the Project
In order to start the project, you must
1. In Eclipse, run `com.cooksys.FlightApplication.java` as a Spring Boot Project
2. In the command prompt, navigate to the `ui` directory inside the project
3. Run `npm install --save`
4. Run `npm build`
5. Run `npm start`
6. In the browser, navigate to `localhost:8080`

If the project has started successfully, you should see a page with a map on it. This map should have four cities in Tennessee connected by lines of different colors.