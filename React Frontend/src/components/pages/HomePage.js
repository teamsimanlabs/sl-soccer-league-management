// src/components/pages/HomePage.js
import React, { useState, useEffect } from 'react';
import { fetchLeagueInfo } from '../../services/leagueService';

const HomePage = () => {
  const [leagueInfo, setLeagueInfo] = useState({
    name: '',
    season: '',
    description: ''
  });
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const loadLeagueInfo = async () => {
      try {
        const info = await fetchLeagueInfo();
        setLeagueInfo(info);
      } catch (error) {
        console.error('Failed to fetch league info:', error);
      } finally {
        setIsLoading(false);
      }
    };

    loadLeagueInfo();
  }, []);

  if (isLoading) {
    return <div>Loading league information...</div>;
  }

  return (
    <div>
      <h1>{leagueInfo.name}</h1>
      <h2>Season: {leagueInfo.season}</h2>
      <p>{leagueInfo.description}</p>
    </div>
  );
};

export default HomePage;
