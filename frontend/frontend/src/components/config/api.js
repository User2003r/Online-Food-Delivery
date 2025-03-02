import axios from "axios";

export const API_URL = "http://localhost:8080";

export const api = axios.create({
  basedURL: API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});
