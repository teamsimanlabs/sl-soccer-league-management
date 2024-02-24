# Simal Labs Soccer League App (React Frontend)
This project serves as the React-based frontend for the final capstone project at Simal Labs, focusing on managing soccer leagues. It includes features such as React Router for navigation, Context API for state management, and Axios for API interactions, alongside a comprehensive authentication system.

# Project Setup
First, ensure you have Node.js and npm installed. Then, follow these steps to set up the project:

Clone the repository to your local machine.
Navigate to the project directory and install dependencies:
Copy code
npm install
Review the .env.local file at the root of the project to configure environment variables:
arduino
Copy code
REACT_APP_API_URL=http://localhost:9000
Note: The backend API is expected to run on port 9000.

Start the development server:
sql
Copy code
npm start
Your application will be available at http://localhost:3000.

# Authentication
Upon launching the app, you will be redirected to a login page if you are not already authenticated. This project uses React Router for navigation and includes a simple authentication flow:

Routes are configured with public and private access.
Private routes require authentication.
Authentication state is managed globally using Context API.
Here's an example of a route configuration in src/AppRouter.js:

jsx
Copy code
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PrivateRoute from './components/PrivateRoute';
import HomePage from './components/pages/HomePage';
import LoginPage from './components/pages/LoginPage';

const AppRouter = () => (
  <Router>
    <Switch>
      <PrivateRoute path="/" exact component={HomePage} />
      <Route path="/login" component={LoginPage} />
    </Switch>
  </Router>
);

# State Management
This application uses the Context API for state management, focusing on user authentication status and data. We store the authentication token and user information globally, making it accessible throughout the application.

# Login Process
To log in, navigate to the /login page and enter your credentials. The application uses Axios to communicate with the backend API. Upon successful authentication, the application stores the token in local storage and updates the global state, redirecting you to the homepage.

Example login function in src/context/AuthContext.js:

jsx
Copy code
const loginUser = async (credentials) => {
  const response = await axios.post(`${process.env.REACT_APP_API_URL}/login`, credentials);
  if (response.status === 200) {
    localStorage.setItem('authToken', response.data.token);
    setAuthToken(response.data.token);
    // Additional steps to set user context and redirect to home
  }
};
# Logout Process
To log out, a function in the AuthContext removes the user's token from local storage and clears the global authentication state, redirecting the user to the login page.

# Registration
New users can register through the /register page. The registration process is similar to login, requiring a username, password, and additional user details.

# Conclusion
This README outlines the basic setup and functionality of the Simal Labs Soccer League App's React frontend. It includes instructions for setting up the project, managing state, and handling authentication. Adjust and expand upon this template as your project grows and evolves.