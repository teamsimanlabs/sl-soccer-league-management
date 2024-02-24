import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // Updated import to use useNavigate
import { login } from '../../services/authService';

const LoginPage = () => {
  // State for credentials and error message
  const [credentials, setCredentials] = useState({ username: '', password: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate(); // Updated to use navigate

  // Update state with form input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setCredentials(prevCredentials => ({
      ...prevCredentials,
      [name]: value,
    }));
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent default form submission behavior
    try {
      const response = await login(credentials); // Attempt to login with credentials
      localStorage.setItem('authToken', response.token); // Save the token on successful login
      navigate('/'); // Updated to use navigate for redirection
    } catch (err) {
      // Update error state to display error message
      setError('Failed to login. Please check your credentials.');
    }
  };

  return (
    <div>
      <h1>Login</h1>
      {error && <p className="error">{error}</p>} {/* Display error message if any */}
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            name="username"
            value={credentials.username}
            onChange={handleChange} // Update state on input change
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={credentials.password}
            onChange={handleChange} // Update state on input change
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;
