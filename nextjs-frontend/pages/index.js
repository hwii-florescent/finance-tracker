import { useEffect } from "react";
import { useRouter } from "next/router";

export default function Home() {
  const router = useRouter();

  useEffect(() => {
    // Redirect to the login page when the home page is accessed
    router.push("/login");
  }, [router]);

  return null; // Nothing to render since it redirects to /login
}
