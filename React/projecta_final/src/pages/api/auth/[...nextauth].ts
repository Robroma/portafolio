import NextAuth from "next-auth";
import Credentials from "next-auth/providers/credentials";
import axios from "axios";

interface UserData {
  description: UserDescription;
  displayName: string;
}

interface UserDescription {
  user: string;
  group: string;
  domain: string;
}

interface ICredentials {
  username: string,
  password: string,
}


export default NextAuth({
  providers: [
    Credentials({
      id: "credentials",
      name: "Credentials",
      credentials: {
        email: {
          label: "Email",
          type: "text",
        },
        password: {
          label: "Password",
          type: "password",
        },
      },
      async authorize(credentials) {
        if (!credentials?.email || !credentials?.password) {
          console.log("1");
          throw new Error("Email and password required");
        }

        // const credent: ICredentials = {
        //   username: credentials.email,
        //   password: credentials.password,
        // }
        // const response = await axios.post("http://localhost:3002/login", credent);
        // const ldapOutput: string = response.data;
        // const descriptionStr =
        //   ldapOutput?.match(/Description: (.+?)<br>/)?.[1] ?? "";
        // const [, user, group, domain] =
        //   descriptionStr.match(/^User: (.+?); Group: (.+?); Domain: (.+)$/) ??
        //   [];
        // const displayName =
        //   ldapOutput.match(/Display Name: (.+?)<br>/)?.[1] ?? "";
        const user = {
          id: "asdasd",
          name: "sddsads",
          email: "Robert Torro Martinez",
          image: "inf"
        }

        if (!user) {
          throw new Error("Invalid email or password");
        }


        return {
          id: user.id,
          name: user.name,
          email: user.email,
          image: user.image,
        };
      },
    }),
  ],
  pages: {
    signIn: "/auth",
  },
  secret: "LlKq6ZtYbr+hTC073mAmAh9/h2HwMfsFo4hrfCx5mLg=",
});