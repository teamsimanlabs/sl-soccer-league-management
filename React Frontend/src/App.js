import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import logo from './logo.svg';
import './App.css';
import NavigationBar from './components/shared/NavigationBar';
import HomePage from './components/pages/HomePage';
import LoginPage from './components/pages/LoginPage';

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    // Placeholder for authentication check
    const authToken = localStorage.getItem('authToken');
    setIsAuthenticated(!!authToken);
  }, []);

  return (
    <Router>
      <div className="App">
        <header className="App-header">
          <NavigationBar isAuthenticated={isAuthenticated} />
          <img src={logo} className="App-logo" alt="logo" />
          <Routes> {/* Updated from Switch to Routes */}
            <Route path="/" element={<HomePage />} /> {/* Updated Route syntax */}
            <Route path="/login" element={<LoginPage />} />
            {/* Add more routes as needed */}
          </Routes>
        </header>
      </div>
    </Router>
  );
}

export default App;
