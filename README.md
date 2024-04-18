# Conference Planner API

## Introduction

The Conference Planner API enables users to dynamically generate schedules for a variety of talks and events, streamlining the organization and management of conferences. This tool is designed to handle multiple simultaneous sessions, and integrates functionalities for both morning and afternoon sessions without overlap, ensuring a smooth conference flow.

## Getting Started

This section provides a basic guide on how to get a local copy of the Conference Planner API up and running on your machine.

### Prerequisites

Before installing the project, ensure you have the following software installed on your computer:

- Java 21 or higher
- Spring Boot 3 or higher
- Maven 3.6 or higher

### Installation Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/conference-planner-api.git

2. **Navigate to the project directory::**

   ```bash
   cd conference-planner-api

3. **Build the project using Maven:**

   ```bash
   mvn clean install

4. **Run the application:**

   ```bash
   mvn spring-boot:run

## Usage

To use the Conference Planner API, make sure the application is up and running as detailed in the [Getting Started](#getting-started) section. For interacting with the API and to see all available endpoints with their expected parameters and responses, use the Swagger UI.

### Accessing Swagger UI

You can access the Swagger UI by navigating to the following URL after starting the application:

[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

This web interface provides a comprehensive and interactive documentation of all API endpoints. It allows you to execute API calls directly from your browser, making it an invaluable tool for understanding and testing the API functionalities.

### Example Request

To schedule a series of talks for a conference, you can use the following `curl` command. Replace the placeholder URL with your API endpoint:

- **Method:** POST
- **URL:** `http://localhost:8080/api/conference/schedule`
- **Headers:**
  - Accept: `*/*`
  - Content-Type: `application/json`
- **Body:**
  ```json
  {
    "talks": [
      {"title": "Architecting Your Codebase", "duration": "60min"},
      {"title": "Overdoing it in Python", "duration": "45min"},
      {"title": "Flavors of Concurrency in Java", "duration": "lightning"}
    ]
  }


This `curl` command sends a POST request to the server to schedule conference talks based on the data provided in the JSON payload.

### Success and Error Responses

The API utilizes a standardized response format for both successful operations and errors, handled by the `GenericResponseHandler`. Here’s how responses are structured:

- **Success Response:**
  
  When a request is successfully processed, the API returns a JSON object that includes a `success` flag set to true, a `data` object containing the response data, and an `infoMessage` providing a confirmation message.

  ```json
  {
    "success": true,
    "data": {
      "tracks": [
        {
          "track_1": [
            {
              "time": "9:00 AM",
              "title": "Architecting Your Codebase 60min"
            },
            {
              "time": "10:00 AM",
              "title": "Overdoing it in Python 45min"
            },
            {
              "time": "10:45 AM",
              "title": "Flavors of Concurrency in Java lightning"
            },
            {
              "time": "10:50 AM",
              "title": "Lunch"
            },
            {
              "time": "4:00 PM",
              "title": "Networking Event"
            }
          ]
        }
      ]
    },
    "infoMessage": "Conference scheduled successfully"
  }

- **Error Response:**

  If there are issues processing a request (e.g., validation errors, server errors), the API returns a success flag set to false and an errorMessage detailing what went wrong.

  ```json
  {
    "success": false,
    "errorMessage": "An error occurred: The request data is invalid."
  }

## Tests

Tests in our project are used to verify that the application functions correctly. Our basic test coverage is structured as follows:

### Edge Case Tests

Edge case tests ensure that the application behaves correctly at its boundaries. Specifically, we test the following scenarios for `ConferenceScheduleServiceUtil` and `ConferenceScheduleService`:

1. **Null Input Test**: Verifying that empty values are handled correctly without causing errors.
2. **Boundary Values Test**: Testing that boundary values (e.g., minimum and maximum values) are handled correctly.


