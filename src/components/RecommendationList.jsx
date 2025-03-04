import React, { useEffect, useState } from "react";
import { getRecommendations } from "../services/api";
import { Card, CardContent, Typography, CircularProgress } from "@mui/material";

const RecommendationList = ({ userId }) => {
  const [recommendations, setRecommendations] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchRecommendations = async () => {
      setLoading(true);
      const data = await getRecommendations(userId, 5); // Fetch top 5 recommendations
      setRecommendations(data);
      setLoading(false);
    };

    fetchRecommendations();
  }, [userId]);

  return (
    <div>
      <Typography variant="h5">Recommended for You</Typography>
      {loading ? (
        <CircularProgress />
      ) : (
        recommendations.map((item, index) => (
          <Card key={index} style={{ margin: "10px 0" }}>
            <CardContent>
              <Typography variant="h6">Item ID: {item}</Typography>
            </CardContent>
          </Card>
        ))
      )}
    </div>
  );
};

export default RecommendationList;
