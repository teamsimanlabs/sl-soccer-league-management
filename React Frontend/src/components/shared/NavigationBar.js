import React from 'react';
import { Link, useNavigate } from 'react-router-dom'; // Import useNavigate instead of useHistory

const NavigationBar = ({ isAuthenticated }) => {
  const navigate = useNavigate(); // Use useNavigate hook

  const handleLogout = () => {
    // Perform logout operations like clearing the auth token
    localStorage.removeItem('authToken');
    // Redirect to login page or home page after logout using navigate
    navigate('/login');
    // Here, add any other logic needed for logging out, such as clearing global state
  };

  return (
    <nav>
      <ul style={{ listStyleType: 'none', padding: 0 }}>
        <li style={{ display: 'inline', marginRight: '10px' }}>
          <Link to="/">Home</Link>
        </li>
        {isAuthenticated ? (
          <>
            <li style={{ display: 'inline', marginRight: '10px' }}>
              <Link to="/dashboard">Dashboard</Link>
            </li>
            <li style={{ display: 'inline', marginRight: '10px' }}>
              <button onClick={handleLogout}>Logout</button>
            </li>
          </>
        ) : (
          <li style={{ display: 'inline', marginRight: '10px' }}>
            <Link to="/login">Login</Link>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default NavigationBar;
