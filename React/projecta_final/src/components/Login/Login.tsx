import { useSession, signIn } from "next-auth/react";
import { useRouter } from "next/router";
import { useCallback, useState } from "react";
import axios from "axios";

const Login = () => {
  const router = useRouter();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const loginCall = useCallback(
    async (e: React.FormEvent<HTMLFormElement>) => {
      e.preventDefault();
      if (email === "") {
        alert("Empty user");
      } else if (password === "") {
        alert("Empty password");
      } else {
        try {
          const log = await signIn("credentials", {
            email,
            password,
            redirect: false,
            callbackUrl: "/",
          });
          router.push("/");          

          if (log?.status === 401) {
            alert("Invalid email or password");
          }
        } catch (error) {
          console.log(error);
        }
      }
    },
    [email, password, router]
  );

  return (
    <div className="container" onClick={() => {}}>
      <div className="top">
        <div className="bottom">
          <form className="center" onSubmit={loginCall}>
            <h2>Please Log In</h2>
            <input
              type="text"
              placeholder="user"
              onChange={(e) => setEmail(e.target.value)}
            />
            <input
              type="password"
              placeholder="password"
              onChange={(e) => setPassword(e.target.value)}
            />
            <button className="logButton">Log in</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
