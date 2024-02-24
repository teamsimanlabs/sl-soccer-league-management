// src/services/leagueService.js
const fetchLeagueInfo = async () => {
    // This is a mock function. Replace it with actual API call.
    return Promise.resolve({
      name: "Simal Labs Soccer League",
      season: "2023/2024",
      description: "Welcome to the premier soccer league for emerging talents."
    });
  };
  
  export { fetchLeagueInfo };
  