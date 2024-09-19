import { useRouter } from 'next/router';
import { useEffect } from 'react';
import styles from '../styles/Home.module.css'; // Add your styles here

export default function HomePage() {
  const router = useRouter();

  useEffect(() => {
    // Check if the user is authenticated (token exists)
    const token = localStorage.getItem('authToken');
    if (!token) {
      // If no token is found, redirect to the login page
      router.push('/login');
    }
  }, [router]);

  const handleLogout = () => {
    // Remove the token and redirect to login
    localStorage.removeItem('authToken');
    router.push('/login');
  };

  return (
    <div className={styles.container}>
      <h1>Welcome to the Application</h1>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}
