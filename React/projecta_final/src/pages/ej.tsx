import React, { useEffect, useState } from "react";
import axios from "axios";
import { useSession, signIn } from "next-auth/react";



interface IUsuari {
  id: number;
  email: string;
  password: string;
  address: string;
  address2: string;
  city: string;
  province: string;
  zip: string;
  student: string;
  time: string;
}

// async function login(username: string, password: string) {
//   try {
//     const response = await axios.post('http://localhost:3002/api/login', {
//       username,
//       password,
//     });

//     if (response.status === 200) {
//       const { token } = response.data;
//       localStorage.setItem('token', token);
//       console.log('perara2');
//     } else {
//       console.log('perara3');
//       console.log(response.data.error);
//     }
//   } catch (error) {
//     console.log('perara4')
//     console.error(error);
//   }
// }

const MyComponent = () => {
  interface UserData {
    description: UserDescription;
    displayName: string;
  }

  interface UserDescription {
    user: string;
    group: string;
    domain: string;
  }

  const data = {
    username: "a211227rt",
    password: "Q0725FXT",
  };

  const [aux, setAux] = useState();
  const getMYSQl = () =>{
    const { data: session } = useSession();

    const nom = session?.user?.email?.split(" ")[0];
    const cognoms = session?.user?.email?.split(" ").slice(1).join(" ");
    const rol = session?.user?.image === "inf" ? 1 : 0;

    // Y aquí hacemos la petición a tu API para hacer el insert
     axios.post("http://localhost:3002/api/insertUsuari", {
      usuID: session?.user?.name,
      nom: nom,
      cognoms: cognoms,
      rol: rol, // Aquí deberías usar el rol que corresponda
      classe: session?.user?.image, // Y aquí la clase que corresponda
    });
  }

  const [usuari, setUsuari] = useState<UserData>();
  const getLdap = () =>
    axios
      .post("http://localhost:3002/login", data)
      .then(function (response) {
        const ldapOutput: string = response.data;
        const descriptionStr =
          ldapOutput?.match(/Description: (.+?)<br>/)?.[1] ?? "";
        const [, user, group, domain] =
          descriptionStr.match(/^User: (.+?); Group: (.+?); Domain: (.+)$/) ??
          [];
        const displayName =
          ldapOutput.match(/Display Name: (.+?)<br>/)?.[1] ?? "";

          console.log(user)
          console.log(group)
          console.log(domain)
          console.log(displayName)
        setUsuari({
          description: {
            user,
            group,
            domain,
          },
          displayName,
        });
        console.log(usuari)
      })
      .catch(function (error) {
        console.log(error);
      });

  return (
    <div>
      <h1>{usuari?.description.user}</h1>
      <button onClick={getMYSQl} > msql</button>
      <button onClick={getLdap}> ldap</button>
    </div>
  );
};

export default MyComponent;
