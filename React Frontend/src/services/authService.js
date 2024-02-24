// src/services/authService.js
import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:9000'; // Update this URL to your API's base URL

const login = async (credentials) => {
  try {
    const response = await axios.post(`${API_URL}/login`, credentials);
    if (response.data && response.data.token) {
      // Assuming the backend response includes a token and user information
      // Store the token in local storage or in-memory storage as needed
      localStorage.setItem('authToken', response.data.token);
      // You can also store user information if needed
      return response.data; // return the entire response data including token and user
    } else {
      throw new Error('No token received');
    }
  } catch (error) {
    if (error.response && error.response.status === 401) {
      // Handle 401 Unauthorized response specifically if needed
      throw new Error('Invalid credentials');
    } else {
      // Handle other errors
      throw new Error('Login failed');
    }
  }
};

export { login };
