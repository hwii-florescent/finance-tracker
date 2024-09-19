import { useState } from "react";
import axios from "axios";
import { useRouter } from "next/router";
import styles from "../styles/Login.module.css"; // Add your styles here

export default function LoginPage() {
  const router = useRouter();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.get("http://localhost:8080/api/v1/auth");

      if (response.status === 200) {
        // Save the auth token to localStorage (or use cookies for better security)
        localStorage.setItem("authToken", response.data.token);
        // Redirect to the home page after successful login
        router.push("/home");
      }
    } catch (err) {
      setError("Incorrect username or password");
    }
  };

  return (
    <div className={styles.container}>
      <h1>Login</h1>
      <form onSubmit={handleSubmit} className={styles.form}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit">Login</button>
      </form>
      {error && <p>{error}</p>}
    </div>
  );
}
