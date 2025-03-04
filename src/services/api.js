import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api"; // Change this to your backend URL

export const getRecommendations = async (userId, count) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/recommendations`, {
      params: { userId, count },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching recommendations:", error);
    return [];
  }
};
