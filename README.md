Real-Time Data Processing System for Weather Monitoring

Overview
The Real-Time Data Processing System for Weather Monitoring is a web application that fetches, processes, and displays current weather data and alerts for various cities in India. It utilizes the OpenWeatherMap API to provide real-time weather information, including temperature, conditions, and weather alerts. This system is designed with a user-friendly interface built with React for the frontend and Spring Boot with Jakarta for the backend.

Features
- Fetches current weather data for multiple cities in India, including Delhi, Mumbai, Chennai, Bangalore, Kolkata, and Hyderabad.
- Displays daily weather summaries with average, maximum, and minimum temperatures.
- Provides real-time weather alerts to keep users informed of severe weather conditions.
- Responsive design that works well on various devices.
- Utilizes a clean and modern user interface.

 Tech Stack
- Frontend: React
- Backend: Spring Boot(version:-3.3.4)
- Database: In-memory database (Spring Data JPA)
- API: OpenWeatherMap API for fetching weather data

## Installation

Prerequisites
- Java Development Kit (JDK) 11 or higher
- Node.js (version 14 or higher)
- npm (Node Package Manager)
- Access to OpenWeatherMap API (API key)

Clone the repository
git clone https://github.com/1kranthi/WeatherApp.git
cd weatherapp

Backend Setup
    1. Navigate to the backend directory:
       cd weatherapp
    2. Install dependencies:
       ./mvnw install
    3. Configure your application.properties file with the OpenWeatherMap API key:
       openweathermap.api.key=YOUR_API_KEY
    4. Run the backend application:
       ./mvnw spring-boot:run
Frontend Setup
    1. Navigate to the frontend directory:
       cd frontend
    2. Install dependencies:
       npm install
    3. Start the React application:
       npm start
Usage
    1. After starting both the backend and frontend, open your web browser and navigate to http://localhost:3000.
    2. You will see the homepage displaying current weather data and alerts.
    3. The application automatically fetches and displays weather summaries and alerts for the specified cities.
API Endpoints
Backend API Endpoints
    • Get Current Weather:
        ◦ GET /api/weather/current
        ◦ Description: Fetches the current weather data for the predefined cities.
    • Get Weather Summaries:
        ◦ GET /api/weather/summaries
        ◦ Description: Retrieves daily weather summaries for the specified cities.
    • Get Weather Alerts:
        ◦ GET /api/weather/alerts
        ◦ Description: Fetches weather alerts based on current conditions.
        
Frontend Integration
The frontend communicates with the backend through Axios for making API calls. Ensure that the backend server is running while accessing the frontend.
Acknowledgments
    • OpenWeatherMap for providing the weather data API.
    • React and Spring Boot for being the backbone of this application.
    • Inspiration from various online resources and documentation.

Key Sections Explained:
- Overview: A brief description of the project and its purpose.
- Features: A list of the application's capabilities.
- Tech Stack: The technologies used in the project.
- Installation: Step-by-step instructions on how to set up the project locally.
- Usage: Instructions on how to run and use the application.
- API Endpoints: Detailed information about the backend API endpoints.
- Contribution: Guidelines for contributing to the project.
- License: Information regarding the project's licensing.
- Acknowledgments: Recognition of libraries, services, or inspirations used in the project.
