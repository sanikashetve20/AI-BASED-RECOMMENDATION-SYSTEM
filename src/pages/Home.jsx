import React, { useState } from "react";
import RecommendationList from "../components/RecommendationList";
import { Container, TextField, Button } from "@mui/material";

const Home = () => {
  const [userId, setUserId] = useState("");
  const [showRecommendations, setShowRecommendations] = useState(false);

  const handleSubmit = () => {
    setShowRecommendations(true);
  };

  return (
    <Container>
      <h2>AI-Based Recommendation System</h2>
      <TextField
        label="Enter User ID"
        variant="outlined"
        value={userId}
        onChange={(e) => setUserId(e.target.value)}
        fullWidth
        margin="normal"
      />
      <Button variant="contained" color="primary" onClick={handleSubmit}>
        Get Recommendations
      </Button>

      {showRecommendations && <RecommendationList userId={userId} />}
    </Container>
  );
};

export default Home;
